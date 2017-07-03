package ingraph.relalg.calculators;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import ingraph.relalg.calculators.JoinSchemaCalculator;
import ingraph.relalg.util.visitors.PostOrderTreeVisitor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import relalg.AbstractEdgeVariable;
import relalg.AbstractJoinOperator;
import relalg.AttributeVariable;
import relalg.DualObjectSourceOperator;
import relalg.ElementVariable;
import relalg.ExpandOperator;
import relalg.Expression;
import relalg.ExpressionVariable;
import relalg.GetEdgesOperator;
import relalg.GetVerticesOperator;
import relalg.Operator;
import relalg.PathOperator;
import relalg.ProjectionOperator;
import relalg.RelalgContainer;
import relalg.RelalgFactory;
import relalg.UnaryOperator;
import relalg.UnionOperator;
import relalg.Variable;
import relalg.VariableExpression;
import relalg.VertexVariable;

/**
 * Calculates the external schema of the operators in the relational algebra tree.
 * 
 * This calculation uses a postorder traversal (action are applied from the bottom to the top)
 * first it uses recursion / dispatch methods to reach the (unary) input nodes,
 * then each method returns with the inferred schema.
 * 
 * For example, a join node concatenates the schema of its input nodes (left/right) and removes
 * duplicate attributes.
 */
@SuppressWarnings("all")
public class ExternalSchemaCalculator {
  @Extension
  private PostOrderTreeVisitor treeVisitor = new PostOrderTreeVisitor();
  
  @Extension
  private JoinSchemaCalculator joinSchemaCalculator = new JoinSchemaCalculator();
  
  @Extension
  private RelalgFactory factory = RelalgFactory.eINSTANCE;
  
  private final boolean includeEdges;
  
  public ExternalSchemaCalculator() {
    this(true);
  }
  
  public ExternalSchemaCalculator(final boolean includeEdges) {
    this.includeEdges = includeEdges;
  }
  
  public RelalgContainer calculateExternalSchema(final RelalgContainer container) {
    RelalgContainer _xblockexpression = null;
    {
      boolean _isExternalSchemaInferred = container.isExternalSchemaInferred();
      if (_isExternalSchemaInferred) {
        throw new IllegalStateException("ExternalSchemaCalculator on relalg container was already executed");
      } else {
        container.setExternalSchemaInferred(true);
      }
      Operator _rootExpression = container.getRootExpression();
      final Procedure1<Operator> _function = new Procedure1<Operator>() {
        public void apply(final Operator it) {
          ExternalSchemaCalculator.this.fillExternalSchema(it);
        }
      };
      this.treeVisitor.traverse(_rootExpression, _function);
      _xblockexpression = container;
    }
    return _xblockexpression;
  }
  
  private List<Variable> _fillExternalSchema(final DualObjectSourceOperator op) {
    return this.defineExternalSchema(op, Collections.<Variable>unmodifiableList(CollectionLiterals.<Variable>newArrayList()));
  }
  
  private List<Variable> _fillExternalSchema(final GetVerticesOperator op) {
    VertexVariable _vertexVariable = op.getVertexVariable();
    return this.defineExternalSchema(op, Collections.<Variable>unmodifiableList(CollectionLiterals.<Variable>newArrayList(_vertexVariable)));
  }
  
  private List<Variable> _fillExternalSchema(final GetEdgesOperator op) {
    List<Variable> _xifexpression = null;
    if (this.includeEdges) {
      VertexVariable _sourceVertexVariable = op.getSourceVertexVariable();
      AbstractEdgeVariable _edgeVariable = op.getEdgeVariable();
      VertexVariable _targetVertexVariable = op.getTargetVertexVariable();
      _xifexpression = this.defineExternalSchema(op, Collections.<Variable>unmodifiableList(CollectionLiterals.<Variable>newArrayList(_sourceVertexVariable, _edgeVariable, _targetVertexVariable)));
    } else {
      VertexVariable _sourceVertexVariable_1 = op.getSourceVertexVariable();
      VertexVariable _targetVertexVariable_1 = op.getTargetVertexVariable();
      _xifexpression = this.defineExternalSchema(op, Collections.<Variable>unmodifiableList(CollectionLiterals.<Variable>newArrayList(_sourceVertexVariable_1, _targetVertexVariable_1)));
    }
    return _xifexpression;
  }
  
  private List<Variable> _fillExternalSchema(final ProjectionOperator op) {
    List<Variable> _xblockexpression = null;
    {
      Operator _input = op.getInput();
      final EList<Variable> inputSchema = _input.getExternalSchema();
      EList<ExpressionVariable> _elements = op.getElements();
      final Function1<ExpressionVariable, Expression> _function = new Function1<ExpressionVariable, Expression>() {
        public Expression apply(final ExpressionVariable it) {
          return it.getExpression();
        }
      };
      List<Expression> _map = ListExtensions.<ExpressionVariable, Expression>map(_elements, _function);
      Iterable<ElementVariable> _filter = Iterables.<ElementVariable>filter(_map, ElementVariable.class);
      final Consumer<ElementVariable> _function_1 = new Consumer<ElementVariable>() {
        public void accept(final ElementVariable it) {
          boolean _contains = inputSchema.contains(it);
          boolean _not = (!_contains);
          if (_not) {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("Variable ");
            String _name = it.getName();
            _builder.append(_name, "");
            _builder.append(" is not part of the schema in projection operator.");
            throw new IllegalStateException(_builder.toString());
          }
        }
      };
      _filter.forEach(_function_1);
      EList<ExpressionVariable> _elements_1 = op.getElements();
      final Function1<ExpressionVariable, Expression> _function_2 = new Function1<ExpressionVariable, Expression>() {
        public Expression apply(final ExpressionVariable it) {
          return it.getExpression();
        }
      };
      List<Expression> _map_1 = ListExtensions.<ExpressionVariable, Expression>map(_elements_1, _function_2);
      Iterable<AttributeVariable> _filter_1 = Iterables.<AttributeVariable>filter(_map_1, AttributeVariable.class);
      final Consumer<AttributeVariable> _function_3 = new Consumer<AttributeVariable>() {
        public void accept(final AttributeVariable it) {
          ElementVariable _element = it.getElement();
          boolean _contains = inputSchema.contains(_element);
          boolean _not = (!_contains);
          if (_not) {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("Attribute ");
            String _name = it.getName();
            _builder.append(_name, "");
            _builder.append(" cannot be projected as its vertex/edge variable does not exists.");
            throw new IllegalStateException(_builder.toString());
          }
        }
      };
      _filter_1.forEach(_function_3);
      EList<ExpressionVariable> _elements_2 = op.getElements();
      List<Variable> _extractVariables = this.extractVariables(_elements_2);
      _xblockexpression = this.defineExternalSchema(op, _extractVariables);
    }
    return _xblockexpression;
  }
  
  private List<Variable> extractVariables(final List<ExpressionVariable> expressionVariables) {
    final Function1<ExpressionVariable, Variable> _function = new Function1<ExpressionVariable, Variable>() {
      public Variable apply(final ExpressionVariable expressionVariable) {
        Variable _xifexpression = null;
        boolean _isHasInferredName = expressionVariable.isHasInferredName();
        boolean _not = (!_isHasInferredName);
        if (_not) {
          ExpressionVariable _createExpressionVariable = ExternalSchemaCalculator.this.factory.createExpressionVariable();
          final Procedure1<ExpressionVariable> _function = new Procedure1<ExpressionVariable>() {
            public void apply(final ExpressionVariable it) {
              String _name = expressionVariable.getName();
              it.setName(_name);
              RelalgContainer _namedElementContainer = expressionVariable.getNamedElementContainer();
              it.setNamedElementContainer(_namedElementContainer);
            }
          };
          _xifexpression = ObjectExtensions.<ExpressionVariable>operator_doubleArrow(_createExpressionVariable, _function);
        } else {
          Variable _xblockexpression = null;
          {
            final Expression expr = expressionVariable.getExpression();
            Variable _xifexpression_1 = null;
            if ((expr instanceof VariableExpression)) {
              _xifexpression_1 = ((VariableExpression)expr).getVariable();
            } else {
              _xifexpression_1 = expressionVariable;
            }
            _xblockexpression = _xifexpression_1;
          }
          _xifexpression = _xblockexpression;
        }
        return _xifexpression;
      }
    };
    List<Variable> _map = ListExtensions.<ExpressionVariable, Variable>map(expressionVariables, _function);
    return IterableExtensions.<Variable>toList(_map);
  }
  
  private List<Variable> _fillExternalSchema(final ExpandOperator op) {
    List<Variable> _xblockexpression = null;
    {
      Operator _input = op.getInput();
      EList<Variable> _externalSchema = _input.getExternalSchema();
      final ArrayList<Variable> schema = Lists.<Variable>newArrayList(_externalSchema);
      if (this.includeEdges) {
        AbstractEdgeVariable _edgeVariable = op.getEdgeVariable();
        schema.add(_edgeVariable);
      }
      VertexVariable _targetVertexVariable = op.getTargetVertexVariable();
      schema.add(_targetVertexVariable);
      _xblockexpression = this.defineExternalSchema(op, schema);
    }
    return _xblockexpression;
  }
  
  private List<Variable> _fillExternalSchema(final UnaryOperator op) {
    List<Variable> _xblockexpression = null;
    {
      Operator _input = op.getInput();
      EList<Variable> _externalSchema = _input.getExternalSchema();
      final ArrayList<Variable> schema = Lists.<Variable>newArrayList(_externalSchema);
      _xblockexpression = this.defineExternalSchema(op, schema);
    }
    return _xblockexpression;
  }
  
  private List<Variable> _fillExternalSchema(final AbstractJoinOperator op) {
    EList<Variable> _xblockexpression = null;
    {
      Operator _leftInput = op.getLeftInput();
      EList<Variable> _externalSchema = _leftInput.getExternalSchema();
      final ArrayList<Variable> leftInputSchema = Lists.<Variable>newArrayList(_externalSchema);
      Operator _rightInput = op.getRightInput();
      EList<Variable> _externalSchema_1 = _rightInput.getExternalSchema();
      final ArrayList<Variable> rightInputSchema = Lists.<Variable>newArrayList(_externalSchema_1);
      final List<Variable> schema = this.joinSchemaCalculator.calculateJoinSchema(op, leftInputSchema, rightInputSchema);
      this.defineExternalSchema(op, schema);
      Operator _rightInput_1 = op.getRightInput();
      EList<Variable> _externalSchema_2 = _rightInput_1.getExternalSchema();
      final Function1<Variable, String> _function = new Function1<Variable, String>() {
        public String apply(final Variable it) {
          return it.getName();
        }
      };
      final List<String> rightSchemaNames = ListExtensions.<Variable, String>map(_externalSchema_2, _function);
      Operator _leftInput_1 = op.getLeftInput();
      EList<Variable> _externalSchema_3 = _leftInput_1.getExternalSchema();
      final Function1<Variable, Boolean> _function_1 = new Function1<Variable, Boolean>() {
        public Boolean apply(final Variable variable) {
          String _name = variable.getName();
          return Boolean.valueOf(rightSchemaNames.contains(_name));
        }
      };
      final Iterable<Variable> commonVariables = IterableExtensions.<Variable>filter(_externalSchema_3, _function_1);
      EList<Variable> _commonVariables = op.getCommonVariables();
      Iterables.<Variable>addAll(_commonVariables, commonVariables);
      _xblockexpression = op.getExternalSchema();
    }
    return _xblockexpression;
  }
  
  private List<Variable> _fillExternalSchema(final UnionOperator op) {
    List<Variable> _xblockexpression = null;
    {
      Operator _leftInput = op.getLeftInput();
      EList<Variable> _externalSchema = _leftInput.getExternalSchema();
      Operator _rightInput = op.getRightInput();
      EList<Variable> _externalSchema_1 = _rightInput.getExternalSchema();
      boolean _equals = _externalSchema.equals(_externalSchema_1);
      if (_equals) {
        throw new IllegalStateException("All sub queries in a UNION must have the same column names");
      }
      Operator _leftInput_1 = op.getLeftInput();
      EList<Variable> _externalSchema_2 = _leftInput_1.getExternalSchema();
      _xblockexpression = this.defineExternalSchema(op, _externalSchema_2);
    }
    return _xblockexpression;
  }
  
  private List<Variable> _fillExternalSchema(final PathOperator op) {
    List<Variable> _xblockexpression = null;
    {
      Operator _input = op.getInput();
      EList<Variable> _externalSchema = _input.getExternalSchema();
      final ArrayList<Variable> schema = Lists.<Variable>newArrayList(_externalSchema);
      VertexVariable _targetVertexVariable = op.getTargetVertexVariable();
      schema.add(_targetVertexVariable);
      _xblockexpression = this.defineExternalSchema(op, schema);
    }
    return _xblockexpression;
  }
  
  /**
   * defineSchema
   */
  private List<Variable> defineExternalSchema(final Operator op, final List<Variable> externalSchema) {
    List<Variable> _xblockexpression = null;
    {
      ImmutableSet<Variable> _copyOf = ImmutableSet.<Variable>copyOf(externalSchema);
      final ImmutableList<Variable> uniqueList = _copyOf.asList();
      EList<Variable> _externalSchema = op.getExternalSchema();
      _externalSchema.addAll(uniqueList);
      _xblockexpression = externalSchema;
    }
    return _xblockexpression;
  }
  
  private List<Variable> fillExternalSchema(final Operator op) {
    if (op instanceof PathOperator) {
      return _fillExternalSchema((PathOperator)op);
    } else if (op instanceof ExpandOperator) {
      return _fillExternalSchema((ExpandOperator)op);
    } else if (op instanceof GetEdgesOperator) {
      return _fillExternalSchema((GetEdgesOperator)op);
    } else if (op instanceof ProjectionOperator) {
      return _fillExternalSchema((ProjectionOperator)op);
    } else if (op instanceof UnionOperator) {
      return _fillExternalSchema((UnionOperator)op);
    } else if (op instanceof AbstractJoinOperator) {
      return _fillExternalSchema((AbstractJoinOperator)op);
    } else if (op instanceof DualObjectSourceOperator) {
      return _fillExternalSchema((DualObjectSourceOperator)op);
    } else if (op instanceof GetVerticesOperator) {
      return _fillExternalSchema((GetVerticesOperator)op);
    } else if (op instanceof UnaryOperator) {
      return _fillExternalSchema((UnaryOperator)op);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(op).toString());
    }
  }
}
