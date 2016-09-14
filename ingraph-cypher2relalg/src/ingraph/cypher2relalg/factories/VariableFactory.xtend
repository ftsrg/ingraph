package ingraph.cypher2relalg.factories

import ingraph.cypher2relalg.factories.ElementFactory
import relalg.Variable
import relalg.Container

abstract class VariableFactory<TVariable extends Variable> extends ElementFactory<TVariable> {
	
	new(Container container) {
		super(container)
	}
	
	override createElement(String elementName) {
		val e = super.createElement(elementName)
		if (elementName == null) {
			e.dontCare = true
		}
		return e
	}
}