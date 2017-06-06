package sre.task

import sre.task.Core._
import sre.task.Expressions.BExpr

object Direction {
  sealed trait Value
  final case object Out extends Value
  final case object In extends Value
  final case object Both extends Value

  val values: Set[Value] = Set(Out, In, Both)
}
object Iterators {

  class VertexIterator(
                        private val iterator: Iterator[VertexT]
                      ) extends Iterator[VertexT] {

    def check(assert: BExpr[VertexT]): Iterator[VertexT] =
      new VertexIterator(iterator.filter((vertex) => assert(vertex)))

    def extend(direction: Direction.Value, label: Option[String]): EdgeIterator = {
      type M = scala.collection.Map[String, EdgeT]
      type M2I = M => Iterator[EdgeT]

      val map2Iter = label match {
        case Some(label) => (x: M) => x.get(label).iterator // we can create an iterator out of an Option
        case _ => (x: M) => x.valuesIterator
      }

      val navigate = direction match {
        case Direction.Out => (v: VertexT, mapToIter: M2I)
          => mapToIter(v.edges)
        case Direction.In => (v: VertexT, mapToIter: M2I)
          => mapToIter(v.reverseEdges)
        case Direction.In => (v: VertexT, mapToIter: M2I)
          => mapToIter(v.edges) ++ mapToIter(v.reverseEdges)
      }

      new EdgeIterator(iterator.flatMap((v) => navigate(v, map2Iter)))

    }

    override def hasNext: Boolean = iterator.hasNext

    override def next(): VertexT = iterator.next()
  }

  class EdgeIterator(
                      private val iterator: Iterator[EdgeT]
                    ) extends Iterator[EdgeT] {

    def check(assert: BExpr[EdgeT]) =
      new EdgeIterator(iterator.filter((edge) => assert(edge)))

    def targetVertex() = ???
    def sourceVertex() = ???

    override def hasNext: Boolean = iterator.hasNext

    override def next(): EdgeT = iterator.next()
  }
}


