package sre.task

import ingraph.ire.{Indexer, IngraphEdge, IngraphVertex}
import org.scalatest.FunSpec
import sre.task.Expressions._
import sre.task.Iterators.{EdgeIterator, VertexIterator}

class TaskSpec extends FunSpec {

  describe("VertexIterator") {
    describe("check") {
      it("should filter vertices based on property") {
        val indexer = new Indexer()

        indexer.addVertex(IngraphVertex(0, Set.empty, Map("flavor" -> "chocolate")))
        indexer.addVertex(IngraphVertex(1, Set.empty, Map("flavor" -> "pistachio")))
        indexer.addVertex(IngraphVertex(2, Set.empty, Map("name" -> "Bertha")))

        val vertices = new VertexIterator(indexer.vertexLookup.valuesIterator)

        val actual = vertices.check(
          Or(
            Property("name", Not(Null)),
            Property("flavor", Val(Eq("pistachio")))
          )
        ).to[Set]

        assert(actual == Set(
          IngraphVertex(1, Set.empty, Map("flavor" -> "pistachio")),
          IngraphVertex(2, Set.empty, Map("name" -> "Bertha")))
        )
      }
    }

    describe("extend") {
      val indexer = new Indexer()

      val (a, b, c, d) = (
        IngraphVertex(0, Set.empty, Map("flavor" -> "chocolate")),
        IngraphVertex(1, Set.empty, Map("flavor" -> "pistachio")),
        IngraphVertex(2, Set.empty, Map("name" -> "Bertha")),
        IngraphVertex(3, Set.empty, Map("name" -> "Cat")))


      val (x, y, z, w) = (
        IngraphEdge(0, c, a, "LIKES", Map.empty),
        IngraphEdge(1, c, b, "LIKES", Map.empty),
        IngraphEdge(2, c, d, "KNOWS", Map.empty),
        IngraphEdge(3, d, c, "KNOWS", Map.empty)
      )

      indexer.addVertex(a)
      indexer.addVertex(b)
      indexer.addVertex(c)
      indexer.addVertex(d)

      indexer.addEdge(x)
      indexer.addEdge(y)
      indexer.addEdge(z)
      indexer.addEdge(w)

      describe("should navigate edges") {
        describe("pointing outward") {
          it("unfiltered") {
            val vertices = new VertexIterator(indexer.vertexLookup.valuesIterator)

            val actual = vertices.extend(Direction.Out, None).to[Set]

            assert(actual == Set(
              IngraphEdge(0, c, a, "LIKES", Map.empty),
              IngraphEdge(1, c, b, "LIKES", Map.empty),
              IngraphEdge(2, c, d, "KNOWS", Map.empty),
              IngraphEdge(3, d, c, "KNOWS", Map.empty)
            ))
          }
        }
        describe("pointing inward") {
          it("filtered by label") {
            val vertices = new VertexIterator(indexer.vertexLookup.valuesIterator)

            val actual = vertices.extend(Direction.In, Some("KNOWS")).to[Set]

            assert(actual == Set(
              IngraphEdge(2, c, d, "KNOWS", Map.empty),
              IngraphEdge(3, d, c, "KNOWS", Map.empty)
            ))
          }
        }
        describe("pointing in both directions") {
          it("filtered by label") {

          }
        }
      }
    }
  }

  describe("EdgeIterator") {
    describe("check") {
      it("should") {
        val indexer = new Indexer()

        val (a, b, c) = (
          IngraphVertex(0, Set.empty, Map("flavor" -> "chocolate")),
          IngraphVertex(1, Set.empty, Map("flavor" -> "pistachio")),
          IngraphVertex(2, Set.empty, Map("name" -> "Bertha")))
        indexer.addVertex(a)
        indexer.addVertex(b)
        indexer.addVertex(c)

        indexer.addEdge(IngraphEdge(0, c, a, "LIKES", Map("rating" -> new Integer(3))))
        indexer.addEdge(IngraphEdge(1, c, b, "LIKES", Map("rating" -> new Integer(5), "date" -> new Integer(10))))

        val edges = new EdgeIterator(indexer.edgeLookup.valuesIterator)

        val actual = edges.check(
          Or(
            Property("date", Or(Null, Val(Gt(10)))),
            Property("rating", Val(Gt(4)))
          )
        ).to[Set]

        assert(actual == Set(
          IngraphEdge(0, c, a, "LIKES", Map("rating" -> new Integer(3))),
          IngraphEdge(1, c, b, "LIKES", Map("rating" -> new Integer(5), "date" -> new Integer(10))))
        )
      }
    }
  }

}
