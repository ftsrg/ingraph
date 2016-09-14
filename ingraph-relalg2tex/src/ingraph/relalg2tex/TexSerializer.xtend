package ingraph.relalg2tex

import java.io.File
import java.nio.charset.Charset
import org.apache.commons.io.FileUtils
import org.eclipse.emf.common.util.EList
import relalg.AlgebraExpression
import relalg.AllDifferentOperator
import relalg.AntiJoinOperator
import relalg.BetaOperator
import relalg.Container
import relalg.Direction
import relalg.DuplicateEliminationOperator
import relalg.EdgeVariable
import relalg.ExpandOperator
import relalg.GetEdgesOperator
import relalg.GetVerticesOperator
import relalg.JoinOperator
import relalg.ProductionOperator
import relalg.ProjectionOperator
import relalg.SelectionOperator
import relalg.UnionOperator
import relalg.Variable
import relalg.ArithmeticComparisonOperator
import relalg.BinaryLogicalOperator
import relalg.BinaryArithmeticOperator
import relalg.UnaryArithmeticOperator

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
				\documentclass[varwidth,convert={density=120}]{standalone}
				
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

	def dispatch operatorSymbol(SelectionOperator op) {
		'''\selection{«op.condition?.replaceAll(" ", "~")»}'''
	}

	def dispatch operatorSymbol(
		GetEdgesOperator op) {
		'''\getedges{«op.sourceVertexVariable.name.escape»}{«op.targetVertexVariable.vertexLabel.name.escape»}{«op.edgeVariable.edgeLabel.name.escape»}'''
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

	def dispatch betaOperator(UnionOperator operator) {
		'''union'''
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

	/**
	 * toStrings for operators
	 */
	def toString(ArithmeticComparisonOperator op) {
		switch (op) {
			case EQUAL_TO: '''='''
			case GREATER_THAN: '''>'''
			case GREATER_THAN_OR_EQUAL: '''\geq'''
			case LESS_THAN: '''<'''
			case LESS_THAN_OR_EQUAL: '''\leq'''
			case NOT_EQUAL_TO: '''\neq'''
		}
	}

	def toString(BinaryLogicalOperator op) {
		switch (op) {
			case AND: '''\land'''
			case OR: '''\lor'''
			case XOR: '''\lxor'''
		}
	}

	def toString(BinaryArithmeticOperator op) {
		switch (op) {
			case DIVISION: '''/'''
			case MINUS: '''-'''
			case MOD: '''\mod'''
			case MULTIPLICATION: '''\cdot'''
			case PLUS: '''+'''
			case POWER: '''^'''
		}
	}

	def toString(UnaryArithmeticOperator op) {
		switch (op) {
			case MINUS: '''-'''
			case PLUS: ''''''
		}
	}

}
