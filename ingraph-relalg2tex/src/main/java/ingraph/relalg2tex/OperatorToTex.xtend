package ingraph.relalg2tex

import ingraph.relalg2tex.config.RelalgSerializerConfig
import java.util.List
import org.eclipse.emf.common.util.EList
import relalg.AbstractJoinOperator
import relalg.AllDifferentOperator
import relalg.AntiJoinOperator
import relalg.BinaryOperator
import relalg.Direction
import relalg.DuplicateEliminationOperator
import relalg.EdgeVariable
import relalg.ExpandOperator
import relalg.ExpressionVariable
import relalg.GetEdgesOperator
import relalg.GetVerticesOperator
import relalg.GroupingOperator
import relalg.JoinOperator
import relalg.LabelSetStatus
import relalg.LeftOuterJoinOperator
import relalg.MaxHops
import relalg.PathOperator
import relalg.ProductionOperator
import relalg.ProjectionOperator
import relalg.SelectionOperator
import relalg.SortAndTopOperator
import relalg.SortEntry
import relalg.SortOperator
import relalg.TopOperator
import relalg.UnionOperator
import relalg.UnwindOperator
import relalg.VertexVariable

class OperatorToTex {
  
  extension StringEscaper stringEscaper = new StringEscaper
  extension ExpressionConverter expressionConverter = new ExpressionConverter
  protected RelalgSerializerConfig config

  new(RelalgSerializerConfig config) {
    this.config = config
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
      '''«op.edgeVariable.toTexParameterWithLabels»''' + //
      '''{«op.minHops»}{«op.maxHops.hopsToString»}'''
    ]
  }

  def CharSequence hopsToString(MaxHops hops) {
    switch hops.maxHopsType {
      case LIMITED: hops.hops.toString
      case UNLIMITED: '''\infty'''
      default: throw new UnsupportedOperationException('''MaxHopsType «hops.maxHopsType» not supported.''')
    }
  }

  /**
   * NullaryOperators
   */
  def dispatch operatorToTex(GetVerticesOperator op) {
    #[
      '''\getvertices«op.vertexVariable.toTexParameterWithLabels»'''
    ]
  }

  /**
   * UnaryOperators
   */
  def dispatch operatorToTex(GroupingOperator op) {
    #['''\grouping{«op.entries.map[convertExpression].join(", ")»}''']
  }

  def dispatch operatorToTex(ProductionOperator op) {
    throw new UnsupportedOperationException('''Visualization of the production operator is currently not supported.''')
  }

  def dispatch operatorToTex(ProjectionOperator op) {
    #['''\projection{«op.elements.returnableElementList»}''']
  }

  def dispatch operatorToTex(SelectionOperator op) {
    #[
      '''\selection{''' +
      '''«IF op.condition !== null»«op.condition.convertExpression»«ELSE»\mathtt{«op.conditionString.escape.conditionToTex»}«ENDIF»''' +
      '''}'''
    ]
  }

  def dispatch operatorToTex(SortOperator op) {
    #[ sortOperatorToTex(op) ]
  }

  def dispatch operatorToTex(TopOperator op) {
    #[ topOperatorToTex(op) ]
  }

  def dispatch operatorToTex(SortAndTopOperator op) {
    #[ topOperatorToTex(op) + sortOperatorToTex(op) ]
  }

  def topOperatorToTex(TopOperator op) {
    '''\topp{«if (op.limit !== null) op.limit.value else ""»}{«if (op.skip !== null) op.skip.value else ""»}'''.toString
  }

  def sortOperatorToTex(SortOperator op) {
    '''\sort{«op.entries.map[entryToTex].join(", ")»}'''.toString
  }
  
  
  def dispatch operatorToTex(PathOperator op) {
    #[
      '''\transitiveclosure«op.direction.directionToTex»''' + //
      '''{«op.sourceVertexVariable.escapedName»}''' + //
      '''«op.targetVertexVariable.toTexParameterWithLabels»''' + //
      '''«op.edgeVariable.toTexParameterWithLabels»''' + //
      '''{«op.minHops»}{«op.maxHops.hopsToString»}'''
    ]
  }

  def dispatch operatorToTex(UnwindOperator op) {
    #['''\unwind{«op.sourceVariable.escapedName»}{«op.targetVariable.escapedName»}''']
  }

  /**
   * operatorToTeX
   */
  def dispatch operatorToTex(GetEdgesOperator op) {
    #[
      '''\getedges''' + '''«op.sourceVertexVariable.toTexParameterWithLabels»''' +
      '''«op.targetVertexVariable.toTexParameterWithLabels»''' + '''«op.edgeVariable.toTexParameterWithLabels»'''
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

  /**
   * directionToTex
   */
  def directionToTex(Direction direction) {
    switch direction {
      case BOTH: '''both'''
      case IN: '''in'''
      case OUT: '''out'''
      default: throw new UnsupportedOperationException('''Direction «direction» not supported.''')
    }
  }
  
    /**
   * Convert ElementVariable to string, including the labels
   */
  def dispatch toTexParameterWithLabels(VertexVariable variable) {
    '''{«variable.escapedName»}{'''
    + switch variable.vertexLabelSet?.status {
      case LabelSetStatus.CONTRADICTING: "CONTRADICTING LABEL CONSTRAINTS"
      case null: "" // null labelset means empty labelset constraint
      default: variable.vertexLabelSet.vertexLabels.map[escapedName].join(" \\land ")
    }
    + '''}'''
  }

  def dispatch toTexParameterWithLabels(EdgeVariable variable) {
    '''{«variable.escapedName»}{'''
    + switch variable.edgeLabelSet?.status {
      case LabelSetStatus.CONTRADICTING: "CONTRADICTING LABEL CONSTRAINTS"
      case null: "" // null labelset means empty labelset constraint
      default: variable.edgeLabelSet.edgeLabels.map[escapedName].join(" \\lor ")
    }
    + '''}'''
  }
 
  def edgeVariableList(EList<EdgeVariable> edgeVariables) {
    '''«edgeVariables.map["\\var{"+ escapedName + "}"].join(",~")»'''
  }



  /**
   * sort entry to TeX
   */
  def entryToTex(SortEntry entry) {
    val direction = switch (entry.direction) {
      case ASCENDING: "asc"
      case DESCENDING: "desc"
      default: throw new UnsupportedOperationException('''SortEntry «entry.direction» not supported.''')
    }
    '''\«direction» «entry.expression.convertExpression»'''
  }

  /**
   * list
   */
  def returnableElementList(List<ExpressionVariable> elements) {
    '''«elements.map[
      convertExpression(expression) + if (dontCare || hasInferredName) "" else '''\assign \var{«name»}'''
    ].join(",~")»'''
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