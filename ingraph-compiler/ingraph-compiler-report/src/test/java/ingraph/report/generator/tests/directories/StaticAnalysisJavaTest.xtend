package ingraph.report.generator.tests.directories

import org.junit.Test
import ingraph.report.generator.tests.ReportDirectoryTest

class StaticAnalysisJavaTest extends ReportDirectoryTest {

	@Test
	def void generateChapter() {
		toChapter("static-analysis-java", "Static Analysis for Java", "Static Analysis (Java)")
	}

}
