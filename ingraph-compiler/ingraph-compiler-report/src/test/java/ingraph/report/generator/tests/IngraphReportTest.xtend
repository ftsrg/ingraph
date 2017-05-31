package ingraph.report.generator.tests

import ingraph.relalg2tex.config.RelalgConverterConfig
import ingraph.relalg2tex.config.RelalgConverterConfigBuilder
import ingraph.report.generator.data.TestQuery
import ingraph.report.generator.util.TechReportEscaper
import java.io.File
import java.nio.charset.Charset
import java.util.ArrayList
import java.util.Map
import org.apache.commons.io.FileUtils

abstract class IngraphReportTest {

	protected extension TechReportEscaper escaper = new TechReportEscaper

	val PROGRESSBAR_TABLE_HEADER = '''\begin{longtable}{llr@{ of }l}'''
	val PROGRESSBAR_TABLE_FOOTER = '''\end{longtable}'''
	val RelalgConverterConfig treeSerializerConfig

	new() {
		this.treeSerializerConfig = new RelalgConverterConfigBuilder() //
			.setIncludeCommonVariables(true) //
			.setIncludeProductionOperator(true) //
			.setParentheses(true) //
			.setSchemaIndices(true) //
			.build
	}

	new(RelalgConverterConfig treeSerializerConfig) {
		this.treeSerializerConfig = treeSerializerConfig
	}

	def String progressbarTableRow(String color, String title, int working, int total) {
		val ratio = calculateCompilingQueryRatio(working, total)
		'''
			«title» &
			\progressbar[width=9cm, heighta=.3cm, filledcolor=«color», emptycolor=white, borderwidth=1pt, tickswidth=1pt, subdivisions=10]{«ratio»} &
			«working» &
			«total» \\
		'''

	}

	def printChapter(String chapterName, String chapterTitle, String shortTitle,
		Map<String, Iterable<TestQuery>> chapterQuerySpecifications) {
		var doc = '''
			\chapter{«chapterTitle»}
			\label{chp:«chapterName»}
		'''

		val sectionProgressbars = new ArrayList<String>

		var chapterCompilingQueries = 0
		var chapterTotalQueries = 0
		var sections = ''''''
		for (cqs : chapterQuerySpecifications.entrySet) {
			val sectionTitle = cqs.key
			val sectionQuerySpecifications = cqs.value
			val sectionLabel = sectionTitle.toLabel

			val cp = new ChapterProcessor(treeSerializerConfig)
			cp.processQueries(sectionQuerySpecifications)

			val sectionCompilingQueries = cp.compilingQueries
			val sectionTotalQueries = cp.totalQueries
			val sectionProgressbar = progressbarTableRow("gray", '''\hyperref[sec:«sectionLabel»]{«sectionTitle»}''', sectionCompilingQueries, sectionTotalQueries)

			sectionProgressbars.add(sectionProgressbar)
			sections += '''
				\section{«sectionTitle»}
				\label{sec:«sectionLabel»}

				«PROGRESSBAR_TABLE_HEADER»
				«sectionProgressbar»
				«PROGRESSBAR_TABLE_FOOTER»
			'''

			for (subsection : cp.subsections) {
				sections += subsection
			}

			chapterCompilingQueries += sectionCompilingQueries
			chapterTotalQueries += sectionTotalQueries
		}

		doc += '''
				«PROGRESSBAR_TABLE_HEADER»
				\input{appendix/progressbar-«chapterName»}
				«sectionProgressbars.join»
				«PROGRESSBAR_TABLE_FOOTER»
		'''
		doc += sections

		val progressbarTableRow = progressbarTableRow( //
			"progressbargreen", //
			'''\hyperref[chp:«chapterName»]{«shortTitle»}''', //
			chapterCompilingQueries, chapterTotalQueries)

		val appendixDir = "../../opencypher-report/appendix/"
		val charset = Charset.forName("UTF-8")
		FileUtils.writeStringToFile(new File('''«appendixDir»progressbar-«chapterName».tex'''), progressbarTableRow, charset)
		FileUtils.writeStringToFile(new File('''«appendixDir»chapter-«chapterName».tex'''), doc, charset)
	}

	protected def String calculateCompilingQueryRatio(int working, int total) {
		if (total == 0) {
			"1"
		} else {
			String.format("%.02f", (working as float) / total)
		}
	}

}
