package ingraph.search2constraints.constraints

import org.eclipse.xtend.lib.annotations.Data
import relalg.EdgeVariable
import relalg.VertexVariable

@Data
class DirectedEdge implements Constraint{
	
	val VertexVariable sourceVar
	val EdgeVariable edgeVar
	val VertexVariable targetVar
	
}
