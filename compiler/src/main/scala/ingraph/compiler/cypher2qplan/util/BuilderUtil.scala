package ingraph.compiler.cypher2qplan.util

import ingraph.model.{expr, qplan}
import org.apache.spark.sql.catalyst.{expressions => cExpr}
import org.slizaa.neo4j.opencypher.{openCypher => oc}

import scala.collection.JavaConverters._

object BuilderUtil {
  def parseToVertexLabelSet(ll: oc.NodeLabels): expr.VertexLabelSet = {
    if (ll != null && ll.getNodeLabels != null && !ll.getNodeLabels.isEmpty) {
      expr.VertexLabelSet(ll.getNodeLabels.asScala.map( l => l.getLabelName ).toSet, expr.NonEmpty)
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

  def convertToSkipLimitConstant(expression: oc.Expression): cExpr.Expression = {
    expression match {
      case e if e == null => null //this is in-line with a null-safe call on expression as it was used before
      case e: oc.NumberConstant => expr.EStub("Number Literal") //FIXME: LiteralBuilder.buildNumberLiteral(expression, ce)
      case e: oc.Parameter => expr.EStub("Parameter") //FIXME: buildRelalgParameter(expression, ce)
      case e => expr.EStub(s"Only NumberConstants and parameters are supported as SKIP/LIMIT values, got ${e.getClass.getName}")
    }
  }
}
