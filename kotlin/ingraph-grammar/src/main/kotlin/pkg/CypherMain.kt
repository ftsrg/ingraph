package pkg

import cypher.grammar.CypherLexer
import cypher.grammar.CypherParser
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

fun main(args: Array<String>) {
    val input = ANTLRInputStream("MATCH (n) RETURN (n)")
    val lexer = CypherLexer(input)
    val tokenStream = CommonTokenStream(lexer)
    val parser = CypherParser(tokenStream)

    val cypher = parser.cypher()
    println(cypher)

    val listener = MyCypherListener()
    ParseTreeWalker.DEFAULT.walk(listener, cypher)
}
