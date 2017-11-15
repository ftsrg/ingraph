package ingraph.compiler.qplan2jplan

import ingraph.model.jplan.JNode
import ingraph.model.{expr, jplan, qplan}
import ingraph.model.qplan.QNode

object QPlanToJPlan {
  def transform(plan: QNode): JNode = {
    plan match {
      // leaf
      case qplan.GetVertices(v) => jplan.GetVertices(v)
      case qplan.Dual() => jplan.Dual()

      // unary read
      case qplan.Expand(src, trg, edge, dir, qplan.GetVertices(v)) => edge match {
        case e: expr.EdgeAttribute => jplan.GetEdges(src, trg, e, dir)
        case el: expr.EdgeListAttribute => jplan.GetEdgeLists(src, trg, el, dir)
      }
      case qplan.Expand(src, trg, edge, dir, child) => edge match {
        case e: expr.EdgeAttribute => jplan.Join(transform(child), jplan.GetEdges(src, trg, e, dir))
        case el: expr.EdgeListAttribute => jplan.Join(transform(child), jplan.GetEdgeLists(src, trg, el, dir))
      }
      case qplan.Top(skipExpr, limitExpr, qplan.Sort(order, child)) => jplan.SortAndTop(skipExpr, limitExpr, order, transform(child))
      case qplan.Production(child) => jplan.Production(transform(child))
      case qplan.Projection(projectList, child) => jplan.Projection(projectList, transform(child))
      case qplan.Selection(condition, child) => jplan.Selection(condition, transform(child))
      case qplan.DuplicateElimination(child) => jplan.DuplicateElimination(transform(child))
      case qplan.AllDifferent(edges, child) => jplan.AllDifferent(edges, transform(child))
      case qplan.Unwind(collection, element, child) => jplan.Unwind(collection, element, transform(child))
      // unary CUD
      case qplan.Create(attributes, child) => jplan.Create(attributes, transform(child))
      case qplan.Delete(attributes, detach, child) => jplan.Delete(attributes, detach, transform(child))
      case qplan.Merge(attributes, child) => jplan.Merge(attributes, transform(child))
      case qplan.Remove(vertexLabelUpdates, child) => jplan.Remove(vertexLabelUpdates, transform(child))
      case qplan.SetNode(vertexLabelUpdates, child) => jplan.SetNode(vertexLabelUpdates, transform(child))

      // binary
      case qplan.Union(bag, l, r) => jplan.Union(bag, transform(l), transform(r))
      case qplan.Join(l, r) => jplan.Join(transform(l), transform(r))
      case qplan.LeftOuterJoin(l, r) => jplan.LeftOuterJoin(transform(l), transform(r))
      case qplan.ThetaLeftOuterJoin(l, r, condition) => jplan.ThetaLeftOuterJoin(transform(l), transform(r), condition)
      case qplan.AntiJoin(l, r) => jplan.AntiJoin(transform(l), transform(r))
    }
  }
}
