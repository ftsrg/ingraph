package ingraph.report.generator.tests.directories

import org.junit.Test
import ingraph.report.generator.tests.ReportDirectoryTest

class FraudDetectionTest extends ReportDirectoryTest {

	@Test
	def void generateChapter() {
		toChapter("fraud-detection", "Fraud Detection")
	}

}
