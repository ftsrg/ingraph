package ingraph.relalg2tex.converters.elementconverters;

import ingraph.relalg2tex.converters.elementconverters.ExpressionConverter;
import ingraph.relalg2tex.converters.elementconverters.StringEscaper;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import relalg.AbstractEdgeVariable;
import relalg.Direction;
import relalg.Expression;
import relalg.MaxHops;
import relalg.MaxHopsType;
import relalg.OrderDirection;
import relalg.SortEntry;

@SuppressWarnings("all")
public class MiscConverters {
  @Extension
  private StringEscaper stringEscaper = new StringEscaper();
  
  @Extension
  private ExpressionConverter expressionConverter = new ExpressionConverter();
  
  public CharSequence convertDirection(final Direction direction) {
    CharSequence _switchResult = null;
    if (direction != null) {
      switch (direction) {
        case BOTH:
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("both");
          _switchResult = _builder;
          break;
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
        default:
          StringConcatenation _builder_3 = new StringConcatenation();
          _builder_3.append("Direction ");
          _builder_3.append(direction, "");
          _builder_3.append(" not supported.");
          throw new UnsupportedOperationException(_builder_3.toString());
      }
    } else {
      StringConcatenation _builder_3 = new StringConcatenation();
      _builder_3.append("Direction ");
      _builder_3.append(direction, "");
      _builder_3.append(" not supported.");
      throw new UnsupportedOperationException(_builder_3.toString());
    }
    return _switchResult;
  }
  
  public String convertConditionString(final String s) {
    String _escape = this.stringEscaper.escape(s);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(" ");
    _builder.append("XOR ");
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append(" ");
    _builder_1.append("\\\\lxor ");
    String _replaceAll = _escape.replaceAll(_builder.toString(), _builder_1.toString());
    StringConcatenation _builder_2 = new StringConcatenation();
    _builder_2.append(" ");
    _builder_2.append("AND ");
    StringConcatenation _builder_3 = new StringConcatenation();
    _builder_3.append(" ");
    _builder_3.append("\\\\land ");
    String _replaceAll_1 = _replaceAll.replaceAll(_builder_2.toString(), _builder_3.toString());
    StringConcatenation _builder_4 = new StringConcatenation();
    _builder_4.append(" ");
    _builder_4.append("OR ");
    StringConcatenation _builder_5 = new StringConcatenation();
    _builder_5.append(" ");
    _builder_5.append("\\\\lor ");
    String _replaceAll_2 = _replaceAll_1.replaceAll(_builder_4.toString(), _builder_5.toString());
    StringConcatenation _builder_6 = new StringConcatenation();
    _builder_6.append(" ");
    StringConcatenation _builder_7 = new StringConcatenation();
    _builder_7.append("\\ ");
    return _replaceAll_2.replaceAll(_builder_6.toString(), _builder_7.toString());
  }
  
  public CharSequence hopsToString(final MaxHops hops) {
    CharSequence _switchResult = null;
    MaxHopsType _maxHopsType = hops.getMaxHopsType();
    if (_maxHopsType != null) {
      switch (_maxHopsType) {
        case LIMITED:
          int _hops = hops.getHops();
          _switchResult = Integer.valueOf(_hops).toString();
          break;
        case UNLIMITED:
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("\\infty");
          _switchResult = _builder;
          break;
        default:
          StringConcatenation _builder_1 = new StringConcatenation();
          _builder_1.append("MaxHopsType ");
          MaxHopsType _maxHopsType_1 = hops.getMaxHopsType();
          _builder_1.append(_maxHopsType_1, "");
          _builder_1.append(" not supported.");
          throw new UnsupportedOperationException(_builder_1.toString());
      }
    } else {
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("MaxHopsType ");
      MaxHopsType _maxHopsType_1 = hops.getMaxHopsType();
      _builder_1.append(_maxHopsType_1, "");
      _builder_1.append(" not supported.");
      throw new UnsupportedOperationException(_builder_1.toString());
    }
    return _switchResult;
  }
  
  public CharSequence sortEntryToTex(final SortEntry entry) {
    CharSequence _xblockexpression = null;
    {
      String _switchResult = null;
      OrderDirection _direction = entry.getDirection();
      if (_direction != null) {
        switch (_direction) {
          case ASCENDING:
            _switchResult = "asc";
            break;
          case DESCENDING:
            _switchResult = "desc";
            break;
          default:
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("SortEntry ");
            OrderDirection _direction_1 = entry.getDirection();
            _builder.append(_direction_1, "");
            _builder.append(" not supported.");
            throw new UnsupportedOperationException(_builder.toString());
        }
      } else {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("SortEntry ");
        OrderDirection _direction_1 = entry.getDirection();
        _builder.append(_direction_1, "");
        _builder.append(" not supported.");
        throw new UnsupportedOperationException(_builder.toString());
      }
      final String direction = _switchResult;
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("\\");
      _builder_1.append(direction, "");
      _builder_1.append(" ");
      Expression _expression = entry.getExpression();
      CharSequence _convertExpression = this.expressionConverter.convertExpression(_expression);
      _builder_1.append(_convertExpression, "");
      _xblockexpression = _builder_1;
    }
    return _xblockexpression;
  }
  
  public CharSequence edgeVariableList(final List<AbstractEdgeVariable> edgeVariables) {
    StringConcatenation _builder = new StringConcatenation();
    final Function1<AbstractEdgeVariable, String> _function = (AbstractEdgeVariable it) -> {
      CharSequence _escapedName = this.stringEscaper.escapedName(it);
      String _plus = ("\\var{" + _escapedName);
      return (_plus + "}");
    };
    List<String> _map = ListExtensions.<AbstractEdgeVariable, String>map(edgeVariables, _function);
    String _join = IterableExtensions.join(_map, ", ");
    _builder.append(_join, "");
    return _builder;
  }
}
