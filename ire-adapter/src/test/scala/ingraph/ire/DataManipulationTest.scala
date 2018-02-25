package ingraph.ire

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

  test("create unlabeled vertices") {
    val indexer = new Indexer()
    val q = "CREATE ({value: 1})"
    new IngraphOneTimeAdapter(q, "", indexer).terminate()
    println(new IngraphIncrementalAdapter("MATCH (n) return n","", indexer).result())

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
      new IngraphOneTimeAdapter(oneOff, "remove", indexer).terminate()
      assert(whereIsAdapter.result() == List(Vector(3)))
      new IngraphOneTimeAdapter(oneOff, "remove", indexer).terminate()
    }
  }

  test("delete vertex works") {
    val indexer = initializeIndexer()

    val oneOff = "MATCH (t:Train) DETACH DELETE t"
    new IngraphOneTimeAdapter(oneOff, "remove", indexer).terminate()

    val whereIsTrain = "MATCH (t:Train) RETURN t"
    val whereIsAdapter = new IngraphIncrementalAdapter(whereIsTrain, "something", indexer)

    assert(whereIsAdapter.result() == List())
  }

  test("create constant vertex works") {
    val indexer = initializeIndexer()

    val oneOff = "CREATE (e: Engine {power: 'poweeeer'})"// CREATE (e:Engine {power: t.power + '^2'})"
    new IngraphOneTimeAdapter(oneOff, "create", indexer).terminate()
    val whereIsTrain = "MATCH (e: Engine) RETURN e, e.power"// WHERE e.power != 1 RETURN e, e.power"
    val whereIsAdapter = new IngraphIncrementalAdapter(whereIsTrain, "", indexer)

    assert(whereIsAdapter.result() == Seq(Vector(-4964420948893066024L, "poweeeer")))
  }

  ignore("create matched vertex works") {
    val indexer = initializeIndexer()

    val oneOff = "MATCH (t: Train) CREATE (e: Engine {power: t.power})"
    new IngraphOneTimeAdapter(oneOff, "create", indexer).terminate()
    val whereIsTrain = "MATCH (e: Engine) RETURN e, e.power"// WHERE e.power != 1 RETURN e, e.power"
    val whereIsAdapter = new IngraphIncrementalAdapter(whereIsTrain, "", indexer)

    assert(whereIsAdapter.result() == Seq(Vector(-4964420948893066024L, "poweeeer")))
  }


  test("create constant single edges works") {
    val indexer = new Indexer()

    val oneOff = "CREATE (t:Train)-[r:ON {track: 'bomb'}]->(seg1:Segment)"
    new IngraphOneTimeAdapter(oneOff, "create", indexer).terminate()
    val whereIsTrain = "MATCH (t:Train)-[r:ON]->(seg1:Segment) RETURN r, r.track"
    val whereIsAdapter = new IngraphIncrementalAdapter(whereIsTrain, "", indexer)
    assert(whereIsAdapter.result() == Seq(Vector(3831662765844904176L, "bomb")))
  }

  test("create matched single edges") {
    val indexer = initializeIndexer()

    val create = "MATCH (seg:Segment) CREATE (seg)-[m:MonitoredBy]->(s:Sensor)"
    new IngraphOneTimeAdapter(create, "create", indexer).terminate()
    val query = "MATCH (seg:Segment)-[m:MonitoredBy]->(s:Sensor) RETURN seg, m, s"
    val whereIsAdapter = new IngraphIncrementalAdapter(query, "", indexer)
    assert(whereIsAdapter.result() == Seq(
      Vector(3, 3831662765844904176L, -4964420948893066024L),
      Vector(2, 6137546356583794141L, 7564655870752979346L)))
  }

  test("create two edges works") {
    val indexer = new Indexer()

    val oneOff = "CREATE (t:Train)-[r:ON]->(seg1:Segment)-[:NEXT]->(seg2:Segment)"
    new IngraphOneTimeAdapter(oneOff, "create", indexer).terminate()
    val whereIsTrain = "MATCH (t:Train)-[r:ON]->(seg1:Segment)-[:NEXT]->(seg2:Segment) RETURN t, seg1, seg2"
    val whereIsAdapter = new IngraphIncrementalAdapter(whereIsTrain, "", indexer)

    assert(whereIsAdapter.result() == Seq(Vector(-4964420948893066024L, 7564655870752979346L, 6137546356583794141L)))
  }

  test("delete vertex by id works") {
    val indexer = initializeIndexer()
    val oneOff = "MATCH (t:Train {id: 1}) DETACH DELETE t"
    assert(indexer.vertexById(1L).nonEmpty)
    new IngraphOneTimeAdapter(oneOff, "del", indexer).terminate()
    assert(indexer.vertexById(1L).isEmpty)
  }


  test("delete edge by id works") {
    val indexer = initializeIndexer()
    val oneOff = "match (:Train)-[r:ON {id: 4}]->(:Segment) DELETE r"
    assert(indexer.edgeById(4L).nonEmpty)
    new IngraphOneTimeAdapter(oneOff, "del", indexer).terminate()
    assert(indexer.edgeById(4L).isEmpty)
  }
}
