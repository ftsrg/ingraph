package ingraph.search2constraints.constraints

import org.eclipse.xtend.lib.annotations.Data
import relalg.Variable
import java.util.Set

@Data
class Equality implements Constraint {  
	
	val Set<Variable> vars
	
	public new(Set<Variable> vars){
		
		if(vars.size > 2 || vars.size < 1){
			throw new IllegalArgumentException("An equality constraint must have at least 1, and at most 2 affected variables.")			
		}
		
		this.vars = vars
	}
	
	override toString()'''
		
	'''
} 