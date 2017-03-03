package ingraph.report.generator.tests.directories

import ingraph.report.generator.tests.DirectoryTest
import org.junit.Test

class MovieDatabaseReportTest extends DirectoryTest {
	
	@Test
	def void generateChapter() {
		toChapter("movie-database", "Movie Database")
	}
	
}
