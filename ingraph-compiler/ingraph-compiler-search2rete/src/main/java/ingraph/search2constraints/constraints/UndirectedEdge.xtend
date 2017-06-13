package ingraph.search2constraints.constraints

import java.util.Set
import org.eclipse.xtend.lib.annotations.Data
import relalg.EdgeVariable
import relalg.VertexVariable

@Data
class UndirectedEdge implements Constraint{
	
	val Set<VertexVariable> vertexVariables
	val EdgeVariable edgeVar
	
	public new(Set<VertexVariable> vertexVariables, EdgeVariable edgeVar){
		
		if(vertexVariables.size > 2 || vertexVariables.size < 1){
			throw new IllegalArgumentException("An undirected edge constraint must have at least 1, and at most 2 affected vertex variables.")			
		}
		
		this.vertexVariables = vertexVariables
		this.edgeVar = edgeVar
	}
	
		override toString() {
			val v1 = vertexVariables.get(0)
			val v2 = if(vertexVariables.length > 1) {
				vertexVariables.get(1)
			} else {
				vertexVariables.get(0)
			}
			
				
			
		return '''«this.class.simpleName» [ «v1.name» : «FOR l : v1.vertexLabelSet.vertexLabels SEPARATOR ' AND '»«l.name»«ENDFOR» -- «edgeVar.name» : «FOR l : edgeVar.edgeLabelSet.edgeLabels SEPARATOR ' OR '»«l.name»«ENDFOR» -- «v2.name» : «FOR l : v2.vertexLabelSet.vertexLabels SEPARATOR ' AND '»«l.name»«ENDFOR» ]'''
	
	}
	
}
