package ingraph.report.generator.tests

import ingraph.logger.IngraphLogger
import ingraph.report.generator.QueryProcessor
import ingraph.report.generator.data.TestQuery
import ingraph.report.generator.util.TechReportEscaper
import java.io.File
import java.nio.charset.Charset
import java.util.Map
import org.apache.commons.io.FileUtils

abstract class IngraphReportTest {

	protected extension TechReportEscaper escaper = new TechReportEscaper

	def printChapter(String chapterName, String chapterTitle, String shortTitle, 
		Map<String, Iterable<TestQuery>> chapterQuerySpecifications) {
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
				\begin{tabular}{llr@{ of }l}
				\input{appendix/progressbar-«chapterName»}
				\end{tabular}
		'''
		doc += sections

		val progressbar = '''
			\hyperref[chp:«chapterName»]{«shortTitle»} &
			\progressbar[width=10cm, heighta=.3cm, filledcolor=OliveGreen, emptycolor=white, borderwidth=1pt, tickswidth=1pt, subdivisions=10]{«chapterCompilingQueryRatio»} &
			«chapterCompilingQueries» &
			«chapterTotalQueries» \\
		'''

		val appendixDir = "../opencypher-report/appendix/"
		val charset = Charset.forName("UTF-8")
		FileUtils.writeStringToFile(new File('''«appendixDir»progressbar-«chapterName».tex'''), progressbar, charset)
		FileUtils.writeStringToFile(new File('''«appendixDir»chapter-«chapterName».tex'''), doc, charset)
	}

	protected def String calculateCompilingQueryRation(int sectionTotalQueries, int sectionCompilingQueries) {
		if (sectionTotalQueries == 0) {
			"1"
		} else {
			String.format("%.02f", (sectionCompilingQueries as float) / sectionTotalQueries)
		}
	}

}
