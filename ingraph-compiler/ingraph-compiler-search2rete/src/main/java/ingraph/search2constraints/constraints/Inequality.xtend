package ingraph.search2constraints.constraints

import java.util.Set
import org.eclipse.xtend.lib.annotations.Data
import relalg.Variable

@Data
class Inequality implements Constraint{
	
	Set<Variable> vars
	
	public new(Set<Variable> vars){
		
		if(vars.size > 2 || vars.size < 1){
			throw new IllegalArgumentException("An inequality constraint must have at least 1, and at most 2 affected variables.")			
		}
		
		this.vars = vars
	}
}
