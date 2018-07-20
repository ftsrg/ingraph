package ingraph.compiler.cypher2gplan.util

import ingraph.compiler.cypher2gplan.builders.{ExpressionBuilder, LiteralBuilder}
import ingraph.compiler.exceptions.CompilerException
import ingraph.model.expr
import org.apache.spark.sql.catalyst.{expressions => cExpr}
import org.eclipse.emf.common.util.EList
import org.slizaa.neo4j.opencypher.{openCypher => oc}

import scala.collection.JavaConverters._

object BuilderUtil {
  def parseToVertexLabelSet(ll: oc.NodeLabels): expr.VertexLabelSet = {
    if (ll != null && ll.getNodeLabels != null && !ll.getNodeLabels.isEmpty) {
      parseToVertexLabelSet(ll.getNodeLabels)
    } else {
      expr.VertexLabelSet()
    }
  }

  def parseToVertexLabelSet(el: EList[oc.NodeLabel]): expr.VertexLabelSet = {
    if (el != null && !el.isEmpty) {
      expr.VertexLabelSet(el.asScala.map( l => l.getLabelName ).toSet, expr.NonEmpty)
    } else {
      expr.VertexLabelSet()
    }
  }

  def parseToEdgeLabelSet(tl: oc.RelationshipTypes): expr.EdgeLabelSet = {
    if (tl != null && tl.getRelTypeName != null && !tl.getRelTypeName.isEmpty) {
      expr.EdgeLabelSet(tl.getRelTypeName.asScala.toSet, expr.NonEmpty)
    } else {
      expr.EdgeLabelSet()
    }
  }

  /**
    * Given a RelationshipPattern instance, it's direction information
    * is mapped to the expr model's Direction type.
    *
    * @param pattern the relationship pattern
    * @return the appropriate direction descriptor
    */
  def convertToDirection(pattern: oc.RelationshipPattern): expr.Direction = {
    val isLeftArrow = pattern.isIncoming
    val isRightArrow = pattern.isOutgoing

    if (isLeftArrow && isRightArrow || !(isLeftArrow || isRightArrow))
      expr.Both
    else if (isLeftArrow) expr.In else expr.Out
  }

  def convertToSkipLimitConstant(expression: oc.Expression): Option[cExpr.Expression] = {
    Option(expression).fold[ Option[cExpr.Expression] ](
      None //this is in-line with a null-safe call on expression as it was used before
    )( _ match {
      case e: oc.NumberLiteral => Some(LiteralBuilder.buildNumberLiteral(e))
      case e: oc.Parameter => Some(ExpressionBuilder.buildParameter(e))
      case e => throw new CompilerException(s"Only NumberConstants and parameters are supported as SKIP/LIMIT values, got ${e.getClass.getName}")
    })
  }
}
