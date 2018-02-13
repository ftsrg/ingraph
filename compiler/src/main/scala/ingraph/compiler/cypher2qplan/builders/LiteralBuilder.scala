package ingraph.compiler.cypher2qplan.builders

import ingraph.compiler.cypher2qplan.util.StringUtil
import ingraph.compiler.exceptions.CompilerException
import ingraph.model.expr.{types => eTypes}
import org.apache.spark.sql.catalyst.{expressions => cExpr}
import org.slizaa.neo4j.opencypher.openCypher.BoolConstant
import org.slizaa.neo4j.opencypher.{openCypher => oc}

import scala.collection.JavaConverters._

object LiteralBuilder {
  def buildNumber(e: oc.NumberConstant): Int = {
    var n: Int = 0

    try {
      n = Integer.parseInt(e.getValue)
    } catch {
      case _: NumberFormatException => {
        //FIXME: WARNING
        println("WARNING: " + s"Unable to parse ${e.getValue} as integer.")
      }
    }

    n
  }

  def buildNumberLiteral(e: oc.NumberConstant): cExpr.Literal = {
    try {
      cExpr.Literal(e.getValue.toLong)
    } catch {
      case _: NumberFormatException => {
        try {
          //FIXME: Literal does not support BigInt
          cExpr.Literal(BigInt(e.getValue))
        } catch {
          case _: NumberFormatException => {
            cExpr.Literal(e.getValue.toDouble)
          }
        }
      }
    }
  }

  def buildStringLiteral(e: oc.StringConstant): cExpr.Literal = {
    cExpr.Literal(StringUtil.unescapeCypherString(e.getValue))
  }

  def buildBoolLiteral(e: BoolConstant): cExpr.Literal = cExpr.Literal(e.getValue.toLowerCase.toBoolean)

  def buildProperties(p: oc.Properties): eTypes.TPropertyMap = {
    p match {
      case e: oc.MapLiteral => buildPropertyMap(e)
      case null => Map()
      case _ => throw new CompilerException("Can't handle vertex/edge properties other than MapLiteral.")
    }
  }
  def buildPropertyMap(pm: oc.MapLiteral): eTypes.TPropertyMap = {
    Option(pm).fold[ eTypes.TPropertyMap ]( Map() )(
      _.getEntries.asScala.map( (e) => Tuple2[String, cExpr.Expression](e.getKey, ExpressionBuilder.buildExpressionNoJoinAllowed(e.getValue)) ).toMap
    )
  }
}
