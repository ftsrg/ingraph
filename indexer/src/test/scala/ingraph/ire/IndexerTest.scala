package ingraph.ire

import org.neo4j.driver.internal.{InternalNode, InternalRelationship}
import org.neo4j.driver.v1.Values
import org.scalatest.WordSpec

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
  indexer.addEdge(new InternalRelationship(9, 3, 2, "hates"))

  "IngraphEdge" should {
    "reverse itself" in {
      val edge: IngraphEdge = indexer.edgesByType("eats").toSeq.head
      val inverse = edge.inverse()
      assert(edge.sourceVertex == inverse.targetVertex)
      assert(inverse.sourceVertex == edge.targetVertex)
    }
  }

  "Indexer" should {
    "return edges by type" in {
      assert(indexer.edgesByType("hates").map(_.id).toSet == Set(6, 7, 9))
      assert(indexer.edgesByType("eats").map(_.id).toSet == Set(8))
    }

    "return vertices by id" in {
      val cat = indexer.verticesById(3).get
      assert(cat.id == 3)
      assert(cat.edgesOut("hates").map(_.targetVertex.id).toSet == Set(1, 2))
    }

    "make create navigable entities" in {
      val maybeVertex = indexer.verticesById(2)
      assert(maybeVertex.map(_.edgesOut("owns").head.sourceVertex.id).contains(2))
      assert(maybeVertex.map(_.edgesIn("owns").head.targetVertex.id).contains(2))
    }

//    "can query all vertices" in {
//      assert(indexer.verticesJava().asScala.map(_.id).toSet == Set(1, 2, 3))
//    }

  }
}
