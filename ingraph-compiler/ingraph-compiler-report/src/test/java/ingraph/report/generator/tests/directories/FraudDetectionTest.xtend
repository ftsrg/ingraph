package ingraph.report.generator.tests.directories

import ingraph.report.generator.tests.DirectoryTest
import org.junit.Test

class FraudDetectionTest extends DirectoryTest {

	@Test
	def void generateChapter() {
		toChapter("fraud-detection", "Fraud Detection")
	}

}
