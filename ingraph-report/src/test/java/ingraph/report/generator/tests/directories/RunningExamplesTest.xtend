package ingraph.report.generator.tests.directories

import ingraph.relalg2tex.config.RelalgConverterConfig
import ingraph.report.generator.tests.DirectoryTest
import org.junit.Test

class RunningExamplesTest extends DirectoryTest {

	new() {
		super(RelalgConverterConfig.builder.includeCommonVariables(true).textualOperators(false).build)
	}

	@Test
	def void generateChapter() {
		toChapter("running-examples", "Running examples")
	}

}
