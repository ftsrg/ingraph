package ingraph.report.generator.tests.directories

import ingraph.report.generator.tests.DirectoryTest
import org.junit.Test

class RailwayVerificationTest extends DirectoryTest {

	@Test
	def void generateChapter() {
		toChapter("railway-verification", "Railway verification")
	}

}
