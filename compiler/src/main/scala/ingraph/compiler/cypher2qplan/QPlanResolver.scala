package ingraph.compiler.cypher2qplan

import java.util.concurrent.atomic.AtomicLong

import ingraph.model.expr.{ProjectionDescriptor, ResolvableName}
import ingraph.model.qplan.{QNode, UnaryQNode}
import ingraph.model.{expr, misc, qplan}
import org.apache.spark.sql.catalyst.analysis.{UnresolvedAlias, UnresolvedAttribute, UnresolvedFunction, UnresolvedStar}
import org.apache.spark.sql.catalyst.expressions.{Expression, NamedExpression}
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan
import org.apache.spark.sql.catalyst.{expressions => cExpr}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object QPlanResolver {
  // structure to hold the name resolution cache
  type TNameResolverScope = mutable.Map[String, (String, expr.ResolvableName)]

  def resolveQPlan(unresolvedQueryPlan: qplan.QNode): qplan.QNode = {
    // should there be other rule sets (partial functions), combine them using orElse,
    // e.g. pfunc1 orElse pfunc2
    val resolvedNames = resolveNamesForQNode(unresolvedQueryPlan)
    val resolved = resolvedNames.transform(qplanResolver).asInstanceOf[qplan.QNode]

//    val elements = unresolvedQueryPlan.flatMap {
//      case qplan.GetVertices(v) => Some(v)
//      case qplan.Expand(src, trg, edge, _, _) => Some(edge, trg)
//      case _ => None
//    }

    resolved
  }

  /**
    * This is to resolve the names in expressions of Qplan.
    */
  private def resolveNamesForQNode(q: qplan.QNode): qplan.QNode = {
    // this holds the QNode's to be processed to create build the reverse Polish Notation of the tree
    val qNodeStack = mutable.Stack[qplan.QNode](q)
    // this holds the qplan in reverse polish notation, i.e. stack head has the leafs while its bottom element should hold the root node
    val qPlanPolishNotation = mutable.Stack[qplan.QNode]()
    // this will hold the operand stack for the Polish Notation evaluation algorithm used to rebuild the tree
    val operandStack = mutable.Stack[qplan.QNode]()

    // create the reverse polish notation in the stack qPlanPolishNotation
    while (qNodeStack.nonEmpty) {
      val n = qNodeStack.pop
      qPlanPolishNotation.push(n)
      n match {
        case u: qplan.UnaryQNode => qNodeStack.push(u.child)
        case b: qplan.BinaryQNode => qNodeStack.push(b.right, b.left) // this way left child will come out first, so it will be closer in qPlanPolishNotation to n itself
        case l: qplan.LeafQNode => null
        case x => throw new RuntimeException(s"Unexpected type found in the QPlan tree: ${x.getClass}")
      }
    }

    // this will hold those names that are in scope, so no new resolution should be invented
    var nameResolverScope = mutable.Map[String, (String, expr.ResolvableName)]()
    // scoped name resolver shorthand
    def r[T <: Expression](v: T): T = v.transform(expressionNameResolver(snrGen(nameResolverScope), nameResolverScope)).asInstanceOf[T]

    // re-assemble the tree resolving names
    while (qPlanPolishNotation.nonEmpty) {
      val newQNode: qplan.QNode = qPlanPolishNotation.pop match {
        case b: qplan.BinaryQNode => {
          val leftChild = operandStack.pop
          val rightChild = operandStack.pop
          val newOp: qplan.BinaryQNode = b match {
            case qplan.Union(all, _, _) => qplan.Union(all, leftChild, rightChild)
            case qplan.Join(_, _) => qplan.Join(leftChild, rightChild)
            case qplan.LeftOuterJoin(_, _) => qplan.LeftOuterJoin(leftChild, rightChild)
            case qplan.ThetaLeftOuterJoin(_, _, condition) => qplan.ThetaLeftOuterJoin(leftChild, rightChild, condition)
              // case AntiJoin skipped because it is introduced later in the beautification step
          }
          newOp
        }
        case u: qplan.UnaryQNode => {
          val child = operandStack.pop
          val newOp: qplan.UnaryQNode = u match {
            case qplan.AllDifferent(e, _) => qplan.AllDifferent(e, child)
            case qplan.DuplicateElimination(_) => qplan.DuplicateElimination(child)
            case qplan.Expand(src, trg, edge, dir, _) => qplan.Expand(r(src), r(trg), r(edge), dir, child)
            case qplan.Production(_) => qplan.Production(child)
            case qplan.UnresolvedProjection(projectList, _) => qplan.UnresolvedProjection(projectList, child)
            // case {Projection, Grouping} skipped because it is introduced in a later resolution stage
            case qplan.Selection(condition, _) => qplan.Selection(r(condition), child)
            case qplan.Sort(order, _) => qplan.Sort(order, child)
            case qplan.Top(skipExpr, limitExpr, _) => qplan.Top(skipExpr, limitExpr, child)
            case qplan.Unwind(collection, element, _) => qplan.Unwind(collection, element, child)
            case qplan.Create(attributes, _) => qplan.Create(attributes, child)
            case qplan.Delete(attributes, detach, _) => qplan.Delete(attributes, detach, child)
            case qplan.Merge(attributes, _) => qplan.Merge(attributes, child)
            case qplan.SetNode(vertexLabelUpdates, _) => qplan.SetNode(vertexLabelUpdates, child)
            case qplan.Remove(vertexLabelUpdates, _) => qplan.Remove(vertexLabelUpdates, child)
          }
          newOp
        }
        case l: qplan.LeafQNode => {
          val newOp: qplan.LeafQNode = l match {
            case qplan.GetVertices(v) => qplan.GetVertices(r(v))
            case x => x
          }
          newOp
        }
      }
      operandStack.push(newQNode)
    }

    if (operandStack.length == 1)
      operandStack.pop
    else
      throw new RuntimeException(s"A single item expected in the stack after re-assembling the QNode tree at name resolution. Instead, it has ${operandStack.length} entries.")
  }

  def expressionNameResolver(snr: (String, expr.ResolvableName) => Option[String], nameResolverScope: TNameResolverScope): PartialFunction[Expression, Expression] = {
    case rn: expr.ResolvableName =>
      if (rn.resolvedName.isDefined) rn // do not resolve already resolved stuff again
      else rn match {
        case expr.VertexAttribute (name, labels, properties, isAnonymous, _) => expr.VertexAttribute(name, labels, properties, isAnonymous, snr(name, rn))
        case expr.EdgeAttribute(name, labels, properties, isAnonymous, _) => expr.EdgeAttribute(name, labels, properties, isAnonymous, snr(name, rn))
        case expr.EdgeListAttribute(name, labels, properties, isAnonymous, minHops, maxHops, _) => expr.EdgeListAttribute(name, labels, properties, isAnonymous, minHops, maxHops, snr(name, rn))
        case expr.PropertyAttribute(name, elementAttribute, _) => expr.PropertyAttribute(name,
          // see "scoped name resolver shorthand" above
          elementAttribute.transform(expressionNameResolver(snr, nameResolverScope)).asInstanceOf[expr.ElementAttribute],
          snr(s"${elementAttribute.name}$$${name}", rn))
      }
    case UnresolvedAttribute(nameParts) => {
      if (nameParts.length >= 1 && nameParts.length <= 2) {
        val elementName = nameParts(0) // should be OK as .length >= 1
        val scopeCacheEntry = nameResolverScope.get(elementName)
        val elementAttribute = if (scopeCacheEntry.isDefined) {
          val rn = Some(scopeCacheEntry.get._1)
          // copy the type with basic stuff only
          scopeCacheEntry.get._2 match {
            case expr.VertexAttribute(name, _, _, isAnonymous, _) => expr.VertexAttribute(name, isAnonymous = isAnonymous, resolvedName = rn)
            case expr.EdgeAttribute(name, _, _, isAnonymous, _) => expr.EdgeAttribute(name, isAnonymous = isAnonymous, resolvedName = rn)
            case expr.EdgeListAttribute(name, _, _, isAnonymous, minHops, maxHops, _) => expr.EdgeListAttribute(name, isAnonymous = isAnonymous, resolvedName = rn, minHops = minHops, maxHops = maxHops)
          }
        } else {
          throw new RuntimeException(s"Unresolvable name ${elementName}.")
        }

        if (nameParts.length == 1) {
          elementAttribute
        } else { // nameParts.length == 2
          val propertyName = nameParts(1) // should be OK as .length == 2
          expr.PropertyAttribute(propertyName, elementAttribute, snr(s"${elementAttribute.name}$$${propertyName}",
            expr.PropertyAttribute(propertyName, elementAttribute)) // this is a dirty hack to tell the resolver that we are about to resolve a PropertyAttribute instance
          )
        }
      } else {
        throw new RuntimeException(s"Unexpected number of name parts, namely ${nameParts.length} for ${nameParts}")
      }
    }
    // fallback: no-op resolution.
    case x => x
  }

  // function generator for name resolution shorthand: "scoped name resolution"
  def snrGen(nameResolverScope: TNameResolverScope): (String, expr.ResolvableName) => Option[String] = (baseName, target) => {
    nameResolverScope.get(baseName) match {
      case Some(x) => if (x._2.getClass != target.getClass)
        throw new RuntimeException(s"Name collision across types: ${baseName}. In the cache, it is ${x._2.getClass}, but now it was passed as ${target.getClass}")
      case None => {
        nameResolverScope.put(baseName, (generateUniqueName(baseName), target))
      }
    }
    // reaching this point means we have baseName in the name reolver scope
    Some(nameResolverScope(baseName)._1)
  }


  // always use .getAndIncrement on this object
  private val generatedNameCounterMap = mutable.Map[String, AtomicLong]()

  def generateUniqueName(baseName: String): String = {
    s"${baseName}#${generatedNameCounterMap.getOrElseUpdate(baseName, new AtomicLong).getAndIncrement}"
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
