package ingraph.ire

import org.neo4j.driver.internal.{InternalNode, InternalRelationship}
import org.neo4j.driver.internal.value.InternalValue
import org.neo4j.driver.v1.{Value, Values}
import org.scalatest.{FlatSpec, WordSpec}

import scala.collection.JavaConverters._
class IndexerTest extends WordSpec {
  val indexer = new Indexer(new EntityToTupleMapper(Map(), Map(), Map()))
  indexer.addVertex(new InternalNode(1, Seq("dog").asJava, Map("age" -> Values.value(5)).asJava))
  indexer.addVertex(new InternalNode(2, Seq("person").asJava, Map("age" -> Values.value(25)).asJava))
  indexer.addVertex(new InternalNode(3, Seq("cat").asJava, Map("age" -> Values.value(7)).asJava))
  indexer.addEdge(new InternalRelationship(4, 2, 1, "owns"))
  indexer.addEdge(new InternalRelationship(5, 3, 2, "owns"))
  indexer.addEdge(new InternalRelationship(6, 1, 3, "hates"))
  indexer.addEdge(new InternalRelationship(7, 3, 1, "hates"))
  indexer.addEdge(new InternalRelationship(8, 1, 1, "eats"))

  "Indexer" should {

    "return edges by label" in {
      assert(indexer.edgesByLabel("hates").map(_.id).toSet == Set(6, 7))
      assert(indexer.edgesByLabel("eats").map(_.id).toSet == Set(8))
    }

    "return vertices by id" in {
      assert(indexer.vertexById(3).id == 3)
    }

    "make create navigable entities" in {
      assert(indexer.vertexById(2).edges("owns").sourceVertex.id == 2)
      assert(indexer.vertexById(2).reverseEdges("owns").targetVertex.id == 2)
    }
  }
}
