package pkg

import cypher.grammar.CypherLexer
import cypher.grammar.CypherParser
import java.io.FileReader
import java.io.Reader
import java.io.StringReader
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

fun main(args: Array<String>) {
    walkCypherStatement(StringReader("MATCH (n) RETURN (n)"))
    walkCypherStatement(FileReader("../../queries/PosLength.cyp"))
}

fun walkCypherStatement(stmt: Reader) {
    val input = ANTLRInputStream(stmt)
    val lexer = CypherLexer(input)
    val tokenStream = CommonTokenStream(lexer)
    val parser = CypherParser(tokenStream)

    val cypher = parser.cypher()
    println(cypher)

    val listener = MyCypherListener()
    ParseTreeWalker.DEFAULT.walk(listener, cypher)
}