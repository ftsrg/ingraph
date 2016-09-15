package ingraph.cypher2relalg

import ingraph.antlr.CypherLexer
import ingraph.antlr.CypherParser
import java.io.File
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker
import org.apache.commons.io.FileUtils
import ingraph.cypher2relalg.cypherlisteners.RelalgCypherListener

class RelalgParser {

	def static parseFile(String queryName) {
		val filepath = "../queries/" + queryName + ".cyp"
		val query = FileUtils.readFileToString(new File(filepath), "UTF-8")
		parse(query)
	}	
	
	def static parse(String query) {
		val input = new ANTLRInputStream(query)
		val lexer = new CypherLexer(input)
		val tokenStream = new CommonTokenStream(lexer)
		val parser = new CypherParser(tokenStream)

		val context = parser.cypher()
		val listener = new RelalgCypherListener()
		ParseTreeWalker.DEFAULT.walk(listener, context)

		return listener.container
	}

}
