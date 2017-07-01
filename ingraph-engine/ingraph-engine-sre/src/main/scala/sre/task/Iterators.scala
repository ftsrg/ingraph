package sre.task

import simulacrum.{noop, typeclass}
import sre.task.Core.{Context, ContextLike, Direction, EdgeT, ElementT, In, LabelT, Navigator, NavigatorContext, Out, VertexT, WithVariables}
import sre.task.Expressions.{AssertExpr, RefExpr}

import scala.language.higherKinds
import scalaz.{-\/, \/-}

object Iterators {

  @typeclass trait ElementIterator[It[_]] {
    def check[Item[_], Element <: ElementT, Ctx >: Context[ElementT]](iterator: It[Item[Element]])
                                                                     (assert: AssertExpr[Ctx])
                                                                     (implicit ctxLike: ContextLike[Item]): It[Item[Element]]

    // FIXME: have to add @noop because a limitation in simulacrum
    @noop def record[Item[_], Element <: ElementT](iterator: It[Item[Element]],
                                                   name: String,
                                                   what: RefExpr[Context[Element], Any])
                                                  (implicit ctxLike: ContextLike[Item],
                                                   withVariables: WithVariables[Item[Element]]
                                                  ): It[Item[Element]]
  }

  @typeclass trait VertexIterator[It[_]] extends ElementIterator[It] {
    def ext[Item[_], Vertex <: VertexT](iterator: It[Item[Vertex]])(
      direction: Option[Direction],
      label: Option[LabelT])
                                       (implicit ctxLike: ContextLike[Item]): It[NavigatorContext[EdgeT]]
  }

  @typeclass trait EdgeIterator[It[_]] extends ElementIterator[It] {
    def end[Item[_], Edge <: EdgeT](iterator: It[Item[Edge]], direction: Direction)
                                   (implicit ctxLike: ContextLike[Item]): It[Context[VertexT]]
  }

  @typeclass trait NavigatingEdgeIterator[It[_]] extends EdgeIterator[It] {
    def end[Item[_], Edge <: EdgeT](iterator: Iterator[Item[Edge]])
                                   (implicit ctxLike: ContextLike[Item],
                                    navigator: Navigator[Item[_]]
                                   ): Iterator[Context[VertexT]]
  }

  trait ElementIteratorForIterator extends ElementIterator[Iterator] {
    def check[Item[Element], Element <: ElementT, Ctx >: Context[ElementT]](iterator: Iterator[Item[Element]])
                                                                           (assert: AssertExpr[Ctx])
                                                                           (implicit ctxLike: ContextLike[Item]): Iterator[Item[Element]] = {
      iterator.filter((el) => {
        val ctx = ContextLike[Item]
        val head = ctx.head(el)(el)
        val x = Context(head, ctx.variables(el))
        assert(x) match {
          case \/-(Some(boolean)) => boolean
          case \/-(None) => false
          case -\/(exception) => throw exception // Let's throw instead of complicating the types even more.
        }
      })
    }

    override def record[Item[Element], Element <: ElementT](iterator: Iterator[Item[Element]],
                                                            name: String,
                                                            what: RefExpr[Context[Element], Any])
                                                           (implicit ctxLike: ContextLike[Item],
                                                            withVariables: WithVariables[Item[Element]]
                                                           ): Iterator[Item[Element]] = {
      iterator.map((item) => {
        val vars = ContextLike[Item].variables(item)
        what(Context(ContextLike[Item].head(item)(item), vars)) match {
          case \/-(Some(value)) => WithVariables[Item[Element]].withVariables(item, vars + (name -> value))
          case \/-(_) => item
          case -\/(exception) => throw exception // Let's throw instead of complicating the types even more.
        }
      })
    }
  }

  trait VertexIteratorForIterator extends ElementIteratorForIterator with VertexIterator[Iterator] {

    override def ext[Elem[_], V <: VertexT](
                                             iterator: Iterator[Elem[V]])(
                                             direction: Option[Direction],
                                             label: Option[LabelT])
                                           (implicit ctxLike: ContextLike[Elem]): Iterator[NavigatorContext[EdgeT]] = {
      type M = scala.collection.Map[String, EdgeT]
      type M2I = M => Iterator[EdgeT]

      val map2Iter = label match {
        case Some(label) => (x: M) => x.get(label).iterator // we can create an iterator out of an Option
        case _ => (x: M) => x.valuesIterator
      }

      val navigate = direction match {
        case Some(Out) => (v: VertexT, mapToIter: M2I)
        =>
          mapToIter(v.edges).map((_, Out))
        case Some(In) => (v: VertexT, mapToIter: M2I)
        =>
          mapToIter(v.reverseEdges).map((_, In))
        case None => (v: VertexT, mapToIter: M2I)
        =>
          mapToIter(v.edges).map((_, Out)) ++ mapToIter(v.reverseEdges).map((_, In))
      }

      for {
        a <- iterator
        item = ContextLike[Elem]
        vtx = item.head(a)(a)
        (edge, dir) <- navigate(vtx, map2Iter)
      } yield {
        // direction flipped because what is outgoing for a vertex is incoming for an edge
        NavigatorContext(edge, item.variables(a), dir.flip)
      }
    }
  }

  trait EdgeIteratorForIterator extends ElementIteratorForIterator with EdgeIterator[Iterator] {
    override def end[Item[_], Edge <: EdgeT](iterator: Iterator[Item[Edge]], direction: Direction)
                                            (implicit ctxLike: ContextLike[Item]): Iterator[Context[VertexT]] = {
      for {
        elem <- iterator
      } yield {
        val ctx = ContextLike[Item]
        val e = ctx.head(elem)(elem)
        direction match {
          case In => Context(e.targetVertex, ctx.variables(elem))
          case Out => Context(e.sourceVertex, ctx.variables(elem))
        }
      }
    }
  }

  trait NavigatingEdgeIteratorForIterator extends EdgeIteratorForIterator with NavigatingEdgeIterator[Iterator] {
    override def end[Item[_], Edge <: EdgeT](iterator: Iterator[Item[Edge]])
                                            (implicit ctxLike: ContextLike[Item],
                                             navigator: Navigator[Item[_]]
                                            ): Iterator[Context[VertexT]] = {
      for {
        elem <- iterator
      } yield {
        val ctx = ContextLike[Item]
        val e = ctx.head(elem)(elem)
        val navi = Navigator[Item[_]]
        navi.direction(elem) match {
          case In => Context(e.targetVertex, ctx.variables(elem))
          case Out => Context(e.sourceVertex, ctx.variables(elem))
        }
      }
    }
  }

  implicit val elementIteratorForIterator = new ElementIteratorForIterator {}
  implicit val vertexIteratorForIterator = new VertexIteratorForIterator {}
  implicit val edgeIteratorForIterator = new EdgeIteratorForIterator {}
  implicit val navigatingEdgeIteratorForIterator = new NavigatingEdgeIteratorForIterator {}
}


