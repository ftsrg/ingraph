package ingraph.relalg2tex.converters.variableconverters

import ingraph.relalg2tex.converters.elementconverters.StringEscaper
import relalg.ExpressionVariable

class VariableNameConverter extends AbstractVariableConverter {

	extension StringEscaper stringEscaper = new StringEscaper

	def dispatch convertVariable(ExpressionVariable variable) {
		'''\var{«variable.escapedName»}'''
	}

}
