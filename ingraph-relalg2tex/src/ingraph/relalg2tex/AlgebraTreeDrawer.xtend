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
	
	
	
	def dispatch children(GetNodesOperator operation) {
		''''''
	}
	
	def dispatch children(AlphaOperator operation) {
		'''child{«operator.parent.node»}'''
	}

	def dispatch children(BetaOperator operation) {
		'''
		child{«operator.leftParent.node»}
		child{«operator.rightParent.node»}
		'''
	}
	

	def dispatch String convert(GetNodesOperator operation) {
		'''\getnodes{«operator.attribute.name»}'''
	}

	def dispatch String convert(InputRelation relation) {
		'''\relation{«relation.type»}'''
	}

	def dispatch String convert(ExpandOperator operation) {
		'''\expand«operator.direction.toString.toLowerCase»{}{}'''
	}

	def dispatch String convert(ProjectionOperator operation) {
		'''\projection_{...} \left(«operator.parent.convert»\right)'''
	}

	/***
	 * Beta operators are treated uniformly
	 */
	def dispatch String convert(BetaOperator operation) {
		'''\«betaOperator(operator)»«mask(operation)»'''
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
