package ingraph.compiler.qplan2iplan

import ingraph.model.qplan
import org.apache.spark.sql.catalyst.{TableIdentifier, analysis, plans}
import org.apache.spark.sql.catalyst.plans.logical

object QPlanToSqlPlan {
  def transform(plan: qplan.QNode): logical.LogicalPlan = {
    plan match {
      // leaf
      case qplan.GetVertices(v) => analysis.UnresolvedRelation(TableIdentifier(v.name))
      case qplan.Dual() => logical.OneRowRelation

      // unary read
      case qplan.Expand(src, trg, edge, dir, child) =>
        logical.Join(
          transform(child),
          logical.Join(
            analysis.UnresolvedRelation(TableIdentifier(edge.name)),
            analysis.UnresolvedRelation(TableIdentifier(trg.name)),
            plans.Inner,
            None
          ),
          plans.Inner,
          None
        )
      case qplan.Top(skipExpr, limitExpr, qplan.Sort(order, child)) => logical.GlobalLimit(skipExpr, transform(child))

      case qplan.Production(child) => transform(child) // throw away production nodes for now
      case qplan.Projection(projectList, child) => logical.Project(projectList, transform(child))
      case qplan.Selection(condition, child) => logical.Filter(condition, transform(child))
      case qplan.DuplicateElimination(child) => logical.Distinct(transform(child))
      case qplan.AllDifferent(edges, child) => transform(child) // TODO
//      case qplan.Unwind(collection, element, child) => iplan.Unwind(collection, element, transform(child))
      // unary CUD
//      case qplan.Create(attributes, child) => iplan.Create(attributes, transform(child))
//      case qplan.Delete(attributes, detach, child) => iplan.Delete(attributes, detach, transform(child))
//      case qplan.Merge(attributes, child) => iplan.Merge(attributes, transform(child))
//      case qplan.Remove(vertexLabelUpdates, child) => iplan.Remove(vertexLabelUpdates, transform(child))
//      case qplan.SetNode(vertexLabelUpdates, child) => iplan.SetNode(vertexLabelUpdates, transform(child))

      // binary
      case qplan.Union(true, l, r)                   => logical.Union(transform(l), transform(r))
      case qplan.Union(false, l, r)                  => logical.Distinct(logical.Union(transform(l), transform(r)))
      case qplan.Join(l, r)                          => logical.Join(transform(l), transform(r), plans.Inner, null)
      case qplan.LeftOuterJoin(l, r)                 => logical.Join(transform(l), transform(r), plans.LeftOuter, null)
      case qplan.ThetaLeftOuterJoin(l, r, condition) => logical.Join(transform(l), transform(r), plans.LeftOuter, null)
      case qplan.AntiJoin(l, r)                      => logical.Join(transform(l), transform(r), plans.LeftAnti, null)
    }
  }
}
