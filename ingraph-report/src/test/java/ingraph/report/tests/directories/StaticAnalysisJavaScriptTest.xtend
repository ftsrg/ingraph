package ingraph.report.tests.directories

import org.junit.Test

class StaticAnalysisJavaScriptTest extends DirectoryTest {

	@Test
	def void generateChapter() {
		toChapter("static-analysis-javascript", "Static Analysis for JavaScript")
	}

}
