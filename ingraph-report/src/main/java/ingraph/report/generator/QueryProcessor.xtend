package ingraph.report.generator

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.logger.IngraphLogger
import ingraph.relalg.inferencers.BasicSchemaInferencer
import ingraph.relalg.inferencers.ExtraVariableInferencer
import ingraph.relalg.inferencers.FullSchemaInferencer
import ingraph.relalg2rete.Relalg2ReteTransformation
import ingraph.relalg2tex.config.RelalgConverterConfig
import ingraph.relalg2tex.converters.relalgconverters.Relalg2TexExpressionConverter
import ingraph.relalg2tex.converters.relalgconverters.Relalg2TexTreeConverter
import ingraph.report.generator.data.TestQuery
import ingraph.report.generator.util.TechReportEscaper
import java.io.Closeable
import java.util.List
import org.apache.commons.lang3.exception.ExceptionUtils
import org.eclipse.emf.ecore.util.EcoreUtil
import relalg.RelalgContainer

class QueryProcessor implements Closeable {
	
	extension Relalg2ReteTransformation relalg2ReteTransformation = new Relalg2ReteTransformation
	extension BasicSchemaInferencer basicSchemaInferencer = new BasicSchemaInferencer
	extension ExtraVariableInferencer extraVariableInferencer = new ExtraVariableInferencer
	extension FullSchemaInferencer fullSchemaInferencer = new FullSchemaInferencer
	extension TechReportEscaper escaper = new TechReportEscaper
	extension IngraphLogger logger = new IngraphLogger(QueryProcessor.name)

	protected val treeSerializerConfig = RelalgConverterConfig.builder.includeCommonVariables(true).textualOperators(true).build
	protected val treeSerializer = new Relalg2TexTreeConverter(treeSerializerConfig)
	protected val expressionConverter = new Relalg2TexExpressionConverter

	def boolean toSubsection(TestQuery testQuery, List<CharSequence> subsections) {
		var RelalgContainer container = null
		try {
			container = Cypher2Relalg.processString(testQuery.querySpecification)
		} catch (Exception e) {
			info(ExceptionUtils.getStackTrace(e))
		}
		subsections.add(subsection(container, testQuery.queryName, testQuery.querySpecification, testQuery.regressionTest))
		return container !== null
	}

	def subsection(RelalgContainer container, String name, String listing, boolean regressionTest) {
		'''
		\subsection[«name.escape»]{«name.escape»«IF (regressionTest)» \textcolor{gray}{[regression test]}«ENDIF»}

		\subsubsection*«"Query specification".toHeader(name)»

		\begin{lstlisting}
		«listing»
		\end{lstlisting}

		«IF container === null»
		\subsubsection*{Cannot parse query}
		Cannot parse query. This is probably a limitation in our current parser and not an error in the query specification.

		«ELSE»
		\subsubsection*«"Relational algebra expression for search-based evaluation".toHeader(name)»

		«val expression = container.expression»
		«IF expression === null»
			Cannot visualize expression.
		«ELSE»
			\begin{align*}
			\begin{autobreak}
			r = «expression»
			\end{autobreak}
			\end{align*}
		«ENDIF»

		\subsubsection*«"Relational algebra tree for search-based evaluation".toHeader(name)»

		«val searchTree = container.visualizeTree»
		«IF searchTree === null»
			Cannot visualize tree.
		«ELSE»
			\begin{center}
			\begin{adjustbox}{max width=\textwidth, max height=\textheight}
			«searchTree»
			\end{adjustbox}
			\end{center}
		«ENDIF»

		\subsubsection*«"Incremental relational algebra tree".toHeader(name)»

		«val incrementalTree = container.visualizeWithTransformations»
		«IF incrementalTree === null»
			Cannot visualize incremental tree.
		«ELSE»
			\begin{center}
			\begin{adjustbox}{max width=\textwidth, max height=\textheight}
			«incrementalTree»
			\end{adjustbox}
			\end{center}
		«ENDIF»

		«ENDIF»
		'''
	}
	
	def toHeader(String title, String queryName) {
		'''{«title.escape» \textcolor{gray}{(«queryName.escape»)}}'''
	}

	def expression(RelalgContainer container) {
		try {
			val expressionContainer = EcoreUtil.copy(container)
			expressionContainer.inferBasicSchema
			expressionConverter.convert(expressionContainer).toString
		} catch (Exception e) {
			info(ExceptionUtils.getStackTrace(e))
			null
		}
	}

	def visualizeTree(RelalgContainer container) {
		try {
			val treeContainer = EcoreUtil.copy(container)
			treeContainer.inferBasicSchema
			treeSerializer.convert(treeContainer)
		} catch (Exception e) {
			info(ExceptionUtils.getStackTrace(e))
			null
		}
	}

	def visualizeWithTransformations(RelalgContainer container) {
		try {
			val incrementalContainer = EcoreUtil.copy(container)
			incrementalContainer.transformToRete
			incrementalContainer.inferBasicSchema
			incrementalContainer.inferExtraVariables
			incrementalContainer.inferFullSchema
			treeSerializer.convert(incrementalContainer)
		} catch (Exception e) {
			info(ExceptionUtils.getStackTrace(e))
			null
		}
	}
	
	override close() {
		relalg2ReteTransformation.close
	}


}