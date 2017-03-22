package ingraph.relalg2tex.converters.elementconverters

import ingraph.relalg2tex.config.RelalgConverterConfig
import relalg.AbstractJoinOperator
import relalg.AllDifferentOperator
import relalg.AntiJoinOperator
import relalg.BinaryOperator
import relalg.DualObjectSourceOperator
import relalg.DuplicateEliminationOperator
import relalg.ExpandOperator
import relalg.GetEdgesOperator
import relalg.GetVerticesOperator
import relalg.GroupingAndProjectionOperator
import relalg.GroupingOperator
import relalg.JoinOperator
import relalg.LeftOuterJoinOperator
import relalg.PathOperator
import relalg.ProductionOperator
import relalg.ProjectionOperator
import relalg.SelectionOperator
import relalg.SortAndTopOperator
import relalg.SortOperator
import relalg.TopOperator
import relalg.UnionOperator
import relalg.UnwindOperator

class OperatorConverter {
	
	extension MiscConverters miscConverters = new MiscConverters
	extension StringEscaper stringEscaper = new StringEscaper
	extension ElementConverter elementConverter = new ElementConverter
	extension ExpressionConverter expressionConverter = new ExpressionConverter
	protected RelalgConverterConfig config

	new(RelalgConverterConfig config) {
		this.config = config
	}

	def dispatch convertOperator(AllDifferentOperator op) {
		#['''\alldifferent{«op.edgeVariables.edgeVariableList»}''']
	}

	def dispatch convertOperator(BinaryOperator op) {
		#['''\«binaryOperator(op)»''']
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

	def dispatch convertOperator(GetVerticesOperator op) {
		#[
			'''\getvertices«op.vertexVariable.convertElement»'''
		]
	}

	def dispatch convertOperator(DualObjectSourceOperator op) {
		#[
			'''\var{Dual}'''
		]
	}

	/**
	 * UnaryOperators
	 */
	def dispatch convertOperator(ProductionOperator op) {
		throw new UnsupportedOperationException('''Visualization of the production operator is currently not supported.''')
	}

	def dispatch convertOperator(GroupingOperator op) {
		#['''\grouping{«op.entries.map[convertExpression].join(", ")»}''']
	}

	def dispatch convertOperator(ProjectionOperator op) {
		#['''\projection{«op.elements.convertReturnableElementList»}''']
	}

	def dispatch convertOperator(GroupingAndProjectionOperator op) {
		#['''\grouping{«op.entries.map[convertExpression].join(", ")»} \projection{«op.elements.convertReturnableElementList»}''']
	}

	def dispatch convertOperator(SelectionOperator op) {
		#[
			'''\selection{''' +
			'''«IF op.condition !== null»«op.condition.convertExpression»«ELSE»\mathtt{«op.conditionString.convertConditionString»}«ENDIF»''' +
			'''}'''
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

	def topOperatorToTex(TopOperator op) {
		'''\topp{«if (op.limit !== null) op.limit.convertExpression else ""»}{«if (op.skip !== null) op.skip.convertExpression else ""»}'''.toString
	}

	def sortOperatorToTex(SortOperator op) {
		'''\sort{«op.entries.map[entryToTex].join(", ")»}'''.toString
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
		#['''\unwind{«op.element.convertReturnableElement»}''']
	}

	def dispatch convertOperator(GetEdgesOperator op) {
		#[
			'''\getedges«IF op.directed»directed«ELSE»undirected«ENDIF»''' +
			'''«op.sourceVertexVariable.convertElement»«op.targetVertexVariable.convertElement»«op.edgeVariable.convertElement»'''
		]
	}

	/**
	 * BinaryOperators
	 */
	def dispatch binaryOperator(AbstractJoinOperator operator) {
		'''«operator.joinOperator»''' +
		'''«IF config.includeCommonVariables»\{«operator.commonVariables.map['''\var{«escapedName»}'''].join(", ")»\}«ENDIF»'''
	}

	def dispatch binaryOperator(UnionOperator operator) {
		'''union'''
	}

	def dispatch joinOperator(JoinOperator operator) {
		'''join'''
	}

	def dispatch joinOperator(AntiJoinOperator operator) {
		'''antijoin'''
	}

	def dispatch joinOperator(LeftOuterJoinOperator operator) {
		'''leftouterjoin'''
	}

	/**
	 * TernaryOperators
	 */
//  def dispatch

}
