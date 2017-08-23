package ingraph.compiler.qplan2iplan

import ingraph.model.iplan
import ingraph.model.iplan.INode

import org.apache.spark.sql.catalyst.expressions.{Attribute, Expression, NamedExpression}

object SchemaInferencer {
  def transform(plan: INode, ea: Seq[Attribute] = Seq()): INode = {
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
      case o: iplan.Join => ???

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

  def schemaToMapNames(op: INode): Map[String, Int] =  {
    op.internalSchema.zipWithIndex.map(f => f._1.toString() -> f._2).toMap
  }

}
