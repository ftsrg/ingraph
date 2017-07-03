package ingraph.relalg.expressions;

import relalg.AttributeVariable;
import relalg.Expression;
import relalg.ExpressionVariable;
import relalg.Variable;
import relalg.VariableExpression;

@SuppressWarnings("all")
public class ExpressionUnwrapper {
  public static Variable extractBaseVariable(final AttributeVariable av) {
    if (((av.getElement() != null) && (av.getExpVar() == null))) {
      return av.getElement();
    } else {
      if (((av.getElement() == null) && (av.getExpVar() != null))) {
        ExpressionVariable _expVar = av.getExpVar();
        return ExpressionUnwrapper.extractExpressionVariable(_expVar);
      } else {
        throw new IllegalStateException(
          "AttributeVariable must have non-null value for element or expVar, but not for both!");
      }
    }
  }
  
  public static Variable extractExpressionVariable(final ExpressionVariable ev) {
    Variable _xblockexpression = null;
    {
      final Expression expression = ev.getExpression();
      Variable _xifexpression = null;
      if ((expression instanceof VariableExpression)) {
        _xifexpression = ExpressionUnwrapper.extractVariableExpression(((VariableExpression)expression));
      } else {
        _xifexpression = ev;
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  public static Variable extractVariableExpression(final VariableExpression ve) {
    Variable _xblockexpression = null;
    {
      final Variable variable = ve.getVariable();
      Variable _xifexpression = null;
      if ((variable instanceof ExpressionVariable)) {
        return ExpressionUnwrapper.extractExpressionVariable(((ExpressionVariable)variable));
      } else {
        _xifexpression = variable;
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
}
