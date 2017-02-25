package ingraph.report.tests.directories

import org.junit.Test

class FraudDetectionTest extends DirectoryTest {

    @Test
    def void generateChapter() {
        toChapter("fraud-detection", "Fraud Detection")
    }

}
