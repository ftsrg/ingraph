package ingraph.relalg2tex.converters.elementconverters

import relalg.AttributeVariable
import relalg.ElementVariable
import relalg.ExpressionVariable
import relalg.ListVariable

class VariableConverter {

	extension ExpressionConverter expressionConverter = new ExpressionConverter
	extension StringEscaper stringEscaper = new StringEscaper
 
	def dispatch convertVariable(ElementVariable variable) {
		'''«variable.name»'''
	}

	def dispatch convertVariable(AttributeVariable variable) {
		'''«variable.baseVariable.name».«variable.name»'''
	}

	def dispatch convertVariable(ListVariable variable) {
		'''«variable.name»'''
	}

	def dispatch convertVariable(ExpressionVariable el) {
		convertExpression(el.expression) +
		  if (el.dontCare || el.hasInferredName) "" else '''\assign \var{«el.name.escape»}'''
	}

}
