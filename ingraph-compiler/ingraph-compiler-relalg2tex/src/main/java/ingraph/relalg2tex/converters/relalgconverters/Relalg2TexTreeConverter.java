package ingraph.relalg2tex.converters.relalgconverters;

import ingraph.relalg.util.ContainerExtractor;
import ingraph.relalg2tex.config.RelalgConverterConfig;
import ingraph.relalg2tex.converters.elementconverters.SchemaConverter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import relalg.AbstractJoinOperator;
import relalg.BeamerOperator;
import relalg.BinaryOperator;
import relalg.Cardinality;
import relalg.NullaryOperator;
import relalg.Operator;
import relalg.RelalgContainer;
import relalg.UnaryOperator;
import relalg.Variable;

import java.util.Arrays;

@SuppressWarnings("all")
public class Relalg2TexTreeConverter extends AbstractRelalg2TexConverter {
  @Extension
  private SchemaConverter schemaConverter = new SchemaConverter(this.config.isSchemaIndices());

  @Extension
  private ContainerExtractor containerExtractor = new ContainerExtractor();

  public Relalg2TexTreeConverter() {
    super();
  }

  public Relalg2TexTreeConverter(final RelalgConverterConfig config) {
    super(config);
  }

  @Override
  public CharSequence convertBody(final Operator expression) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _isTextualOperators = this.config.isTextualOperators();
      if (_isTextualOperators) {
        _builder.append("\\toggletrue{textualoperators}");
      } else {
        _builder.append("\\togglefalse{textualoperators}");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("\\begin{forest} for tree={align=center}");
    _builder.newLine();
    CharSequence _node = this.toNode(expression);
    _builder.append(_node, "");
    _builder.newLineIfNotEmpty();
    _builder.append(";");
    _builder.newLine();
    _builder.append("\\end{forest}");
    _builder.newLine();
    return _builder;
  }

  /**
   * toNode
   */
  public CharSequence toNode(final Operator op) {
    CharSequence _xblockexpression = null;
    {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("[");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append("{");
      CharSequence _operator = this.operator(op);
      _builder.append(_operator, "\t\t\t\t");
      _builder.newLineIfNotEmpty();
      {
        boolean _isOmitSchema = this.config.isOmitSchema();
        boolean _not = (!_isOmitSchema);
        if (_not) {
          {
            RelalgContainer _extractContainer = this.containerExtractor.extractContainer(op);
            boolean _isExternalSchemaInferred = _extractContainer.isExternalSchemaInferred();
            if (_isExternalSchemaInferred) {
              _builder.append("\t\t\t\t");
              _builder.append("\\\\ \\footnotesize");
              _builder.newLine();
              _builder.append("\t\t\t\t");
              _builder.append("$\\color{externalschemacolor} ");
              EList<Variable> _externalSchema = op.getExternalSchema();
              CharSequence _convertSchema = this.schemaConverter.convertSchema(_externalSchema);
              _builder.append(_convertSchema, "\t\t\t\t");
              _builder.append(" $");
              _builder.newLineIfNotEmpty();
            }
          }
          {
            RelalgContainer _extractContainer_1 = this.containerExtractor.extractContainer(op);
            boolean _isExtraVariablesInferred = _extractContainer_1.isExtraVariablesInferred();
            if (_isExtraVariablesInferred) {
              _builder.append("\t\t\t\t");
              _builder.append("\\\\ \\footnotesize");
              _builder.newLine();
              _builder.append("\t\t\t\t");
              _builder.append("$\\color{extravariablescolor} ");
              EList<Variable> _extraVariables = op.getExtraVariables();
              CharSequence _convertSchema_1 = this.schemaConverter.convertSchema(_extraVariables);
              _builder.append(_convertSchema_1, "\t\t\t\t");
              _builder.append(" $");
              _builder.newLineIfNotEmpty();
            }
          }
          {
            RelalgContainer _extractContainer_2 = this.containerExtractor.extractContainer(op);
            boolean _isInternalSchemaInferred = _extractContainer_2.isInternalSchemaInferred();
            if (_isInternalSchemaInferred) {
              _builder.append("\t\t\t\t");
              _builder.append("\\\\ \\footnotesize");
              _builder.newLine();
              {
                if ((op instanceof BeamerOperator)) {
                  _builder.append("\t\t\t\t");
                  _builder.append("\t");
                  _builder.append("$\\color{internalschemacolor} ");
                  EList<Variable> _internalSchema = ((BeamerOperator)op).getInternalSchema();
                  EList<Integer> _tupleIndices = ((BeamerOperator)op).getTupleIndices();
                  CharSequence _convertSchemaWithIndices = this.schemaConverter.convertSchemaWithIndices(_internalSchema, _tupleIndices);
                  _builder.append(_convertSchemaWithIndices, "\t\t\t\t\t");
                  _builder.append("$");
                  _builder.newLineIfNotEmpty();
                } else {
                  _builder.append("\t\t\t\t");
                  _builder.append("\t");
                  _builder.append("$\\color{internalschemacolor} ");
                  EList<Variable> _internalSchema_1 = op.getInternalSchema();
                  CharSequence _convertSchemaWithIndices_1 = this.schemaConverter.convertSchemaWithIndices(_internalSchema_1);
                  _builder.append(_convertSchemaWithIndices_1, "\t\t\t\t\t");
                  _builder.append("$");
                  _builder.newLineIfNotEmpty();
                }
              }
            }
          }
        }
      }
      {
        if ((((op instanceof AbstractJoinOperator) && this.containerExtractor.extractContainer(op).isInternalSchemaInferred()) && this.config.isIncludeCommonVariables())) {
          _builder.append("\t\t\t\t");
          _builder.append("\\\\ \\footnotesize");
          _builder.newLine();
          _builder.append("\t\t\t\t");
          _builder.append("$\\color{orange}");
          _builder.newLine();
          _builder.append("\t\t\t\t");
          _builder.append("\\langle \\var{");
          EList<Integer> _leftMask = ((AbstractJoinOperator) op).getLeftMask();
          String _join = IterableExtensions.join(_leftMask, ", ");
          _builder.append(_join, "\t\t\t\t");
          _builder.append("} \\rangle :");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t\t\t");
          _builder.append("\\langle \\var{");
          EList<Integer> _rightMask = ((AbstractJoinOperator) op).getRightMask();
          String _join_1 = IterableExtensions.join(_rightMask, ", ");
          _builder.append(_join_1, "\t\t\t\t");
          _builder.append("} \\rangle$");
          _builder.newLineIfNotEmpty();
        }
      }
      {
        if ((this.config.isIncludeCardinality() && (op.getCardinality() != null))) {
          _builder.append("\t\t\t\t");
          _builder.append("\\\\ \\footnotesize \\# ");
          Cardinality _cardinality = op.getCardinality();
          String _formatCardinality = this.formatCardinality(_cardinality);
          _builder.append(_formatCardinality, "\t\t\t\t");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("\t\t\t\t");
      _builder.append("}");
      final String start = _builder.toString();
      StringConcatenation _builder_1 = new StringConcatenation();
      {
        if ((op instanceof NullaryOperator)) {
          _builder_1.append(",for tree={nullarynodecolor,densely dashed}");
        }
      }
      _builder_1.append("]");
      _builder_1.newLineIfNotEmpty();
      final String end = _builder_1.toString();
      StringConcatenation _builder_2 = new StringConcatenation();
      {
        boolean _includeOperator = this.includeOperator(op);
        if (_includeOperator) {
          _builder_2.append(start, "");
        }
      }
      CharSequence _children = null;
      if (op!=null) {
        _children=this.children(op);
      }
      _builder_2.append(_children, "");
      {
        boolean _includeOperator_1 = this.includeOperator(op);
        if (_includeOperator_1) {
          _builder_2.append(end, "");
        }
      }
      _xblockexpression = _builder_2;
    }
    return _xblockexpression;
  }

  /**
   * children
   */
  protected CharSequence _children(final NullaryOperator op) {
    StringConcatenation _builder = new StringConcatenation();
    return _builder;
  }

  protected CharSequence _children(final UnaryOperator op) {
    StringConcatenation _builder = new StringConcatenation();
    Operator _input = op.getInput();
    CharSequence _node = null;
    if (_input!=null) {
      _node=this.toNode(_input);
    }
    _builder.append(_node, "");
    return _builder;
  }

  protected CharSequence _children(final BinaryOperator op) {
    StringConcatenation _builder = new StringConcatenation();
    Operator _leftInput = op.getLeftInput();
    CharSequence _node = this.toNode(_leftInput);
    _builder.append(_node, "");
    _builder.newLineIfNotEmpty();
    Operator _rightInput = op.getRightInput();
    CharSequence _node_1 = this.toNode(_rightInput);
    _builder.append(_node_1, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }

  public String formatCardinality(final Cardinality cardinality) {
    double _value = cardinality.getValue();
    return String.format("%.02f", Double.valueOf(_value));
  }

  /**
   * operator
   */
  @Override
  public CharSequence operator(final Operator op) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("$");
    CharSequence _convertOperator = null;
    if (op!=null) {
      _convertOperator=this.operatorToTex.convertOperator(op);
    }
    _builder.append(_convertOperator, "");
    _builder.append("$");
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
