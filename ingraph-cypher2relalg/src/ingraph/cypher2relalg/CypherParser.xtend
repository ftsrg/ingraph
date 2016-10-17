package ingraph.cypher2relalg

import java.io.ByteArrayInputStream
import org.eclipse.emf.common.util.URI
import org.eclipse.xtext.resource.XtextResourceSet
import org.slizaa.neo4j.opencypher.OpenCypherStandaloneSetup
import org.slizaa.neo4j.opencypher.openCypher.Cypher
import org.slizaa.neo4j.opencypher.openCypher.Match
import org.slizaa.neo4j.opencypher.openCypher.SingleQuery
import relalg.RelalgFactory
import ingraph.emf.util.PrettyPrinter

class CypherParser {

	def static parseFile(String queryName) {
		// https://typefox.io/how-and-why-use-xtext-without-the-ide
		val injector = new OpenCypherStandaloneSetup().createInjectorAndDoEMFRegistration();
		val resourceSet = injector.getInstance(XtextResourceSet);
		val filePath = "../queries/" + queryName + ".cyp"
		val cypherQuery = resourceSet.getResource(URI.createFileURI(filePath), true).contents.get(0) as Cypher;
		processQuery(cypherQuery)
	}

	def static parseString(String queryString) {
		// https://wiki.eclipse.org/Xtext/FAQ
		val injector = new OpenCypherStandaloneSetup().createInjectorAndDoEMFRegistration();
		val resourceSet = injector.getInstance(XtextResourceSet);
		val resource = resourceSet.createResource(URI.createURI("dummy:/example.cyp"));
		val in = new ByteArrayInputStream(queryString.getBytes());
		resource.load(in, resourceSet.getLoadOptions());
		val cypherQuery = resource.contents.get(0) as Cypher;
		processQuery(cypherQuery)
	}

	def static processQuery(Cypher cypherQuery) {
		val container = RelalgFactory.eINSTANCE.createRelationalAlgebraContainer

		val singleQuery = cypherQuery.statement as SingleQuery
		singleQuery.clauses.filter(typeof(Match)).forEach[
			println(PrettyPrinter.prettyPrint(it))
		]

		container
	}

}
