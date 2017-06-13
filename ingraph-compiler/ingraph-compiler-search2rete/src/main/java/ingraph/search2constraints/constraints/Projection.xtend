package ingraph.search2constraints.constraints

import org.eclipse.xtend.lib.annotations.Data
import relalg.Variable
import java.util.List

@Data
class Projection implements Constraint {
	List<? extends Variable> variablesToKeep

	override toString() '''
		«this.class.simpleName» [ «FOR v : variablesToKeep SEPARATOR ','»«v»«ENDFOR» ]
	'''
}
