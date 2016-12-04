package ingraph.report.tests

import com.google.common.collect.Lists
import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.optimization.transformations.relalg2rete.Relalg2ReteTransformation
import ingraph.relalg.util.SchemaInferencer
import ingraph.relalg2tex.RelalgExpressionSerializer
import ingraph.relalg2tex.RelalgSerializerConfig
import ingraph.relalg2tex.RelalgTreeSerializer
import ingraph.report.FeatureStandaloneSetup
import ingraph.report.feature.Feature
import ingraph.report.feature.Scenario
import ingraph.report.feature.ThenStep
import ingraph.report.feature.WhenStep
import java.io.File
import java.nio.charset.Charset
import java.util.Collections
import java.util.List
import org.apache.commons.io.FileUtils
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.xtext.resource.XtextResourceSet
import org.junit.Test

class FeatureParsingTest {

	val treeSerializer = new RelalgTreeSerializer
	
	val config = RelalgSerializerConfig.builder.build
	val expressionSerializer = new RelalgExpressionSerializer(config)
	extension SchemaInferencer inferencer = new SchemaInferencer
	extension Relalg2ReteTransformation Relalg2ReteTransformation = new Relalg2ReteTransformation

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
					«val query = scenario.steps.filter(typeof(WhenStep)).map[desc].join»
					«IF 
						scenario.steps.filter(typeof(ThenStep)).filter[it.text.contains("SyntaxError should be raised")].isEmpty &&
						!query.contains("CREATE ") &&
						!query.contains("MERGE ") &&
						!query.contains("REMOVE ") &&
						!query.contains("SET ") &&
						!query.contains("DELETE ")	
					»
					\subsection{«scenario.name.escape»}

					\subsubsection*{Query specification}

					\begin{lstlisting}
					«scenario.steps.filter(typeof(WhenStep)).map[desc].join»
					\end{lstlisting}

					\subsubsection*{Relational algebra expression}

					\begin{flalign*}
					& «scenario.steps.filter(typeof(WhenStep)).map[desc].join.expression» &
					\end{flalign*}

					\subsubsection*{Relational algebra tree}

					«scenario.steps.filter(typeof(WhenStep)).map[desc].join.visualize»

					\subsubsection*{Relational algebra tree for incremental queries}

					«scenario.steps.filter(typeof(WhenStep)).map[desc].join.visualizeWithTransformations»
					«ENDIF»
				«ENDFOR»
			«ENDFOR»
			'''
		FileUtils.writeStringToFile(new File("../opencypher-report/acceptance-tests.tex"), doc, Charset.defaultCharset())
	}

	def processFile(File file, ResourceSet resourceSet) {
		val resource = resourceSet.createResource(URI.createURI(file.absolutePath))
		resource.load(Collections.emptyMap)
		val feature = resource.contents.get(0) as Feature
		feature.name = feature.name.replaceAll("^Feature: ", "").replaceAll("\n", "")
		feature
	}

	def processScenario(Scenario s) {
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
			val container = Cypher2Relalg.processString(s)
			expressionSerializer.serialize(container.addSchemaInformation)
		} catch (Exception e) {
			'''\text{Cannot convert to expression.}'''
		}
	}

	def visualize(String s) {
		try {
			val container = Cypher2Relalg.processString(s)
			treeSerializer.serialize(container.addSchemaInformation)
		} catch (Exception e) {
			'''\text{Cannot visualize tree.}'''
		}
	}

	def visualizeWithTransformations(String s) {
		try {
			val container = Cypher2Relalg.processString(s)
			treeSerializer.serialize(container.transformToRete.addSchemaInformation)
		} catch (Exception e) {
			'''Cannot visualize incremental tree.'''
		}
	}

}
