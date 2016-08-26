package ingraph.cypher2relalg

import ingraph.antlr.CypherLexer
import ingraph.antlr.CypherParser
import java.io.File
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import org.apache.commons.io.FileUtils
import org.antlr.v4.runtime.tree.ParseTreeWalker

class RelalgParser {

	def static parse(String filename) {
		val filepath = "../queries/" + filename + ".cyp";
		val query = FileUtils.readFileToString(new File(filepath), "UTF-8");

		val input = new ANTLRInputStream(query);
		val lexer = new CypherLexer(input);
		val tokenStream = new CommonTokenStream(lexer);
		val parser = new CypherParser(tokenStream);

		val cypher = parser.cypher();
		val listener = new RelalgCypherListener();

		ParseTreeWalker.DEFAULT.walk(listener, cypher);
		
		println(listener.vertexLabels)
		println(listener.vertexVariables)
	}

}
