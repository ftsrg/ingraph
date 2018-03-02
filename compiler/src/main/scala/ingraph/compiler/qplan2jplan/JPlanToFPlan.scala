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

  private def transform(jnode: jplan.JNode, req: Seq[ResolvableName]): fplan.FNode = {

    /**
      * Return whether a property selected for propagation is a duplicate:
      * (1) it is in the input operator's/operators' output, or
      * (2) it is already part of the required properties
      */
    def duplicate(attribute: ResolvableName, inputOperatorOutput: Seq[ResolvableName], requiredProperties: Seq[ResolvableName]) = {
      inputOperatorOutput.map(_.resolvedName).contains(attribute.resolvedName) || requiredProperties.map(_.resolvedName).contains(attribute.resolvedName)
    }

    jnode match {
      // leaf
      case o: jplan.GetEdges     => fplan.GetEdges(req, o)
      case o: jplan.GetVertices  => fplan.GetVertices(req, o)
      case o: jplan.Dual         =>
        if (req.nonEmpty) {
          throw new IllegalStateException(s"Dual node cannot provide properties: ${req}")
        }
        fplan.Dual(o)

      // unary
      case o: jplan.Projection =>
        val reqOp = extractProperties(o.projectList).filter(!duplicate(_, o.child.output, req))
        fplan.Projection(req, o, transform(o.child, req ++ reqOp))
      case o: jplan.Grouping =>
        val reqOp = extractProperties(o.projectList).filter(!duplicate(_, o.child.output, req))
        fplan.Grouping(req, o, transform(o.child, req ++ reqOp))
      case o: jplan.Selection =>
        val reqOp = extractProperties(o.condition).filter(!duplicate(_, o.child.output, req))
        fplan.Selection(o, transform(o.child, req ++ reqOp))
      case o: jplan.Unwind =>
        fplan.Unwind(o, transform(o.child, req ++ extractProperties(o.unwindAttribute.list)))
      case o: jplan.SortAndTop =>
        val reqOp = o.order.flatMap(x => extractProperties(x.child)).filter(!duplicate(_, o.child.output, req))
        fplan.SortAndTop (o, transform(o.child, req ++ reqOp))

      // the rest is just the same, isn't it?
      case o: jplan.AllDifferent         => fplan.AllDifferent        (o, transform(o.child, req))
      case o: jplan.DuplicateElimination => fplan.DuplicateElimination(o, transform(o.child, req))
      case o: jplan.Production           => fplan.Production          (o, transform(o.child, req))
      // unary DMLs
      case o: jplan.Create               =>
        val reqOp = extractPropertiesFromInsertion(o.attribute)
        fplan.Create(o, transform(o.child, req ++ reqOp))
      case o: jplan.Delete               => fplan.Delete(o, transform(o.child, req))
      case o: jplan.Merge                =>
        fplan.Merge(o, transform(o.child, req))
      case o: jplan.Remove               => fplan.Remove(o, transform(o.child, req))
      case o: jplan.SetNode              => fplan.SetNode(o, transform(o.child, req))

      // binary
      case j: jplan.JoinLike => {
        // ThetaLeftOuterJoins require special treatment: they are the only join operators that can require new properties
        val reqOp = j match {
          case o: jplan.ThetaLeftOuterJoin => extractProperties(o.condition).filter(!duplicate(_, o.left.output ++ o.right.output, req))
          case _ => Seq()
        }

        val rpTotal = req ++ reqOp

        val rpLeft = propagate(rpTotal, j.left.output)
        val rpRight = propagate(rpTotal, j.right.output).filter(x => !rpLeft.map(_.resolvedName).contains(x.resolvedName))

        val left = transform(j.left, rpLeft)
        val right = transform(j.right, rpRight)

        j match {
          case o: jplan.AntiJoin => {
            assert(rpRight.isEmpty)
            fplan.AntiJoin(o, left, right)
          }
          case o: jplan.Join => fplan.Join(o, left, right)
          case o: jplan.LeftOuterJoin => fplan.LeftOuterJoin(o, left, right)
          case o: jplan.ThetaLeftOuterJoin =>
            val reqOp = extractProperties(o.condition).filter(!duplicate(_, o.left.output ++ o.right.output, rpTotal))
            fplan.ThetaLeftOuterJoin(o, left, right)
          case o: jplan.TransitiveJoin => fplan.TransitiveJoin(o, left, right)
        }
      }
      case o: jplan.Union => fplan.Union(o,
        transform(o.left, req),
        transform(o.right, req)
      )
    }
  }

  /**
    * propagate required property attributes to wherever their element is
    */
  def propagate(requiredProperties: Seq[ResolvableName], inputSchema: Seq[ResolvableName]): Seq[ResolvableName] = {
    requiredProperties
      .flatMap {
        case a: PropertyAttribute => Some(a)
        case _ => None
      }
      .filter(a => inputSchema.map(_.resolvedName).contains(a.elementAttribute.resolvedName))
  }

  def extractProperties(expression: Expression): Seq[ResolvableName] = {
    (expression match {
      case a: ReturnItem => extractProperties(a.child)
      case a: PropertyAttribute => Seq(a)
      case _ => Seq()
    }) ++ expression.children.flatMap(a => extractProperties(a))
  }

  def extractProperties(projectList: TProjectList): Seq[ResolvableName] = {
    projectList.flatMap(extractProperties(_))
  }

  def extractPropertiesFromInsertion(attribute: ResolvableName): Seq[ResolvableName] = {
    attribute match {
      case a: VertexAttribute => extractProperties(a)
      case RichEdgeAttribute(src, trg, edge, _) => Seq(src, trg, edge).flatMap(extractProperties)
      case _ => Seq()
    }
  }

}
