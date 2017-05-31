package ingraph.report.generator.tests

import com.google.common.collect.ImmutableMap
import ingraph.relalg2tex.config.RelalgConverterConfig
import ingraph.report.generator.data.TestQuery
import ingraph.report.generator.data.TestQueryBuilder
import ingraph.report.generator.util.NaturalOrderComparator
import java.io.File
import java.nio.charset.Charset
import java.util.Collections
import java.util.LinkedList
import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils

abstract class ReportDirectoryTest extends IngraphReportTest {

	new() {
		super()
	}

	new(RelalgConverterConfig treeSerializerConfig) {
		super(treeSerializerConfig)
	}

	def toChapter(String directoryName, String chapterTitle) {
		toChapter(directoryName, chapterTitle, chapterTitle)
	}

	def toChapter(String directoryName, String chapterTitle, String shortTitle) {
		val directoryPath = '''../../queries/«directoryName»/'''
		val fileNames = FileUtils.listFiles(new File(directoryPath), #["cypher"], false).map[name].toList
		Collections.sort(fileNames, new NaturalOrderComparator());

		val sectionTestQueries = new LinkedList<TestQuery>
		for (fileName : fileNames) {
			val queryName = FilenameUtils.removeExtension(fileName)
			val querySpecification = FileUtils.readFileToString(new File(directoryPath + fileName), Charset.forName("UTF-8"))
			val testQuery = new TestQueryBuilder().setQueryName(queryName).setQuerySpecification(querySpecification).build
			sectionTestQueries.add(testQuery)
		}

		val chapterQuerySpecifications = ImmutableMap.<String, Iterable<TestQuery>>of("Queries", sectionTestQueries)
		printChapter(directoryName, chapterTitle, shortTitle, chapterQuerySpecifications)
	}

}
