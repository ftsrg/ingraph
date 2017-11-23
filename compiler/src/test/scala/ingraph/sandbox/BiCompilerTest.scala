package ingraph.sandbox

class BiCompilerTest extends CompilerTest {

  test("17") {
    val stages = compile(
      """MATCH (country:Country {name: 'Austria'})
        |MATCH (a:Person)-[:isLocatedIn]->(:City)-[:isPartOf]->(country)
        |MATCH (b:Person)-[:isLocatedIn]->(:City)-[:isPartOf]->(country)
        |MATCH (c:Person)-[:isLocatedIn]->(:City)-[:isPartOf]->(country)
        |MATCH (a)-[:knows]-(b), (b)-[:knows]-(c), (c)-[:knows]-(a)
        |WHERE a.id < b.id
        |  AND b.id < c.id
        |RETURN count(*)
      """.stripMargin)
  }

}
