package ingraph.model.plan

import ingraph.model.expr.types.TProjectList
import ingraph.model.expr._
import org.apache.spark.sql.catalyst.expressions.{Expression, SortOrder}
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan

trait TGetVertices {
  val v: VertexAttribute
}

trait TDual {}

trait TAllDifferent {
  val edges: Seq[AbstractEdgeAttribute]
}

trait TDuplicateElimination {}

trait TProduction {
  val child: LogicalPlan
}

trait TAbstractProjection extends ProjectionDescriptor {}

trait TGrouping {
  val aggregationCriteria: Seq[Expression]
  val projectList: TProjectList
}

trait TSelection {
  val condition: Expression
}

trait TSort {
  val order: Seq[SortOrder]
}

trait TTop {
  val skipExpr: Option[Expression]
  val limitExpr: Option[Expression]
}

trait TSortAndTop extends TSort with TTop {}

trait TUnwind {
  val unwindAttribute: UnwindAttribute
}

trait TGetEdges {
  val src: VertexAttribute
  val trg: VertexAttribute
  val edge: EdgeAttribute
  val directed: Boolean
}

trait TUnion {}

trait TJoinLike {
  def commonAttributes: Seq[ResolvableName]
}

trait TJoin {}

trait TThetaLeftOuterJoin {}

trait TAntiJoin {}

trait TLeftOuterJoin {}

trait TTransitiveJoin {}

trait TCreate {}

trait TDelete {}

trait TMerge {}

trait TSetNode {}

trait TRemove {}

