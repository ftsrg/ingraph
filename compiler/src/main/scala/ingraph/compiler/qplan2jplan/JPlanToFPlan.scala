package ingraph.compiler.qplan2jplan

import ingraph.model.expr._
import ingraph.model.expr.types.TProjectList
import ingraph.model.{fplan, jplan}
import org.apache.spark.sql.catalyst.expressions.Expression

/**
  * Performs schema inferencing to transform a JPlan to an FPlan.
  */
object JPlanToFPlan {

  def transform(jnode: jplan.JNode): fplan.FNode = {
    transform(jnode, Seq())
  }

  private def transform(jnode: jplan.JNode, extraAttributes: Seq[ResolvableName]): fplan.FNode = {
    val ea = extraAttributes.distinct

    jnode match {
      // leaf
      case o: jplan.GetEdges     => fplan.GetEdges(ea, o)
      case o: jplan.GetEdgeLists => fplan.GetEdgeLists(ea, o)
      case o: jplan.GetVertices  => fplan.GetVertices(ea, o)
      case o: jplan.Dual         =>
        if (ea.nonEmpty) {
          throw new IllegalStateException(s"Dual node cannot hold extra attributes: ${ea}")
        }
        fplan.Dual(o)

      // unary
      case o: jplan.Projection =>
        val newExtra = extractAttributes(o.projectList).filter(a => !o.child.output.map(_.resolvedName).contains(a.resolvedName) && !ea.contains(a.resolvedName))
        fplan.Projection(ea, o, transform(o.child, ea ++ newExtra))
      case o: jplan.Grouping =>
        val newExtra = extractAttributes(o.projectList).filter(a => !o.child.output.map(_.resolvedName).contains(a.resolvedName) && !ea.contains(a.resolvedName))
        fplan.Grouping(ea, o, transform(o.child, ea ++ newExtra))
      case o: jplan.Selection =>
        val newExtra = extractAttributes(o.condition).filter(a => !o.child.output.map(_.resolvedName).contains(a.resolvedName) && !ea.contains(a.resolvedName))
        fplan.Selection(ea, o, transform(o.child, ea ++ newExtra))
      case o: jplan.Unwind =>
        fplan.Unwind(ea, o, transform(o.child, ea ++ extractAttributes(o.unwindAttribute.list)))
      // the rest is just the same, isn't it?
      case o: jplan.AllDifferent         => fplan.AllDifferent        (ea, o, transform(o.child, ea))
      case o: jplan.DuplicateElimination => fplan.DuplicateElimination(ea, o, transform(o.child, ea))
      case o: jplan.Production           => fplan.Production          (ea, o, transform(o.child, ea))
      case o: jplan.SortAndTop           => fplan.SortAndTop          (ea, o, transform(o.child, ea))
      // unary DMLs
      case o: jplan.Create               => fplan.Create              (ea, o, transform(o.child, ea))
      case o: jplan.Delete               => fplan.Delete              (ea, o, transform(o.child, ea))
      case o: jplan.Merge                => fplan.Merge               (ea, o, transform(o.child, ea))
      case o: jplan.Remove               => fplan.Remove              (ea, o, transform(o.child, ea))
      case o: jplan.SetNode              => fplan.SetNode             (ea, o, transform(o.child, ea))

      // binary
      case o: jplan.AntiJoin => fplan.AntiJoin(
          ea, o,
          transform(o.left, ea),
          transform(o.right, Seq())
        )
      case j: jplan.EquiJoinLike => {
        val eaLeft = propagate(ea, j.left.output)
        val eaRight = propagate(ea, j.right.output).filter(x => !eaLeft.map(_.resolvedName).contains(x.resolvedName))

        val left = transform(j.left, eaLeft)
        val right = transform(j.right, eaRight)

        j match {
          case o: jplan.Join => fplan.Join(ea, o, left, right)
          case o: jplan.LeftOuterJoin => fplan.LeftOuterJoin(ea, o, left, right)
          case o: jplan.ThetaLeftOuterJoin => fplan.ThetaLeftOuterJoin(ea, o, left, right)
        }
      }
      case o: jplan.Union => fplan.Union(ea, o,
        transform(o.left, ea),
        transform(o.right, ea)
      )
    }
  }

  /**
    * propagate property attributes to wherever their element is
    */
  def propagate(extraAttributes: Seq[ResolvableName], inputSchema: Seq[ResolvableName]): Seq[ResolvableName] = {
    extraAttributes
      .flatMap {
        case a: PropertyAttribute => Some(a)
        case _ => None
      }
      .filter(a => inputSchema.map(_.resolvedName).contains(a.elementAttribute.resolvedName))

    // !inputSchema.contains(a) // do we need this?
  }

  def extractAttributes(expression: Expression): Seq[ResolvableName] = {
    (expression match {
      case a: ReturnItem => extractAttributes(a.child)
      case a: PropertyAttribute => Seq(a)
      case _ => Seq()
    }) ++ expression.children.flatMap(extractAttributes(_))
  }

  def extractAttributes(projectList: TProjectList): Seq[ResolvableName] = {
    projectList.flatMap(a => extractAttributes(a))
  }

}
