package ingraph.search2tasks.tasks.unary

import ingraph.search2tasks.tasks.Task
import org.eclipse.xtend.lib.annotations.Data
import relalg.AbstractEdgeVariable
import relalg.VertexVariable

@Data
class ExpandBothTask implements Task{
	
	val VertexVariable sourceVar
	val AbstractEdgeVariable edgeVar
	val VertexVariable targetVar
	
}
