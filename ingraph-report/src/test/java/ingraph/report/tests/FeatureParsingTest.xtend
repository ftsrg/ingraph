package ingraph.report.tests

import ingraph.cypher2relalg.RelalgParser
import ingraph.optimization.transformations.SchemaInferencer
import ingraph.relalg2tex.RelalgTreeSerializer
import ingraph.report.FeatureStandaloneSetup
import ingraph.report.feature.Feature
import ingraph.report.feature.Scenario
import ingraph.report.feature.WhenStep
import java.io.File
import java.util.Collections
import org.apache.commons.io.FileUtils
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.xtext.resource.XtextResourceSet
import org.junit.Test

class FeatureParsingTest {

	extension RelalgTreeSerializer serializer = new RelalgTreeSerializer(false)
	extension SchemaInferencer inferencer = new SchemaInferencer

	@Test
	def void loadModel() {
		val CUCUMBER_TESTS_DIR = "../opencypher-tests/"

		val injector = new FeatureStandaloneSetup().createInjectorAndDoEMFRegistration()
		val resourceSet = injector.getInstance(typeof(XtextResourceSet))

		val files = FileUtils.listFiles(new File(CUCUMBER_TESTS_DIR), #["feature"], true)

		val doc = '''
			\chapter{TCK Acceptance Tests}
			
			«FOR feature : files.map[processFile(resourceSet)]»
				\section{«feature.name.escape»}
				
				«FOR scenario : feature.scenarios.filter(typeof(Scenario)).map[processScenario].filter[!name.contains("Fail")]»					
					\subsection{«scenario.name.escape»}
					
					\begin{lstlisting}
					«scenario.steps.filter(typeof(WhenStep)).map[desc].join»
					\end{lstlisting}
					
					«scenario.steps.filter(typeof(WhenStep)).map[desc].join.visualize»
				«ENDFOR»	
			«ENDFOR»
		'''

		FileUtils.writeStringToFile(new File("../opencypher-report/acceptance-tests.tex"), doc)
	}

	def processFile(File file, ResourceSet resourceSet) {
		val resource = resourceSet.createResource(URI.createURI(file.absolutePath))
		resource.load(Collections.emptyMap)
		val feature = resource.contents.get(0) as Feature
		feature.name = feature.name.replaceAll("^Feature: ", "").replaceAll("\n", "")
		feature
	}

	def processScenario(Scenario s) {
		// println(resource.errors)
		s.name = s.name.replaceAll("^Scenario: ", "").replaceAll("\n", "")

		s.steps.filter(typeof(WhenStep)).forEach[desc = desc.cleanup]
//		s.steps.filter(typeof(GivenStep)).forEach[desc = desc.cleanup]
//		s.steps.filter(typeof(AndStep)).forEach[desc = desc.cleanup]
//		s.steps.filter(typeof(ThenStep)).forEach[desc = desc.cleanup]
		s
	}

	/**
	 * Drops indentation and quotes.
	 */
	def cleanup(String s) {
		s //
		.replaceAll('''"""''', "") // """
		.replaceAll("'''", "") // '''
		.replaceAll("\n      ", "\n") // indentation
		.replaceAll("^\n", "") // newline at the start 
		.replaceAll("\n$", "") // newline at the end
	}

	def escape(String s) {
		s //
		.replaceAll('''#''', '''\\#''') //
		.replaceAll('''_''', '''\\_''')
	}

	def visualize(String s) {
		try {
			val container = RelalgParser.parse(s)
			container.addSchemaInformation.serialize
		} catch (Exception e) {
			'''Cannot visualize'''
		}
	}

}
