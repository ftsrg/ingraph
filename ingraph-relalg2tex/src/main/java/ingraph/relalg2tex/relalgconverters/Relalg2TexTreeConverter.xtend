package ingraph.relalg2tex.relalgconverters

import ingraph.relalg.util.GetContainer
import ingraph.relalg2tex.converters.elementconverters.SchemaConverter
import relalg.AbstractJoinOperator
import relalg.BinaryOperator
import relalg.Cardinality
import relalg.NullaryOperator
import relalg.Operator
import relalg.ProjectionOperator
import relalg.TernaryOperator
import relalg.UnaryOperator
import ingraph.relalg2tex.config.RelalgConverterConfig

class Relalg2TexTreeConverter extends AbstractRelalg2TexConverter {

  extension SchemaConverter schemaConverter = new SchemaConverter
  extension GetContainer getContainer = new GetContainer

  new() {
    super()
  }

  new(RelalgConverterConfig config) {
    super(config)
  }

  override convertBody(Operator expression) {
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
    '''
    [
    {«op.operator»
    «IF op.getContainer.basicSchemaInferred»
    \\ \footnotesize
    $\color{gray} «convertSchema(op.basicSchema)» $
    «ENDIF»
    «IF op.getContainer.extraAttributesInferred»
    \\ \footnotesize
    $\color{violet} «convertSchema(op.extraAttributes)» $
    «ENDIF»
    «IF op.getContainer.fullSchemaInferred»
    \\ \footnotesize
    $\color{orange} «convertSchema(op.fullSchema)» $
    «IF op instanceof ProjectionOperator»
    \\ \footnotesize
    $\color{orange} \langle \var{«op.tupleIndices.join(", ")»} \rangle $
    «ENDIF»
    «ENDIF»
    «IF op instanceof AbstractJoinOperator && op.getContainer.fullSchemaInferred && config.includeCommonVariables»
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
