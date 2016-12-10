package ingraph.report.tests

import org.junit.Test

class SocialNetworkBenchmarkTest extends IngraphReportTest {

	@Test
	def void generateChapter() {
		toChapter("ldbc-snb", "LDBC Social Network Benchmark")
	}

}
