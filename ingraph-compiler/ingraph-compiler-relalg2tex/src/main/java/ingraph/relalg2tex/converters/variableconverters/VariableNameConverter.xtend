package ingraph.relalg2tex.converters.variableconverters

import relalg.ExpressionVariable

class VariableNameConverter extends AbstractVariableConverter {

	def dispatch convertVariable(ExpressionVariable ev) {
		ev.unwrap.name
	}

}
