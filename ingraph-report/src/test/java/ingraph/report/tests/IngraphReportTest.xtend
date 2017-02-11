package ingraph.report.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.optimization.transformations.relalg2rete.Relalg2ReteTransformation
import ingraph.relalg.inferencers.BasicSchemaInferencer
import ingraph.relalg.inferencers.ExtraAttributeInferencer
import ingraph.relalg.inferencers.FullSchemaInferencer
import ingraph.relalg2tex.config.RelalgSerializerConfig
import ingraph.relalg2tex.serializers.RelalgExpressionSerializer
import ingraph.relalg2tex.serializers.RelalgTreeSerializer
import ingraph.report.tests.util.NaturalOrderComparator
import java.io.File
import java.nio.charset.Charset
import java.util.Collections
import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils
import org.eclipse.emf.ecore.util.EcoreUtil
import relalg.RelalgContainer

class IngraphReportTest {

  extension Relalg2ReteTransformation Relalg2ReteTransformation = new Relalg2ReteTransformation
  extension BasicSchemaInferencer basicSchemaInferencer = new BasicSchemaInferencer
  extension ExtraAttributeInferencer extraAttributeInferencer = new ExtraAttributeInferencer
  extension FullSchemaInferencer fullSchemaInferencer = new FullSchemaInferencer

	protected val treeSerializerConfig = RelalgSerializerConfig.builder.includeCommonVariables(true).build

	protected val treeSerializer = new RelalgTreeSerializer(treeSerializerConfig)
	protected val expressionSerializer = new RelalgExpressionSerializer

	def toChapter(String directoryName, String chapterTitle) {
	val fileNames = FileUtils.listFiles(new File('''../queries/«directoryName»'''), #["cypher"], false).map[name].
		toList
	Collections.sort(fileNames, new NaturalOrderComparator());

	val doc = '''
		\chapter{«chapterTitle»}
		\label{chp:«directoryName»}

		\section{Queries}

		«FOR fileName : fileNames»
		«val query = FilenameUtils.removeExtension(fileName)»
		«toSection(directoryName, query)»
		«ENDFOR»
	'''
	FileUtils.writeStringToFile(new File('''../opencypher-report/appendix/«directoryName».tex'''), doc,
		Charset.defaultCharset())
	}

	/**
	 * Drops indentation and quotes.
	 */
	def cleanup(String s) {
	s //
	.replaceAll('''"""''', "") // """
	.replaceAll("'''", "") // '''
	.replaceAll("\n		", "\n") // indentation
	.replaceAll("^\n", "") // newline at the start
	.replaceAll("\n$", "") // newline at the end
	}

	def escape(String s) {
	s //
	.replaceAll('''#''', ''' no.''') //
	.replaceAll('''_''', '''\_''') //
	.replaceAll('''`''', "'") //
	}

	def expression(RelalgContainer container) {
	try {
		val container2 = EcoreUtil.copy(container)
	  container2.inferBasicSchema
		expressionSerializer.serialize(container2).toString
	} catch (Exception e) {
		e.printStackTrace
		null
	}
	}

	def visualizeTree(RelalgContainer container) {
	try {
	  val treeContainer = EcoreUtil.copy(container)
	  treeContainer.inferBasicSchema
		treeSerializer.serialize(treeContainer)
	} catch (Exception e) {
		e.printStackTrace
		null
	}
	}

	def visualizeWithTransformations(RelalgContainer container) {
		try {
		  val container2 = EcoreUtil.copy(container)
		  container2.transformToRete
		  container2.inferBasicSchema
		  container2.inferExtraAttributes
      container2.inferFullSchema
			treeSerializer.serialize(container2)
		} catch (Exception e) {
			e.printStackTrace
			null
		}
	}

	def toSection(String directoryName, String query) {
	val fileName = '''../queries/«directoryName»/«query».cypher'''
	val querySpecification = FileUtils.readFileToString(new File(fileName), Charset.forName("UTF-8"))

	println(querySpecification)
	var RelalgContainer container = null
	try {
		container = Cypher2Relalg.processString(querySpecification.toString)
	} catch (Exception e) {
		e.printStackTrace
	}

	section(container, query, querySpecification)
	}

	def section(RelalgContainer container, String name, String listing) {
	'''
	\subsection{«name.escape»}

	\subsubsection*«header("Query specification", name)»

	\begin{lstlisting}
	«listing»
	\end{lstlisting}

	«IF container == null»
	\subsubsection*{Cannot parse query}
	Cannot parse query. This is probably a limitation in our current parser and not an error in the query specification.

	«ELSE»
«««	start of subsubsections for expression
	\subsubsection*«header("Relational algebra expression", name)»

	«val expression = container.expression»
	«IF expression == null»
	Cannot visualize expression.
	«ELSE»
	\begin{align*}
	\begin{autobreak}
	r = «expression»
	\end{autobreak}
	\end{align*}
	«ENDIF»

	\subsubsection*«header("Relational algebra tree", name)»

	«val basicTree = container.visualizeTree»
	«IF basicTree == null»
	Cannot visualize tree.
	«ELSE»
	\begin{center}
	\begin{adjustbox}{max width=\textwidth}
	«basicTree»
	\end{adjustbox}
	\end{center}
	«ENDIF»

	\subsubsection*«header("Incremental relational algebra tree", name)»

	«val incrementalTree = container.visualizeWithTransformations»
	«IF incrementalTree == null»
	Cannot visualize incremental tree.
	«ELSE»
	\begin{center}
	\begin{adjustbox}{max width=\textwidth}
	«incrementalTree»
	\end{adjustbox}
	\end{center}
	«ENDIF»
«««	end of subsubsections for expression
	«ENDIF»
	'''
	}
  
  def header(String title, String query) {
    '''{«title» \textcolor{gray}{(«query»)}}'''
  }

}
