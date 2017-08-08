package ingraph.relalg2tex.converters.elementconverters;

import com.google.common.base.Objects;
import ingraph.relalg2tex.constants.RelNullConstants;
import ingraph.relalg2tex.converters.elementconverters.ElementConverter;
import ingraph.relalg2tex.converters.elementconverters.ExpressionOperatorTypeConverter;
import ingraph.relalg2tex.converters.elementconverters.StringEscaper;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import relalg.AbstractEdgeVariable;
import relalg.ArithmeticComparisonExpression;
import relalg.ArithmeticComparisonOperatorType;
import relalg.ArithmeticExpression;
import relalg.AttributeVariable;
import relalg.BigIntegerLiteral;
import relalg.BinaryArithmeticOperationExpression;
import relalg.BinaryArithmeticOperatorType;
import relalg.BinaryLogicalExpression;
import relalg.BinaryLogicalOperatorType;
import relalg.BooleanLiteral;
import relalg.Case;
import relalg.CaseExpression;
import relalg.ComparableExpression;
import relalg.Direction;
import relalg.DoubleLiteral;
import relalg.EdgeLabelSet;
import relalg.ElementVariable;
import relalg.EmptyListExpression;
import relalg.Expression;
import relalg.ExpressionVariable;
import relalg.FunctionExpression;
import relalg.IndexRangeAccessExpression;
import relalg.IndexSimpleAccessExpression;
import relalg.IntegerLiteral;
import relalg.ListExpression;
import relalg.LogicalExpression;
import relalg.NavigationDescriptor;
import relalg.NullLiteral;
import relalg.Parameter;
import relalg.ParameterComparableExpression;
import relalg.RelalgModelElement;
import relalg.SimpleCaseExpression;
import relalg.StringLiteral;
import relalg.UnaryGraphObjectLogicalExpression;
import relalg.UnaryGraphObjectLogicalOperatorType;
import relalg.UnaryLogicalExpression;
import relalg.UnaryLogicalOperatorType;
import relalg.Variable;
import relalg.VariableExpression;
import relalg.VertexLabelSet;
import relalg.VertexVariable;
import relalg.function.Function;

@SuppressWarnings("all")
public class ExpressionConverter {
  @Extension
  private StringEscaper stringEscaper = new StringEscaper();
  
  @Extension
  private ExpressionOperatorTypeConverter operatorConverter = new ExpressionOperatorTypeConverter();
  
  @Extension
  private ElementConverter elementConverter = new ElementConverter();
  
  /**
   * In case of VariableExpression wrapping a VertexVariable or AbstractEdgeVariable,
   * labelset constraints will also be serialized.
   * 
   * Otherwise, it falls back to convertExpression.
   */
  public CharSequence convertExpressionWithLabelSet(final Expression exp) {
    if ((exp instanceof VariableExpression)) {
      final Variable v = ((VariableExpression)exp).getVariable();
      if ((v instanceof VertexVariable)) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("\\vertexvariable{");
        CharSequence _escapedName = this.stringEscaper.escapedName(v);
        _builder.append(_escapedName, "");
        _builder.append("}{");
        VertexLabelSet _vertexLabelSet = ((VertexVariable)v).getVertexLabelSet();
        String _convertVertexLabelSet = this.elementConverter.convertVertexLabelSet(_vertexLabelSet);
        _builder.append(_convertVertexLabelSet, "");
        _builder.append("}");
        return _builder;
      } else {
        if ((v instanceof AbstractEdgeVariable)) {
          StringConcatenation _builder_1 = new StringConcatenation();
          _builder_1.append("\\edgevariable{");
          CharSequence _escapedName_1 = this.stringEscaper.escapedName(v);
          _builder_1.append(_escapedName_1, "");
          _builder_1.append("}{");
          EdgeLabelSet _edgeLabelSet = ((AbstractEdgeVariable)v).getEdgeLabelSet();
          String _convertEdgeLabelSet = this.elementConverter.convertEdgeLabelSet(_edgeLabelSet);
          _builder_1.append(_convertEdgeLabelSet, "");
          _builder_1.append("}");
          return _builder_1;
        }
      }
    }
    return this.convertExpression(exp);
  }
  
  protected CharSequence _convertExpression(final IntegerLiteral integerLiteral) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\literal{");
    long _value = integerLiteral.getValue();
    _builder.append(_value, "");
    _builder.append("}");
    return _builder;
  }
  
  protected CharSequence _convertExpression(final IndexSimpleAccessExpression ie) {
    StringConcatenation _builder = new StringConcatenation();
    Expression _list = ie.getList();
    CharSequence _convertExpression = this.convertExpression(_list);
    _builder.append(_convertExpression, "");
    _builder.append("[\\literal{");
    int _idx = ie.getIdx();
    _builder.append(_idx, "");
    _builder.append("}]");
    return _builder;
  }
  
  protected CharSequence _convertExpression(final IndexRangeAccessExpression ie) {
    StringConcatenation _builder = new StringConcatenation();
    Expression _list = ie.getList();
    CharSequence _convertExpression = this.convertExpression(_list);
    _builder.append(_convertExpression, "");
    _builder.append("[\\literal{");
    int _lower = ie.getLower();
    _builder.append(_lower, "");
    _builder.append("}..\\literal{");
    int _upper = ie.getUpper();
    _builder.append(_upper, "");
    _builder.append("}]");
    return _builder;
  }
  
  protected CharSequence _convertExpression(final BigIntegerLiteral bigintegerLiteral) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\literal{");
    BigInteger _value = bigintegerLiteral.getValue();
    _builder.append(_value, "");
    _builder.append("}");
    return _builder;
  }
  
  protected CharSequence _convertExpression(final DoubleLiteral doubleLiteral) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\literal{");
    double _value = doubleLiteral.getValue();
    _builder.append(_value, "");
    _builder.append("}");
    return _builder;
  }
  
  protected CharSequence _convertExpression(final StringLiteral stringLiteral) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\literal{\"");
    String _value = stringLiteral.getValue();
    String _escape = this.stringEscaper.escape(_value);
    _builder.append(_escape, "");
    _builder.append("\"}");
    return _builder;
  }
  
  protected CharSequence _convertExpression(final Parameter parameter) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\var{\\$");
    String _name = parameter.getName();
    _builder.append(_name, "");
    _builder.append("}");
    return _builder;
  }
  
  protected CharSequence _convertExpression(final ParameterComparableExpression pce) {
    StringConcatenation _builder = new StringConcatenation();
    Parameter _parameter = pce.getParameter();
    CharSequence _convertExpression = this.convertExpression(_parameter);
    _builder.append(_convertExpression, "");
    return _builder;
  }
  
  protected CharSequence _convertExpression(final ElementVariable elementVariable) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\var{");
    CharSequence _escapedName = this.stringEscaper.escapedName(elementVariable);
    _builder.append(_escapedName, "");
    _builder.append("}");
    return _builder;
  }
  
  protected CharSequence _convertExpression(final AttributeVariable attributeVariable) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\var{");
    {
      ElementVariable _element = attributeVariable.getElement();
      boolean _tripleNotEquals = (_element != null);
      if (_tripleNotEquals) {
        ElementVariable _element_1 = attributeVariable.getElement();
        CharSequence _escapedName = this.stringEscaper.escapedName(_element_1);
        _builder.append(_escapedName, "");
      } else {
        ExpressionVariable _expVar = attributeVariable.getExpVar();
        CharSequence _escapedName_1 = this.stringEscaper.escapedName(_expVar);
        _builder.append(_escapedName_1, "");
      }
    }
    _builder.append(".");
    CharSequence _escapedName_2 = this.stringEscaper.escapedName(attributeVariable);
    _builder.append(_escapedName_2, "");
    _builder.append("}");
    return _builder;
  }
  
  protected CharSequence _convertExpression(final ExpressionVariable expVariable) {
    CharSequence _xifexpression = null;
    boolean _isHasInferredName = expVariable.isHasInferredName();
    if (_isHasInferredName) {
      Expression _expression = expVariable.getExpression();
      _xifexpression = this.convertExpression(_expression);
    } else {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("\\var{");
      CharSequence _escapedName = this.stringEscaper.escapedName(expVariable);
      _builder.append(_escapedName, "");
      _builder.append("}");
      _xifexpression = _builder;
    }
    return _xifexpression;
  }
  
  protected CharSequence _convertExpression(final VariableExpression ve) {
    Variable _variable = ve.getVariable();
    return this.convertExpression(_variable);
  }
  
  protected CharSequence _convertExpression(final FunctionExpression fe) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\literal{");
    Function _functor = fe.getFunctor();
    String _string = _functor.toString();
    String _escape = this.stringEscaper.escape(_string);
    _builder.append(_escape, "");
    _builder.append("}");
    _builder.newLineIfNotEmpty();
    {
      Function _functor_1 = fe.getFunctor();
      boolean _notEquals = (!Objects.equal(_functor_1, Function.COUNT_ALL));
      if (_notEquals) {
        _builder.append("\\left( ");
        EList<Expression> _arguments = fe.getArguments();
        final Function1<Expression, CharSequence> _function = (Expression it) -> {
          return this.convertExpression(it);
        };
        List<CharSequence> _map = ListExtensions.<Expression, CharSequence>map(_arguments, _function);
        String _join = IterableExtensions.join(_map, ", ");
        _builder.append(_join, "");
        _builder.append(" \\right)");
      }
    }
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  protected CharSequence _convertExpression(final EmptyListExpression fe) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\left[ \\right]");
    return _builder;
  }
  
  protected CharSequence _convertExpression(final ListExpression fe) {
    String _xblockexpression = null;
    {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("\\left[");
      String retVal = _builder.toString();
      for (ListExpression i = fe; (!(i instanceof EmptyListExpression)); i = i.getTail()) {
        String _retVal = retVal;
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append(" ");
        Expression _head = i.getHead();
        CharSequence _convertExpression = this.convertExpression(_head);
        _builder_1.append(_convertExpression, " ");
        {
          ListExpression _tail = i.getTail();
          boolean _not = (!(_tail instanceof EmptyListExpression));
          if (_not) {
            _builder_1.append(",");
          }
        }
        retVal = (_retVal + _builder_1);
      }
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append(" ");
      _builder_1.append("\\right]");
      _xblockexpression = (retVal + _builder_1);
    }
    return _xblockexpression;
  }
  
  protected CharSequence _convertExpression(final BinaryLogicalExpression exp) {
    StringConcatenation _builder = new StringConcatenation();
    LogicalExpression _leftOperand = exp.getLeftOperand();
    CharSequence _convertExpression = this.convertExpression(_leftOperand);
    _builder.append(_convertExpression, "");
    _builder.append(" ");
    BinaryLogicalOperatorType _operator = exp.getOperator();
    CharSequence _convert = this.operatorConverter.convert(_operator);
    _builder.append(_convert, "");
    _builder.append(" ");
    LogicalExpression _rightOperand = exp.getRightOperand();
    CharSequence _convertExpression_1 = this.convertExpression(_rightOperand);
    _builder.append(_convertExpression_1, "");
    return _builder;
  }
  
  protected CharSequence _convertExpression(final UnaryLogicalExpression exp) {
    StringConcatenation _builder = new StringConcatenation();
    UnaryLogicalOperatorType _operator = exp.getOperator();
    CharSequence _convert = this.operatorConverter.convert(_operator);
    _builder.append(_convert, "");
    _builder.append(" \\left( ");
    LogicalExpression _operand = exp.getOperand();
    CharSequence _convertExpression = this.convertExpression(_operand);
    _builder.append(_convertExpression, "");
    _builder.append(" \\right)");
    return _builder;
  }
  
  protected CharSequence _convertExpression(final UnaryGraphObjectLogicalExpression exp) {
    StringConcatenation _builder = new StringConcatenation();
    Variable _operand = exp.getOperand();
    CharSequence _convertExpression = this.convertExpression(_operand);
    _builder.append(_convertExpression, "");
    _builder.append(" ");
    UnaryGraphObjectLogicalOperatorType _operator = exp.getOperator();
    String _convert = this.operatorConverter.convert(_operator);
    _builder.append(_convert, "");
    return _builder;
  }
  
  protected CharSequence _convertExpression(final ArithmeticComparisonExpression exp) {
    StringConcatenation _builder = new StringConcatenation();
    ComparableExpression _leftOperand = exp.getLeftOperand();
    CharSequence _convertExpression = this.convertExpression(_leftOperand);
    _builder.append(_convertExpression, "");
    _builder.append(" ");
    ArithmeticComparisonOperatorType _operator = exp.getOperator();
    CharSequence _convertOperatorType = this.operatorConverter.convertOperatorType(_operator);
    _builder.append(_convertOperatorType, "");
    _builder.append(" ");
    ComparableExpression _rightOperand = exp.getRightOperand();
    CharSequence _convertExpression_1 = this.convertExpression(_rightOperand);
    _builder.append(_convertExpression_1, "");
    return _builder;
  }
  
  protected CharSequence _convertExpression(final BinaryArithmeticOperationExpression exp) {
    StringConcatenation _builder = new StringConcatenation();
    ArithmeticExpression _leftOperand = exp.getLeftOperand();
    CharSequence _convertExpression = this.convertExpression(_leftOperand);
    _builder.append(_convertExpression, "");
    _builder.append(" ");
    BinaryArithmeticOperatorType _operator = exp.getOperator();
    CharSequence _convert = this.operatorConverter.convert(_operator);
    _builder.append(_convert, "");
    _builder.append(" ");
    ArithmeticExpression _rightOperand = exp.getRightOperand();
    CharSequence _convertExpression_1 = this.convertExpression(_rightOperand);
    _builder.append(_convertExpression_1, "");
    return _builder;
  }
  
  protected CharSequence _convertExpression(final NavigationDescriptor exp) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\navigationdescriptor");
    CharSequence _switchResult = null;
    Direction _direction = exp.getDirection();
    if (_direction != null) {
      switch (_direction) {
        case IN:
          StringConcatenation _builder_1 = new StringConcatenation();
          _builder_1.append("in");
          _switchResult = _builder_1;
          break;
        case OUT:
          StringConcatenation _builder_2 = new StringConcatenation();
          _builder_2.append("out");
          _switchResult = _builder_2;
          break;
        case BOTH:
          StringConcatenation _builder_3 = new StringConcatenation();
          _builder_3.append("both");
          _switchResult = _builder_3;
          break;
        default:
          break;
      }
    }
    String _plus = (_builder.toString() + _switchResult);
    StringConcatenation _builder_4 = new StringConcatenation();
    AbstractEdgeVariable _edgeVariable = exp.getEdgeVariable();
    String _convertElement = this.elementConverter.convertElement(_edgeVariable);
    _builder_4.append(_convertElement, "");
    String _plus_1 = (_plus + _builder_4);
    StringConcatenation _builder_5 = new StringConcatenation();
    _builder_5.append("{");
    VertexVariable _sourceVertexVariable = exp.getSourceVertexVariable();
    CharSequence _convertExpression = this.convertExpression(_sourceVertexVariable);
    _builder_5.append(_convertExpression, "");
    _builder_5.append("}{");
    VertexVariable _targetVertexVariable = exp.getTargetVertexVariable();
    CharSequence _convertExpression_1 = this.convertExpression(_targetVertexVariable);
    _builder_5.append(_convertExpression_1, "");
    _builder_5.append("}");
    return (_plus_1 + _builder_5);
  }
  
  protected CharSequence _convertExpression(final CaseExpression ce) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\literal{case}");
    _builder.newLine();
    _builder.append("\\left(");
    _builder.newLine();
    _builder.append("\t");
    {
      if ((ce instanceof SimpleCaseExpression)) {
        _builder.append("\\literal{test:\\ }");
        Expression _test = ((SimpleCaseExpression)ce).getTest();
        CharSequence _convertExpression = this.convertExpression(_test);
        _builder.append(_convertExpression, "\t");
        _builder.append(", ");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    EList<Case> _cases = ce.getCases();
    final Function1<Case, String> _function = (Case it) -> {
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("\\left \\{");
      Expression _when = it.getWhen();
      CharSequence _convertExpression_1 = this.convertExpression(_when);
      String _plus = (_builder_1.toString() + _convertExpression_1);
      StringConcatenation _builder_2 = new StringConcatenation();
      _builder_2.append("\\Rightarrow");
      String _plus_1 = (_plus + _builder_2);
      Expression _then = it.getThen();
      CharSequence _convertExpression_2 = this.convertExpression(_then);
      String _plus_2 = (_plus_1 + _convertExpression_2);
      StringConcatenation _builder_3 = new StringConcatenation();
      _builder_3.append("\\right \\}");
      return (_plus_2 + _builder_3);
    };
    List<String> _map = ListExtensions.<Case, String>map(_cases, _function);
    String _join = IterableExtensions.join(_map, ", ");
    _builder.append(_join, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    {
      Expression _fallback = ce.getFallback();
      boolean _tripleNotEquals = (_fallback != null);
      if (_tripleNotEquals) {
        _builder.append(", \\literal{else:\\ }");
        Expression _fallback_1 = ce.getFallback();
        CharSequence _convertExpression_1 = this.convertExpression(_fallback_1);
        _builder.append(_convertExpression_1, "\t");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("\\right)");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence _convertExpression(final BooleanLiteral exp) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\mathtt{");
    String _xifexpression = null;
    boolean _isValue = exp.isValue();
    if (_isValue) {
      _xifexpression = "true";
    } else {
      _xifexpression = "false";
    }
    _builder.append(_xifexpression, "");
    _builder.append("}");
    return _builder;
  }
  
  protected CharSequence _convertExpression(final NullLiteral x) {
    return RelNullConstants.relNull;
  }
  
  protected CharSequence _convertExpression(final Void x) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Void:null");
    return _builder;
  }
  
  public CharSequence convertExpression(final RelalgModelElement bigintegerLiteral) {
    if (bigintegerLiteral instanceof BigIntegerLiteral) {
      return _convertExpression((BigIntegerLiteral)bigintegerLiteral);
    } else if (bigintegerLiteral instanceof DoubleLiteral) {
      return _convertExpression((DoubleLiteral)bigintegerLiteral);
    } else if (bigintegerLiteral instanceof IntegerLiteral) {
      return _convertExpression((IntegerLiteral)bigintegerLiteral);
    } else if (bigintegerLiteral instanceof NullLiteral) {
      return _convertExpression((NullLiteral)bigintegerLiteral);
    } else if (bigintegerLiteral instanceof AttributeVariable) {
      return _convertExpression((AttributeVariable)bigintegerLiteral);
    } else if (bigintegerLiteral instanceof BinaryArithmeticOperationExpression) {
      return _convertExpression((BinaryArithmeticOperationExpression)bigintegerLiteral);
    } else if (bigintegerLiteral instanceof BooleanLiteral) {
      return _convertExpression((BooleanLiteral)bigintegerLiteral);
    } else if (bigintegerLiteral instanceof ElementVariable) {
      return _convertExpression((ElementVariable)bigintegerLiteral);
    } else if (bigintegerLiteral instanceof StringLiteral) {
      return _convertExpression((StringLiteral)bigintegerLiteral);
    } else if (bigintegerLiteral instanceof ArithmeticComparisonExpression) {
      return _convertExpression((ArithmeticComparisonExpression)bigintegerLiteral);
    } else if (bigintegerLiteral instanceof BinaryLogicalExpression) {
      return _convertExpression((BinaryLogicalExpression)bigintegerLiteral);
    } else if (bigintegerLiteral instanceof EmptyListExpression) {
      return _convertExpression((EmptyListExpression)bigintegerLiteral);
    } else if (bigintegerLiteral instanceof ExpressionVariable) {
      return _convertExpression((ExpressionVariable)bigintegerLiteral);
    } else if (bigintegerLiteral instanceof IndexRangeAccessExpression) {
      return _convertExpression((IndexRangeAccessExpression)bigintegerLiteral);
    } else if (bigintegerLiteral instanceof IndexSimpleAccessExpression) {
      return _convertExpression((IndexSimpleAccessExpression)bigintegerLiteral);
    } else if (bigintegerLiteral instanceof Parameter) {
      return _convertExpression((Parameter)bigintegerLiteral);
    } else if (bigintegerLiteral instanceof ParameterComparableExpression) {
      return _convertExpression((ParameterComparableExpression)bigintegerLiteral);
    } else if (bigintegerLiteral instanceof UnaryGraphObjectLogicalExpression) {
      return _convertExpression((UnaryGraphObjectLogicalExpression)bigintegerLiteral);
    } else if (bigintegerLiteral instanceof UnaryLogicalExpression) {
      return _convertExpression((UnaryLogicalExpression)bigintegerLiteral);
    } else if (bigintegerLiteral instanceof CaseExpression) {
      return _convertExpression((CaseExpression)bigintegerLiteral);
    } else if (bigintegerLiteral instanceof FunctionExpression) {
      return _convertExpression((FunctionExpression)bigintegerLiteral);
    } else if (bigintegerLiteral instanceof ListExpression) {
      return _convertExpression((ListExpression)bigintegerLiteral);
    } else if (bigintegerLiteral instanceof NavigationDescriptor) {
      return _convertExpression((NavigationDescriptor)bigintegerLiteral);
    } else if (bigintegerLiteral instanceof VariableExpression) {
      return _convertExpression((VariableExpression)bigintegerLiteral);
    } else if (bigintegerLiteral == null) {
      return _convertExpression((Void)null);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(bigintegerLiteral).toString());
    }
  }
}
