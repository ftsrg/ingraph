package ingraph.cypher2relalg

import ingraph.cypher2relalg.factories.EdgeLabelFactory
import ingraph.cypher2relalg.factories.EdgeVariableFactory
import ingraph.cypher2relalg.factories.VertexLabelFactory
import ingraph.cypher2relalg.factories.VertexVariableFactory
import org.slizaa.neo4j.opencypher.openCypher.Cypher
import org.slizaa.neo4j.opencypher.openCypher.Match
import org.slizaa.neo4j.opencypher.openCypher.PatternElement
import org.slizaa.neo4j.opencypher.openCypher.SingleQuery
import relalg.RelalgFactory
import org.slizaa.neo4j.opencypher.openCypher.Where
import org.slizaa.neo4j.opencypher.openCypher.Return
import org.slizaa.neo4j.opencypher.openCypher.ReturnItem
import org.slizaa.neo4j.opencypher.openCypher.ReturnItems

class RelalgBuilder {

	extension RelalgFactory factory = RelalgFactory.eINSTANCE

	val container = createRelalgContainer

	val vertexVariableFactory = new VertexVariableFactory(container)
	val edgeVariableFactory = new EdgeVariableFactory(container)

	val vertexLabelFactory = new VertexLabelFactory(container)
	val edgeLabelFactory = new EdgeLabelFactory(container)

	def build(Cypher cypher) {
		val statement = cypher.statement
		if (statement instanceof SingleQuery) {
			val clauses = statement.clauses

			clauses.filter(typeof(Match)).forEach [
				val element = it.pattern.patterns.get(0) as PatternElement
				val variableName = element.nodepattern.variable.name

				val vertexVariable = vertexVariableFactory.createElement(variableName)

				println(element.nodepattern.nodeLabels.nodeLabels.map[labelName].join(", "))
			]
			clauses.filter(typeof(Where)).forEach [
				
			]
			clauses.filter(typeof(Return)).forEach[ 
				val distinct = it.distinct
				val items = it.body as ReturnItems
				println(items.items.map[expression].join(", "))
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
