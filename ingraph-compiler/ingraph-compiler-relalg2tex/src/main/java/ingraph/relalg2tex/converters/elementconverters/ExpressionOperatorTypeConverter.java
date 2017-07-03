package ingraph.relalg2tex.converters.elementconverters;

import ingraph.relalg2tex.constants.RelNullConstants;
import org.eclipse.xtend2.lib.StringConcatenation;
import relalg.ArithmeticComparisonOperatorType;
import relalg.BinaryArithmeticOperatorType;
import relalg.BinaryLogicalOperatorType;
import relalg.UnaryArithmeticOperatorType;
import relalg.UnaryGraphObjectLogicalOperatorType;
import relalg.UnaryLogicalOperatorType;

@SuppressWarnings("all")
public class ExpressionOperatorTypeConverter {
  public CharSequence convertOperatorType(final ArithmeticComparisonOperatorType op) {
    CharSequence _switchResult = null;
    if (op != null) {
      switch (op) {
        case EQUAL_TO:
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("=");
          _switchResult = _builder;
          break;
        case GREATER_THAN:
          StringConcatenation _builder_1 = new StringConcatenation();
          _builder_1.append(">");
          _switchResult = _builder_1;
          break;
        case GREATER_THAN_OR_EQUAL:
          StringConcatenation _builder_2 = new StringConcatenation();
          _builder_2.append("\\geq");
          _switchResult = _builder_2;
          break;
        case LESS_THAN:
          StringConcatenation _builder_3 = new StringConcatenation();
          _builder_3.append("<");
          _switchResult = _builder_3;
          break;
        case LESS_THAN_OR_EQUAL:
          StringConcatenation _builder_4 = new StringConcatenation();
          _builder_4.append("\\leq");
          _switchResult = _builder_4;
          break;
        case NOT_EQUAL_TO:
          StringConcatenation _builder_5 = new StringConcatenation();
          _builder_5.append("\\neq");
          _switchResult = _builder_5;
          break;
        default:
          StringConcatenation _builder_6 = new StringConcatenation();
          _builder_6.append("SortEntry ");
          _builder_6.append(op, "");
          _builder_6.append(" not supported.");
          throw new UnsupportedOperationException(_builder_6.toString());
      }
    } else {
      StringConcatenation _builder_6 = new StringConcatenation();
      _builder_6.append("SortEntry ");
      _builder_6.append(op, "");
      _builder_6.append(" not supported.");
      throw new UnsupportedOperationException(_builder_6.toString());
    }
    return _switchResult;
  }
  
  public CharSequence convert(final BinaryLogicalOperatorType op) {
    CharSequence _switchResult = null;
    if (op != null) {
      switch (op) {
        case AND:
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("\\land");
          _switchResult = _builder;
          break;
        case OR:
          StringConcatenation _builder_1 = new StringConcatenation();
          _builder_1.append("\\lor");
          _switchResult = _builder_1;
          break;
        case XOR:
          StringConcatenation _builder_2 = new StringConcatenation();
          _builder_2.append("\\lxor");
          _switchResult = _builder_2;
          break;
        default:
          StringConcatenation _builder_3 = new StringConcatenation();
          _builder_3.append("BinaryLogicalOperator ");
          _builder_3.append(op, "");
          _builder_3.append(" not supported.");
          throw new UnsupportedOperationException(_builder_3.toString());
      }
    } else {
      StringConcatenation _builder_3 = new StringConcatenation();
      _builder_3.append("BinaryLogicalOperator ");
      _builder_3.append(op, "");
      _builder_3.append(" not supported.");
      throw new UnsupportedOperationException(_builder_3.toString());
    }
    return _switchResult;
  }
  
  public CharSequence convert(final UnaryLogicalOperatorType op) {
    CharSequence _switchResult = null;
    if (op != null) {
      switch (op) {
        case NOT:
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("\\neg");
          _switchResult = _builder;
          break;
        case IS_NOT_NULL:
          _switchResult = RelNullConstants.isNotNull;
          break;
        case IS_NULL:
          _switchResult = RelNullConstants.isNull;
          break;
        default:
          StringConcatenation _builder_1 = new StringConcatenation();
          _builder_1.append("UnaryLogicalOperator ");
          _builder_1.append(op, "");
          _builder_1.append(" not supported.");
          throw new UnsupportedOperationException(_builder_1.toString());
      }
    } else {
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("UnaryLogicalOperator ");
      _builder_1.append(op, "");
      _builder_1.append(" not supported.");
      throw new UnsupportedOperationException(_builder_1.toString());
    }
    return _switchResult;
  }
  
  public String convert(final UnaryGraphObjectLogicalOperatorType op) {
    String _switchResult = null;
    if (op != null) {
      switch (op) {
        case IS_NOT_NULL:
          _switchResult = RelNullConstants.isNotNull;
          break;
        case IS_NULL:
          _switchResult = RelNullConstants.isNull;
          break;
        default:
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("UnaryNodeLogicalOperator ");
          _builder.append(op, "");
          _builder.append(" not supported.");
          throw new UnsupportedOperationException(_builder.toString());
      }
    } else {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("UnaryNodeLogicalOperator ");
      _builder.append(op, "");
      _builder.append(" not supported.");
      throw new UnsupportedOperationException(_builder.toString());
    }
    return _switchResult;
  }
  
  public CharSequence convert(final BinaryArithmeticOperatorType op) {
    CharSequence _switchResult = null;
    if (op != null) {
      switch (op) {
        case DIVISION:
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("/");
          _switchResult = _builder;
          break;
        case MINUS:
          StringConcatenation _builder_1 = new StringConcatenation();
          _builder_1.append("-");
          _switchResult = _builder_1;
          break;
        case MOD:
          StringConcatenation _builder_2 = new StringConcatenation();
          _builder_2.append("\\mod");
          _switchResult = _builder_2;
          break;
        case MULTIPLICATION:
          StringConcatenation _builder_3 = new StringConcatenation();
          _builder_3.append("\\cdot");
          _switchResult = _builder_3;
          break;
        case PLUS:
          StringConcatenation _builder_4 = new StringConcatenation();
          _builder_4.append("+");
          _switchResult = _builder_4;
          break;
        case POWER:
          StringConcatenation _builder_5 = new StringConcatenation();
          _builder_5.append("^");
          _switchResult = _builder_5;
          break;
        default:
          StringConcatenation _builder_6 = new StringConcatenation();
          _builder_6.append("BinaryArithmeticOperator ");
          _builder_6.append(op, "");
          _builder_6.append(" not supported.");
          throw new UnsupportedOperationException(_builder_6.toString());
      }
    } else {
      StringConcatenation _builder_6 = new StringConcatenation();
      _builder_6.append("BinaryArithmeticOperator ");
      _builder_6.append(op, "");
      _builder_6.append(" not supported.");
      throw new UnsupportedOperationException(_builder_6.toString());
    }
    return _switchResult;
  }
  
  public CharSequence convert(final UnaryArithmeticOperatorType op) {
    CharSequence _switchResult = null;
    if (op != null) {
      switch (op) {
        case MINUS:
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("-");
          _switchResult = _builder;
          break;
        case PLUS:
          StringConcatenation _builder_1 = new StringConcatenation();
          _switchResult = _builder_1;
          break;
        default:
          StringConcatenation _builder_2 = new StringConcatenation();
          _builder_2.append("UnaryArithmeticOperator ");
          _builder_2.append(op, "");
          _builder_2.append(" not supported.");
          throw new UnsupportedOperationException(_builder_2.toString());
      }
    } else {
      StringConcatenation _builder_2 = new StringConcatenation();
      _builder_2.append("UnaryArithmeticOperator ");
      _builder_2.append(op, "");
      _builder_2.append(" not supported.");
      throw new UnsupportedOperationException(_builder_2.toString());
    }
    return _switchResult;
  }
}
