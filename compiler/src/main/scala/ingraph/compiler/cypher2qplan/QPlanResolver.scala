package ingraph.compiler.cypher2qplan

import ingraph.model.expr.ProjectionDescriptor
import ingraph.model.qplan.{QNode, UnaryQNode}
import ingraph.model.{expr, misc, qplan}
import org.apache.spark.sql.catalyst.analysis.{UnresolvedAlias, UnresolvedFunction, UnresolvedStar}
import org.apache.spark.sql.catalyst.expressions.{Expression, NamedExpression}
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan
import org.apache.spark.sql.catalyst.{expressions => cExpr}

import scala.collection.mutable.ListBuffer

object QPlanResolver {
  def resolveQPlan(unresolvedQueryPlan: qplan.QNode): qplan.QNode = {
    // should there be other rule sets (partial functions), combine them using orElse,
    // e.g. pfunc1 orElse pfunc2
    val resolvedNames = unresolvedQueryPlan.transformUp(qplanNameResolver).asInstanceOf[qplan.QNode]
    val resolved = resolvedNames.transform(qplanResolver).asInstanceOf[qplan.QNode]

//    val elements = unresolvedQueryPlan.flatMap {
//      case qplan.GetVertices(v) => Some(v)
//      case qplan.Expand(src, trg, edge, _, _) => Some(edge, trg)
//      case _ => None
//    }

    resolved
  }

  /**
    * These are the name resolver rules that applies to all unresolved QPlans.
    */
  val qplanNameResolver: PartialFunction[LogicalPlan, LogicalPlan] = {
    // Unary
    //    case qplan.Projection(projectList, child) => qplan.Projection(projectList.map(_.transform(expressionResolver).asInstanceOf[NamedExpression]), child)
    case qplan.UnresolvedProjection(projectList, child) => {
      val resolvedProjectList = projectList.map(_.transform(expressionResolver).asInstanceOf[NamedExpression])
      projectionResolveHelper(resolvedProjectList, child)
    }
    case qplan.Selection(condition, child) => qplan.Selection(condition.transform(expressionResolver), child)
    case qplan.Top(skipExpr, limitExpr, child) => qplan.Top(skipExpr.transform(expressionResolver), limitExpr.transform(expressionResolver), child)
    case qplan.Unwind(collection, element, child) => qplan.Top(collection.transform(expressionResolver), element.transform(expressionResolver), child)
    // DML
    case qplan.Delete(attributes, detach, child) => qplan.Delete(resolveAttributes(attributes, child), detach, child)
    case qplan.Create(attributes, child) => qplan.Create(filterForAttributesOfChildOutput(attributes, child, invert=true), child)
  }


  /**
    * These are the resolver rules that applies to all unresolved QPlans.
    *
    * There are some nodes that do not need resolution: GetVertices, DuplicateElimination, Expand, Join, Union, etc.
    */
  val qplanResolver: PartialFunction[LogicalPlan, LogicalPlan] = {
    // Unary
//    case qplan.Projection(projectList, child) => qplan.Projection(projectList.map(_.transform(expressionResolver).asInstanceOf[NamedExpression]), child)
    case qplan.UnresolvedProjection(projectList, child) => {
      val resolvedProjectList = projectList.map(_.transform(expressionResolver).asInstanceOf[NamedExpression])
      projectionResolveHelper(resolvedProjectList, child)
    }
    case qplan.Selection(condition, child) => qplan.Selection(condition.transform(expressionResolver), child)
    case qplan.Top(skipExpr, limitExpr, child) => qplan.Top(skipExpr.transform(expressionResolver), limitExpr.transform(expressionResolver), child)
    case qplan.Unwind(collection, element, child) => qplan.Top(collection.transform(expressionResolver), element.transform(expressionResolver), child)
    // DML
    case qplan.Delete(attributes, detach, child) => qplan.Delete(resolveAttributes(attributes, child), detach, child)
    case qplan.Create(attributes, child) => qplan.Create(filterForAttributesOfChildOutput(attributes, child, invert=true), child)
  }

  val expressionResolver: PartialFunction[Expression, Expression] = {
    case UnresolvedFunction(functionIdentifier, children, isDistinct) => expr.FunctionInvocation(misc.Function.fromCypherName(functionIdentifier.identifier, children.length, isDistinct), children, isDistinct)
  }

  /**
    * Resolve attribute references according to the output schema of the child QNode
    * @param attributes
    * @param child
    * @return
    */
  protected def resolveAttributes(attributes: Seq[cExpr.Attribute], child: qplan.QNode): Seq[cExpr.Attribute] = {
    val transformedAttributes = attributes.flatMap( a => child.output.find( co => co.name == a.name ) )

    if (attributes.length != transformedAttributes.length) {
      throw new RuntimeException(s"Unable to resolve all attributes. Resolved ${transformedAttributes.length} out of ${attributes.length}")
    }

    transformedAttributes
  }

  /**
    * Filters the attributes passed in for being included in the child.output schema
    * @param attributes
    * @param child
    * @param invert iff true, match is inverted, i.e. only those are returned which were not found
    * @return
    */
  protected def filterForAttributesOfChildOutput(attributes: Seq[cExpr.Attribute], child: qplan.QNode, invert: Boolean = false): Seq[cExpr.Attribute] = {
    attributes.flatMap( a => if ( invert.^(child.output.exists( co => co.name == a.name )) ) Some(a) else None )
  }


  /**
    * Creates either a Projection or a Grouping instance based on the expressions found in projectList.
    *
    * Grouping is returned iff we find at least one expression in projectList having a call to an aggregate function at its top-level.
    *
    *
    * @param child
    * @param projectList
    * @return
    */
  protected def projectionResolveHelper(projectList: Seq[NamedExpression], child: QNode): UnaryQNode with ProjectionDescriptor = {
    /**
      * Returns true iff e is an expression having a call to an aggregation function at its top-level.
      * @param e
      * @return
      */
    def isAggregatingFunctionInvocation(e: cExpr.Expression): Boolean = {
      e match {
        case expr.FunctionInvocation(f, _, _) => f.isAggregation
        case _ => false
      }
    }

    // look for aggregation functions in top-level position of return items
    // those having no aggregation function in top-level position will form the aggregation criteria if at least one aggregation is seen
    val aggregationCriteriaCandidate: ListBuffer[Expression] = ListBuffer.empty

    val seenAggregate = projectList.foldLeft[Boolean](false)((b, a) => b || (a match {
      case cExpr.Alias(e, _) => if (isAggregatingFunctionInvocation(e)) {
        true
      } else {
        aggregationCriteriaCandidate += e
        false
      }
      case UnresolvedAlias(e, _) => if (isAggregatingFunctionInvocation(e)) {
        true
      } else {
        aggregationCriteriaCandidate += e
        false
      }
      // we expect to have all return items be either Alias or UnresolvedAlias
      // FIXME: UnresolvedStar is also allowed until it is resolved
      case UnresolvedStar(_) => false
      case x => throw new RuntimeException(s"Unexpected type found in return item position: ${x.getClass}")
    }))

    if (seenAggregate) {
      qplan.Grouping(aggregationCriteriaCandidate, projectList, child)
    } else {
      qplan.Projection(projectList, child)
    }
  }
}
