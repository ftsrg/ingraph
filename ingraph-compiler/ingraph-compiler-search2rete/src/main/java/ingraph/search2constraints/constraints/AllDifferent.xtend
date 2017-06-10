package ingraph.search2constraints.constraints

import java.util.List
import org.eclipse.xtend.lib.annotations.Data
import relalg.AbstractEdgeVariable

@Data
class AllDifferent implements Constraint{
	
	List<AbstractEdgeVariable> edgeVariables
	
	public new (List<AbstractEdgeVariable> edgeVariables){
		this.edgeVariables = edgeVariables		
	}
	
}
