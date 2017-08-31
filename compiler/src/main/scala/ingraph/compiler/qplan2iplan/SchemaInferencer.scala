package ingraph.compiler.qplan2iplan

import ingraph.model.expr.PropertyAttribute
import ingraph.model.iplan
import ingraph.model.iplan.INode
import org.apache.spark.sql.catalyst.expressions.{Attribute, Expression, NamedExpression}

object SchemaInferencer {
  def transform(plan: INode, ea: Seq[NamedExpression] = Seq()): INode = {
    val ead = ea.distinct

    plan match {
      // leaf
      case o: iplan.GetEdges => o.copy(extraAttributes = ead)
      case o: iplan.GetVertices => o.copy(extraAttributes = ead)

      // unary
      case o: iplan.AllDifferent => o.copy(
        child = transform(o.child, ead),
        extraAttributes = ead
      )
      case o: iplan.DuplicateElimination => o.copy(
        child = transform(o.child, ead),
        extraAttributes = ead
      )
      case o: iplan.Production => o.copy(
        child = transform(o.child, ead),
        extraAttributes = ead
      )
      case o: iplan.Projection => o.copy(
        child = transform(o.child, ead ++ extractAttributes(o.projectList)),
        extraAttributes = ead
      )
      case o: iplan.Selection => {
        o.copy(
          child = transform(o.child, ead ++ extractAttributes(o.condition)),
          extraAttributes = ead
        )
      }
      case o: iplan.SortAndTop => o.copy(
        child = transform(o.child, ead),
        extraAttributes = ead
      )

      // binary
      case o: iplan.AntiJoin => o.copy(
          left = transform(o.left, ead),
          right = transform(o.right, Seq()),
          extraAttributes = ead
      )

      case o: iplan.Join => {
        val eaLeft = propagate(ead, o.left.output)
        val eaRight = propagate(ead, o.right.output).filter(!eaLeft.contains(_))

        o.copy(
          left = transform(o.left, eaLeft),
          right = transform(o.right, eaRight),
          extraAttributes = ead
        )
      }
      // TODO https://github.com/FTSRG/ingraph/blob/master/ingraph-compiler/ingraph-compiler-relalg-model-util/src/main/ingraph_xtend/relalg/calculators/JoinSchemaCalculator.xtend

      case o: iplan.Union => o.copy(
        left = transform(o.left, ead),
        right = transform(o.right, ead),
        extraAttributes = ead
      )
    }
  }

  def extractAttributes(expression: Expression): Seq[Attribute] = {
    (expression match {
      case a: Attribute => Seq(a)
      case _ => Seq()
    }) ++ expression.children.flatMap(extractAttributes(_))
  }

  def extractAttributes(projectList: Seq[NamedExpression]): Seq[Attribute] = {
    projectList.flatMap(extractAttributes(_))
  }

  def schemaToMapNames(op: INode): Map[String, Int] = {
    op.internalSchema.zipWithIndex.map(f => f._1.toString() -> f._2).toMap
  }

  /**
    * propagate property attributes to wherever their element is
    */
  def propagate(extraAttributes: Seq[NamedExpression], inputSchema: Seq[NamedExpression]): Seq[NamedExpression] = {
    extraAttributes
      .flatMap {case a: PropertyAttribute => Some(a)}
      .filter(a => inputSchema.contains(a.elementAttribute))

    // !inputSchema.contains(a) // do we need this?
  }

}
