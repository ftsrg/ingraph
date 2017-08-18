package ingraph.ire

import org.neo4j.driver.internal.{InternalNode, InternalRelationship}
import org.neo4j.driver.internal.value.InternalValue
import org.neo4j.driver.v1.{Value, Values}
import org.scalatest.{FlatSpec, WordSpec}

import scala.collection.JavaConverters._
class IndexerTest extends WordSpec {
  val indexer = new Indexer()
  indexer.addVertex(new InternalNode(1, Seq("dog").asJava, Map("age" -> Values.value(5)).asJava))
  indexer.addVertex(new InternalNode(2, Seq("person").asJava, Map("age" -> Values.value(25)).asJava))
  indexer.addVertex(new InternalNode(3, Seq("cat").asJava, Map("age" -> Values.value(7)).asJava))
  indexer.addEdge(new InternalRelationship(4, 2, 1, "owns"))
  indexer.addEdge(new InternalRelationship(5, 3, 2, "owns"))
  indexer.addEdge(new InternalRelationship(6, 1, 3, "hates"))
  indexer.addEdge(new InternalRelationship(7, 3, 1, "hates"))
  indexer.addEdge(new InternalRelationship(8, 1, 1, "eats"))

  "IngraphEdge" should {
    "reverse itself" in {
      val edge: IngraphEdge = indexer.edgesByLabel("eats").toSeq.head
      val inverse = edge.inverse()
      assert(edge.sourceVertex == inverse.targetVertex)
      assert(inverse.sourceVertex == edge.targetVertex)
    }
  }

  "Indexer" should {

    "return edges by label" in {
      assert(indexer.edgesByLabel("hates").map(_.id).toSet == Set(6, 7))
      assert(indexer.edgesByLabel("eats").map(_.id).toSet == Set(8))
    }

    "return vertices by id" in {
      assert(indexer.vertexById(3).map(_.id) == Some(3))
    }

    "make create navigable entities" in {
      assert(indexer.vertexById(2).map(_.edges("owns").sourceVertex.id) == Some(2))
      assert(indexer.vertexById(2).map(_.reverseEdges("owns").targetVertex.id) == Some(2))
    }

    "can query all vertices" in {
      assert(indexer.verticesJava().asScala.map(_.id).toSet == Set(1, 2, 3))
    }
  }
}
