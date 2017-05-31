package ingraph.report.generator.tests.directories

import org.junit.Test
import ingraph.report.generator.tests.ReportDirectoryTest

class LdbcSnbBiTest extends ReportDirectoryTest {

	@Test
	def void generateChapter() {
		toChapter("ldbc-snb-bi", "LDBC Social Network Benchmark -- Business Intelligence Workload", "LDBC SNB BI")
	}

}
