package ingraph.report.generator.tests.directories

import ingraph.relalg2tex.config.RelalgConverterConfigBuilder
import ingraph.report.generator.tests.DirectoryTest
import org.junit.Test

class RailwayVerificationTest extends DirectoryTest {

	new() {
		super(new RelalgConverterConfigBuilder().setOmitSchema(false).setConsoleOutput(false).
		setStandaloneDocument(false).setIncludeCommonVariables(false).setParentheses(true).
		setIncludeProductionOperator(false).setTextualOperators(true).build)
	}

	@Test
	def void generateChapter() {
		toChapter("railway-verification", "Railway verification")
	}

}
