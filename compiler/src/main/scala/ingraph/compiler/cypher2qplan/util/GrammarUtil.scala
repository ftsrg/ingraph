package ingraph.compiler.cypher2qplan.util


import org.eclipse.emf.ecore.EObject
import org.slizaa.neo4j.opencypher.{openCypher => oc}

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
			case _: oc.Create => true
			case _: oc.Delete => true
			case _: oc.Merge  => true
			case _: oc.Remove => true
			case _: oc.Set    => true
			case _ => false
		}
	}
}
