package ingraph.cypherparser

import java.io.File
import java.util.HashMap
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.xmi.XMIResource
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl
import org.slizaa.neo4j.opencypher.openCypher.Cypher
import org.eclipse.emf.ecore.util.EcoreUtil

class CypherUtil {

	public static val MODEL_EXTENSION = "cypxmi"

	def static void save(Cypher cypher, String filename) {
		EcoreUtil.resolveAll(cypher)

		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(CypherUtil.MODEL_EXTENSION,
			new XMIResourceFactoryImpl())

		val resourceSet = new ResourceSetImpl
		val filePath = filename + "." + CypherUtil.MODEL_EXTENSION
		val absoluteFilePath = new File(filePath).absolutePath
		
		val uri = URI.createFileURI(absoluteFilePath)
		val resource = resourceSet.createResource(uri)
		resource.contents.add(cypher)

		val saveOptions = new HashMap<String, Object>
		saveOptions.put(XMIResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
		saveOptions.put(XMIResource.OPTION_ENCODING, "UTF-8");
		resource.save(saveOptions)
	}

}
