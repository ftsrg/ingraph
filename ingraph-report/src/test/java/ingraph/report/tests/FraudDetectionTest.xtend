package ingraph.report.tests

import org.junit.Test

class FraudDetectionTest extends IngraphReportTest {

    @Test
    def void generateChapter() {
        toChapter("fraud-detection", "Fraud Detection")
    }

}
