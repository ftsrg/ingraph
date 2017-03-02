package ingraph.report.tests.tck

import com.google.common.collect.Lists
import com.google.common.io.Files
import ingraph.report.FeatureStandaloneSetup
import ingraph.report.feature.Feature
import ingraph.report.feature.Scenario
import ingraph.report.feature.ThenStep
import ingraph.report.feature.WhenStep
import ingraph.report.tests.IngraphReportTest
import ingraph.report.tests.TestQuery
import java.io.File
import java.nio.charset.Charset
import java.util.Collections
import java.util.LinkedHashMap
import java.util.LinkedList
import java.util.List
import java.util.Map
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

		val List<File> files = Lists.newArrayList(FileUtils.listFiles(new File(CUCUMBER_TESTS_DIR), #["feature"], true))
		Collections.sort(files)

		val Map<String, Iterable<TestQuery>> chapterQuerySpecifications = new LinkedHashMap

//		val Map<File, Feature> features = new LinkedHashMap<File, Feature>
//		files.forEach[
//			features.put(it, it.processFile(resourceSet))
//		]
		val Map<File, Feature> features = newLinkedHashMap(files.map[it -> it.processFile(resourceSet)])

		for (featureEntry : features.entrySet) {
			val file = featureEntry.key
			val feature = featureEntry.value

			val testQueries = new LinkedList<TestQuery>
			for (scenario : feature.scenarios.filter(typeof(Scenario)).map[processScenario].filter [
				!name.contains("Fail")
			]) {
				val querySpecification = scenario.steps.filter(typeof(WhenStep)).map[desc].join.unindent

				if ( //
				scenario.steps.filter(typeof(ThenStep)).filter[it.text.contains("SyntaxError should be raised")].
					isEmpty && //
				!querySpecification.contains("CREATE ") && //
				!querySpecification.contains("MERGE ") && //
				!querySpecification.contains("REMOVE ") && //
				!querySpecification.contains("SET ") && //
				!querySpecification.contains("DELETE ") //
				) {
					val scenarioId = '''«file.name»: Scenario: «scenario.name»'''
					println(scenarioId)
					val regressionTest = failingAndRegressionTests.contains(scenarioId)
					val testQuery = TestQuery.builder.queryName(scenario.name).querySpecification(querySpecification).regressionTest(regressionTest).
						build
					testQueries.add(testQuery)
				}
			}
			chapterQuerySpecifications.put(feature.name.escape, testQueries)
		}

		printChapter("tck", "TCK Acceptance Tests", chapterQuerySpecifications)
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
