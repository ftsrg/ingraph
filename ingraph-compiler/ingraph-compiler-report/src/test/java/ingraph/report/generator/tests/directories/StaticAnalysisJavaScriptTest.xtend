package ingraph.report.generator.tests.directories

import org.junit.Test
import ingraph.report.generator.tests.ReportDirectoryTest

class StaticAnalysisJavaScriptTest extends ReportDirectoryTest {

	@Test
	def void generateChapter() {
		toChapter("static-analysis-javascript", "Static Analysis for JavaScript", "Static Analysis (JS)")
	}

}
