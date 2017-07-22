package ingraph.cypher2relalg.util

import ingraph.cypher2relalg.factories.AttributeVariableFactory
import java.util.HashMap
import relalg.ElementVariable
import relalg.ExpressionVariable
import relalg.RelalgContainer
import relalg.RelalgFactory
import relalg.Variable

class ElementVariableUtil {
	protected extension RelalgFactory factory = RelalgFactory.eINSTANCE
	protected final RelalgContainer container

	val factories = new HashMap<Variable, AttributeVariableFactory>

	new(RelalgContainer container) {
		this.container = container
	}

	def createAttribute(Variable element, String attributeName) {
		if (element instanceof ElementVariable || element instanceof ExpressionVariable) {
			if (factories.get(element)==null) {
				factories.put(element, new AttributeVariableFactory(container))
			}

			val attribute = factories.get(element).createElement(attributeName)
			if (element instanceof ElementVariable) {
				attribute.element = element
			} else if (element instanceof ExpressionVariable) {
				attribute.expVar = element
			} else {
				// this should never happen
				throw new RuntimeException('FIXME: this should never be reached.')
			}

			return attribute
		} else {
			null
		}
	}
}
