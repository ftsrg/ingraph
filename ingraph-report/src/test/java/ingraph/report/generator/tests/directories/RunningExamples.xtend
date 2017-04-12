package ingraph.report.generator.tests.directories

import ingraph.report.generator.tests.DirectoryTest
import org.junit.Test

class RunningExamples extends DirectoryTest {

	@Test
	def void generateChapter() {
		toChapter("running-examples", "Running examples")
	}

}
