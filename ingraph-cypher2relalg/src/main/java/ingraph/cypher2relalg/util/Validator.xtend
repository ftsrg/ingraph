package ingraph.cypher2relalg.util

import org.slizaa.neo4j.opencypher.openCypher.Match

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
}
