package ingraph.report.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.optimization.transformations.relalg2rete.Relalg2ReteTransformation
import ingraph.relalg.inferencers.BasicSchemaInferencer
import ingraph.relalg.inferencers.FullSchemaInferencer
import ingraph.relalg2tex.config.RelalgConverterConfig
import ingraph.relalg2tex.relalgconverters.Relalg2TexTreeConverter
import ingraph.report.tests.escape.Escaper
import java.util.ArrayList
import java.util.List
import java.util.Map
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtend.lib.annotations.Accessors
import relalg.RelalgContainer
import ingraph.relalg2tex.relalgconverters.Relalg2TexExpressionConverter
import ingraph.relalg.inferencers.ExtraVariableInferencer

class QueryProcessor {
	
	extension Relalg2ReteTransformation Relalg2ReteTransformation = new Relalg2ReteTransformation
	extension BasicSchemaInferencer basicSchemaInferencer = new BasicSchemaInferencer
	extension ExtraVariableInferencer extraVariableInferencer = new ExtraVariableInferencer
	extension FullSchemaInferencer fullSchemaInferencer = new FullSchemaInferencer
	extension Escaper escaper = new Escaper

	protected val treeSerializerConfig = RelalgConverterConfig.builder.includeCommonVariables(true).build
	protected val treeSerializer = new Relalg2TexTreeConverter(treeSerializerConfig)
	protected val expressionConverter = new Relalg2TexExpressionConverter

	@Accessors(PUBLIC_GETTER) var totalQueries = 0
	@Accessors(PUBLIC_GETTER) var compilingQueries = 0
	@Accessors(PUBLIC_GETTER) var List<CharSequence> subsections = new ArrayList

	def void processQueries(Map<String, String> querySpecifications) {
		for (qs : querySpecifications.entrySet) {
			toSubsection(qs.key, qs.value)
		}
	}

	def void toSubsection(String queryName, String querySpecification) {
		totalQueries++
		var RelalgContainer container = null
		try {
			container = Cypher2Relalg.processString(querySpecification)
			compilingQueries++
		} catch (Exception e) {
			e.printStackTrace
		}
		subsections.add(subsection(container, queryName, querySpecification))
	}

	def subsection(RelalgContainer container, String name, String listing) {
		'''
		\subsection{«name.escape»}

		\subsubsection*«header("Query specification", name)»

		\begin{lstlisting}
		«listing»
		\end{lstlisting}

		«IF container === null»
		\subsubsection*{Cannot parse query}
		Cannot parse query. This is probably a limitation in our current parser and not an error in the query specification.

		«ELSE»
		\subsubsection*«header("Relational algebra expression for search-based evaluation", name)»

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

		\subsubsection*«header("Relational algebra tree for search-based evaluation", name)»

		«val searchTree = container.visualizeTree»
		«IF searchTree === null»
		Cannot visualize tree.
		«ELSE»
		\begin{center}
		\begin{adjustbox}{max width=\textwidth}
		«searchTree»
		\end{adjustbox}
		\end{center}
		«ENDIF»

		\subsubsection*«header("Incremental relational algebra tree", name)»

		«val incrementalTree = container.visualizeWithTransformations»
		«IF incrementalTree === null»
		Cannot visualize incremental tree.
		«ELSE»
		\begin{center}
		\begin{adjustbox}{max width=\textwidth}
		«incrementalTree»
		\end{adjustbox}
		\end{center}
		«ENDIF»

		«ENDIF»
		'''
	}
	
	def header(String title, String query) {
		'''{«title» \textcolor{gray}{(«query»)}}'''
	}

	def expression(RelalgContainer container) {
		try {
			val container2 = EcoreUtil.copy(container)
			container2.inferBasicSchema
			expressionConverter.convert(container2).toString
		} catch (Exception e) {
			e.printStackTrace
			null
		}
	}

	def visualizeTree(RelalgContainer container) {
		try {
			val treeContainer = EcoreUtil.copy(container)
			treeContainer.inferBasicSchema
			treeSerializer.convert(treeContainer)
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
			container2.inferExtraVariables
			container2.inferFullSchema
			treeSerializer.convert(container2)
		} catch (Exception e) {
			e.printStackTrace
			null
		}
	}


}