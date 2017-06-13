package ingraph.search2constraints.constraints

import java.util.Set
import org.eclipse.xtend.lib.annotations.Data
import relalg.Variable

@Data
class Equality implements Constraint {

	val Set<Variable> vars

	public new(Set<Variable> vars) {

		if (vars.size > 2 || vars.size < 1) {
			throw new IllegalArgumentException(
				"An equality constraint must have at least 1, and at most 2 affected variables.")
		}

		this.vars = vars
	}

	override toString() {
		val varList = vars.toList
		'''
			«varList.get(0)» == «IF varList.length>1»«varList.get(1)»«ELSE»«varList.get(0)»«ENDIF»
		'''
	}
}
