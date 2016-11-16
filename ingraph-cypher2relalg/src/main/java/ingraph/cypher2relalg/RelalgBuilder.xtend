package ingraph.cypher2relalg

import ingraph.cypher2relalg.factories.EdgeLabelFactory
import ingraph.cypher2relalg.factories.EdgeVariableFactory
import ingraph.cypher2relalg.factories.VertexLabelFactory
import ingraph.cypher2relalg.factories.VertexVariableFactory
import ingraph.cypher2relalg.util.Cypher2RelalgUtil
import ingraph.cypher2relalg.util.ElementVariableUtil
import ingraph.emf.util.PrettyPrinter
import org.eclipse.emf.ecore.util.EcoreUtil
import org.slizaa.neo4j.opencypher.openCypher.Cypher
import org.slizaa.neo4j.opencypher.openCypher.Expression
import org.slizaa.neo4j.opencypher.openCypher.ExpressionAnd
import org.slizaa.neo4j.opencypher.openCypher.ExpressionComparison
import org.slizaa.neo4j.opencypher.openCypher.ExpressionNodeLabelsAndPropertyLookup
import org.slizaa.neo4j.opencypher.openCypher.ExpressionOr
import org.slizaa.neo4j.opencypher.openCypher.ExpressionXor
import org.slizaa.neo4j.opencypher.openCypher.Match
import org.slizaa.neo4j.opencypher.openCypher.NodePattern
import org.slizaa.neo4j.opencypher.openCypher.NumberConstant
import org.slizaa.neo4j.opencypher.openCypher.PatternElement
import org.slizaa.neo4j.opencypher.openCypher.PatternElementChain
import org.slizaa.neo4j.opencypher.openCypher.PatternPart
import org.slizaa.neo4j.opencypher.openCypher.RegularQuery
import org.slizaa.neo4j.opencypher.openCypher.RelationshipDetail
import org.slizaa.neo4j.opencypher.openCypher.Return
import org.slizaa.neo4j.opencypher.openCypher.ReturnItems
import org.slizaa.neo4j.opencypher.openCypher.SingleQuery
import org.slizaa.neo4j.opencypher.openCypher.Statement
import org.slizaa.neo4j.opencypher.openCypher.StringConstant
import org.slizaa.neo4j.opencypher.openCypher.Variable
import org.slizaa.neo4j.opencypher.openCypher.VariableRef
import org.slizaa.neo4j.opencypher.openCypher.Where
import relalg.ArithmeticComparisonOperator
import relalg.BinaryLogicalOperator
import relalg.ComparableElement
import relalg.Direction
import relalg.ExpandOperator
import relalg.JoinOperator
import relalg.LogicalExpression
import relalg.Operator
import relalg.RelalgFactory
import relalg.UnaryLogicalOperator
import relalg.UnaryOperator
import relalg.VertexVariable

class RelalgBuilder {

	extension RelalgFactory factory = RelalgFactory.eINSTANCE
	extension Cypher2RelalgUtil cypher2RelalgUtil = new Cypher2RelalgUtil

	val container = createRelalgContainer

	extension ElementVariableUtil elementVariableUtil = new ElementVariableUtil(container)

	val vertexVariableFactory = new VertexVariableFactory(container)
	val edgeVariableFactory = new EdgeVariableFactory(container)

	val vertexLabelFactory = new VertexLabelFactory(container)
	val edgeLabelFactory = new EdgeLabelFactory(container)

	def build(Cypher cypher) {
		EcoreUtil.resolveAll(cypher)

		println(PrettyPrinter.format(cypher))

		val statement = cypher.statement
		// legacy_builder_by_szarnyasg(statement)
		container.rootExpression = buildRelalg(statement)

		container
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
			throw new UnsupportedOperationException('''More than one return clauses received. We received actually «singleQuery_returnClauseList.length».''')
		}
	}

	def UnaryOperator buildRelalgReturn(Return r, Operator content) {
		// FIXME: add * handling
		val returnBody = r.body as ReturnItems

		val trimmer = createProjectionOperator => [
			input = content
			variables.addAll(returnBody.items.map[buildRelalgComparableElement(it.expression) as relalg.Variable])
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

	def dispatch Operator buildRelalg(Match m) {
		// FIXME: handle OPTIONAL
		// handle comma-separated patternParts in the MATCH clause
		val pattern_PatternPartList = m.pattern.patterns.map[buildRelalg(it)]

		// they are natural joined together
		val allDifferentOperator = createAllDifferentOperator => [
			input = buildLeftDeepTree(typeof(JoinOperator), pattern_PatternPartList?.iterator)
		]

		if (m.where != null) {
			createSelectionOperator => [
				condition = buildRelalgLogicalExpression(m.where.expression)
				input = allDifferentOperator
			]
		} else {
			allDifferentOperator
		}
	}

	def dispatch LogicalExpression buildRelalgLogicalExpression(ExpressionAnd e) {
		createBinaryLogicalExpression => [
			operator = BinaryLogicalOperator.AND
			leftOperand = buildRelalgLogicalExpression(e.left)
			rightOperand = buildRelalgLogicalExpression(e.right)
		]
	}

	def dispatch LogicalExpression buildRelalgLogicalExpression(ExpressionOr e) {
		createBinaryLogicalExpression => [
			operator = BinaryLogicalOperator.OR
			leftOperand = buildRelalgLogicalExpression(e.left)
			rightOperand = buildRelalgLogicalExpression(e.right)
		]
	}

	def dispatch LogicalExpression buildRelalgLogicalExpression(ExpressionXor e) {
		createBinaryLogicalExpression => [
			operator = BinaryLogicalOperator.XOR
			leftOperand = buildRelalgLogicalExpression(e.left)
			rightOperand = buildRelalgLogicalExpression(e.right)
		]
	}

	def dispatch LogicalExpression buildRelalgLogicalExpression(Expression e) {
		switch e.operator.toLowerCase {
			case "not":
				createUnaryLogicalExpression => [
					operator = UnaryLogicalOperator.NOT
					leftOperand = buildRelalgLogicalExpression(e.left)
				]
			default:
				throw new UnsupportedOperationException("TODO: " + e.operator)
		}
	}

	def dispatch LogicalExpression buildRelalgLogicalExpression(ExpressionComparison e) {
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
		]
	}

	def dispatch ComparableElement buildRelalgComparableElement(NumberConstant e) {
		try {
			val n = Integer.parseInt(e.value)
			createIntegerLiteral => [
				value = n
			]
		} catch (NumberFormatException ex) {
			createDoubleLiteral => [
				value = Double.parseDouble(e.value)
			]
		}
	}

	def dispatch ComparableElement buildRelalgComparableElement(StringConstant e) {
		createStringLiteral => [
			value = e.value
		]
	}

	def dispatch ComparableElement buildRelalgComparableElement(Variable e) {
		//FIXME: determine whether we need to return a VertexVariable or an EdgeVariable
		vertexVariableFactory.createElement(e.name)
	}

	def dispatch ComparableElement buildRelalgComparableElement(ExpressionNodeLabelsAndPropertyLookup e) {
		val ce = buildRelalgComparableElement(e.left)

		switch ce {
			VertexVariable: {
				if (e.propertyLookups.length == 1) {
					ce.createAttribute(e.propertyLookups.get(0).propertyKeyName)
				} else {
					throw new UnsupportedOperationException('''PropertyLookup count «e.propertyLookups.length» not supported.''')
				}
			}

			default: {
				throw new UnsupportedOperationException('''Unsupported ComparableElement type received: «ce.class.name»''')
			}

		}


	}

	def dispatch ComparableElement buildRelalgComparableElement(VariableRef e) {
		buildRelalgComparableElement(e.variableRef)
	}

	def dispatch Operator buildRelalg(PatternPart p) {
		// TODO: handle variable assignment
		// pass through variable assignment body to buildRelalg(PatternElement e)
		buildRelalg(p.part)
	}

	def dispatch Operator buildRelalg(PatternElement e) {
		val patternElement_GetVerticesOperator = createGetVerticesOperator => [
			vertexVariable = buildVertexVariable(e.nodepattern)
		]
		val patternElement_ExpandList = e.chain.map[buildRelalg(it) as ExpandOperator]

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
		val edgeVariableName = r.variable?.name
		val edgeVariable = edgeVariableFactory.createElement(edgeVariableName)

		// add labels to the variable
		r.types?.relTypeName?.forEach [
			edgeVariable.ensureLabel(edgeLabelFactory.createElement(it))
		]

		edgeVariable
	}

	protected def VertexVariable buildVertexVariable(NodePattern n) {
		val vertexVariableName = n.variable?.name
		val vertexVariable = vertexVariableFactory.createElement(vertexVariableName)

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
