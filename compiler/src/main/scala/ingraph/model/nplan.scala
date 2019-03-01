package ingraph.model.nplan

import ingraph.model.expr._
import ingraph.model.expr.types.TProjectList
import ingraph.model.plan._
import ingraph.model.treenodes._
import org.apache.spark.sql.catalyst.expressions.{Expression, SortOrder}
import org.apache.spark.sql.catalyst.plans.logical._

trait NNode extends LogicalPlan {
  override def children: Seq[NNode]
  override def output: Seq[ResolvableName]
}

abstract class LeafNNode extends GenericLeafNode[NNode] with NNode {}
abstract class UnaryNNode extends GenericUnaryNode[NNode] with NNode {
  override def output: Seq[ResolvableName] = child.output
}
abstract class BinaryNNode extends GenericBinaryNode[NNode] with NNode {}

trait JoinLike extends BinaryNNode with TJoinLike {
  def commonAttributes: Seq[ResolvableName] = left.output.filter(x => right.output.map(_.resolvedName).contains(x.resolvedName))

  /**
    * @return input side preference, used to determine which input node (left/right) should be preferred when
    *         propagating the "required properties" by the schema inferencer of {@see NPlanToFPlan}
    */
  def inputPreference: Side = Left()
}
trait EquiJoinLike extends JoinLike {
  override def output: Seq[ResolvableName] = left.output ++ right.output.filter(x => !left.output.map(_.resolvedName).contains(x.resolvedName))
}

// leaf nodes
case class GetVertices(v: VertexAttribute) extends LeafNNode with TGetVertices {
  override def output: Seq[ResolvableName] = Seq(v)
}

case class GetEdges(src: VertexAttribute,
                    trg: VertexAttribute,
                    edge: EdgeAttribute,
                    directed: Boolean)
  extends LeafNNode with TGetEdges {
  override def output = Seq(src, edge, trg)
}

case class Dual() extends LeafNNode with TDual {
  override def output = Seq()
}

// unary nodes
case class AllDifferent(edges: Seq[AbstractEdgeAttribute],
                        child: NNode) extends UnaryNNode with TAllDifferent {}

case class DuplicateElimination(child: NNode) extends UnaryNNode with TDuplicateElimination {}

case class Production(child: NNode) extends UnaryNNode with TProduction {}

abstract class AbstractProjection(projectList: TProjectList, child: NNode) extends UnaryNNode with ProjectionDescriptor with TAbstractProjection {
  override def output = projectList
}

case class UnresolvedProjection(override val projectList: TProjectList, override val child: NNode) extends AbstractProjection(projectList, child)
case class Projection(override val projectList: TProjectList, override val child: NNode) extends AbstractProjection(projectList, child)

case class Grouping(aggregationCriteria: Seq[Expression], projectList: TProjectList, child: NNode) extends UnaryNNode with ProjectionDescriptor with TGrouping {
  override def output = projectList
}

case class Selection(condition: Expression,
                     child: NNode) extends UnaryNNode with TSelection {}

case class Unwind(unwindAttribute: UnwindAttribute, child: NNode) extends UnaryNNode with TUnwind {
  override def output = child.output ++ Seq(unwindAttribute)
}

case class SortAndTop(skipExpr: Option[Expression],
                      limitExpr: Option[Expression],
                      order: Seq[SortOrder],
                      child: NNode) extends UnaryNNode with TSortAndTop {}

// binary nodes
case class Union(bag: Boolean,
                 left: NNode,
                 right: NNode) extends BinaryNNode with TUnion {
  override def output: Seq[ResolvableName] = left.output
}

case class AntiJoin(left: NNode, right: NNode) extends BinaryNNode with JoinLike with TAntiJoin {
  override def output: Seq[ResolvableName] = left.output
}

case class Join(left: NNode, right: NNode, override val inputPreference: Side = Left()) extends BinaryNNode with EquiJoinLike with TJoin {}

case class TransitiveJoin(left: NNode, right: GetEdges, edgeList: EdgeListAttribute) extends BinaryNNode with EquiJoinLike with TTransitiveJoin {}

case class LeftOuterJoin(left: NNode, right: NNode) extends BinaryNNode with EquiJoinLike with TLeftOuterJoin {}

case class ThetaLeftOuterJoin(left: NNode,
                              right: NNode,
                              condition: Expression) extends BinaryNNode with EquiJoinLike with TThetaLeftOuterJoin {
}

// DML operators
abstract class CudOperator(child: NNode) extends UnaryNNode {}

case class Create(attribute: ResolvableName, child: NNode) extends CudOperator(child) with TCreate {
  override def output: Seq[ResolvableName] = child.output ++ Seq(attribute)
}

case class Delete(attributes: Seq[ResolvableName], detach: Boolean, child: NNode) extends CudOperator(child) with TDelete {}

case class Merge(attributes: ResolvableName, child: NNode) extends CudOperator(child) with TMerge {}

case class SetNode(vertexLabelUpdates: Set[VertexLabelUpdate], child: NNode) extends CudOperator(child) with TSetNode {}

case class Remove(vertexLabelUpdates: Set[VertexLabelUpdate], child: NNode) extends CudOperator(child) with TRemove {}
