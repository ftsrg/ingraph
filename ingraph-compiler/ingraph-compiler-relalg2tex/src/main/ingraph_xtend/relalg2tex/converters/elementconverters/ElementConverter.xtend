package ingraph.relalg2tex.converters.elementconverters

import relalg.AbstractEdgeVariable
import relalg.EdgeLabelSet
import relalg.LabelSet
import relalg.LabelSetStatus
import relalg.VertexLabelSet
import relalg.VertexVariable

/**
	* Convert ElementVariable to string, including labels.
	*/
class ElementConverter {

	extension StringEscaper stringEscaper = new StringEscaper

	def dispatch convertElement(VertexVariable variable) {
		'''{«variable.escapedName»}{'''
		+ convertVertexLabelSet(variable.vertexLabelSet)
		+ '''}'''
	}

	def dispatch convertElement(AbstractEdgeVariable variable) {
		// for EdgeListVariable instances, limits are serialized directly in OperatorConverter.convertOperator(ExpandOperator op)
		'''{«variable.escapedName»}{'''
		+ convertEdgeLabelSet(variable.edgeLabelSet)
		+ '''}'''
	}

	def convertVertexLabelSet(VertexLabelSet ls) {
		convertLabelSet(ls, " \\land ")
	}
	def convertEdgeLabelSet(EdgeLabelSet ls) {
		convertLabelSet(ls, " \\lor ")
	}
	def convertLabelSet(LabelSet ls, String separator) {
		switch ls?.status {
			case LabelSetStatus.CONTRADICTING: "CONTRADICTING~LABEL~CONSTRAINTS"
			case null: "" // null labelset means empty labelset constraint
			default:
				switch ls {
					VertexLabelSet: ls.vertexLabels
					EdgeLabelSet: ls.edgeLabels
				}.map[escapedName].join(separator)
		}
	}
}
