package ingraph.report.generator.tests.tck

import com.google.common.collect.Lists
import com.google.common.io.Files
import ingraph.report.featuregrammar.FeatureStandaloneSetup
import ingraph.report.featuregrammar.feature.Feature
import ingraph.report.featuregrammar.feature.Scenario
import ingraph.report.featuregrammar.feature.ThenStep
import ingraph.report.featuregrammar.feature.WhenStep
import ingraph.report.generator.data.TestQuery
import ingraph.report.generator.data.TestQueryBuilder
import ingraph.report.generator.tests.IngraphReportTest
import java.io.File
import java.nio.charset.Charset
import java.util.Collections
import java.util.LinkedHashMap
import java.util.LinkedList
import org.apache.commons.io.FileUtils
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.xtext.resource.XtextResourceSet
import org.junit.Test

class TckReportTest extends IngraphReportTest {

	@Test
	def void generateChapter() {
		val CUCUMBER_TESTS_DIR = "../opencypher-tests/"
		val failingAndRegressionTests = Files.readLines(
			new File('''«CUCUMBER_TESTS_DIR»failing-and-regression-tests.txt'''),
			Charset.forName("UTF-8")
		)

		val injector = new FeatureStandaloneSetup().createInjectorAndDoEMFRegistration()
		val resourceSet = injector.getInstance(typeof(XtextResourceSet))

		val files = Lists.newArrayList(FileUtils.listFiles(new File(CUCUMBER_TESTS_DIR), #["feature"], false))
		Collections.sort(files)

		val chapterTestQueries = new LinkedHashMap<String, Iterable<TestQuery>>
		for (file : files) {
			val feature = file.processFile(resourceSet)

			val testQueries = new LinkedList<TestQuery>
			for (scenario : feature.scenarios.filter(typeof(Scenario)).map[processScenario].filter [
				!name.contains("Fail")
			]) {
				val querySpecification = scenario.steps.filter(typeof(WhenStep)).map[desc].join.unindent

				if ( //
				scenario.steps.filter(typeof(ThenStep)).filter[it.text.contains("SyntaxError should be raised")].
					isEmpty && //
//				!querySpecification.contains("CREATE ") && //
				!querySpecification.contains("MERGE ") && //
				!querySpecification.contains("REMOVE ") && //
				!querySpecification.contains("SET ") && //
//				!querySpecification.contains("DELETE ") && //
				true
				) {
					val scenarioId = '''«file.name»: Scenario: «scenario.name»'''
					println(scenarioId)
					val regressionTest = failingAndRegressionTests.contains(scenarioId)
					val testQuery = new TestQueryBuilder().setQueryName(scenario.name).setQuerySpecification(querySpecification).setRegressionTest(regressionTest).
						build
					testQueries.add(testQuery)
				}
			}
			chapterTestQueries.put(feature.name.escape, testQueries)
		}

		val title = "TCK Acceptance Tests"
		printChapter("tck", title, title, chapterTestQueries)
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

}
