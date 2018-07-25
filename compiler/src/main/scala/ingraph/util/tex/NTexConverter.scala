package ingraph.util.tex

import ingraph.model.expr.AbstractEdgeAttribute
import ingraph.model.nplan._
import ingraph.model.treenodes.{GenericBinaryNode, GenericLeafNode, GenericUnaryNode}
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan

class NTexConverter extends GraphTexConverter[NNode] {
  override def toTex(p: LogicalPlan): String = {
    p match {
      case p: Production => toTex(p.child)  // ignore Production operation
      case _=> super.toTex(p)
    }
  }

  override protected[this] def unaryToTex(u: GenericUnaryNode[NNode]): String = {
    u match {
      case _: DuplicateElimination => this.duplicateElimination
      case s: SortAndTop => this.sortAndTop(s.order, s.skipExpr, s.limitExpr)
      case g: Grouping =>
        val projectionList = for (p <- g.projectList) yield toTex(p)
        val aggregationCriteria = for (a <- g.aggregationCriteria) yield toTex(a)
        this.grouping(projectionList, aggregationCriteria)

      case u: Unwind => this.unwind(u.unwindAttribute)
      case s: Selection => this.selection(s.condition)
      case p: Projection =>
        val projectList = for (attr <- p.projectList) yield toTex(attr)
        this.projection(projectList)
      case a: AllDifferent => this.allDifferent(a.edges)

      case _: Create => this.create()
      case _: Delete => this.delete()
      case _: Merge => this.merge()
      case _: SetNode => this.setNode()
      case _: Remove => this.remove()

      case _ => s"?? ${escapeTex(u.nodeName)}"
    }
  }

  override protected[this] def binaryToTeX(b: GenericBinaryNode[NNode]): String = {
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
        val common = for (c <- j.common) yield toTex(c)
        this.join(operator, common)
      case u: Union => this.union
      case _ => s"?? ${escapeTex(b.nodeName)}"
    }
  }

  def leafToTex(l: GenericLeafNode[NNode]): String = {
    l match {
      case gv: GetVertices => this.getVertices(gv.v)
      case ge: GetEdges => this.getEdges(ge.directed, ge.src, ge.trg, ge.edge)
      case _: Dual => this.dual
      case _ => s"?? ${escapeTex(l.nodeName)}"
    }
  }
}
