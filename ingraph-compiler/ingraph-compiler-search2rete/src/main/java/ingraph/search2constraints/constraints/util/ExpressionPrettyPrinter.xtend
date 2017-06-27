package ingraph.search2constraints.constraints.util

import ingraph.relalg.calculators.FunctionArgumentExtractor
import relalg.ArithmeticComparisonExpression
import relalg.ArithmeticComparisonOperatorType
import relalg.BinaryLogicalExpression
import relalg.BinaryLogicalOperatorType
import relalg.Expression
import relalg.FunctionExpression
import relalg.Literal
import relalg.UnaryGraphObjectLogicalExpression
import relalg.UnaryLogicalExpression
import relalg.VariableExpression

class ExpressionPrettyPrinter {
	
	private val arithmeticComparisonOperatorMapping = newHashMap( 
		ArithmeticComparisonOperatorType.EQUAL_TO -> "=", 
	  ArithmeticComparisonOperatorType.NOT_EQUAL_TO-> "<>", 
	  ArithmeticComparisonOperatorType.GREATER_THAN-> ">", 
	  ArithmeticComparisonOperatorType.GREATER_THAN_OR_EQUAL-> ">=", 
	  ArithmeticComparisonOperatorType.LESS_THAN-> "<", 
	  ArithmeticComparisonOperatorType.LESS_THAN_OR_EQUAL-> "<"
	)
	
	private val binaryLogicalOperatorMapping = newHashMap(
  	BinaryLogicalOperatorType.AND -> "&&",
		BinaryLogicalOperatorType.OR -> "||",
  	BinaryLogicalOperatorType.XOR -> "^^"
	)

	extension FunctionArgumentExtractor fae = new FunctionArgumentExtractor

	def dispatch CharSequence prettyPrintCondition(UnaryLogicalExpression expression) {
		prettyPrintCondition(expression.operand)
	}

	def dispatch CharSequence prettyPrintCondition(ArithmeticComparisonExpression expression) {
		'''( «prettyPrintCondition(expression.leftOperand)» «arithmeticComparisonOperatorMapping.get(expression.operator)» «prettyPrintCondition(expression.rightOperand)» )'''
	}

	def dispatch CharSequence prettyPrintCondition(BinaryLogicalExpression expression) {
		'''( «prettyPrintCondition(expression.leftOperand)» «binaryLogicalOperatorMapping.get(expression.operator)» «prettyPrintCondition(expression.rightOperand)» )'''
	}

	def dispatch CharSequence prettyPrintCondition(VariableExpression expression) {
		'''«expression.variable.name»'''
	}

	def dispatch CharSequence prettyPrintCondition(FunctionExpression expression) {
		'''«expression.functor.name»(«FOR a : expression.extractFunctionArguments SEPARATOR ','» «a.name» «ENDFOR»)'''
	}

	def dispatch CharSequence prettyPrintCondition(Literal lit) {
		val valueField = lit.class.declaredFields.filter[name == "value"].head
		valueField.accessible = true
		'''«valueField.get(lit)»'''
	}
	
	def dispatch CharSequence prettyPrintCondition(UnaryGraphObjectLogicalExpression expression) {
		'''«expression.operator»(«expression.operand.name»)'''
	}

	// default branch: no attributes
	def dispatch CharSequence prettyPrintCondition(Expression expression) {
		'''( No prettyprint for «expression» of type «expression.class.name» )'''
	}
}