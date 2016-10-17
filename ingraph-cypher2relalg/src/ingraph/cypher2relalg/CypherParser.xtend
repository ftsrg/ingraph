package ingraph.cypher2relalg

import ingraph.CypherStandaloneSetup
import ingraph.cypher.CypherQuery
import java.io.ByteArrayInputStream
import org.eclipse.emf.common.util.URI
import org.eclipse.xtext.resource.XtextResourceSet
import relalg.RelalgFactory

class CypherParser {

	def static parseFile(String queryName) {
		// https://typefox.io/how-and-why-use-xtext-without-the-ide
		val injector = new CypherStandaloneSetup().createInjectorAndDoEMFRegistration();
		val resourceSet = injector.getInstance(XtextResourceSet);
		val filePath = "../queries/" + queryName + ".cyp"
		val cypherQuery = resourceSet.getResource(URI.createFileURI(filePath), true).contents.get(0) as CypherQuery;
		processQuery(cypherQuery)
	}

	def static parseString(String queryString) {
		// https://wiki.eclipse.org/Xtext/FAQ
		val injector = new CypherStandaloneSetup().createInjectorAndDoEMFRegistration();
		val resourceSet = injector.getInstance(XtextResourceSet);
		val resource = resourceSet.createResource(URI.createURI("dummy:/example.cyp"));
		val in = new ByteArrayInputStream(queryString.getBytes());
		resource.load(in, resourceSet.getLoadOptions());
		val cypherQuery = resource.contents.get(0) as CypherQuery;
		processQuery(cypherQuery)
	}

	def static processQuery(CypherQuery cypherQuery) {
		val container = RelalgFactory.eINSTANCE.createRelationalAlgebraContainer

		container
	}

}
