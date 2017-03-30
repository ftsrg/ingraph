package ingraph.relalg2tex.converters.elementconverters

import relalg.AttributeVariable
import relalg.ElementVariable
import relalg.ExpressionVariable
import relalg.ListVariable

class VariableConverter {
 
	/**
	 * convertVariable
	 */
	def dispatch convertVariable(ElementVariable variable) {
		'''«variable.name»'''
	}

	def dispatch convertVariable(AttributeVariable variable) {
		'''«variable.baseVariable.name».«variable.name»'''
	}

	def dispatch convertVariable(ListVariable variable) {
		'''«variable.name»'''
	}

  // FIXME: how does this relate to MiscConverters.convertReturnableElement(ExpressionVariable)
  // See #92
	def dispatch convertVariable(ExpressionVariable variable) {
		//'''«convertExpression(variable, variable.expression)»'''
		'''«variable.name»'''
	}

//
//  def dispatch convertExpression(ExpressionVariable variable, VariableListExpression expression) {
//    '''«expression.variable.name»[]'''
//  }
//
//  def dispatch convertExpression(ExpressionVariable variable, VariableExpression expression) {
//    '''«expression.variable.name»'''
//  }
//
//  def dispatch convertExpression(ExpressionVariable variable, Literal expression) {
//    '''«variable.name»'''
//  }
//
//  def dispatch convertExpression(ExpressionVariable variable, FunctionExpression expression) {
//    '''«expression.functor.lowerCaseName»(«expression.arguments.map[toString.escape].join(", ")»)'''
//  }
//
//  def dispatch convertExpression(ExpressionVariable variable, Expression expression) {
//    throw new UnsupportedOperationException('''Cannot convert ExpressionVariable «variable» with Expression «expression»''')
//  }

}
