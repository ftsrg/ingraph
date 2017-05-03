package ingraph.relalg2tex.converters.variableconverters

import ingraph.relalg2tex.converters.elementconverters.StringEscaper
import relalg.AttributeVariable
import relalg.EdgeListVariable
import relalg.ElementVariable
import relalg.ListVariable
import ingraph.relalg2tex.converters.elementconverters.MiscConverters

class AbstractVariableConverter {

	extension StringEscaper stringEscaper = new StringEscaper
	extension MiscConverters miscConverters = new MiscConverters
 
	def dispatch convertVariable(ElementVariable variable) {
		'''\var{«variable.escapedName»}'''
	}

	def dispatch convertVariable(EdgeListVariable variable) {
		'''\var{[«variable.escapedName»]}_{«variable.minHops»}^{«variable.maxHops.hopsToString»}'''
	}

	def dispatch convertVariable(AttributeVariable variable) {
		'''\var{«variable.baseVariable.escapedName».«variable.escapedName»}'''
	}

	def dispatch convertVariable(ListVariable variable) {
		'''\var{«variable.escapedName»}'''
	}

}
