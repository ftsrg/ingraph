package ingraph.report.generator.tests.directories

import ingraph.report.generator.tests.DirectoryTest
import org.junit.Test

class LdbcSnbSimpleTest extends DirectoryTest {

	@Test
	def void generateChapter() {
		toChapter("ldbc-snb-simple", "LDBC Social Network Benchmark -- Simple Test", "LDBC SNB simple")
	}

}
