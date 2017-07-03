package ingraph.relalg.util;

import java.util.Arrays;
import relalg.EdgeVariable;
import relalg.ElementVariable;
import relalg.Expression;
import relalg.ExpressionVariable;
import relalg.Variable;
import relalg.VariableExpression;
import relalg.VertexVariable;

@SuppressWarnings("all")
public class ElementVariableExtractor {
  /**
   * Finds and extracts (returns) the enclosed ElementVariable instance.
   * If not found, null is returned.
   * 
   * For a VertexVariable and EdgeVariable, this is idempotent, i.e. returns the variable instance itself.
   * 
   * For an ExpressionVariable, see if it wraps a VertexVariable or EdgeVariable, and if so, returns that variable.
   * Note, that this wrapping can be deep like
   * (ExpressionVariable wraps VariableExpression wraps)+ ElementVariable
   */
  protected ElementVariable _extractElementVariable(final VertexVariable v) {
    return v;
  }
  
  protected ElementVariable _extractElementVariable(final EdgeVariable e) {
    return e;
  }
  
  protected ElementVariable _extractElementVariable(final ExpressionVariable ev) {
    ElementVariable _xblockexpression = null;
    {
      final Expression exp = ev.getExpression();
      ElementVariable _xifexpression = null;
      if ((exp instanceof VariableExpression)) {
        Variable _variable = ((VariableExpression)exp).getVariable();
        _xifexpression = this.extractElementVariable(_variable);
      } else {
        throw new IllegalStateException("Cannot extract elementVariable");
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  public ElementVariable extractElementVariable(final Variable e) {
    if (e instanceof EdgeVariable) {
      return _extractElementVariable((EdgeVariable)e);
    } else if (e instanceof VertexVariable) {
      return _extractElementVariable((VertexVariable)e);
    } else if (e instanceof ExpressionVariable) {
      return _extractElementVariable((ExpressionVariable)e);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(e).toString());
    }
  }
}
