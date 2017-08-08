package ingraph.relalg2tex.converters.operatorconverters;

import ingraph.relalg2tex.config.RelalgConverterConfig;
import ingraph.relalg2tex.converters.elementconverters.ElementConverter;
import ingraph.relalg2tex.converters.elementconverters.ExpressionConverter;
import ingraph.relalg2tex.converters.elementconverters.MiscConverters;
import ingraph.relalg2tex.converters.elementconverters.StringEscaper;
import ingraph.relalg2tex.converters.operatorconverters.BinaryOperatorConverter;
import ingraph.relalg2tex.converters.operatorconverters.GroupingProjectionOperatorConverter;
import ingraph.relalg2tex.converters.operatorconverters.SortTopOperatorConverter;
import ingraph.relalg2tex.converters.variableconverters.VariableExpressionConverter;
import ingraph.relalg2tex.converters.variableconverters.VariableNameConverter;
import java.util.Arrays;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import relalg.AbstractEdgeVariable;
import relalg.AllDifferentOperator;
import relalg.BinaryOperator;
import relalg.CreateOperator;
import relalg.DeleteOperator;
import relalg.Direction;
import relalg.DualObjectSourceOperator;
import relalg.DuplicateEliminationOperator;
import relalg.EdgeListVariable;
import relalg.ExpandOperator;
import relalg.Expression;
import relalg.ExpressionVariable;
import relalg.GetEdgesOperator;
import relalg.GetVerticesOperator;
import relalg.GroupingOperator;
import relalg.LogicalExpression;
import relalg.MaxHops;
import relalg.Operator;
import relalg.PathOperator;
import relalg.ProductionOperator;
import relalg.ProjectionOperator;
import relalg.SelectionOperator;
import relalg.SortAndTopOperator;
import relalg.SortOperator;
import relalg.TopOperator;
import relalg.UnwindOperator;
import relalg.VertexVariable;

@SuppressWarnings("all")
public class OperatorConverter {
  private RelalgConverterConfig config;
  
  @Extension
  private StringEscaper stringEscaper = new StringEscaper();
  
  @Extension
  private MiscConverters miscConverters = new MiscConverters();
  
  @Extension
  private ElementConverter elementConverter = new ElementConverter();
  
  @Extension
  private ExpressionConverter expressionConverter = new ExpressionConverter();
  
  @Extension
  private GroupingProjectionOperatorConverter groupingProjectionOperatorConverter = new GroupingProjectionOperatorConverter();
  
  @Extension
  private SortTopOperatorConverter sortTopOperatorConverter = new SortTopOperatorConverter();
  
  @Extension
  private BinaryOperatorConverter binaryOperatorConverter;
  
  private final VariableNameConverter variableNameConverter = new VariableNameConverter();
  
  private final VariableExpressionConverter variableExpressionConverter = new VariableExpressionConverter();
  
  public OperatorConverter(final RelalgConverterConfig config) {
    this.config = config;
    BinaryOperatorConverter _binaryOperatorConverter = new BinaryOperatorConverter(config);
    this.binaryOperatorConverter = _binaryOperatorConverter;
  }
  
  /**
   * NullaryOperators
   */
  protected CharSequence _convertOperator(final DualObjectSourceOperator op) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\dual");
    return _builder;
  }
  
  protected CharSequence _convertOperator(final GetEdgesOperator op) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\getedges");
    {
      Direction _direction = op.getDirection();
      boolean _tripleEquals = (_direction == Direction.BOTH);
      if (_tripleEquals) {
        _builder.append("undirected");
      } else {
        _builder.append("directed");
      }
    }
    StringConcatenation _builder_1 = new StringConcatenation();
    VertexVariable _sourceVertexVariable = op.getSourceVertexVariable();
    String _convertElement = this.elementConverter.convertElement(_sourceVertexVariable);
    _builder_1.append(_convertElement, "");
    VertexVariable _targetVertexVariable = op.getTargetVertexVariable();
    String _convertElement_1 = this.elementConverter.convertElement(_targetVertexVariable);
    _builder_1.append(_convertElement_1, "");
    AbstractEdgeVariable _edgeVariable = op.getEdgeVariable();
    String _convertElement_2 = this.elementConverter.convertElement(_edgeVariable);
    _builder_1.append(_convertElement_2, "");
    return (_builder.toString() + _builder_1);
  }
  
  protected CharSequence _convertOperator(final GetVerticesOperator op) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\getvertices");
    VertexVariable _vertexVariable = op.getVertexVariable();
    String _convertElement = this.elementConverter.convertElement(_vertexVariable);
    _builder.append(_convertElement, "");
    return _builder;
  }
  
  /**
   * UnaryOperators
   */
  protected CharSequence _convertOperator(final AllDifferentOperator op) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\alldifferent{");
    EList<AbstractEdgeVariable> _edgeVariables = op.getEdgeVariables();
    CharSequence _edgeVariableList = this.miscConverters.edgeVariableList(_edgeVariables);
    _builder.append(_edgeVariableList, "");
    _builder.append("}");
    return _builder;
  }
  
  protected CharSequence _convertOperator(final DuplicateEliminationOperator op) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\duplicateelimination");
    return _builder;
  }
  
  protected CharSequence _convertOperator(final ExpandOperator op) {
    String _xblockexpression = null;
    {
      final AbstractEdgeVariable ev = op.getEdgeVariable();
      String _xifexpression = null;
      if ((op instanceof PathOperator)) {
        throw new UnsupportedOperationException("Path operators are not yet supported.");
      } else {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("\\expand");
        StringConcatenation _builder_1 = new StringConcatenation();
        Direction _direction = op.getDirection();
        CharSequence _convertDirection = this.miscConverters.convertDirection(_direction);
        _builder_1.append(_convertDirection, "");
        String _plus = (_builder.toString() + _builder_1);
        StringConcatenation _builder_2 = new StringConcatenation();
        _builder_2.append("{");
        VertexVariable _sourceVertexVariable = op.getSourceVertexVariable();
        CharSequence _escapedName = this.stringEscaper.escapedName(_sourceVertexVariable);
        _builder_2.append(_escapedName, "");
        _builder_2.append("}");
        String _plus_1 = (_plus + _builder_2);
        StringConcatenation _builder_3 = new StringConcatenation();
        VertexVariable _targetVertexVariable = op.getTargetVertexVariable();
        String _convertElement = this.elementConverter.convertElement(_targetVertexVariable);
        _builder_3.append(_convertElement, "");
        String _plus_2 = (_plus_1 + _builder_3);
        StringConcatenation _builder_4 = new StringConcatenation();
        AbstractEdgeVariable _edgeVariable = op.getEdgeVariable();
        String _convertElement_1 = this.elementConverter.convertElement(_edgeVariable);
        _builder_4.append(_convertElement_1, "");
        String _plus_3 = (_plus_2 + _builder_4);
        CharSequence _xifexpression_1 = null;
        if ((ev instanceof EdgeListVariable)) {
          StringConcatenation _builder_5 = new StringConcatenation();
          _builder_5.append("{");
          int _minHops = ((EdgeListVariable)ev).getMinHops();
          _builder_5.append(_minHops, "");
          _builder_5.append("}{");
          MaxHops _maxHops = ((EdgeListVariable)ev).getMaxHops();
          CharSequence _hopsToString = this.miscConverters.hopsToString(_maxHops);
          _builder_5.append(_hopsToString, "");
          _builder_5.append("}");
          _xifexpression_1 = _builder_5;
        } else {
          StringConcatenation _builder_6 = new StringConcatenation();
          _builder_6.append("{1}{1}");
          _xifexpression_1 = _builder_6;
        }
        _xifexpression = (_plus_3 + _xifexpression_1);
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  protected CharSequence _convertOperator(final ProductionOperator op) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\production{");
    EList<ExpressionVariable> _elements = op.getElements();
    CharSequence _convertReturnableElementList = this.groupingProjectionOperatorConverter.convertReturnableElementList(_elements);
    _builder.append(_convertReturnableElementList, "");
    _builder.append("}");
    return _builder;
  }
  
  protected CharSequence _convertOperator(final GroupingOperator op) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _groupingOperator = this.groupingProjectionOperatorConverter.groupingOperator(op);
    _builder.append(_groupingOperator, "");
    return _builder;
  }
  
  protected CharSequence _convertOperator(final ProjectionOperator op) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _projectionOperator = this.groupingProjectionOperatorConverter.projectionOperator(op);
    _builder.append(_projectionOperator, "");
    return _builder;
  }
  
  protected CharSequence _convertOperator(final CreateOperator op) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\create{");
    EList<ExpressionVariable> _elements = op.getElements();
    final Function1<ExpressionVariable, CharSequence> _function = (ExpressionVariable it) -> {
      return this.variableNameConverter.convertVariable(it);
    };
    List<CharSequence> _map = ListExtensions.<ExpressionVariable, CharSequence>map(_elements, _function);
    String _join = IterableExtensions.join(_map, ", ");
    _builder.append(_join, "");
    _builder.append("}");
    return _builder;
  }
  
  protected CharSequence _convertOperator(final DeleteOperator op) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\delete{");
    {
      boolean _isDetach = op.isDetach();
      if (_isDetach) {
        _builder.append("*");
      }
    }
    _builder.append("}{");
    EList<ExpressionVariable> _elements = op.getElements();
    final Function1<ExpressionVariable, CharSequence> _function = (ExpressionVariable it) -> {
      Expression _expression = it.getExpression();
      return this.expressionConverter.convertExpression(_expression);
    };
    List<CharSequence> _map = ListExtensions.<ExpressionVariable, CharSequence>map(_elements, _function);
    String _join = IterableExtensions.join(_map, ", ");
    _builder.append(_join, "");
    _builder.append("}");
    return _builder;
  }
  
  protected CharSequence _convertOperator(final SelectionOperator op) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\selection{");
    _builder.newLine();
    _builder.append("\t");
    {
      LogicalExpression _condition = op.getCondition();
      boolean _tripleNotEquals = (_condition != null);
      if (_tripleNotEquals) {
        LogicalExpression _condition_1 = op.getCondition();
        CharSequence _convertExpression = this.expressionConverter.convertExpression(_condition_1);
        _builder.append(_convertExpression, "\t");
      } else {
        _builder.append("\\mathtt{");
        String _conditionString = op.getConditionString();
        String _convertConditionString = this.miscConverters.convertConditionString(_conditionString);
        _builder.append(_convertConditionString, "\t");
        _builder.append("}");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence _convertOperator(final SortOperator op) {
    return this.sortTopOperatorConverter.sortOperatorToTex(op);
  }
  
  protected CharSequence _convertOperator(final TopOperator op) {
    return this.sortTopOperatorConverter.topOperatorToTex(op);
  }
  
  protected CharSequence _convertOperator(final SortAndTopOperator op) {
    String _sortOperatorToTex = this.sortTopOperatorConverter.sortOperatorToTex(op);
    String _pOperatorToTex = this.sortTopOperatorConverter.topOperatorToTex(op);
    return (_sortOperatorToTex + _pOperatorToTex);
  }
  
  protected CharSequence _convertOperator(final UnwindOperator op) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\unwind{");
    ExpressionVariable _element = op.getElement();
    CharSequence _convertVariable = this.variableExpressionConverter.convertVariable(_element);
    _builder.append(_convertVariable, "");
    _builder.append("}");
    return _builder;
  }
  
  /**
   * BinaryOperators
   */
  protected CharSequence _convertOperator(final BinaryOperator op) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\");
    CharSequence _convertBinaryOperator = this.binaryOperatorConverter.convertBinaryOperator(op);
    _builder.append(_convertBinaryOperator, "");
    return _builder;
  }
  
  public CharSequence convertOperator(final Operator op) {
    if (op instanceof GroupingOperator) {
      return _convertOperator((GroupingOperator)op);
    } else if (op instanceof CreateOperator) {
      return _convertOperator((CreateOperator)op);
    } else if (op instanceof DeleteOperator) {
      return _convertOperator((DeleteOperator)op);
    } else if (op instanceof ExpandOperator) {
      return _convertOperator((ExpandOperator)op);
    } else if (op instanceof GetEdgesOperator) {
      return _convertOperator((GetEdgesOperator)op);
    } else if (op instanceof ProductionOperator) {
      return _convertOperator((ProductionOperator)op);
    } else if (op instanceof ProjectionOperator) {
      return _convertOperator((ProjectionOperator)op);
    } else if (op instanceof SortAndTopOperator) {
      return _convertOperator((SortAndTopOperator)op);
    } else if (op instanceof AllDifferentOperator) {
      return _convertOperator((AllDifferentOperator)op);
    } else if (op instanceof DualObjectSourceOperator) {
      return _convertOperator((DualObjectSourceOperator)op);
    } else if (op instanceof DuplicateEliminationOperator) {
      return _convertOperator((DuplicateEliminationOperator)op);
    } else if (op instanceof GetVerticesOperator) {
      return _convertOperator((GetVerticesOperator)op);
    } else if (op instanceof SelectionOperator) {
      return _convertOperator((SelectionOperator)op);
    } else if (op instanceof SortOperator) {
      return _convertOperator((SortOperator)op);
    } else if (op instanceof TopOperator) {
      return _convertOperator((TopOperator)op);
    } else if (op instanceof UnwindOperator) {
      return _convertOperator((UnwindOperator)op);
    } else if (op instanceof BinaryOperator) {
      return _convertOperator((BinaryOperator)op);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(op).toString());
    }
  }
}
