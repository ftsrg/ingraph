package ingraph.compiler.cypher2qplan.builders

import ingraph.compiler.cypher2qplan.structures.MatchDescriptor
import ingraph.compiler.cypher2qplan.util.GrammarUtil
import ingraph.model.qplan.QNode
import ingraph.model.{expr, qplan}
import org.apache.spark.sql.catalyst.analysis.UnresolvedAlias
import org.slizaa.neo4j.opencypher.openCypher.{Create, Delete, Merge, Remove, Set, Unwind}
import org.slizaa.neo4j.opencypher.{openCypher => oc}

import scala.collection.JavaConverters._
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object StatementBuilder {
  def dispatchBuildStatement(statement: oc.Statement): qplan.QNode = {
    statement match {
      case s: oc.SingleQuery => buildStatement(s)
      case s: oc.RegularQuery => buildStatement(s)
    }
  }

  /**
    * Builds the qplan for a single query built from several query parts.
    */
  def buildStatement(q: oc.SingleQuery): qplan.QNode = {
    val clauses = q.getClauses.asScala.toVector // we are to use indexed access

    // Compiled form of the query is the chain of the query parts.
    // The first query part will receive a dual object source there.
    // Accumulate tree built from successive query parts in result
    var result: qplan.QNode = qplan.Dual()

    // Do some checks on the clauses of the single query
    //FIXME: Validator.checkSingleQueryClauseSequence(clauses, ce.l)

    /**
      * Process each query part.
      *
      * A query part has the form (MATCH*)((CREATE|DELETE)+ RETURN?|(WITH UNWIND?)|UNWIND|RETURN)
      *
      * Note: GrammarUtil.isCudClause is currently limited to CREATE and DELETE clauses.
      */
    var from = 0
    for (i <- clauses.indices) {
      val current = clauses(i)
      val next = if (i + 1 < clauses.length) {
        clauses(i + 1)
      } else {
        null
      }
      if (GrammarUtil.isCudClause(current) && ! GrammarUtil.isCudClause(next) && ! next.isInstanceOf[oc.Return]
        || current.isInstanceOf[oc.With] && ! next.isInstanceOf[oc.Unwind]
        || current.isInstanceOf[oc.Unwind]
        || current.isInstanceOf[oc.Return]
      ) {
        // [fromX, toX) is the range of clauses that form a subquery
        val fromX = from
        val toX = i + 1

        result = buildQueryPart(clauses.slice(fromX, toX), result)

        from = i + 1
      }
    }

    result
  }

  def buildStatement(q: oc.RegularQuery): qplan.QNode = {
    // the Xtext grammar actually produces a left deep parse tree of a union
    // so this might be wither a SingleQuery or a RegularQuery, in case of more union's
    var result = dispatchBuildStatement(q.getSingleQuery)

    for (u <- q.getUnion.asScala) {
      result = qplan.Union(result, dispatchBuildStatement(u.getSingleQuery))
    }

    // FIXME: validator
    //		if (!Validator.checkIfUnionQueryColumnNamesMatch(result, ce.l)) {
    //			ce.l.unrecoverableError('''All sub queries of a UNION query must have the same column aliases.''')
    //		}

    result
  }

  /**
    * This is the workhorse for building the graph relational algebra expression for a single query part.
    *
    * @param chain is the compiled form of the query parts processed so far. This will be put at the appropriate place.
    */
  protected def buildQueryPart(clauses: Seq[oc.Clause], chain: qplan.QNode): qplan.QNode = {

//    // FIXME: do some checks on the MATCH clauses
//    Validator.checkSubQueryMatchClauseSequence(clauses.filter(typeof(Match)), ce.l)
//    // FIXME: do some checks on the clause sequence of this subquery
//    Validator.checkSubQueryClauseSequence(clauses, ce.l)
//
    /*
     * We compile all MATCH clauses and attach to a (left outer) join operator.
     * The first one will not be used to chain match clauses together,
     * but it will be used to chain query parts together.
     * In case of the first query part, a dual source will be put there
     * when chaining together query parts (in buildStatement(oc.SingleQuery) parent call).
     */
    val q_MatchList = clauses.flatMap{
      case m: oc.Match => Some(buildMatchDescriptor(m))
      case _ => None
    }
//          .filter( c => c.isInstanceOf[oc.Match] )
//          .map( m => buildMatchDescriptor(m.asInstanceOf[oc.Match]) )

    /*
     * content will be built starting from what is passed in as chain, i.e. the tree built from query parts so far.
     *
     * This is achieved by starting from a Join(chain, Dual)
     */
    val content = q_MatchList.foldLeft(
      chain //qplan.Join(chain, qplan.Dual())
      )(
        (b, a) => a match {
          case md if  md.isOptional &&  md.hasCondition => qplan.ThetaLeftOuterJoin(b, md.op.get, md.condition.get)
          case md if  md.isOptional && !md.hasCondition => qplan.LeftOuterJoin(b, md.op.get)
          case md if !md.isOptional &&  md.hasCondition => qplan.Selection(md.condition.get, qplan.Join(b, md.op.get))
          case md if !md.isOptional && !md.hasCondition => qplan.Join(b, md.op.get)
        }
      )

    val singleQuery_returnOrWithClause = clauses.flatMap{
      case w: oc.With => Some(w)
      case r: oc.Return => Some(r)
      case _ => None
    }
    val afterReturn = if (singleQuery_returnOrWithClause.isEmpty) {
      content
    } else {
      ReturnBuilder.dispatchBuildReturn(singleQuery_returnOrWithClause.head, content)
    }

    //FIXME: continue processing clauses
    //return afterReturn

    // .filter( c => c.isInstanceOf[oc.With] || c.isInstanceOf[oc.Return] )
    val singleQuery_unwindClauseList: Seq[Unwind] = clauses.flatMap{ case u: Unwind => Some(u) case _ => None }
    // TODO handle multiple UNWINDs

//    val content = q_MatchList.foldLeft[qplan.QNode](
//      chain //qplan.Join(chain, qplan.Dual())
//    )(
//      (b, a) => a match {
//        case md if  md.isOptional &&  md.hasCondition => qplan.ThetaLeftOuterJoin(b, md.op.get, md.condition.get)
//      }
//    )

    // there might be multiple
    val afterUnwind: QNode = singleQuery_unwindClauseList.foldLeft(afterReturn)(
      (prev, unwind) => {
        val expr = ExpressionBuilder.buildExpressionNoJoinAllowed(unwind.getExpression)
        val variable = AttributeBuilder.buildAttribute(unwind.getVariable)
        qplan.Unwind(UnresolvedAlias(expr), variable, prev)
      }
    )

    // process CUD operations
    val afterCud = clauses.foldLeft(afterUnwind)(
      (prev, clause) => clause match {
        case c: Create => ??? // CudBuilder.buildCreateOperator(c, prev)
        case d: Delete => CudBuilder.buildDeleteOperator(d, prev)
        case m: Merge => ???
        case r: Remove => ???
        case s: Set => ???
        case _ => prev
      }
    )


    //    val afterCud = {
//      var Operator op = afterUnwind
//
//      for (cudClause: clauses.filter([GrammarUtil.isCudClause(it)])) {
//        op = switch cudClause {
//          Create: CudBuilder.buildCreateOperator(cudClause, op, ce)
//          Delete: CudBuilder.buildDeleteOperator(cudClause, op, ce)
//          default: {
//            ce.l.unsupported('''Currently we only support CREATE and DELETE of the possible CUD operations. Found: «cudClause.class.name».''')
//            null
//          }
//        }
//      }
//
//      op
//    }
    return afterCud
  }

  /**
    * MATCH clause is compiled as follows:
    * (the lower elements being the input for the upper ones)
    *
    * - Left outer join of the patterns extracted from the where clause (is any)
    * - AllDifferentOperator on the edges in the patternParts
    * - natural join of comma-separated patternParts in the MATCH clause
    *
    * Also a filter boolean condition is built from the where clause.
    * In case no WHERE condition applies, condition is null meaning no filtering,
    * i.e. pass through all records.
    *
    * @return a MatchDescriptor,
    *         whose op attribute is the compiled form of the MATCH without the WHERE,
    *         and condition attribute holds the filter condition.
    */
  def buildMatchDescriptor(m: oc.Match): MatchDescriptor = {
    val result = new MatchDescriptor()

    result.optional = Some(m.isOptional)

    val edgeAttributesOfMatchClause = mutable.HashSet.empty[expr.EdgeAttribute]
    // handle comma-separated patternParts in the MATCH clause
    val pattern_PatternPartList = ListBuffer.empty[qplan.QNode]
    for (pattern <- m.getPattern.getPatterns.asScala) {
      val op = PatternBuilder.buildPattern(pattern)

      //FIXME: edgeVariablesOfMatchClause += Cypher2RelalgUtil.extractEdgeVariables(op)

      pattern_PatternPartList += op
    }

    // they are natural joined together
    val allDifferentOperator = qplan.AllDifferent(
      edgeAttributesOfMatchClause.toSeq,
      pattern_PatternPartList.foldLeft[qplan.QNode]( qplan.Dual() )( (b, a) => qplan.Join(b, a) )
    )

    if (m.getWhere != null) {
      // left outer joins extracted from the patterns in the where clause
      val joinOperationsOfWhereClause = ListBuffer.empty[qplan.QNode]

      result.condition = Some(ExpressionBuilder.buildExpression(m.getWhere.getExpression, joinOperationsOfWhereClause))
        //FIXME: LogicalExpressionBuilder.buildLogicalExpression(m.where.expression, joinOperationsOfWhereClause, ce)

      result.op = if (joinOperationsOfWhereClause.isEmpty) {
        Some(allDifferentOperator)
      } else {
        /*
         * add allDifferentOperator before the joins derived from the where clause
         * and create the tree of left outer joins
         */
        Some(
          joinOperationsOfWhereClause.foldLeft[qplan.QNode]( allDifferentOperator )( (b, a) => qplan.LeftOuterJoin(b, a) )
        )
      }
    } else {
      result.op =  Some(allDifferentOperator)
    }

    result
  }
}
