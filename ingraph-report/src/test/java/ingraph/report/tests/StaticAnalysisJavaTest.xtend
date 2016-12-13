package ingraph.report.tests

import org.junit.Test

class StaticAnalysisJavaTest extends IngraphReportTest {

	@Test
	def void generateChapter() {
		toChapter("static-analysis-java", "Static Analysis for Java")
	}

}
