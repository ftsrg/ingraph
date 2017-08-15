package ingraph.model.iplan

import ingraph.model.expr
import org.apache.spark.sql.catalyst.expressions.{Attribute, Expression, NamedExpression, SortOrder}
import org.apache.spark.sql.catalyst.plans.logical._

trait INode extends LogicalPlan {
  def extraAttributes: Seq[Attribute]
  def internalSchema: Seq[Attribute]
}

abstract class LeafINode extends LeafNode with INode {
  override def internalSchema: Seq[Attribute] = output ++ extraAttributes
}

abstract class UnaryINode extends UnaryNode with INode {
  override def child: INode
  override def output: Seq[Attribute] = child.output
  override def internalSchema: Seq[Attribute] = child.internalSchema
}

abstract class BinaryINode extends BinaryNode with INode {
  override def left: INode
  override def right: INode
}

// leaf nodes
case class GetVertices(v: expr.VertexAttribute,
                       override val extraAttributes: Seq[Attribute] = Seq()) extends LeafINode {
  override def output: Seq[Attribute] = Seq()
}

case class GetEdges(src: expr.VertexAttribute,
                    trg: expr.VertexAttribute,
                    edge: expr.EdgeAttribute,
                    dir: expr.Direction,
                    override val extraAttributes: Seq[Attribute] = Seq())
  extends LeafINode with expr.NavigationDescriptor {
  override def output = Seq(src, trg, edge)
}

// unary nodes
case class AllDifferent(child: INode,
                        override val extraAttributes: Seq[Attribute] = Seq()) extends UnaryINode {}

case class DuplicateElimination(child: INode,
                                override val extraAttributes: Seq[Attribute] = Seq()) extends UnaryINode {}

case class Projection(projectList: Seq[NamedExpression],
                      child: INode,
                      override val extraAttributes: Seq[Attribute] = Seq()) extends UnaryINode {}

case class Selection(condition: Expression,
                     child: INode,
                     override val extraAttributes: Seq[Attribute] = Seq()) extends UnaryINode {}

case class SortAndTop(skipExpr: Expression,
                      limitExpr: Expression,
                      order: Seq[SortOrder],
                      child: INode,
                      override val extraAttributes: Seq[Attribute] = Seq()) extends UnaryINode {}

// binary nodes
case class Union(left: INode,
                 right: INode,
                 override val extraAttributes: Seq[Attribute] = Seq()) extends BinaryINode {
  override def output: Seq[Attribute] = left.output
  override def internalSchema: Seq[Attribute] = left.asInstanceOf[INode].internalSchema
}

case class Join(left: INode,
                right: INode,
                override val extraAttributes: Seq[Attribute] = Seq()) extends BinaryINode {
  override def output: Seq[Attribute] = left.output ++ right.output // minus common attributes
  override def internalSchema: Seq[Attribute] =
    left.internalSchema ++ right.internalSchema
}
