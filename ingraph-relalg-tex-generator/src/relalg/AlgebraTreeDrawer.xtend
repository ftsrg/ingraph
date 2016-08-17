package relalg

class AlgebraTreeDrawer extends TexSerializer {

	new(boolean full) {
		super(full)
	}

	override serializeBody(AlgebraExpression expression) {
		'''
		\begin{tikzpicture}[]
		\«node(expression)»
		;
		\end{tikzpicture}
		'''
	}
	
	def CharSequence node(AlgebraExpression expression) {
		'''node {$«expression.convert»$} «children(expression)»'''
	}
	
	
	
	def dispatch children(GetNodesOperation operation) {
		''''''
	}
	
	def dispatch children(AlphaOperation operation) {
		'''child{«operation.parent.node»}'''
	}

	def dispatch children(BetaOperation operation) {
		'''
		child{«operation.leftParent.node»}
		child{«operation.rightParent.node»}
		'''
	}
	

	def dispatch String convert(GetNodesOperation operation) {
		'''\getnodes{«operation.attribute.name»}'''
	}

	def dispatch String convert(InputRelation relation) {
		'''\relation{«relation.type»}'''
	}

	def dispatch String convert(ExpandOperation operation) {
		'''\expand«operation.direction.toString.toLowerCase»{}{}'''
	}

	def dispatch String convert(TrimmerOperation operation) {
		'''\projection_{...} \left(«operation.parent.convert»\right)'''
	}

	/***
	 * Beta operations are treated uniformly
	 */
	def dispatch String convert(BetaOperation operation) {
		'''\«betaOperator(operation)»«mask(operation)»'''
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
