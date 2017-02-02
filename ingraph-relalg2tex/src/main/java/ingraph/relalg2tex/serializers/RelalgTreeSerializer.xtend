package ingraph.relalg2tex.serializers

import ingraph.relalg2tex.StringEscaper
import ingraph.relalg2tex.config.RelalgSerializerConfig
import java.util.List
import relalg.AbstractJoinOperator
import relalg.AttributeVariable
import relalg.BinaryOperator
import relalg.Cardinality
import relalg.ElementVariable
import relalg.Expression
import relalg.ExpressionVariable
import relalg.ListVariable
import relalg.NullaryOperator
import relalg.Operator
import relalg.TernaryOperator
import relalg.UnaryOperator
import relalg.Variable
import relalg.VariableListExpression
import relalg.ProjectionOperator

class RelalgTreeSerializer extends AbstractRelalgSerializer {

  extension StringEscaper stringEscaper = new StringEscaper

  new() {
    super()
  }

  new(RelalgSerializerConfig config) {
    super(config)
  }

  override serializeBody(Operator expression) {
    '''
      \begin{forest} for tree={align=center}
      «toNode(expression)»
      ;
      \end{forest}
    '''
  }

  /**
   * toNode
   */
  def CharSequence toNode(Operator op) {
//    Optimization: an AllDifferent operator with a single edge variable is not useful at all.
//    «IF (expression instanceof AllDifferentOperator) && (expression as AllDifferentOperator).edgeVariables.length <= 1»
//      «toNode((expression as AllDifferentOperator).getInput)»
//    «ELSE»
    '''
      [
    {«op.operator»
    «IF !op.basicSchema.isEmpty»
    \\ \footnotesize
    $\color{gray} «serializeSchema(op.basicSchema)» $
    «ENDIF»
    «IF !op.extraAttributes.isEmpty»
    \\ \footnotesize
    $\color{blue} «serializeSchema(op.extraAttributes)» $
    «ENDIF»
    «IF !op.fullSchema.isEmpty»
    \\ \footnotesize
    $\color{orange} «serializeSchema(op.fullSchema)» $
    «IF op instanceof ProjectionOperator»
    \\ \footnotesize
    $\color{orange} \langle \var{«op.tupleIndices.join(", ")»} \rangle $
    «ENDIF»
    «ENDIF»
    «IF op instanceof AbstractJoinOperator && !op.fullSchema.isEmpty && config.includeCommonVariables»
    \\ \footnotesize
    $\color{orange}
    \langle \var{«(op as AbstractJoinOperator).leftMask.join(", ")»} \rangle :
    \langle \var{«(op as AbstractJoinOperator).rightMask.join(", ")»} \rangle$
    «ENDIF»
    «IF config.includeCardinality && op.cardinality !== null»
      \\ \footnotesize \# «op.cardinality.formatCardinality»
      «ENDIF»}«op?.children» ««« process children nodes
    «IF op instanceof NullaryOperator»,tier=input,for tree={blue,densely dashed}«ENDIF»
    ]'''
  }

  def serializeSchema(List<Variable> schema) {
    '''\langle \var{«schema.map[ serializeVariable.escape ].join(', ')»} \rangle'''
  }

  def dispatch serializeVariable(ElementVariable variable) {
    '''«variable.name»'''
  }

  def dispatch serializeVariable(AttributeVariable variable) {
    '''«variable.element.name».«variable.name»'''
  }

  def dispatch serializeVariable(ListVariable variable) {
    '''«variable.name»'''
  }

  def dispatch serializeVariable(ExpressionVariable variable) {
    '''«serializeExpression(variable.expression)»'''
  }

  //

  def dispatch serializeExpression(VariableListExpression expression) {
    '''«expression.variable.name»[]'''
  }

  def dispatch serializeExpression(Expression expression) {
    throw new UnsupportedOperationException('''Cannot serialize expression «expression»''')
  }


  /**
   * children
   */
  def dispatch children(NullaryOperator op) {
    ''''''
  }

  def dispatch children(UnaryOperator op) {
    '''

      «op.input?.toNode»
    '''
  }

  def dispatch children(BinaryOperator op) {
    '''

      «op.leftInput.toNode»
      «op.rightInput.toNode»
    '''
  }
  
  def dispatch children(TernaryOperator op) {
    '''

      «op.leftInput.toNode»
      «op.middleInput.toNode»
      «op.rightInput.toNode»
    '''
  }

  def formatCardinality(Cardinality cardinality) {
    return String.format("%.02f", cardinality.value)
  }
  
  /**
   * operator
   */
  override operator(Operator op) {
    '''$«op?.convertOperator.join('''$\\$''')»$'''
  }

}
