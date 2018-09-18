package ingraph.ire

import ingraph.bulkloader.csv.data.{CsvEdge, CsvVertex}
import org.scalatest.WordSpec

import scala.collection.JavaConverters._
class IndexerTest extends WordSpec {
  val indexer = new Indexer()
  indexer.addVertex(new CsvVertex(1L, Map("age" -> 5).asJava),  Set("dog"))
  indexer.addVertex(new CsvVertex(2L, Map("age" -> 25).asJava), Set("person"))
  indexer.addVertex(new CsvVertex(3L, Map("age" -> 7).asJava),  Set("cat"))
  indexer.addEdge(new CsvEdge(2L, 4L, 1L), "person", "owns",  "dog"   )
  indexer.addEdge(new CsvEdge(3L, 5L, 2L), "cat",    "owns",  "person")
  indexer.addEdge(new CsvEdge(1L, 6L, 3L), "dog",    "hates", "cat"   )
  indexer.addEdge(new CsvEdge(3L, 7L, 1L), "cat",    "hates", "dog"   )
  indexer.addEdge(new CsvEdge(1L, 8L, 1L), "dog",    "eats",  "dog"   )
  indexer.addEdge(new CsvEdge(3L, 9L, 2L), "cat",    "hates", "person")

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
      val cat = indexer.vertexById(3).get
      assert(cat.id == 3)
      assert(cat.edgesOut("hates").map(_.targetVertex.id).toSet == Set(1, 2))
    }

    "make create navigable entities" in {
      val maybeVertex = indexer.vertexById(2)
      assert(maybeVertex.map(_.edgesOut("owns").head.sourceVertex.id).contains(2))
      assert(maybeVertex.map(_.edgesIn("owns").head.targetVertex.id).contains(2))
    }

    "can query all vertices" in {
      assert(indexer.vertices().map(_.id).toSet == Set(1, 2, 3))
    }

  }
}
