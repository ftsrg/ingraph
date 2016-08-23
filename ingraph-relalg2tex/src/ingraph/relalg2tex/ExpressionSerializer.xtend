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
	def dispatch String convert(GetNodesOperator operator) {
		'''\getnodes{«operator.attribute.name»}'''
	}

	def dispatch String convert(InputRelation relation) {
		'''\relation{«relation.type»}'''
	}

	def dispatch String convert(DuplicateEliminationOperator operator) {
		'''\duplicateelimination \left(«operator.parent.convert»\right)'''
	}

	def dispatch String convert(ExpandOperator operator) {
		'''\expand«operator.direction.toString.toLowerCase»{}{} \left(«operator.parent.convert»\right)'''
	}

	def dispatch String convert(ProjectionOperator operator) {
		'''\projection{...} \left(«operator.parent.convert»\right)'''
	}

	/***
	 * Beta operators are treated uniformly
	 */
	def dispatch String convert(BetaOperator operator) {
		'''
		«IF parentheses» \left( «ENDIF»
		«operator.leftParent.convert» \«betaOperator(operator)»«mask(operator)» «operator.rightParent.convert»«IF parentheses» \right) «ENDIF»'''
	}

	def mask(BetaOperator operator) {
		val b = operator.bindings

		'''{«b.map[leftAttribute.name].join(",")»}{«b.map[rightAttribute.name].join(",")»}'''
	}

	def dispatch betaOperator(JoinOperator operator) {
		'''join'''
	}

	def dispatch betaOperator(AntiJoinOperator operator) {
		'''antijoin'''
	}

}
