package ingraph.cypher2relalg.util

import org.eclipse.emf.ecore.EObject
import org.slizaa.neo4j.opencypher.openCypher.Create
import org.slizaa.neo4j.opencypher.openCypher.Delete

/**
 * Utility class to use for the slizaa openCypher Xtext grammar.
 */
class GrammarUtil {
	/**
	 * Determines whether a clause is a CREATE/UPDATE/DELETE or not.
	 * 
	 * FIXME: UPDATE is not handled yet.
	 */
	static def isCudClause(EObject o) {
		switch o {
			Create: true
			Delete: true
			default: false
		}
	}
}