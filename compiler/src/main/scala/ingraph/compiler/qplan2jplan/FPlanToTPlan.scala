package ingraph.compiler.qplan2jplan

import ingraph.model.expr._
import ingraph.model.fplan
import ingraph.model.tplan

object FPlanToTPlan {

  private def transform(fnode: fplan.FNode): tplan.TNode = {
    fnode match {
      // leaf
      case o: fplan.GetEdges     => tplan.GetEdges(o)
      case o: fplan.GetEdgeLists => tplan.GetEdgeLists(o)
      case o: fplan.GetVertices  => tplan.GetVertices(o)
      case o: fplan.Dual         => tplan.Dual(o)

      // unary
      case o: fplan.Projection =>
        tplan.Projection(o, transform(o.child))
      case o: fplan.Grouping =>
        tplan.Grouping(o, transform(o.child))
      case o: fplan.Selection =>
        tplan.Selection(o, transform(o.child))
      case o: fplan.Unwind =>
        tplan.Unwind(o, transform(o.child))
      // the rest is just the same, isn't it?
      case o: fplan.AllDifferent         => tplan.AllDifferent        (o, transform(o.child))
      case o: fplan.DuplicateElimination => tplan.DuplicateElimination(o, transform(o.child))
      case o: fplan.Production           => tplan.Production          (o, transform(o.child))
      case o: fplan.SortAndTop           => tplan.SortAndTop          (o, transform(o.child))
      // unary DMLs
      case o: fplan.Create               => tplan.Create              (o, transform(o.child))
      case o: fplan.Delete               => tplan.Delete              (o, transform(o.child))
      case o: fplan.Merge                => tplan.Merge               (o, transform(o.child))
      case o: fplan.Remove               => tplan.Remove              (o, transform(o.child))
      case o: fplan.SetNode              => tplan.SetNode             (o, transform(o.child))

      // binary
      case j: fplan.JoinLike => {
        val left = transform(j.left)
        val right = transform(j.right)
        val leftMask = Seq()
        val rightMask = Seq()

        j match {
          case o: fplan.AntiJoin           => tplan.AntiJoin(leftMask, rightMask, o, left, right)
          case o: fplan.Join               => tplan.Join(leftMask, rightMask, o, left, right)
          case o: fplan.LeftOuterJoin      => tplan.LeftOuterJoin(leftMask, rightMask, o, left, right)
          case o: fplan.ThetaLeftOuterJoin => tplan.ThetaLeftOuterJoin(leftMask, rightMask, o, left, right)
        }
      }
      case o: fplan.Union => tplan.Union(o, transform(o.left), transform(o.right))
    }
  }


}
