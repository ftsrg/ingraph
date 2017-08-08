package ingraph.relalg2tex.converters.operatorconverters;

import ingraph.relalg2tex.config.RelalgConverterConfig;
import ingraph.relalg2tex.converters.elementconverters.StringEscaper;
import ingraph.relalg2tex.converters.variableconverters.VariableNameConverter;
import java.util.Arrays;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import relalg.AbstractJoinOperator;
import relalg.AntiJoinOperator;
import relalg.BinaryOperator;
import relalg.EdgeListVariable;
import relalg.JoinOperator;
import relalg.LeftOuterJoinOperator;
import relalg.TransitiveClosureJoinOperator;
import relalg.UnionOperator;
import relalg.Variable;

@SuppressWarnings("all")
public class BinaryOperatorConverter {
  private final RelalgConverterConfig config;
  
  @Extension
  private StringEscaper stringEscaper = new StringEscaper();
  
  @Extension
  private VariableNameConverter variableNameConverter = new VariableNameConverter();
  
  public BinaryOperatorConverter(final RelalgConverterConfig config) {
    this.config = config;
  }
  
  protected CharSequence _convertBinaryOperator(final AbstractJoinOperator op) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _joinOperator = this.joinOperator(op);
    _builder.append(_joinOperator, "");
    StringConcatenation _builder_1 = new StringConcatenation();
    {
      boolean _isIncludeCommonVariables = this.config.isIncludeCommonVariables();
      if (_isIncludeCommonVariables) {
        _builder_1.append(" \\{");
        EList<Variable> _commonVariables = op.getCommonVariables();
        final Function1<Variable, String> _function = (Variable it) -> {
          StringConcatenation _builder_2 = new StringConcatenation();
          _builder_2.append("\\var{");
          CharSequence _escapedName = this.stringEscaper.escapedName(it);
          _builder_2.append(_escapedName, "");
          _builder_2.append("}");
          return _builder_2.toString();
        };
        List<String> _map = ListExtensions.<Variable, String>map(_commonVariables, _function);
        String _join = IterableExtensions.join(_map, ", ");
        _builder_1.append(_join, "");
        _builder_1.append("\\}");
      }
    }
    return (_builder.toString() + _builder_1);
  }
  
  protected CharSequence _convertBinaryOperator(final UnionOperator op) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("union");
    return _builder;
  }
  
  protected CharSequence _joinOperator(final JoinOperator op) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("join");
    return _builder;
  }
  
  protected CharSequence _joinOperator(final TransitiveClosureJoinOperator op) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("transitivejoin_{");
    EdgeListVariable _edgeListVariable = op.getEdgeListVariable();
    CharSequence _convertVariable = this.variableNameConverter.convertVariable(_edgeListVariable);
    _builder.append(_convertVariable, "");
    _builder.append("}");
    return _builder;
  }
  
  protected CharSequence _joinOperator(final AntiJoinOperator op) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("antijoin");
    return _builder;
  }
  
  protected CharSequence _joinOperator(final LeftOuterJoinOperator op) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("leftouterjoin");
    return _builder;
  }
  
  public CharSequence convertBinaryOperator(final BinaryOperator op) {
    if (op instanceof UnionOperator) {
      return _convertBinaryOperator((UnionOperator)op);
    } else if (op instanceof AbstractJoinOperator) {
      return _convertBinaryOperator((AbstractJoinOperator)op);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(op).toString());
    }
  }
  
  public CharSequence joinOperator(final AbstractJoinOperator op) {
    if (op instanceof JoinOperator) {
      return _joinOperator((JoinOperator)op);
    } else if (op instanceof LeftOuterJoinOperator) {
      return _joinOperator((LeftOuterJoinOperator)op);
    } else if (op instanceof TransitiveClosureJoinOperator) {
      return _joinOperator((TransitiveClosureJoinOperator)op);
    } else if (op instanceof AntiJoinOperator) {
      return _joinOperator((AntiJoinOperator)op);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(op).toString());
    }
  }
}
