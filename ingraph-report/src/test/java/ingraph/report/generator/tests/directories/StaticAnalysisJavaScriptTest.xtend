package ingraph.report.generator.tests.directories

import ingraph.report.generator.tests.DirectoryTest
import org.junit.Test

class StaticAnalysisJavaScriptTest extends DirectoryTest {

	@Test
	def void generateChapter() {
		toChapter("static-analysis-javascript", "Static Analysis for JavaScript", "Static Analysis (JS)")
	}

}
