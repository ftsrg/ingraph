package ingraph.sre

import ingraph.model.qplan
import ingraph.model.qplan.QNode

// https://github.com/FTSRG/ingraph/blob/master/ingraph-compiler/ingraph-compiler-search2rete/src/main/ingraph_xtend/search2tasks/TaskCompiler.xtend
object QPlanToConstraints {
  def transform(plan: QNode): Unit = {
    plan match {
      // leaf
      case qplan.GetVertices(v) => ???

      // unary
      case qplan.Expand(src, trg, edge, dir, qplan.GetVertices(v)) => ???
      case qplan.Expand(src, trg, edge, dir, child) => ???
      case qplan.Top(skipExpr, limitExpr, qplan.Sort(order, child)) => ???
      case qplan.Projection(projectList, child) => ???
      case qplan.Selection(condition, child) => ???
      case qplan.DuplicateElimination(child) => ???
      case qplan.AllDifferent(edges, child) => ???
      case qplan.Unwind(element, child) => ???

      // binary
      case qplan.Union(l, r) => ???
    }
  }
}
