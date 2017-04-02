package ingraph.relalg2tex.converters.operatorconverters

import ingraph.relalg2tex.converters.variableconverters.VariableNameConverter
import java.util.List
import relalg.ExpressionVariable
import relalg.GroupingOperator
import relalg.ProjectionOperator

class GroupingProjectionOperatorConverter {

	extension ingraph.relalg2tex.converters.elementconverters.ExpressionConverter expressionConverter = new ingraph.relalg2tex.converters.elementconverters.ExpressionConverter
	extension VariableNameConverter variableNameConverter = new VariableNameConverter

	def convertReturnableElementList(List<ExpressionVariable> elements) {
		'''«elements.map[convertVariable].join(", ")»'''
	}
	
	def convertReturnableElementListNegative(List<ExpressionVariable> elements) {
		'''«elements.map['''\ominus «convertVariable»'''].join(", ")»'''
	}

	def groupingOperator(GroupingOperator op) {
		'''\grouping{«op.entries.map[convertExpression].join(", ")»}'''
	}

	def projectionOperator(ProjectionOperator op) {
		'''
			\projection{«op.elements.convertReturnableElementList»
				«IF !op.elements.empty && !op.elementsToRemove.empty»,«ENDIF»
				«op.elementsToRemove.convertReturnableElementListNegative»}{«op.aggregations.convertReturnableElementList»}
		'''
	}

}
