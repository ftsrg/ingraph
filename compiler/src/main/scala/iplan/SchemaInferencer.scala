package iplan

import org.apache.spark.sql.catalyst.expressions.{Attribute, Expression, NamedExpression}

object SchemaInferencer {
  def transform(plan: INode, eap: Seq[Attribute] = Seq()): INode = {
    plan match {
      // leaf
      case o: iplan.GetEdges => o.copy(extraAttributes = eap)
      case o: iplan.GetVertices => o.copy(extraAttributes = eap)

      // unary
      case o: iplan.AllDifferent => o.copy(
        child = transform(o.child),
        extraAttributes = eap
      )
      case o: iplan.DuplicateElimination => o.copy(
        child = transform(o.child),
        extraAttributes = eap
      )
      case o: iplan.Projection => o.copy(
        child = transform(o.child, eap ++ extractAttributes(o.projectList)),
        extraAttributes = eap
      )
      case o: iplan.Selection => {
        o.copy(
          child = transform(o.child, eap ++ extractAttributes(o.condition)),
          extraAttributes = eap
        )
      }
      case o: iplan.SortAndTop => o.copy(
        child = transform(o.child),
        extraAttributes = eap
      )

      // binary
      case o: iplan.Join => ???

      case o: iplan.Union => o.copy(
        left = transform(o.left),
        right = transform(o.right),
        extraAttributes = eap
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

}
