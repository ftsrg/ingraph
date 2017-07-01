package ingraph.search2constraints.constraints

import org.eclipse.xtend.lib.annotations.Data
import relalg.EdgeVariable
import relalg.VertexVariable

@Data
class DirectedEdge implements Constraint {

	val VertexVariable sourceVar
	val EdgeVariable edgeVar
	val VertexVariable targetVar

	override toString() '''
		«this.class.simpleName» [ «sourceVar.name» : «FOR l : sourceVar.vertexLabelSet.vertexLabels SEPARATOR ' AND '»«l.name»«ENDFOR» -- «edgeVar.name» : «FOR l : edgeVar.edgeLabelSet.edgeLabels SEPARATOR ' OR '»«l.name»«ENDFOR» -->> «targetVar.name» : «FOR l : targetVar.vertexLabelSet.vertexLabels SEPARATOR ' AND '»«l.name»«ENDFOR» ]
	'''

}
