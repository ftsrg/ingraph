package ingraph.search2constraints.constraints

import ingraph.relalg.calculators.FunctionArgumentExtractor
import org.eclipse.xtend.lib.annotations.Data
import relalg.ArithmeticComparisonExpression
import relalg.ArithmeticComparisonOperatorType
import relalg.BinaryLogicalExpression
import relalg.BinaryLogicalOperatorType
import relalg.Expression
import relalg.FunctionExpression
import relalg.Literal
import relalg.LogicalExpression
import relalg.UnaryGraphObjectLogicalExpression
import relalg.UnaryLogicalExpression
import relalg.VariableExpression
import ingraph.search2constraints.constraints.util.ExpressionPrettyPrinter

@Data
class Selection implements Constraint {

	LogicalExpression condition

	extension ExpressionPrettyPrinter printer = new ExpressionPrettyPrinter

	override toString() '''
		«this.class.simpleName» [ condition: «prettyPrintCondition(condition)» ]
	'''

}
