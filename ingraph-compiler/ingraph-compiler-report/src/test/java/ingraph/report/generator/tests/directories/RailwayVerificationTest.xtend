package ingraph.report.generator.tests.directories

import org.junit.Test
import ingraph.report.generator.tests.ReportDirectoryTest

class RailwayVerificationTest extends ReportDirectoryTest {

	@Test
	def void generateChapter() {
		toChapter("railway-verification", "Railway verification")
	}

}
