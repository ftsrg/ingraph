package ingraph.search2constraints.constraints

import org.eclipse.xtend.lib.annotations.Data
import relalg.LogicalExpression

@Data
class Selection implements Constraint {
	LogicalExpression condition

	override toString() '''
		«this.class.simpleName» [ condition: «condition» ]
	'''
}
