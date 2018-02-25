package ingraph.compiler.qplan2jplan

import ingraph.model.expr._
import ingraph.model.{jplan, qplan}

object QPlanToJPlan {
  def transform(plan: qplan.QNode): jplan.JNode = {
    plan match {
      // leaf
      case qplan.GetVertices(v) => jplan.GetVertices(v)
      case qplan.Dual() => jplan.Dual()

      // unary read
      case qplan.Expand(src, trg, edge, dir, qplan.GetVertices(v)) => edge match {
        case e: EdgeAttribute => getEdgesForDirection(src, trg, e, dir)
        case el: EdgeListAttribute =>
          jplan.TransitiveJoin(
            jplan.GetVertices(src),
            expandToTransitiveEdges(src, trg, el, dir),
            el
          )
      }
      case qplan.Expand(src, trg, edge, dir, child) => edge match {
        case e: EdgeAttribute => jplan.Join(transform(child), getEdgesForDirection(src, trg, e, dir))
        case el: EdgeListAttribute => jplan.TransitiveJoin(transform(child), expandToTransitiveEdges(src, trg, el, dir), el)
      }
      case qplan.Top(skipExpr, limitExpr, qplan.Sort(order, child)) => jplan.SortAndTop(skipExpr, limitExpr, order, transform(child))
      // if Sort operator found w/o Top, then skip and limit defaults to None
      case qplan.Top(_, _, _) => throw new UnsupportedOperationException("Vanilla 'SKIP'/'LIMIT' is not supported, add an 'ORDER BY' clause.")
      case qplan.Sort(order, child) => jplan.SortAndTop(None, None, order, transform(child))
      case qplan.Production(child) => jplan.Production(transform(child))
      case qplan.Projection(projectList, child) => jplan.Projection(projectList, transform(child))
      case qplan.Grouping(aggregationCriteria, projectList, child) => jplan.Grouping(aggregationCriteria, projectList, transform(child))
      case qplan.Selection(condition, child) => jplan.Selection(condition, transform(child))
      case qplan.DuplicateElimination(child) => jplan.DuplicateElimination(transform(child))
      case qplan.AllDifferent(edges, child) => jplan.AllDifferent(edges, transform(child))
      case qplan.Unwind(unwindItem, child) => jplan.Unwind(unwindItem, transform(child))
      // unary CUD
      case qplan.Create(attributes, child) =>
        attributes.foldLeft(transform(child)) { (op, attribute) => jplan.Create(attribute, op) }
      case qplan.Delete(attributes, detach, child) => jplan.Delete(attributes, detach, transform(child))
      case qplan.Merge(attributes, child) =>
        attributes.foldLeft(transform(child)) { (op, attribute) => jplan.Merge(attribute, op) }
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

  def getEdgesForDirection(src: VertexAttribute,
                           trg: VertexAttribute,
                           e: EdgeAttribute,
                           dir: Direction): jplan.GetEdges = {
    dir match {
      case Out  => jplan.GetEdges(src, trg, e, directed = true)
      case In   => jplan.GetEdges(trg, src, e, directed = true)
      case Both => jplan.GetEdges(src, trg, e, directed = false)
    }
  }


  def expandToTransitiveEdges(src: VertexAttribute,
                              trg: VertexAttribute,
                              el: EdgeListAttribute,
                              dir: Direction): jplan.GetEdges = {
    val e = EdgeAttribute(el.name, el.labels, el.properties, el.isAnonymous, el.resolvedName)
    getEdgesForDirection(src, trg, e, dir)
  }

}
