package ingraph.report.tests.directories

import org.junit.Test

class TrainBenchmarkReportTest extends DirectoryTest {

	@Test
	def void generateChapter() {
		toChapter("trainbenchmark", "Train Benchmark")
	}

}
