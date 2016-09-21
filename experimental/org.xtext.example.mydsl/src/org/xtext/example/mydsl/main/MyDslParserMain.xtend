package org.xtext.example.mydsl.main

import java.io.ByteArrayInputStream
import org.eclipse.emf.common.util.URI
import org.eclipse.xtext.resource.XtextResourceSet
import org.xtext.example.mydsl.MyDslStandaloneSetup
import org.xtext.example.mydsl.myDsl.CypherQuery

class MyDslParserMain {
	def static void main(String[] args) {
		// do this only once per application
		val injector = new MyDslStandaloneSetup().createInjectorAndDoEMFRegistration();

		// obtain a resourceset from the injector
		val resourceSet = injector.getInstance(typeof(XtextResourceSet));

		val queryString = "CREATE (v0)<-[e0:EdgLabel0]-(v1:Label1)-[]->(v2:Label2)<-[e1:EdgeLabel1]-(v3:Label3)"

		// load a resource by URI, in this case from the file system
		val resource = resourceSet.createResource(URI.createURI("dummy:/example.cypher"));
		val inputStream = new ByteArrayInputStream(queryString.getBytes());
		resource.load(inputStream, resourceSet.getLoadOptions());
		val cypherQuery = resource.getContents().get(0) as CypherQuery;

		println(PrettyPrinter.prettyPrint(cypherQuery))
	}
}
