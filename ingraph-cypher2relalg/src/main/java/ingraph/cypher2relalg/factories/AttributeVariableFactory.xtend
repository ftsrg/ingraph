package ingraph.cypher2relalg.factories

import ingraph.cypher2relalg.factories.VariableFactory
import relalg.RelalgContainer
import relalg.AttributeVariable

class AttributeVariableFactory extends VariableFactory<AttributeVariable> {

  new(RelalgContainer container) {
    super(container)
  }

  override createSpecificNamedElement() {
    createAttributeVariable
  }

}
