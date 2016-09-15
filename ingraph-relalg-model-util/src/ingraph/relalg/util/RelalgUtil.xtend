package ingraph.relalg.util

import java.util.Collections
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl
import relalg.Container

class RelalgUtil {

	def static save(Container container, String filename) {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("relalg", new XMIResourceFactoryImpl());

		val resourceSet = new ResourceSetImpl
		val uri = URI.createFileURI(filename + ".relalg")
		val resource = resourceSet.createResource(uri)
		resource.contents.add(container)
		resource.save(Collections.emptyMap)

		return container
	}
	
}
