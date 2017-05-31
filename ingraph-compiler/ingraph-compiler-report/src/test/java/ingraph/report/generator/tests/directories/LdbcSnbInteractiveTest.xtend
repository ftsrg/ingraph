package ingraph.report.generator.tests.directories

import org.junit.Test
import ingraph.report.generator.tests.ReportDirectoryTest

class LdbcSnbInteractiveTest extends ReportDirectoryTest {

	@Test
	def void generateChapter() {
		toChapter("ldbc-snb-interactive", "LDBC Social Network Benchmark -- Interactive Workload",
			"LDBC SNB Interactive")
	}

}
