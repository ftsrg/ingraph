package cypher.grammar.my;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import cypher.grammar.CypherLexer;
import cypher.grammar.CypherParser;
import cypher.grammar.CypherParser.CypherContext;

public class HelloMain {

	public static void main(String[] args) {
		
		CharStream input = new ANTLRInputStream("MATCH (n) RETURN (n)");
		CypherLexer lexer = new CypherLexer(input);
		TokenStream tokenStream = new CommonTokenStream(lexer);
		CypherParser parser = new CypherParser(tokenStream);
		
		CypherContext cypher = parser.cypher();		
		System.out.println(cypher);
		
		ParseTreeListener listener = new MyCypherListener();
		ParseTreeWalker.DEFAULT.walk(listener, cypher);
		
	}
}
