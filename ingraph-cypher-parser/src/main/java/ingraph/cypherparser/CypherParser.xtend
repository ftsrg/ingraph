package ingraph.cypherparser

import java.io.ByteArrayInputStream
import org.eclipse.emf.common.util.URI
import org.eclipse.xtext.resource.XtextResourceSet
import org.slizaa.neo4j.opencypher.OpenCypherStandaloneSetup
import org.slizaa.neo4j.opencypher.openCypher.Cypher
import org.eclipse.xtext.resource.XtextResource
import org.eclipse.xtext.validation.CheckMode
import org.eclipse.xtext.util.CancelIndicator
import org.eclipse.xtext.validation.Issue
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.diagnostics.Severity

class CypherParser {

	def static Cypher parseFile(String fileName) {
		// https://typefox.io/how-and-why-use-xtext-without-the-ide
		val injector = new OpenCypherStandaloneSetup().createInjectorAndDoEMFRegistration()
		val resourceSet = injector.getInstance(XtextResourceSet)
		val filePath = "../queries/" + fileName + ".cypher"
		val resource = resourceSet.getResource(URI.createFileURI(filePath), true)
		validateAndThrowError(resource)
		return resource.contents.get(0) as Cypher
	}

	def static Cypher parseString(String queryString) {
		// https://wiki.eclipse.org/Xtext/FAQ
		val injector = new OpenCypherStandaloneSetup().createInjectorAndDoEMFRegistration()
		val resourceSet = injector.getInstance(XtextResourceSet)
		val resource = resourceSet.createResource(URI.createURI("http:/example.cypher"))
		val in = new ByteArrayInputStream(queryString.getBytes())
		resource.load(in, resourceSet.getLoadOptions())
		validateAndThrowError(resource)
		return resource.contents.get(0) as Cypher
	}

	def protected static validateAndThrowError(Resource resource) {
		var seenError = false
		var String firstError = null
		val validator = (resource as XtextResource).getResourceServiceProvider().getResourceValidator()
		val issues = validator.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl)
		for (Issue issue : issues) {
			if (issue.severity == Severity.ERROR && !seenError) {
				seenError = true
				firstError = issue.message
			}
			println(issue.message)
		}
		if (seenError) {
			throw new RuntimeException('''Error during cypher parse, the first error was: «firstError»''')
		}
	}
}
