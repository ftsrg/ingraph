package sre.task

import ingraph.ire.{Indexer, IngraphEdge, IngraphVertex}
import org.scalatest.FunSpec
import sre.task.Core._
import sre.task.Expressions.This.Property
import sre.task.Expressions._
import sre.task.Iterators._

class IteratorSpec extends FunSpec {

  // FIXME: I think it is a bug in simulacrum, but chaining does not work
  val v = VertexIterator[Iterator]
  val e = VertexIterator[Iterator]
  val ne = NavigatingEdgeIterator[Iterator]

  implicit object Helpers {
    def createIteratorForVertices(vertices: Iterator[IngraphVertex]) =
      vertices.map((v) => Context(v, Map()))

    def createIteratorForEdges(edges: Iterator[IngraphEdge]) =
      edges.map((e) => Context(e, Map()))
  }

  import Helpers._

  describe("Iterators") {
    val indexer = new Indexer()

    val (a, b, c, d) = (
      IngraphVertex(0, Set.empty, Map("flavor" -> "chocolate")),
      IngraphVertex(1, Set.empty, Map("flavor" -> "pistachio")),
      IngraphVertex(2, Set.empty, Map("name" -> "Bertha")),
      IngraphVertex(3, Set.empty, Map("name" -> "Cat")))


    val (x, y, z, w) = (
      IngraphEdge(0, c, a, "LIKES", Map("rating" -> new Integer(3))),
      IngraphEdge(1, c, b, "LIKES", Map("rating" -> new Integer(5), "date" -> new Integer(10))),
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

    val vertices = createIteratorForVertices(indexer.vertexLookup.valuesIterator)

    it("should work for `name is not null or x.flavor = pistachio or null = null`") {
      val exp = Or(
        IsNotNull(This.Property("name")),
        Or(
          Eq(This.Property("flavor"), Const("pistachio")),
          Eq(Null, Null)
        )
      )
      val actual = v.check(vertices)(exp).map(_.head).to[Set]

      assert(Set(
        IngraphVertex(1, Set.empty, Map("flavor" -> "pistachio")),
        IngraphVertex(2, Set.empty, Map("name" -> "Bertha")),
        IngraphVertex(3, Set.empty, Map("name" -> "Cat"))
      ) == actual)
    }
    it("should filter for name == Cat and navigate out on KNOWS edges") {
      val vertices = createIteratorForVertices(indexer.vertexLookup.valuesIterator)

      val _1 = v.check(vertices)(Eq(This.Property("name"), Const("Cat")))
      val _2 = v.ext(_1)(Some(Out), Some("KNOWS"))
      val _3 = ne.end(_2)

      val actual = _3.map(_.head).to[Set]

      assert(Set(
        IngraphVertex(2, Set.empty, Map("name" -> "Bertha"))
      ) == actual)
    }
    it("should filter for a: name == Cat and navigate out on KNOWS edges twice") {
      val vertices = createIteratorForVertices(indexer.vertexLookup.valuesIterator)
      val _1 = v.check(vertices)(Eq(This.Property("name"), Const("Cat")))
      val _2 = v.ext(_1)(Some(Out), Some("KNOWS"))
      val _3 = ne.record(_2, "e0", This.apply)
      val _4 = ne.end(_3)
      val _5 = v.ext(_4)(Some(Out), Some("KNOWS"))
      val _6 = ne.check(_5)(Not(Eq(This.apply, Var("e0"))))
      val _7 = ne.end(_6)

      val actual = _7.map(_.head).to[Set]

      assert(Set(
        IngraphVertex(3, Set.empty, Map("name" -> "Cat"))
      ) == actual)
    }

//    it("should work for edges") {
//
//      val edges = createIteratorForEdges(indexer.edgeLookup.valuesIterator)
//
//      val actual = e.check(edges)(
//        Or(
//          Or(IsNull(This.Property("date")), Gt(This.Property("date"), Const(10)))
//          Gt(This.Property("rating"), Const(4))
//        ))
//      ).to[Set]
//
//      assert(actual == Set(
//        IngraphEdge(0, c, a, "LIKES", Map("rating" -> new Integer(3))),
//        IngraphEdge(1, c, b, "LIKES", Map("rating" -> new Integer(5), "date" -> new Integer(10))))
//      )
//    }
  }
}
