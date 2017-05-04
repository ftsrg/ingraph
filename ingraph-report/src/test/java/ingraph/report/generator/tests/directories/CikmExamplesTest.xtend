package ingraph.report.generator.tests.directories

import ingraph.relalg2tex.config.RelalgConverterConfigBuilder
import ingraph.report.generator.tests.DirectoryTest
import org.junit.Test

class CikmExamplesTest extends DirectoryTest {

	new() {
		super(new RelalgConverterConfigBuilder().setOmitSchema(false).setConsoleOutput(false).
		setStandaloneDocument(false).setIncludeCommonVariables(false).setParentheses(true).
		setIncludeProductionOperator(false).setTextualOperators(false).build)
	}

	@Test
	def void generateChapter() {
		toChapter("cikm-examples", "CIKM examples")
	}

}
