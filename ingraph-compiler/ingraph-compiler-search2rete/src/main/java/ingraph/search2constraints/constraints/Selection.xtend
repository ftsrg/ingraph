package ingraph.search2constraints.constraints

import ingraph.search2constraints.constraints.util.ExpressionPrettyPrinter
import org.eclipse.xtend.lib.annotations.Data
import relalg.LogicalExpression

@Data
class Selection implements Constraint {

	LogicalExpression condition

	extension ExpressionPrettyPrinter printer = new ExpressionPrettyPrinter

	override toString() '''
		«this.class.simpleName» [ condition: «prettyPrintCondition(condition)» ]
	'''

}
