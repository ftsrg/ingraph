package ingraph.report.generator.tests.directories

import ingraph.relalg2tex.config.RelalgConverterConfigBuilder
import ingraph.report.generator.tests.DirectoryTest
import org.junit.Test

class RunningExamplesTest extends DirectoryTest {

	new() {
		super(new RelalgConverterConfigBuilder().setIncludeCommonVariables(true).setTextualOperators(false).build)
	}

	@Test
	def void generateChapter() {
		toChapter("running-examples", "Running examples")
	}

}
