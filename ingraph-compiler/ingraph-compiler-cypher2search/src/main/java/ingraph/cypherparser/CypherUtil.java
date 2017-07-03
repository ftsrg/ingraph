package ingraph.cypherparser;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.slizaa.neo4j.opencypher.openCypher.Cypher;

@SuppressWarnings("all")
public class CypherUtil {
  public final static String MODEL_EXTENSION = "cypxmi";
  
  public static void save(final Cypher cypher, final String filename) {
    try {
      EcoreUtil.resolveAll(cypher);
      Map<String, Object> _extensionToFactoryMap = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
      XMIResourceFactoryImpl _xMIResourceFactoryImpl = new XMIResourceFactoryImpl();
      _extensionToFactoryMap.put(CypherUtil.MODEL_EXTENSION, _xMIResourceFactoryImpl);
      final ResourceSetImpl resourceSet = new ResourceSetImpl();
      final String filePath = ((filename + ".") + CypherUtil.MODEL_EXTENSION);
      File _file = new File(filePath);
      final String absoluteFilePath = _file.getAbsolutePath();
      final URI uri = URI.createFileURI(absoluteFilePath);
      final Resource resource = resourceSet.createResource(uri);
      EList<EObject> _contents = resource.getContents();
      _contents.add(cypher);
      final HashMap<String, Object> saveOptions = new HashMap<String, Object>();
      saveOptions.put(XMIResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
      saveOptions.put(XMIResource.OPTION_ENCODING, "UTF-8");
      resource.save(saveOptions);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
