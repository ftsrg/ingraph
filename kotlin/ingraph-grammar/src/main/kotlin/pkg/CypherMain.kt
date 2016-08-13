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
    //walkCypherStatement(StringReader("MATCH (n) RETURN (n)"))
    //walkCypherStatement(FileReader("../../queries/PosLength.cyp"))
    //walkCypherStatement(FileReader("../../queries/Example.cyp"))
    //walkCypherStatement(FileReader("../../queries/ConnectedSegments.cyp"))
    walkCypherStatement(FileReader("../../queries/RouteSensor.cyp"))
    //walkCypherStatement(FileReader("../../queries/SemaphoreNeighbor.cyp"))
    //walkCypherStatement(FileReader("../../queries/SwitchMonitored.cyp"))
    //walkCypherStatement(FileReader("../../queries/SwitchSet.cyp"))
    //walkCypherStatement(StringReader("MATCH (sensor:Sensor) <-[:monitoredBy]-(segment1:Segment) RETURN sensor, segment1"))
    //walkCypherStatement(StringReader("MATCH (n1),(n2) RETURN n1, n2"))
    //walkCypherStatement(StringReader("MATCH (n1), (n2) RETURN n1, n2"))
    //walkCypherStatement(StringReader("MATCH (n1) , (n2) RETURN n1, n2"))
    //walkCypherStatement(StringReader("MATCH (a:Label1)<-[:fooType]-(:Label2) RETURN a"))
    //walkCypherStatement(StringReader("MATCH (n), (m) RETURN n.value AS n, m.value AS m"))
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