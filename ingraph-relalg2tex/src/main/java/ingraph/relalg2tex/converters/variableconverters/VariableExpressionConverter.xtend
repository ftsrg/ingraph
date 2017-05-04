package ingraph.relalg2tex.converters.variableconverters

import ingraph.relalg2tex.converters.elementconverters.ExpressionConverter
import ingraph.relalg2tex.converters.elementconverters.StringEscaper
import relalg.ExpressionVariable

class VariableExpressionConverter extends AbstractVariableConverter {

	extension StringEscaper stringEscaper = new StringEscaper
	extension ExpressionConverter expressionConverter = new ExpressionConverter

	def dispatch convertVariable(ExpressionVariable el) {
		convertExpression(el.expression) +
		  if (el.dontCare || el.hasInferredName) "" else '''\assign \var{«el.name.escape»}'''
	}

}