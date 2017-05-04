package ingraph.relalg2tex.converters.operatorconverters

import ingraph.relalg2tex.converters.elementconverters.ExpressionConverter
import ingraph.relalg2tex.converters.variableconverters.VariableExpressionConverter
import java.util.List
import relalg.ExpressionVariable
import relalg.GroupingOperator
import relalg.ProjectionOperator
import relalg.Variable

class GroupingProjectionOperatorConverter {

	extension ExpressionConverter expressionConverter = new ExpressionConverter
	extension VariableExpressionConverter variableExpressionConverter = new VariableExpressionConverter

	def convertReturnableElementList(List<? extends Variable> elements) {
		'''«elements.map[convertVariable].join(", ")»'''
	}
	
	def convertReturnableElementListNegative(List<ExpressionVariable> elements) {
		'''«elements.map['''\ominus «convertVariable»'''].join(", ")»'''
	}

	def groupingOperator(GroupingOperator op) {
		'''\grouping{«op.elements.convertReturnableElementList»}{«op.aggregationCriteria.map[convertExpression].join(", ")»}'''
	}

	def projectionOperator(ProjectionOperator op) {
		'''
			\projection{«op.elements.convertReturnableElementList»
«««				«IF !op.elements.empty && !op.elementsToRemove.empty»,«ENDIF»
«««				«op.elementsToRemove.convertReturnableElementListNegative»
				}{}
		'''
	}

}
