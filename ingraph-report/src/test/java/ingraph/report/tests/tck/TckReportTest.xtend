package ingraph.report.tests.tck

import com.google.common.collect.Lists
import ingraph.report.FeatureStandaloneSetup
import ingraph.report.feature.Feature
import ingraph.report.feature.Scenario
import ingraph.report.feature.ThenStep
import ingraph.report.feature.WhenStep
import ingraph.report.tests.IngraphReportTest
import java.io.File
import java.util.Collections
import java.util.LinkedHashMap
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

		val injector = new FeatureStandaloneSetup().createInjectorAndDoEMFRegistration()
		val resourceSet = injector.getInstance(typeof(XtextResourceSet))

		val List<File> files = Lists.newArrayList(FileUtils.listFiles(new File(CUCUMBER_TESTS_DIR), #["feature"], true))
		Collections.sort(files)

		val Map<String, Map<String, String>> chapterQuerySpecifications = new LinkedHashMap

		val features = files.map[ processFile(resourceSet) ]
		for (feature : features) {
				val querySpecifications = new LinkedHashMap
				for (scenario : feature.scenarios.filter(typeof(Scenario)).map[processScenario].filter[!name.contains("Fail")]) {
					
					val querySpecification = scenario.steps.filter(typeof(WhenStep)).map[desc].join.unindent

					if (
						scenario.steps.filter(typeof(ThenStep)).filter[it.text.contains("SyntaxError should be raised")].isEmpty &&
						!querySpecification.contains("CREATE ") &&
						!querySpecification.contains("MERGE ") &&
						!querySpecification.contains("REMOVE ") &&
						!querySpecification.contains("SET ") &&
						!querySpecification.contains("DELETE ")) { 
						querySpecifications.put(scenario.name.escape, querySpecification)
					}
				}
				chapterQuerySpecifications.put(feature.name.escape, querySpecifications)
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
