package ingraph.sandbox

import ingraph.compiler.CypherToQPlan
import ingraph.compiler.cypher2qplan.CypherParser
import ingraph.compiler.qplan2iplan.QPlanToSqlPlan
import org.scalatest.FunSuite


class SparkSqlSandbox extends FunSuite {

  test("infer schema #1") {
    val cypher = CypherParser.parseString("MATCH (p)-[e:LIVES]->(c) RETURN p.id, c.id")
    val qplan = CypherToQPlan.build(cypher)
    val resolvedPlan = QPlanToSqlPlan.transformAndResolve(qplan)

    println(qplan)
    println(resolvedPlan)
  }

  test("infer schema #2") {
    val cypher = CypherParser.parseString("MATCH (p)-[e:LIVES]->(c) WHERE p.age > 25 RETURN p.name, e.since, c.country")
    val qplan = CypherToQPlan.build(cypher)
    val resolvedPlan = QPlanToSqlPlan.transformAndResolve(qplan)

    println(qplan)
    println(resolvedPlan)
  }

  test("infer schema #3") {
    val cypher = CypherParser.parseString("MATCH (p) WITH p AS p2 RETURN p2.id")
    val qplan = CypherToQPlan.build(cypher)
    val resolvedPlan = QPlanToSqlPlan.transformAndResolve(qplan)

    println(qplan)
    println(resolvedPlan)
  }

}
