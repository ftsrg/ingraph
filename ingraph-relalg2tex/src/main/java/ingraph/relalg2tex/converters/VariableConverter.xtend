package ingraph.relalg2tex.converters

import relalg.AttributeVariable
import relalg.ElementVariable
import relalg.ExpressionVariable
import relalg.ListVariable

class VariableConverter {
 
  /**
   * serializeVariable
   */
  def dispatch serializeVariable(ElementVariable variable) {
    '''«variable.name»'''
  }

  def dispatch serializeVariable(AttributeVariable variable) {
    '''«variable.element.name».«variable.name»'''
  }

  def dispatch serializeVariable(ListVariable variable) {
    '''«variable.name»'''
  }

  def dispatch serializeVariable(ExpressionVariable variable) {
    //'''«convertExpression(variable, variable.expression)»'''
    '''«variable.name»'''
  }

  //
//
//  def dispatch serializeExpression(ExpressionVariable variable, VariableListExpression expression) {
//    '''«expression.variable.name»[]'''
//  }
//
//  def dispatch serializeExpression(ExpressionVariable variable, VariableExpression expression) {
//    '''«expression.variable.name»'''
//  }
//
//  def dispatch serializeExpression(ExpressionVariable variable, Literal expression) {
//    '''«variable.name»'''
//  }
//
//  def dispatch serializeExpression(ExpressionVariable variable, FunctionExpression expression) {
//    '''«expression.functor.lowerCaseName»(«expression.arguments.map[toString.escape].join(", ")»)'''
//  }
//
//  def dispatch serializeExpression(ExpressionVariable variable, Expression expression) {
//    throw new UnsupportedOperationException('''Cannot serialize ExpressionVariable «variable» with Expression «expression»''')
//  }

}
