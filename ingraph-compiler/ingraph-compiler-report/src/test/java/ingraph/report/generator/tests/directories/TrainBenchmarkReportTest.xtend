package ingraph.report.generator.tests.directories

import ingraph.report.generator.tests.DirectoryTest
import org.junit.Test

class TrainBenchmarkReportTest extends DirectoryTest {

	@Test
	def void generateChapter() {
		toChapter("trainbenchmark", "Train Benchmark")
	}

}
