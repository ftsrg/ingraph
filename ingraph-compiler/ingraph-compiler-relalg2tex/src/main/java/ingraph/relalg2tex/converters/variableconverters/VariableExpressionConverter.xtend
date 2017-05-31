package ingraph.relalg2tex.converters.variableconverters

import ingraph.relalg2tex.converters.elementconverters.ExpressionConverter
import relalg.ExpressionVariable

class VariableExpressionConverter extends AbstractVariableConverter {

	extension ExpressionConverter expressionConverter = new ExpressionConverter

	def dispatch convertVariable(ExpressionVariable ev) {
		'''«ev.expression.convertExpression»«IF !(ev.dontCare || ev.hasInferredName)»\assign \var{«ev.unwrap.escapedName»}«ENDIF»'''
	}

}