package ingraph.ire

import org.scalatest.FunSuite

class BiEngineTest extends FunSuite {

  ignore("test 1") {
    val indexer = new Indexer()

    val readQuery =
      """MATCH (country:Country {name: 'Austria'})
        |MATCH (a:Person)-[:isLocatedIn]->(:City)-[:isPartOf]->(country)
        |MATCH (b:Person)-[:isLocatedIn]->(:City)-[:isPartOf]->(country)
        |MATCH (c:Person)-[:isLocatedIn]->(:City)-[:isPartOf]->(country)
        |MATCH (a)-[:knows]-(b), (b)-[:knows]-(c), (c)-[:knows]-(a)
        |WHERE a.id < b.id
        |  AND b.id < c.id
        |RETURN count(*)
      """.stripMargin

    val readAdapter = new IngraphIncrementalAdapter(readQuery, "read", indexer)
    val result = readAdapter.result()
  }

}
