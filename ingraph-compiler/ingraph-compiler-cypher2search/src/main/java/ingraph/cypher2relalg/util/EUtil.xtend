package ingraph.cypher2relalg.util

import java.util.List
import org.eclipse.emf.ecore.EObject

/**
 * Utility method to general EMF operations.
 */
class EUtil {
	/**
	 * Inspect the container hierarchy of a given EObject instance whether it includes given classes in the given order.
	 * 
	 * Container hierarchy is inspected starting from the given EObject instance
	 * and the list in the lookfor parameter is traversed from its beginning.
	 * 
	 * An empty list of lookfor matches every container hierarchy including the null EObject instance. 
	 * 
	 * @param ref the EObject whose hierarchy is to be inspected
	 * @param lookfor list of class names to look for in the container hierarchy. 
	 */
	static def boolean hasInContainerHierarchy(EObject ref, List<Class> lookfor, Class stopClass) {
		var current = ref
		for (l: lookfor) {
			while (!l.isInstance(current) && !stopClass.isInstance(current) && current !== null) {
				current = current.eContainer				
			}
			if (current === null) {
				// we have something more to find, but a null object is the candidate
				return false
			} else if (stopClass.isInstance(current) && !l.isInstance(current)) {
				return false
			} else {
				// current was a match so we step one further
				current = current.eContainer
			}
		}
		return true
	}
}