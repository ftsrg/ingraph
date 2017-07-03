package ingraph.relalg.util;

import java.util.Arrays;
import org.eclipse.emf.ecore.EObject;
import relalg.Operator;
import relalg.RelalgContainer;

@SuppressWarnings("all")
public class ContainerExtractor {
  protected RelalgContainer _extractContainer(final RelalgContainer container) {
    return container;
  }
  
  protected RelalgContainer _extractContainer(final Operator op) {
    EObject _eContainer = op.eContainer();
    return this.extractContainer(_eContainer);
  }
  
  public RelalgContainer extractContainer(final EObject op) {
    if (op instanceof Operator) {
      return _extractContainer((Operator)op);
    } else if (op instanceof RelalgContainer) {
      return _extractContainer((RelalgContainer)op);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(op).toString());
    }
  }
}
