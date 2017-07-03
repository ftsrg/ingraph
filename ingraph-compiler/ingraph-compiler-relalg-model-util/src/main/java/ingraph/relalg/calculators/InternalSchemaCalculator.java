package ingraph.relalg.calculators;

import ingraph.relalg.calculators.JoinSchemaCalculator;
import ingraph.relalg.calculators.MaskCalculator;
import ingraph.relalg.collectors.CollectionHelper;
import ingraph.relalg.util.visitors.PostOrderTreeVisitor;
import java.util.Arrays;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import relalg.AbstractJoinOperator;
import relalg.ExpandOperator;
import relalg.Expression;
import relalg.ExpressionVariable;
import relalg.GroupingOperator;
import relalg.NullaryOperator;
import relalg.Operator;
import relalg.ProductionOperator;
import relalg.ProjectionOperator;
import relalg.RelalgContainer;
import relalg.RelalgFactory;
import relalg.UnaryOperator;
import relalg.UnionOperator;
import relalg.Variable;
import relalg.VariableExpression;

/**
 * Calculates the internal schema that consists of the external schema plus the extra variables.
 */
@SuppressWarnings("all")
public class InternalSchemaCalculator {
  @Extension
  private PostOrderTreeVisitor treeVisitor = new PostOrderTreeVisitor();
  
  @Extension
  private JoinSchemaCalculator joinSchemaCalculator = new JoinSchemaCalculator();
  
  @Extension
  private CollectionHelper listUnionCalculator = new CollectionHelper();
  
  @Extension
  private MaskCalculator maskCalculator = new MaskCalculator();
  
  @Extension
  private RelalgFactory factory = RelalgFactory.eINSTANCE;
  
  public RelalgContainer calculateInternalSchema(final RelalgContainer container) {
    RelalgContainer _xblockexpression = null;
    {
      boolean _isExtraVariablesInferred = container.isExtraVariablesInferred();
      boolean _not = (!_isExtraVariablesInferred);
      if (_not) {
        throw new IllegalStateException("ExtraVariablesCalculator must be executed before InternalSchemaCalculator");
      } else {
        boolean _isInternalSchemaInferred = container.isInternalSchemaInferred();
        if (_isInternalSchemaInferred) {
          throw new IllegalStateException("InternalSchemaCalculator on relalg container was already executed");
        } else {
          container.setInternalSchemaInferred(true);
        }
      }
      Operator _rootExpression = container.getRootExpression();
      final Procedure1<Operator> _function = new Procedure1<Operator>() {
        public void apply(final Operator it) {
          InternalSchemaCalculator.this.fillInternalSchema(it);
        }
      };
      this.treeVisitor.traverse(_rootExpression, _function);
      Operator _rootExpression_1 = container.getRootExpression();
      final Procedure1<Operator> _function_1 = new Procedure1<Operator>() {
        public void apply(final Operator it) {
          InternalSchemaCalculator.this.maskCalculator.calculateTuples(it);
        }
      };
      this.treeVisitor.traverse(_rootExpression_1, _function_1);
      _xblockexpression = container;
    }
    return _xblockexpression;
  }
  
  /**
   * fillInternalSchema
   */
  private void _fillInternalSchema(final NullaryOperator op) {
    EList<Variable> _externalSchema = op.getExternalSchema();
    EList<Variable> _extraVariables = op.getExtraVariables();
    final List<Variable> internalSchema = this.listUnionCalculator.<Variable>uniqueUnion(_externalSchema, _extraVariables);
    this.defineInternalSchema(op, internalSchema);
  }
  
  private void _fillInternalSchema(final UnaryOperator op) {
    Operator _input = op.getInput();
    final EList<Variable> internalSchema = _input.getInternalSchema();
    this.defineInternalSchema(op, internalSchema);
  }
  
  private void _fillInternalSchema(final UnionOperator op) {
    Operator _leftInput = op.getLeftInput();
    final EList<Variable> internalSchema = _leftInput.getInternalSchema();
    this.defineInternalSchema(op, internalSchema);
  }
  
  private void _fillInternalSchema(final AbstractJoinOperator op) {
    Operator _leftInput = op.getLeftInput();
    EList<Variable> _internalSchema = _leftInput.getInternalSchema();
    Operator _rightInput = op.getRightInput();
    EList<Variable> _internalSchema_1 = _rightInput.getInternalSchema();
    final List<Variable> internalSchema = this.joinSchemaCalculator.calculateJoinSchema(op, _internalSchema, _internalSchema_1);
    this.defineInternalSchema(op, internalSchema);
  }
  
  /**
   * defineInternalSchema
   */
  private void _defineInternalSchema(final ProductionOperator op, final List<? extends Variable> internalSchema) {
    final Function1<Variable, String> _function = new Function1<Variable, String>() {
      public String apply(final Variable it) {
        return it.getName();
      }
    };
    final List<String> internalSchemaNames = ListExtensions.map(internalSchema, _function);
    EList<ExpressionVariable> _elements = op.getElements();
    EList<Variable> _externalSchema = op.getExternalSchema();
    final Function1<Variable, ExpressionVariable> _function_1 = new Function1<Variable, ExpressionVariable>() {
      public ExpressionVariable apply(final Variable it) {
        ExpressionVariable _xblockexpression = null;
        {
          final Variable element = it;
          ExpressionVariable _createExpressionVariable = InternalSchemaCalculator.this.factory.createExpressionVariable();
          final Procedure1<ExpressionVariable> _function = new Procedure1<ExpressionVariable>() {
            public void apply(final ExpressionVariable it) {
              VariableExpression _createVariableExpression = InternalSchemaCalculator.this.factory.createVariableExpression();
              final Procedure1<VariableExpression> _function = new Procedure1<VariableExpression>() {
                public void apply(final VariableExpression it) {
                  it.setVariable(element);
                  RelalgContainer _namedElementContainer = element.getNamedElementContainer();
                  it.setExpressionContainer(_namedElementContainer);
                }
              };
              VariableExpression _doubleArrow = ObjectExtensions.<VariableExpression>operator_doubleArrow(_createVariableExpression, _function);
              it.setExpression(_doubleArrow);
              it.setHasInferredName(true);
              RelalgContainer _namedElementContainer = element.getNamedElementContainer();
              it.setNamedElementContainer(_namedElementContainer);
            }
          };
          _xblockexpression = ObjectExtensions.<ExpressionVariable>operator_doubleArrow(_createExpressionVariable, _function);
        }
        return _xblockexpression;
      }
    };
    List<ExpressionVariable> _map = ListExtensions.<Variable, ExpressionVariable>map(_externalSchema, _function_1);
    _elements.addAll(_map);
    EList<Variable> _internalSchema = op.getInternalSchema();
    EList<Variable> _externalSchema_1 = op.getExternalSchema();
    _internalSchema.addAll(_externalSchema_1);
    EList<Integer> _tupleIndices = op.getTupleIndices();
    EList<Variable> _internalSchema_1 = op.getInternalSchema();
    final Function1<Variable, Integer> _function_2 = new Function1<Variable, Integer>() {
      public Integer apply(final Variable variable) {
        String _name = variable.getName();
        return Integer.valueOf(internalSchemaNames.indexOf(_name));
      }
    };
    List<Integer> _map_1 = ListExtensions.<Variable, Integer>map(_internalSchema_1, _function_2);
    _tupleIndices.addAll(_map_1);
  }
  
  private void _defineInternalSchema(final GroupingOperator op, final List<? extends Variable> internalSchema) {
    this.defineInternalSchemaForProjectionOperator(op, internalSchema);
    this.defineInternalSchemaForGroupingOperator(op, internalSchema);
  }
  
  private void _defineInternalSchema(final ProjectionOperator op, final List<? extends Variable> internalSchema) {
    this.defineInternalSchemaForProjectionOperator(op, internalSchema);
  }
  
  private void _defineInternalSchema(final ExpandOperator op, final List<? extends Variable> internalSchema) {
    EList<Variable> _internalSchema = op.getInternalSchema();
    EList<Variable> _externalSchema = op.getExternalSchema();
    EList<Variable> _extraVariables = op.getExtraVariables();
    List<Variable> _uniqueUnion = this.listUnionCalculator.<Variable>uniqueUnion(_externalSchema, _extraVariables);
    _internalSchema.addAll(_uniqueUnion);
  }
  
  private void _defineInternalSchema(final Operator op, final List<? extends Variable> internalSchema) {
    EList<Variable> _internalSchema = op.getInternalSchema();
    _internalSchema.addAll(internalSchema);
  }
  
  /**
   * Miscellaneous
   */
  private void defineInternalSchemaForProjectionOperator(final ProjectionOperator op, final List<? extends Variable> internalSchema) {
    final Function1<Variable, String> _function = new Function1<Variable, String>() {
      public String apply(final Variable it) {
        return it.getName();
      }
    };
    final List<String> internalSchemaNames = ListExtensions.map(internalSchema, _function);
    EList<Variable> _internalSchema = op.getInternalSchema();
    EList<Variable> _externalSchema = op.getExternalSchema();
    EList<Variable> _extraVariables = op.getExtraVariables();
    List<Variable> _uniqueUnion = this.listUnionCalculator.<Variable>uniqueUnion(_externalSchema, _extraVariables);
    _internalSchema.addAll(_uniqueUnion);
    EList<ExpressionVariable> _internalElements = op.getInternalElements();
    EList<ExpressionVariable> _elements = op.getElements();
    _internalElements.addAll(_elements);
    EList<ExpressionVariable> _internalElements_1 = op.getInternalElements();
    EList<Variable> _extraVariables_1 = op.getExtraVariables();
    final Function1<Variable, ExpressionVariable> _function_1 = new Function1<Variable, ExpressionVariable>() {
      public ExpressionVariable apply(final Variable it) {
        ExpressionVariable _xblockexpression = null;
        {
          final Variable element = it;
          ExpressionVariable _createExpressionVariable = InternalSchemaCalculator.this.factory.createExpressionVariable();
          final Procedure1<ExpressionVariable> _function = new Procedure1<ExpressionVariable>() {
            public void apply(final ExpressionVariable it) {
              VariableExpression _createVariableExpression = InternalSchemaCalculator.this.factory.createVariableExpression();
              final Procedure1<VariableExpression> _function = new Procedure1<VariableExpression>() {
                public void apply(final VariableExpression it) {
                  it.setVariable(element);
                  RelalgContainer _namedElementContainer = element.getNamedElementContainer();
                  it.setExpressionContainer(_namedElementContainer);
                }
              };
              VariableExpression _doubleArrow = ObjectExtensions.<VariableExpression>operator_doubleArrow(_createVariableExpression, _function);
              it.setExpression(_doubleArrow);
              it.setHasInferredName(true);
              RelalgContainer _namedElementContainer = element.getNamedElementContainer();
              it.setNamedElementContainer(_namedElementContainer);
            }
          };
          _xblockexpression = ObjectExtensions.<ExpressionVariable>operator_doubleArrow(_createExpressionVariable, _function);
        }
        return _xblockexpression;
      }
    };
    List<ExpressionVariable> _map = ListExtensions.<Variable, ExpressionVariable>map(_extraVariables_1, _function_1);
    _internalElements_1.addAll(_map);
    EList<Integer> _tupleIndices = op.getTupleIndices();
    EList<Variable> _internalSchema_1 = op.getInternalSchema();
    final Function1<Variable, Integer> _function_2 = new Function1<Variable, Integer>() {
      public Integer apply(final Variable variable) {
        String _name = variable.getName();
        return Integer.valueOf(internalSchemaNames.indexOf(_name));
      }
    };
    List<Integer> _map_1 = ListExtensions.<Variable, Integer>map(_internalSchema_1, _function_2);
    _tupleIndices.addAll(_map_1);
  }
  
  private void defineInternalSchemaForGroupingOperator(final GroupingOperator op, final List<? extends Variable> internalSchema) {
    EList<Expression> _aggregationCriteria = op.getAggregationCriteria();
    EList<Variable> _extraVariables = op.getExtraVariables();
    final Function1<Variable, VariableExpression> _function = new Function1<Variable, VariableExpression>() {
      public VariableExpression apply(final Variable it) {
        VariableExpression _xblockexpression = null;
        {
          final Variable extraVariable = it;
          VariableExpression _createVariableExpression = InternalSchemaCalculator.this.factory.createVariableExpression();
          final Procedure1<VariableExpression> _function = new Procedure1<VariableExpression>() {
            public void apply(final VariableExpression it) {
              it.setVariable(extraVariable);
              RelalgContainer _namedElementContainer = extraVariable.getNamedElementContainer();
              it.setExpressionContainer(_namedElementContainer);
            }
          };
          _xblockexpression = ObjectExtensions.<VariableExpression>operator_doubleArrow(_createVariableExpression, _function);
        }
        return _xblockexpression;
      }
    };
    List<VariableExpression> _map = ListExtensions.<Variable, VariableExpression>map(_extraVariables, _function);
    _aggregationCriteria.addAll(_map);
    EList<ExpressionVariable> _elements = op.getElements();
    EList<Variable> _extraVariables_1 = op.getExtraVariables();
    final Function1<Variable, ExpressionVariable> _function_1 = new Function1<Variable, ExpressionVariable>() {
      public ExpressionVariable apply(final Variable it) {
        ExpressionVariable _xblockexpression = null;
        {
          final Variable extraVariable = it;
          ExpressionVariable _createExpressionVariable = InternalSchemaCalculator.this.factory.createExpressionVariable();
          final Procedure1<ExpressionVariable> _function = new Procedure1<ExpressionVariable>() {
            public void apply(final ExpressionVariable it) {
              VariableExpression _createVariableExpression = InternalSchemaCalculator.this.factory.createVariableExpression();
              final Procedure1<VariableExpression> _function = new Procedure1<VariableExpression>() {
                public void apply(final VariableExpression it) {
                  it.setVariable(extraVariable);
                  RelalgContainer _namedElementContainer = extraVariable.getNamedElementContainer();
                  it.setExpressionContainer(_namedElementContainer);
                }
              };
              VariableExpression _doubleArrow = ObjectExtensions.<VariableExpression>operator_doubleArrow(_createVariableExpression, _function);
              it.setExpression(_doubleArrow);
              RelalgContainer _namedElementContainer = extraVariable.getNamedElementContainer();
              it.setNamedElementContainer(_namedElementContainer);
              it.setHasInferredName(true);
            }
          };
          _xblockexpression = ObjectExtensions.<ExpressionVariable>operator_doubleArrow(_createExpressionVariable, _function);
        }
        return _xblockexpression;
      }
    };
    List<ExpressionVariable> _map_1 = ListExtensions.<Variable, ExpressionVariable>map(_extraVariables_1, _function_1);
    _elements.addAll(_map_1);
  }
  
  private void fillInternalSchema(final Operator op) {
    if (op instanceof UnionOperator) {
      _fillInternalSchema((UnionOperator)op);
      return;
    } else if (op instanceof AbstractJoinOperator) {
      _fillInternalSchema((AbstractJoinOperator)op);
      return;
    } else if (op instanceof NullaryOperator) {
      _fillInternalSchema((NullaryOperator)op);
      return;
    } else if (op instanceof UnaryOperator) {
      _fillInternalSchema((UnaryOperator)op);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(op).toString());
    }
  }
  
  private void defineInternalSchema(final Operator op, final List<? extends Variable> internalSchema) {
    if (op instanceof GroupingOperator) {
      _defineInternalSchema((GroupingOperator)op, internalSchema);
      return;
    } else if (op instanceof ExpandOperator) {
      _defineInternalSchema((ExpandOperator)op, internalSchema);
      return;
    } else if (op instanceof ProductionOperator) {
      _defineInternalSchema((ProductionOperator)op, internalSchema);
      return;
    } else if (op instanceof ProjectionOperator) {
      _defineInternalSchema((ProjectionOperator)op, internalSchema);
      return;
    } else if (op != null) {
      _defineInternalSchema(op, internalSchema);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(op, internalSchema).toString());
    }
  }
}
