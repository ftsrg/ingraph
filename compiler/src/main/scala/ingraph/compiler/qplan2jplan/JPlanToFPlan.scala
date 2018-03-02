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

  private def transform(jnode: jplan.JNode, ea: Seq[ResolvableName]): fplan.FNode = {
    /**
      * Return whether an attribute selected for propagation is a duplicate:
      * (1) it is in the input operator's/operators' output, or
      * (2) it is already part of the extra extra attributes
      */
    def duplicate(attribute: ResolvableName, inputOperatorOutput: Seq[ResolvableName], extraAttributes: Seq[ResolvableName]) = {
      inputOperatorOutput.map(_.resolvedName).contains(attribute.resolvedName) || extraAttributes.map(_.resolvedName).contains(attribute.resolvedName)
    }

    jnode match {
      // leaf
      case o: jplan.GetEdges     => fplan.GetEdges(ea, o)
      case o: jplan.GetVertices  => fplan.GetVertices(ea, o)
      case o: jplan.Dual         =>
        if (ea.nonEmpty) {
          throw new IllegalStateException(s"Dual node cannot hold extra attributes: ${ea}")
        }
        fplan.Dual(o)

      // unary
      case o: jplan.Projection =>
        val opExtra = extractAttributes(o.projectList).filter(!duplicate(_, o.child.output, ea))
        fplan.Projection(ea, o, transform(o.child, ea ++ opExtra))
      case o: jplan.Grouping =>
        val opExtra = extractAttributes(o.projectList).filter(!duplicate(_, o.child.output, ea))
        fplan.Grouping(ea, o, transform(o.child, ea ++ opExtra))
      case o: jplan.Selection =>
        val opExtra = extractAttributes(o.condition).filter(!duplicate(_, o.child.output, ea))
        fplan.Selection(o, transform(o.child, ea ++ opExtra))
      case o: jplan.Unwind =>
        fplan.Unwind(o, transform(o.child, ea ++ extractAttributes(o.unwindAttribute.list)))
      case o: jplan.SortAndTop =>
        val opExtra = o.order.flatMap(x => extractAttributes(x.child)).filter(!duplicate(_, o.child.output, ea))
        fplan.SortAndTop (o, transform(o.child, ea ++ opExtra))

      // the rest is just the same, isn't it?
      case o: jplan.AllDifferent         => fplan.AllDifferent        (o, transform(o.child, ea))
      case o: jplan.DuplicateElimination => fplan.DuplicateElimination(o, transform(o.child, ea))
      case o: jplan.Production           => fplan.Production          (o, transform(o.child, ea))
      // unary DMLs
      case o: jplan.Create               =>
        val opExtra = extractAttributesFromInsertion(o.attribute)
        fplan.Create(o, transform(o.child, ea ++ opExtra))
      case o: jplan.Delete               => fplan.Delete(o, transform(o.child, ea))
      case o: jplan.Merge                =>
        fplan.Merge(o, transform(o.child, ea))
      case o: jplan.Remove               => fplan.Remove(o, transform(o.child, ea))
      case o: jplan.SetNode              => fplan.SetNode(o, transform(o.child, ea))

      // binary
      case j: jplan.JoinLike => {
        // ThetaLeftOuterJoins require special treatment: they are the only join operators that can introduce new extra variables
        val opExtra = j match {
          case o: jplan.ThetaLeftOuterJoin => extractAttributes(o.condition).filter(!duplicate(_, o.left.output ++ o.right.output, ea))
          case _ => Seq()
        }

        val eaTotal = ea ++ opExtra

        val eaLeft = propagate(eaTotal, j.left.output)
        val eaRight = propagate(eaTotal, j.right.output).filter(x => !eaLeft.map(_.resolvedName).contains(x.resolvedName))

        val left = transform(j.left, eaLeft)
        val right = transform(j.right, eaRight)

        j match {
          case o: jplan.AntiJoin => {
            assert(eaRight.isEmpty)
            fplan.AntiJoin(o, left, right)
          }
          case o: jplan.Join => fplan.Join(o, left, right)
          case o: jplan.LeftOuterJoin => fplan.LeftOuterJoin(o, left, right)
          case o: jplan.ThetaLeftOuterJoin =>
            val opExtra = extractAttributes(o.condition).filter(!duplicate(_, o.left.output ++ o.right.output, eaTotal))
            fplan.ThetaLeftOuterJoin(o, left, right)
          case o: jplan.TransitiveJoin => fplan.TransitiveJoin(o, left, right)
        }
      }
      case o: jplan.Union => fplan.Union(o,
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
  }

  def extractAttributes(expression: Expression): Seq[ResolvableName] = {
    (expression match {
      case a: ReturnItem => extractAttributes(a.child)
      case a: PropertyAttribute => Seq(a)
      case _ => Seq()
    }) ++ expression.children.flatMap(a => extractAttributes(a))
  }

  def extractAttributes(projectList: TProjectList): Seq[ResolvableName] = {
    projectList.flatMap(extractAttributes(_))
  }

  def extractAttributesFromInsertion(attribute: ResolvableName): Seq[ResolvableName] = {
    attribute match {
      case a: VertexAttribute => extractAttributes(a)
      case RichEdgeAttribute(src, trg, edge, _) => Seq(src, trg, edge).flatMap(extractAttributes)
      case _ => Seq()
    }
  }

}
