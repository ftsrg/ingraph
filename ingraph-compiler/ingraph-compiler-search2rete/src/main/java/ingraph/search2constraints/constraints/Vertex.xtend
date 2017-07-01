package ingraph.search2constraints.constraints

import org.eclipse.xtend.lib.annotations.Data
import relalg.VertexVariable

@Data
class Vertex implements Constraint{
	
	val VertexVariable vertexVariable
	
	override toString()'''
		«this.class.simpleName» [ vertexVariable «vertexVariable», labels: «FOR l : vertexVariable.vertexLabelSet.vertexLabels SEPARATOR ','» «l.name» «ENDFOR»]
	'''
		
}
