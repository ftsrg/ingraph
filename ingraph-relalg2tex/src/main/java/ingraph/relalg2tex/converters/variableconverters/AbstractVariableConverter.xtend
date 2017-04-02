package ingraph.relalg2tex.converters.variableconverters

import ingraph.relalg2tex.converters.elementconverters.StringEscaper
import relalg.AttributeVariable
import relalg.ElementVariable
import relalg.ListVariable

class AbstractVariableConverter {

	extension StringEscaper stringEscaper = new StringEscaper
 
	def dispatch convertVariable(ElementVariable variable) {
		'''«variable.escapedName»'''
	}

	def dispatch convertVariable(AttributeVariable variable) {
		'''«variable.baseVariable.escapedName».«variable.escapedName»'''
	}

	def dispatch convertVariable(ListVariable variable) {
		'''«variable.escapedName»'''
	}

}
