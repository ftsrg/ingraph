package ingraph.sandbox.compiler

import ingraph.compiler.CypherToQPlan
import ingraph.compiler.cypher2qplan.CypherParser
import org.scalatest.FunSuite

class DmlTest extends FunSuite {

  test("should compile MATCH") {
    val cypher = CypherParser.parseString("MATCH (n) RETURN n")
    val plan = CypherToQPlan.compileToQPlan(cypher)
    println(plan)
  }

  test("should compile MATCH and UNWIND") {
    val cypher = CypherParser.parseString("MATCH (n) UNWIND n.favColors AS favColor RETURN n, favColor")
    val plan = CypherToQPlan.compileToQPlan(cypher)
    println(plan)
  }

  test("should compile vanilla CREATE") {
    val cypher = CypherParser.parseString("CREATE (:Person)")
    val plan = CypherToQPlan.compileToQPlan(cypher)
    println(plan)
  }

  test("should compile CREATE after MATCH") {
    val cypher = CypherParser.parseString("MATCH (n) CREATE (n)-[:KNOWS]->(:Person)")
    val plan = CypherToQPlan.compileToQPlan(cypher)
    println(plan)
  }

  test("should compile CREATE after MATCH on two nodes") {
    val cypher = CypherParser.parseString("MATCH (n:Person), (c:City) CREATE (n)-[:KNOWS]->(c)")
    val plan = CypherToQPlan.compileToQPlan(cypher)
    println(plan)
  }


}
