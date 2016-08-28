package ingraph.cypher2relalg.factories

import ingraph.cypher2relalg.factories.ElementFactory
import relalg.Variable

abstract class VariableFactory<TVariable extends Variable> extends ElementFactory<TVariable> {
	override createElement(String elementName) {
		val e = super.createElement(elementName)
		if (elementName == null) {
			e.dontCare = true
		}
		return e
	}
}