package ingraph.cypher2relalg

import ingraph.cypher2relalg.factories.EdgeLabelFactory
import ingraph.cypher2relalg.factories.EdgeVariableFactory
import ingraph.cypher2relalg.factories.VertexLabelFactory
import ingraph.cypher2relalg.factories.VertexVariableFactory
import ingraph.emf.util.PrettyPrinter
import org.eclipse.emf.ecore.util.EcoreUtil
import org.slizaa.neo4j.opencypher.openCypher.Cypher
import org.slizaa.neo4j.opencypher.openCypher.ExpressionNodeLabelsAndPropertyLookup
import org.slizaa.neo4j.opencypher.openCypher.Match
import org.slizaa.neo4j.opencypher.openCypher.PatternElement
import org.slizaa.neo4j.opencypher.openCypher.Return
import org.slizaa.neo4j.opencypher.openCypher.ReturnItems
import org.slizaa.neo4j.opencypher.openCypher.SingleQuery
import org.slizaa.neo4j.opencypher.openCypher.VariableRef
import org.slizaa.neo4j.opencypher.openCypher.Where
import relalg.RelalgFactory

class RelalgBuilder {

	extension RelalgFactory factory = RelalgFactory.eINSTANCE

	val container = createRelalgContainer

	val vertexVariableFactory = new VertexVariableFactory(container)
	val edgeVariableFactory = new EdgeVariableFactory(container)

	val vertexLabelFactory = new VertexLabelFactory(container)
	val edgeLabelFactory = new EdgeLabelFactory(container)

	def build(Cypher cypher) {
		EcoreUtil.resolveAll(cypher)

		println(PrettyPrinter.format(cypher))

		val statement = cypher.statement
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

		container
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
