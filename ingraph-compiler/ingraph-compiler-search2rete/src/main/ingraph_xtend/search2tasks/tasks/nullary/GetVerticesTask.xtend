package ingraph.search2tasks.tasks.nullary

import ingraph.search2tasks.tasks.Task
import org.eclipse.xtend.lib.annotations.Data
import relalg.VertexVariable

@Data
class GetVerticesTask implements Task{
	
	val VertexVariable variable
	
}
