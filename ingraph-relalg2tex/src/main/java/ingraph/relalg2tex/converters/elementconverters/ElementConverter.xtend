package ingraph.relalg2tex.converters.elementconverters

import relalg.EdgeVariable
import relalg.LabelSetStatus
import relalg.VertexVariable

/**
  * Convert ElementVariable to string, including the labels
  */
class ElementConverter {

  extension StringEscaper stringEscaper = new StringEscaper  
 
  def dispatch convertElement(VertexVariable variable) {
    '''{«variable.escapedName»}{'''
    + switch variable.vertexLabelSet?.status {
      case LabelSetStatus.CONTRADICTING: "CONTRADICTING LABEL CONSTRAINTS"
      case null: "" // null labelset means empty labelset constraint
      default: variable.vertexLabelSet.vertexLabels.map[escapedName].join(" \\land ")
    }
    + '''}'''
  }

  def dispatch convertElement(EdgeVariable variable) {
    '''{«variable.escapedName»}{'''
    + switch variable.edgeLabelSet?.status {
      case LabelSetStatus.CONTRADICTING: "CONTRADICTING LABEL CONSTRAINTS"
      case null: "" // null labelset means empty labelset constraint
      default: variable.edgeLabelSet.edgeLabels.map[escapedName].join(" \\lor ")
    }
    + '''}'''
  }

}
