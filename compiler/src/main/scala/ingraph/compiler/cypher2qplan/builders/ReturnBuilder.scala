package ingraph.compiler.cypher2qplan.builders

import ingraph.compiler.cypher2qplan.util.BuilderUtil
import ingraph.model.{expr, qplan}
import org.apache.spark.sql.catalyst.analysis.{UnresolvedAlias, UnresolvedStar}
import org.apache.spark.sql.catalyst.{expressions => cExpr}
import org.slizaa.neo4j.opencypher.{openCypher => oc}

import scala.collection.JavaConverters._
import scala.collection.mutable.ListBuffer

object ReturnBuilder {
  def dispatchBuildReturn(clause: oc.Clause, content: qplan.QNode): qplan.QNode = {
    clause match {
      case r: oc.Return => buildReturnBody(r.isDistinct, r.getBody, content)
      case w: oc.With => buildWithClause(w, content)
    }
  }

  def buildWithClause(w: oc.With, content: qplan.QNode): qplan.QNode = {
    val rb = buildReturnBody(w.isDistint, w.getReturnBody, content)

    Option(w.getWhere).fold(rb)(
      (where) => {
        val condition = ExpressionBuilder.buildExpressionNoJoinAllowed(where.getExpression)
        qplan.Selection(condition, rb)
      }
    )
  }


  /**
    * Process the common part of a RETURN and a WITH clause,
    * i.e. the distinct flag and the ReturnBody.
    */
  def buildReturnBody(distinct: Boolean, returnBody: oc.ReturnBody, content: qplan.QNode): qplan.QNode = {
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
      Option(returnItem.getAlias).fold(elements += expr.ReturnItem(e))( (alias) => elements += expr.ReturnItem(e, Some(alias.getName)) )
    }

    //TODO: check after resolving: if (elements.empty) unrecoverableError('''RETURN items processed and resulted in no column values to return''')

    // We create an unresolved projection which is to be resolved to either Projection or Grouping
    val projection = qplan.UnresolvedProjection(elements, content)

    // add duplicate-elimination operator if return DISTINCT was specified
    val op1 = if (distinct) qplan.DuplicateElimination(projection) else projection

    val op2 = Option(returnBody.getOrder).fold(op1)(
      (order) => {
        val sortEntries: Seq[cExpr.SortOrder] = order.getOrderBy.asScala.map(oe => {
          val sortExpression = ExpressionBuilder.buildExpressionNoJoinAllowed(oe.getExpression)
          val sortDirection: cExpr.SortDirection = Option(oe.getSort)
            .filter( _.toUpperCase.startsWith("DESC") ) // not null and DESC -> Some("DESC"), else none
            .fold[cExpr.SortDirection](cExpr.Ascending)( _ => cExpr.Descending)
          cExpr.SortOrder(sortExpression, sortDirection)
        })
        qplan.Sort(sortEntries, op1)
      }
    )

    val op3 = (Option(returnBody.getSkip), Option(returnBody.getLimit)) match {
      case (None, None) => op2
      case (skipOpt, limitOpt) => {
        val s = skipOpt.flatMap( (skip)  => BuilderUtil.convertToSkipLimitConstant(skip.getSkip) )
        val l = limitOpt.flatMap( (limit) => BuilderUtil.convertToSkipLimitConstant(limit.getLimit) )
        qplan.Top(s, l, op2)
      }
    }
    op3
  }
}
