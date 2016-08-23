package ingraph.relalg2tex

import relalg.AlgebraExpression
import relalg.AntiJoinOperator
import relalg.BetaOperator
import relalg.ExpandOperator
import relalg.GetNodesOperator
import relalg.InputRelation
import relalg.JoinOperator
import relalg.ProjectionOperator
import relalg.DuplicateEliminationOperator

class ExpressionSerializer extends TexSerializer {

	/***
	 * Whether to use parentheses in the TeX expressions
	 */
	boolean parentheses

	new(boolean full, boolean parentheses) {
		super(full)
		this.parentheses = parentheses
	}

	override serializeBody(AlgebraExpression expression) {
		'''$$«convert(expression)»$$'''
	}
	
	/***
	 * 
	 */
	def dispatch String convert(GetNodesOperator operation) {
		'''\getnodes{«operator.attribute.name»}'''
	}

	def dispatch String convert(InputRelation relation) {
		'''\relation{«relation.type»}'''
	}

	def dispatch String convert(DuplicateEliminationOperator operation) {
		'''\duplicateelimination \left(«operator.parent.convert»\right)'''
	}

	def dispatch String convert(ExpandOperator operation) {
		'''\expand«operator.direction.toString.toLowerCase»{}{} \left(«operation.parent.convert»\right)'''
	}

	def dispatch String convert(ProjectionOperator operation) {
		'''\projection{...} \left(«operator.parent.convert»\right)'''
	}

	/***
	 * Beta operators are treated uniformly
	 */
	def dispatch String convert(BetaOperator operation) {
		'''
		«IF parentheses» \left( «ENDIF»
		«operator.leftParent.convert» \«betaOperator(operation)»«mask(operation)» «operation.rightParent.convert»«IF parentheses» \right) «ENDIF»'''
	}

	def mask(BetaOperator operation) {
		val b = operator.bindings

		'''{«b.map[leftAttribute.name].join(",")»}{«b.map[rightAttribute.name].join(",")»}'''
	}

	def dispatch betaOperator(JoinOperator operation) {
		'''join'''
	}

	def dispatch betaOperator(AntiJoinOperator operation) {
		'''antijoin'''
	}

}
