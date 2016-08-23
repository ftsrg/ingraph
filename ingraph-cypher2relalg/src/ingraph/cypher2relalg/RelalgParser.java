package ingraph.cypher2relalg;

import ingraph.antlr.CypherLexer;
import ingraph.antlr.CypherParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class RelalgParser {

    public static <K,V> V lookup(K key, Map<? super K, V> map) {
        if(map.containsKey(key)) return map.get(key);
        else throw new UnsupportedOperationException(key + " is not available in this map.");
    }

    public static void parse(final String filename) throws IOException {
        final String filepath = "../queries/" + filename + ".cyp";
        final String query = FileUtils.readFileToString(new File(filepath), "UTF-8");

        final CharStream input = new ANTLRInputStream(query);
        final CypherLexer lexer = new CypherLexer(input);
        final TokenStream tokenStream = new CommonTokenStream(lexer);
        final CypherParser parser = new CypherParser(tokenStream);

        final CypherParser.CypherContext cypher = parser.cypher();
        System.out.println("context: " + cypher);

        final ingraph.cypher2relalg.RelalgCypherListener listener = new ingraph.cypher2relalg.RelalgCypherListener();
        ParseTreeWalker.DEFAULT.walk(listener, cypher);

        System.out.println();
        System.out.println("relations:");

        // xtend it.getValue().lookup(listener.relationSource)

        listener.relations.entrySet().forEach(
            it -> System.out.println(it.getKey() + ", source: " + lookup(it.getValue(), listener.relationSource).getName() + ", target: " + lookup(it.getValue(), listener.relationTarget).getName())
        );

        System.out.println();
        System.out.println("attributes:");
        listener.attributes.entrySet().forEach(
            it -> System.out.println(it.getKey() + ", labels: " + listener.nodeLabels.get(it.getValue()))
        );
    }
}
