package ingraph.relalg.calculators

import com.google.common.collect.Iterables
import com.google.common.collect.Lists
import java.util.List
import relalg.ArithmeticComparisonExpression
import relalg.AttributeVariable
import relalg.BinaryLogicalExpression
import relalg.Case
import relalg.Expression
import relalg.FunctionExpression
import relalg.GenericCaseExpression
import relalg.SimpleCaseExpression
import relalg.UnaryLogicalExpression
import relalg.Variable
import relalg.VariableExpression
import org.eclipse.emf.common.util.UniqueEList.FastCompare
import ingraph.relalg.collectors.CollectionHelper

class ExpressionToVariables {

	extension FunctionArgumentExtractor fae = new FunctionArgumentExtractor
	extension CollectionHelper ch = new CollectionHelper

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

	def dispatch List<? extends Variable> getAttributes(SimpleCaseExpression expression) {
		union(expression.test.getAttributes, expression.cases.map[extractAttributes].flatten, expression.fallback.getAttributes)
	}

	def dispatch List<? extends Variable> getAttributes(GenericCaseExpression expression) {
		union(expression.cases.map[extractAttributes].flatten, expression.fallback.getAttributes)
	}

	// default branch: no attributes
	def dispatch List<? extends Variable> getAttributes(Expression expression) {
		#[]
	}

	//

	def extractAttributes(Case c) {
		 union(c.when.getAttributes, c.then.getAttributes)
	}

}
