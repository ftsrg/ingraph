package ingraph.relalg.calculators;

import com.google.common.collect.Iterables;
import ingraph.relalg.util.ListExpressionUtil;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import relalg.ArithmeticExpression;
import relalg.BinaryArithmeticOperationExpression;
import relalg.Expression;
import relalg.FunctionExpression;
import relalg.ListExpression;
import relalg.Literal;
import relalg.UnaryArithmeticOperationExpression;
import relalg.Variable;
import relalg.VariableExpression;

@SuppressWarnings("all")
public class FunctionArgumentExtractor {
  public List<? extends Variable> extractFunctionArguments(final FunctionExpression fe) {
    EList<Expression> _arguments = fe.getArguments();
    final Function1<Expression, List<? extends Variable>> _function = new Function1<Expression, List<? extends Variable>>() {
      public List<? extends Variable> apply(final Expression it) {
        return FunctionArgumentExtractor.this.extractVariableFromExpression(it);
      }
    };
    List<List<? extends Variable>> _map = ListExtensions.<Expression, List<? extends Variable>>map(_arguments, _function);
    Iterable<Variable> _flatten = Iterables.<Variable>concat(_map);
    return IterableExtensions.<Variable>toList(_flatten);
  }
  
  protected List<? extends Variable> _extractVariableFromExpression(final FunctionExpression a) {
    return this.extractFunctionArguments(a);
  }
  
  protected List<? extends Variable> _extractVariableFromExpression(final VariableExpression a) {
    Variable _variable = a.getVariable();
    return Collections.<Variable>unmodifiableList(CollectionLiterals.<Variable>newArrayList(_variable));
  }
  
  protected List<? extends Variable> _extractVariableFromExpression(final UnaryArithmeticOperationExpression a) {
    ArithmeticExpression _operand = a.getOperand();
    List<? extends Variable> _extractVariableFromExpression = this.extractVariableFromExpression(_operand);
    Iterable<Variable> _flatten = Iterables.<Variable>concat(Collections.<List<? extends Variable>>unmodifiableList(CollectionLiterals.<List<? extends Variable>>newArrayList(_extractVariableFromExpression)));
    return IterableExtensions.<Variable>toList(_flatten);
  }
  
  protected List<? extends Variable> _extractVariableFromExpression(final BinaryArithmeticOperationExpression a) {
    ArithmeticExpression _leftOperand = a.getLeftOperand();
    List<? extends Variable> _extractVariableFromExpression = this.extractVariableFromExpression(_leftOperand);
    ArithmeticExpression _rightOperand = a.getRightOperand();
    List<? extends Variable> _extractVariableFromExpression_1 = this.extractVariableFromExpression(_rightOperand);
    Iterable<Variable> _flatten = Iterables.<Variable>concat(Collections.<List<? extends Variable>>unmodifiableList(CollectionLiterals.<List<? extends Variable>>newArrayList(_extractVariableFromExpression, _extractVariableFromExpression_1)));
    return IterableExtensions.<Variable>toList(_flatten);
  }
  
  protected List<? extends Variable> _extractVariableFromExpression(final Literal a) {
    return Collections.<Variable>unmodifiableList(CollectionLiterals.<Variable>newArrayList());
  }
  
  protected List<? extends Variable> _extractVariableFromExpression(final ListExpression a) {
    List<Expression> _list = ListExpressionUtil.toList(a);
    final Function1<Expression, List<? extends Variable>> _function = new Function1<Expression, List<? extends Variable>>() {
      public List<? extends Variable> apply(final Expression it) {
        return FunctionArgumentExtractor.this.extractVariableFromExpression(it);
      }
    };
    List<List<? extends Variable>> _map = ListExtensions.<Expression, List<? extends Variable>>map(_list, _function);
    Iterable<Variable> _flatten = Iterables.<Variable>concat(_map);
    return IterableExtensions.<Variable>toList(_flatten);
  }
  
  public List<? extends Variable> extractVariableFromExpression(final Expression a) {
    if (a instanceof BinaryArithmeticOperationExpression) {
      return _extractVariableFromExpression((BinaryArithmeticOperationExpression)a);
    } else if (a instanceof UnaryArithmeticOperationExpression) {
      return _extractVariableFromExpression((UnaryArithmeticOperationExpression)a);
    } else if (a instanceof Literal) {
      return _extractVariableFromExpression((Literal)a);
    } else if (a instanceof FunctionExpression) {
      return _extractVariableFromExpression((FunctionExpression)a);
    } else if (a instanceof ListExpression) {
      return _extractVariableFromExpression((ListExpression)a);
    } else if (a instanceof VariableExpression) {
      return _extractVariableFromExpression((VariableExpression)a);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(a).toString());
    }
  }
}
