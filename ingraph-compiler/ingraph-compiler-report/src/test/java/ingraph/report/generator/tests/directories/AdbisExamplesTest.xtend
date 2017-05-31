package ingraph.report.generator.tests.directories

import org.junit.Test
import ingraph.report.generator.tests.ReportDirectoryTest

class AdbisExamplesTest extends ReportDirectoryTest {

	@Test
	def void generateChapter() {
		toChapter("adbis-examples", "ADBIS examples")
	}

}
