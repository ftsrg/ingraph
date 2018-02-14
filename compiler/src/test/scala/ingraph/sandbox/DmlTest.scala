package ingraph.sandbox

import ingraph.compiler.CypherToQPlan
import ingraph.compiler.cypher2qplan.CypherParser
import ingraph.model.fplan

class DmlTest extends CompilerTest {

  test("should compile simple DELETE for a vertex") {
    compile("MATCH (n:Person) DELETE n")
  }

  test("should compile simple DELETE for an edge") {
    compile("MATCH (p1:Person)-[r:KNOWS]-(p2:Person) DELETE r")
  }

  test("should compile simple DELETE for an entire pattern") {
    compile("MATCH (p1:Person)-[r:KNOWS]-(p2:Person) DELETE p1, r, p2")
  }

  test("should compile vanilla CREATE") {
    compile("CREATE (p:Person)")
  }

  test("should compile vanilla CREATE with attributes") {
    compile("CREATE (p:Person {name: 'Batman'})")
  }

  test("should compile vanilla CREATE with attributes 2") {
    compile("CREATE (t:Train {a: 1})")
  }

  test("should compile CREATE with a single edges") {
    compile("CREATE (n:Person)-[:LIVES]->(c:City)")
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
    compile("CREATE (n)-[:KNOWS {since: 'Yesterday'} ]->(:Person {name: 'A'} )")
  }

  test("should compile CREATE with property map, number literals only") {
    compile("CREATE (n)-[:KNOWS {strength: 7} ]->(:Person {age: 25, weight: 70} )")
  }

  test("should compile CREATE with property map with a mix of literals and expressions") {
    compile("CREATE (n)-[:KNOWS {since: 'Yesterday'} ]->(:Person {name: 'A', age: 3+5} )")
  }

  test("should compile CREATE after MATCH") {
    compile("MATCH (n) CREATE (n)-[:KNOWS]->(:Person)")
  }

  test("should compile CREATE after MATCH on two nodes") {
    compile("MATCH (n:Person), (c:City) CREATE (n)-[:KNOWS]->(c)")
  }

  ignore("should compile REMOVE that targets labels") {
    compile("MATCH (n) REMOVE n:Actor:Director")
  }

  ignore("should compile SET that targets labels") {
    compile("MATCH (n) SET n:Actor:Director")
  }

}
