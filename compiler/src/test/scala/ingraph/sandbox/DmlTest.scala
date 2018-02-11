package ingraph.sandbox

import ingraph.compiler.CypherToQPlan
import ingraph.compiler.cypher2qplan.CypherParser
import ingraph.model.fplan

class DmlTest extends CompilerTest {

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

  test("should compile vanilla CREATE with attributes") {
    val cypher = CypherParser.parseString("CREATE (p:Person {name: 'Batman'})")
    val plan = CypherToQPlan.build(cypher)
    println(plan)
  }

  test("should compile vanilla CREATE with attributes 2") {
    val cypher = CypherParser.parseString("CREATE (t:Train {a: 1})")
    val plan = CypherToQPlan.build(cypher)
    println(plan)
  }

  test("should compile CREATE with a single edges") {
    val cypher = CypherParser.parseString("CREATE (n:Person)-[:LIVES]->(c:City)")
    val plan = CypherToQPlan.build(cypher)
    println(plan)
  }

  test("should compile CREATE with two edges") {
    val stages = compile("CREATE (n:Person)-[:LIVES]->(c:City)-[:IS_LOCATED_IN]->(ctr:Country)")

    println(stages.jplan.children(0)                                                .output)
    println(stages.jplan.children(0).children(0)                                    .output)
    println(stages.jplan.children(0).children(0).children(0)                        .output)
    println(stages.jplan.children(0).children(0).children(0).children(0)            .output)
    println(stages.jplan.children(0).children(0).children(0).children(0).children(0).output)

    println("-------")
    println("-------")

    println(stages.fplan.children(0)                                                .asInstanceOf[fplan.Create].attribute)
    println(stages.fplan.children(0).children(0)                                    .asInstanceOf[fplan.Create].attribute)
    println(stages.fplan.children(0).children(0).children(0)                        .asInstanceOf[fplan.Create].attribute)
    println(stages.fplan.children(0).children(0).children(0).children(0)            .asInstanceOf[fplan.Create].attribute)
    println(stages.fplan.children(0).children(0).children(0).children(0).children(0).asInstanceOf[fplan.Create].attribute)

    println("-------")
    println("------->>>>>>>>>")

    println(stages.fplan.children(0)                                                .asInstanceOf[fplan.Create].attribute)
    println(stages.fplan.children(0).children(0)                                    .asInstanceOf[fplan.Create].attribute)
    println(stages.fplan.children(0).children(0).children(0)                        .asInstanceOf[fplan.Create].attribute)
    println(stages.fplan.children(0).children(0).children(0).children(0)            .asInstanceOf[fplan.Create].attribute)
    println(stages.fplan.children(0).children(0).children(0).children(0).children(0).asInstanceOf[fplan.Create].attribute)

    println("-------")
    println("-------")

    println(stages.fplan.children(0)                                                .asInstanceOf[fplan.Create].internalSchema)
    println(stages.fplan.children(0).children(0)                                    .asInstanceOf[fplan.Create].internalSchema)
    println(stages.fplan.children(0).children(0).children(0)                        .asInstanceOf[fplan.Create].internalSchema)
    println(stages.fplan.children(0).children(0).children(0).children(0)            .asInstanceOf[fplan.Create].internalSchema)
    println(stages.fplan.children(0).children(0).children(0).children(0).children(0).asInstanceOf[fplan.Create].internalSchema)

    println("-------")
    println("-------")
  }

  test("should compile CREATE with property map, string literals only") {
    val cypher = CypherParser.parseString("CREATE (n)-[:KNOWS {since: 'Yesterday'} ]->(:Person {name: 'A'} )")
    val plan = CypherToQPlan.build(cypher)
    println(plan)
  }

  test("should compile CREATE with property map, number literals only") {
    val cypher = CypherParser.parseString("CREATE (n)-[:KNOWS {strength: 7} ]->(:Person {age: 25, weight: 70} )")
    val plan = CypherToQPlan.build(cypher)
    println(plan)
  }

  test("should compile CREATE with property map with a mix of literals and expressions") {
    val cypher = CypherParser.parseString("CREATE (n)-[:KNOWS {since: 'Yesterday'} ]->(:Person {name: 'A', age: 3+5} )")
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

  ignore("should compile REMOVE that targets labels") {
    val cypher = CypherParser.parseString("MATCH (n) REMOVE n:Actor:Director")
    val plan = CypherToQPlan.build(cypher)
    println(plan)
  }

  ignore("should compile SET that targets labels") {
    val cypher = CypherParser.parseString("MATCH (n) SET n:Actor:Director")
    val plan = CypherToQPlan.build(cypher)
    println(plan)
  }

}
