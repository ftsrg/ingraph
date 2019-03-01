package ingraph.compiler.cypher2gplan

import ingraph.model.{expr, gplan}
import org.apache.spark.sql.catalyst.analysis.UnresolvedAttribute
import org.apache.spark.sql.catalyst.expressions.Expression
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan
import org.apache.spark.sql.catalyst.{expressions => cExpr}

object GPlanExpander {
  def expandGPlan(rawQueryPlan: gplan.GNode): gplan.GNode = {
    // should there be other rule sets (partial functions), combine them using orElse,
    // e.g. pfunc1 orElse pfunc2
    // expanding GetVertices involves creating other GetVertices, so transformUp is to avoid infinite recursion
    val full = rawQueryPlan.transformUp(gplanExpander)

    full.asInstanceOf[gplan.GNode]
  }

  /**
    * These are the expand rules to complement compiler's brewity.
    */
  val gplanExpander: PartialFunction[LogicalPlan, LogicalPlan] = {
    // Nullary
    case gplan.GetVertices(vertexAttribute) if vertexAttribute.properties.nonEmpty => {
      val condition: Expression = propertyMapToCondition(vertexAttribute.properties, vertexAttribute.name)
      gplan.Selection(condition, gplan.GetVertices(vertexAttribute))
    }
    case gplan.Expand(srcVertexAttribute, trgVertexAttribute, edge, dir, child) if edge.properties.nonEmpty || trgVertexAttribute.properties.nonEmpty => {
      val selectionOnEdge = gplan.Selection(propertyMapToCondition(edge.properties, edge.name), gplan.Expand(srcVertexAttribute, trgVertexAttribute, edge, dir, child))
      val selectionOnTargetVertex = gplan.Selection(propertyMapToCondition(trgVertexAttribute.properties, trgVertexAttribute.name), selectionOnEdge)
      selectionOnTargetVertex
    }
  }

  def propertyMapToCondition(properties: expr.types.TPropertyMap, baseName: String): Expression = {
    properties.map( (p) => cExpr.EqualTo(UnresolvedAttribute(Seq(baseName, p._1)), p._2) )
              .foldLeft[Expression]( cExpr.Literal(true) )( (b, a) => cExpr.And(b, a) )
  }
}
