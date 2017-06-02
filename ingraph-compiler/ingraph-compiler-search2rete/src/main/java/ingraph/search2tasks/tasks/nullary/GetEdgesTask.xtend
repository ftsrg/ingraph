package ingraph.search2tasks.tasks.nullary

import org.eclipse.xtend.lib.annotations.Data
import ingraph.search2tasks.tasks.Task
import relalg.VertexVariable
import relalg.AbstractEdgeVariable

@Data
class GetEdgesTask implements Task{
	
	val VertexVariable sourceVar
	val AbstractEdgeVariable edgeVar
	val VertexVariable targetVar
	
}
