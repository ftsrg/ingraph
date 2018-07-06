package ingraph.compiler.plantransformers

import ingraph.model.expr._
import ingraph.model.{nplan, gplan}

object GPlanToNPlan {
  def transform(plan: gplan.GNode): nplan.NNode = {
    plan match {
      // leaf
      case gplan.GetVertices(v) => nplan.GetVertices(v)
      case gplan.Dual() => nplan.Dual()

      // unary read
      case gplan.Expand(src, trg, e: EdgeAttribute, dir, child) =>
        val getEdges = getEdgesForDirection(src, trg, e, dir)
        child match {
          case gv: gplan.GetVertices => getEdges
          case _                     => nplan.Join(transform(child), getEdges)
        }
      case gplan.Expand(src, trg, el: EdgeListAttribute, dir, child) =>
        val transitiveLeft = child match {
          case gplan.GetVertices(v) => nplan.GetVertices(v)
          case _                    => transform(child)
        }
        val transitiveRight = expandToTransitiveEdges(src, trg, el, dir)
        val sourceJoin = nplan.GetVertices(src)
        val targetJoin = nplan.GetVertices(trg)
        // perform an additional join to ensure that the type of the target nodes is correct
        // note that this join is right-preferring, i.e. its 'requiredProperties' will be propagated to its right input
        nplan.Join(
          nplan.TransitiveJoin(transitiveLeft, transitiveRight, el),
          targetJoin,
          Right()
        )
      case gplan.Top(skipExpr, limitExpr, gplan.Sort(order, child)) => nplan.SortAndTop(skipExpr, limitExpr, order, transform(child))
      // if Sort operator found w/o Top, then skip and limit defaults to None
      case gplan.Top(_, _, _) => throw new UnsupportedOperationException("Vanilla 'SKIP'/'LIMIT' is not supported, add an 'ORDER BY' clause. Please.")
      case gplan.Sort(order, child) => nplan.SortAndTop(None, None, order, transform(child))
      case gplan.Production(child) => nplan.Production(transform(child))
      case gplan.Projection(projectList, child) => nplan.Projection(projectList, transform(child))
      case gplan.Grouping(aggregationCriteria, projectList, child) => nplan.Grouping(aggregationCriteria, projectList, transform(child))
      case gplan.Selection(condition, child) => nplan.Selection(condition, transform(child))
      case gplan.DuplicateElimination(child) => nplan.DuplicateElimination(transform(child))
      case gplan.AllDifferent(edges, child) => nplan.AllDifferent(edges, transform(child))
      case gplan.Unwind(unwindItem, child) => nplan.Unwind(unwindItem, transform(child))
      // unary CUD
      case gplan.Create(attributes, child) =>
        attributes.foldLeft(transform(child)) { (op, attribute) => nplan.Create(attribute, op) }
      case gplan.Delete(attributes, detach, child) => nplan.Delete(attributes, detach, transform(child))
      case gplan.Merge(attributes, child) =>
        attributes.foldLeft(transform(child)) { (op, attribute) => nplan.Merge(attribute, op) }
      case gplan.Remove(vertexLabelUpdates, child) => nplan.Remove(vertexLabelUpdates, transform(child))
      case gplan.SetNode(vertexLabelUpdates, child) => nplan.SetNode(vertexLabelUpdates, transform(child))

      // binary
      case gplan.Union(bag, l, r) => nplan.Union(bag, transform(l), transform(r))
      case gplan.Join(l, r) => nplan.Join(transform(l), transform(r))
      case gplan.LeftOuterJoin(l, r) => nplan.LeftOuterJoin(transform(l), transform(r))
      case gplan.ThetaLeftOuterJoin(l, r, condition) => nplan.ThetaLeftOuterJoin(transform(l), transform(r), condition)
      case gplan.AntiJoin(l, r) => nplan.AntiJoin(transform(l), transform(r))
    }
  }

  def getEdgesForDirection(src: VertexAttribute,
                           trg: VertexAttribute,
                           e: EdgeAttribute,
                           dir: Direction): nplan.GetEdges = {
    dir match {
      case Out  => nplan.GetEdges(src, trg, e, directed = true)
      case In   => nplan.GetEdges(trg, src, e, directed = true)
      case Both => nplan.GetEdges(src, trg, e, directed = false)
    }
  }

  def expandToTransitiveEdges(src: VertexAttribute,
                              trg: VertexAttribute,
                              el: EdgeListAttribute,
                              dir: Direction): nplan.GetEdges = {
    val e = EdgeAttribute(el.name, el.labels, el.properties, el.isAnonymous, el.resolvedName)
    val getEdges = getEdgesForDirection(src, trg, e, dir)
    val src2 = getEdges.src.copy(labels = VertexLabelSet())
    val trg2 = getEdges.trg.copy(labels = VertexLabelSet())
    val getEdges2 = getEdges.copy(src = src2, trg = trg2)
    getEdges2
  }

}
