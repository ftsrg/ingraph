package ingraph.report.tests

import org.junit.Test

class TrainBenchmarkReportTest extends IngraphReportTest {

	@Test
	def void generateChapter() {
		toChapter("trainbenchmark", "Train Benchmark")
	}

}
