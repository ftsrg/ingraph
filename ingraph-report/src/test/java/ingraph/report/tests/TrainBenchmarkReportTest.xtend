package ingraph.report.tests

import java.io.File
import java.nio.charset.Charset
import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils
import org.junit.Test

class TrainBenchmarkReportTest extends IngraphReportTest {

	@Test
	def void generateChapter() {
		val files = FileUtils.listFiles(new File("../queries/trainbenchmark"), #["cypher"], true);

		val doc = '''
			\chapter{TCK Acceptance Tests}
			\label{chp:acceptance-tests}
			
			«FOR file : files» 
				«val query = FilenameUtils.removeExtension(file.name)»
				«toSection(query)»
			«ENDFOR»
		'''
		FileUtils.writeStringToFile(new File("../opencypher-report/trainbenchmark.tex"), doc, Charset.defaultCharset())
	}

	def toSection(String query) {
		val fileName = "../queries/trainbenchmark/" + query + ".cypher"
		val querySpecification = FileUtils.readFileToString(new File(fileName), Charset.forName("UTF-8"))

		'''
		\section{«query»}
		
		\subsection*{Query specification}
		
		\begin{lstlisting}
		«querySpecification»
		\end{lstlisting}
		
		\subsection*{Relational algebra expression}
		
		\begin{flalign*}
		«querySpecification.expression»
		\end{flalign*}
		
		\subsection*{Relational algebra tree}
		«querySpecification.expression.visualize»
		
		\subsection*{Relational algebra tree for incremental queries}
		«querySpecification.visualizeWithTransformations»
		'''
	}

}
