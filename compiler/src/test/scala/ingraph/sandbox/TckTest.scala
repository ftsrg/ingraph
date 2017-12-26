package ingraph.sandbox

import ingraph.compiler.{CypherToQPlan, FPlanParser}
import ingraph.compiler.cypher2qplan.{CypherParser, QPlanResolver}
import ingraph.compiler.qplan2jplan.{QPlanToJPlan, SchemaInferencer}
import ingraph.model.fplan.FNode
import ingraph.model.jplan.JNode
import ingraph.model.qplan.QNode
import org.scalatest.FunSuite

class TckTest extends FunSuite {

  val log = true

  case class CompilationStages(
    qPlan: QNode,
    jPlan: JNode,
    fPlan: FNode
  )

  def compile(query: String): CompilationStages = {
    val cypher = CypherParser.parseString(query)
    val qPlan = CypherToQPlan.build(cypher)
    val jPlan = QPlanToJPlan.transform(qPlan)
    val fPlan = SchemaInferencer.transform(jPlan)

    if (log) {
      println("=============================================================================")
      println("Query:")
      println(query)
      println("-----------------------------------------------------------------------------")
      println("QPlan:")
      println(qPlan)
      println("-----------------------------------------------------------------------------")
      println("JPlan:")
      println(jPlan)
      println("-----------------------------------------------------------------------------")
      println("FPlan:")
//      println(fPlan)
    }

    return CompilationStages(qPlan, jPlan, fPlan)
  }

  test("Hello World") {
    val stages = compile(
      """MATCH (n)
        |RETURN n
      """.stripMargin
    )
  }

  test("Filtering for vertices in MATCH") {
    val stages = compile(
      """MATCH (n {name: 'John'})
        |RETURN n
      """.stripMargin
    )
  }

  test("Filtering for edges in MATCH") {
    val stages = compile(
      """MATCH (n)-[:REL {prop: 'value'}]->(m)
        |RETURN n, m
      """.stripMargin
    )
  }

  test("TCK test: Use multiple MATCH clauses to do a Cartesian product") {
    val stages = compile(
      """MATCH (n), (m)
        |RETURN n.value AS n, m.value AS m
      """.stripMargin
    )
  }

  test("TCK test: Filter out based on node prop name") {
    val stages = compile(
      """MATCH ()-[rel:X]-(a)
        |WHERE a.name = 'Andres'
        |RETURN a
      """.stripMargin
    )
  }

  test("TCK test: Filter based on rel prop name") {
    val stages = compile(
      """MATCH (node)-[r:KNOWS]->(a)
        |WHERE r.name = 'monkey'
        |RETURN a
      """.stripMargin
    )
  }

  test("TCK test: Get neighbours") {
    val stages = compile(
      """MATCH (n1)-[rel:KNOWS]->(n2)
        |RETURN n1, n2
      """.stripMargin
    )
  }

  test("TCK test: Get two related nodes") {
    val stages = compile(
      """MATCH ()-[rel:KNOWS]->(x)
        |RETURN x
      """.stripMargin
    )
  }

  test("TCK test: Get related to related to") {
    val stages = compile(
      """MATCH (n)-->(a)-->(b)
        |RETURN b
      """.stripMargin
    )
  }

  test("TCK test: Handle comparison between node properties") {
    val stages = compile(
      """MATCH (n)-[rel]->(x)
        |WHERE n.animal = x.animal
        |RETURN n, x
      """.stripMargin
    )
  }

  test("TCK test: Handle OR in the WHERE clause") {
    val stages = compile(
      """MATCH (n)
        |WHERE n.p1 = 12 OR n.p2 = 13
        |RETURN n
      """.stripMargin
    )
  }

  test("TCK test: Return relationships by collecting them as a list") {
    val stages = compile(
      """MATCH (a:Start)-[r:REL*2..2]-(b)
        |RETURN r
      """.stripMargin
    )
  }

//  test("TCK test: ") {
//    val stages = compile(
//      """
//      """.stripMargin
//    )
//  }

}
