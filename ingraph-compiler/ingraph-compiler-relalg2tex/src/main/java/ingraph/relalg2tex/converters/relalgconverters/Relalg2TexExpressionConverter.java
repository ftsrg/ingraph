package ingraph.relalg2tex.converters.relalgconverters;

import ingraph.relalg2tex.config.RelalgConverterConfig;
import ingraph.relalg2tex.converters.relalgconverters.AbstractRelalg2TexConverter;
import java.util.Arrays;
import org.eclipse.xtend2.lib.StringConcatenation;
import relalg.BinaryOperator;
import relalg.NullaryOperator;
import relalg.Operator;
import relalg.UnaryOperator;

@SuppressWarnings("all")
public class Relalg2TexExpressionConverter extends AbstractRelalg2TexConverter {
  public Relalg2TexExpressionConverter() {
    super();
  }
  
  public Relalg2TexExpressionConverter(final RelalgConverterConfig config) {
    super(config);
    boolean _isTextualOperators = config.isTextualOperators();
    if (_isTextualOperators) {
      throw new UnsupportedOperationException("Textual operators are not supported for expression converters.");
    }
  }
  
  @Override
  public CharSequence convertBody(final Operator expression) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _isTextualOperators = this.config.isTextualOperators();
      if (_isTextualOperators) {
        _builder.append("\\togglefalse{textualoperators}");
      }
    }
    _builder.newLineIfNotEmpty();
    {
      boolean _isStandaloneDocument = this.config.isStandaloneDocument();
      if (_isStandaloneDocument) {
        _builder.append("$$");
      }
    }
    _builder.newLineIfNotEmpty();
    CharSequence _children = this.children(expression);
    _builder.append(_children, "");
    _builder.newLineIfNotEmpty();
    {
      boolean _isStandaloneDocument_1 = this.config.isStandaloneDocument();
      if (_isStandaloneDocument_1) {
        _builder.append("$$");
      }
    }
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  /**
   * children
   * 
   * we add newlines intentionally to allow the autobreak package
   * to break the expressions to multiple lines
   */
  protected CharSequence _children(final NullaryOperator op) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _operator = this.operator(op);
    _builder.append(_operator, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  protected CharSequence _children(final UnaryOperator op) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _includeOperator = this.includeOperator(op);
      if (_includeOperator) {
        CharSequence _operator = this.operator(op);
        _builder.append(_operator, "");
        {
          boolean _isParentheses = this.config.isParentheses();
          if (_isParentheses) {
            _builder.append("\\Big(");
          }
        }
      }
    }
    _builder.newLineIfNotEmpty();
    Operator _input = op.getInput();
    CharSequence _children = this.children(_input);
    _builder.append(_children, "");
    _builder.newLineIfNotEmpty();
    {
      boolean _includeOperator_1 = this.includeOperator(op);
      if (_includeOperator_1) {
        {
          boolean _isParentheses_1 = this.config.isParentheses();
          if (_isParentheses_1) {
            _builder.append("\\Big)");
          }
        }
      }
    }
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  protected CharSequence _children(final BinaryOperator op) {
    StringConcatenation _builder = new StringConcatenation();
    Operator _leftInput = op.getLeftInput();
    CharSequence _children = this.children(_leftInput);
    _builder.append(_children, "");
    _builder.newLineIfNotEmpty();
    CharSequence _operator = this.operator(op);
    _builder.append(_operator, "");
    _builder.newLineIfNotEmpty();
    Operator _rightInput = op.getRightInput();
    CharSequence _children_1 = this.children(_rightInput);
    _builder.append(_children_1, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence children(final Operator op) {
    if (op instanceof BinaryOperator) {
      return _children((BinaryOperator)op);
    } else if (op instanceof NullaryOperator) {
      return _children((NullaryOperator)op);
    } else if (op instanceof UnaryOperator) {
      return _children((UnaryOperator)op);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(op).toString());
    }
  }
}
