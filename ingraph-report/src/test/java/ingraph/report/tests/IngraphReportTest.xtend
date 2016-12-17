package ingraph.report.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.optimization.transformations.relalg2rete.Relalg2ReteTransformation
import ingraph.relalg.util.SchemaInferencer
import ingraph.relalg2tex.RelalgExpressionSerializer
import ingraph.relalg2tex.RelalgSerializerConfig
import ingraph.relalg2tex.RelalgTreeSerializer
import java.io.File
import java.nio.charset.Charset
import java.util.Collections
import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils
import relalg.RelalgContainer

class IngraphReportTest {

    protected extension SchemaInferencer inferencer = new SchemaInferencer
    protected extension Relalg2ReteTransformation Relalg2ReteTransformation = new Relalg2ReteTransformation
    protected val treeSerializer = new RelalgTreeSerializer
    protected val config = RelalgSerializerConfig.builder.build
    protected val expressionSerializer = new RelalgExpressionSerializer(config)

    def toChapter(String directoryName, String chapterTitle) {
        val fileNames = FileUtils.listFiles(new File('''../queries/«directoryName»'''), #["cypher"], false).map[name].
            toList
        Collections.sort(fileNames, new NaturalOrderComparator());

        val doc = '''
            \chapter{«chapterTitle»}
            \label{chp:«directoryName»}
            
            «FOR fileName : fileNames»
                «val query = FilenameUtils.removeExtension(fileName)»
                «toSection(directoryName, query)»
            «ENDFOR»
        '''
        FileUtils.writeStringToFile(new File('''../opencypher-report/«directoryName».tex'''), doc,
            Charset.defaultCharset())
    }

    /**
     * Drops indentation and quotes.
     */
    def cleanup(String s) {
        s //
        .replaceAll('''"""''', "") // """
        .replaceAll("'''", "") // '''
        .replaceAll("\n      ", "\n") // indentation
        .replaceAll("^\n", "") // newline at the start
        .replaceAll("\n$", "") // newline at the end
    }

    def escape(String s) {
        s //
        .replaceAll('''#''', '''\\#''') //
        .replaceAll('''_''', '''\\_''')
    }

    def expression(RelalgContainer container) {
        try {
            expressionSerializer.serialize(container.addSchemaInformation).toString
        } catch (Exception e) {
            e.printStackTrace
            '''\text{Cannot convert to expression.}'''
        }
    }

    def visualize(RelalgContainer container) {
        try {
            treeSerializer.serialize(container.addSchemaInformation)
        } catch (Exception e) {
            e.printStackTrace
            '''\text{Cannot visualize tree.}'''
        }
    }

    def visualizeWithTransformations(RelalgContainer container) {
        try {
            treeSerializer.serialize(container.transformToRete.addSchemaInformation)
        } catch (Exception e) {
            e.printStackTrace
            '''Cannot visualize incremental tree.'''
        }
    }

    def toSection(String directoryName, String query) {
        val fileName = '''../queries/«directoryName»/«query».cypher'''
        val querySpecification = FileUtils.readFileToString(new File(fileName), Charset.forName("UTF-8"))

        println(querySpecification)
        var RelalgContainer cypher = null
        try {
            cypher = Cypher2Relalg.processString(querySpecification.toString)
        } catch (Exception e) {
            e.printStackTrace
        }

        '''
            \section{«query»}
            
            \subsection*{Query specification}
            
            \begin{lstlisting}
            «querySpecification»
            \end{lstlisting}
            
            \subsection*{Relational algebra expression}
            
            \begin{flalign*}
            «cypher.expression»
            \end{flalign*}
            
            \subsection*{Relational algebra tree}
            \adjustbox{max width=\textwidth}{%
            «cypher.visualize»
            }
            
            \subsection*{Relational algebra tree for incremental queries}
            \adjustbox{max width=\textwidth}{%
            «cypher.visualizeWithTransformations»
            }
            
        '''
    }

}
