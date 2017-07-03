package ingraph.cypher2relalg.util;

import ingraph.logger.IngraphLogger;
import java.util.Arrays;
import org.eclipse.xtend2.lib.StringConcatenation;
import relalg.AttributeVariable;
import relalg.Expression;
import relalg.ExpressionVariable;
import relalg.NamedElement;
import relalg.RelalgModelElement;
import relalg.Variable;
import relalg.VariableExpression;

/**
 * Given an Expression, infers its name.
 * 
 * Variables and aliases return expressions has their name,
 * but for e.g. expressions like '2 + 3',
 * there should be some deterministic way to assign a name.
 */
@SuppressWarnings("all")
public class ExpressionNameInferencer {
  private static int n = 0;
  
  protected static String _inferName(final ExpressionVariable e, final IngraphLogger logger) {
    String _xifexpression = null;
    boolean _isDontCare = e.isDontCare();
    boolean _not = (!_isDontCare);
    if (_not) {
      _xifexpression = e.getName();
    } else {
      Expression _expression = e.getExpression();
      _xifexpression = ExpressionNameInferencer.inferName(_expression, logger);
    }
    return _xifexpression;
  }
  
  protected static String _inferName(final AttributeVariable e, final IngraphLogger logger) {
    Variable _baseVariable = e.getBaseVariable();
    String _name = _baseVariable.getName();
    String _plus = (_name + ".");
    String _name_1 = e.getName();
    return (_plus + _name_1);
  }
  
  protected static String _inferName(final NamedElement e, final IngraphLogger logger) {
    return e.getName();
  }
  
  protected static String _inferName(final VariableExpression e, final IngraphLogger logger) {
    Variable _variable = e.getVariable();
    return ExpressionNameInferencer.inferName(_variable, logger);
  }
  
  protected static String _inferName(final Expression e, final IngraphLogger logger) {
    String _xblockexpression = null;
    {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Don\'t know how to assign name to an expression of type ");
      Class<? extends Expression> _class = e.getClass();
      String _name = _class.getName();
      _builder.append(_name, "");
      _builder.append(", please assign an alias to the corresponding return item. For now, we return a generated unique name.");
      logger.warning(_builder.toString());
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("_iname");
      int _plusPlus = ExpressionNameInferencer.n++;
      _builder_1.append(_plusPlus, "");
      _xblockexpression = _builder_1.toString();
    }
    return _xblockexpression;
  }
  
  public static String inferName(final RelalgModelElement e, final IngraphLogger logger) {
    if (e instanceof AttributeVariable) {
      return _inferName((AttributeVariable)e, logger);
    } else if (e instanceof ExpressionVariable) {
      return _inferName((ExpressionVariable)e, logger);
    } else if (e instanceof VariableExpression) {
      return _inferName((VariableExpression)e, logger);
    } else if (e instanceof Expression) {
      return _inferName((Expression)e, logger);
    } else if (e instanceof NamedElement) {
      return _inferName((NamedElement)e, logger);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(e, logger).toString());
    }
  }
}
