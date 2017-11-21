package ingraph.model.jplan

import ingraph.model.expr
import ingraph.model.expr.types.TProjectList
import ingraph.model.expr.{EdgeAttribute, ProjectionDescriptor, VertexLabelUpdate}
import ingraph.model.treenodes._
import org.apache.spark.sql.catalyst.expressions.{Attribute, Expression, NamedExpression, SortOrder}
import org.apache.spark.sql.catalyst.plans.logical._

// abstract classes and traits
trait JNode extends LogicalPlan {
  override def children: Seq[JNode]
}

abstract class LeafJNode extends GenericLeafNode[JNode] with JNode {}
abstract class UnaryJNode extends GenericUnaryNode[JNode] with JNode {
  override def output: Seq[Attribute] = child.output
}
abstract class BinaryJNode extends GenericBinaryNode[JNode] with JNode {}

trait JoinLike extends BinaryJNode {
  def common: Seq[Attribute] = left.output.filter(right.output.contains(_))
}
trait EquiJoinLike extends JoinLike {
  override def output: Seq[Attribute] = left.output ++ right.output.filter(left.output.contains(_))
}

// leaf nodes
case class GetVertices(v: expr.VertexAttribute) extends LeafJNode {
  override def output: Seq[Attribute] = Seq(v)
}

case class GetEdges(src: expr.VertexAttribute,
                    trg: expr.VertexAttribute,
                    edge: expr.EdgeAttribute,
                    dir: expr.Direction)
  extends LeafJNode with expr.NavigationDescriptor {
  override def output = Seq(src, edge, trg)
}

case class GetEdgeLists(src: expr.VertexAttribute,
                    trg: expr.VertexAttribute,
                    edge: expr.EdgeListAttribute,
                    dir: expr.Direction)
  extends LeafJNode with expr.NavigationDescriptor {
  override def output = Seq(src, edge, trg)
}

case class Dual() extends LeafJNode {
  override def output = Seq()
}

// unary nodes
case class AllDifferent(edges: Seq[EdgeAttribute],
                        child: JNode) extends UnaryJNode {}

case class DuplicateElimination(child: JNode) extends UnaryJNode {}

case class Production(child: JNode) extends UnaryJNode {}

abstract class AbstractProjection(projectList: TProjectList, child: JNode) extends UnaryJNode with ProjectionDescriptor {
  override def output = projectOutput
}

case class UnresolvedProjection(override val projectList: TProjectList, override val child: JNode) extends AbstractProjection(projectList, child)
case class Projection(override val projectList: TProjectList, override val child: JNode) extends AbstractProjection(projectList, child)

case class Grouping(aggregationCriteria: Seq[Expression], projectList: TProjectList, child: JNode) extends UnaryJNode with ProjectionDescriptor {
  override def output = projectOutput
}

case class Selection(condition: Expression,
                     child: JNode) extends UnaryJNode {}

case class Unwind(collection: Expression, element: Attribute, child: JNode) extends UnaryJNode {
  override def output = Seq() // child.output.updated(child.output.indexOf(element), element)
  // TODO indexOf might be unable to find the attribute
}

case class SortAndTop(skipExpr: Expression,
                      limitExpr: Expression,
                      order: Seq[SortOrder],
                      child: JNode) extends UnaryJNode {}

// binary nodes
case class Union(bag: Boolean,
                 left: JNode,
                 right: JNode) extends BinaryJNode {
  override def output: Seq[Attribute] = left.output
}

case class AntiJoin(left: JNode, right: JNode) extends BinaryJNode with JoinLike {
  override def output: Seq[Attribute] = left.output
}

case class Join(left: JNode, right: JNode) extends BinaryJNode with EquiJoinLike {}

case class LeftOuterJoin(left: JNode, right: JNode) extends BinaryJNode with EquiJoinLike {}

case class ThetaLeftOuterJoin(
                               left: JNode,
                               right: JNode,
                               condition: Expression) extends BinaryJNode with EquiJoinLike {
}

// DML operators
abstract class CudOperator(child: JNode) extends UnaryJNode {}

case class Create(attributes: Seq[Attribute], child: JNode) extends CudOperator(child) {}

case class Delete(attributes: Seq[Attribute], detach: Boolean, child: JNode) extends CudOperator(child) {}

case class Merge(attributes: Seq[Attribute], child: JNode) extends CudOperator(child) {}

case class SetNode(vertexLabelUpdates: Set[VertexLabelUpdate], child: JNode) extends CudOperator(child) {}

case class Remove(vertexLabelUpdates: Set[VertexLabelUpdate], child: JNode) extends CudOperator(child) {}
