package ingraph.report.tests.directories

import org.junit.Test

class SocialNetworkBenchmarkInteractiveTest extends DirectoryTest {

	@Test
	def void generateChapter() {
		toChapter("ldbc-snb-interactive", "LDBC Social Network Benchmark -- Interactive Workload")
	}

}
