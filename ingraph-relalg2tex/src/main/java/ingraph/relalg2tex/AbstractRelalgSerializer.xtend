package ingraph.relalg2tex

import java.io.File
import java.nio.charset.Charset
import java.util.List
import org.apache.commons.io.FileUtils
import org.eclipse.emf.common.util.EList
import relalg.AbstractJoinOperator
import relalg.AllDifferentOperator
import relalg.AntiJoinOperator
import relalg.ArithmeticComparisonExpression
import relalg.ArithmeticComparisonOperatorType
import relalg.ArithmeticOperationExpression
import relalg.AttributeVariable
import relalg.BinaryArithmeticOperatorType
import relalg.BinaryLogicalExpression
import relalg.BinaryLogicalOperatorType
import relalg.BinaryOperator
import relalg.BooleanLiteral
import relalg.Direction
import relalg.DuplicateEliminationOperator
import relalg.EdgeVariable
import relalg.ElementVariable
import relalg.EmptyListExpression
import relalg.ExpandOperator
import relalg.ExpressionVariable
import relalg.FunctionExpression
import relalg.GetEdgesOperator
import relalg.GetVerticesOperator
import relalg.GroupingOperator
import relalg.IntegerLiteral
import relalg.JoinOperator
import relalg.LabelSetStatus
import relalg.LeftOuterJoinOperator
import relalg.ListExpression
import relalg.MaxHops
import relalg.NamedElement
import relalg.Operator
import relalg.ProductionOperator
import relalg.ProjectionOperator
import relalg.RelalgContainer
import relalg.SelectionOperator
import relalg.SortAndTopOperator
import relalg.SortEntry
import relalg.SortOperator
import relalg.StringLiteral
import relalg.TopOperator
import relalg.UnaryArithmeticOperatorType
import relalg.UnaryGraphObjectLogicalExpression
import relalg.UnaryGraphObjectLogicalOperatorType
import relalg.UnaryLogicalExpression
import relalg.UnaryLogicalOperatorType
import relalg.UnionOperator
import relalg.UnwindOperator
import relalg.VariableExpression
import relalg.VertexVariable
import relalg.PathOperator

abstract class AbstractRelalgSerializer {

  protected val RelalgSerializerConfig config

  protected new() {
    this.config = RelalgSerializerConfig.builder.build
  }

  protected new(RelalgSerializerConfig config) {
    this.config = config
  }


  def serialize(RelalgContainer container, String filename) {
    val tex = serialize(container)

    val file = new File("../visualization/" + filename + ".tex")
    FileUtils.writeStringToFile(file, tex.toString, Charset.forName("UTF-8"))

    tex
  }

  def serialize(RelalgContainer container) {
    //FIXME: make this work again, jmarton, 20161126
    //val schemaInferencer = new SchemaInferencer(false)
    //schemaInferencer.addSchemaInformation(container)

    val s = convertAlgebraExpression(container.rootExpression)

    if (config.consoleOutput) {
      println(s)
    }

    return s
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
        % !TeX program = pdflatex
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
   * nullaryOperator
   */
  def dispatch operatorToTex(GetEdgesOperator op) {
    #[
      '''\getedges''' + '''«op.sourceVertexVariable.toTexParameterWithLabels»''' +
      '''«op.targetVertexVariable.toTexParameterWithLabels»''' + '''«op.edgeVariable.toTexParameterWithLabels»'''
    ]
  }

  def dispatch operatorToTex(GetVerticesOperator op) {
    #[
      '''\getvertices«op.vertexVariable.toTexParameterWithLabels»'''
    ]
  }

  /**
   * unaryOperator
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
   * binaryOperator
   */
  def dispatch binaryOperator(AbstractJoinOperator operator) {
    '''«operator.joinOperator»''' +
    '''«IF config.includeCommonVariables»\{«operator.commonVariables.map['''\var{«escapedName»}'''].join(", ")»\}«ENDIF»'''
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

  def dispatch joinOperator(LeftOuterJoinOperator operator) {
    '''leftouterjoin'''
  }

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
   * escape
   */
  def escape(CharSequence s) {
    s.toString
    .replace('\b', '''\b''')
    .replace('\f', '''\f''')
    .replace('\n', '''\n''')
    .replace('\r', '''\r''')
    .replace('\t', '''\t''')
    .replace('''{''', '''\string{''')
    .replace('''}''', '''\string}''')
    .replaceAll('''\\(?!string[{}])''', '''\\backslash{}''')
    .replace('''_''', '''\_''')
    .replace(''' ''', '''\ ''')
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
    '''\«direction» \var{«entry.variable.escapedName»}'''
  }

  /**
   * list
   */
  def returnableElementList(List<ExpressionVariable> elements) {
    '''«elements.map[
      convertExpression(expression) + if (dontCare || hasInferredName) "" else '''\assign \var{«name»}'''
    ].join(",~")»'''
  }

  def edgeVariableList(EList<EdgeVariable> edgeVariables) {
    '''«edgeVariables.map["\\var{"+ escapedName + "}"].join(",~")»'''
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

  /**
   * escapedName
   */
  def escapedName(NamedElement element) {
    '''«element?.name?.escape»'''
  }

  /**
   * conversion for operators
   */
  def convert(ArithmeticComparisonOperatorType op) {
    switch (op) {
      case EQUAL_TO: '''='''
      case GREATER_THAN: '''>'''
      case GREATER_THAN_OR_EQUAL: '''\geq'''
      case LESS_THAN: '''<'''
      case LESS_THAN_OR_EQUAL: '''\leq'''
      case NOT_EQUAL_TO: '''\neq'''
      default: throw new UnsupportedOperationException('''SortEntry «op» not supported.''')
    }
  }

  def convert(BinaryLogicalOperatorType op) {
    switch (op) {
      case AND: '''\land'''
      case OR: '''\lor'''
      case XOR: '''\lxor'''
      default: throw new UnsupportedOperationException('''BinaryLogicalOperator «op» not supported.''')
    }
  }

  def convert(UnaryLogicalOperatorType op) {
    switch (op) {
      case NOT: '''\neg'''
      case IS_NOT_NULL: RelNullHandler.isNotNull
      case IS_NULL: RelNullHandler.isNull
      default: throw new UnsupportedOperationException('''UnaryLogicalOperator «op» not supported.''')
    }
  }

  def convert(UnaryGraphObjectLogicalOperatorType op) {
    switch (op) {
      case IS_NOT_NULL: RelNullHandler.isNotNull
      case IS_NULL: RelNullHandler.isNull
      default: throw new UnsupportedOperationException('''UnaryNodeLogicalOperator «op» not supported.''')
    }
  }

  def convert(BinaryArithmeticOperatorType op) {
    switch (op) {
      case DIVISION: '''/'''
      case MINUS: '''-'''
      case MOD: '''\mod'''
      case MULTIPLICATION: '''\cdot'''
      case PLUS: '''+'''
      case POWER: '''^'''
      default: throw new UnsupportedOperationException('''BinaryArithmeticOperator «op» not supported.''')
    }
  }

  def convert(UnaryArithmeticOperatorType op) {
    switch (op) {
      case MINUS: '''-'''
      case PLUS: ''''''
      default: throw new UnsupportedOperationException('''UnaryArithmeticOperator «op» not supported.''')
    }
  }

  def dispatch convertExpression(Void x) {
    '''Void:null'''
  }

  /**
   * convertComparable
   */
  def dispatch convertExpression(IntegerLiteral integerLiteral) {
    '''\literal{«integerLiteral.value.toString»}'''
  }

  def dispatch convertExpression(StringLiteral stringLiteral) {
    '''\literal{'«stringLiteral.value.toString.escape»'}'''
  }

  def dispatch convertExpression(ElementVariable elementVariable) {
    '''\var{«elementVariable.escapedName»}'''
  }

  def dispatch convertExpression(AttributeVariable attributeVariable) {
    '''\var{«attributeVariable.element.name».«attributeVariable.escapedName»}'''
  }

  /**
   * convertExpression
   */
  def dispatch convertExpression(VariableExpression ve) {
    convertExpression(ve.variable)
  }

  def dispatch convertExpression(FunctionExpression fe) {
    '''«fe.functor.toString.escape» \left( «fe.arguments.map[convertExpression].join(", ")» \right)'''
  }

  def dispatch convertExpression(EmptyListExpression fe) {
    '''\left[ \right]'''
  }

  def dispatch convertExpression(ListExpression fe) {
    var retVal = '''\left['''

    for(var i = fe; !(i instanceof EmptyListExpression); i = i.tail) {
      retVal += ''' «i.head.convertExpression»«IF !(i.tail instanceof EmptyListExpression)»,«ENDIF»'''
    }

    retVal + ''' \right]'''
  }

  def dispatch String convertExpression(BinaryLogicalExpression exp) {
    '''«exp.leftOperand.convertExpression» «exp.operator.convert» «exp.rightOperand.convertExpression»'''
  }

  def dispatch String convertExpression(UnaryLogicalExpression exp) {
    '''«exp.operator.convert» \left( «exp.operand.convertExpression» \right)'''
  }

  def dispatch String convertExpression(UnaryGraphObjectLogicalExpression exp) {
    '''«exp.operand.convertExpression» «exp.getOperator.convert»'''
  }

  def dispatch String convertExpression(ArithmeticComparisonExpression exp) {
    '''«exp.leftOperand.convertExpression» «exp.operator.convert» «exp.rightOperand.convertExpression»'''
  }

  def dispatch String convertExpression(ArithmeticOperationExpression exp) {
    '''«exp.leftOperand.convertExpression» «exp.operator.convert» «exp.rightOperand.convertExpression»'''
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
