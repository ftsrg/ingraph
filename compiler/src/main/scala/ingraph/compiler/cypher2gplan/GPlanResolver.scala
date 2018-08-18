package ingraph.compiler.cypher2gplan

import java.util.concurrent.atomic.AtomicLong

import ingraph.compiler.cypher2gplan.util.TransformUtil
import ingraph.compiler.exceptions.{CompilerException, IllegalAggregationException, NameResolutionException, UnexpectedTypeException}
import ingraph.model.expr.ResolvableName
import ingraph.model.gplan.{GNode, Grouping, Projection, UnaryGNode}
import ingraph.model.{expr, gplan, misc}
import org.apache.spark.sql.catalyst.analysis.{UnresolvedAttribute, UnresolvedFunction, UnresolvedStar}
import org.apache.spark.sql.catalyst.expressions.{Expression, SortOrder}
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan
import org.apache.spark.sql.catalyst.{expressions => cExpr}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object GPlanResolver {
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

  def resolveGPlan(unresolvedQueryPlan: gplan.GNode): gplan.GNode = {
    // should there be other rule sets (partial functions), combine them using orElse,
    // e.g. pfunc1 orElse pfunc2
    val resolvedNames = resolveNamesForGNode(unresolvedQueryPlan)
    val resolved = resolvedNames.transform(gplanResolver).asInstanceOf[gplan.GNode]

//    val elements = unresolvedQueryPlan.flatMap {
//      case gplan.GetVertices(v) => Some(v)
//      case gplan.Expand(src, trg, edge, _, _) => Some(edge, trg)
//      case _ => None
//    }

    resolved
  }

  /**
    * This is to resolve the names in expressions of GPlan.
    */
  private def resolveNamesForGNode(q: gplan.GNode): gplan.GNode = {
    // this holds the GNode's to be processed to create build the reverse Polish Notation of the tree
    val qNodeStack = mutable.Stack[gplan.GNode](q)
    // this holds the gplan in reverse polish notation, i.e. stack head has the leafs while its bottom element should hold the root node
    val gPlanPolishNotation = mutable.Stack[gplan.GNode]()
    // this will hold the operand stack for the Polish Notation evaluation algorithm used to rebuild the tree
    val operandStack = mutable.Stack[gplan.GNode]()

    // create the reverse polish notation in the stack gPlanPolishNotation
    while (qNodeStack.nonEmpty) {
      val n = qNodeStack.pop
      gPlanPolishNotation.push(n)
      n match {
        case u: gplan.UnaryGNode => qNodeStack.push(u.child)
        /*
         * Ensure that right child comes out first, so it will be closer in gPlanPolishNotation to n itself,
         * meaning that in "evaluation time" (i.e. for the sake of name resolution), left child will be evaluated first.
         *
         * This is important as query parts (those that end at WITH or RETURN clause) are assembled to form a left deep tree of join nodes
         *
         * This also means that name resolution builds on the operand order of joins, so before name resolution, joins are non-commutative
         */
        case b: gplan.BinaryGNode => qNodeStack.push(b.left, b.right)
        case _: gplan.LeafGNode => {}
        case x => throw new UnexpectedTypeException(x, "GPlan tree")
      }
    }

    reAssembleTreeResolvingNames(gPlanPolishNotation, operandStack)

    // return the re-constructed tree
    operandStack.length match {
      case 1 => operandStack.pop
      case _ => throw new CompilerException(s"A single item expected in the stack after re-assembling the GNode tree at name resolution. Instead, it has ${operandStack.length} entries.")
    }
  }

  protected def reAssembleTreeResolvingNames(gPlanPolishNotation: mutable.Stack[gplan.GNode], operandStack: mutable.Stack[gplan.GNode]) = {
    // this will hold those names that are in scope, so no new resolution should be invented
    var nameResolverCache = new TNameResolverCache
    // HACK: this will hold the previous name resolving scope, which will be used for Sort and Top operators
    var oldNameResolverScope: Option[TNameResolverCache] = None
    // scoped name resolver shorthand
    def r[T <: Expression](v: T): T = r2(v, nameResolverCache)
    // scoped name resolver shorthand allowing to pass in the nameResolverCache
    def r2[T <: Expression](v: T, nrs: TNameResolverCache): T = v.transform(expressionNameResolver(new SNR(nrs))).asInstanceOf[T]

    // re-assemble the tree resolving names
    while (gPlanPolishNotation.nonEmpty) {
      val newGNode: gplan.GNode = gPlanPolishNotation.pop match {
        case b: gplan.BinaryGNode => {
          val rightChild = operandStack.pop
          val leftChild = operandStack.pop
          val newOp: gplan.BinaryGNode = b match {
            case gplan.Union(all, _, _) => gplan.Union(all, leftChild, rightChild)
            case gplan.Join(_, _) => gplan.Join(leftChild, rightChild)
            case gplan.LeftOuterJoin(_, _) => gplan.LeftOuterJoin(leftChild, rightChild)
            case gplan.ThetaLeftOuterJoin(_, _, condition) => gplan.ThetaLeftOuterJoin(leftChild, rightChild, r(condition))
            // case AntiJoin skipped because it is introduced later in the beautification step
          }
          newOp
        }
        case u: gplan.UnaryGNode => {
          val child = operandStack.pop
          val newOp: gplan.UnaryGNode = u match {
            case gplan.AllDifferent(e, _) => gplan.AllDifferent(e.map(r(_)), child)
            case gplan.DuplicateElimination(_) => gplan.DuplicateElimination(child)
            case gplan.Expand(src, trg, edge, dir, _) => gplan.Expand(r(src), r(trg), r(edge), dir, child)
            // resolve names in listexpression, then resolve the unwindattribute itself
            case gplan.Unwind(ua, _) => gplan.Unwind(r(expr.UnwindAttribute(r(ua.list), ua.name, ua.resolvedName)), child)
            case gplan.Production(_) => gplan.Production(child)
            case gplan.UnresolvedProjection(projectList, _) => {
              // initialize new namespace applicable after the projection operator
              val nextQueryPartNameResolverCache = new TNameResolverCache
              val nextSnr = new SNR(nextQueryPartNameResolverCache)
              val resolvedProjectListBuf = ListBuffer.empty[expr.ReturnItem]
              for (ri <- projectList) {
                val resolvedChild = r(ri.child)
                val resolvedName: expr.types.TResolvedName = ri.alias match {
                  case Some(alias) => nextSnr.resolve(alias, resolvedChild)
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
                    case rc => nextSnr.resolve("_expr", rc)
                  }
                }
                resolvedProjectListBuf.append(expr.ReturnItem(resolvedChild, ri.alias, resolvedName))
              }
              val resolvedProjectList: expr.types.TProjectList = resolvedProjectListBuf.toSeq

              // retain old name resolver scope
              oldNameResolverScope = Some(nameResolverCache)
              nameResolverCache = nextQueryPartNameResolverCache
              gplan.UnresolvedProjection(resolvedProjectList, child)
            }
            // case {Projection, Grouping} skipped because it is introduced in a later resolution stage
            case gplan.Selection(condition, _) => gplan.Selection(r(condition), child)
            case gplan.Sort(order, _) => gplan.Sort(order.map( _ match {
              case cExpr.SortOrder(sortExpr, dir, no, se) => try {
                cExpr.SortOrder(r(sortExpr), dir, no, se)
              } catch {
                // in case of name resolution problem, we fall back to the last name resolution scope, if available. If again can't resolve, we throw the exception
                case nre: NameResolutionException => cExpr.SortOrder(r2(sortExpr, oldNameResolverScope.getOrElse(throw nre)), dir, no, se)
              }
            }), child)
            case gplan.Top(skipExpr, limitExpr, _) => gplan.Top(skipExpr, limitExpr, child)
            case gplan.Create(attributes, _) => gplan.Create(attributes.map(r(_)), child)
            case gplan.UnresolvedDelete(attributes, detach, _) => gplan.UnresolvedDelete(attributes, detach, child)
            case gplan.Merge(attributes, _) => gplan.Merge(attributes.map(r(_)), child)
            case gplan.SetNode(vertexLabelUpdates, _) => gplan.SetNode(vertexLabelUpdates, child)
            case gplan.Remove(vertexLabelUpdates, _) => gplan.Remove(vertexLabelUpdates, child)
          }
          newOp
        }
        case l: gplan.LeafGNode => {
          val newOp: gplan.LeafGNode = l match {
            case gplan.GetVertices(v) => gplan.GetVertices(r(v))
            case x => x
          }
          newOp
        }
      }
      operandStack.push(newGNode)
    }
  }

  def expressionNameResolver(snr: SNR): PartialFunction[Expression, Expression] = {
    case rn: expr.ResolvableName =>
      if (rn.resolvedName.isDefined) rn // do not resolve already resolved stuff again
      else rn match {
        case expr.VertexAttribute (name, labels, properties, isAnonymous, _) => expr.VertexAttribute(name, labels, properties, isAnonymous, snr.resolve(name, rn))
        case expr.EdgeAttribute(name, labels, properties, isAnonymous, _) => expr.EdgeAttribute(name, labels, properties, isAnonymous, snr.resolve(name, rn))
        case expr.RichEdgeAttribute(src, trg, edge, dir) => expr.RichEdgeAttribute(
          src.transform(expressionNameResolver(snr)).asInstanceOf[expr.VertexAttribute],
          trg.transform(expressionNameResolver(snr)).asInstanceOf[expr.VertexAttribute],
          edge.transform(expressionNameResolver(snr)).asInstanceOf[expr.EdgeAttribute],
          dir
        )
        case expr.EdgeListAttribute(name, labels, properties, isAnonymous, minHops, maxHops, _) => expr.EdgeListAttribute(name, labels, properties, isAnonymous, minHops, maxHops, snr.resolve(name, rn))
        case expr.PropertyAttribute(name, elementAttribute, _) => expr.PropertyAttribute(name,
          // see "scoped name resolver shorthand" above
          elementAttribute.transform(expressionNameResolver(snr)).asInstanceOf[expr.ElementAttribute],
          snr.resolve(s"${elementAttribute.name}$$${name}", rn))
        case expr.UnwindAttribute(list, name, _) => expr.UnwindAttribute(list, name, snr.resolve(name, rn))
      }
    case UnresolvedAttribute(nameParts) => nameParts.length match {
      case 1 | 2 => {
        val elementName = nameParts(0) // should be OK as .length >= 1
        val scopeCacheEntry = snr.nameResolverCache.get(elementName)
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
              expr.PropertyAttribute(propertyName, ea, snr.resolve(s"${ea.name}$$${propertyName}",
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

  /**
    * Scoped name resolver that holds its cache it utilizes.
    * @param pNameResolverCache the name resolution cache to utilize
    */
  class SNR(pNameResolverCache: TNameResolverCache) {
    def nameResolverCache = pNameResolverCache
    def resolve: TScopedNameResolver = (baseName, target) => {
      val resolvedName: expr.types.TResolvedNameValue = pNameResolverCache.get(baseName) match {
        // we don't have the result type info for UNWIND and single index lookup expressions
        // so our best guess is to use the first type we encounter in the current query type
        case Some((rn, expr.UnwindAttribute(_, _, _))) => {
          pNameResolverCache.update(baseName, (rn, target))
          rn
        }
        case Some((rn, expr.IndexLookupExpression(_, _))) => {
          pNameResolverCache.update(baseName, (rn, target))
          rn
        }
        case Some((rn, entry)) => if (entry.getClass != target.getClass) {
          throw new CompilerException(s"Name collision across types: ${baseName}. In the cache, it is ${entry.getClass}, but now it was passed as ${target.getClass}")
        } else {
          rn
        }
        case None => {
          val rn = expr.types.TResolvedNameValue(baseName, SNR.generateUniqueName(baseName))
          pNameResolverCache.put(baseName, (rn, target))
          rn
        }
      }
      Some(resolvedName)
    }
  }

  object SNR {
    // always use .getAndIncrement on this object
    private val generatedNameCounterMap = mutable.Map[String, AtomicLong]()

    def generateUniqueName(baseName: String): String = {
      s"${baseName}#${generatedNameCounterMap.getOrElseUpdate(baseName, new AtomicLong).getAndIncrement}"
    }
  }


  /**
    * These are the resolver rules that applies to all unresolved GPlans.
    *
    * There are some nodes that do not need resolution: GetVertices, DuplicateElimination, Expand, Join, Union, etc.
    */
  val gplanResolver: PartialFunction[LogicalPlan, LogicalPlan] = {
    // Unary, compound
    case gplan.Top(skipExpr, limitExpr, gplan.Sort(order, gplan.UnresolvedProjection(projectList, child))) =>
      sortProjectResolveHelper(order, projectList, child, Some(topResolveHelper(skipExpr, limitExpr, gplan.GStub())))
    case gplan.Sort(order, gplan.UnresolvedProjection(projectList, child)) => sortProjectResolveHelper(order, projectList, child, None)
    // Unary
    case gplan.UnresolvedProjection(projectList, child) => {
      val resolvedProjectList = projectList.map( pi => expr.ReturnItem(pi.child.transform(expressionResolver), pi.alias, pi.resolvedName) )
      projectionResolveHelper(resolvedProjectList, child)
    }
    case gplan.Selection(condition, child) => gplan.Selection(condition.transform(expressionResolver), child)
    case gplan.Sort(order, child) => sortResolveHelper(order, child)
    case gplan.ThetaLeftOuterJoin(left, right, condition) => gplan.ThetaLeftOuterJoin(left, right, condition.transform(expressionResolver))
    case gplan.Top(skipExpr, limitExpr, child) => topResolveHelper(skipExpr, limitExpr, child)
    case gplan.Unwind(expr.UnwindAttribute(collection, alias, resolvedName), child) => gplan.Unwind(expr.UnwindAttribute(collection.transform(expressionResolver), alias, resolvedName), child)
    // DML
    case gplan.UnresolvedDelete(attributes, detach, child) => gplan.Delete(resolveAttributes(attributes, child), detach, child)
    case gplan.Create(attributes, child) => gplan.Create(filterForAttributesOfChildOutput(attributes, child, invert=true), child)
  }

  val expressionResolver: PartialFunction[Expression, Expression] = {
    case expr.ExpressionAttribute(expression, name, rn) => expr.ExpressionAttribute(expression.transform(expressionResolver), name, rn)
    case UnresolvedFunction(functionIdentifier, children, isDistinct) => expr.FunctionInvocation(misc.Function.fromCypherName(functionIdentifier.identifier, children.length, isDistinct), children, isDistinct)
  }

  /**
    * Resolve attribute references according to the output schema of the child GNode
    * @param attributes
    * @param child
    * @return
    */
  protected def resolveAttributes(attributes: Seq[cExpr.NamedExpression], child: gplan.GNode): Seq[expr.ResolvableName] = {
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
  protected def filterForAttributesOfChildOutput(attributes: Seq[expr.ResolvableName], child: gplan.GNode, invert: Boolean = false): Seq[expr.ResolvableName] = {
    attributes.flatMap( a => if ( invert.^(child.output.exists( co => co.name == a.name )) ) Some(a) else None )
  }


  private def topResolveHelper(skipExpr: Option[Expression], limitExpr: Option[Expression], child: GNode) = {
    gplan.Top(
      TransformUtil.transformOption(skipExpr, expressionResolver)
      , TransformUtil.transformOption(limitExpr, expressionResolver)
      , child)
  }

  private def sortProjectResolveHelper(order: Seq[SortOrder], projectList: expr.types.TProjectList, child: GNode, topOp: Option[gplan.Top]) = {
    val resolvedProjectList = projectList.map(pi => expr.ReturnItem(pi.child.transform(expressionResolver), pi.alias, pi.resolvedName))
    projectionResolveHelper(resolvedProjectList, child) match {
      case g: Grouping => {
        val newSortOp = sortResolveHelper(order, g)
        // TODO: check if order by refers only to the result of grouping
        newSortOp
      }
      case p: Projection => {
        val newSortOp = sortResolveHelper(order, p)
        // find resolved names that the sort involves but the projection hides
        val additionalSortItems = newSortOp.order.filterNot(so => so.child match {
          case rn: ResolvableName => p.projectList.foldLeft(false)((acc, ri) => acc || ri.resolvedName == rn.resolvedName)
          case _ => true
        })
        if (additionalSortItems.isEmpty) {
          // no resolved names needed for the sorting were hidden by the projection, so we return that unmodified
          newSortOp
        } else {
          // we build a more loose projection, then sort, then fall back to the projection originally requested.
          val innerSortOp = gplan.Sort(newSortOp.order,
            gplan.Projection(p.projectList ++ additionalSortItems.map(so => {
              so.child match {
                case rn: ResolvableName => expr.ReturnItem(so.child, None, rn.resolvedName)
                case x => throw new UnexpectedTypeException(x, "we were filtering for resolvedNames in sort list")
              }
            }),
              p.child
            )
          )

          gplan.Projection(p.projectList,
            topOp.fold[GNode](innerSortOp)( (top) => gplan.Top(top.skipExpr, top.limitExpr, innerSortOp))
          )
        }
      }
    }
  }

  private def sortResolveHelper(order: Seq[SortOrder], child: GNode) = {
    gplan.Sort(
      order.map(_.transform(expressionResolver) match {
        case so: SortOrder => so
        case x => throw new UnexpectedTypeException(x, "sort items after resolution")
      })
      , child)
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
  protected def projectionResolveHelper(projectList: expr.types.TProjectList, child: GNode): UnaryGNode with expr.ProjectionDescriptor = {
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

    val aggregationCriteriaCandidate: Seq[cExpr.Expression] = projectList.flatMap( returnItem => {
      val returnItemExpression = returnItem.child

      // validate aggregation semantics: no aggregation function is allowed at non top-level
      returnItemExpression.children.foreach( c => c.find(isAggregatingFunctionInvocation).fold[Unit](Unit)( e => throw new IllegalAggregationException(e.toString) ) )

      // see if this return item is an aggregation
      returnItemExpression match {
        // FIXME: UnresolvedStar is also allowed until it is resolved
        case UnresolvedStar(_) => Some(returnItemExpression)
        case e: cExpr.Expression => if (isAggregatingFunctionInvocation(e)) {
          None
        } else {
          Some(e)
        }
        // This should never be reached, as projectList is expr.types.TProjectList
        case x => throw new UnexpectedTypeException(x, "return item position")
      }
    })

    // FIXME: when resolving UnresolvedStar, this needs revising
    if (aggregationCriteriaCandidate.length != projectList.length) {
      gplan.Grouping(aggregationCriteriaCandidate, projectList, child)
    } else {
      gplan.Projection(projectList, child)
    }
  }
}
