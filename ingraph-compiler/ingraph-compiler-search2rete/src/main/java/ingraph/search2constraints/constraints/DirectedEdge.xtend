package ingraph.search2constraints.constraints

import org.eclipse.xtend.lib.annotations.Data
import relalg.EdgeVariable
import relalg.VertexVariable

@Data
class DirectedEdge implements Constraint{
	
	val VertexVariable sourceVar
	val EdgeVariable edgeVar
	val VertexVariable targetVar
	
	override toString()'''
		DirectedEdge [
			sourceVar = «sourceVar.name» , labels: «FOR l : sourceVar.vertexLabelSet.vertexLabels SEPARATOR ','» «l.toString» «ENDFOR»
			edgeVar = «edgeVar.name» , type: «FOR l : edgeVar.edgeLabelSet.edgeLabels SEPARATOR ','» «l.toString» «ENDFOR»
			targetVar = «targetVar.name» , labels: «FOR l : targetVar.vertexLabelSet.vertexLabels SEPARATOR ','» «l.toString» «ENDFOR»
		]
	'''
	
}
