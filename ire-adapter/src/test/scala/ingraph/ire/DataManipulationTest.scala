package ingraph.ire

import org.neo4j.driver.v1.Value
import org.scalatest.FunSuite

class DataManipulationTest extends FunSuite {

  def initializeIndexer(): Indexer = {
    val indexer = new Indexer()

    val v1 = IngraphVertex(1L, Set("Train"), Map[String, Value]())
    val v2 = IngraphVertex(2L, Set("Segment"), Map[String, Value]())
    val v3 = IngraphVertex(3L, Set("Segment"), Map[String, Value]())

    indexer.addVertex(v1)
    indexer.addVertex(v2)
    indexer.addVertex(v3)

    indexer.addEdge(IngraphEdge(4L, v1, v2, "ON"))
    indexer.addEdge(IngraphEdge(5L, v2, v3, "NEXT"))
    indexer.addEdge(IngraphEdge(6L, v3, v2, "NEXT"))

    indexer
  }

  test("delete and create edge work") {
    val indexer = initializeIndexer()

    val oneOff = """MATCH (t:Train)-[r:ON]->(seg1:Segment)-[:NEXT]->(seg2:Segment)
                  |DELETE r
                  |CREATE (t)-[:ON]->(seg2)""".stripMargin

    val whereIsTrain = "MATCH (t:Train)-[r:ON]->(s:Segment) RETURN s"
    val whereIsAdapter = new IngraphIncrementalAdapter(whereIsTrain, "something", indexer)

    for (i <- 1 to 10 ) {
      assert(whereIsAdapter.result() == List(Vector(2)))
      new IngraphSearchAdapter(oneOff, "remove", indexer).terminate()
      assert(whereIsAdapter.result() == List(Vector(3)))
      new IngraphSearchAdapter(oneOff, "remove", indexer).terminate()
    }
  }

  test("delete vertex works") {
    val indexer = initializeIndexer()

    val oneOff = "MATCH (t:Train) DELETE t"
    new IngraphSearchAdapter(oneOff, "remove", indexer).terminate()

    val whereIsTrain = "MATCH (t:Train) RETURN t"
    val whereIsAdapter = new IngraphIncrementalAdapter(whereIsTrain, "something", indexer)

    assert(whereIsAdapter.result() == List())
  }

  test("create vertex works") {
    val indexer = new Indexer()

    val oneOff = "CREATE (t:Train)"
    new IngraphSearchAdapter(oneOff, "create", indexer).terminate()
    val whereIsTrain = "MATCH (t:Train) RETURN t"
    val whereIsAdapter = new IngraphIncrementalAdapter(whereIsTrain, "", indexer)

    assert(whereIsAdapter.result().size == 1)
  }

  ignore("create a single edges works") {
    val indexer = new Indexer()

    val oneOff = "CREATE (t:Train)-[r:ON]->(seg1:Segment)"
    new IngraphSearchAdapter(oneOff, "create", indexer).terminate()
    val whereIsTrain = "MATCH (t:Train) RETURN t"
    val whereIsAdapter = new IngraphIncrementalAdapter(whereIsTrain, "", indexer)

    assert(whereIsAdapter.result().size == 1)
  }

  ignore("create two edges works") {
    val indexer = new Indexer()

    val oneOff = "CREATE (t:Train)-[r:ON]->(seg1:Segment)-[:NEXT]->(seg2:Segment)"
    new IngraphSearchAdapter(oneOff, "create", indexer).terminate()
    val whereIsTrain = "MATCH (t:Train) RETURN t"
    val whereIsAdapter = new IngraphIncrementalAdapter(whereIsTrain, "", indexer)

    assert(whereIsAdapter.result().size == 1)
  }

}
