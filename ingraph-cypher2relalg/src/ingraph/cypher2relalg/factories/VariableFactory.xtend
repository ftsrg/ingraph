package ingraph.cypher2relalg.factories

import relalg.Variable
import relalg.RelalgContainer

abstract class VariableFactory<TVariable extends Variable> extends ElementFactory<TVariable> {
	
	new(RelalgContainer container) {
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