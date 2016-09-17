package ingraph.relalg2tex

import relalg.BetaOperator
import relalg.DuplicateEliminationOperator
import relalg.ExpandOperator
import relalg.GetVerticesOperator
import relalg.Operator
import relalg.ProjectionOperator

class ExpressionSerializer extends AbstractRelalgSerializer {

	/**
	 * whether to use parentheses in the TeX expressions
	 */
	boolean parentheses

	new(boolean full, boolean parentheses) {
		super(full)
		this.parentheses = parentheses
	}

	override serializeBody(Operator expression) {
		'''$$«convert(expression)»$$'''
	}

	/**
	 * alpha operators
	 */
	def dispatch String convert(GetVerticesOperator operator) {
		'''\getvertices{}'''
	}

	def dispatch String convert(DuplicateEliminationOperator operator) {
		'''\duplicateelimination \left(«operator.input.convert»\right)'''
	}

	def dispatch String convert(ExpandOperator operator) {
		'''\expand«operator.direction.toString.toLowerCase»{}{} \left(«operator.input.convert»\right)'''
	}

	def dispatch String convert(ProjectionOperator operator) {
		'''\projection{...} \left(«operator.input.convert»\right)'''
	}

	/**
	 * beta operators
	 */
	def dispatch String convert(BetaOperator operator) {
		'''
		«IF parentheses» \left( «ENDIF»
		«operator.leftInput.convert» \«betaOperator(operator)» «operator.rightInput.convert»«IF parentheses» \right) «ENDIF»'''
	}

}
