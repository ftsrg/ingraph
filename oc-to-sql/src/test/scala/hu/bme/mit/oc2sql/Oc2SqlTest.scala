package hu.bme.mit.oc2sql

import ingraph.compiler.CypherToQPlan
import ingraph.compiler.cypher2qplan.CypherParser
import org.scalatest.FunSuite

class Oc2SqlTest extends FunSuite {

  test("convert to SQL") {
    val cypher = CypherParser.parseString(
      """MATCH (segment:Segment)
        |WHERE segment.length <= 0
        |RETURN segment, segment.length AS length
        |""".stripMargin)
    val qplan = CypherToQPlan.build(cypher)
    println(qplan)
  }

}
