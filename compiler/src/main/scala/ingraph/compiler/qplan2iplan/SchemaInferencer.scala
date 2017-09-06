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
      case o: iplan.GetEdges      => eplan.LeafENode(ea, o)
      case o: iplan.GetVertices   => eplan.LeafENode(ea, o)

      // unary
      case o: iplan.Projection => UnaryENode(ea, o, transform(o.child, ea ++ extractAttributes(o.projectList)))
      case o: iplan.Selection  => UnaryENode(ea, o, transform(o.child, ea ++ extractAttributes(o.condition)))
      case o: iplan.UnaryINode => UnaryENode(ea, o, transform(o.child, ea))

      // binary
      case o: iplan.AntiJoin => JoinLikeENode(ea, o,
          transform(o.left, ea),
          transform(o.right, Seq())
      )
      case o: iplan.Join => {
        val eaLeft = propagate(ea, o.left.output)
        val eaRight = propagate(ea, o.right.output).filter(!eaLeft.contains(_))
        JoinLikeENode(ea, o,
          transform(o.left, eaLeft),
          transform(o.right, eaRight)
        )
      }
      case o: iplan.Union => UnionENode(ea, o,
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
