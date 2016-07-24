package relalg

import org.eclipse.xtext.xbase.lib.Functions.Function1

class Serializer {
	
	boolean parentheses
	
	new(boolean parentheses) {
		this.parentheses = parentheses
	}
	
	def <Source> ite(Source s, Function1<Source, Boolean> pred, String s1, String s2){
		if (pred.apply(s)) {
			return s1
		} else {
			return s2
		}
	}

	def convert(AlgebraExpression expression) {
		'''$$«convertToLateX(expression)»$$'''
	}
	
	def dispatch String convertToLateX(TrimmerOperation operation) {
		'''\projection_{...} («operation.parent.convertToLateX»)'''
	}

	def dispatch String convertToLateX(BetaOperation operation) {
		'''«IF parentheses» \left( «ENDIF»«operation.leftParent.convertToLateX» \«betaOperator(operation)»«mask(operation)» «operation.rightParent.convertToLateX »«IF parentheses» \right) «ENDIF»'''
	}
	
	def mask(BetaOperation operation) {
		val b = operation.bindings
		
		'''{«b.map[leftAttribute.name].join(",")»}{«b.map[rightAttribute.name].join(",")»}'''
	}
	
	def dispatch String convertToLateX(InputRelation relation) {
		'''\relation{«relation.type»}'''
	}

	def dispatch betaOperator(JoinOperation operation) {
		'''join'''
	}

	def dispatch betaOperator(AntiJoinOperation operation) {
		'''antijoin'''
	}

}
