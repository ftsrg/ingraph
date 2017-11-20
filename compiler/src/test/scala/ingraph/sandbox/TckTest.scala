package ingraph.sandbox

import ingraph.compiler.{CypherToQPlan, FPlanParser}
import ingraph.compiler.cypher2qplan.{CypherParser, QPlanResolver}
import ingraph.compiler.qplan2jplan.{QPlanToJPlan, SchemaInferencer}
import ingraph.model.fplan.FNode
import ingraph.model.jplan.JNode
import ingraph.model.qplan.QNode
import org.scalatest.FunSuite

class TckTest extends FunSuite {

  case class CompilationStages(
    unresolvedQPlan: QNode,
    resolvedQPlan: QNode,
    jPlan: JNode,
    fPlan: FNode
  )

  def compile(query: String): CompilationStages = {
    val cypher = CypherParser.parseString(query)
    val unresolvedQPlan = CypherToQPlan.build(cypher)
    val resolvedQPlan = QPlanResolver.resolveQPlan(unresolvedQPlan)
    val jPlan = QPlanToJPlan.transform(resolvedQPlan)
    val fPlan = SchemaInferencer.transform(jPlan)

    println("=============================================================================")
    println("Query:")
    println(query)
    println("-----------------------------------------------------------------------------")
    println("Unresolved QPlan:")
    println(unresolvedQPlan)
    println("-----------------------------------------------------------------------------")
    println("Resolved QPlan:")
    println(resolvedQPlan)

    return CompilationStages(unresolvedQPlan, resolvedQPlan, jPlan, fPlan)
  }

  test("Hello World") {
    val stages = compile(
      """MATCH (n)
        |RETURN n
      """.stripMargin
    )
  }

  test("TCK test for MATCH 1") {
    val stages = compile(
      """MATCH (n), (m)
        |RETURN n.value AS n, m.value AS m
      """.stripMargin
    )
  }

}
