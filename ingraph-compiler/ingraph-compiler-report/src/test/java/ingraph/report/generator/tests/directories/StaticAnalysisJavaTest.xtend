package ingraph.report.generator.tests.directories

import ingraph.report.generator.tests.DirectoryTest
import org.junit.Test

class StaticAnalysisJavaTest extends DirectoryTest {

	@Test
	def void generateChapter() {
		toChapter("static-analysis-java", "Static Analysis for Java", "Static Analysis (Java)")
	}

}
