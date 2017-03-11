package ingraph.report.generator.tests.directories

import ingraph.report.generator.tests.DirectoryTest
import org.junit.Test

class SocialNetworkBenchmarkBiTest extends DirectoryTest {

	@Test
	def void generateChapter() {
		toChapter("ldbc-snb-bi", "LDBC Social Network Benchmark -- Business Intelligence Workload", "LDBC SNB BI")
	}

}
