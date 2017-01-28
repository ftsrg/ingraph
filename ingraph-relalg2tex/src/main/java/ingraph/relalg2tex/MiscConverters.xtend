package ingraph.relalg2tex

import java.util.List
import org.eclipse.emf.common.util.EList
import relalg.Direction
import relalg.EdgeVariable
import relalg.ExpressionVariable
import relalg.MaxHops
import relalg.SortEntry

class MiscConverters {
  
  extension StringEscaper stringEscaper = new StringEscaper
  extension ExpressionConverter expressionConverter = new ExpressionConverter
  
  def convertDirection(Direction direction) {
    switch direction {
      case BOTH: '''both'''
      case IN: '''in'''
      case OUT: '''out'''
      default: throw new UnsupportedOperationException('''Direction «direction» not supported.''')
    }
  }
  
  def convertConditionString(String s) {
    s.escape //
      .replaceAll(''' XOR ''', ''' \\lxor ''') //
      .replaceAll(''' AND ''', ''' \\land ''') //
      .replaceAll(''' OR ''', ''' \\lor ''') //
      .replaceAll(''' ''', '''~''') //
  }
  
  def CharSequence hopsToString(MaxHops hops) {
    switch hops.maxHopsType {
      case LIMITED: hops.hops.toString
      case UNLIMITED: '''\infty'''
      default: throw new UnsupportedOperationException('''MaxHopsType «hops.maxHopsType» not supported.''')
    }
  }

  def entryToTex(SortEntry entry) {
    val direction = switch (entry.direction) {
      case ASCENDING: "asc"
      case DESCENDING: "desc"
      default: throw new UnsupportedOperationException('''SortEntry «entry.direction» not supported.''')
    }
    '''\«direction» «entry.expression.convertExpression»'''
  }

  def returnableElementList(List<ExpressionVariable> elements) {
    '''«elements.map[
      convertExpression(expression) + if (dontCare || hasInferredName) "" else '''\assign \var{«name»}'''
    ].join(",~")»'''
  }

  def edgeVariableList(List<EdgeVariable> edgeVariables) {
    '''«edgeVariables.map["\\var{"+ escapedName + "}"].join(",~")»'''
  }

}