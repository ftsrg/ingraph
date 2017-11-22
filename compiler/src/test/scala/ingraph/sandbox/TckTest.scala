package ingraph.sandbox

import ingraph.compiler.CypherToQPlan
import ingraph.compiler.cypher2qplan.{CypherParser, QPlanResolver}
import ingraph.compiler.qplan2jplan.{QPlanToJPlan, SchemaInferencer}
import ingraph.model.fplan.{FNode, LeafFNode}
import ingraph.model.jplan.JNode
import ingraph.model.qplan.QNode
import org.scalatest.FunSuite

class TckTest extends FunSuite {

  val log = true

  case class CompilationStages(
    unresolvedQPlan: QNode,
    resolvedQPlan: QNode,
    jplan: JNode,
    fplan: FNode
  )

  def compile(query: String): CompilationStages = {
    val cypher = CypherParser.parseString(query)
    val unresolvedQPlan = CypherToQPlan.build(cypher)
    val resolvedQPlan = QPlanResolver.resolveQPlan(unresolvedQPlan)
    val jplan = QPlanToJPlan.transform(resolvedQPlan)
    val fplan = SchemaInferencer.transform(jplan)

    if (log) {
      println("=============================================================================")
      println("Query:")
      println(query)
      println("-----------------------------------------------------------------------------")
      println("Unresolved QPlan:")
      println(unresolvedQPlan)
      println("-----------------------------------------------------------------------------")
      println("Resolved QPlan:")
      println(resolvedQPlan)
      println("-----------------------------------------------------------------------------")
      println("JPlan:")
      println(jplan)
      println("-----------------------------------------------------------------------------")
      println("FPlan:")
//      println(fplan)
    }

    return CompilationStages(unresolvedQPlan, resolvedQPlan, jplan, fplan)
  }

  test("CREATE") {
    val stages = compile("""CREATE (t:Train {a: 1})""")
  }

  test("Hello World") {
    val stages = compile(
      """MATCH (n)
        |RETURN n
      """.stripMargin
    )
  }

  def getLeafNodes(plan: FNode): Seq[FNode] = {
    if (plan.isInstanceOf[LeafFNode]) return plan :: Nil
    return plan.children.flatMap(x => getLeafNodes(x))
  }

  test("Filtering for vertices in MATCH") {
    val stages = compile(
      """MATCH (n {name: 'John'})
        |RETURN n
      """.stripMargin
    )
    assert(getLeafNodes(stages.fplan)(0).extraAttributes.length == 1)
  }

  test("Filtering for edges in MATCH") {
    val stages = compile(
      """MATCH (n)-[:REL {prop: 'value'}]->(m)
        |RETURN n, m
      """.stripMargin
    )
    assert(getLeafNodes(stages.fplan)(0).extraAttributes.length == 1)
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
    assert(getLeafNodes(stages.fplan)(0).extraAttributes.length == 1)
  }

  test("TCK test: Filter based on rel prop name") {
    val stages = compile(
      """MATCH (node)-[r:KNOWS]->(a)
        |WHERE r.name = 'monkey'
        |RETURN a
      """.stripMargin
    )
    assert(getLeafNodes(stages.fplan)(0).extraAttributes.length == 1)
  }

  test("TCK test: Get neighbours") {
    val stages = compile(
      """MATCH (n1)-[rel:KNOWS]->(n2)
        |RETURN n1, n2
      """.stripMargin
    )
    assert(getLeafNodes(stages.fplan)(0).extraAttributes.length == 0)
  }

  test("TCK test: Get two related nodes") {
    val stages = compile(
      """MATCH ()-[rel:KNOWS]->(x)
        |RETURN x
      """.stripMargin
    )
    assert(getLeafNodes(stages.fplan)(0).extraAttributes.length == 0)
  }

  test("TCK test: Get related to related to") {
    val stages = compile(
      """MATCH (n)-->(a)-->(b)
        |RETURN b
      """.stripMargin
    )
    assert(getLeafNodes(stages.fplan)(0).extraAttributes.length == 0)
  }

  test("TCK test: Handle comparison between node properties") {
    val stages = compile(
      """MATCH (n)-[rel]->(x)
        |WHERE n.animal = x.animal
        |RETURN n, x
      """.stripMargin
    )
    assert(getLeafNodes(stages.fplan)(0).extraAttributes.length == 2)
  }

  test("TCK test: Handle OR in the WHERE clause") {
    val stages = compile(
      """MATCH (n)
        |WHERE n.p1 = 12 OR n.p2 = 13
        |RETURN n
      """.stripMargin
    )
    assert(getLeafNodes(stages.fplan)(0).extraAttributes.length == 2)
  }

  test("TCK test: Return relationships by collecting them as a list") {
    val stages = compile(
      """MATCH (a:Start)-[r:REL*2..2]-(b)
        |RETURN r
      """.stripMargin
    )
    assert(getLeafNodes(stages.fplan)(0).extraAttributes.length == 0)
  }

//  test("TCK test: ") {
//    val stages = compile(
//      """
//      """.stripMargin
//    )
//  }

}
