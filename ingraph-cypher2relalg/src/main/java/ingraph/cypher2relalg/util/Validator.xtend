package ingraph.cypher2relalg.util

import ingraph.logger.IngraphLogger
import java.util.LinkedList
import java.util.List
import org.slizaa.neo4j.opencypher.openCypher.Clause
import org.slizaa.neo4j.opencypher.openCypher.Create
import org.slizaa.neo4j.opencypher.openCypher.Delete
import org.slizaa.neo4j.opencypher.openCypher.Match
import org.slizaa.neo4j.opencypher.openCypher.Return
import org.slizaa.neo4j.opencypher.openCypher.Unwind
import org.slizaa.neo4j.opencypher.openCypher.With
import relalg.Operator
import relalg.ProjectionOperator
import relalg.UnaryOperator
import relalg.UnionOperator

class Validator {
	/**
	 * Some checks for a single subquery's MATCH clauses.
	 *
	 * Note: pass all the MATCH clauses in sequence to allow for proper checking.
	 *
	 * Checks performed:
	 * - It is invalid to have MATCH after OPTIONAL MATCH.
	 */
	def static void checkSubQueryMatchClauseSequence(Iterable<Match> singleQuery_MatchList, IngraphLogger logger) {
		val head = singleQuery_MatchList?.head
		if (head != null) {
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
	 * Some checks for a single subquery's clauses
	 *
	 * A subquery has the form (MATCH*)(CREATE|DELETE*|(WITH UNWIND?)|UNWIND|RETURN)
	 *
	 * Checks performed:
	 * - it consists of only MATCH, CREATE, DELETE, WITH, UNWIND and RETURN clauses
	 * - it ends with RETURN, CREATE, DELETE, WITH or UNWIND
	 * - it has at most 1 of either RETURN, WITH or CREATE
	 * - it has at most 1 UNWIND
	 * - it has at least one of WITH, CREATE, DELETE, UNWIND and RETURN
	 * - before DELETE there must be at least one MATCH clause
	 */
	def static void checkSubQueryClauseSequence(List<Clause> clauses, IngraphLogger logger) {
		var numReturnOrWith = 0
		var numUnwind = 0
		var numMatch = 0
		var numCreate = 0
		var numDelete = 0
		for (Clause c: clauses) {
			switch c {
				Match: numMatch++
				Create: numCreate++
				Delete: numDelete++
				Unwind: numUnwind++
				With, Return: numReturnOrWith++
				default: logger.unsupported('''Currently we only support MATCH, CREATE, DELETE, WITH, UNWIND and RETURN clauses in a single subquery. Found: «c.class.name».''')
			}
		}
		if ( ! (clauses.last instanceof Return
		     || clauses.last instanceof With
		     || clauses.last instanceof Unwind
		     || clauses.last instanceof Create
		     || clauses.last instanceof Delete
		)) {
			logger.unsupported('''Last clause of a single subquery must be RETURN, CREATE, DELETE, WITH or UNWIND, but found «clauses.last.class.name» instead.''')
		}
		if ( numUnwind > 1 ) {
			logger.unsupported('''At most 1 Unwind clause allowed in a subquery. Found «numUnwind».''')
		}
		if ( numReturnOrWith > 1 ) {
			logger.unsupported('''At most 1 Return or With clause allowed in a subquery. Found «numReturnOrWith».''')
		}
		if ( numDelete + numCreate + numUnwind + numReturnOrWith < 1 ) {
			logger.unsupported('''There must be at least one of WITH, CREATE, DELETE, RETURN or UNWIND in a subquery, but none of them was found.''')
		}
		if ( numDelete > 1 && numMatch < 1 ) {
			logger.unsupported('''There must be at least one of MATCH before DELETE in a subquery, but no MATCH was found.''')	
		}
	}

	/**
	 * Some checks for a single query's clauses
	 *
	 * Checks performed:
	 * - we currently support only the following clauses: MATCH, CREATE, DELETE, WITH UNWIND?, UNWIND, RETURN
	 * - last clause must be a RETURN clause
	 */
	def static void checkSingleQueryClauseSequence(List<Clause> clauses, IngraphLogger logger) {
		for (Clause c: clauses) {
			if ( ! (c instanceof Match
			     || c instanceof With
			     || c instanceof Unwind
			     || c instanceof Return
			     || c instanceof Create
			     || c instanceof Delete
			)) {
				logger.unsupported('''Currently we only support MATCH, CREATE, DELETE, WITH, UNWIND and RETURN clauses in a single query. Found: «c.class.name».''')
			}
		}
		if ( ! (clauses.last instanceof Return || clauses.last instanceof Create || clauses.last instanceof Delete)) {
			logger.unsupported('''Last clause of a single query must be RETURN, CREATE or DELETE but found «clauses.last.class.name» instead.''')
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
