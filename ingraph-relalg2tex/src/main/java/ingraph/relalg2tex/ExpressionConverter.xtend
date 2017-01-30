package ingraph.relalg2tex

import relalg.ArithmeticComparisonExpression
import relalg.ArithmeticOperationExpression
import relalg.AttributeVariable
import relalg.BinaryLogicalExpression
import relalg.BooleanLiteral
import relalg.ElementVariable
import relalg.EmptyListExpression
import relalg.ExpressionVariable
import relalg.FunctionExpression
import relalg.IntegerLiteral
import relalg.ListExpression
import relalg.NullLiteral
import relalg.StringLiteral
import relalg.UnaryGraphObjectLogicalExpression
import relalg.UnaryLogicalExpression
import relalg.VariableExpression

class ExpressionConverter {
  
  extension StringEscaper stringEscaper = new StringEscaper
  extension OperatorTypeConverter operatorConverter = new OperatorTypeConverter

  def dispatch CharSequence convertExpression(IntegerLiteral integerLiteral) {
    '''\literal{«integerLiteral.value.toString»}'''
  }

  def dispatch CharSequence convertExpression(StringLiteral stringLiteral) {
    '''\literal{'«stringLiteral.value.toString.escape»'}'''
  }

  def dispatch CharSequence convertExpression(ElementVariable elementVariable) {
    '''\var{«elementVariable.escapedName»}'''
  }

  def dispatch CharSequence convertExpression(AttributeVariable attributeVariable) {
    '''\var{«attributeVariable.element.name».«attributeVariable.escapedName»}'''
  }

  def dispatch CharSequence convertExpression(ExpressionVariable expVariable) {
    if (expVariable.hasInferredName) {
      convertExpression(expVariable.expression)
    } else {
      '''\var{«expVariable.escapedName»}'''
    }
  }
  
  def dispatch CharSequence convertExpression(VariableExpression ve) {
    convertExpression(ve.variable)
  }  

  def dispatch CharSequence convertExpression(FunctionExpression fe) {
    '''«fe.functor.lowerCaseName.escape» \left( «fe.arguments.map[convertExpression].join(", ")» \right)'''
  }

  def dispatch CharSequence convertExpression(EmptyListExpression fe) {
    '''\left[ \right]'''
  }

  def dispatch CharSequence convertExpression(ListExpression fe) {
    var retVal = '''\left['''

    for(var i = fe; !(i instanceof EmptyListExpression); i = i.tail) {
      retVal += ''' «i.head.convertExpression»«IF !(i.tail instanceof EmptyListExpression)»,«ENDIF»'''
    }

    retVal + ''' \right]'''
  }

  def dispatch CharSequence convertExpression(BinaryLogicalExpression exp) {
    '''«exp.leftOperand.convertExpression» «exp.operator.convert» «exp.rightOperand.convertExpression»'''
  }

  def dispatch CharSequence convertExpression(UnaryLogicalExpression exp) {
    '''«exp.operator.convert» \left( «exp.operand.convertExpression» \right)'''
  }

  def dispatch CharSequence convertExpression(UnaryGraphObjectLogicalExpression exp) {
    '''«exp.operand.convertExpression» «exp.getOperator.convert»'''
  }

  def dispatch CharSequence convertExpression(ArithmeticComparisonExpression exp) {
    '''«exp.leftOperand.convertExpression» «exp.operator.convertOperatorType» «exp.rightOperand.convertExpression»'''
  }

  def dispatch CharSequence convertExpression(ArithmeticOperationExpression exp) {
    '''«exp.leftOperand.convertExpression» «exp.operator.convert» «exp.rightOperand.convertExpression»'''
  }

  def dispatch CharSequence convertExpression(BooleanLiteral exp) {
    '''\mathtt{«if (exp.value) "true" else "false"»}'''
  }

  def dispatch CharSequence convertExpression(NullLiteral x) {
    RelNullConstants.relNull
  }

  def dispatch CharSequence convertExpression(Void x) {
    '''Void:null'''
  }

}