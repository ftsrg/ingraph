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

      // unary read
      case qplan.Expand(src, trg, edge, dir, qplan.GetVertices(v)) => iplan.GetEdges(src, trg, edge, dir)
      case qplan.Expand(src, trg, edge, dir, child) => iplan.Join(transform(child), iplan.GetEdges(src, trg, edge, dir))
      case qplan.Top(skipExpr, limitExpr, qplan.Sort(order, child)) => iplan.SortAndTop(skipExpr, limitExpr, order, transform(child))
      case qplan.Production(child) => iplan.Production(transform(child))
      case qplan.Projection(projectList, child) => iplan.Projection(projectList, transform(child))
      case qplan.Selection(condition, child) => iplan.Selection(condition, transform(child))
      case qplan.DuplicateElimination(child) => iplan.DuplicateElimination(transform(child))
      case qplan.AllDifferent(edges, child) => iplan.AllDifferent(edges, transform(child))
      case qplan.Unwind(collection, element, child) => iplan.Unwind(collection, element, transform(child))
      // unary CUD
      case qplan.Create(attributes, child) => iplan.Create(attributes, transform(child))
      case qplan.Delete(attributes, detach, child) => iplan.Delete(attributes, detach, transform(child))
      case qplan.Merge(attributes, child) => iplan.Merge(attributes, transform(child))
      case qplan.Remove(vertexLabelUpdates, child) => iplan.Remove(vertexLabelUpdates, transform(child))
      case qplan.SetNode(vertexLabelUpdates, child) => iplan.SetNode(vertexLabelUpdates, transform(child))

      // binary
      case qplan.Union(bag, l, r) => iplan.Union(bag, transform(l), transform(r))
      case qplan.Join(l, r) => iplan.Join(transform(l), transform(r))
      case qplan.LeftOuterJoin(l, r) => iplan.LeftOuterJoin(transform(l), transform(r))
      case qplan.ThetaLeftOuterJoin(l, r, condition) => iplan.ThetaLeftOuterJoin(transform(l), transform(r), condition)
      case qplan.AntiJoin(l, r) => iplan.AntiJoin(transform(l), transform(r))
    }
  }
}
