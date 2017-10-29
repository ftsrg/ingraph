package ingraph.nre

import ingraph.ire.Indexer
import org.neo4j.driver.internal.{InternalNode, InternalRelationship}
import org.neo4j.driver.v1.Value
import org.scalatest.FunSuite

import scala.collection.JavaConverters._

class RandomTest extends FunSuite {
  test("Optional match returns Vector(null) on empty input") {
    val query = "OPTIONAL MATCH (n) RETURN n"
    val adapter = new IngraphNaiveAdapter(query, "opt")
    assert(1 == 0)
  }

  ignore("Constant returns work") {
    val query = "RETURN 1 + 1"
    val adapter = new IngraphNaiveAdapter(query, "opt")
    assert(adapter.engine.getResults() == List(Vector(2)))
  }

  ignore("Vertices can be removed from indexer") {
    val indexer = new Indexer()
    val query = "MATCH (n:Person) RETURN n"
    val adapter = new IngraphNaiveAdapter(query, "remove", indexer)

    indexer.addVertex(new InternalNode(1L, List("Person").asJava, Map[String, Value]().asJava))
    indexer.addVertex(new InternalNode(2L, List("Person").asJava, Map[String, Value]().asJava))
    assert(adapter.result().toSet == Set(Vector(2), Vector(1)))
    indexer.removeVertexById(1L)
    assert(adapter.result().toSet == Set(Vector(2)))
    indexer.addVertex(new InternalNode(1L, List("Person").asJava, Map[String, Value]().asJava))
    assert(adapter.result().toSet == Set(Vector(2), Vector(1)))
  }

  ignore("Edges can be removed from indexer") {
    val indexer = new Indexer()
    val query = "MATCH (n:Person) -[:Knows]-> (m:Person) RETURN m"
    val adapter = new IngraphNaiveAdapter(query, "remove", indexer)
    indexer.addVertex(new InternalNode(1L, List("Person").asJava, Map[String, Value]().asJava))
    indexer.addVertex(new InternalNode(2L, List("Person").asJava, Map[String, Value]().asJava))
    indexer.addEdge(new InternalRelationship(3L, 1L, 2L, "Knows"))
    assert(adapter.result().toSet == Set(Vector(2)))
    indexer.removeEdgeById(3L)
    assert(adapter.result().toSet == Set())
    indexer.addEdge(new InternalRelationship(3L, 1L, 2L, "Knows"))
    assert(adapter.result().toSet == Set(Vector(2)))
  }

}
