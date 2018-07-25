package ingraph.util.tex

import ingraph.model.fplan._
import ingraph.model.treenodes.{GenericBinaryNode, GenericLeafNode, GenericUnaryNode}
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan

class FTexConverter extends GraphTexConverter[FNode] {
  override def toTex(p: LogicalPlan): String = {
    p match {
      case p: Production => toTex(p.child)  // ignore Production operation
      case _=> super.toTex(p)
    }
  }

  override protected[this] def unaryToTex(u: GenericUnaryNode[FNode]): String = {
    u match {
      case _: DuplicateElimination => this.duplicateElimination
      case s: SortAndTop => this.sortAndTop(s.nnode.order, s.skipExpr, s.limitExpr)
      case g: Grouping =>
        val projectionList = for (p <- g.nnode.projectList) yield toTex(p)
        val aggregationCriteria = for (a <- g.nnode.aggregationCriteria) yield toTex(a)
        this.grouping(projectionList, aggregationCriteria)

      case u: Unwind => this.unwind(u.unwindAttribute)
      case s: Selection => this.selection(s.nnode.condition)
      case p: Projection =>
        val projectList =
          for (attr <- p.nnode.projectList)
            yield toTex(attr)
        this.projection(projectList)
      case a: AllDifferent => this.allDifferent(a.nnode.edges)

      case _: Create => this.create()
      case _: Delete => this.delete()
      case _: Merge => this.merge()
      case _: SetNode => this.setNode()
      case _: Remove => this.remove()

      case _ => s"?? ${u.nodeName}"
    }
  }

  override protected[this] def binaryToTeX(b: GenericBinaryNode[FNode]): String = {
    b match {
      case j: JoinLike =>
        val operator =  j match {
          case _: Join => "\\join"
          case _: ThetaLeftOuterJoin => "\\leftouterjoin"
          case _: AntiJoin => "\\antijoin"
          case _: LeftOuterJoin => "\\leftouterjoin"
          case _: TransitiveJoin => "\\transitivejoin"
          case _ => ???
        }
        val common = for (c <- j.flatCommon) yield toTex(c)
        this.join(operator, common)
      case u: Union => this.union
      case _ => s"?? ${escapeTex(b.nodeName)}"
    }
  }

  def leafToTex(l: GenericLeafNode[FNode]): String = {
    l match {
      case gv: GetVertices => this.getVertices(gv.nnode.v)
      case ge: GetEdges => this.getEdges(ge.nnode.directed, ge.nnode.src, ge.nnode.trg, ge.nnode.edge)
      case _: Dual => this.dual
      case _ => s"?? ${escapeTex(l.nodeName)}"
    }
  }
}
