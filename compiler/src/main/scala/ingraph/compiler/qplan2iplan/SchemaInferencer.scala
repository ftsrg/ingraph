package ingraph.compiler.qplan2iplan

import ingraph.model.eplan._
import ingraph.model.expr.PropertyAttribute
import ingraph.model.iplan
import ingraph.model.eplan
import ingraph.model.iplan.INode
import org.apache.spark.sql.catalyst.expressions.{Attribute, Expression, NamedExpression}

object SchemaInferencer {

  def transform(inode: INode, extraAttributes: Seq[NamedExpression] = Seq()): ENode = {
    val ea = extraAttributes.distinct

    inode match {
      // leaf
      case o: iplan.GetEdges => eplan.GetEdges(ea, o)
      case o: iplan.GetVertices => eplan.GetVertices(ea, o)

      // unary
      case o: iplan.Projection =>
        val newExtra = extractAttributes(o.projectList).filter(a => !o.child.output.map(_.name).contains(a.name) && !ea.contains(a.name))
        eplan.Projection(ea, o, transform(o.child, ea ++ newExtra))
      case o: iplan.Selection =>
        val newExtra = extractAttributes(o.condition).filter(a => !o.child.output.map(_.name).contains(a.name) && !ea.contains(a.name))
        eplan.Selection(ea, o, transform(o.child, ea ++ newExtra))
      // the rest is just the same, isn't it?
      case o: iplan.AllDifferent         => eplan.AllDifferent        (ea, o, transform(o.child, ea))
      case o: iplan.DuplicateElimination => eplan.DuplicateElimination(ea, o, transform(o.child, ea))
      case o: iplan.Production           => eplan.Production          (ea, o, transform(o.child, ea))
      case o: iplan.SortAndTop           => eplan.SortAndTop          (ea, o, transform(o.child, ea))

      // binary
      case o: iplan.AntiJoin => eplan.AntiJoin(
          ea, o,
          transform(o.left, ea),
          transform(o.right, Seq())
        )
      case j: iplan.EquiJoinLike => {
        val eaLeft = propagate(ea, j.left.output)
        val eaRight = propagate(ea, j.right.output).filter(!eaLeft.contains(_))
        val left = transform(j.left, eaLeft)
        val right = transform(j.right, eaRight)

        j match {
          case o: iplan.Join => eplan.Join(ea, o, left, right)
          case o: iplan.LeftOuterJoin => eplan.LeftOuterJoin(ea, o, left, right)
          case o: iplan.ThetaLeftOuterJoin => eplan.ThetaLeftOuterJoin(ea, o, left, right)
        }
      }
      case o: iplan.Union => eplan.Union(ea, o,
        transform(o.left, ea),
        transform(o.right, ea)
      )
    }
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
