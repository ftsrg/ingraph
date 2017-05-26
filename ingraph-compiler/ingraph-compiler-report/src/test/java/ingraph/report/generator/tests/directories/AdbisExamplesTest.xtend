package ingraph.report.generator.tests.directories

import ingraph.report.generator.tests.DirectoryTest
import org.junit.Test

class AdbisExamplesTest extends DirectoryTest {

	@Test
	def void generateChapter() {
		toChapter("adbis-examples", "ADBIS examples")
	}

}
