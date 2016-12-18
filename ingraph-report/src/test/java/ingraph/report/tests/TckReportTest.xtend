package ingraph.report.tests

import com.google.common.collect.Lists
import ingraph.cypher2relalg.Cypher2Relalg
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
import relalg.RelalgContainer

class TckReportTest extends IngraphReportTest {

	@Test
	def void generateChapter() {
		val CUCUMBER_TESTS_DIR = "../opencypher-tests/"

		val injector = new FeatureStandaloneSetup().createInjectorAndDoEMFRegistration()
		val resourceSet = injector.getInstance(typeof(XtextResourceSet))

		val List<File> files = Lists.newArrayList(FileUtils.listFiles(new File(CUCUMBER_TESTS_DIR), #["feature"], true))
		Collections.sort(files)

		val doc = '''
			\chapter{TCK Acceptance Tests}
			\label{chp:tck}

			«FOR feature : files.map[processFile(resourceSet)]»
				\section{«feature.name.escape»}

				«FOR scenario : feature.scenarios.filter(typeof(Scenario)).map[processScenario].filter[!name.contains("Fail")]»
					«val querySpecification = scenario.steps.filter(typeof(WhenStep)).map[desc].join»
					«IF 
						scenario.steps.filter(typeof(ThenStep)).filter[it.text.contains("SyntaxError should be raised")].isEmpty &&
						!querySpecification.contains("CREATE ") &&
						!querySpecification.contains("MERGE ") &&
						!querySpecification.contains("REMOVE ") &&
						!querySpecification.contains("SET ") &&
						!querySpecification.contains("DELETE ")	
					»
					«var RelalgContainer container = null»
					«try {
					    container = Cypher2Relalg.processString(querySpecification.toString)
					    ''''''
					} catch (Exception e) {
					    e.printStackTrace
                    }»
					
					«section(container, scenario.name.escape, querySpecification)»
					«ENDIF»
				«ENDFOR»
			«ENDFOR»
			'''
		FileUtils.writeStringToFile(new File("../opencypher-report/appendix/tck.tex"), doc, Charset.defaultCharset())
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
