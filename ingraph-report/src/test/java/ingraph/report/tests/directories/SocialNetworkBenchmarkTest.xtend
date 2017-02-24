package ingraph.report.tests.directories

import org.junit.Test

class SocialNetworkBenchmarkTest extends DirectoryTest {

	@Test
	def void generateChapter() {
		toChapter("ldbc-snb", "LDBC Social Network Benchmark")
	}

}
