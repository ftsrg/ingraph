package ingraph.ire

import org.neo4j.driver.v1.Value
import org.scalatest.FunSuite

class DataManipulationTest extends FunSuite {

  def initializeIndexer(): Indexer = {
    val indexer = new Indexer()

    val v1 = IngraphVertex(1L, Set("Train"), Map[String, Any]("power" -> "poweeeer"))
    val v2 = IngraphVertex(2L, Set("Segment"), Map[String, Any]())
    val v3 = IngraphVertex(3L, Set("Segment"), Map[String, Any]())

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

  test("create constant vertex works") {
    val indexer = initializeIndexer()

    val oneOff = "CREATE (e: Engine {power: 'poweeeer'})"// CREATE (e:Engine {power: t.power + '^2'})"
    new IngraphSearchAdapter(oneOff, "create", indexer).terminate()
    val whereIsTrain = "MATCH (e: Engine) RETURN e, e.power"// WHERE e.power != 1 RETURN e, e.power"
    val whereIsAdapter = new IngraphIncrementalAdapter(whereIsTrain, "", indexer)

    assert(whereIsAdapter.result() == Seq(Vector(-4964420948893066024L, "poweeeer")))
  }

  test("create matched vertex works") {
    val indexer = initializeIndexer()

    val oneOff = "MATCH (t: Train) WHERE t.power = t.power CREATE (e: Engine {power: t.power})"
    new IngraphSearchAdapter(oneOff, "create", indexer).terminate()
    val whereIsTrain = "MATCH (e: Engine) RETURN e, e.power"// WHERE e.power != 1 RETURN e, e.power"
    val whereIsAdapter = new IngraphIncrementalAdapter(whereIsTrain, "", indexer)

    assert(whereIsAdapter.result() == Seq(Vector(-4964420948893066024L, "poweeeer")))
  }



  test("create constant single edges works") {
    val indexer = new Indexer()

    val oneOff = "CREATE (t:Train)-[r:ON]->(seg1:Segment)"
    new IngraphSearchAdapter(oneOff, "create", indexer).terminate()
    val whereIsTrain = "MATCH (t:Train)-[r:ON]->(seg1:Segment) RETURN t, seg1"
    val whereIsAdapter = new IngraphIncrementalAdapter(whereIsTrain, "", indexer)
    assert(whereIsAdapter.result() == Seq(Vector(6137546356583794141L, -594798593157429144L)))
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
