package ingraph.model.iplan

import ingraph.model.expr
import ingraph.model.expr.EdgeAttribute
import org.apache.spark.sql.catalyst.expressions.{Attribute, Expression, NamedExpression, SortOrder}
import org.apache.spark.sql.catalyst.plans.logical._

trait INode extends LogicalPlan {
  def extraAttributes: Seq[NamedExpression]
  def internalSchema: Seq[NamedExpression]
}

abstract class LeafINode extends LeafNode with INode {
  override def internalSchema: Seq[NamedExpression] = output ++ extraAttributes
}

abstract class UnaryINode extends UnaryNode with INode {
  override def child: INode
  override def output: Seq[Attribute] = child.output
  override def internalSchema: Seq[NamedExpression] = child.internalSchema
}

abstract class BinaryINode extends BinaryNode with INode {
  override def left: INode
  override def right: INode
}

// leaf nodes
case class GetVertices(v: expr.VertexAttribute,
                       override val extraAttributes: Seq[NamedExpression] = Seq()) extends LeafINode {
  override def output: Seq[Attribute] = Seq(v)
}

case class GetEdges(src: expr.VertexAttribute,
                    trg: expr.VertexAttribute,
                    edge: expr.EdgeAttribute,
                    dir: expr.Direction,
                    override val extraAttributes: Seq[NamedExpression] = Seq())
  extends LeafINode with expr.NavigationDescriptor {
  override def output = Seq(src, trg, edge)
}

case class Dual() extends LeafINode {
  override def output = Seq()
  override def extraAttributes: Seq[NamedExpression] = Seq()
}

// unary nodes
case class AllDifferent(child: INode,
                        edges: Seq[EdgeAttribute],
                        override val extraAttributes: Seq[NamedExpression] = Seq()) extends UnaryINode {}

case class DuplicateElimination(child: INode,
                                override val extraAttributes: Seq[NamedExpression] = Seq()) extends UnaryINode {}

case class Production(child: INode,
                      override val extraAttributes: Seq[NamedExpression] = Seq()) extends UnaryINode {}

case class Projection(projectList: Seq[NamedExpression],
                      child: INode,
                      override val extraAttributes: Seq[NamedExpression] = Seq()) extends UnaryINode {
  override def internalSchema: Seq[NamedExpression] = projectList
}

case class Selection(condition: Expression,
                     child: INode,
                     override val extraAttributes: Seq[NamedExpression] = Seq()) extends UnaryINode {}

case class SortAndTop(skipExpr: Expression,
                      limitExpr: Expression,
                      order: Seq[SortOrder],
                      child: INode,
                      override val extraAttributes: Seq[NamedExpression] = Seq()) extends UnaryINode {}

// binary nodes
case class Union(left: INode,
                 right: INode,
                 override val extraAttributes: Seq[NamedExpression] = Seq()) extends BinaryINode {
  override def output: Seq[Attribute] = left.output
  override def internalSchema: Seq[NamedExpression] = left.asInstanceOf[INode].internalSchema
}

case class AntiJoin(left: INode,
                right: INode,
                override val extraAttributes: Seq[NamedExpression] = Seq()) extends BinaryINode {
  override def output: Seq[Attribute] = left.output ++ right.output // minus common attributes
  override def internalSchema: Seq[NamedExpression] =
    left.internalSchema ++ right.internalSchema
}

case class Join(left: INode,
                right: INode,
                override val extraAttributes: Seq[NamedExpression] = Seq()) extends BinaryINode {
  override def output: Seq[Attribute] = left.output ++ right.output // minus common attributes
  override def internalSchema: Seq[NamedExpression] =
    left.internalSchema ++ right.internalSchema
}

case class LeftOuterJoin(left: INode,
                              right: INode,
                              override val extraAttributes: Seq[NamedExpression] = Seq()) extends BinaryINode {
  override def output: Seq[Attribute] = left.output ++ right.output // minus common attributes
  override def internalSchema: Seq[NamedExpression] =
    left.internalSchema ++ right.internalSchema
}

case class ThetaLeftOuterJoin(left: INode,
                right: INode,
                condition: Expression,
                override val extraAttributes: Seq[NamedExpression] = Seq()) extends BinaryINode {
  override def output: Seq[Attribute] = left.output ++ right.output // minus common attributes
  override def internalSchema: Seq[NamedExpression] =
    left.internalSchema ++ right.internalSchema
}
