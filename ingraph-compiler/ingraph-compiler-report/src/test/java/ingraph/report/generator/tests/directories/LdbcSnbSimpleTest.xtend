package ingraph.report.generator.tests.directories

import org.junit.Test
import ingraph.report.generator.tests.ReportDirectoryTest

class LdbcSnbSimpleTest extends ReportDirectoryTest {

	@Test
	def void generateChapter() {
		toChapter("ldbc-snb-simple", "LDBC Social Network Benchmark -- Simple Test", "LDBC SNB simple")
	}

}
