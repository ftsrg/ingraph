package ingraph.compiler.cypher2gplan.builders

import ingraph.compiler.cypher2gplan.util.BuilderUtil
import ingraph.compiler.cypher2gplan.util.GrammarUtil._
import ingraph.model.{expr, gplan}
import org.apache.spark.sql.catalyst.analysis.UnresolvedStar
import org.apache.spark.sql.catalyst.{expressions => cExpr}
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
    val rb = buildReturnBody(w.isDistint, w.getReturnBody, content, isReturnClause = false)

    Option(w.getWhere).fold(rb)(
      (where) => {
        val condition = ExpressionBuilder.buildExpressionNoJoinAllowed(where.getExpression)
        gplan.Selection(condition, rb)
      }
    )
  }


  /**
    * Process the common part of a RETURN and a WITH clause,
    * i.e. the distinct flag and the ReturnBody.
    */
  def buildReturnBody(distinct: Boolean, returnBody: oc.ReturnBody, content: gplan.GNode, isReturnClause: Boolean): gplan.GNode = {
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

      // use parsed text from query as alias if no name is available in a RETURN clause
      val parsedText =
        if (isReturnClause) Some(returnItem.parsedText)
        else None
      val alias = Option(returnItem.getAlias)
        .map(_.getName)
        .map(Some(_))
        .getOrElse(parsedText)

      elements += expr.ReturnItem(e, alias)
    }

    //TODO: check after resolving: if (elements.empty) unrecoverableError('''RETURN items processed and resulted in no column values to return''')

    // We create an unresolved projection which is to be resolved to either Projection or Grouping
    val projection = gplan.UnresolvedProjection(elements, content)

    // add duplicate-elimination operator if return DISTINCT was specified
    val op1 = if (distinct) gplan.DuplicateElimination(projection) else projection

    val op2 = Option(returnBody.getOrder).fold(op1)(
      (order) => {
        val sortEntries: Seq[cExpr.SortOrder] = order.getOrderBy.asScala.map(oe => {
          val sortExpression = ExpressionBuilder.buildExpressionNoJoinAllowed(oe.getExpression)
          val sortDirection: cExpr.SortDirection = Option(oe.getSort)
            .filter( _.toUpperCase.startsWith("DESC") ) // not null and DESC -> Some("DESC"), else none
            .fold[cExpr.SortDirection](cExpr.Ascending)( _ => cExpr.Descending)
          cExpr.SortOrder(sortExpression, sortDirection)
        })
        gplan.Sort(sortEntries, op1)
      }
    )

    val op3 = (Option(returnBody.getSkip), Option(returnBody.getLimit)) match {
      case (None, None) => op2
      case (skipOpt, limitOpt) => {
        val s = skipOpt.flatMap( (skip)  => BuilderUtil.convertToSkipLimitConstant(skip.getSkip) )
        val l = limitOpt.flatMap( (limit) => BuilderUtil.convertToSkipLimitConstant(limit.getLimit) )
        gplan.Top(s, l, op2)
      }
    }
    op3
  }
}
