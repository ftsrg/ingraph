package cypher.main;

import cypher.grammar.CypherLexer;
import cypher.grammar.CypherParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import cypher.grammar.CypherParser.CypherContext;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class HelloMain {

	public static void main(String[] args) throws IOException {
		final String query = FileUtils.readFileToString(new File(args[0]), "UTF-8");

		final CharStream input = new ANTLRInputStream(query);
		final CypherLexer lexer = new CypherLexer(input);
		final TokenStream tokenStream = new CommonTokenStream(lexer);
		final CypherParser parser = new CypherParser(tokenStream);
		
		final CypherContext cypher = parser.cypher();
		System.out.println(cypher);
		
		final ParseTreeListener listener = new MyCypherListener();
		ParseTreeWalker.DEFAULT.walk(listener, cypher);
		
	}
}
