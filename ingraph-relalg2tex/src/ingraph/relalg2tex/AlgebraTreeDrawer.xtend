package ingraph.relalg2tex

import relalg.AlgebraExpression
import relalg.AllDifferentOperator
import relalg.AlphaOperator
import relalg.AntiJoinOperator
import relalg.BetaOperator
import relalg.Direction
import relalg.DuplicateEliminationOperator
import relalg.ExpandOperator
import relalg.FilterOperator
import relalg.GetVerticesOperator
import relalg.JoinOperator
import relalg.ProductionOperator
import relalg.ProjectionOperator

class AlgebraTreeDrawer extends TexSerializer {

	new(boolean full) {
		super(full)
	}

	override serializeBody(AlgebraExpression expression) {
		'''
			\begin{tikzpicture}[]
			\«toNode(expression)»
			;
			\end{tikzpicture}
		'''
	}

	/**
	 * toNode
	 */
	def CharSequence toNode(AlgebraExpression expression) {
		'''node {$«expression.operatorSymbol»$}«children(expression)»'''
	}

	/**
	 * childrent
	 */
	def dispatch children(GetVerticesOperator op) {
		''''''
	}

	def dispatch children(AlphaOperator op) {
		'''
			
				child{«op.input.toNode»}
		'''
	}

	def dispatch children(BetaOperator op) {
		'''
			
				child{«op.leftInput.toNode»}
				child{«op.rightInput.toNode»}
		'''
	}

}
