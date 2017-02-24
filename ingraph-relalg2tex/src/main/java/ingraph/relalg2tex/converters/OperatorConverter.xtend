package ingraph.relalg2tex.converters

import ingraph.relalg2tex.config.RelalgSerializerConfig
import relalg.AbstractJoinOperator
import relalg.AllDifferentOperator
import relalg.AntiJoinOperator
import relalg.BinaryOperator
import relalg.DualObjectSourceOperator
import relalg.DuplicateEliminationOperator
import relalg.ExpandOperator
import relalg.GetEdgesOperator
import relalg.GetVerticesOperator
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
  protected RelalgSerializerConfig config

  new(RelalgSerializerConfig config) {
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
  def dispatch convertOperator(GroupingOperator op) {
    #['''\grouping{«op.entries.map[convertExpression].join(", ")»}''']
  }

  def dispatch convertOperator(ProductionOperator op) {
    throw new UnsupportedOperationException('''Visualization of the production operator is currently not supported.''')
  }

  def dispatch convertOperator(ProjectionOperator op) {
    #['''\projection{«op.elements.returnableElementList»}''']
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
    #[ topOperatorToTex(op) + sortOperatorToTex(op) ]
  }

  def topOperatorToTex(TopOperator op) {
    '''\topp{«if (op.limit !== null) op.limit.value else ""»}{«if (op.skip !== null) op.skip.value else ""»}'''.toString
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
    #['''\unwind{«op.sourceVariable.escapedName»}{«op.targetVariable.escapedName»}''']
  }

  /**
   * operatorToTeX
   */
  def dispatch convertOperator(GetEdgesOperator op) {
    #[
      '''\getedges''' +
      '''«op.sourceVertexVariable.convertElement»''' +
      '''«op.targetVertexVariable.convertElement»''' +
      '''«op.edgeVariable.convertElement»'''
    ]
  }

//  override dispatch operatorToTex(GetEdgesOperator op) {
//    #[
//      '''\getedgesi«op.sourceVertexVariable.toTexParameterWithLabels»«op.targetVertexVariable.toTexParameterWithLabels»''',
//      '''\getedgesii«op.edgeVariable.toTexParameterWithLabels»'''
//    ]
//  }
  
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

  /** JoinLikeOperators */
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
