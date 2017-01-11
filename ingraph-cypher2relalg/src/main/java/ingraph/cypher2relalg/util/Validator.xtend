package ingraph.cypher2relalg.util

import java.util.LinkedList
import java.util.List
import org.slizaa.neo4j.opencypher.openCypher.Match
import relalg.Operator
import relalg.ProjectionOperator
import relalg.UnaryOperator
import relalg.UnionOperator

class Validator {
  /**
   * Some checks for a single query's MATCH clauses.
   *
   * Note: pass all the MATCH clauses in sequence to allow for proper checking.
   *
   * Checks performed:
   * - Currently we support only single queries having at least 1 MATCH caluse
   * - We don't yet support starting with an OPTIONAL MATCH
   * - It is invalid to have MATCH after OPTIONAL MATCH.
   */
  def static void checkMatchClauseSequence(Iterable<Match> singleQuery_MatchList, IngraphLogger logger) {
    val head = singleQuery_MatchList?.head
    if (head == null) {
      logger.unsupported('''Currently we support only single queries having at least 1 MATCH caluse''')
    } else if (head.optional) {
      // FIXME: #40
      logger.unsupported("We don't yet support starting with an OPTIONAL MATCH")
    } else {
      var seenOptionalMatch = false
      for (Match o : singleQuery_MatchList) {
        if (o.optional) {
          seenOptionalMatch = true
        } else if (seenOptionalMatch) {
          logger.unrecoverableError('It is invalid to have MATCH after OPTIONAL MATCH.')
        }
      }
    }
  }

  /**
   * For a union query, this checks if all operands of the union has the same column names
   * as required by the openCypher.
   *
   * We expect a tree that on each branches from root, the following pattern matches:
   * <pre>UnionOperator* (UnaryOperator not ProjectionOperator)* ProjectionOperator</pre>
   *
   * When the root is not a UnionOperator, we assume it is valid, and return true.
   */
  def static checkIfUnionQueryColumnNamesMatch(Operator query, IngraphLogger logger) {
    if (query instanceof UnionOperator) {
      val columnNamesList = new LinkedList<List<String>>
      val fifo = new LinkedList<Operator>

      fifo.add(query)
      while (!fifo.empty) {
        val el = fifo.removeFirst
        if (el instanceof UnionOperator) {
          fifo.add(el.leftInput)
          fifo.add(el.rightInput)
        } else if (el instanceof UnaryOperator) {
          // find the projection operator down the UnaryOperator chain
          var Operator p = el
          while ((p instanceof UnaryOperator) && !(p instanceof ProjectionOperator)) {
            p = (p as UnaryOperator).input
          }
          if (p instanceof ProjectionOperator) {
            columnNamesList.add(getColumnNames(p, logger))
          } else {
            logger.unrecoverableError('''Expected to find a projection operator, but found «p.class.name» instead.''')
          }
        } else {
          logger.unrecoverableError('''Unexpected operator received when checking column names in a union query: «el.class.name»''')
        }
      }
      if (columnNamesList.size<2) {
        logger.unrecoverableError('''It is strange that we found only «columnNamesList.size» column names list under a union operator tree. We expected at least 2.''')
      }
      val refList = columnNamesList.removeFirst
      var result = true
      for (currList: columnNamesList) {
        if (refList.size != currList.size) {
          result=false
          logger.warning('''All sub queries of a UNION must have the same number of output columns.''')
          return result
        } else {
          for(var i=0; i<refList.size; i++) {
            if (!refList.get(i).equals(currList.get(i))) {
              result = false
              return result
            }
          }
        }
      }
      result
    } else {
      return true
    }
  }

  /**
   * Get column names as returned by a projection operator.
   */
  def private static getColumnNames(ProjectionOperator p, IngraphLogger logger) {
    p.elements.map[ExpressionNameInferencer.inferName(it, logger)]
  }
}
