package ingraph.ire

import ingraph.bulkloader.csv.data.{CsvEdge, CsvVertex}
import org.neo4j.driver.internal.{InternalNode, InternalRelationship}
import org.neo4j.driver.v1.Value
import org.scalatest.FunSuite

import scala.collection.JavaConverters._

class RandomTest extends FunSuite {

  test("Support 'node has labels' function") {
    val query =
      """MATCH (te:TrackElement)
        |RETURN te:Segment""".stripMargin
    val adapter = new IngraphIncrementalAdapter(query)
    assert(adapter.engine.getResults() == List())
  }

  test("Optional match returns Vector(null) on empty input") {
    val query = "OPTIONAL MATCH (n) RETURN n"
    val adapter = new IngraphIncrementalAdapter(query)
    assert(adapter.engine.getResults() == List(Vector(null)))
  }

  test("Constant returns work") {
    val query = "RETURN 1 + 1"
    val adapter = new IngraphIncrementalAdapter(query)
    assert(adapter.engine.getResults() == List(Vector(2 )))
  }

  test("Vertices can be removed from indexer") {
    val indexer = new Indexer()
    val query = "MATCH (n:Person) RETURN n"
    val adapter = new IngraphIncrementalAdapter(query, "remove", indexer)

    indexer.addVertex(new CsvVertex(1L), List("Person"))
    indexer.addVertex(new CsvVertex(2L), List("Person"))
    assert(adapter.result().toSet == Set(Vector(2), Vector(1)))
    indexer.removeVertexById(1L)
    assert(adapter.result().toSet == Set(Vector(2)))
    indexer.addVertex(new CsvVertex(1L), List("Person"))
    assert(adapter.result().toSet == Set(Vector(2), Vector(1)))
  }

  test("Edges can be removed from indexer") {
    val indexer = new Indexer()
    val query = "MATCH (n:Person)-[:KNOWS]->(m:Person) RETURN m"
    val adapter = new IngraphIncrementalAdapter(query, "remove", indexer)
    indexer.addVertex(new CsvVertex(1L), List("Person"))
    indexer.addVertex(new CsvVertex(2L), List("Person"))
    indexer.addEdge(new CsvEdge(1L, 3L, 2L), "Person", "KNOWS", "Person")
    assert(adapter.result().toSet == Set(Vector(2)))
    indexer.removeEdgeById(3L)
    assert(adapter.result().toSet == Set())
    indexer.addEdge(new CsvEdge(1L, 3L, 2L), "Person", "KNOWS", "Person")
    assert(adapter.result().toSet == Set(Vector(2)))
  }

  test("Boolean literals work") {
    val indexer = new Indexer()
    indexer.addVertex(IngraphVertex(5L, Set("P")))
    val query = "MATCH (n:P) WHERE true RETURN n"
    val adapter = new IngraphIncrementalAdapter(query, "", indexer)
    assert(adapter.result().toSet == Set(Vector(5)))
  }

  test("Unwind") {
    val query = "UNWIND [1, 2, 'ot'] as x RETURN x"
    val adapter = new IngraphIncrementalAdapter(query, "")
    adapter.indexer.addVertex(IngraphVertex(5L, Set("ot")))
    assert(adapter.result() == List(Vector(1), Vector(2), Vector("ot")))
  }

}
