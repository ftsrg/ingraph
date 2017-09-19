package ingraph.sandbox

import ingraph.compiler.CypherToQPlan
import ingraph.compiler.cypher2qplan.CypherParser
import org.scalatest.FunSuite

class DmlTest extends FunSuite {

  test("should compile MATCH") {
    val cypher = CypherParser.parseString("MATCH (n) RETURN n")
    val plan = CypherToQPlan.build(cypher)
    println(plan)
  }

  test("should compile MATCH and UNWIND") {
    val cypher = CypherParser.parseString("MATCH (n) UNWIND n.favColors AS favColor RETURN n, favColor")
    val plan = CypherToQPlan.build(cypher)
    println(plan)
  }

  test("should compile MATCH and multiple UNWINDs") {
    val cypher = CypherParser.parseString(
      """MATCH (n)
        |UNWIND n.favColors AS favColor
        |UNWIND n.favMovies AS favMovie
        |RETURN n, favColor, favMovie
        |""".stripMargin)
    val plan = CypherToQPlan.build(cypher)
    println(plan)
  }

  test("should compile simple DELETE for a vertex") {
    val cypher = CypherParser.parseString("MATCH (n:Person) DELETE n")
    val plan = CypherToQPlan.build(cypher)
    println(plan)
  }

  test("should compile simple DELETE for an edge") {
    val cypher = CypherParser.parseString("MATCH (p1:Person)-[r:KNOWS]-(p2:Person) DELETE r")
    val plan = CypherToQPlan.build(cypher)
    println(plan)
  }

  test("should compile simple DELETE for an entire pattern") {
    val cypher = CypherParser.parseString("MATCH (p1:Person)-[r:KNOWS]-(p2:Person) DELETE p1, r, p2")
    val plan = CypherToQPlan.build(cypher)
    println(plan)
  }

  test("should compile vanilla CREATE") {
    val cypher = CypherParser.parseString("CREATE (p:Person)")
    val plan = CypherToQPlan.build(cypher)
    println(plan)
  }

  test("should compile CREATE after MATCH") {
    val cypher = CypherParser.parseString("MATCH (n) CREATE (n)-[:KNOWS]->(:Person)")
    val plan = CypherToQPlan.build(cypher)
    println(plan)
  }

  test("should compile CREATE after MATCH on two nodes") {
    val cypher = CypherParser.parseString("MATCH (n:Person), (c:City) CREATE (n)-[:KNOWS]->(c)")
    val plan = CypherToQPlan.build(cypher)
    println(plan)
  }

  test("should compile REMOVE that targets labels") {
    val cypher = CypherParser.parseString("MATCH (n) REMOVE n:Actor:Director")
    val plan = CypherToQPlan.build(cypher)
    println(plan)
  }

  test("should compile SET that targets labels") {
    val cypher = CypherParser.parseString("MATCH (n) SET n:Actor:Director")
    val plan = CypherToQPlan.build(cypher)
    println(plan)
  }

}
