package ingraph.relalg2tex.converters.operatorconverters;

import ingraph.relalg2tex.converters.elementconverters.ExpressionConverter;
import ingraph.relalg2tex.converters.variableconverters.VariableExpressionConverter;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import relalg.Expression;
import relalg.ExpressionVariable;
import relalg.GroupingOperator;
import relalg.ProjectionOperator;
import relalg.Variable;

@SuppressWarnings("all")
public class GroupingProjectionOperatorConverter {
  @Extension
  private ExpressionConverter expressionConverter = new ExpressionConverter();
  
  @Extension
  private VariableExpressionConverter variableExpressionConverter = new VariableExpressionConverter();
  
  public CharSequence convertReturnableElementList(final List<? extends Variable> elements) {
    StringConcatenation _builder = new StringConcatenation();
    final Function1<Variable, CharSequence> _function = (Variable it) -> {
      return this.variableExpressionConverter.convertVariable(it);
    };
    List<CharSequence> _map = ListExtensions.map(elements, _function);
    String _join = IterableExtensions.join(_map, ", ");
    _builder.append(_join, "");
    return _builder;
  }
  
  public CharSequence convertReturnableElementListNegative(final List<ExpressionVariable> elements) {
    StringConcatenation _builder = new StringConcatenation();
    final Function1<ExpressionVariable, String> _function = (ExpressionVariable it) -> {
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("\\ominus ");
      CharSequence _convertVariable = this.variableExpressionConverter.convertVariable(it);
      _builder_1.append(_convertVariable, "");
      return _builder_1.toString();
    };
    List<String> _map = ListExtensions.<ExpressionVariable, String>map(elements, _function);
    String _join = IterableExtensions.join(_map, ", ");
    _builder.append(_join, "");
    return _builder;
  }
  
  public CharSequence groupingOperator(final GroupingOperator op) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\grouping{");
    EList<ExpressionVariable> _elements = op.getElements();
    CharSequence _convertReturnableElementList = this.convertReturnableElementList(_elements);
    _builder.append(_convertReturnableElementList, "");
    _builder.append("}{");
    EList<Expression> _aggregationCriteria = op.getAggregationCriteria();
    final Function1<Expression, CharSequence> _function = (Expression it) -> {
      return this.expressionConverter.convertExpression(it);
    };
    List<CharSequence> _map = ListExtensions.<Expression, CharSequence>map(_aggregationCriteria, _function);
    String _join = IterableExtensions.join(_map, ", ");
    _builder.append(_join, "");
    _builder.append("}");
    return _builder;
  }
  
  public CharSequence projectionOperator(final ProjectionOperator op) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\projection{");
    EList<ExpressionVariable> _elements = op.getElements();
    CharSequence _convertReturnableElementList = this.convertReturnableElementList(_elements);
    _builder.append(_convertReturnableElementList, "");
    _builder.append("}{}");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
}
