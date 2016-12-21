package ingraph.report.tests

import org.junit.Test
import ingraph.report.tests.IngraphReportTest

class TrainBenchmarkReportTest extends IngraphReportTest {

	@Test
	def void generateChapter() {
		toChapter("trainbenchmark", "Train Benchmark")
	}

}
