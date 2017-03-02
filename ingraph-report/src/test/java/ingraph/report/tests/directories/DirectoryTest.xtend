package ingraph.report.tests.directories

import com.google.common.collect.ImmutableMap
import ingraph.report.tests.IngraphReportTest
import ingraph.report.tests.util.NaturalOrderComparator
import java.io.File
import java.nio.charset.Charset
import java.util.Collections
import java.util.HashMap
import java.util.Map
import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils

abstract class DirectoryTest extends IngraphReportTest {
	
	def toChapter(String directoryName, String chapterTitle) {
		val directoryPath = '''../queries/«directoryName»/'''
		val fileNames = FileUtils.listFiles(new File(directoryPath), #["cypher"], false).map[name].toList
		Collections.sort(fileNames, new NaturalOrderComparator());
	 
		val sectionQuerySpecifications = new HashMap<String, String>
		for (fileName : fileNames) {
				val queryName = FilenameUtils.removeExtension(fileName)
				val querySpecification = FileUtils.readFileToString(new File(directoryPath + fileName), Charset.forName("UTF-8"))
				sectionQuerySpecifications.put(queryName, querySpecification)
		}
		
		val Map<String, Map<String, String>> chapterQuerySpecifications = ImmutableMap.of("Queries", sectionQuerySpecifications)
		printChapter(directoryName, chapterTitle, chapterQuerySpecifications)
	}
	
}
