package ingraph.relalg.calculators

import ingraph.relalg.util.ListExpressionUtil
import java.util.List
import relalg.BinaryArithmeticOperationExpression
import relalg.FunctionExpression
import relalg.ListExpression
import relalg.Literal
import relalg.UnaryArithmeticOperationExpression
import relalg.Variable
import relalg.VariableExpression

class FunctionArgumentExtractor {

	def List<? extends Variable> extractFunctionArguments(FunctionExpression fe) {
			fe.arguments.map[extractVariableFromExpression].flatten.toList
	}

	// extractVariableFromExpression
	def dispatch List<? extends Variable> extractVariableFromExpression(FunctionExpression a) {
		extractFunctionArguments(a)
	}

	def dispatch List<? extends Variable> extractVariableFromExpression(VariableExpression a) {
		#[a.variable]
	}

	def dispatch List<? extends Variable> extractVariableFromExpression(UnaryArithmeticOperationExpression a) {
		#[extractVariableFromExpression(a.operand)].flatten.toList
	}

	def dispatch List<? extends Variable> extractVariableFromExpression(BinaryArithmeticOperationExpression a) {
		#[extractVariableFromExpression(a.leftOperand), extractVariableFromExpression(a.rightOperand)].flatten.toList
	}

	def dispatch List<? extends Variable> extractVariableFromExpression(Literal a) {
		#[]
	}
	
	def dispatch List<? extends Variable> extractVariableFromExpression(ListExpression a) {
		ListExpressionUtil.toList(a).map[extractVariableFromExpression].flatten.toList
	}

}
