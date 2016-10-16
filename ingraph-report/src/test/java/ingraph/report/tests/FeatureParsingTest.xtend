package ingraph.report.tests

import com.google.common.collect.Lists
import ingraph.cypher2relalg.RelalgParser
import ingraph.optimization.transformations.Relalg2ReteTransformation
import ingraph.optimization.transformations.SchemaInferencer
import ingraph.relalg2tex.RelalgTreeSerializer
import ingraph.report.FeatureStandaloneSetup
import ingraph.report.feature.Feature
import ingraph.report.feature.Scenario
import ingraph.report.feature.WhenStep
import java.io.File
import java.util.Collections
import java.util.List
import org.apache.commons.io.FileUtils
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.xtext.resource.XtextResourceSet
import org.junit.Test
import ingraph.relalg2tex.RelalgExpressionSerializer
import ingraph.report.feature.ThenStep

class FeatureParsingTest {

	val RelalgTreeSerializer treeSerializer = new RelalgTreeSerializer(false)
	val RelalgExpressionSerializer expressionSerializer = new RelalgExpressionSerializer(false, false)
	extension SchemaInferencer inferencer = new SchemaInferencer
	extension Relalg2ReteTransformation relalg2ReteTransformation = new Relalg2ReteTransformation

	@Test
	def void generateReport() {
		val CUCUMBER_TESTS_DIR = "../opencypher-tests/"

		val injector = new FeatureStandaloneSetup().createInjectorAndDoEMFRegistration()
		val resourceSet = injector.getInstance(typeof(XtextResourceSet))

		val List<File> files = Lists.newArrayList(FileUtils.listFiles(new File(CUCUMBER_TESTS_DIR), #["feature"], true))
		Collections.sort(files)

		val doc = '''
			\chapter{TCK Acceptance Tests}
			\label{chp:acceptance-tests}

			«FOR feature : files.map[processFile(resourceSet)]»
				\section{«feature.name.escape»}

				«FOR scenario : feature.scenarios.filter(typeof(Scenario)).map[processScenario].filter[!name.contains("Fail")]»
					«IF scenario.steps.filter(typeof(ThenStep)).filter[it.text.contains("SyntaxError should be raised")].isEmpty»
					\subsection{«scenario.name.escape»}

					\subsubsection*{Query specification}

					\begin{lstlisting}
					«scenario.steps.filter(typeof(WhenStep)).map[desc].join»
					\end{lstlisting}

					\subsubsection*{Relational algebra expression}

					«scenario.steps.filter(typeof(WhenStep)).map[desc].join.expression»

					\subsubsection*{Relational algebra tree}

					«scenario.steps.filter(typeof(WhenStep)).map[desc].join.visualize»

					\subsubsection*{Relational algebra tree for incremental queries}

					«scenario.steps.filter(typeof(WhenStep)).map[desc].join.visualizeWithTransformations»
					«ENDIF»
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

	def expression(String s) {
		try {
			val container = RelalgParser.parse(s)
			expressionSerializer.serialize(container.addSchemaInformation)
		} catch (Exception e) {
			'''Cannot convert to expression.'''
		}
	}

	def visualize(String s) {
		try {
			val container = RelalgParser.parse(s)
			treeSerializer.serialize(container.addSchemaInformation)
		} catch (Exception e) {
			'''Cannot visualize tree.'''
		}
	}

	def visualizeWithTransformations(String s) {
		try {
			val container = RelalgParser.parse(s)
			treeSerializer.serialize(container.transform.addSchemaInformation)
		} catch (Exception e) {
			'''Cannot visualize incremental tree.'''
		}
	}

}
