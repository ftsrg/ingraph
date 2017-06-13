package ingraph.search2constraints.constraints

import org.eclipse.xtend.lib.annotations.Data
import relalg.AbstractEdgeVariable

@Data
class Inequality implements Constraint{
	
	AbstractEdgeVariable edgeVariable
	AbstractEdgeVariable otherEdgeVariable
	 
}
