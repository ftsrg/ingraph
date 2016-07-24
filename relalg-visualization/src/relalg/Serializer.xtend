package relalg

class Serializer {

	def convert(AlgebraExpression expression) {
		'''$$«convertToLateX(expression)»$$'''
	}
	
	def dispatch String convertToLateX(TrimmerOperation operation) {
		'''\projection_{...} («operation.parent.convertToLateX»)'''
	}

	def dispatch String convertToLateX(BetaOperation operation) {
		'''\left(«operation.leftParent.convertToLateX» \«betaOperator(operation)»«mask(operation)» «operation.rightParent.convertToLateX »\right)'''
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
