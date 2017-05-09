package ingraph.ire

import org.neo4j.driver.internal.{InternalNode, InternalRelationship}
import org.neo4j.driver.v1.Value
import org.scalatest.FunSuite

import scala.io.Source

class RandomTest extends FunSuite {
  test("Optional match returns Vector(null) on empty input") {
    val query = "OPTIONAL MATCH (n) RETURN n"
    val adapter = new IngraphAdapter(query, "opt")
    assert(adapter.engine.getResults() == List(Vector(null)))
  }

  test("Constant returns work") {
    val query = "RETURN 1 + 1"
    val adapter = new IngraphAdapter(query, "opt")
    assert(adapter.engine.getResults() == List(Vector(2 )))
  }

  test("Vertices can be removed from indexer") {
    import scala.collection.JavaConverters._
    val query = "MATCH (n:Person) RETURN n"
    val adapter = new IngraphAdapter(query, "remove")
    Indexer.addVertex(new InternalNode(1L, List("Person").asJava, Map[String, Value]().asJava))
    Indexer.addVertex(new InternalNode(2L, List("Person").asJava, Map[String, Value]().asJava))
    assert(adapter.result().toSet == Set(Vector(2), Vector(1)))
    Indexer.removeVertexById(1L)
    assert(adapter.result().toSet == Set(Vector(2)))
    Indexer.addVertex(new InternalNode(1L, List("Person").asJava, Map[String, Value]().asJava))
    assert(adapter.result().toSet == Set(Vector(2), Vector(1)))
  }

  test("Edges can be removed from indexer") {
    import scala.collection.JavaConverters._
    val query = "MATCH (n:Person) -[:Knows]-> (m:Person) RETURN m"
    val adapter = new IngraphAdapter(query, "remove")
    Indexer.addVertex(new InternalNode(1L, List("Person").asJava, Map[String, Value]().asJava))
    Indexer.addVertex(new InternalNode(2L, List("Person").asJava, Map[String, Value]().asJava))
    Indexer.addEdge(new InternalRelationship(3L, 1L, 2L, "Knows"))
    assert(adapter.result().toSet == Set(Vector(2)))
    Indexer.removeEdgeById(3L)
    assert(adapter.result().toSet == Set())
    Indexer.addEdge(new InternalRelationship(3L, 1L, 2L, "Knows"))
    assert(adapter.result().toSet == Set(Vector(2)))
  }
}
