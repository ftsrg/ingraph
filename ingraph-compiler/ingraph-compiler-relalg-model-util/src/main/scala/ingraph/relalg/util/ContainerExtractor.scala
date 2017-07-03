package ingraph.relalg.util

import org.eclipse.emf.ecore.EObject
import relalg.{Operator, RelalgContainer}

class ContainerExtractor {

  def extractContainer(obj : EObject): RelalgContainer = {
    obj match {
      case c: RelalgContainer => c
      case op: Operator => extractContainer(op.eContainer)
    }
  }

}
