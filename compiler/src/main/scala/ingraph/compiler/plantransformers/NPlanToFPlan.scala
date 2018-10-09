package ingraph.compiler.plantransformers

import ingraph.model.expr._
import ingraph.model.expr.types.TProjectList
import ingraph.model.{fplan, nplan}
import org.apache.spark.sql.catalyst.expressions.Expression

/**
  * Performs schema inferencing to transform a NPlan to an FPlan.
  */
object NPlanToFPlan {

  def transform(nnode: nplan.NNode): fplan.FNode = {
    transform(nnode, Seq())
  }

  private def transform(getEdges: nplan.GetEdges, req: Seq[ResolvableName]): fplan.GetEdges = fplan.GetEdges(req, getEdges)

  private def transform(nnode: nplan.NNode, req: Seq[ResolvableName]): fplan.FNode = {

    /**
      * Return whether a property selected for propagation is a duplicate:
      * (1) it is in the input operator's/operators' output, or
      * (2) it is already part of the required properties
      */
    def duplicate(attribute: ResolvableName, inputOperatorOutput: Seq[ResolvableName], requiredProperties: Seq[ResolvableName]) = {
      inputOperatorOutput.map(_.resolvedName).contains(attribute.resolvedName) || requiredProperties.map(_.resolvedName).contains(attribute.resolvedName)
    }

    nnode match {
      // leaf
      case o: nplan.GetEdges     => transform(o, req)
      case o: nplan.GetVertices  => fplan.GetVertices(req, o)
      case o: nplan.Dual         =>
        if (req.nonEmpty) {
          throw new IllegalStateException(s"Dual node cannot provide properties: ${req}")
        }
        fplan.Dual(o)

      // unary
      case o: nplan.Projection =>
        val reqOp = extractProperties(o.projectList).filter(!duplicate(_, o.child.output, req))
        fplan.Projection(req, o, transform(o.child, req ++ reqOp))
      case o: nplan.Grouping =>
        val reqOp = extractProperties(o.projectList).filter(!duplicate(_, o.child.output, req))
        fplan.Grouping(req, o, transform(o.child, req ++ reqOp))
      case o: nplan.Selection =>
        val reqOp = extractProperties(o.condition).filter(!duplicate(_, o.child.output, req))
        fplan.Selection(o, transform(o.child, req ++ reqOp))
      case o: nplan.Unwind =>
        fplan.Unwind(o, transform(o.child, req ++ extractProperties(o.unwindAttribute.list)))
      case o: nplan.SortAndTop =>
        val reqOp = o.order.flatMap(x => extractProperties(x.child)).filter(!duplicate(_, o.child.output, req))
        fplan.SortAndTop (o, transform(o.child, req ++ reqOp))

      // the rest is just the same, isn't it?
      case o: nplan.AllDifferent         => fplan.AllDifferent        (o, transform(o.child, req))
      case o: nplan.DuplicateElimination => fplan.DuplicateElimination(o, transform(o.child, req))
      case o: nplan.Production           => fplan.Production          (o, transform(o.child, req))
      // unary DMLs
      case o: nplan.Create               =>
        val reqOp = extractPropertiesFromInsertion(o.attribute).filter(!duplicate(_, o.child.output, req))
        fplan.Create(o, transform(o.child, req ++ reqOp))
      case o: nplan.Delete               => fplan.Delete(o, transform(o.child, req))
      case o: nplan.Merge                =>
        fplan.Merge(o, transform(o.child, req))
      case o: nplan.Remove               => fplan.Remove(o, transform(o.child, req))
      case o: nplan.SetNode              => fplan.SetNode(o, transform(o.child, req))

      // binary
      case j: nplan.JoinLike => {
        // ThetaLeftOuterJoins require special treatment: they are the only join operators that can require new properties
        val reqOp = j match {
          case o: nplan.ThetaLeftOuterJoin => extractProperties(o.condition).filter(!duplicate(_, o.left.output ++ o.right.output, req))
          case _ => Seq()
        }

        val rpTotal = req ++ reqOp

        val (rpLeft, rpRight) = j.inputPreference match {
          case Left() =>
            val rpLeft  = propagate(rpTotal, j.left.output)
            val rpRight = propagate(rpTotal, j.right.output).filter(x => !rpLeft.map(_.resolvedName).contains(x.resolvedName))
            (rpLeft, rpRight)
          case Right() =>
            val rpRight = propagate(rpTotal, j.right.output)
            val rpLeft  = propagate(rpTotal, j.left.output).filter(x => !rpRight.map(_.resolvedName).contains(x.resolvedName))
            (rpLeft, rpRight)
        }

        val left = transform(j.left, rpLeft)
        val right = transform(j.right, rpRight)

        assert(
          rpTotal.length == rpLeft.length + rpRight.length,
          "Some properties were not propagated to either side of the join-like operator"
        )

        j match {
          case o: nplan.AntiJoin => {
            assert(rpRight.isEmpty)
            fplan.AntiJoin(o, left, right)
          }
          case o: nplan.Join => fplan.Join(o, left, right)
          case o: nplan.LeftOuterJoin => fplan.LeftOuterJoin(o, left, right)
          case o: nplan.ThetaLeftOuterJoin =>
            val reqOp = extractProperties(o.condition).filter(!duplicate(_, o.left.output ++ o.right.output, rpTotal))
            fplan.ThetaLeftOuterJoin(o, left, right)
          case o: nplan.TransitiveJoin => fplan.TransitiveJoin(o, left, transform(o.right, rpRight)) // to keep the type of the right node
        }
      }
      case o: nplan.Union => fplan.Union(o,
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
      case FunctionInvocation(
        ingraph.model.misc.Function.NODE_HAS_LABELS,
        List(VertexAttribute(name, _, _, _, resolvedName), VertexLabelSet(labels, _)),
        _
      ) => Seq(NodeHasLabelsAttribute(s"${name}_has_label", labels))
      case a: ReturnItem => extractProperties(a.child)
      case a: PropertyAttribute => Seq(a)
      case _ => Seq()
    }) ++ expression.children.flatMap(extractProperties).distinct
  }

  def extractProperties(projectList: TProjectList): Seq[ResolvableName] = {
    projectList.flatMap(extractProperties).distinct
  }

  def extractPropertiesFromInsertion(attribute: ResolvableName): Seq[ResolvableName] = {
    attribute match {
      case e: ElementAttribute => e.properties.flatMap(p => extractProperties(p._2)).toSeq
      case RichEdgeAttribute(src, trg, edge, _) => Seq(src, trg, edge).flatMap(extractProperties)
      case _ => Seq()
    }
  }

}
