package ingraph.report.tests

import ingraph.report.tests.escape.Escaper
import java.io.File
import java.nio.charset.Charset
import java.util.Map
import org.apache.commons.io.FileUtils

abstract class IngraphReportTest {
	
	protected extension Escaper escaper = new Escaper

	def ch(String chapterName, String chapterTitle, Map<String, Map<String, String>> chapterQuerySpecifications) {
		var doc = '''
			\chapter{«chapterTitle»}
			\label{chp:«chapterName»}
		'''

		for (cqs : chapterQuerySpecifications.entrySet) {
			val sectionTitle = cqs.key
			val sectionQuerySpecifications = cqs.value
			 
			val qp = new QueryProcessor
			qp.processQueries(sectionQuerySpecifications)    
			
			val compilingQueryRatio = if (qp.totalQueries == 0) {
				"1"
			} else {
				String.format("%.02f", (qp.compilingQueries as float)/qp.totalQueries)
			}
		
			doc +=
			'''
				\section{«sectionTitle»}

				\progressbar[width=10cm, heighta=.3cm, filledcolor=green, emptycolor=red, borderwidth=1pt, tickswidth=1pt, subdivisions=10]{«compilingQueryRatio»}
				«qp.compilingQueries» of «qp.totalQueries»
				
			'''
			
			for (subsection : qp.subsections) {
				doc += subsection
			}
		}

		FileUtils.writeStringToFile(new File('''../opencypher-report/appendix/«chapterName».tex'''), doc, Charset.defaultCharset())
	}

}
