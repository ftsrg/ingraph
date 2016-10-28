package ingraph.cypher2relalg

import ingraph.cypher2relalg.factories.EdgeLabelFactory
import ingraph.cypher2relalg.factories.EdgeVariableFactory
import ingraph.cypher2relalg.factories.VertexLabelFactory
import ingraph.cypher2relalg.factories.VertexVariableFactory
import ingraph.cypher2relalg.util.Cypher2RelalgUtil
import ingraph.emf.util.PrettyPrinter
import org.eclipse.emf.ecore.util.EcoreUtil
import org.slizaa.neo4j.opencypher.openCypher.Cypher
import org.slizaa.neo4j.opencypher.openCypher.ExpressionNodeLabelsAndPropertyLookup
import org.slizaa.neo4j.opencypher.openCypher.Match
import org.slizaa.neo4j.opencypher.openCypher.NodePattern
import org.slizaa.neo4j.opencypher.openCypher.PatternElement
import org.slizaa.neo4j.opencypher.openCypher.PatternElementChain
import org.slizaa.neo4j.opencypher.openCypher.PatternPart
import org.slizaa.neo4j.opencypher.openCypher.RelationshipDetail
import org.slizaa.neo4j.opencypher.openCypher.Return
import org.slizaa.neo4j.opencypher.openCypher.ReturnItems
import org.slizaa.neo4j.opencypher.openCypher.SingleQuery
import org.slizaa.neo4j.opencypher.openCypher.Statement
import org.slizaa.neo4j.opencypher.openCypher.VariableRef
import org.slizaa.neo4j.opencypher.openCypher.Where
import relalg.Direction
import relalg.ExpandOperator
import relalg.JoinOperator
import relalg.Operator
import relalg.RelalgFactory
import relalg.VertexVariable
import relalg.ProjectionOperator
import relalg.UnaryOperator

class RelalgBuilder {

	extension RelalgFactory factory = RelalgFactory.eINSTANCE
	extension Cypher2RelalgUtil cypher2RelalgUtil = new Cypher2RelalgUtil

	val container = createRelalgContainer

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

	def dispatch Operator buildRelalg(SingleQuery q) {
		val clauses = q.clauses

		val singleQuery_MatchList = clauses.filter(typeof(Match)).map[buildRelalg(it)]
		val content = buildLeftDeepTree(typeof(JoinOperator), singleQuery_MatchList?.iterator)

		val singleQuery_returnClauseList = clauses.filter(typeof(Return)).map[buildRelalgReturn(it, content)]

		if (singleQuery_returnClauseList == null || singleQuery_returnClauseList.empty) {
			content
		} else {
			singleQuery_returnClauseList.head
		}
	}

	def UnaryOperator buildRelalgReturn(Return r, Operator content) {
		// FIXME: add variables: variables.addAll(return_VariableList)
		val trimmer = createProjectionOperator => [input = content]

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
			// TODO: proper expression/where clause handling
			createSelectionOperator => [
				conditionString = 'TODO';
				input = allDifferentOperator
			]
		} else {
			allDifferentOperator
		}
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
