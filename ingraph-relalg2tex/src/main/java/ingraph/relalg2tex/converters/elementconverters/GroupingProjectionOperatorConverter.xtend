package ingraph.relalg2tex.converters.elementconverters

import java.util.List
import relalg.ExpressionVariable
import relalg.GroupingOperator
import relalg.ProjectionOperator

class GroupingProjectionOperatorConverter {

	extension ExpressionConverter expressionConverter = new ExpressionConverter
	extension VariableConverter variableConverter = new VariableConverter

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