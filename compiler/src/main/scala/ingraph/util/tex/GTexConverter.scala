package ingraph.util.tex

import ingraph.model.expr
import ingraph.model.gplan._
import ingraph.model.treenodes.{GenericBinaryNode, GenericLeafNode, GenericUnaryNode}
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan

class GTexConverter extends GraphTexConverter[GNode] {

  override def toTex(p: LogicalPlan): String = {
    p match {
      case p: Production => toTex(p.child)  // ignore Production operation
      case _=> super.toTex(p)
    }
  }

  override protected[this] def unaryToTex(u: GenericUnaryNode[GNode]): String = {
    u match {
      case _: DuplicateElimination => this.duplicateElimination
      case s: Sort => this.sort(s.order)
      case e: Expand =>
        val op = e.dir match {
          case expr.Both => "\\expandbothop"
          case expr.Out => "\\expandoutop"
          case expr.In => "\\expandinop"
          case _ => ???
        }
        val srcname = escapeTex(e.src.name)
        val trgname = escapeTex(e.trg.name)
        val trglabels = concat(e.trg.labels.vertexLabels, ", ")
        val edge = escapeTex(s"${e.edge.name} : ${e.edge.labels.edgeLabels.mkString(", ")}")
        s"$$${op}_\\atom{${srcname}}^\\atom{${trgname}:${trglabels}} \\atom{[${edge}]}$$"

      case g: Grouping =>
        val projectionList = for (p <- g.projectList) yield toTex(p)
        val aggregationCriteria = for (a <- g.aggregationCriteria) yield toTex(a)
        this.grouping(projectionList, aggregationCriteria)
      case u: Unwind => this.unwind(u.unwindAttribute)
      case t: Top => this.top(t.skipExpr, t.limitExpr)
      case s: Selection => this.selection(s.condition)
      case p: Projection =>
        val projectList = for (attr <- p.projectList) yield toTex(attr)
        projection(projectList)

      case a: AllDifferent => this.allDifferent(a.edges)

      case _: Create => this.create()
      case _: Delete => this.delete()
      case _: Merge => this.merge()
      case _: SetNode => this.setNode()
      case _: Remove => this.remove()

      case _ => s"?? ${escapeTex(u.nodeName)}"
    }
  }

  override protected[this] def binaryToTeX(b: GenericBinaryNode[GNode]): String = {
    b match {
      case j: JoinLike =>
        val operator = j match {
          case _: Join => "\\join"
          case _: ThetaLeftOuterJoin => "\\leftouterjoin"
          case _: AntiJoin => "\\antijoin"
          case _: LeftOuterJoin => "\\leftouterjoin"
          case _ => ???
        }
        val common = for (c <- j.commonAttributes) yield toTex(c)
        this.join(operator, common)
      case u: Union => this.union
      case _ => s"?? ${escapeTex(b.nodeName)}"
    }
  }

  override protected[this] def leafToTex(l: GenericLeafNode[GNode]): String = {
    l match {
      case gv: GetVertices => this.getVertices(gv.v)
      case _: Dual => this.dual
      case _ => s"?? ${escapeTex(l.nodeName)}"
    }
  }
}
