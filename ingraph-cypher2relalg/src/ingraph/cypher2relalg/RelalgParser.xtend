package ingraph.cypher2relalg

import ingraph.antlr.CypherLexer
import ingraph.antlr.CypherParser
import java.io.File
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import org.apache.commons.io.FileUtils
import org.antlr.v4.runtime.tree.ParseTreeWalker
import ingraph.antlr.CypherParser.CypherContext

class RelalgParser {

	def static parse(String queryName) {
		println("Parsing query: " + queryName)
		println("=================================")
		
		val filepath = "../queries/" + queryName + ".cyp"
		val query = FileUtils.readFileToString(new File(filepath), "UTF-8")

		val input = new ANTLRInputStream(query)
		val lexer = new CypherLexer(input)
		val tokenStream = new CommonTokenStream(lexer)
		val parser = new CypherParser(tokenStream)

		val CypherContext cypher = parser.cypher()

		val listener = new RelalgCypherListener()
		ParseTreeWalker.DEFAULT.walk(listener, cypher)

		println("Vertex labels:    " + listener.vertexLabelFactory.elements.entrySet.map[key.toString].join(", "))
		println("Vertex variables: " + listener.vertexVariableFactory.vertexVariables.entrySet.map[key.toString].join(", "))
		println()
		println("Edge labels:    " + listener.edgeLabelFactory.elements.entrySet.map[key.toString].join(", "))
		println("Edge variables: " + listener.edgeVariableFactory.edgeVariables.entrySet.map[key.toString].join(", "))
		println()
	}

}
