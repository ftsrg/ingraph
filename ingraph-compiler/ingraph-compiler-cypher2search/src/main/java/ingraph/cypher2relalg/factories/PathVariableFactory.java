package ingraph.cypher2relalg.factories;

import ingraph.cypher2relalg.factories.VariableFactory;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import relalg.PathVariable;
import relalg.RelalgContainer;

@SuppressWarnings("all")
public class PathVariableFactory extends VariableFactory<PathVariable> {
  public PathVariableFactory(final RelalgContainer container) {
    super(container);
  }
  
  @Override
  public PathVariable createSpecificNamedElement() {
    PathVariable _createPathVariable = this.factory.createPathVariable();
    final Procedure1<PathVariable> _function = (PathVariable it) -> {
    };
    return ObjectExtensions.<PathVariable>operator_doubleArrow(_createPathVariable, _function);
  }
}
