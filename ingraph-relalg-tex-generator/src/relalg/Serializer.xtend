package relalg

class Serializer {
	
	/***
	 * Whether to use parentheses in the TeX expressions
	 */
	boolean parentheses
	
	new(boolean parentheses) {
		this.parentheses = parentheses
	}

	def serialize(AlgebraExpression expression) {
		'''$$«convert(expression)»$$'''
	}

	/***
	 * 
	 */	
	def dispatch String convert(GetNodesOperation operation) {
		'''\getnodes{«operation.attribute.name»}'''
	}

	def dispatch String convert(InputRelation relation) {
		'''\relation{«relation.type»}'''
	}
	
	
	def dispatch String convert(ExpandOperation operation) {
		'''\expand«operation.direction.toString.toLowerCase»{}{} \left(«operation.parent.convert»\right)'''
	}
	
	def dispatch String convert(TrimmerOperation operation) {
		'''\projection_{...} \left(«operation.parent.convert»\right)'''
	}

	/***
	 * Beta operations are treated uniformly
	 */
	def dispatch String convert(BetaOperation operation) {
		'''
		«IF parentheses» \left( «ENDIF»
		«operation.leftParent.convert»
		\«betaOperator(operation)»«mask(operation)»
		«operation.rightParent.convert »
		«IF parentheses» \right) «ENDIF»
		'''
	}
	
	def mask(BetaOperation operation) {
		val b = operation.bindings
		
		'''{«b.map[leftAttribute.name].join(",")»}{«b.map[rightAttribute.name].join(",")»}'''
	}

	def dispatch betaOperator(JoinOperation operation) {
		'''join'''
	}

	def dispatch betaOperator(AntiJoinOperation operation) {
		'''antijoin'''
	}

}
