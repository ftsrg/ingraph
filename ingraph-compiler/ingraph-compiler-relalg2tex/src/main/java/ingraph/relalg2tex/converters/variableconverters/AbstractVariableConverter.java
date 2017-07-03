package ingraph.relalg2tex.converters.variableconverters;

import ingraph.relalg.expressions.ExpressionUnwrapper;
import ingraph.relalg2tex.converters.elementconverters.ExpressionConverter;
import ingraph.relalg2tex.converters.elementconverters.MiscConverters;
import ingraph.relalg2tex.converters.elementconverters.StringEscaper;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import relalg.AttributeVariable;
import relalg.EdgeListVariable;
import relalg.ElementVariable;
import relalg.Expression;
import relalg.ExpressionVariable;
import relalg.ListVariable;
import relalg.MaxHops;
import relalg.PropertyList;
import relalg.Variable;

@SuppressWarnings("all")
public abstract class AbstractVariableConverter {
  @Extension
  protected StringEscaper stringEscaper = new StringEscaper();
  
  @Extension
  private MiscConverters miscConverters = new MiscConverters();
  
  @Extension
  private ExpressionConverter expressionConverter = new ExpressionConverter();
  
  protected CharSequence _convertVariable(final ElementVariable variable) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\var{");
    CharSequence _escapedName = this.stringEscaper.escapedName(variable);
    _builder.append(_escapedName, "");
    _builder.append("} ");
    {
      PropertyList _properties = variable.getProperties();
      boolean _tripleNotEquals = (_properties != null);
      if (_tripleNotEquals) {
        PropertyList _properties_1 = variable.getProperties();
        CharSequence _convertProperties = this.convertProperties(_properties_1);
        _builder.append(_convertProperties, "");
      }
    }
    return _builder;
  }
  
  protected CharSequence _convertVariable(final EdgeListVariable variable) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\var{[");
    CharSequence _escapedName = this.stringEscaper.escapedName(variable);
    _builder.append(_escapedName, "");
    _builder.append("]}_{");
    int _minHops = variable.getMinHops();
    _builder.append(_minHops, "");
    _builder.append("}^{");
    MaxHops _maxHops = variable.getMaxHops();
    CharSequence _hopsToString = this.miscConverters.hopsToString(_maxHops);
    _builder.append(_hopsToString, "");
    _builder.append("}");
    return _builder;
  }
  
  protected CharSequence _convertVariable(final AttributeVariable variable) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\var{");
    Variable _baseVariable = variable.getBaseVariable();
    CharSequence _escapedName = this.stringEscaper.escapedName(_baseVariable);
    _builder.append(_escapedName, "");
    _builder.append(".");
    CharSequence _escapedName_1 = this.stringEscaper.escapedName(variable);
    _builder.append(_escapedName_1, "");
    _builder.append("}");
    return _builder;
  }
  
  protected CharSequence _convertVariable(final ListVariable variable) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\var{");
    CharSequence _escapedName = this.stringEscaper.escapedName(variable);
    _builder.append(_escapedName, "");
    _builder.append("}");
    return _builder;
  }
  
  public CharSequence convertProperties(final PropertyList propertyList) {
    CharSequence _xifexpression = null;
    EMap<String, Expression> _entries = propertyList.getEntries();
    boolean _isEmpty = _entries.isEmpty();
    if (_isEmpty) {
      _xifexpression = "";
    } else {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("_{\\{ ");
      EMap<String, Expression> _entries_1 = propertyList.getEntries();
      final Function1<Map.Entry<String, Expression>, CharSequence> _function = (Map.Entry<String, Expression> it) -> {
        return this.convertPropertyListEntry(it);
      };
      List<CharSequence> _map = ListExtensions.<Map.Entry<String, Expression>, CharSequence>map(_entries_1, _function);
      String _join = IterableExtensions.join(_map, ", ");
      _builder.append(_join, "");
      _builder.append(" \\}}");
      _xifexpression = _builder;
    }
    return _xifexpression;
  }
  
  public CharSequence convertPropertyListEntry(final Map.Entry<String, Expression> entry) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\atom{");
    String _key = entry.getKey();
    _builder.append(_key, "");
    _builder.append("}: ");
    Expression _value = entry.getValue();
    CharSequence _convertExpression = this.expressionConverter.convertExpression(_value);
    _builder.append(_convertExpression, "");
    return _builder;
  }
  
  public Variable unwrap(final ExpressionVariable ev) {
    return ExpressionUnwrapper.extractExpressionVariable(ev);
  }
  
  public CharSequence convertVariable(final Variable variable) {
    if (variable instanceof EdgeListVariable) {
      return _convertVariable((EdgeListVariable)variable);
    } else if (variable instanceof AttributeVariable) {
      return _convertVariable((AttributeVariable)variable);
    } else if (variable instanceof ElementVariable) {
      return _convertVariable((ElementVariable)variable);
    } else if (variable instanceof ListVariable) {
      return _convertVariable((ListVariable)variable);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(variable).toString());
    }
  }
}
