package ingraph.relalg.calculators

import com.google.common.collect.Iterables
import com.google.common.collect.Lists
import java.util.List
import relalg.ArithmeticComparisonExpression
import relalg.AttributeVariable
import relalg.BinaryLogicalExpression
import relalg.Expression
import relalg.FunctionExpression
import relalg.UnaryLogicalExpression
import relalg.Variable
import relalg.VariableExpression

class ExpressionToVariables {

	extension FunctionArgumentExtractor fae = new FunctionArgumentExtractor

	def dispatch List<? extends Variable> getAttributes(UnaryLogicalExpression expression) {
		getAttributes(expression.operand)
	}
	
	def dispatch List<? extends Variable> getAttributes(ArithmeticComparisonExpression expression) {
		Lists.newArrayList(Iterables.concat(
			getAttributes(expression.leftOperand),
			getAttributes(expression.rightOperand)
		))
	}

	def dispatch List<? extends Variable> getAttributes(BinaryLogicalExpression expression) {
		Lists.newArrayList(Iterables.concat(
			getAttributes(expression.leftOperand),
			getAttributes(expression.rightOperand)
		))
	}

	def dispatch List<? extends Variable> getAttributes(VariableExpression expression) {
		if (expression.variable instanceof AttributeVariable) {
			#[expression.variable as AttributeVariable]
		} else {
			#[]
		}
	}
	
	def dispatch List<? extends Variable> getAttributes(FunctionExpression expression) {
		expression.extractFunctionArguments
	}
	
	// default branch: no attributes
	def dispatch List<? extends Variable> getAttributes(Expression expression) {
		#[]
	}

}
