package ingraph.compiler.cypher2gplan

import ingraph.compiler.cypher2gplan.builders.AttributeBuilder
import ingraph.compiler.exceptions.ExpandChainException
import ingraph.model.expr.types.EdgeLabel
import ingraph.model.expr.{AbstractEdgeAttribute, ElementAttribute}
import ingraph.model.{expr, gplan}
import org.apache.spark.sql.catalyst.analysis.UnresolvedAttribute
import org.apache.spark.sql.catalyst.expressions.Expression
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan
import org.apache.spark.sql.catalyst.{expressions => cExpr}
import org.apache.spark.sql.types.BooleanType

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object GPlanBeautifier {
  def beautifyResolvedGPlan(resolvedQueryPlan: gplan.GNode): gplan.GNode = {
    // should there be other rule sets (partial functions), combine them using orElse,
    // e.g. pfunc1 orElse pfunc2
    val beautiful = transformUpToNTimes(resolvedQueryPlan, commonBeautifier, 7)

    beautiful.asInstanceOf[gplan.GNode]
  }

  /**
    * Transforms the plan using rule once. After that, it repeats transformation up to n times until plan does not change.
    */
  def transformUpToNTimes(plan: LogicalPlan, rule: PartialFunction[LogicalPlan, LogicalPlan], n: Int): LogicalPlan = {
    // transformUp yields stable plan usually faster
    val transformed = plan.transformUp(rule)

    if (n <= 0 || (plan fastEquals transformed)) {
      transformed
    } else {
      transformUpToNTimes(transformed, rule, n-1)
    }
  }

  def beautifyUnresolvedGPlan(unresolvedQueryPlan: gplan.GNode): gplan.GNode = {
    // should there be other rule sets (partial functions), combine them using orElse,
    // e.g. pfunc1 orElse pfunc2
    val beautiful = unresolvedQueryPlan.transform(commonBeautifier)

    beautiful.asInstanceOf[gplan.GNode]
  }

  /**
    * These are the structural beautifier rules that applies to all GPlans,
    * irrespectively whether it is resolved or not.
    *
    * Note: most rules will will be common.
    */
  val commonBeautifier: PartialFunction[LogicalPlan, LogicalPlan] = {
    case gplan.Join(gplan.Dual(), o2) => o2
      // FIXME: projection should be introduced by the compiler, and also match it here
    case gplan.Selection(cExpr.Not(e), gplan.LeftOuterJoin(base, filter)) if checkForAntijoin(e, filter) => gplan.AntiJoin(base, filter)
    case gplan.Selection(cExpr.Not(e), gplan.LeftOuterJoin(filter, base)) if checkForAntijoin(e, filter) => gplan.AntiJoin(base, filter)
    case gplan.Selection(cExpr.Literal(true, BooleanType), child) => child
    case gplan.Selection(condition, child) => gplan.Selection(condition.transform(expressionBeautifier), child)
    case gplan.AllDifferent(edges, child) if edgeLabelSetsAreDistinct(edges) => child
  }

  val expressionBeautifier: PartialFunction[Expression, Expression] = {
    case cExpr.And(cExpr.Literal(true, BooleanType), r) => r
    case cExpr.And(l, cExpr.Literal(true, BooleanType)) => l
    case cExpr.Or(cExpr.Literal(false, BooleanType), r) => r
    case cExpr.Or(l, cExpr.Literal(false, BooleanType)) => l
    case cExpr.Not(cExpr.Not(e)) => e
    case cExpr.UnaryPositive(e) => e
    case cExpr.UnaryMinus(cExpr.UnaryMinus(e)) => e
  }

  /**
    * This checks whether the expression and the filter forms a combination which is candidate for antijoin.
    *
    * Checks performed:
    *  1. expression if of the form AND_i{IS NOT NULL attr_i}. Note: the negation is already matched at an outer level
    *  2. filter is a chain of the form Expand*GetVertices, where * stands for 0, 1 or more occurences
    *  3. attr_i in (1) correspond to those in the chain of (2)
    */
  protected def checkForAntijoin(topLevelExpression: cExpr.Expression, filter: gplan.GNode): Boolean = {
    var check1 = true
    // traverse topLevelExpression to see if it is composed solely of AND's of IsNotNull({Edge,Vertex}Attribute) constraints
    val exprQueue = mutable.Queue.apply(topLevelExpression)
    val attributesOfCondition = ListBuffer.empty[cExpr.Expression]
    while (check1 && exprQueue.nonEmpty) {
      exprQueue.dequeue() match {
        case cExpr.And(c1, c2) => exprQueue.enqueue(c1, c2)
        case cExpr.IsNotNull(expr.EdgeAttribute(n, l, p, ia, rn)) => attributesOfCondition.append(expr.EdgeAttribute(n, l, p, ia, rn))
        case cExpr.IsNotNull(expr.VertexAttribute(n, l, p, ia, rn)) => attributesOfCondition.append(expr.VertexAttribute(n, l, p, ia, rn))
        case cExpr.IsNotNull(UnresolvedAttribute(nameParts)) => attributesOfCondition.append(UnresolvedAttribute(nameParts))
        case _ => check1 = false
      }

    }
    if (!check1) return false

    var check2 = true
    // check if filter is a chain of Expand operators on top of a single GetVertices, and attributes are properly chained
    val attributesOfFilter = try {
      AttributeBuilder.extractAttributesFromExpandChain(filter)
    } catch {
      case _: ExpandChainException => {
        check2 = false
        ListBuffer.empty[cExpr.Expression]
      }
    }
    if (!check2) return false

    //var check3 = attributesOfCondition.toSet == attributesOfFilter.toSet
    // compare by name
    var check3 = attributesOfCondition.flatMap( e => e match {
      case ea: ElementAttribute => Some(ea.name)
      case ua: UnresolvedAttribute => Some(ua.name)
    } ).toSet == attributesOfFilter.flatMap( e => e match {
      case ea: ElementAttribute => Some(ea.name)
      case ua: UnresolvedAttribute => Some(ua.name)
    }).toSet

    if (!check3) return false

    return check1 && check2 && check3
  }

  /**
    * This checks whether all the edges are structurally different.
    *
    * Checks performed:
    *  1. each of them describe a single edge, and has non-empty egde label (type) constraints
    *  2. each edge label constraint is non-overlapping to all the previous ones
    */
  protected def edgeLabelSetsAreDistinct(edges: Seq[AbstractEdgeAttribute]): Boolean = {
    val check1 = edges.foldLeft[Boolean](true)( (acc, edge) => acc && edge.labels.edgeLabels.nonEmpty && edge.isInstanceOf[expr.EdgeAttribute] )
    if (!check1) return false

    var check2=true
    val unionOfEdgeLabels: Set[EdgeLabel] = edges.foldLeft(Set[EdgeLabel]())( (acc, edge) =>  {
      if (acc.intersect(edge.labels.edgeLabels).nonEmpty) {
        check2 = false
      }
      acc.union(edge.labels.edgeLabels)
    })
    if (!check2) return false

    return check1 && check2
  }
}
