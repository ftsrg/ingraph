package ingraph.relalg2tex.converters.elementconverters;

import ingraph.relalg2tex.converters.elementconverters.TupleConverter;
import ingraph.relalg2tex.converters.variableconverters.VariableNameConverter;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure2;
import relalg.Variable;

@SuppressWarnings("all")
public class SchemaConverter {
  @Extension
  private VariableNameConverter variableNameConverter = new VariableNameConverter();
  
  @Extension
  private TupleConverter tupleConverter = new TupleConverter();
  
  private boolean schemaIndices;
  
  public SchemaConverter(final boolean schemaIndices) {
    this.schemaIndices = schemaIndices;
  }
  
  public CharSequence convertSchema(final List<Variable> schema) {
    CharSequence _xblockexpression = null;
    {
      int _size = schema.size();
      final ArrayList<Object> list = new ArrayList<Object>(_size);
      final Procedure2<Variable, Integer> _function = (Variable element, Integer index) -> {
        CharSequence _convertVariable = this.variableNameConverter.convertVariable(element);
        list.add(_convertVariable);
      };
      IterableExtensions.<Variable>forEach(schema, _function);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("\\langle ");
      String _join = IterableExtensions.join(list, ", ");
      _builder.append(_join, "");
      _builder.append(" \\rangle");
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
  
  public CharSequence convertSchemaWithIndices(final List<Variable> schema) {
    return this.convertSchemaWithIndices(schema, null);
  }
  
  public CharSequence convertSchemaWithIndices(final List<Variable> schema, final List<Integer> tupleIndices) {
    CharSequence _xblockexpression = null;
    {
      int _size = schema.size();
      final ArrayList<Object> list = new ArrayList<Object>(_size);
      final Procedure2<Variable, Integer> _function = (Variable element, Integer index) -> {
        StringConcatenation _builder = new StringConcatenation();
        {
          if (this.schemaIndices) {
            _builder.append("{}_{");
            _builder.append(index, "");
            _builder.append("}^{");
            Integer _get = null;
            if (tupleIndices!=null) {
              _get=tupleIndices.get((index).intValue());
            }
            Object _convertTupleIndex = null;
            if (_get!=null) {
              _convertTupleIndex=this.tupleConverter.convertTupleIndex(_get);
            }
            _builder.append(_convertTupleIndex, "");
            _builder.append("}");
          }
        }
        _builder.newLineIfNotEmpty();
        _builder.append("\\var{");
        CharSequence _convertVariable = this.variableNameConverter.convertVariable(element);
        _builder.append(_convertVariable, "");
        _builder.append("}");
        _builder.newLineIfNotEmpty();
        list.add(_builder.toString());
      };
      IterableExtensions.<Variable>forEach(schema, _function);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("\\langle ");
      String _join = IterableExtensions.join(list, ", ");
      _builder.append(_join, "");
      _builder.append(" \\rangle");
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
}
