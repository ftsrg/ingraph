package ingraph.compiler.cypher2qplan

import ingraph.model.expr.{ProjectionDescriptor, VertexAttribute}
import ingraph.model.qplan.{QNode, UnaryQNode}
import ingraph.model.{expr, misc, qplan}
import org.apache.spark.sql.catalyst.analysis.{UnresolvedAlias, UnresolvedAttribute, UnresolvedFunction, UnresolvedStar}
import org.apache.spark.sql.catalyst.expressions.{Expression, NamedExpression}
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan
import org.apache.spark.sql.catalyst.{expressions => cExpr}
import org.apache.spark.sql.types.BooleanType

import scala.collection.mutable.ListBuffer

object QPlanExpander {
  def expandQPlan(rawQueryPlan: qplan.QNode): qplan.QNode = {
    // should there be other rule sets (partial functions), combine them using orElse,
    // e.g. pfunc1 orElse pfunc2
    // expanding GetVertices involves creating other GetVertices, so transformUp is to avoid infinite recursion
    val full = rawQueryPlan.transformUp(qplanExpander)


    full.asInstanceOf[qplan.QNode]
  }

  /**
    * These are the resolver rules that applies to all unresolved QPlans.
    *
    * There are some nodes that do not need resolution: GetVertices, DuplicateElimination, Expand, Join, Union, etc.
    */
  val qplanExpander: PartialFunction[LogicalPlan, LogicalPlan] = {
    // Nullary
    case qplan.GetVertices(VertexAttribute(name, labels, properties, isAnonymous)) if properties.nonEmpty => {
      val condition: Expression = propertyMapToCondition(properties, name)
      qplan.Selection(condition, qplan.GetVertices(VertexAttribute(name, labels, properties, isAnonymous)))
    }
    case qplan.Expand(srcVertexAttribute, trgVertexAttribute, edge, dir, child) if edge.properties.nonEmpty || trgVertexAttribute.properties.nonEmpty => {
      val selectionOnEdge = qplan.Selection(propertyMapToCondition(edge.properties, edge.name), qplan.Expand(srcVertexAttribute, trgVertexAttribute, edge, dir, child))
      val selectionOnTargetVertex = qplan.Selection(propertyMapToCondition(trgVertexAttribute.properties, trgVertexAttribute.name), selectionOnEdge)
      selectionOnTargetVertex
    }
  }

  def propertyMapToCondition(properties: expr.types.TPropertyMap, baseName: String): Expression = {
    properties.map( (p) => cExpr.EqualTo(UnresolvedAttribute(Seq(baseName, p._1)), p._2) )
              .foldLeft[Expression]( cExpr.Literal(true) )( (b, a) => cExpr.And(b, a) )
  }
}
