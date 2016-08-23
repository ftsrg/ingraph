package ingraph.relalg2tex

import relalg.AlgebraExpression
import relalg.AlphaOperator
import relalg.AntiJoinOperator
import relalg.BetaOperator
import relalg.ExpandOperator
import relalg.GetNodesOperator
import relalg.InputRelation
import relalg.JoinOperator
import relalg.ProjectionOperator

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
	
	
	
	def dispatch children(GetNodesOperator operator) {
		''''''
	}
	
	def dispatch children(AlphaOperator operator) {
		'''child{«operator.parent.node»}'''
	}

	def dispatch children(BetaOperator operator) {
		'''
		child{«operator.leftParent.node»}
		child{«operator.rightParent.node»}
		'''
	}
	

	def dispatch String convert(GetNodesOperator operator) {
		'''\getnodes{«operator.attribute.name»}'''
	}

	def dispatch String convert(InputRelation relation) {
		'''\relation{«relation.type»}'''
	}

	def dispatch String convert(ExpandOperator operator) {
		'''\expand«operator.direction.toString.toLowerCase»{}{}'''
	}

	def dispatch String convert(ProjectionOperator operator) {
		'''\projection_{...} \left(«operator.parent.convert»\right)'''
	}

	/***
	 * Beta operators are treated uniformly
	 */
	def dispatch String convert(BetaOperator operator) {
		'''\«betaOperator(operator)»«mask(operator)»'''
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
