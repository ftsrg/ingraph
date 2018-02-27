package ingraph.ire

import ingraph.bulkloader.csv.data.{CsvEdge, CsvVertex}
import org.scalatest.WordSpec

import scala.collection.JavaConverters._
class IndexerTest extends WordSpec {
  val indexer = new Indexer()
  indexer.addVertex(new CsvVertex(1L, Seq("dog").asJava,    Map("age" -> 5).asJava))
  indexer.addVertex(new CsvVertex(2L, Seq("person").asJava, Map("age" -> 25).asJava))
  indexer.addVertex(new CsvVertex(3L, Seq("cat").asJava,    Map("age" -> 7).asJava))
  indexer.addEdge(new CsvEdge(4L, "owns",  2L, "person", 1L, "dog"))
  indexer.addEdge(new CsvEdge(5L, "owns",  3L, "cat",    2L, "person"))
  indexer.addEdge(new CsvEdge(6L, "hates", 1L, "dog",    3L, "cat"))
  indexer.addEdge(new CsvEdge(7L, "hates", 3L, "cat",    1L, "dog"))
  indexer.addEdge(new CsvEdge(8L, "eats",  1L, "dog",    1L, "dog"))
  indexer.addEdge(new CsvEdge(9L, "hates", 3L, "cat",    2L, "person"))

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

    "can query all vertices" in {
      assert(indexer.vertices().map(_.id).toSet == Set(1, 2, 3))
    }

  }
}
