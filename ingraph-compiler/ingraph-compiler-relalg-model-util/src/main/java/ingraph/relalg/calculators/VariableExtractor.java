package ingraph.relalg.calculators;

import com.google.common.collect.Iterables;
import ingraph.relalg.calculators.ExpressionToVariables;
import ingraph.relalg.calculators.FunctionArgumentExtractor;
import ingraph.relalg.collectors.CollectionHelper;
import ingraph.relalg.expressions.ExpressionUnwrapper;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import relalg.AttributeVariable;
import relalg.CaseExpression;
import relalg.Expression;
import relalg.ExpressionVariable;
import relalg.FunctionExpression;
import relalg.GroupingOperator;
import relalg.LogicalExpression;
import relalg.Operator;
import relalg.ProductionOperator;
import relalg.ProjectionOperator;
import relalg.SelectionOperator;
import relalg.SortEntry;
import relalg.SortOperator;
import relalg.UnaryOperator;
import relalg.UnwindOperator;
import relalg.Variable;
import relalg.VariableExpression;
import relalg.function.Function;

@SuppressWarnings("all")
public class VariableExtractor {
  @Extension
  private ExpressionToVariables expressionToVariables = new ExpressionToVariables();
  
  @Extension
  private CollectionHelper collectionHelper = new CollectionHelper();
  
  @Extension
  private FunctionArgumentExtractor functionArgumentExtractor = new FunctionArgumentExtractor();
  
  /**
   * Extract extra variables required by unary operators.
   */
  protected List<? extends Variable> _extractUnaryOperatorExtraVariables(final ProjectionOperator op) {
    return this.getExtraVariablesForProjectionOperator(op);
  }
  
  protected List<? extends Variable> _extractUnaryOperatorExtraVariables(final GroupingOperator op) {
    List<? extends Variable> _extraVariablesForProjectionOperator = this.getExtraVariablesForProjectionOperator(op);
    List<? extends Variable> _extraVariablesForGroupingOperator = this.getExtraVariablesForGroupingOperator(op);
    return this.collectionHelper.<Variable>uniqueUnion(_extraVariablesForProjectionOperator, _extraVariablesForGroupingOperator);
  }
  
  protected List<? extends Variable> _extractUnaryOperatorExtraVariables(final SelectionOperator op) {
    LogicalExpression _condition = op.getCondition();
    return this.expressionToVariables.getAttributes(_condition);
  }
  
  protected List<? extends Variable> _extractUnaryOperatorExtraVariables(final ProductionOperator op) {
    EList<ExpressionVariable> _elements = op.getElements();
    Iterable<ExpressionVariable> _filter = Iterables.<ExpressionVariable>filter(_elements, ExpressionVariable.class);
    final Function1<ExpressionVariable, Expression> _function = new Function1<ExpressionVariable, Expression>() {
      public Expression apply(final ExpressionVariable it) {
        return it.getExpression();
      }
    };
    Iterable<Expression> _map = IterableExtensions.<ExpressionVariable, Expression>map(_filter, _function);
    Iterable<VariableExpression> _filter_1 = Iterables.<VariableExpression>filter(_map, VariableExpression.class);
    final Function1<VariableExpression, Variable> _function_1 = new Function1<VariableExpression, Variable>() {
      public Variable apply(final VariableExpression it) {
        return it.getVariable();
      }
    };
    Iterable<Variable> _map_1 = IterableExtensions.<VariableExpression, Variable>map(_filter_1, _function_1);
    Iterable<AttributeVariable> _filter_2 = Iterables.<AttributeVariable>filter(_map_1, AttributeVariable.class);
    return IterableExtensions.<AttributeVariable>toList(_filter_2);
  }
  
  protected List<? extends Variable> _extractUnaryOperatorExtraVariables(final SortOperator op) {
    EList<SortEntry> _entries = op.getEntries();
    final Function1<SortEntry, Expression> _function = new Function1<SortEntry, Expression>() {
      public Expression apply(final SortEntry it) {
        return it.getExpression();
      }
    };
    List<Expression> _map = ListExtensions.<SortEntry, Expression>map(_entries, _function);
    Iterable<VariableExpression> _filter = Iterables.<VariableExpression>filter(_map, VariableExpression.class);
    final Function1<VariableExpression, Variable> _function_1 = new Function1<VariableExpression, Variable>() {
      public Variable apply(final VariableExpression it) {
        return it.getVariable();
      }
    };
    Iterable<Variable> _map_1 = IterableExtensions.<VariableExpression, Variable>map(_filter, _function_1);
    return IterableExtensions.<Variable>toList(_map_1);
  }
  
  protected List<? extends Variable> _extractUnaryOperatorExtraVariables(final UnwindOperator op) {
    ExpressionVariable _element = op.getElement();
    return Collections.<Variable>unmodifiableList(CollectionLiterals.<Variable>newArrayList(_element));
  }
  
  protected List<AttributeVariable> _extractUnaryOperatorExtraVariables(final UnaryOperator op) {
    return Collections.<AttributeVariable>unmodifiableList(CollectionLiterals.<AttributeVariable>newArrayList());
  }
  
  /**
   * auxiliary functions
   */
  public List<? extends Variable> getExtraVariablesForProjectionOperator(final ProjectionOperator op) {
    List<Variable> _xblockexpression = null;
    {
      EList<ExpressionVariable> _elements = op.getElements();
      final Function1<ExpressionVariable, Expression> _function = new Function1<ExpressionVariable, Expression>() {
        public Expression apply(final ExpressionVariable it) {
          return it.getExpression();
        }
      };
      List<Expression> _map = ListExtensions.<ExpressionVariable, Expression>map(_elements, _function);
      Iterable<FunctionExpression> _filter = Iterables.<FunctionExpression>filter(_map, FunctionExpression.class);
      final Function1<FunctionExpression, Boolean> _function_1 = new Function1<FunctionExpression, Boolean>() {
        public Boolean apply(final FunctionExpression it) {
          Function _functor = it.getFunctor();
          boolean _isAggregation = _functor.isAggregation();
          return Boolean.valueOf((!_isAggregation));
        }
      };
      final Iterable<FunctionExpression> functionExpressions = IterableExtensions.<FunctionExpression>filter(_filter, _function_1);
      final Function1<FunctionExpression, List<? extends Variable>> _function_2 = new Function1<FunctionExpression, List<? extends Variable>>() {
        public List<? extends Variable> apply(final FunctionExpression it) {
          return VariableExtractor.this.functionArgumentExtractor.extractFunctionArguments(it);
        }
      };
      Iterable<List<? extends Variable>> _map_1 = IterableExtensions.<FunctionExpression, List<? extends Variable>>map(functionExpressions, _function_2);
      Iterable<Variable> _flatten = Iterables.<Variable>concat(_map_1);
      final List<Variable> arguments = IterableExtensions.<Variable>toList(_flatten);
      EList<ExpressionVariable> _elements_1 = op.getElements();
      Iterable<ExpressionVariable> _filter_1 = Iterables.<ExpressionVariable>filter(_elements_1, ExpressionVariable.class);
      final Function1<ExpressionVariable, Expression> _function_3 = new Function1<ExpressionVariable, Expression>() {
        public Expression apply(final ExpressionVariable it) {
          return it.getExpression();
        }
      };
      final Iterable<Expression> expressions = IterableExtensions.<ExpressionVariable, Expression>map(_filter_1, _function_3);
      Iterable<VariableExpression> _filter_2 = Iterables.<VariableExpression>filter(expressions, VariableExpression.class);
      final Function1<VariableExpression, Variable> _function_4 = new Function1<VariableExpression, Variable>() {
        public Variable apply(final VariableExpression it) {
          return it.getVariable();
        }
      };
      Iterable<Variable> _map_2 = IterableExtensions.<VariableExpression, Variable>map(_filter_2, _function_4);
      Iterable<AttributeVariable> _filter_3 = Iterables.<AttributeVariable>filter(_map_2, AttributeVariable.class);
      final List<AttributeVariable> extraVariables = IterableExtensions.<AttributeVariable>toList(_filter_3);
      Iterable<CaseExpression> _filter_4 = Iterables.<CaseExpression>filter(expressions, CaseExpression.class);
      final Function1<CaseExpression, List<? extends Variable>> _function_5 = new Function1<CaseExpression, List<? extends Variable>>() {
        public List<? extends Variable> apply(final CaseExpression it) {
          return VariableExtractor.this.expressionToVariables.getAttributes(it);
        }
      };
      Iterable<List<? extends Variable>> _map_3 = IterableExtensions.<CaseExpression, List<? extends Variable>>map(_filter_4, _function_5);
      Iterable<Variable> _flatten_1 = Iterables.<Variable>concat(_map_3);
      final List<Variable> caseAttributes = IterableExtensions.<Variable>toList(_flatten_1);
      _xblockexpression = this.collectionHelper.<Variable>uniqueUnion(extraVariables, arguments, caseAttributes);
    }
    return _xblockexpression;
  }
  
  public List<? extends Variable> getExtraVariablesForGroupingOperator(final GroupingOperator op) {
    List<Variable> _xblockexpression = null;
    {
      EList<Variable> _externalSchema = op.getExternalSchema();
      final Function1<Variable, String> _function = new Function1<Variable, String>() {
        public String apply(final Variable it) {
          return it.fullName();
        }
      };
      final List<String> externalSchemaNames = ListExtensions.<Variable, String>map(_externalSchema, _function);
      EList<Expression> _aggregationCriteria = op.getAggregationCriteria();
      Iterable<ExpressionVariable> _filter = Iterables.<ExpressionVariable>filter(_aggregationCriteria, ExpressionVariable.class);
      final Function1<ExpressionVariable, Variable> _function_1 = new Function1<ExpressionVariable, Variable>() {
        public Variable apply(final ExpressionVariable it) {
          return ExpressionUnwrapper.extractExpressionVariable(it);
        }
      };
      Iterable<Variable> _map = IterableExtensions.<ExpressionVariable, Variable>map(_filter, _function_1);
      final Function1<Variable, Boolean> _function_2 = new Function1<Variable, Boolean>() {
        public Boolean apply(final Variable it) {
          String _fullName = it.fullName();
          boolean _contains = externalSchemaNames.contains(_fullName);
          return Boolean.valueOf((!_contains));
        }
      };
      Iterable<Variable> _filter_1 = IterableExtensions.<Variable>filter(_map, _function_2);
      _xblockexpression = IterableExtensions.<Variable>toList(_filter_1);
    }
    return _xblockexpression;
  }
  
  /**
   * calculatedVariables
   */
  protected List<? extends Variable> _getCalculatedVariables(final ProjectionOperator op) {
    return Collections.<Variable>unmodifiableList(CollectionLiterals.<Variable>newArrayList());
  }
  
  protected List<? extends Variable> _getCalculatedVariables(final Operator op) {
    return Collections.<Variable>unmodifiableList(CollectionLiterals.<Variable>newArrayList());
  }
  
  public List<? extends Variable> extractUnaryOperatorExtraVariables(final UnaryOperator op) {
    if (op instanceof GroupingOperator) {
      return _extractUnaryOperatorExtraVariables((GroupingOperator)op);
    } else if (op instanceof ProductionOperator) {
      return _extractUnaryOperatorExtraVariables((ProductionOperator)op);
    } else if (op instanceof ProjectionOperator) {
      return _extractUnaryOperatorExtraVariables((ProjectionOperator)op);
    } else if (op instanceof SelectionOperator) {
      return _extractUnaryOperatorExtraVariables((SelectionOperator)op);
    } else if (op instanceof SortOperator) {
      return _extractUnaryOperatorExtraVariables((SortOperator)op);
    } else if (op instanceof UnwindOperator) {
      return _extractUnaryOperatorExtraVariables((UnwindOperator)op);
    } else if (op != null) {
      return _extractUnaryOperatorExtraVariables(op);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(op).toString());
    }
  }
  
  public List<? extends Variable> getCalculatedVariables(final Operator op) {
    if (op instanceof ProjectionOperator) {
      return _getCalculatedVariables((ProjectionOperator)op);
    } else if (op != null) {
      return _getCalculatedVariables(op);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(op).toString());
    }
  }
}
