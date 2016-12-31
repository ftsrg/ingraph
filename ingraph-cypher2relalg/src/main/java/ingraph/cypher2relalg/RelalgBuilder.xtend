package ingraph.cypher2relalg

import ingraph.cypher2relalg.factories.EdgeLabelFactory
import ingraph.cypher2relalg.factories.EdgeVariableFactory
import ingraph.cypher2relalg.factories.VertexLabelFactory
import ingraph.cypher2relalg.factories.VertexVariableFactory
import ingraph.cypher2relalg.util.Cypher2RelalgUtil
import ingraph.cypher2relalg.util.ElementVariableUtil
import ingraph.cypher2relalg.util.IngraphLogger
import ingraph.cypher2relalg.util.Validator
import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.util.EcoreUtil
import org.slizaa.neo4j.opencypher.openCypher.Cypher
import org.slizaa.neo4j.opencypher.openCypher.ExpressionAnd
import org.slizaa.neo4j.opencypher.openCypher.ExpressionComparison
import org.slizaa.neo4j.opencypher.openCypher.ExpressionMulDiv
import org.slizaa.neo4j.opencypher.openCypher.ExpressionNodeLabelsAndPropertyLookup
import org.slizaa.neo4j.opencypher.openCypher.ExpressionOr
import org.slizaa.neo4j.opencypher.openCypher.ExpressionPlusMinus
import org.slizaa.neo4j.opencypher.openCypher.ExpressionPower
import org.slizaa.neo4j.opencypher.openCypher.ExpressionXor
import org.slizaa.neo4j.opencypher.openCypher.IsNotNullExpression
import org.slizaa.neo4j.opencypher.openCypher.IsNullExpression
import org.slizaa.neo4j.opencypher.openCypher.Match
import org.slizaa.neo4j.opencypher.openCypher.NodePattern
import org.slizaa.neo4j.opencypher.openCypher.NumberConstant
import org.slizaa.neo4j.opencypher.openCypher.Parameter
import org.slizaa.neo4j.opencypher.openCypher.ParenthesizedExpression
import org.slizaa.neo4j.opencypher.openCypher.PatternElement
import org.slizaa.neo4j.opencypher.openCypher.PatternElementChain
import org.slizaa.neo4j.opencypher.openCypher.PatternPart
import org.slizaa.neo4j.opencypher.openCypher.RegularQuery
import org.slizaa.neo4j.opencypher.openCypher.RelationshipDetail
import org.slizaa.neo4j.opencypher.openCypher.RelationshipsPattern
import org.slizaa.neo4j.opencypher.openCypher.Return
import org.slizaa.neo4j.opencypher.openCypher.ReturnItems
import org.slizaa.neo4j.opencypher.openCypher.SingleQuery
import org.slizaa.neo4j.opencypher.openCypher.StringConstant
import org.slizaa.neo4j.opencypher.openCypher.VariableRef
import relalg.ArithmeticExpression
import relalg.AttributeVariable
import relalg.ComparableExpression
import relalg.Direction
import relalg.EdgeVariable
import relalg.ExpandOperator
import relalg.Expression
import relalg.JoinOperator
import relalg.LeftOuterJoinOperator
import relalg.LogicalExpression
import relalg.MaxHopsType
import relalg.NumberLiteral
import relalg.Operator
import relalg.OrderDirection
import relalg.RelalgFactory
import relalg.UnaryOperator
import relalg.Variable
import relalg.VertexVariable
import relalg.BinaryArithmeticOperatorType
import relalg.ArithmeticComparisonOperatorType
import relalg.BinaryLogicalOperatorType
import relalg.UnaryLogicalOperatorType
import relalg.UnaryNodeLogicalOperatorType

class RelalgBuilder {

  extension RelalgFactory factory = RelalgFactory.eINSTANCE
  extension IngraphLogger logger = new IngraphLogger(RelalgBuilder.name)
  extension Cypher2RelalgUtil cypher2RelalgUtil = new Cypher2RelalgUtil(logger)

  // this will be returned and will hold the result of the compilation
  // never rename this to container as that name will collide with the Expression.container field name
  val topLevelContainer = createRelalgContainer

  extension ElementVariableUtil elementVariableUtil = new ElementVariableUtil(topLevelContainer)

  val vertexVariableFactory = new VertexVariableFactory(topLevelContainer)
  val edgeVariableFactory = new EdgeVariableFactory(topLevelContainer)

  val vertexLabelFactory = new VertexLabelFactory(topLevelContainer)
  val edgeLabelFactory = new EdgeLabelFactory(topLevelContainer)

  def build(Cypher cypher) {
    EcoreUtil.resolveAll(cypher)

//    println(PrettyPrinter.format(cypher))
    val statement = cypher.statement
    topLevelContainer.rootExpression = buildRelalg(statement)

    topLevelContainer
  }

  def dispatch Operator buildRelalg(RegularQuery q) {
    val queryListHead = buildRelalg(q.singleQuery)
    val queryListTail = q.union?.map [
      val mapIt = it
      createUnionOperator => [
        bag = mapIt.all
        rightInput = buildRelalg(mapIt.singleQuery)
      ]
    ]

    chainBinaryOperatorsLeft(queryListHead, queryListTail)
  }

  def dispatch Operator buildRelalg(SingleQuery q) {
    val clauses = q.clauses

    // do some checks on the MATCH clauses
    Validator.checkMatchClauseSequence(clauses.filter(typeof(Match)), logger)

    /*
     * We compile all MATCH clauses and attach to a (left outer) join operator.
     * The first one will not be used, but its rightOperand will be extracted, though.
     */
    val singleQuery_MatchList = clauses.filter(typeof(Match)).map[
      val mapIt = it
      val joinOp = if (mapIt.optional) {
        createLeftOuterJoinOperator
      } else {
        createJoinOperator
      }
      joinOp => [
        rightInput = buildRelalg(mapIt)
      ]
    ]

    /*
     * The compiled form of the first MATCH clause is on the rightInput
     * of the first join operator.
     *
     * This join operator is in fact, unnecessary.
     */
    val content = chainBinaryOperatorsLeft(singleQuery_MatchList?.head?.rightInput, singleQuery_MatchList?.tail)

    val singleQuery_returnClauseList = clauses.filter(typeof(Return)).map[buildRelalgReturn(it, content)]

    if (singleQuery_returnClauseList == null || singleQuery_returnClauseList.empty) {
      unsupported('''We received no RETURN clauses but a node retrieval query must end with exactly one. However, node creating queries can skip RETURN clause, but they are not supported yet.''')
      null
    } else if (singleQuery_returnClauseList.length == 1) {
      singleQuery_returnClauseList.
        head
    } else {
      unrecoverableError('''More than one return clauses received. We received actually «singleQuery_returnClauseList.length».''')
      null
    }
  }

  def UnaryOperator buildRelalgReturn(Return r, Operator content) {
    // FIXME: add * handling
    val returnBody = r.body as ReturnItems

    val trimmer = createProjectionOperator => [
      input = content
      elements.addAll(returnBody.items.map [
        val mapIt = it
        createReturnableElement => [
          expression = buildRelalgExpression(mapIt.expression)
          alias = mapIt.alias?.name
        ]
      ])
    // variables.addAll(returnBody.items.map[buildRelalgComparableElement(it.expression) as relalg.Variable])
    ]

    // add duplicate-elimination operator if return DISTINCT was specified
    val op1 = if (r.distinct) {
      createDuplicateEliminationOperator => [
        input = trimmer
      ]
    } else {
      trimmer
    }

    val order = returnBody.order
    val op2 = if (order != null) {
      val sortEntries = order.orderBy.map[
        val sortDirection = if (sort.startsWith("ASC")) OrderDirection.ASCENDING else OrderDirection.DESCENDING

        val sortVariableName = (expression as VariableRef).variableRef.name
        val sortVariable = vertexVariableFactory.createElement(sortVariableName)
        createSortEntry => [
          direction = sortDirection
          variable = sortVariable
        ]
      ]
      createSortOperator => [
        entries.addAll(sortEntries)
        input = op1
      ]
    } else {
      op1
    }

    val skip = returnBody.skip
    val limit = returnBody.limit
    val op3 = if (skip != null || limit != null) {
      createTopOperator => [
        skip = skip?.skip.expressionToInteger
        limit = limit?.limit.expressionToInteger
        input = op2
      ]
    } else {
      op2
    }

    op3
  }

  def Integer expressionToInteger(org.slizaa.neo4j.opencypher.openCypher.Expression expression) {
    if (expression instanceof NumberConstant) {
      Integer.parseInt(expression.value)
    } else {
      null
    }
  }

  /*
   * MATCH clause is compiled as follows:
   * (the lower elements being the input for the upper ones)
   *
   * - Selection as built from the where clause
   * - Left outer join of the patterns extracted from the where clause (is any)
   * - AllDifferentOperator on the edges in the patternParts
   * - natural join of comma-separated patternParts in the MATCH clause
   */
  def dispatch Operator buildRelalg(Match m) {
    // FIXME: handle OPTIONAL
    // handle comma-separated patternParts in the MATCH clause
    val pattern_PatternPartList = m.pattern.patterns.map[buildRelalg(it)]

    // they are natural joined together
    val allDifferentOperator = createAllDifferentOperator => [
      input = buildLeftDeepTree(typeof(JoinOperator), pattern_PatternPartList?.iterator)
    ]

    if (m.where != null) {
      // left outer joins extracted from the patterns in the where clause
      val EList<Operator> joinOperationsOfWhereClause = new BasicEList<Operator>()

      val selectionOperator = createSelectionOperator => [
        condition = buildRelalgLogicalExpression(m.where.expression, joinOperationsOfWhereClause)
      ]

      val selectionInput = if (joinOperationsOfWhereClause.empty) {
          allDifferentOperator
        } else {
          /*
           * add allDifferentOperator before the joins derived from the where clause
           * and create the tree of left outer joins
           */
          val EList<Operator> h = new BasicEList<Operator>()
          h.add(allDifferentOperator)
          h.addAll(joinOperationsOfWhereClause)

          buildLeftDeepTree(typeof(LeftOuterJoinOperator), h.iterator)
        }

      selectionOperator.input = selectionInput
      selectionOperator
    } else {
      allDifferentOperator
    }
  }

  def dispatch LogicalExpression buildRelalgLogicalExpression(ExpressionAnd e, EList<Operator> joins) {
    createBinaryLogicalExpression => [
      operator = BinaryLogicalOperatorType.AND
      leftOperand = buildRelalgLogicalExpression(e.left, joins)
      rightOperand = buildRelalgLogicalExpression(e.right, joins)
      container = topLevelContainer
    ]
  }

  def dispatch LogicalExpression buildRelalgLogicalExpression(ExpressionOr e, EList<Operator> joins) {
    createBinaryLogicalExpression => [
      operator = BinaryLogicalOperatorType.OR
      leftOperand = buildRelalgLogicalExpression(e.left, joins)
      rightOperand = buildRelalgLogicalExpression(e.right, joins)
      container = topLevelContainer
    ]
  }

  def dispatch LogicalExpression buildRelalgLogicalExpression(ExpressionXor e, EList<Operator> joins) {
    createBinaryLogicalExpression => [
      operator = BinaryLogicalOperatorType.XOR
      leftOperand = buildRelalgLogicalExpression(e.left, joins)
      rightOperand = buildRelalgLogicalExpression(e.right, joins)
      container = topLevelContainer
    ]
  }

  def dispatch LogicalExpression buildRelalgLogicalExpression(IsNotNullExpression e, EList<Operator> joins) {
    createUnaryNodeLogicalExpression => [
      operator = UnaryNodeLogicalOperatorType.IS_NOT_NULL
      leftOperand = buildRelalgVariable(e.left)
      container = topLevelContainer
    ]
  }

  def dispatch LogicalExpression buildRelalgLogicalExpression(IsNullExpression e, EList<Operator> joins) {
    createUnaryNodeLogicalExpression => [
      operator = UnaryNodeLogicalOperatorType.IS_NULL
      leftOperand = buildRelalgVariable(e.left)
      container = topLevelContainer
    ]
  }

  def dispatch LogicalExpression buildRelalgLogicalExpression(
    org.slizaa.neo4j.opencypher.openCypher.Expression e,
    EList<Operator> joins
  ) {
    switch e.operator.toLowerCase {
      case "not":
        createUnaryLogicalExpression => [
          operator = UnaryLogicalOperatorType.NOT
          leftOperand = buildRelalgLogicalExpression(e.left, joins)
          container = topLevelContainer
        ]
      default: {
        unsupported("TODO: " + e.operator)
        null
      }
    }
  }

  def dispatch LogicalExpression buildRelalgLogicalExpression(ParenthesizedExpression e, EList<Operator> joins) {
    buildRelalgLogicalExpression(e.expression, joins)
  }

  def dispatch LogicalExpression buildRelalgLogicalExpression(RelationshipsPattern e, EList<Operator> joins) {
    // We add all the variables in the pattern as a NOT NULL expression
    // TODO: add the pattern itself as an outer join
    val EList<LogicalExpression> relationshipVariableExpressions = new BasicEList<LogicalExpression>()

    relationshipVariableExpressions.add(createUnaryNodeLogicalExpression => [
      operator = UnaryNodeLogicalOperatorType.IS_NOT_NULL
      leftOperand = buildVertexVariable(e.nodePattern)
      container = topLevelContainer
    ])

    relationshipVariableExpressions.addAll(
      e.chain.map [
        val mapIt = it
        createUnaryNodeLogicalExpression => [
          operator = UnaryNodeLogicalOperatorType.IS_NOT_NULL
          leftOperand = buildEdgeVariable(mapIt.relationshipPattern.detail)
          container = topLevelContainer
        ]
      ]
    )
    relationshipVariableExpressions.addAll(
      e.chain.map [
        val mapIt = it
        createUnaryNodeLogicalExpression => [
          operator = UnaryNodeLogicalOperatorType.IS_NOT_NULL
          leftOperand = buildVertexVariable(mapIt.nodePattern)
          container = topLevelContainer
        ]
      ]
    )

    joins.add(buildRelalg(e))

    buildLeftDeepTree(BinaryLogicalOperatorType.AND, relationshipVariableExpressions.iterator, topLevelContainer)
  }

  def dispatch LogicalExpression buildRelalgLogicalExpression(ExpressionComparison e, EList<Operator> joins) {
    // FIXME: add type check to ensure that the operands are comparable
    createArithmeticComparisonExpression => [
      operator = switch e.operator {
        case "=": ArithmeticComparisonOperatorType.EQUAL_TO
        case "!=",
        case "<>": ArithmeticComparisonOperatorType.NOT_EQUAL_TO
        case "<": ArithmeticComparisonOperatorType.LESS_THAN
        case "<=": ArithmeticComparisonOperatorType.LESS_THAN_OR_EQUAL
        case ">": ArithmeticComparisonOperatorType.GREATER_THAN
        case ">=": ArithmeticComparisonOperatorType.GREATER_THAN_OR_EQUAL
      }
      leftOperand = buildRelalgComparableElement(e.left)
      rightOperand = buildRelalgComparableElement(e.right)
      container = topLevelContainer
    ]
  }

  def dispatch ComparableExpression buildRelalgComparableElement(NumberConstant e) {
    buildRelalgNumberLiteral(e)
  }

  def dispatch ComparableExpression buildRelalgComparableElement(StringConstant e) {
    createStringLiteral => [
      value = e.value
      container = topLevelContainer
    ]
  }

  def dispatch ComparableExpression buildRelalgComparableElement(VariableRef e) {
    buildRelalgVariable(e)
  }

  def dispatch ComparableExpression buildRelalgComparableElement(ExpressionPlusMinus e) {
    buildRelalgArithmeticExpression(e)
  }

  def dispatch ComparableExpression buildRelalgComparableElement(ExpressionMulDiv e) {
    buildRelalgArithmeticExpression(e)
  }

  def dispatch ComparableExpression buildRelalgComparableElement(ExpressionPower e) {
    buildRelalgArithmeticExpression(e)
  }

  def dispatch ComparableExpression buildRelalgComparableElement(ExpressionNodeLabelsAndPropertyLookup e) {
    val x = buildRelalgVariable(e)
    // as AttributeVariable
    if (x instanceof AttributeVariable) {
      x as AttributeVariable
    } else {
      unsupported('''Unsupported type received: «x.class.name»''')
      null
    }
  }

  def dispatch ComparableExpression buildRelalgComparableElement(Parameter p) {
    val relalgParameter = createParameter => [name = p.parameter]
    relalgParameter
  }

  def dispatch Expression buildRelalgExpression(VariableRef e) {
    buildRelalgVariable(e)
  }

  def dispatch Expression buildRelalgExpression(ExpressionNodeLabelsAndPropertyLookup e) {
    buildRelalgVariable(e)
  }

  def dispatch ArithmeticExpression buildRelalgArithmeticExpression(ExpressionPlusMinus e) {
    createArithmeticOperationExpression => [
      operator = switch e.operator {
        case "+": BinaryArithmeticOperatorType.PLUS
        case "-": BinaryArithmeticOperatorType.MINUS
      }
      leftOperand = buildRelalgArithmeticExpression(e.left)
      rightOperand = buildRelalgArithmeticExpression(e.right)
      container = topLevelContainer
    ]

  }

  def dispatch ArithmeticExpression buildRelalgArithmeticExpression(ExpressionMulDiv e) {
    createArithmeticOperationExpression => [
      operator = switch e.operator {
        case "*": BinaryArithmeticOperatorType.MULTIPLICATION
        case "/": BinaryArithmeticOperatorType.DIVISION
        case "%": BinaryArithmeticOperatorType.MOD
      }
      leftOperand = buildRelalgArithmeticExpression(e.left)
      rightOperand = buildRelalgArithmeticExpression(e.right)
      container = topLevelContainer
    ]

  }

  def dispatch ArithmeticExpression buildRelalgArithmeticExpression(ExpressionPower e) {
    createArithmeticOperationExpression => [
      operator = switch e.operator {
        case "^": BinaryArithmeticOperatorType.POWER
      }
      leftOperand = buildRelalgArithmeticExpression(e.left)
      rightOperand = buildRelalgArithmeticExpression(e.right)
      container = topLevelContainer
    ]

  }

  def dispatch ArithmeticExpression buildRelalgArithmeticExpression(NumberConstant e) {
    buildRelalgNumberLiteral(e)
  }

  def dispatch Variable buildRelalgVariable(ExpressionNodeLabelsAndPropertyLookup e) {
    val ev = buildRelalgVariable(e.left)
    switch ev {
      VertexVariable: {
        if (e.propertyLookups.length == 1) {
          ev.createAttribute(e.propertyLookups.get(0).propertyKeyName)
        } else {
          unrecoverableError('''PropertyLookup count «e.propertyLookups.length» not supported.''')
          null
        }
      }
      EdgeVariable: {
        if (e.propertyLookups.length == 1) {
          ev.createAttribute(e.propertyLookups.get(0).propertyKeyName)
        } else {
          unrecoverableError('''PropertyLookup count «e.propertyLookups.length» not supported.''')
          null
        }
      }
      default: {
        unrecoverableError('''Unsupported type received: «ev.class.name»''')
        null
      }
    }
  }

  def dispatch Variable buildRelalgVariable(VariableRef varRef) {
    switch varRef {
      case vertexVariableFactory.hasElement(varRef.variableRef.name) &&
        edgeVariableFactory.hasElement(varRef.variableRef.name): {
        unrecoverableError('''Variable name ambigous: «varRef.variableRef.name»''')
        null
      }
      case vertexVariableFactory.hasElement(varRef.variableRef.name): {
        vertexVariableFactory.createElement(varRef.variableRef.name)
      }
      case edgeVariableFactory.hasElement(varRef.variableRef.name): {
        edgeVariableFactory.createElement(varRef.variableRef.name)
      }
      default: {
        unrecoverableError('''Variable name not found: «varRef.variableRef.name»''')
        null
      }
    }
  }

  def NumberLiteral buildRelalgNumberLiteral(NumberConstant e) {
    try {
      val n = Integer.parseInt(e.value)
      createIntegerLiteral => [
        value = n
        container = topLevelContainer
      ]
    } catch (NumberFormatException ex) {
      createDoubleLiteral => [
        value = Double.parseDouble(e.value)
        container = topLevelContainer
      ]
    }
  }

  def dispatch Operator buildRelalg(PatternPart p) {
    // TODO: handle variable assignment
    if (p.^var != null) {
      unsupported('Variable assignment not supported for PatternPart (in MATCH clause)')
    }
    // pass through variable assignment body to buildRelalg(PatternElement e)
    buildRelalg(p.part)
  }

  def dispatch Operator buildRelalg(PatternElement e) {
    buildRelalgFromPattern(e.nodepattern, e.chain)
  }

  def dispatch Operator buildRelalg(RelationshipsPattern e) {
    buildRelalgFromPattern(e.nodePattern, e.chain)
  }

  /*
   * This will create the relational algebraic representation of a patternElement.
   *
   * This was factored out to handle PatternElement and RelationshipsPattern in the same code
   */
  def Operator buildRelalgFromPattern(NodePattern n, EList<PatternElementChain> chain) {
    val patternElement_GetVerticesOperator = createGetVerticesOperator => [
      vertexVariable = buildVertexVariable(n)
    ]
    val patternElement_ExpandList = chain.map[buildRelalg(it) as ExpandOperator]

    chainExpandOperators(patternElement_GetVerticesOperator, patternElement_ExpandList)
  }

  def dispatch Operator buildRelalg(PatternElementChain ec) {
    val patternElementChain_VertexVariable = buildVertexVariable(ec.nodePattern)
    val patternElementChain_EdgeVariable = buildEdgeVariable(ec.relationshipPattern.detail)

    val isLeftArrow = ec.relationshipPattern.incoming
    val isRightArrow = ec.relationshipPattern.outgoing

    val range = ec.relationshipPattern.detail.range

    createExpandOperator() => [
      edgeVariable = patternElementChain_EdgeVariable;
      direction = if (isLeftArrow && isRightArrow || ! (isLeftArrow || isRightArrow))
        Direction.BOTH
      else if(isLeftArrow) Direction.IN else Direction.OUT;
      targetVertexVariable = patternElementChain_VertexVariable;
      minHops = if(range == null) 1 else Integer.valueOf(ec.relationshipPattern.detail.range.lower)
      maxHops = if (range == null)
        createMaxHops() => [
          maxHopsType = MaxHopsType.LIMITED
          hops = 1
        ]
      else
        createMaxHops() => [
          maxHopsType = if(range.upper != null) MaxHopsType.LIMITED else MaxHopsType.UNLIMITED;
          hops = if(range.upper != null) Integer.valueOf(ec.relationshipPattern.detail.range.upper);
        ]
    ]

  }

  def buildEdgeVariable(RelationshipDetail r) {
    val edgeVariable = edgeVariableFactory.createElement(r)

    // add labels to the variable
    edgeVariable.combineLabelSet(r.types?.relTypeName, edgeLabelFactory)

    edgeVariable
  }

  protected def VertexVariable buildVertexVariable(NodePattern n) {
    val vertexVariable = vertexVariableFactory.createElement(n)

    // add labels to the variable
    n.nodeLabels?.nodeLabels?.forEach [
      vertexVariable.ensureLabel(vertexLabelFactory.createElement(it.labelName))
    ]
    vertexVariable
  }

//  def dispatch buildRelalg(Cypher rule) {
//    println(String::format("received unsupported cypher element: %s", rule.class.toString()))
//  }
}
