package ingraph.cypher2relalg.util;

import java.util.List;
import org.eclipse.emf.ecore.EObject;

/**
 * Utility method to general EMF operations.
 */
@SuppressWarnings("all")
public class EUtil {
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
  public static boolean hasInContainerHierarchy(final EObject ref, final List<Class> lookfor, final Class stopClass) {
    EObject current = ref;
    for (final Class l : lookfor) {
      {
        while ((((!l.isInstance(current)) && (!stopClass.isInstance(current))) && (current != null))) {
          EObject _eContainer = current.eContainer();
          current = _eContainer;
        }
        if ((current == null)) {
          return false;
        } else {
          if ((stopClass.isInstance(current) && (!l.isInstance(current)))) {
            return false;
          } else {
            EObject _eContainer = current.eContainer();
            current = _eContainer;
          }
        }
      }
    }
    return true;
  }
}
