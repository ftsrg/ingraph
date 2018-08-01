package ingraph.util.tex

import ingraph.model.plan.TProduction
import ingraph.model.treenodes.GenericBinaryNode
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan

class GraphTexConverter[T <: LogicalPlan] extends TexConverter[T] {
  override def toStandaloneDoc(tex: List[String], ingraphDir: String, comment: String): String = {
    val begin = "\\begin{forest}"
    val end = "\\end{forest}"
    val texGraph = s"$begin${tex.mkString(s"$end\\quad$begin")}$end"
    super.toStandaloneDoc(List(texGraph), ingraphDir, comment)
  }

  override def toTex(p: LogicalPlan): String = {
    p match {
      case p: TProduction => toTex(p.child)  // ignore production operator
      case b: GenericBinaryNode[T] => s"[${binaryToTeX(b)} ${toTex(b.left)} ${toTex(b.right)}]\n"
      case _=> s"[${super.toTex(p)}]\n"
    }
  }
}
