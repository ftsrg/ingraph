package ingraph.relalg.util;

import java.util.Collections;
import java.util.Map;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.xtext.xbase.lib.Exceptions;
import relalg.RelalgContainer;

@SuppressWarnings("all")
public class RelalgUtil {
  public final static String MODEL_EXTENSION = "relalg";
  
  public static void save(final RelalgContainer container, final String filename) {
    try {
      Map<String, Object> _extensionToFactoryMap = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
      XMIResourceFactoryImpl _xMIResourceFactoryImpl = new XMIResourceFactoryImpl();
      _extensionToFactoryMap.put(RelalgUtil.MODEL_EXTENSION, _xMIResourceFactoryImpl);
      final ResourceSetImpl resourceSet = new ResourceSetImpl();
      final URI uri = URI.createFileURI(((filename + ".") + RelalgUtil.MODEL_EXTENSION));
      final Resource resource = resourceSet.createResource(uri);
      EList<EObject> _contents = resource.getContents();
      _contents.add(container);
      Map<Object, Object> _emptyMap = Collections.<Object, Object>emptyMap();
      resource.save(_emptyMap);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
