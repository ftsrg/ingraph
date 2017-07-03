package ingraph.relalg.calculators;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import ingraph.relalg.calculators.FunctionArgumentExtractor;
import ingraph.relalg.collectors.CollectionHelper;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import relalg.ArithmeticComparisonExpression;
import relalg.AttributeVariable;
import relalg.BinaryLogicalExpression;
import relalg.Case;
import relalg.ComparableExpression;
import relalg.Expression;
import relalg.FunctionExpression;
import relalg.GenericCaseExpression;
import relalg.LogicalExpression;
import relalg.SimpleCaseExpression;
import relalg.UnaryLogicalExpression;
import relalg.Variable;
import relalg.VariableExpression;

@SuppressWarnings("all")
public class ExpressionToVariables {
  @Extension
  private FunctionArgumentExtractor fae = new FunctionArgumentExtractor();
  
  @Extension
  private CollectionHelper ch = new CollectionHelper();
  
  protected List<? extends Variable> _getAttributes(final UnaryLogicalExpression expression) {
    LogicalExpression _operand = expression.getOperand();
    return this.getAttributes(_operand);
  }
  
  protected List<? extends Variable> _getAttributes(final ArithmeticComparisonExpression expression) {
    ComparableExpression _leftOperand = expression.getLeftOperand();
    List<? extends Variable> _attributes = this.getAttributes(_leftOperand);
    ComparableExpression _rightOperand = expression.getRightOperand();
    List<? extends Variable> _attributes_1 = this.getAttributes(_rightOperand);
    Iterable<Variable> _concat = Iterables.<Variable>concat(_attributes, _attributes_1);
    return Lists.<Variable>newArrayList(_concat);
  }
  
  protected List<? extends Variable> _getAttributes(final BinaryLogicalExpression expression) {
    LogicalExpression _leftOperand = expression.getLeftOperand();
    List<? extends Variable> _attributes = this.getAttributes(_leftOperand);
    LogicalExpression _rightOperand = expression.getRightOperand();
    List<? extends Variable> _attributes_1 = this.getAttributes(_rightOperand);
    Iterable<Variable> _concat = Iterables.<Variable>concat(_attributes, _attributes_1);
    return Lists.<Variable>newArrayList(_concat);
  }
  
  protected List<? extends Variable> _getAttributes(final VariableExpression expression) {
    List<Variable> _xifexpression = null;
    Variable _variable = expression.getVariable();
    if ((_variable instanceof AttributeVariable)) {
      Variable _variable_1 = expression.getVariable();
      _xifexpression = Collections.<Variable>unmodifiableList(CollectionLiterals.<Variable>newArrayList(((AttributeVariable) _variable_1)));
    } else {
      _xifexpression = Collections.<Variable>unmodifiableList(CollectionLiterals.<Variable>newArrayList());
    }
    return _xifexpression;
  }
  
  protected List<? extends Variable> _getAttributes(final FunctionExpression expression) {
    return this.fae.extractFunctionArguments(expression);
  }
  
  protected List<? extends Variable> _getAttributes(final SimpleCaseExpression expression) {
    Expression _test = expression.getTest();
    List<? extends Variable> _attributes = this.getAttributes(_test);
    EList<Case> _cases = expression.getCases();
    final Function1<Case, List<Variable>> _function = new Function1<Case, List<Variable>>() {
      public List<Variable> apply(final Case it) {
        return ExpressionToVariables.this.extractAttributes(it);
      }
    };
    List<List<Variable>> _map = ListExtensions.<Case, List<Variable>>map(_cases, _function);
    Iterable<Variable> _flatten = Iterables.<Variable>concat(_map);
    Expression _fallback = expression.getFallback();
    List<? extends Variable> _attributes_1 = this.getAttributes(_fallback);
    return this.ch.<Variable>union(_attributes, _flatten, _attributes_1);
  }
  
  protected List<? extends Variable> _getAttributes(final GenericCaseExpression expression) {
    EList<Case> _cases = expression.getCases();
    final Function1<Case, List<Variable>> _function = new Function1<Case, List<Variable>>() {
      public List<Variable> apply(final Case it) {
        return ExpressionToVariables.this.extractAttributes(it);
      }
    };
    List<List<Variable>> _map = ListExtensions.<Case, List<Variable>>map(_cases, _function);
    Iterable<Variable> _flatten = Iterables.<Variable>concat(_map);
    Expression _fallback = expression.getFallback();
    List<? extends Variable> _attributes = this.getAttributes(_fallback);
    return this.ch.<Variable>union(_flatten, _attributes);
  }
  
  protected List<? extends Variable> _getAttributes(final Expression expression) {
    return Collections.<Variable>unmodifiableList(CollectionLiterals.<Variable>newArrayList());
  }
  
  public List<Variable> extractAttributes(final Case c) {
    Expression _when = c.getWhen();
    List<? extends Variable> _attributes = this.getAttributes(_when);
    Expression _then = c.getThen();
    List<? extends Variable> _attributes_1 = this.getAttributes(_then);
    return this.ch.<Variable>union(_attributes, _attributes_1);
  }
  
  public List<? extends Variable> getAttributes(final Expression expression) {
    if (expression instanceof ArithmeticComparisonExpression) {
      return _getAttributes((ArithmeticComparisonExpression)expression);
    } else if (expression instanceof BinaryLogicalExpression) {
      return _getAttributes((BinaryLogicalExpression)expression);
    } else if (expression instanceof GenericCaseExpression) {
      return _getAttributes((GenericCaseExpression)expression);
    } else if (expression instanceof SimpleCaseExpression) {
      return _getAttributes((SimpleCaseExpression)expression);
    } else if (expression instanceof UnaryLogicalExpression) {
      return _getAttributes((UnaryLogicalExpression)expression);
    } else if (expression instanceof FunctionExpression) {
      return _getAttributes((FunctionExpression)expression);
    } else if (expression instanceof VariableExpression) {
      return _getAttributes((VariableExpression)expression);
    } else if (expression != null) {
      return _getAttributes(expression);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(expression).toString());
    }
  }
}
