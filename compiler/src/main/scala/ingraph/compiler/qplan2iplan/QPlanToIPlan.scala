package ingraph.compiler.qplan2iplan

import ingraph.model.iplan.INode
import ingraph.model.{iplan, qplan}
import ingraph.model.qplan.QNode

object QPlanToIPlan {
  def transform(plan: QNode): INode = {
    plan match {
      // leaf
      case qplan.GetVertices(v) => iplan.GetVertices(v)
      case qplan.Dual() => iplan.Dual()

      // unary
      case qplan.Expand(src, trg, edge, dir, qplan.GetVertices(v)) => iplan.GetEdges(src, trg, edge, dir)
      case qplan.Expand(src, trg, edge, dir, child) => iplan.Join(transform(child), iplan.GetEdges(src, trg, edge, dir))
      case qplan.Top(skipExpr, limitExpr, qplan.Sort(order, child)) => iplan.SortAndTop(skipExpr, limitExpr, order, transform(child))
      case qplan.Production(child) => iplan.Production(transform(child))
      case qplan.Projection(projectList, child) => iplan.Projection(projectList, transform(child))
      case qplan.Selection(condition, child) => iplan.Selection(condition, transform(child))
      case qplan.DuplicateElimination(child) => iplan.DuplicateElimination(transform(child))
      case qplan.AllDifferent(child, edges) => iplan.AllDifferent(transform(child), edges)

      // binary
      case qplan.Union(l, r) => iplan.Union(transform(l), transform(r))
      case qplan.Join(l, r) => iplan.Join(transform(l), transform(r))
      case qplan.LeftOuterJoin(l, r) => iplan.LeftOuterJoin(transform(l), transform(r))
      case qplan.ThetaLeftOuterJoin(l, r, condition) => iplan.ThetaLeftOuterJoin(transform(l), transform(r), condition)
      case qplan.AntiJoin(l, r) => iplan.AntiJoin(transform(l), transform(r))
    }
  }
}
