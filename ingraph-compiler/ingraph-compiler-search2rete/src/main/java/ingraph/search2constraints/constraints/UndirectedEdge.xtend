package ingraph.search2constraints.constraints

import java.util.Set
import org.eclipse.xtend.lib.annotations.Data
import relalg.EdgeVariable
import relalg.VertexVariable

@Data
class UndirectedEdge implements Constraint{
	
	val Set<VertexVariable> sourceVars
	val EdgeVariable edgeVar
	
	public new(Set<VertexVariable> sourceVars, EdgeVariable edgeVar){
		
		if(sourceVars.size > 2 || sourceVars.size < 1){
			throw new IllegalArgumentException("An undirected edge constraint must have at least 1, and at most 2 affected vertex variables.")			
		}
		
		this.sourceVars = sourceVars
		this.edgeVar = edgeVar
	}
	
}
