package ingraph.relalg2tex.converters.variableconverters

import ingraph.relalg2tex.converters.elementconverters.ExpressionConverter
import relalg.ExpressionVariable

class VariableExpressionConverter extends AbstractVariableConverter {

	extension ExpressionConverter expressionConverter = new ExpressionConverter

	def dispatch convertVariable(ExpressionVariable ev) {
		'''«convertExpression(ev.expression)»«IF !(ev.dontCare || ev.hasInferredName)»\assign «convertExpressionVariable(ev)»«ENDIF»'''
	}

}