package ingraph.ire

import org.neo4j.driver.v1.Value
import org.scalatest.FunSuite

class EdgeDirectionHandlingTest extends FunSuite {

  def initializeIndexer(): Indexer = {
    val indexer = new Indexer()

    val v1 = IngraphVertex(1L, Set("Train", "Misleading"), Map[String, Value]())
    val v2 = IngraphVertex(2L, Set("Segment", "Fakery"), Map[String, Value]())
    val v3 = IngraphVertex(3L, Set("Segment"), Map[String, Value]())

    indexer.addVertex(v1)
    indexer.addVertex(v2)
    indexer.addVertex(v3)

    indexer.addEdge(IngraphEdge(4L, v1, v2, "ON"))
    indexer.addEdge(IngraphEdge(5L, v2, v3, "NEXT"))
    indexer.addEdge(IngraphEdge(6L, v3, v2, "NEXT"))

    indexer
  }

  test("handle ->") {
    val indexer = initializeIndexer()
    val adapter = new IncrementalQueryAdapter(
      "MATCH (t:Train)-[r:ON]->(seg1:Segment) RETURN t, seg1", "->", indexer)
    assert(adapter.results() == List(Vector(1, 2)))
  }

  test("handle <-") {
    val indexer = initializeIndexer()
    val adapter = new IncrementalQueryAdapter(
      "MATCH (seg1: Segment)<-[r:ON]-(t:Train) RETURN t, seg1", "->", indexer)
    assert(adapter.results() == List(Vector(1, 2)))
  }

  test("handle undirected") {
    val indexer = initializeIndexer()
    val adapter = new IncrementalQueryAdapter(
      "MATCH (seg1: Segment)-[r:NEXT]-(seg2:Segment) RETURN seg1, seg2", "->", indexer)
    assert(adapter.results().size == 4) // TODO: test for ids once sorting is done
  }
}
