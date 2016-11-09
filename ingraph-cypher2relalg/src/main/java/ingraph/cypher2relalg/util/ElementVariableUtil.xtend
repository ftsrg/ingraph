package ingraph.cypher2relalg.util

import ingraph.cypher2relalg.factories.AttributeVariableFactory
import java.util.HashMap
import relalg.ElementVariable
import relalg.RelalgFactory
import relalg.RelalgContainer

class ElementVariableUtil {
	protected extension RelalgFactory factory = RelalgFactory.eINSTANCE
	protected final RelalgContainer container

	val factories = new HashMap<ElementVariable, AttributeVariableFactory>

	new(RelalgContainer container) {
		this.container = container
	}

	def createAttribute(ElementVariable element, String attributeName) {
		if (factories.get(element)==null) {
			factories.put(element, new AttributeVariableFactory(container))
		}

		val attribute = factories.get(element).createElement(attributeName)

		return attribute
	}
}
