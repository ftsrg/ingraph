package ingraph.util.tex

import ingraph.model.plan.TProduction
import ingraph.model.treenodes.GenericBinaryNode
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan

class GraphTexConverter[T <: LogicalPlan] extends TexConverter[T] {
  override def toStandaloneDoc(tex: String, ingraphDir: String): String = {
    val texGraph = s"\\begin{forest} $tex \\end{forest}"
    super.toStandaloneDoc(texGraph, ingraphDir)
  }

  override def toTex(p: LogicalPlan): String = {
    p match {
      case p: TProduction => toTex(p.child)  // ignore production operator
      case b: GenericBinaryNode[T] => s"[${binaryToTeX(b)} ${toTex(b.left)} ${toTex(b.right)}]\n"
      case _=> s"[${super.toTex(p)}]\n"
    }
  }
}
