package ingraph.report.tests

import org.junit.Test

class MovieDatabaseReportTest extends IngraphReportTest {
	
	@Test
	def void generateChapter() {
		toChapter("movie-database", "Movie Database")
	}
	
}