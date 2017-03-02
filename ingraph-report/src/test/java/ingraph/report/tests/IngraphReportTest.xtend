package ingraph.report.tests

import ingraph.report.tests.escape.Escaper
import java.io.File
import java.nio.charset.Charset
import java.util.Map
import org.apache.commons.io.FileUtils

abstract class IngraphReportTest {

	protected extension Escaper escaper = new Escaper

	def printChapter(String chapterName, String chapterTitle, Map<String, Map<String, String>> chapterQuerySpecifications) {
		var doc = '''
			\chapter{«chapterTitle»}
			\label{chp:«chapterName»}
		'''

		var chapterCompilingQueries = 0
		var chapterTotalQueries = 0
		var sections = ''''''
		for (cqs : chapterQuerySpecifications.entrySet) {
			val sectionTitle = cqs.key
			val sectionQuerySpecifications = cqs.value

			val qp = new QueryProcessor
			qp.processQueries(sectionQuerySpecifications)

			val sectionCompilingQueries = qp.compilingQueries
			val sectionTotalQueries = qp.totalQueries
			val sectionCompilingQueryRatio = calculateCompilingQueryRation(sectionTotalQueries, sectionCompilingQueries)

			sections += '''
				\section{«sectionTitle»}
				
				Progress:
				
				\progressbar[width=10cm, heighta=.3cm, filledcolor=gray, emptycolor=white, borderwidth=1pt, tickswidth=1pt, subdivisions=10]{«sectionCompilingQueryRatio»}
				«sectionCompilingQueries» of «sectionTotalQueries»
				
			'''

			for (subsection : qp.subsections) {
				sections += subsection
			}

			chapterCompilingQueries += sectionCompilingQueries
			chapterTotalQueries += sectionTotalQueries
		}

		val chapterCompilingQueryRatio = calculateCompilingQueryRation(chapterTotalQueries, chapterCompilingQueries)
		doc += '''
			Total progress:
			
			\progressbar[width=10cm, heighta=.3cm, filledcolor=green, emptycolor=red, borderwidth=1pt, tickswidth=1pt, subdivisions=10]{«chapterCompilingQueryRatio»}
			«chapterCompilingQueries» of «chapterTotalQueries»
		'''
		doc += sections

		FileUtils.writeStringToFile(new File('''../opencypher-report/appendix/«chapterName».tex'''), doc,
			Charset.defaultCharset())
	}

	protected def String calculateCompilingQueryRation(int sectionTotalQueries, int sectionCompilingQueries) {
		if (sectionTotalQueries == 0) {
			"1"
		} else {
			String.format("%.02f", (sectionCompilingQueries as float) / sectionTotalQueries)
		}
	}

}
