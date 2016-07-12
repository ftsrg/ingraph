package cypher.main;

import cypher.grammar.CypherLexer;
import cypher.grammar.CypherParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class MyParser {

    public static void parse(final String filename) throws IOException {
        final String filepath = "../queries/" + filename + ".cyp";
        final String query = FileUtils.readFileToString(new File(filepath), "UTF-8");

        final CharStream input = new ANTLRInputStream(query);
        final CypherLexer lexer = new CypherLexer(input);
        final TokenStream tokenStream = new CommonTokenStream(lexer);
        final CypherParser parser = new CypherParser(tokenStream);

        final CypherParser.CypherContext cypher = parser.cypher();
        System.out.println("context: " + cypher);

        final ParseTreeListener listener = new MyCypherListener();
        ParseTreeWalker.DEFAULT.walk(listener, cypher);
    }
}
