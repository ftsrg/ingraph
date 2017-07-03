package ingraph.cypher2relalg.factories;

import ingraph.cypher2relalg.factories.VariableFactory;
import relalg.AttributeVariable;
import relalg.RelalgContainer;

@SuppressWarnings("all")
public class AttributeVariableFactory extends VariableFactory<AttributeVariable> {
  public AttributeVariableFactory(final RelalgContainer container) {
    super(container);
  }
  
  @Override
  public AttributeVariable createSpecificNamedElement() {
    return this.factory.createAttributeVariable();
  }
}
