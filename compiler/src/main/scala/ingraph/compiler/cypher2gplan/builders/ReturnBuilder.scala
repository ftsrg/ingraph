package ingraph.compiler.cypher2gplan.builders

import ingraph.compiler.cypher2gplan.util.BuilderUtil
import ingraph.model.expr.types.TSortOrder
import ingraph.compiler.cypher2gplan.util.GrammarUtil._
import ingraph.model.{expr, gplan}
import org.apache.spark.sql.catalyst.analysis.UnresolvedStar
import org.apache.spark.sql.catalyst.{expressions => cExpr}
import org.slizaa.neo4j.opencypher.openCypher.VariableRef
import org.slizaa.neo4j.opencypher.{openCypher => oc}

import scala.collection.JavaConverters._
import scala.collection.mutable.ListBuffer

object ReturnBuilder {
  def dispatchBuildReturn(clause: oc.Clause, content: gplan.GNode): gplan.GNode = {
    clause match {
      case r: oc.Return => buildReturnBody(r.isDistinct, r.getBody, content, isReturnClause = true)
      case w: oc.With => buildWithClause(w, content)
    }
  }

  def buildWithClause(w: oc.With, content: gplan.GNode): gplan.GNode = {
    val selectionCondition: Option[cExpr.Expression] = Option(w.getWhere).flatMap(
      (where) => Some(ExpressionBuilder.buildExpressionNoJoinAllowed(where.getExpression))
    )

    buildReturnBody(w.isDistint, w.getReturnBody, content, selectionCondition, isReturnClause = false)
  }


  /**
    * Process the common part of a RETURN and a WITH clause,
    * i.e. the distinct flag and the ReturnBody.
    */
  def buildReturnBody(distinct: Boolean, returnBody: oc.ReturnBody, content: gplan.GNode, selectionCondition: Option[cExpr.Expression] = None, isReturnClause: Boolean): gplan.GNode = {
    val returnItems = returnBody.getReturnItems

    // this will hold the project list compiled
    val elements = ListBuffer[expr.ReturnItem]()

    if ("*".equals(returnItems.getAll)) {
      //TODO: when resolving,
      // - add the non-dontCare vertex variables to the return list sorted by variable name
      // - add the non-dontCare edge variables to the return list sorted by variable name
      elements += expr.ReturnItem(UnresolvedStar(None))
    }
    for (returnItem <- returnItems.getItems.asScala) {
      val e = ExpressionBuilder.buildExpressionNoJoinAllowed(returnItem.getExpression)

      // use parsed text from query as alias if no column name is available in a RETURN clause
      // for VariableRef there's no need to override the name of the variable
      val parsedText =
        if (isReturnClause && !returnItem.getExpression.isInstanceOf[VariableRef]) Some(returnItem.parsedText)
        else None
      val alias = Option(returnItem.getAlias)
        .map(_.getName)
        .map(Some(_))
        .getOrElse(parsedText)

      elements += expr.ReturnItem(e, alias)
    }

    val sortOrder: Option[TSortOrder] = Option(returnBody.getOrder).map(
      (order) => {
        order.getOrderBy.asScala.map(oe => {
          val sortExpression = ExpressionBuilder.buildExpressionNoJoinAllowed(oe.getExpression)
          val sortDirection: cExpr.SortDirection = Option(oe.getSort)
            .filter( _.toUpperCase.startsWith("DESC") ) // not null and DESC -> Some("DESC"), else none
            .fold[cExpr.SortDirection](cExpr.Ascending)( _ => cExpr.Descending)
          cExpr.SortOrder(sortExpression, sortDirection)
        })
      }
    )

    val skipExpr = Option(returnBody.getSkip).flatMap( (skip)  => BuilderUtil.convertToSkipLimitConstant(skip.getSkip) )
    val limitExpr = Option(returnBody.getLimit).flatMap( (limit) => BuilderUtil.convertToSkipLimitConstant(limit.getLimit) )

    //TODO: check after resolving: if (elements.empty) unrecoverableError('''RETURN items processed and resulted in no column values to return''')

    // We create an unresolved projection which is to be resolved to either Projection or Grouping followed by DuplicateElimination, Sort, Top, Selection
    gplan.UnresolvedProjection(elements, content, distinct, sortOrder, skipExpr, limitExpr, selectionCondition)
  }
}
