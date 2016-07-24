package relalg

class Serializer {

	def dispatch String convertToLateX(TrimmerOperation expression) {
		'''\projection_{...} («expression.parent.convertToLateX»)'''
	}

	def dispatch String convertToLateX(BetaOperation expression) {
		'''(«expression.leftParent.convertToLateX» «betaOperator(expression)» «expression.rightParent.convertToLateX »)'''
	}
	
	def dispatch String convertToLateX(InputRelation expression) {
		expression.type
	}

	def dispatch betaOperator(JoinOperation operation) {
		'''\join'''
	}

	def dispatch betaOperator(AntiJoinOperation operation) {
		'''\antijoin'''
	}

}
