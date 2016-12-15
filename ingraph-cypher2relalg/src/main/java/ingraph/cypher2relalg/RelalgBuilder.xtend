package ingraph.cypher2relalg

import ingraph.cypher2relalg.factories.EdgeLabelFactory
import ingraph.cypher2relalg.factories.EdgeVariableFactory
import ingraph.cypher2relalg.factories.VertexLabelFactory
import ingraph.cypher2relalg.factories.VertexVariableFactory
import ingraph.cypher2relalg.util.Cypher2RelalgUtil
import ingraph.cypher2relalg.util.ElementVariableUtil
import ingraph.cypher2relalg.util.IngraphLogger
import ingraph.emf.util.PrettyPrinter
import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.util.EcoreUtil
import org.slizaa.neo4j.opencypher.openCypher.Cypher
import org.slizaa.neo4j.opencypher.openCypher.ExpressionAnd
import org.slizaa.neo4j.opencypher.openCypher.ExpressionComparison
import org.slizaa.neo4j.opencypher.openCypher.ExpressionNodeLabelsAndPropertyLookup
import org.slizaa.neo4j.opencypher.openCypher.ExpressionOr
import org.slizaa.neo4j.opencypher.openCypher.ExpressionXor
import org.slizaa.neo4j.opencypher.openCypher.Match
import org.slizaa.neo4j.opencypher.openCypher.NodePattern
import org.slizaa.neo4j.opencypher.openCypher.NumberConstant
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
import org.slizaa.neo4j.opencypher.openCypher.Statement
import org.slizaa.neo4j.opencypher.openCypher.StringConstant
import org.slizaa.neo4j.opencypher.openCypher.VariableRef
import org.slizaa.neo4j.opencypher.openCypher.Where
import relalg.ArithmeticComparisonOperator
import relalg.AttributeVariable
import relalg.BinaryLogicalOperator
import relalg.ComparableExpression
import relalg.Direction
import relalg.EdgeVariable
import relalg.ExpandOperator
import relalg.Expression
import relalg.JoinOperator
import relalg.LeftOuterJoinOperator
import relalg.LogicalExpression
import relalg.Operator
import relalg.RelalgFactory
import relalg.UnaryLogicalOperator
import relalg.UnaryNodeLogicalOperator
import relalg.UnaryOperator
import relalg.Variable
import relalg.VertexVariable

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

//		println(PrettyPrinter.format(cypher))

		val statement = cypher.statement
		// legacy_builder_by_szarnyasg(statement)
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

		val singleQuery_MatchList = clauses.filter(typeof(Match)).map[buildRelalg(it)]
		val content = buildLeftDeepTree(typeof(JoinOperator), singleQuery_MatchList?.iterator)

		val singleQuery_returnClauseList = clauses.filter(typeof(Return)).map[buildRelalgReturn(it, content)]

		if (singleQuery_returnClauseList == null || singleQuery_returnClauseList.empty) {
			content
		} else if (singleQuery_returnClauseList.length == 1) {
			singleQuery_returnClauseList.head
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
			variables.addAll(returnBody.items.map[
				val mapIt=it
				createReturnableElement => [
					expression = buildRelalgExpression(mapIt.expression)
					alias = mapIt.alias?.name
				]
			])
			//variables.addAll(returnBody.items.map[buildRelalgComparableElement(it.expression) as relalg.Variable])
		]

		// add duplicate-elimination operator if return DISTINCT was specified
		if (r.distinct) {
			createDuplicateEliminationOperator => [
				input = trimmer
			]
		} else {
			trimmer
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
			operator = BinaryLogicalOperator.AND
			leftOperand = buildRelalgLogicalExpression(e.left, joins)
			rightOperand = buildRelalgLogicalExpression(e.right, joins)
			container = topLevelContainer
		]
	}

	def dispatch LogicalExpression buildRelalgLogicalExpression(ExpressionOr e, EList<Operator> joins) {
		createBinaryLogicalExpression => [
			operator = BinaryLogicalOperator.OR
			leftOperand = buildRelalgLogicalExpression(e.left, joins)
			rightOperand = buildRelalgLogicalExpression(e.right, joins)
			container = topLevelContainer
		]
	}

	def dispatch LogicalExpression buildRelalgLogicalExpression(ExpressionXor e, EList<Operator> joins) {
		createBinaryLogicalExpression => [
			operator = BinaryLogicalOperator.XOR
			leftOperand = buildRelalgLogicalExpression(e.left, joins)
			rightOperand = buildRelalgLogicalExpression(e.right, joins)
			container = topLevelContainer
		]
	}

	def dispatch LogicalExpression buildRelalgLogicalExpression(org.slizaa.neo4j.opencypher.openCypher.Expression e
		, EList<Operator> joins
	) {
		switch e.operator.toLowerCase {
			case "not":
				createUnaryLogicalExpression => [
					operator = UnaryLogicalOperator.NOT
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
			operator = UnaryNodeLogicalOperator.IS_NOT_NULL
			leftOperand = buildVertexVariable(e.nodePattern)
			container = topLevelContainer
		])

		relationshipVariableExpressions.addAll(
			e.chain.map[
				val mapIt = it
				createUnaryNodeLogicalExpression => [
					operator = UnaryNodeLogicalOperator.IS_NOT_NULL
					leftOperand = buildEdgeVariable(mapIt.relationshipPattern.detail)
					container = topLevelContainer
				]
			]
		)
		relationshipVariableExpressions.addAll(
			e.chain.map[
				val mapIt = it
				createUnaryNodeLogicalExpression => [
					operator = UnaryNodeLogicalOperator.IS_NOT_NULL
					leftOperand = buildVertexVariable(mapIt.nodePattern)
					container = topLevelContainer
				]
			]
		)

		joins.add(buildRelalg(e))

		buildLeftDeepTree(BinaryLogicalOperator.AND, relationshipVariableExpressions.iterator, topLevelContainer)
	}

	def dispatch LogicalExpression buildRelalgLogicalExpression(ExpressionComparison e, EList<Operator> joins) {
		//FIXME: add type check to ensure that the operands are comparable
		createArithmeticComparisonExpression => [
			operator = switch e.operator {
				case "=": ArithmeticComparisonOperator.EQUAL_TO
				case "!=",
				case "<>": ArithmeticComparisonOperator.NOT_EQUAL_TO
				case "<": ArithmeticComparisonOperator.LESS_THAN
				case "<=": ArithmeticComparisonOperator.LESS_THAN_OR_EQUAL
				case ">": ArithmeticComparisonOperator.GREATER_THAN
				case ">=": ArithmeticComparisonOperator.GREATER_THAN_OR_EQUAL
			}
			leftOperand = buildRelalgComparableElement(e.left)
			rightOperand = buildRelalgComparableElement(e.right)
			container = topLevelContainer
		]
	}

	def dispatch ComparableExpression buildRelalgComparableElement(NumberConstant e) {
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

	def dispatch ComparableExpression buildRelalgComparableElement(StringConstant e) {
		createStringLiteral => [
			value = e.value
			container = topLevelContainer
		]
	}

	def dispatch ComparableExpression buildRelalgComparableElement(VariableRef e) {
		buildRelalgVariable(e)
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

	def dispatch Expression buildRelalgExpression(VariableRef e) {
		buildRelalgVariable(e)
	}

	def dispatch Expression buildRelalgExpression(ExpressionNodeLabelsAndPropertyLookup e) {
		buildRelalgVariable(e)
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
			case vertexVariableFactory.hasElement(varRef.variableRef.name)
						    && edgeVariableFactory.hasElement(varRef.variableRef.name): {
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

		createExpandOperator() => [
			edgeVariable = patternElementChain_EdgeVariable;
			direction = if (isLeftArrow && isRightArrow || ! (isLeftArrow || isRightArrow))
				Direction.BOTH
			else if(isLeftArrow) Direction.IN else Direction.OUT;
			targetVertexVariable = patternElementChain_VertexVariable
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

//	def dispatch buildRelalg(Cypher rule) {
//		println(String::format("received unsupported cypher element: %s", rule.class.toString()))
//	}
	protected def void legacy_builder_by_szarnyasg(Statement statement) {
		if (statement instanceof SingleQuery) {
			val clauses = statement.clauses

			clauses.filter(typeof(Match)).forEach [
				val element = it.pattern.patterns.get(0) as PatternElement
				val variableName = element.nodepattern.variable.name

				val vertexVariable = vertexVariableFactory.createElement(variableName)
			]
			clauses.filter(typeof(Where)).forEach[]
			clauses.filter(typeof(Return)).forEach [
				val distinct = it.distinct
				val body = it.body as ReturnItems
				val all = body.all
				val order = body.order
				val skip = body.skip
				val limit = body.limit

				body.items.forEach [
					// RETURN expression AS alias, ...
					val expression = it.expression
					val alias = it.alias

					switch expression {
						VariableRef: {
							println('''variableref: «expression.variableRef.name»''')
						}
						ExpressionNodeLabelsAndPropertyLookup: {
							println('''lookup: «expression.propertyLookups.get(0).propertyKeyName»''')
						}
						default: {
							println('''Return item «it» not supported''')
						}
					}
					println
				]
			]
		}
	}

// VIATRA Queries
//
// // we shouldn't register a full XMI resource factory for relalg models
// Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("relalg", new XMIResourceFactoryImpl())
//
// val resourceSet = new ResourceSetImpl
// val resource = resourceSet.createResource(URI.createFileURI("file:/queryplan.relalg"))
//
// resource.contents.add(cypher)
//
// val AdvancedViatraQueryEngine engine = AdvancedViatraQueryEngine.createUnmanagedEngine(
// new EMFScope(resourceSet))
//
// println("NodeVariables")
// val nodeVariables = engine.getMatcher(NodeVariablesQuerySpecification.instance).allMatches
// nodeVariables.forEach [
// println(it)
// ]
//
// println("EdgeVariables")
// val edgeVariables = engine.getMatcher(EdgeVariablesQuerySpecification.instance).allMatches
// edgeVariables.forEach [
// println(it)
// ]
// val singleQuery = cypher.statement as SingleQuery
// singleQuery.clauses.filter(typeof(Match)).forEach [
// println(PrettyPrinter.prettyPrint(it))
// ]
// singleQuery.clauses.filter(typeof(Return)).forEach [
// println(PrettyPrinter.prettyPrint(it))
// ]
}
