package ingraph.report.generator.tests.directories

import org.junit.Test
import ingraph.report.generator.tests.ReportDirectoryTest

class TrainBenchmarkReportTest extends ReportDirectoryTest {

	@Test
	def void generateChapter() {
		toChapter("trainbenchmark", "Train Benchmark")
	}

}
