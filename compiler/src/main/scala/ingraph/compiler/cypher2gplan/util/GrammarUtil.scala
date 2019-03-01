package ingraph.compiler.cypher2gplan.util


import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.nodemodel.util.NodeModelUtils
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

  implicit class EObjectExtensions(val obj: EObject) extends AnyVal {
    def parsedText: String = {
      val node = NodeModelUtils.findActualNodeFor(obj)

      // not using INode.getText(), because that includes hidden tokens too
      val beginIndex = node.getOffset
      val endIndex = node.getEndOffset
      val rootText = node.getRootNode.getText

      rootText.substring(beginIndex, endIndex)
    }
  }
}
