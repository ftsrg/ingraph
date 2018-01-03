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
    if (w.getWhere == null) {
      rb
    } else {
      //FIXME: implement proper WHERE sub-clause handling
      // when doing so, check the current openCypher grammar if it also allows WHERE sub-clause for RETURN

//      // left outer joins extracted from the patterns in the where clause
//      // should remain empty in WITH WHERE
//      val EList<Operator> joinOperationsOfWhereClause = new BasicEList<Operator>()
//
//      val selectionOperator = modelFactory.createSelectionOperator => [
//      input = rb
//      condition = LogicalExpressionBuilder.buildLogicalExpression(w.where.expression, joinOperationsOfWhereClause, ce)
//      ]
//
//      if (joinOperationsOfWhereClause.length !== 0) {
//        ce.l.unsupported('''Pattern expression found in WITH ... WHERE, which is unsupported. Consider moving this expression to MATCH...WHERE.''')
//      }

      qplan.Selection(expr.EStub("WITH WHERE"), rb)
    }
  }


  /**
    * Process the common part of a RETURN and a WITH clause,
    * i.e. the distinct flag and the ReturnBody.
    */
  def buildReturnBody(distinct: Boolean, returnBody: oc.ReturnBody, content: qplan.QNode): qplan.QNode = {
    // FIXME (in the grammar): returnBody.returnItems.get(0) is the actual return item list
    // but it should be w/o .get(0)
    val returnItems = returnBody.getReturnItems.get(0)

    // pre-create elements to project to which will be copied to BeamerOperator.elements
    val _elements = ListBuffer[expr.ReturnItem]()

    if ("*".equals(returnItems.getAll)) {
      //TODO: when resolving,
      // - add the non-dontCare vertex variables to the return list sorted by variable name
      // - add the non-dontCare edge variables to the return list sorted by variable name
      _elements += expr.ReturnItem(UnresolvedStar(None))
    }
    for (returnItem <- returnItems.getItems.asScala) {
      val e = ExpressionBuilder.buildExpressionNoJoinAllowed(returnItem.getExpression)

      if (returnItem.getAlias == null)
        _elements += expr.ReturnItem(e)
      else
        _elements += expr.ReturnItem(e, Some(returnItem.getAlias.getName))
    }

    //TODO: check after resolving: if (_elements.empty) unrecoverableError('''RETURN items processed and resulted in no columns values to return''')

    // We create an unresolved projection which is to be resolved to either Projection or Grouping
    val projection = qplan.UnresolvedProjection(_elements, content)

    // add duplicate-elimination operator if return DISTINCT was specified
    val op1 = if (distinct) {
      qplan.DuplicateElimination(projection)
    } else {
      projection
    }

    val order = returnBody.getOrder
    val op2 = if (order == null) {
      op1
    } else {
      val sortEntries: Seq[cExpr.SortOrder] = order.getOrderBy.asScala.map( oe => {
        val sortExpression = ExpressionBuilder.buildExpressionNoJoinAllowed(oe.getExpression)
        val sortDirection: cExpr.SortDirection = if (oe.getSort != null && oe.getSort.toUpperCase.startsWith("DESC")) cExpr.Descending else cExpr.Ascending
        cExpr.SortOrder(sortExpression, sortDirection)
      } )
      qplan.Sort(sortEntries, op1)
    }

    val skip = returnBody.getSkip
    val limit = returnBody.getLimit
    val op3 = if (skip == null && limit == null) {
      op2
    } else {
      val s = if (skip == null) None else BuilderUtil.convertToSkipLimitConstant(skip.getSkip)
      val l = if (limit == null) None else BuilderUtil.convertToSkipLimitConstant(limit.getLimit)
      qplan.Top(s, l, op2)
    }

    op3
  }
}
