package ingraph.relalg2tex.converters.variableconverters;

import ingraph.relalg2tex.converters.elementconverters.ExpressionConverter;
import ingraph.relalg2tex.converters.variableconverters.AbstractVariableConverter;
import java.util.Arrays;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;
import relalg.AttributeVariable;
import relalg.EdgeListVariable;
import relalg.ElementVariable;
import relalg.Expression;
import relalg.ExpressionVariable;
import relalg.ListVariable;
import relalg.Variable;

@SuppressWarnings("all")
public class VariableExpressionConverter extends AbstractVariableConverter {
  @Extension
  private ExpressionConverter expressionConverter = new ExpressionConverter();
  
  protected CharSequence _convertVariable(final ExpressionVariable ev) {
    StringConcatenation _builder = new StringConcatenation();
    Expression _expression = ev.getExpression();
    CharSequence _convertExpression = this.expressionConverter.convertExpression(_expression);
    _builder.append(_convertExpression, "");
    {
      boolean _not = (!(ev.isDontCare() || ev.isHasInferredName()));
      if (_not) {
        _builder.append("\\assign \\var{");
        Variable _unwrap = this.unwrap(ev);
        CharSequence _escapedName = this.stringEscaper.escapedName(_unwrap);
        _builder.append(_escapedName, "");
        _builder.append("}");
      }
    }
    return _builder;
  }
  
  public CharSequence convertVariable(final Variable ev) {
    if (ev instanceof EdgeListVariable) {
      return _convertVariable((EdgeListVariable)ev);
    } else if (ev instanceof AttributeVariable) {
      return _convertVariable((AttributeVariable)ev);
    } else if (ev instanceof ElementVariable) {
      return _convertVariable((ElementVariable)ev);
    } else if (ev instanceof ExpressionVariable) {
      return _convertVariable((ExpressionVariable)ev);
    } else if (ev instanceof ListVariable) {
      return _convertVariable((ListVariable)ev);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(ev).toString());
    }
  }
}
