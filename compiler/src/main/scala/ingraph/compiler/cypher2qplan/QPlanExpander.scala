package ingraph.compiler.cypher2qplan

import ingraph.model.{expr, qplan}
import org.apache.spark.sql.catalyst.analysis.UnresolvedAttribute
import org.apache.spark.sql.catalyst.expressions.Expression
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan
import org.apache.spark.sql.catalyst.{expressions => cExpr}

object QPlanExpander {
  def expandQPlan(rawQueryPlan: qplan.QNode): qplan.QNode = {
    // should there be other rule sets (partial functions), combine them using orElse,
    // e.g. pfunc1 orElse pfunc2
    // expanding GetVertices involves creating other GetVertices, so transformUp is to avoid infinite recursion
    val full = rawQueryPlan.transformUp(qplanExpander)

    full.asInstanceOf[qplan.QNode]
  }

  /**
    * These are the expand rules to complement compiler's brewity.
    */
  val qplanExpander: PartialFunction[LogicalPlan, LogicalPlan] = {
    // Nullary
    case qplan.GetVertices(vertexAttribute) if vertexAttribute.properties.nonEmpty => {
      val condition: Expression = propertyMapToCondition(vertexAttribute.properties, vertexAttribute.name)
      qplan.Selection(condition, qplan.GetVertices(vertexAttribute))
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
