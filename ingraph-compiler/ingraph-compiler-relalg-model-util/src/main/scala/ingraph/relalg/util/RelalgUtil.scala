package ingraph.relalg.util

import java.util.Collections

import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl
import relalg.RelalgContainer

object RelalgUtil {

  val MODEL_EXTENSION = "relalg"

  def save(container: RelalgContainer, filename: String) {
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(MODEL_EXTENSION, new XMIResourceFactoryImpl())

    val resourceSet = new ResourceSetImpl
    val uri = URI.createFileURI(filename + "." + MODEL_EXTENSION)
    val resource = resourceSet.createResource(uri)
    resource.getContents().add(container)
    resource.save(Collections.emptyMap())
  }

}
