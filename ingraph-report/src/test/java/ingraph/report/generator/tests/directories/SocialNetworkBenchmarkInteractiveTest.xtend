package ingraph.report.generator.tests.directories

import ingraph.report.generator.tests.DirectoryTest
import org.junit.Test

class SocialNetworkBenchmarkInteractiveTest extends DirectoryTest {

	@Test
	def void generateChapter() {
		toChapter("ldbc-snb-interactive", "LDBC Social Network Benchmark -- Interactive Workload")
	}

}
