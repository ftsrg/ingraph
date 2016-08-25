package ingraph.relalg2tex

import org.eclipse.emf.common.util.EList
import relalg.AlgebraExpression
import relalg.AllDifferentOperator
import relalg.AlphaOperator
import relalg.AntiJoinOperator
import relalg.BetaOperator
import relalg.DuplicateEliminationOperator
import relalg.ExpandOperator
import relalg.FilterOperator
import relalg.GetVerticesOperator
import relalg.JoinOperator
import relalg.ProductionOperator
import relalg.ProjectionOperator
import relalg.Variable
import relalg.EdgeVariable

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

	// toNode
	def CharSequence toNode(AlgebraExpression expression) {
		'''node {$«expression.convert»$}«children(expression)»'''
	}

	// children
	def dispatch children(GetVerticesOperator op) {
		''''''
	}

	def dispatch children(AlphaOperator op) {
		'''
		
			child{«op.parent.toNode»}
		'''
	}

	def dispatch children(BetaOperator op) {
		'''
		
			child{«op.leftParent.toNode»}
			child{«op.rightParent.toNode»}
		'''
	}

	// convert
	def dispatch convert(AllDifferentOperator op) {
		'''\alldifferent{«op.edgeVariables.edgeVariableList»}'''
	}
	
	def dispatch convert(BetaOperator op) {
		'''\«betaOperator(op)»'''
	}
	
	def dispatch convert(DuplicateEliminationOperator op) {
		'''\duplicateelimination'''
	}

	def dispatch convert(ExpandOperator op) {
		'''\expand«op.direction.toString.toLowerCase»{«op.edgeVariable.name.escape»}{«op.edgeVariable.edgeLabel.name.escape»}''' +
			'''{«op.sourceVertexVariable.name.escape»}{«op.targetVertexVariable.name.escape»}{«op.targetVertexVariable.vertexLabel.name.escape»}'''
	}

	def dispatch convert(FilterOperator op) {
		'''\selection{...}'''
	}

	def dispatch convert(GetVerticesOperator op) {
		'''\getvertices{«op.vertexVariable.name.escape»}{«op.vertexVariable.vertexLabel.name.escape»}'''
	}

	def dispatch convert(ProductionOperator op) {
		'''prod'''
	}

	def dispatch convert(ProjectionOperator op) {
		'''\projection{«op.variables.variableList»}'''
	}

	// betaOperator
	def dispatch betaOperator(JoinOperator operator) {
		'''join'''
	}

	def dispatch betaOperator(AntiJoinOperator operator) {
		'''antijoin'''
	}

	// escape
	def escape(String s) {
		s.replace("_", "\\_")
	}

	// list
	def variableList(EList<Variable> variables) {
		'''«variables.map[name].join(", ")»'''
	}	

	def edgeVariableList(EList<EdgeVariable> edgeVariables) {
		'''«edgeVariables.map[name.escape].join(", ")»'''
	}	

}
