package ingraph.search2constraints.constraints

import org.eclipse.xtend.lib.annotations.Data
import relalg.Variable
import java.util.Set

@Data
class Equality implements Constraint {  
	
	val Set<Variable> vars
	
} 