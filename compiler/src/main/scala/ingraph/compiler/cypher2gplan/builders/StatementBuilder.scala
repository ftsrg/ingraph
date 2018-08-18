package ingraph.compiler.cypher2gplan.builders

import ingraph.compiler.cypher2gplan.structures.MatchDescriptor
import ingraph.compiler.cypher2gplan.util.GrammarUtil
import ingraph.model.gplan.GNode
import ingraph.model.{expr, gplan}
import org.slizaa.neo4j.opencypher.{openCypher => oc}

import scala.collection.JavaConverters._
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object StatementBuilder {
  def dispatchBuildStatement(statement: oc.Statement): gplan.GNode = {
    statement match {
      case s: oc.SingleQuery => buildStatement(s)
      case s: oc.CombinedQuery => buildStatement(s)
    }
  }

  /**
    * Transform a SinglePartQuery to a sequence of clauses.
    */
  def siglePartQuery2Clauses(spq: oc.SinglePartQuery): Seq[oc.Clause] = {
    spq.getReadingClauses.asScala ++ spq.getUpdatingClauses.asScala ++ Option(spq.getReturn)
  }

  /**
    * Transform a MultiPartSubQuery(*) to a sequence of clauses.
    *
    * (*): MultiPartSubQuery is a structural sugar in the openCypher Xtext grammar
    *      not present in the original openCypher grammar
    */
  def multiPartSubQuery2Clauses(mpsq: oc.MultiPartSubQuery): Seq[oc.Clause] = {
      mpsq.getReadingClauses.asScala ++ mpsq.getUpdatingClauses.asScala :+ mpsq.getWithPart
  }

  /** Builds the gplan for a single query built from several query parts.
    */
  def buildStatement(q: oc.SingleQuery): gplan.GNode = {
    // Compiled form of the query is the chain of the query parts.
    // The first query part will receive a dual object source there.
    // Accumulate tree built from successive query parts in result
    var result: gplan.GNode = gplan.Dual()

    // Do some checks on the clauses of the single query
    //FIXME: Validator.checkSingleQueryClauseSequence(clauses, ce.l)
    // A query part should have the form (MATCH*)((CREATE|DELETE)+ RETURN?|(WITH UNWIND?)|UNWIND|RETURN)

    q match {
      case spq: oc.SinglePartQuery => {
        val clauses: Seq[oc.Clause] = siglePartQuery2Clauses(spq)
        result = buildQueryPart(clauses, result)
      }
      case mpq: oc.MultiPartQuery => {
        for (queryPart <- mpq.getSubQueries.asScala) {
          result = buildQueryPart(multiPartSubQuery2Clauses(queryPart), result)
        }

        result = buildQueryPart(siglePartQuery2Clauses(mpq.getSinglePartQuery), result)
      }
    }

    result
  }

  def buildStatement(q: oc.CombinedQuery): gplan.GNode = {
    var result = buildStatement(q.getSingleQuery)

    for (u <- q.getUnion.asScala) {
      result = gplan.Union(u.isAll, result, buildStatement(u.getSingleQuery))
    }

    // FIXME: validator
    //		if (!Validator.checkIfUnionQueryColumnNamesMatch(result, ce.l)) {
    //			ce.l.unrecoverableError('''All sub queries of a UNION query must have the same column aliases.''')
    //		}

    result
  }

  /** This is the workhorse for building the graph relational algebra expression for a single query part.
    *
    * @param chain is the compiled form of the query parts processed so far. This will be put at the appropriate place.
    */
  protected def buildQueryPart(clauses: Seq[oc.Clause], chain: gplan.GNode): gplan.GNode = {
//    // FIXME: do some checks on the MATCH clauses
//    Validator.checkSubQueryMatchClauseSequence(clauses.filter(typeof(Match)), ce.l)
//    // FIXME: do some checks on the clause sequence of this subquery
//    Validator.checkSubQueryClauseSequence(clauses, ce.l)

    /* We compile all clauses and chain them together. In case of MATCH caluses, we use different join operations.
     * We start from what was passed in as chain, i.e. the tree built from query parts so far.
     *
     * Note: we don't care about possible repetition of WITH/RETURN and UNWIND clauses or any other invalid sequences
     * as it does not affect compilation.
     * Clause order is already enforced by the M10+ grammar.
     */
    val content: GNode = clauses.foldLeft(chain)(
      (prev, clause) => clause match {
        case m: oc.Match => buildMatchDescriptor(m) match {
          case MatchDescriptor(true , opTree, Some(condition)) => gplan.ThetaLeftOuterJoin(prev, opTree, condition)
          case MatchDescriptor(true , opTree, None           ) => gplan.LeftOuterJoin(prev, opTree)
          case MatchDescriptor(false, opTree, Some(condition)) => gplan.Selection(condition, gplan.Join(prev, opTree))
          case MatchDescriptor(false, opTree, None           ) => gplan.Join(prev, opTree)
        }
        case w: oc.With   => ReturnBuilder.dispatchBuildReturn(w, prev)
        case r: oc.Return => ReturnBuilder.dispatchBuildReturn(r, prev)
        case u: oc.Unwind => {
          val expression = ExpressionBuilder.buildExpressionNoJoinAllowed(u.getExpression)
          gplan.Unwind(expr.UnwindAttribute(expression, u.getVariable.getName), prev)
        }
        case c: oc.Create => CudBuilder.buildCreateOperator(c, prev)
        case c: oc.Delete => CudBuilder.buildDeleteOperator(c, prev)
        case c: oc.Merge  => CudBuilder.buildMergeOperator (c, prev)
        case c: oc.Remove => CudBuilder.buildRemoveOperator(c, prev)
        case c: oc.Set    => CudBuilder.buildSetOperator   (c, prev)
      }
    )

    // return the result of query part compilation
    content
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
    val optionalMatch = m.isOptional

    val edgeAttributesOfMatchClause = mutable.HashSet.empty[expr.AbstractEdgeAttribute]
    // handle comma-separated patternParts in the MATCH clause
    val pattern_PatternPartList = ListBuffer.empty[gplan.GNode]
    for (pattern <- m.getPattern.getPatterns.asScala) {
      val op = PatternBuilder.buildPattern(pattern)

      edgeAttributesOfMatchClause ++= op.flatMap[expr.AbstractEdgeAttribute]( _ match {
        case e: gplan.Expand => Seq(e.edge)
        case _ => Seq.empty
      })

      pattern_PatternPartList += op
    }

    // they are natural joined together
    val allDifferentOperator = gplan.AllDifferent(
      edgeAttributesOfMatchClause.toSeq,
      pattern_PatternPartList.foldLeft[gplan.GNode]( gplan.Dual() )((b, a) => gplan.Join(b, a) )
    )

    Option(m.getWhere).fold( MatchDescriptor(optionalMatch, allDifferentOperator) )(
      (where)  => {
        // left outer joins extracted from the patterns in the where clause
        val joinOperationsOfWhereClause = ListBuffer.empty[gplan.GNode]

        val condition = Some(ExpressionBuilder.buildExpression(where.getExpression, joinOperationsOfWhereClause))

        /*
         * add allDifferentOperator before the joins derived from the where clause (if any)
         * and create the tree of left outer joins
         */
        val op = joinOperationsOfWhereClause.foldLeft[gplan.GNode]( allDifferentOperator )((b, a) => gplan.LeftOuterJoin(b, a) )

        MatchDescriptor(optionalMatch, op, condition)
      }
    )
  }
}
