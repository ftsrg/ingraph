package ingraph.model.jplan

import ingraph.model.expr._
import ingraph.model.expr.types.TProjectList
import ingraph.model.treenodes._
import org.apache.spark.sql.catalyst.expressions.{Attribute, Expression, SortOrder}
import org.apache.spark.sql.catalyst.plans.logical._

trait JNode extends LogicalPlan {
  override def children: Seq[JNode]
  override def output: Seq[ResolvableName]
}

abstract class LeafJNode extends GenericLeafNode[JNode] with JNode {}
abstract class UnaryJNode extends GenericUnaryNode[JNode] with JNode {
  override def output: Seq[ResolvableName] = child.output
}
abstract class BinaryJNode extends GenericBinaryNode[JNode] with JNode {}

trait JoinLike extends BinaryJNode {
  def common: Seq[ResolvableName] = left.output.filter(right.output.contains(_))
}
trait EquiJoinLike extends JoinLike {
  override def output: Seq[ResolvableName] = left.output ++ right.output.filter(left.output.contains(_))
}

// leaf nodes
case class GetVertices(v: VertexAttribute) extends LeafJNode {
  override def output: Seq[ResolvableName] = Seq(v)
}

case class GetEdges(src: VertexAttribute,
                    trg: VertexAttribute,
                    edge: EdgeAttribute,
                    directed: Boolean)
  extends LeafJNode {
  override def output = Seq(src, edge, trg)
}

case class GetEdgeLists(src: VertexAttribute,
                    trg: VertexAttribute,
                    edge: EdgeListAttribute,
                    directed: Boolean)
  extends LeafJNode {
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
  override def output = projectList
}

case class UnresolvedProjection(override val projectList: TProjectList, override val child: JNode) extends AbstractProjection(projectList, child)
case class Projection(override val projectList: TProjectList, override val child: JNode) extends AbstractProjection(projectList, child)

case class Grouping(aggregationCriteria: Seq[Expression], projectList: TProjectList, child: JNode) extends UnaryJNode with ProjectionDescriptor {
  override def output = projectList
}

case class Selection(condition: Expression,
                     child: JNode) extends UnaryJNode {}

case class Unwind(unwindAttribute: UnwindAttribute, child: JNode) extends UnaryJNode {
  override def output = child.output ++ Seq(unwindAttribute) // child.output.updated(child.output.indexOf(element), element)
  // TODO indexOf might be unable to find the attribute
}

case class SortAndTop(skipExpr: Option[Expression],
                      limitExpr: Option[Expression],
                      order: Seq[SortOrder],
                      child: JNode) extends UnaryJNode {}

// binary nodes
case class Union(bag: Boolean,
                 left: JNode,
                 right: JNode) extends BinaryJNode {
  override def output: Seq[ResolvableName] = left.output
}

case class AntiJoin(left: JNode, right: JNode) extends BinaryJNode with JoinLike {
  override def output: Seq[ResolvableName] = left.output
}

case class Join(left: JNode, right: JNode) extends BinaryJNode with EquiJoinLike {}

case class LeftOuterJoin(left: JNode, right: JNode) extends BinaryJNode with EquiJoinLike {}

case class ThetaLeftOuterJoin(left: JNode,
                              right: JNode,
                              condition: Expression) extends BinaryJNode with EquiJoinLike {
}

// DML operators
abstract class CudOperator(child: JNode) extends UnaryJNode {}

case class Create(attribute: ResolvableName, child: JNode) extends CudOperator(child) {}

case class Delete(attributes: Seq[ResolvableName], detach: Boolean, child: JNode) extends CudOperator(child) {}

case class Merge(attributes: ResolvableName, child: JNode) extends CudOperator(child) {}

case class SetNode(vertexLabelUpdates: Set[VertexLabelUpdate], child: JNode) extends CudOperator(child) {}

case class Remove(vertexLabelUpdates: Set[VertexLabelUpdate], child: JNode) extends CudOperator(child) {}
