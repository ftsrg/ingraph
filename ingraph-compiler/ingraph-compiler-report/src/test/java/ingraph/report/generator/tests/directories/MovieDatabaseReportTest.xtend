package ingraph.report.generator.tests.directories

import org.junit.Test
import ingraph.report.generator.tests.ReportDirectoryTest

class MovieDatabaseReportTest extends ReportDirectoryTest {

	@Test
	def void generateChapter() {
		toChapter("movie-database", "Movie Database")
	}

}
