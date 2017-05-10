package ingraph.ire

import org.neo4j.driver.internal.{InternalNode, InternalRelationship}
import org.neo4j.driver.v1.Value
import org.scalatest.FunSuite

import scala.io.Source
import scala.collection.JavaConverters._

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
    val indexer = new Indexer()
    val query = "MATCH (n:Person) RETURN n"
    val adapter = new IngraphAdapter(query, "remove", indexer)

    indexer.addVertex(new InternalNode(1L, List("Person").asJava, Map[String, Value]().asJava))
    indexer.addVertex(new InternalNode(2L, List("Person").asJava, Map[String, Value]().asJava))
    assert(adapter.result().toSet == Set(Vector(2), Vector(1)))
    indexer.removeVertexById(1L)
    assert(adapter.result().toSet == Set(Vector(2)))
    indexer.addVertex(new InternalNode(1L, List("Person").asJava, Map[String, Value]().asJava))
    assert(adapter.result().toSet == Set(Vector(2), Vector(1)))
  }

  test("Edges can be removed from indexer") {

    val indexer = new Indexer()
    val query = "MATCH (n:Person) -[:Knows]-> (m:Person) RETURN m"
    val adapter = new IngraphAdapter(query, "remove", indexer)
    indexer.addVertex(new InternalNode(1L, List("Person").asJava, Map[String, Value]().asJava))
    indexer.addVertex(new InternalNode(2L, List("Person").asJava, Map[String, Value]().asJava))
    indexer.addEdge(new InternalRelationship(3L, 1L, 2L, "Knows"))
    assert(adapter.result().toSet == Set(Vector(2)))
    indexer.removeEdgeById(3L)
    assert(adapter.result().toSet == Set())
    indexer.addEdge(new InternalRelationship(3L, 1L, 2L, "Knows"))
    assert(adapter.result().toSet == Set(Vector(2)))
  }

  test("One off modifier queries work") {
    val indexer = new Indexer()
    val oneOff = """MATCH (t:Train)-[r:ON]->(seg1:Segment)-[:NEXT]->(seg2:Segment)
                  |DELETE r
                  |CREATE (t)-[:ON]->(seg2)""".stripMargin
    val whereIsTrain = "MATCH (t:Train)-[r:ON]->(s:Segment) RETURN s"
    indexer.addVertex(new InternalNode(1L, List("Train").asJava, Map[String, Value]().asJava))
    indexer.addVertex(new InternalNode(2L, List("Segment").asJava, Map[String, Value]().asJava))
    indexer.addVertex(new InternalNode(3L, List("Segment").asJava, Map[String, Value]().asJava))
    indexer.addEdge(new InternalRelationship(4L, 1L, 2L, "ON"))
    indexer.addEdge(new InternalRelationship(5L, 2L, 3L, "NEXT"))
    val whereIsAdapter = new IngraphAdapter(whereIsTrain, "something", indexer)
    assert(whereIsAdapter.result() == List(Vector(2)))
    new IngraphOneOffQuery(oneOff, "remove", indexer).terminate()
    assert(whereIsAdapter.result() == List(Vector(3)))
  }
}
