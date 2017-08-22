package ingraph.compiler.cypher2qplan.util


import org.eclipse.emf.ecore.EObject
import org.slizaa.neo4j.opencypher.openCypher.Create
import org.slizaa.neo4j.opencypher.openCypher.Delete

/**
 * Utility object to use for the slizaa openCypher Xtext grammar.
 */
object GrammarUtil {
	/**
	 * Determines whether a clause is a CREATE/UPDATE/DELETE or not.
	 *
	 * FIXME: UPDATE is not handled yet.
	 */
	def isCudClause(o: EObject): Boolean = {
		o match {
			case x: Create => true
			case x: Delete => true
			case _ => false
		}
	}
}
