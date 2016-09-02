package ingraph.relalg2tex

import org.eclipse.emf.common.util.EList
import relalg.AlgebraExpression
import relalg.AllDifferentOperator
import relalg.AntiJoinOperator
import relalg.BetaOperator
import relalg.Direction
import relalg.DuplicateEliminationOperator
import relalg.EdgeVariable
import relalg.ExpandOperator
import relalg.FilterOperator
import relalg.GetVerticesOperator
import relalg.JoinOperator
import relalg.ProductionOperator
import relalg.ProjectionOperator
import relalg.Variable
import org.apache.commons.io.FileUtils
import java.nio.charset.Charset
import java.io.File
import relalg.Container

abstract class TexSerializer {

	/**
	 * whether to generate a full TeX document
	 */
	protected boolean full

	new(boolean full) {
		this.full = full
	}

	def serialize(Container container, String filename) {
		val tex = convertExpression(container.rootExpression)
		val file = new File("../visualization/" + filename + ".tex")
		FileUtils.writeStringToFile(file, tex.toString, Charset.forName("UTF-8"))
		tex
	}

	/**
	 * convertExpression
	 */
	def dispatch CharSequence convertExpression(ProductionOperator op) {
		convertExpression(op.input)
	}

	def dispatch CharSequence convertExpression(AlgebraExpression expression) {
		'''
			«IF full»
				\documentclass{minimal}
				
				\input{inputs/relalg-packages}
				\input{inputs/relalg-commands}
				
				\begin{document}
			«ENDIF»
			«serializeBody(expression)»
			«IF full»
				\end{document}
			«ENDIF»
		'''
	}

	def abstract CharSequence serializeBody(AlgebraExpression expression)

	/**
	 * operatorSymbol
	 */
	def dispatch operatorSymbol(AllDifferentOperator op) {
		'''\alldifferent{«op.edgeVariables.edgeVariableList»}'''
	}

	def dispatch operatorSymbol(BetaOperator op) {
		'''\«betaOperator(op)»'''
	}

	def dispatch operatorSymbol(DuplicateEliminationOperator op) {
		'''\duplicateelimination'''
	}

	def dispatch operatorSymbol(ExpandOperator op) {
		'''\expand«op.direction.directionToTex»''' +
			'''{«op.edgeVariable.name.escape»}{«op.edgeVariable.edgeLabel.name.escape»}''' +
			'''{«op.sourceVertexVariable.name.escape»}{«op.targetVertexVariable.name.escape»}''' +
			'''{«op.targetVertexVariable.vertexLabel?.name?.escape»}'''
	}

	def dispatch operatorSymbol(FilterOperator op) {
		'''\selection{...}'''
	}

	def dispatch operatorSymbol(GetVerticesOperator op) {
		'''\getvertices{«op.vertexVariable.name.escape»}{«op.vertexVariable.vertexLabel.name.escape»}'''
	}

	def dispatch operatorSymbol(ProductionOperator op) {
		'''prod'''
	}

	def dispatch operatorSymbol(ProjectionOperator op) {
		'''\projection{«op.variables.variableList»}'''
	}

	/**
	 * betaOperator
	 */
	def dispatch betaOperator(JoinOperator operator) {
		'''join'''
	}

	def dispatch betaOperator(AntiJoinOperator operator) {
		'''antijoin'''
	}

	def directionToTex(Direction direction) {
		switch direction {
			case BOTH: {
				""
			}
			case IN: {
				"in"
			}
			case OUT: {
				"out"
			}
		}
	}

	/**
	 * escape
	 */
	def escape(String s) {
		s.replace("_", "\\_")
	}

	/**
	 * list
	 */
	def variableList(EList<Variable> variables) {
		'''«variables.map["\\var{"+ name.escape + "}"].join(",~")»'''
	}

	def edgeVariableList(EList<EdgeVariable> edgeVariables) {
		'''«edgeVariables.map["\\var{"+ name.escape + "}"].join(",~")»'''
	}

}
