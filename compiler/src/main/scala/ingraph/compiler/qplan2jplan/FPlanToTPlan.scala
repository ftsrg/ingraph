package ingraph.compiler.qplan2jplan

import ingraph.model.expr._
import ingraph.model.{fplan, tplan}
import org.apache.spark.sql.catalyst.expressions.{Expression, SortOrder}

object FPlanToTPlan {

  def transform(fnode: fplan.FNode): tplan.TNode = {
    fnode match {
      // leaf
      case o: fplan.GetEdges     => tplan.GetEdges(o)
      case o: fplan.GetEdgeLists => tplan.GetEdgeLists(o)
      case o: fplan.GetVertices  => tplan.GetVertices(o)
      case o: fplan.Dual         => tplan.Dual(o)

      // unary
      case o: fplan.Projection =>
        tplan.Projection(
          o.jnode.projectList.map(_.child).map(transformExpression(_, o.child.internalSchema)),
          o, transform(o.child)
        )
      case o: fplan.Grouping =>
        tplan.Grouping(
          o.jnode.aggregationCriteria     .map(transformExpression(_, o.child.internalSchema)),
          o.jnode.projectList.map(_.child).map(transformExpression(_, o.child.internalSchema)),
          o, transform(o.child)
        )
      case o: fplan.Selection =>
        tplan.Selection(
          transformExpression(o.jnode.condition, o.child.internalSchema),
          o, transform(o.child)
        )
      case o: fplan.Unwind =>
        tplan.Unwind(o.jnode.unwindAttribute, o, transform(o.child))
      // the expressions in SKIP ... LIMIT ... cannot refer to variables, so a simple copy of the expressions is enough
      case o: fplan.SortAndTop           => tplan.SortAndTop(
        o.jnode.skipExpr, o.jnode.limitExpr,
        o.jnode.order.map(transformExpression(_, o.child.internalSchema).asInstanceOf[SortOrder]),
        o, transform(o.child))
      // identity cases
      case o: fplan.AllDifferent         => tplan.AllDifferent        (o, transform(o.child))
      case o: fplan.DuplicateElimination => tplan.DuplicateElimination(o, transform(o.child))
      case o: fplan.Production           => tplan.Production          (o, transform(o.child))
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
        val leftMask = j.leftMask
        val rightMask = j.rightMask

        j match {
          case o: fplan.AntiJoin           => tplan.AntiJoin          (leftMask, rightMask, o, left, right)
          case o: fplan.Join               => tplan.Join              (leftMask, rightMask, o, left, right)
          case o: fplan.LeftOuterJoin      => tplan.LeftOuterJoin     (leftMask, rightMask, o, left, right)
          case o: fplan.ThetaLeftOuterJoin => tplan.ThetaLeftOuterJoin(leftMask, rightMask,
            transformExpression(o.jnode.condition, o.left.internalSchema, o.right.internalSchema),
            o, left, right)
        }
      }
      case o: fplan.Union => tplan.Union(o, transform(o.left), transform(o.right))
    }
  }

  def transformExpression(expression: Expression, internalSchema: Seq[ResolvableName]): Expression = {
    expression.transform {
      case a: ResolvableName => TupleIndexLiteralAttribute(internalSchema.map(_.resolvedName).indexOf(a.resolvedName))
      case e: Expression => e
    }
  }

  def transformExpression(expression: Expression, internalSchemaLeft: Seq[ResolvableName],
                          internalSchemaRight: Seq[ResolvableName]): Expression = {
    expression.transform {
      case a: ResolvableName => {
        val left = internalSchemaLeft.map(_.resolvedName)
        val right = internalSchemaRight.map(_.resolvedName)
        val resolvedName = a.resolvedName
        if (left.contains(resolvedName)) {
          TupleIndexLiteralAttribute(left.indexOf(resolvedName), Option(Left()))
        } else {
          TupleIndexLiteralAttribute(right.indexOf(resolvedName), Option(Right()))
        }
      }
      case e: Expression => e
    }
  }

}
