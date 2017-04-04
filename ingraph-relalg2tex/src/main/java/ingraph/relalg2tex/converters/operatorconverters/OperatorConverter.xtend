package ingraph.relalg2tex.converters.operatorconverters

import ingraph.relalg2tex.config.RelalgConverterConfig
import ingraph.relalg2tex.converters.elementconverters.ElementConverter
import ingraph.relalg2tex.converters.elementconverters.ExpressionConverter
import ingraph.relalg2tex.converters.elementconverters.MiscConverters
import ingraph.relalg2tex.converters.elementconverters.StringEscaper
import ingraph.relalg2tex.converters.variableconverters.VariableNameConverter
import relalg.AllDifferentOperator
import relalg.BinaryOperator
import relalg.CreateOperator
import relalg.Direction
import relalg.DualObjectSourceOperator
import relalg.DuplicateEliminationOperator
import relalg.EdgeListVariable
import relalg.ExpandOperator
import relalg.GetEdgesOperator
import relalg.GetVerticesOperator
import relalg.GroupingAndProjectionOperator
import relalg.GroupingOperator
import relalg.PathOperator
import relalg.ProductionOperator
import relalg.ProjectionOperator
import relalg.SelectionOperator
import relalg.SortAndTopOperator
import relalg.SortOperator
import relalg.TopOperator
import relalg.UnwindOperator

class OperatorConverter {

	RelalgConverterConfig config

	extension StringEscaper stringEscaper = new StringEscaper
	extension MiscConverters miscConverters = new MiscConverters
	extension ElementConverter elementConverter = new ElementConverter
	extension ExpressionConverter expressionConverter = new ExpressionConverter
	extension VariableNameConverter variableNameConverter = new VariableNameConverter
	extension GroupingProjectionOperatorConverter groupingProjectionOperatorConverter = new GroupingProjectionOperatorConverter
	extension SortTopOperatorConverter sortTopOperatorConverter = new SortTopOperatorConverter
	extension BinaryOperatorConverter binaryOperatorConverter

	new(RelalgConverterConfig config) {
		this.config = config
		binaryOperatorConverter = new BinaryOperatorConverter(config)
	}

	/**
	 * NullaryOperators
	 */
	def dispatch convertOperator(DualObjectSourceOperator op) {
		#[
			'''\var{Dual}'''
		]
	}

	def dispatch convertOperator(GetEdgesOperator op) {
		#[
			'''\getedges«IF op.direction === Direction.BOTH»undirected«ELSE»directed«ENDIF»''' +
			'''«op.sourceVertexVariable.convertElement»«op.targetVertexVariable.convertElement»«op.edgeVariable.convertElement»'''
		]
	}

	def dispatch convertOperator(GetVerticesOperator op) {
		#[
			'''\getvertices«op.vertexVariable.convertElement»'''
		]
	}

	/**
	 * UnaryOperators
	 */
	def dispatch convertOperator(AllDifferentOperator op) {
		#['''\alldifferent{«op.edgeVariables.edgeVariableList»}''']
	}

	def dispatch convertOperator(DuplicateEliminationOperator op) {
		#['''\duplicateelimination''']
	}

	def dispatch convertOperator(ExpandOperator op) {
		val ev=op.edgeVariable
		#[
			if (op instanceof PathOperator) {
				'''\transitiveclosure'''
			} else {
				'''\expand'''
			} + //
			'''«op.direction.convertDirection»''' + //
			'''{«op.sourceVertexVariable.escapedName»}''' + //
			'''«op.targetVertexVariable.convertElement»''' + //
			'''«op.edgeVariable.convertElement»''' + //
			if (ev instanceof EdgeListVariable) {
				'''{«ev.minHops»}{«ev.maxHops.hopsToString»}'''
			} else {
				'''{}{}'''
			}
		]
	}

	def dispatch convertOperator(ProductionOperator op) {
		#['''\production{«op.elements.convertReturnableElementList»}''']
	}

	def dispatch convertOperator(GroupingOperator op) {
		#['''«groupingOperator(op)»''']
	}

	def dispatch convertOperator(ProjectionOperator op) {
		#['''«projectionOperator(op)»''']
	}

	def dispatch convertOperator(CreateOperator op) {
		#['''«createOperator(op)»''']
	}

	def dispatch convertOperator(GroupingAndProjectionOperator op) {
		#['''\groupingop  «projectionOperator(op)» [«op.order.join("; ")»]''']
	}

	def createOperator(CreateOperator op) {
		'''\create{«op.elements.map[it.expression.convertExpression].join(", ")»}'''
	}

	def dispatch convertOperator(SelectionOperator op) {
		#[
			'''
			\selection{
				«IF op.condition !== null»«op.condition.convertExpression»«ELSE»\mathtt{«op.conditionString.convertConditionString»}«ENDIF»
			}
			'''
		]
	}

	def dispatch convertOperator(SortOperator op) {
		#[ sortOperatorToTex(op) ]
	}

	def dispatch convertOperator(TopOperator op) {
		#[ topOperatorToTex(op) ]
	}

	def dispatch convertOperator(SortAndTopOperator op) {
		#[ sortOperatorToTex(op) + topOperatorToTex(op) ]
	}

	def dispatch convertOperator(UnwindOperator op) {
		#['''\unwind{«op.element.convertVariable.escape»}''']
	}

	/**
	 * BinaryOperators
	 */
	def dispatch convertOperator(BinaryOperator op) {
		#['''\«op.convertBinaryOperator»''']
	}

}
