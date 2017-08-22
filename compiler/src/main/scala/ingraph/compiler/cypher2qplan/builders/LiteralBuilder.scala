package ingraph.compiler.cypher2qplan.builders

import ingraph.compiler.cypher2qplan.util.{BuilderUtil, StringUtil}
import ingraph.model.{expr, qplan}
import org.apache.spark.sql.catalyst.analysis.{UnresolvedAlias, UnresolvedStar}
import org.apache.spark.sql.catalyst.{expressions => cExpr}
import org.slizaa.neo4j.opencypher.{openCypher => oc}

import scala.collection.JavaConverters._
import scala.collection.mutable.ListBuffer

object LiteralBuilder {
  def buildNumber(e: oc.NumberConstant): Int = {
    var n: Int = 0

    try {
      n = Integer.parseInt(e.getValue)
    } catch {
      case _: NumberFormatException => {
        //FIXME: WARNING
        println("WARNING: " + s"Unable to parse ${e.getValue} as integer.")
        null
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
}
