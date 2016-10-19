package ingraph.cypher2relalg

import ingraph.cypher2relalg.queries.util.EdgeVariablesQuerySpecification
import ingraph.cypher2relalg.queries.util.NodeVariablesQuerySpecification
import ingraph.cypherparser.CypherParser
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl
import org.eclipse.viatra.query.runtime.api.AdvancedViatraQueryEngine
import org.eclipse.viatra.query.runtime.emf.EMFScope
import org.slizaa.neo4j.opencypher.openCypher.Cypher
import relalg.RelalgFactory
import relalg.RelationalAlgebraContainer

class Cypher2RelAlg {

	def static RelationalAlgebraContainer processFile(String queryFile) {
		val cypher = CypherParser.parseFile(queryFile)
		return processCypher(cypher)
	}

	def static RelationalAlgebraContainer processString(String queryString) {
		val cypher = CypherParser.parseString(queryString)
		return processCypher(cypher)
	}

	def static RelationalAlgebraContainer processCypher(Cypher cypherQuery) {
		val container = RelalgFactory.eINSTANCE.createRelationalAlgebraContainer

		// we shouldn't register a full XMI resource factory for relalg models
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("relalg", new XMIResourceFactoryImpl())
		
		val resourceSet = new ResourceSetImpl
		val resource = resourceSet.createResource(URI.createFileURI("file:/queryplan.relalg"))

		resource.contents.add(cypherQuery)

		val AdvancedViatraQueryEngine engine = AdvancedViatraQueryEngine.createUnmanagedEngine(
			new EMFScope(resourceSet))

		println("NodeVariables")
		val nodeVariables = engine.getMatcher(NodeVariablesQuerySpecification.instance).allMatches
		nodeVariables.forEach [
			println(it)
		]

		println("EdgeVariables")
		val edgeVariables = engine.getMatcher(EdgeVariablesQuerySpecification.instance).allMatches
		edgeVariables.forEach [
			println(it)
		]

//		val singleQuery = cypherQuery.statement as SingleQuery
//		singleQuery.clauses.filter(typeof(Match)).forEach [
//			println(PrettyPrinter.prettyPrint(it))
//		]
//		singleQuery.clauses.filter(typeof(Return)).forEach [
//			println(PrettyPrinter.prettyPrint(it))
//		]
		container
	}

}
