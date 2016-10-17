package ingraph.cypher2relalg.factories

import relalg.RelationalAlgebraContainer
import relalg.Variable

abstract class VariableFactory<TVariable extends Variable> extends ElementFactory<TVariable> {
	
	new(RelationalAlgebraContainer container) {
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