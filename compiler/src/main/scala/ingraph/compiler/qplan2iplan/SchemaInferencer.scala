package ingraph.compiler.qplan2iplan

import ingraph.model.iplan
import ingraph.model.iplan.INode

import org.apache.spark.sql.catalyst.expressions.{Attribute, Expression, NamedExpression}

object SchemaInferencer {
  def transform(plan: INode, ea: Seq[Attribute] = Seq()): INode = {
    plan match {
      // leaf
      case o: iplan.GetEdges => o.copy(extraAttributes = ea)
      case o: iplan.GetVertices => {
        println("Y>" + ea)
        val x = o.copy(extraAttributes = ea)
        println("Z>" + x.extraAttributes)
        x
      }

      // unary
      case o: iplan.AllDifferent => o.copy(
        child = transform(o.child, ea),
        extraAttributes = ea
      )
      case o: iplan.DuplicateElimination => o.copy(
        child = transform(o.child, ea),
        extraAttributes = ea
      )
      case o: iplan.Production => o.copy(
        child = transform(o.child, ea),
        extraAttributes = ea
      )
      case o: iplan.Projection => o.copy(
        child = transform(o.child, ea ++ extractAttributes(o.projectList)),
        extraAttributes = ea
      )
      case o: iplan.Selection => {
        o.copy(
          child = transform(o.child, ea ++ extractAttributes(o.condition)),
          extraAttributes = ea
        )
      }
      case o: iplan.SortAndTop => o.copy(
        child = transform(o.child, ea),
        extraAttributes = ea
      )

      // binary
      case o: iplan.Join => ???

      case o: iplan.Union => o.copy(
        left = transform(o.left, ea),
        right = transform(o.right, ea),
        extraAttributes = ea
      )
    }
  }

  def extractAttributes(expression: Expression): Seq[Attribute] = {
    val x = (expression match {
      case a: Attribute => Seq(a)
      case _ => Seq()
    }) ++ expression.children.flatMap(extractAttributes(_))
    println("X> " + x)
    x
  }

  def extractAttributes(projectList: Seq[NamedExpression]): Seq[Attribute] = {
    projectList.flatMap(extractAttributes(_))
  }

  def schemaToMapNames(op: INode): Map[String, Int] =  {
    println(op.internalSchema)
    op.internalSchema.zipWithIndex.map(f => f._1.toString() -> f._2).toMap
  }

}
