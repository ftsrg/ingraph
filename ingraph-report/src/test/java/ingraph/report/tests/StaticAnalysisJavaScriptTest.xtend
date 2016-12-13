package ingraph.report.tests

import org.junit.Test

class StaticAnalysisJavaScriptTest extends IngraphReportTest {

	@Test
	def void generateChapter() {
		toChapter("static-analysis-javascript", "Static Analysis for JavaScript")
	}

}
