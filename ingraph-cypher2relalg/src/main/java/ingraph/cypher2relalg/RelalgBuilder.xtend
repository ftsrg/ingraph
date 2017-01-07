package ingraph.cypher2relalg

import ingraph.cypher2relalg.util.Cypher2RelalgUtil
import ingraph.cypher2relalg.util.IngraphLogger
import ingraph.cypher2relalg.util.Validator
import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.util.EcoreUtil
import org.slizaa.neo4j.opencypher.openCypher.CaseExpression
import org.slizaa.neo4j.opencypher.openCypher.Cypher
import org.slizaa.neo4j.opencypher.openCypher.ExpressionAnd
import org.slizaa.neo4j.opencypher.openCypher.ExpressionComparison
import org.slizaa.neo4j.opencypher.openCypher.ExpressionList
import org.slizaa.neo4j.opencypher.openCypher.ExpressionMulDiv
import org.slizaa.neo4j.opencypher.openCypher.ExpressionNodeLabelsAndPropertyLookup
import org.slizaa.neo4j.opencypher.openCypher.ExpressionOr
import org.slizaa.neo4j.opencypher.openCypher.ExpressionPlusMinus
import org.slizaa.neo4j.opencypher.openCypher.ExpressionPower
import org.slizaa.neo4j.opencypher.openCypher.ExpressionXor
import org.slizaa.neo4j.opencypher.openCypher.FunctionInvocation
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
import org.slizaa.neo4j.opencypher.openCypher.RelationshipsPattern
import org.slizaa.neo4j.opencypher.openCypher.Return
import org.slizaa.neo4j.opencypher.openCypher.SingleQuery
import org.slizaa.neo4j.opencypher.openCypher.StringConstant
import org.slizaa.neo4j.opencypher.openCypher.Unwind
import org.slizaa.neo4j.opencypher.openCypher.VariableRef
import relalg.ArithmeticComparisonOperatorType
import relalg.ArithmeticExpression
import relalg.AttributeVariable
import relalg.BinaryArithmeticOperatorType
import relalg.BinaryLogicalOperatorType
import relalg.ComparableExpression
import relalg.Direction
import relalg.ExpandOperator
import relalg.Expression
import relalg.FunctionExpression
import relalg.JoinOperator
import relalg.LeftOuterJoinOperator
import relalg.LogicalExpression
import relalg.MaxHopsType
import relalg.NumberLiteral
import relalg.Operator
import relalg.OrderDirection
import relalg.RelalgContainer
import relalg.RelalgFactory
import relalg.UnaryLogicalOperatorType
import relalg.UnaryNodeLogicalOperatorType
import relalg.UnaryOperator
import relalg.function.Function

/**
 * This is the main class of the openCypher to relational algebra compiler.
 *
 * Its main entry point is the {@link #build(Cypher) build} method.
 * As a helper class to manage the whole process of openCypher parsing and compilation,
 * you might want to see the helper methods in the {@link Cypher2Relalg} class.
 */
class RelalgBuilder {

  extension RelalgFactory factory = RelalgFactory.eINSTANCE
  extension IngraphLogger logger = new IngraphLogger(RelalgBuilder.name)
  extension Cypher2RelalgUtil cypher2RelalgUtil = new Cypher2RelalgUtil(logger)

  // this will be returned and will hold the result of the compilation
  // never rename this to container as that name will collide with the Expression.container field name
  val RelalgContainer topLevelContainer
  val VariableBuilder variableBuilder

  // default constructor, the only public one
  new() {
    this.topLevelContainer = createRelalgContainer
    this.variableBuilder = new VariableBuilder(this.topLevelContainer, logger)
  }

  /**
   * Constructor that allows propagating the topLevelContainer and the
   * variable builder instance.
   *
   * Use this to create separate builder for each SingleQuery,
   * and usually you might want to pass a clone of your variable builder created using its
   * {@link VariableBuilder#cloneBuilderWithNewVariableFactories cloneBuilderWithNewVariableFactories}
   * call.
   */
  protected new(RelalgContainer topLevelContainer, VariableBuilder variableBuilder) {
    this.topLevelContainer = topLevelContainer
    this.variableBuilder = variableBuilder
  }

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

  /**
   * Builds the relational algebra expression for a single query by spawning a new builder
   * with the same top-level container, but with own factories for variables.
   */
  def dispatch Operator buildRelalg(SingleQuery q) {
    /*
     * we create a new instance of the RelalgBuilder to use separate variable factories
     * for each singleQuery, but we retain the the label factories as well as
     * top-level container not to break the containment hierarchy and .
     */
    val builder = new RelalgBuilder(topLevelContainer, variableBuilder.cloneBuilderWithNewVariableFactories)
    builder._buildRelalgSingleQuery(q)
  }

  /**
   * This is the workhorse for building the relational algebra expression for a single query.
   *
   * It should not be called to build more than one single query as
   * the variables produced for re-used names would collide.
   */
  def protected Operator _buildRelalgSingleQuery(SingleQuery q) {
    val clauses = q.clauses

    // do some checks on the MATCH clauses
    Validator.checkMatchClauseSequence(clauses.filter(typeof(Match)), logger)

    /*
     * We compile all MATCH clauses and attach to a (left outer) join operator.
     * The first one will not be used, but its rightOperand will be extracted, though.
     */
    val singleQuery_MatchList = clauses.filter(typeof(Match)).map [
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

    //val singleQuery_unwindClauseList = 
    clauses.filter(typeof(Unwind)).forEach[buildRelalgUnwind(it, content)]

    val singleQuery_returnClauseList = clauses.filter(typeof(Return)).map[buildRelalgReturn(it, content)]

    if (singleQuery_returnClauseList === null || singleQuery_returnClauseList.empty) {
      unsupported('''We received no RETURN clauses but a node retrieval query must end with exactly one. However, node creating queries can skip RETURN clause, but they are not supported yet.''')
      null
    } else if (singleQuery_returnClauseList.length == 1) {
      singleQuery_returnClauseList.head
    } else {
      unrecoverableError('''More than one return clauses received. We received actually «singleQuery_returnClauseList.length».''')
      null
    }
  }

  def UnaryOperator buildRelalgUnwind(Unwind u, Operator content) {
    // FIXME: The value of the local variable unwindOperator is not used
    val unwindOperator = createUnwindOperator => [
      sourceVariable = variableBuilder.buildRelalgVariable(u.expression)
      targetVariable = null // TODO createElement => [ ]
    ]
    null
  }

  def UnaryOperator buildRelalgReturn(Return r, Operator content) {
    // FIXME: add * handling
    val returnBody = r.body

    val trimmer = createProjectionOperator => [
      input = content
      elements.addAll(
        returnBody.returnItems.get(0).items.map [ returnItem |
          createReturnableElement => [
            expression = buildRelalgExpression(returnItem.expression)
            alias = returnItem.alias?.name
          ]
        ]
      )
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
    val op2 = if (order !== null) {
        val sortEntries = order.orderBy.map [
          val sortDirection = if(sort !== null && sort.startsWith("DESC")) OrderDirection.
              DESCENDING else OrderDirection.ASCENDING

          val sortVariable = variableBuilder.buildRelalgVariable(expression)
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
    val op3 = if (skip !== null || limit !== null) {
        createTopOperator => [
          skip = skip?.skip?.expressionToSkipLimitConstant
          limit = limit?.limit?.expressionToSkipLimitConstant
          input = op2
        ]
      } else {
        op2
      }

    op3
  }

  def expressionToSkipLimitConstant(org.slizaa.neo4j.opencypher.openCypher.Expression expression) {
    if (expression instanceof NumberConstant) {
      createSkipLimitValue => [value = Integer.parseInt(expression.value)]
    } else {
      unsupported('''Only NumberConstants are supported as SKIP/LIMIT values, got «expression»''')
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

    if (m.where !== null) {
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
      leftOperand = variableBuilder.buildRelalgVariable(e.left)
      container = topLevelContainer
    ]
  }

  def dispatch LogicalExpression buildRelalgLogicalExpression(IsNullExpression e, EList<Operator> joins) {
    createUnaryNodeLogicalExpression => [
      operator = UnaryNodeLogicalOperatorType.IS_NULL
      leftOperand = variableBuilder.buildRelalgVariable(e.left)
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
      leftOperand = variableBuilder.buildVertexVariable(e.nodePattern)
      container = topLevelContainer
    ])

    relationshipVariableExpressions.addAll(
      e.chain.map [
        val mapIt = it
        createUnaryNodeLogicalExpression => [
          operator = UnaryNodeLogicalOperatorType.IS_NOT_NULL
          leftOperand = variableBuilder.buildEdgeVariable(mapIt.relationshipPattern.detail)
          container = topLevelContainer
        ]
      ]
    )
    relationshipVariableExpressions.addAll(
      e.chain.map [
        val mapIt = it
        createUnaryNodeLogicalExpression => [
          operator = UnaryNodeLogicalOperatorType.IS_NOT_NULL
          leftOperand = variableBuilder.buildVertexVariable(mapIt.nodePattern)
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
    createVariableComparableExpression => [
      variable = variableBuilder.buildRelalgVariable(e)
      container = topLevelContainer
    ]
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
    val x = variableBuilder.buildRelalgVariable(e)
    // as AttributeVariable
    if (x instanceof AttributeVariable) {
      createVariableComparableExpression => [
        variable = x
        container = topLevelContainer
      ]
    } else {
      unsupported('''Unsupported type received: «x.class.name»''')
      null
    }
  }

  def dispatch ComparableExpression buildRelalgComparableElement(Parameter p) {
    val relalgParameter = createParameter => [name = p.parameter]
    relalgParameter
  }

  def dispatch ComparableExpression buildRelalgComparableElement(FunctionInvocation fi) {
    val fe = createFunctionComparableExpression => [
      container = topLevelContainer
    ]

    populateFunctionExpression(fe, fi)

    fe
  }

  def dispatch Expression buildRelalgExpression(VariableRef e) {
    variableBuilder.buildRelalgVariable(e)
  }

  def dispatch Expression buildRelalgExpression(NumberConstant e) {
    buildRelalgArithmeticExpression(e)
  }

  def dispatch Expression buildRelalgExpression(CaseExpression e) {
    if (!(e.expression instanceof CaseExpression)) {
      unsupported("Outer CaseExpressions should contain a CaseExpression")
    }

    val caseExpression = e.expression as CaseExpression
    val simpleCaseExpression = createSimpleCaseExpression
    // CASE test
    simpleCaseExpression.test = buildRelalgExpression(caseExpression.caseExpression)

    // WHEN when THEN then
    caseExpression.caseAlternatives.forEach [
      val whenExpression = buildRelalgComparableElement(when)
      val thenExpression = buildRelalgComparableElement(then)
      val case_ = createCase => [when = whenExpression; then = thenExpression]
      simpleCaseExpression.cases.add(case_)
    ]
    // ELSE elseExpression
    simpleCaseExpression.default_ = buildRelalgArithmeticExpression(caseExpression.elseExpression)
    simpleCaseExpression
  }

  def dispatch Expression buildRelalgExpression(FunctionInvocation fi) {
    val fe = createFunctionExpression => [
      container = topLevelContainer
    ]

    populateFunctionExpression(fe, fi)

    fe
  }

  def dispatch Expression buildRelalgExpression(ExpressionNodeLabelsAndPropertyLookup e) {
    variableBuilder.buildRelalgVariable(e)
  }

  def dispatch Expression buildRelalgExpression(ExpressionList el) {
    val emptyList = createEmptyListExpression => [
      head = null
      tail = null
      container = topLevelContainer
    ]
    // the tail of the first expression will be the list that was built
    val first = createListExpression => [
      tail = emptyList
    ]
    var recent = first

    for (e: el.expressions) {
      recent.tail = createListExpression => [
        head = buildRelalgExpression(e)
        tail = emptyList
        container = topLevelContainer
      ]
      recent = recent.tail
    }

    first.tail
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

  def dispatch ArithmeticExpression buildRelalgArithmeticExpression(ExpressionNodeLabelsAndPropertyLookup e) {
    createVariableArithmeticExpression => [
      variable = variableBuilder.buildRelalgVariable(e)
      container = topLevelContainer
    ]
  }

  def dispatch ArithmeticExpression buildRelalgArithmeticExpression(FunctionInvocation fi) {
    val fe = createFunctionArithmeticExpression => [
      container = topLevelContainer
    ]

    populateFunctionExpression(fe, fi)
    // TODO isnumeric

    fe
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
    if (p.^var !== null) {
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
      vertexVariable = variableBuilder.buildVertexVariable(n)
    ]
    val patternElement_ExpandList = chain.map[buildRelalg(it) as ExpandOperator]

    chainExpandOperators(patternElement_GetVerticesOperator, patternElement_ExpandList)
  }

  def dispatch Operator buildRelalg(PatternElementChain ec) {
    val patternElementChain_VertexVariable = variableBuilder.buildVertexVariable(ec.nodePattern)
    val patternElementChain_EdgeVariable = variableBuilder.buildEdgeVariable(ec.relationshipPattern.detail)

    val isLeftArrow = ec.relationshipPattern.incoming
    val isRightArrow = ec.relationshipPattern.outgoing

    val range = ec.relationshipPattern.detail.range

    createExpandOperator() => [
      edgeVariable = patternElementChain_EdgeVariable;
      direction = if (isLeftArrow && isRightArrow || ! (isLeftArrow || isRightArrow))
        Direction.BOTH
      else if(isLeftArrow) Direction.IN else Direction.OUT;
      targetVertexVariable = patternElementChain_VertexVariable;
      minHops = if(range === null) 1 else Integer.valueOf(ec.relationshipPattern.detail.range.lower)
      maxHops = if (range === null)
        createMaxHops() => [
          maxHopsType = MaxHopsType.LIMITED
          hops = 1
        ]
      else
        createMaxHops() => [
          maxHopsType = if(range.upper !== null) MaxHopsType.LIMITED else MaxHopsType.UNLIMITED;
          hops = if(range.upper !== null) Integer.valueOf(ec.relationshipPattern.detail.range.upper);
        ]
    ]

  }

  def void populateFunctionExpression(FunctionExpression fe, FunctionInvocation fi) {
    fe.functor = Function.valueOf(fi.functionName.name.toUpperCase)
    fe.arguments.addAll(fi.parameter.map[buildRelalgExpression(it)])
  }

//  def dispatch buildRelalg(Cypher rule) {
//    println(String::format("received unsupported cypher element: %s", rule.class.toString()))
//  }
}
