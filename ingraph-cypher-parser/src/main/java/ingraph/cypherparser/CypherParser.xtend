package ingraph.cypherparser

import java.io.ByteArrayInputStream
import org.eclipse.emf.common.util.URI
import org.eclipse.xtext.resource.XtextResourceSet
import org.slizaa.neo4j.opencypher.OpenCypherStandaloneSetup
import org.slizaa.neo4j.opencypher.openCypher.Cypher

class CypherParser {

	def static Cypher parseFile(String fileName) {
		// https://typefox.io/how-and-why-use-xtext-without-the-ide
		val injector = new OpenCypherStandaloneSetup().createInjectorAndDoEMFRegistration();
		val resourceSet = injector.getInstance(XtextResourceSet);
		val filePath = "../queries/" + fileName + ".cyp"
		val cypher = resourceSet.getResource(URI.createFileURI(filePath), true).contents.get(0) as Cypher;
		return cypher
	}

	def static Cypher parseString(String queryString) {
		// https://wiki.eclipse.org/Xtext/FAQ
		val injector = new OpenCypherStandaloneSetup().createInjectorAndDoEMFRegistration();
		val resourceSet = injector.getInstance(XtextResourceSet);
		val resource = resourceSet.createResource(URI.createURI("dummy:/example.cyp"));
		val in = new ByteArrayInputStream(queryString.getBytes());
		resource.load(in, resourceSet.getLoadOptions());
		val cypher = resource.contents.get(0) as Cypher;
		return cypher
	}

}
