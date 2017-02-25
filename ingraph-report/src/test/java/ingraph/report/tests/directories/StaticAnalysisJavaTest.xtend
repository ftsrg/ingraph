package ingraph.report.tests.directories

import org.junit.Test

class StaticAnalysisJavaTest extends DirectoryTest {

	@Test
	def void generateChapter() {
		toChapter("static-analysis-java", "Static Analysis for Java")
	}

}
