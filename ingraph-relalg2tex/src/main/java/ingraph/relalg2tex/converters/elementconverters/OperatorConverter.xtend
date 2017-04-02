package ingraph.relalg2tex.converters.elementconverters

import ingraph.relalg2tex.config.RelalgConverterConfig
import relalg.AllDifferentOperator
import relalg.BinaryOperator
import relalg.DualObjectSourceOperator
import relalg.DuplicateEliminationOperator
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

	extension MiscConverters miscConverters = new MiscConverters
	extension StringEscaper stringEscaper = new StringEscaper
	extension ElementConverter elementConverter = new ElementConverter
	extension ExpressionConverter expressionConverter = new ExpressionConverter
	extension VariableConverter variableConverter = new VariableConverter
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
			'''\getedges«IF op.directed»directed«ELSE»undirected«ENDIF»''' +
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
		#[
			'''\expand«op.direction.convertDirection»''' + //
			'''{«op.sourceVertexVariable.escapedName»}''' + //
			'''«op.targetVertexVariable.convertElement»''' + //
			'''«op.edgeVariable.convertElement»''' + //
			'''{«op.minHops»}{«op.maxHops.hopsToString»}'''
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

	def dispatch convertOperator(GroupingAndProjectionOperator op) {
		#['''«groupingOperator(op)» «projectionOperator(op)»''']
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

	def dispatch convertOperator(PathOperator op) {
		#[
			'''\transitiveclosure«op.direction.convertDirection»''' + //
			'''{«op.sourceVertexVariable.escapedName»}''' + //
			'''«op.targetVertexVariable.convertElement»''' + //
			'''«op.edgeVariable.convertElement»''' + //
			'''{«op.minHops»}{«op.maxHops.hopsToString»}'''
		]
	}

	def dispatch convertOperator(UnwindOperator op) {
		#['''\unwind{«op.element.convertVariable»}''']
	}

	/**
	 * BinaryOperators
	 */
	def dispatch convertOperator(BinaryOperator op) {
		#['''\«op.convertBinaryOperator»''']
	}

}
