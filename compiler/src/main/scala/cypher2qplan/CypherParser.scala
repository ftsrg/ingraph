package ingraph.compiler.cypher2qplan

import java.io.ByteArrayInputStream

import org.apache.log4j.{Level, Logger}
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.diagnostics.Severity
import org.eclipse.xtext.resource.{XtextResource, XtextResourceSet}
import org.eclipse.xtext.util.CancelIndicator
import org.eclipse.xtext.validation.CheckMode
import org.slizaa.neo4j.opencypher.OpenCypherStandaloneSetup
import org.slizaa.neo4j.opencypher.openCypher.Cypher

import scala.collection.JavaConverters._


object CypherParser {
  def parseFile(fileName: String): Cypher = {
    Logger.getLogger("org.eclipse.xtext").setLevel(Level.ERROR)

    // https://typefox.io/how-and-why-use-xtext-without-the-ide
    val injector = new OpenCypherStandaloneSetup().createInjectorAndDoEMFRegistration()
    val resourceSet = injector.getInstance(classOf[XtextResourceSet])
    val filePath = "../../queries/" + fileName + ".cypher"
    val resource = resourceSet.getResource(URI.createFileURI(filePath), true)
    validateAndThrowError(resource)

    resource.getContents.get(0).asInstanceOf[Cypher]
  }

  def parseString(queryString: String): Cypher = {
    Logger.getLogger("org.eclipse.xtext").setLevel(Level.ERROR)

    // https://wiki.eclipse.org/Xtext/FAQ
    val injector = new OpenCypherStandaloneSetup().createInjectorAndDoEMFRegistration()
    val resourceSet = injector.getInstance(classOf[XtextResourceSet])
    val resource = resourceSet.createResource(URI.createURI("http:/example.cypher"))
    val in = new ByteArrayInputStream(queryString.getBytes())
    resource.load(in, resourceSet.getLoadOptions())
    validateAndThrowError(resource)

    resource.getContents.get(0).asInstanceOf[Cypher]
  }

  def validateAndThrowError(resource: Resource) {
    var seenError = false
    var firstError: String = null
    val validator = resource.asInstanceOf[XtextResource].getResourceServiceProvider.getResourceValidator
    val issues = validator.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl).asScala
    for (issue <- issues) {
      if (issue.getSeverity == Severity.ERROR && !seenError) {
        seenError = true
        firstError = issue.getMessage
      }
      println(issue.getMessage)
    }
    if (seenError) {
      throw new RuntimeException(s"Error during cypher parse, the first error was: ${firstError}")
    }
  }
}
