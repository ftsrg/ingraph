package ingraph.compiler.cypher2qplan

import java.util.concurrent.atomic.AtomicLong

import ingraph.compiler.cypher2qplan.util.TransformUtil
import ingraph.compiler.exceptions.{CompilerException, IllegalAggregationException, NameResolutionException, UnexpectedTypeException}
import ingraph.model.expr.{ProjectionDescriptor, ResolvableName, ReturnItem}
import ingraph.model.qplan.{QNode, UnaryQNode}
import ingraph.model.{expr, misc, qplan}
import org.apache.spark.sql.catalyst.analysis.{UnresolvedAttribute, UnresolvedFunction, UnresolvedStar}
import org.apache.spark.sql.catalyst.expressions.{Expression, SortOrder}
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan
import org.apache.spark.sql.catalyst.{expressions => cExpr}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object QPlanResolver {
  /**
    * structure to hold the name resolution cache:
    *   key is the lookup name (String)
    *   value is a pair of:
    *      - the result of the name resolution and
    *      - the expression resulted in this particular resolution
    */
  protected class TNameResolverCache {
    type TKey = String
    type TValue = (expr.types.TResolvedNameValue, cExpr.Expression)

    private val impl = mutable.Map[TKey, TValue]()

    def get(k: TKey): Option[TValue] = impl.get(k)
    def put(k: TKey, v: TValue) = impl.put(k, v)
    /** Put en entry under the key of the original basename stored in the resolved value v */
    def put(rn: expr.ResolvableName) = rn match {
      case rne: cExpr.Expression => impl.put(rn.resolvedName.get.baseName, (rn.resolvedName.get, rne))
    }

    def update(k:TKey, v: TValue) = impl.update(k, v)
  }
  type TScopedNameResolver = (String, cExpr.Expression) => expr.types.TResolvedName

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
        /*
         * Ensure that right child comes out first, so it will be closer in qPlanPolishNotation to n itself,
         * meaning that in "evaluation time" (i.e. for the sake of name resolution), left child will be evaluated first.
         *
         * This is important as query parts (those that end at WITH or RETURN clause) are assembled to form a left deep tree of join nodes
         *
         * This also means that name resolution builds on the operand order of joins, so before name resolution, joins are non-commutative
         */
        case b: qplan.BinaryQNode => qNodeStack.push(b.left, b.right)
        case _: qplan.LeafQNode => {}
        case x => throw new UnexpectedTypeException(x, "QPlan tree")
      }
    }

    reAssembleTreeResolvingNames(qPlanPolishNotation, operandStack)

    // return the re-constructed tree
    operandStack.length match {
      case 1 => operandStack.pop
      case _ => throw new CompilerException(s"A single item expected in the stack after re-assembling the QNode tree at name resolution. Instead, it has ${operandStack.length} entries.")
    }
  }

  protected def reAssembleTreeResolvingNames(qPlanPolishNotation: mutable.Stack[qplan.QNode], operandStack: mutable.Stack[qplan.QNode]) = {
    // this will hold those names that are in scope, so no new resolution should be invented
    var nameResolverCache = new TNameResolverCache
    // HACK: this will hold the previous name resolving scope, which will be used for Sort and Top operators
    var oldNameResolverScope: Option[TNameResolverCache] = None
    // scoped name resolver shorthand
    def r[T <: Expression](v: T): T = r2(v, nameResolverCache)
    // scoped name resolver shorthand allowing to pass in the nameResolverCache
    def r2[T <: Expression](v: T, nrs: TNameResolverCache): T = v.transform(expressionNameResolver(snrGen(nrs), nrs)).asInstanceOf[T]

    // re-assemble the tree resolving names
    while (qPlanPolishNotation.nonEmpty) {
      val newQNode: qplan.QNode = qPlanPolishNotation.pop match {
        case b: qplan.BinaryQNode => {
          val rightChild = operandStack.pop
          val leftChild = operandStack.pop
          val newOp: qplan.BinaryQNode = b match {
            case qplan.Union(all, _, _) => qplan.Union(all, leftChild, rightChild)
            case qplan.Join(_, _) => qplan.Join(leftChild, rightChild)
            case qplan.LeftOuterJoin(_, _) => qplan.LeftOuterJoin(leftChild, rightChild)
            case qplan.ThetaLeftOuterJoin(_, _, condition) => qplan.ThetaLeftOuterJoin(leftChild, rightChild, r(condition))
            // case AntiJoin skipped because it is introduced later in the beautification step
          }
          newOp
        }
        case u: qplan.UnaryQNode => {
          val child = operandStack.pop
          val newOp: qplan.UnaryQNode = u match {
            case qplan.AllDifferent(e, _) => qplan.AllDifferent(e.map(r(_)), child)
            case qplan.DuplicateElimination(_) => qplan.DuplicateElimination(child)
            case qplan.Expand(src, trg, edge, dir, _) => qplan.Expand(r(src), r(trg), r(edge), dir, child)
            // resolve names in listexpression, then resolve the unwindattribute itself
            case qplan.Unwind(ua, _) => qplan.Unwind(r(expr.UnwindAttribute(r(ua.list), ua.name, ua.resolvedName)), child)
            case qplan.Production(_) => qplan.Production(child)
            case qplan.UnresolvedProjection(projectList, _) => {
              // initialize new namespace applicable after the projection operator
              val nextQueryPartNameResolverCache = new TNameResolverCache
              val nextSnr = snrGen(nextQueryPartNameResolverCache)
              val resolvedProjectListBuf = ListBuffer.empty[expr.ReturnItem]
              for (ri <- projectList) {
                val resolvedChild = r(ri.child)
                val resolvedName: expr.types.TResolvedName = ri.alias match {
                  case Some(alias) => nextSnr(alias, resolvedChild)
                  case None => resolvedChild match {
                    case rc: expr.ResolvableName => {
                      rc match {
                        case ea: expr.ElementAttribute => nextQueryPartNameResolverCache.put(rc)
                        case pa: expr.PropertyAttribute => nextQueryPartNameResolverCache.put(rc)
                        case ua: expr.UnwindAttribute => nextQueryPartNameResolverCache.put(rc)
                        case ea: expr.ExpressionAttribute => nextQueryPartNameResolverCache.put(rc)
                        case x => throw new UnexpectedTypeException(x, "return item position")
                      }
                      rc.resolvedName
                    }
                    case rc => nextSnr("_expr", rc)
                  }
                }
                resolvedProjectListBuf.append(ReturnItem(resolvedChild, ri.alias, resolvedName))
              }
              val resolvedProjectList: expr.types.TProjectList = resolvedProjectListBuf.toSeq

              // retain old name resolver scope
              oldNameResolverScope = Some(nameResolverCache)
              nameResolverCache = nextQueryPartNameResolverCache
              qplan.UnresolvedProjection(resolvedProjectList, child)
            }
            // case {Projection, Grouping} skipped because it is introduced in a later resolution stage
            case qplan.Selection(condition, _) => qplan.Selection(r(condition), child)
            case qplan.Sort(order, _) => qplan.Sort(order.map( _ match {
              case cExpr.SortOrder(sortExpr, dir, no, se) => try {
                cExpr.SortOrder(r(sortExpr), dir, no, se)
              } catch {
                // in case of name resolution problem, we fall back to the last name resolution scope, if available. If again can't resolve, we throw the exception
                case nre: NameResolutionException => cExpr.SortOrder(r2(sortExpr, oldNameResolverScope.getOrElse(throw nre)), dir, no, se)
              }
            }), child)
            case qplan.Top(skipExpr, limitExpr, _) => qplan.Top(skipExpr, limitExpr, child)
            case qplan.Create(attributes, _) => qplan.Create(attributes.map(r(_)), child)
            case qplan.UnresolvedDelete(attributes, detach, _) => qplan.UnresolvedDelete(attributes, detach, child)
            case qplan.Merge(attributes, _) => qplan.Merge(attributes.map(r(_)), child)
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
  }

  def expressionNameResolver(snr: TScopedNameResolver, nameResolverCache: TNameResolverCache): PartialFunction[Expression, Expression] = {
    case rn: expr.ResolvableName =>
      if (rn.resolvedName.isDefined) rn // do not resolve already resolved stuff again
      else rn match {
        case expr.VertexAttribute (name, labels, properties, isAnonymous, _) => expr.VertexAttribute(name, labels, properties, isAnonymous, snr(name, rn))
        case expr.EdgeAttribute(name, labels, properties, isAnonymous, _) => expr.EdgeAttribute(name, labels, properties, isAnonymous, snr(name, rn))
        case expr.RichEdgeAttribute(src, trg, edge, dir) => expr.RichEdgeAttribute(
          src.transform(expressionNameResolver(snr, nameResolverCache)).asInstanceOf[expr.VertexAttribute],
          trg.transform(expressionNameResolver(snr, nameResolverCache)).asInstanceOf[expr.VertexAttribute],
          edge.transform(expressionNameResolver(snr, nameResolverCache)).asInstanceOf[expr.EdgeAttribute],
          dir
        )
        case expr.EdgeListAttribute(name, labels, properties, isAnonymous, minHops, maxHops, _) => expr.EdgeListAttribute(name, labels, properties, isAnonymous, minHops, maxHops, snr(name, rn))
        case expr.PropertyAttribute(name, elementAttribute, _) => expr.PropertyAttribute(name,
          // see "scoped name resolver shorthand" above
          elementAttribute.transform(expressionNameResolver(snr, nameResolverCache)).asInstanceOf[expr.ElementAttribute],
          snr(s"${elementAttribute.name}$$${name}", rn))
        case expr.UnwindAttribute(list, name, _) => expr.UnwindAttribute(list, name, snr(name, rn))
      }
    case UnresolvedAttribute(nameParts) => nameParts.length match {
      case 1 | 2 => {
        val elementName = nameParts(0) // should be OK as .length >= 1
        val scopeCacheEntry = nameResolverCache.get(elementName)
        val elementAttribute = scopeCacheEntry match { //if (scopeCacheEntry.isDefined) {
          case Some((rnString, entry)) => {
            val rn = Some(rnString)
            // copy the type with basic stuff only
            entry match {
              case expr.VertexAttribute(name, _, _, isAnonymous, _) => expr.VertexAttribute(name, isAnonymous = isAnonymous, resolvedName = rn)
              case expr.EdgeAttribute(name, _, _, isAnonymous, _) => expr.EdgeAttribute(name, isAnonymous = isAnonymous, resolvedName = rn)
              case expr.EdgeListAttribute(name, _, _, isAnonymous, minHops, maxHops, _) => expr.EdgeListAttribute(name, isAnonymous = isAnonymous, resolvedName = rn, minHops = minHops, maxHops = maxHops)
              // handle PropertyAttribute chained from previous query part under some alias
              case expr.PropertyAttribute(name, elementAttribute, _) => expr.PropertyAttribute(name, elementAttribute, resolvedName = rn)
              case expr.UnwindAttribute(list, name, _) => expr.UnwindAttribute(list, name, resolvedName = rn)
              case expr.ExpressionAttribute(e, name, _) => expr.ExpressionAttribute(e, name, resolvedName = rn)
              // fallback for expressions: expression references get wrapped into ExpressionAttribute upon resolution
              case e: cExpr.Expression => expr.ExpressionAttribute(e, elementName, resolvedName = rn)
            }
          }
          case _ => throw new NameResolutionException(elementName)
        }

        if (nameParts.length == 1) {
          elementAttribute
        } else { // nameParts.length == 2
          val propertyName = nameParts(1) // should be OK as .length == 2
          elementAttribute match {
            // if nameParts.length == 2, base should always be an ElementAttribute
            case ea: expr.ElementAttribute =>
              expr.PropertyAttribute(propertyName, ea, snr(s"${ea.name}$$${propertyName}",
                expr.PropertyAttribute(propertyName, ea)) // this is a dirty hack to tell the resolver that we are about to resolve a PropertyAttribute instance
              )
            case x => throw new UnexpectedTypeException(x, "basis position of property dereferencing")
          }
        }
      }
      case _ => throw new CompilerException(s"Unexpected number of name parts, namely ${nameParts.length} for ${nameParts}")
    }
    // fallback: no-op resolution.
    case x => x
  }

  // function generator for name resolution shorthand: "scoped name resolution"
  def snrGen(nameResolverScope: TNameResolverCache): TScopedNameResolver = (baseName, target) => {
    val resolvedName: expr.types.TResolvedNameValue = nameResolverScope.get(baseName) match {
      case Some((rn, expr.UnwindAttribute(_, _, _))) => {
        // we don't have the result type info for UNWIND,
        // so our best guess is to use the first type we encounter in the current query type
        nameResolverScope.update(baseName, (rn, target))
        rn
      }
      case Some((rn, entry)) => if (entry.getClass != target.getClass) {
        throw new CompilerException(s"Name collision across types: ${baseName}. In the cache, it is ${entry.getClass}, but now it was passed as ${target.getClass}")
      } else {
        rn
      }
      case None => {
        val rn = expr.types.TResolvedNameValue(baseName, generateUniqueName(baseName))
        nameResolverScope.put(baseName, (rn, target))
        rn
      }
    }
    Some(resolvedName)
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
      val resolvedProjectList = projectList.map( pi => expr.ReturnItem(pi.child.transform(expressionResolver), pi.alias, pi.resolvedName) )
      projectionResolveHelper(resolvedProjectList, child)
    }
    case qplan.Selection(condition, child) => qplan.Selection(condition.transform(expressionResolver), child)
    case qplan.Sort(order, child) => qplan.Sort(
      order.map(_.transform(expressionResolver) match {
        case so: SortOrder => so
        case x => throw new UnexpectedTypeException(x, "sort items after resolution")
      })
      , child)
    case qplan.ThetaLeftOuterJoin(left, right, condition) => qplan.ThetaLeftOuterJoin(left, right, condition.transform(expressionResolver))
    case qplan.Top(skipExpr, limitExpr, child) => qplan.Top(
      TransformUtil.transformOption(skipExpr, expressionResolver)
      , TransformUtil.transformOption(limitExpr, expressionResolver)
      , child)
    case qplan.Unwind(expr.UnwindAttribute(collection, alias, resolvedName), child) => qplan.Unwind(expr.UnwindAttribute(collection.transform(expressionResolver), alias, resolvedName), child)
    // DML
    case qplan.UnresolvedDelete(attributes, detach, child) => qplan.Delete(resolveAttributes(attributes, child), detach, child)
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
  protected def resolveAttributes(attributes: Seq[cExpr.NamedExpression], child: qplan.QNode): Seq[ResolvableName] = {
    val transformedAttributes = attributes.flatMap( a => child.output.find( co => co.name == a.name ) )

    if (attributes.length != transformedAttributes.length) {
      throw new CompilerException(s"Unable to resolve all attributes. Resolved ${transformedAttributes.length} out of ${attributes.length}")
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
  protected def filterForAttributesOfChildOutput(attributes: Seq[ResolvableName], child: qplan.QNode, invert: Boolean = false): Seq[ResolvableName] = {
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
  protected def projectionResolveHelper(projectList: expr.types.TProjectList, child: QNode): UnaryQNode with ProjectionDescriptor = {
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

    val seenAggregate = projectList.foldLeft[Boolean](false)((b, a) => b || ({
      val projectItem = a.child
      // validate aggregation semantics: no aggregation function is allowed at non top-level
      projectItem.children.foreach( c => c.find(isAggregatingFunctionInvocation).fold[Unit](Unit)( e => throw new IllegalAggregationException(e.toString) ) )

      // see if this return item is an aggregation
      projectItem match {
        // FIXME: UnresolvedStar is also allowed until it is resolved
        case UnresolvedStar(_) => false
        case e: cExpr.Expression => if (isAggregatingFunctionInvocation(e)) {
          true
        } else {
          aggregationCriteriaCandidate += e
          false
        }
        // This should never be reached, as projectList is expr.types.TProjectList
        case x => throw new UnexpectedTypeException(x, "return item position")
      }
    }))

    if (seenAggregate) {
      qplan.Grouping(aggregationCriteriaCandidate, projectList, child)
    } else {
      qplan.Projection(projectList, child)
    }
  }
}
