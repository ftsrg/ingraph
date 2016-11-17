package ingraph.relalg2tex

import ingraph.relalg.util.SchemaInferencer
import java.io.File
import java.nio.charset.Charset
import org.apache.commons.io.FileUtils
import org.eclipse.emf.common.util.EList
import relalg.AbstractJoinOperator
import relalg.AllDifferentOperator
import relalg.AntiJoinOperator
import relalg.ArithmeticComparisonExpression
import relalg.ArithmeticComparisonOperator
import relalg.AttributeVariable
import relalg.BinaryArithmeticOperator
import relalg.BinaryLogicalExpression
import relalg.BinaryLogicalOperator
import relalg.BinaryOperator
import relalg.BooleanLiteral
import relalg.Direction
import relalg.DuplicateEliminationOperator
import relalg.EdgeVariable
import relalg.ElementVariable
import relalg.ExpandOperator
import relalg.GetEdgesOperator
import relalg.GetVerticesOperator
import relalg.IntegerLiteral
import relalg.JoinOperator
import relalg.NamedElement
import relalg.Operator
import relalg.ProductionOperator
import relalg.ProjectionOperator
import relalg.RelalgContainer
import relalg.SelectionOperator
import relalg.StringLiteral
import relalg.UnaryArithmeticOperator
import relalg.UnaryLogicalExpression
import relalg.UnaryLogicalOperator
import relalg.UnionOperator
import relalg.UnwindOperator
import relalg.Variable
import relalg.VertexVariable

abstract class AbstractRelalgSerializer {

	protected val RelalgSerializerConfig config

	protected new() {
		this.config = RelalgSerializerConfig.defaultConfig
	}

	protected new(RelalgSerializerConfig config) {
		this.config = config
	}

	def serialize(RelalgContainer container, String filename) {
		val schemaInferencer = new SchemaInferencer(false)
		schemaInferencer.addSchemaInformation(container)

		val tex = serialize(container)
		if (config.standaloneDocument) {
			val file = new File("../visualization/" + filename + ".tex")
			FileUtils.writeStringToFile(file, tex.toString, Charset.forName("UTF-8"))
		}
		tex
	}

	def serialize(RelalgContainer container) {
		convertAlgebraExpression(container.rootExpression)
	}

	/**
	 * convertExpression
	 */
	def dispatch CharSequence convertAlgebraExpression(ProductionOperator op) {
		convertAlgebraExpression(op.input)
	}

	def dispatch CharSequence convertAlgebraExpression(Operator expression) {
		'''
			«IF config.standaloneDocument»
				\documentclass[varwidth=100cm,convert={density=120}]{standalone}
				\usepackage[active,tightpage]{preview}
				
				\input{../../../ingraph/visualization/inputs/relalg-packages}
				\input{../../../ingraph/visualization/inputs/relalg-commands}
				
				\begin{document}
				\begin{preview}
			«ENDIF»
			«serializeBody(expression)»
			«IF config.standaloneDocument»
				\end{preview}
				\end{document}
			«ENDIF»
		'''
	}

	def abstract CharSequence serializeBody(Operator expression)

	def operator(Operator op) {
		op.operatorToTex.join("")
	}

	/**
	 * operatorToTex
	 */
	def dispatch operatorToTex(AllDifferentOperator op) {
		#['''\alldifferent{«op.edgeVariables.edgeVariableList»}''']
	}

	def dispatch operatorToTex(BinaryOperator op) {
		#['''\«binaryOperator(op)»''']
	}

	def dispatch operatorToTex(DuplicateEliminationOperator op) {
		#['''\duplicateelimination''']
	}

	def dispatch operatorToTex(ExpandOperator op) {
		#[
			'''\expand«op.direction.directionToTex»''' + //
			'''{«op.sourceVertexVariable.escapedName»}''' + //
			'''«op.targetVertexVariable.toTexParameterWithLabels»''' + //
			'''«op.edgeVariable.toTexParameterWithLabels»'''
		]
	}

	def dispatch operatorToTex(SelectionOperator op) {
		#[
			'''\selection{''' +
			'''«IF op.condition != null»«op.condition.convertExpression»«ELSE»\mathtt{«op.conditionString.escape.conditionToTex»}«ENDIF»''' +
			'''}'''
		]
	}

	def dispatch operatorToTex(GetEdgesOperator op) {
		#[
			'''\getedges''' + '''«op.sourceVertexVariable.toTexParameterWithLabels»''' +
			'''«op.targetVertexVariable.toTexParameterWithLabels»''' + '''«op.edgeVariable.toTexParameterWithLabels»'''
		]
	}

	def dispatch operatorToTex(GetVerticesOperator op) {
		#[
			'''\getvertices''' + '''{«op.vertexVariable.escapedName»}''' +
			'''{«op.vertexVariable.vertexLabels.map[escapedName].join(":")»}'''
		]
	}

	def dispatch operatorToTex(ProjectionOperator op) {
		#['''\projection{«op.variables.variableList»}''']
	}

	def dispatch operatorToTex(UnwindOperator op) {
		#['''\unwind{«op.sourceVariable.escapedName»}{«op.targetVariable.escapedName»}''']
	}

	def dispatch operatorToTex(ProductionOperator op) {
		throw new UnsupportedOperationException('''Visualization of the production operator is currently not supported.''')
	}

	/**
	 * binaryOperator
	 */
	def dispatch binaryOperator(AbstractJoinOperator operator) {
		'''«operator.joinOperator»''' +
		'''«IF config.includeMutualVariables»\{«operator.mutualVariables.map['''\var{«name.escape»}'''].join(", ")»\}«ENDIF»'''
	}

	def dispatch binaryOperator(UnionOperator operator) {
		'''union'''
	}

	/** joinOperator */
	def dispatch joinOperator(JoinOperator operator) {
		'''join'''
	}

	def dispatch joinOperator(AntiJoinOperator operator) {
		'''antijoin'''
	}

	/**
	 * directionToTex
	 */
	def directionToTex(Direction direction) {
		switch direction {
			case BOTH: {
				'''both'''
			}
			case IN: {
				'''in'''
			}
			case OUT: {
				'''out'''
			}
		}
	}

	/**
	 * escape
	 */
	def escape(String s) {
		s //
		.replace('''\''', '''\backslash{}''').replace('''_''', '''\_''') //
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
	 * Convert ElementVariable to string, including the labels
	 */
	def dispatch toTexParameterWithLabels(VertexVariable variable) {
		'''{«variable.escapedName»}{«variable.vertexLabels.map[escapedName].join("\\land")»}'''
	}

	def dispatch toTexParameterWithLabels(EdgeVariable variable) {
		'''{«variable.escapedName»}{«variable.edgeLabels.map[escapedName].join("\\lor")»}'''
	}

	/**
	 * escapedName
	 */
	def escapedName(NamedElement element) {
		'''«element?.name?.escape»'''
	}

	/**
	 * conversion for operators
	 */
	def convert(ArithmeticComparisonOperator op) {
		switch (op) {
			case EQUAL_TO: '''='''
			case GREATER_THAN: '''>'''
			case GREATER_THAN_OR_EQUAL: '''\geq'''
			case LESS_THAN: '''<'''
			case LESS_THAN_OR_EQUAL: '''\leq'''
			case NOT_EQUAL_TO: '''\neq'''
		}
	}

	def convert(BinaryLogicalOperator op) {
		switch (op) {
			case AND: '''\land'''
			case OR: '''\lor'''
			case XOR: '''\lxor'''
		}
	}

	def convert(UnaryLogicalOperator op) {
		switch (op) {
			case NOT: '''\neg'''
		}
	}

	def convert(BinaryArithmeticOperator op) {
		switch (op) {
			case DIVISION: '''/'''
			case MINUS: '''-'''
			case MOD: '''\mod'''
			case MULTIPLICATION: '''\cdot'''
			case PLUS: '''+'''
			case POWER: '''^'''
		}
	}

	def convert(UnaryArithmeticOperator op) {
		switch (op) {
			case MINUS: '''-'''
			case PLUS: ''''''
		}
	}

	/**
	 * convertComparable
	 */
	def dispatch convertComparable(IntegerLiteral integerLiteral) {
		'''\literal{«integerLiteral.value.toString»}'''
	}

	def dispatch convertComparable(StringLiteral stringLiteral) {
		'''\literal{'«stringLiteral.value.toString»'}'''
	}

	def dispatch convertComparable(ElementVariable elementVariable) {
		'''\var{«elementVariable.name»}'''
	}

	def dispatch convertComparable(AttributeVariable attributeVariable) {
		'''\var{«attributeVariable.element.name».«attributeVariable.name»}'''
	}

	/**
	 * convertExpression
	 */
	def dispatch String convertExpression(BinaryLogicalExpression exp) {
		'''«exp.leftOperand.convertExpression» «exp.operator.convert» «exp.rightOperand.convertExpression»'''
	}

	def dispatch String convertExpression(UnaryLogicalExpression exp) {
		'''«exp.operator.convert» «exp.leftOperand.convertExpression»'''
	}

	def dispatch String convertExpression(ArithmeticComparisonExpression exp) {
		'''«exp.leftOperand.convertComparable» «exp.operator.convert» «exp.rightOperand.convertComparable»'''
	}

	def dispatch String convertExpression(BooleanLiteral exp) {
		'''\mathtt{«if (exp.value) "true" else "false"»}'''
	}

	/**
	 * prettifyCondition
	 */
	def conditionToTex(String s) {
		s //
		.replaceAll(''' XOR ''', ''' \\lxor ''') //
		.replaceAll(''' AND ''', ''' \\land ''') //
		.replaceAll(''' OR ''', ''' \\lor ''') //
		.replaceAll(''' ''', '''~''') //
	}

}
		