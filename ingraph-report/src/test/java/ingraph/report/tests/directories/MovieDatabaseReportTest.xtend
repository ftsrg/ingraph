package ingraph.report.tests.directories

import org.junit.Test

class MovieDatabaseReportTest extends DirectoryTest {
	
	@Test
	def void generateChapter() {
		toChapter("movie-database", "Movie Database")
	}
	
}
