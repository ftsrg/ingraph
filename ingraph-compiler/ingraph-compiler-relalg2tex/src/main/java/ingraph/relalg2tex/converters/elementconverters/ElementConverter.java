package ingraph.relalg2tex.converters.elementconverters;

import com.google.common.base.Objects;
import ingraph.relalg2tex.converters.elementconverters.StringEscaper;
import java.util.Arrays;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import relalg.AbstractEdgeVariable;
import relalg.EdgeLabelSet;
import relalg.ElementVariable;
import relalg.Label;
import relalg.LabelSet;
import relalg.LabelSetStatus;
import relalg.VertexLabelSet;
import relalg.VertexVariable;

/**
 * Convert ElementVariable to string, including labels.
 */
@SuppressWarnings("all")
public class ElementConverter {
  @Extension
  private StringEscaper stringEscaper = new StringEscaper();
  
  protected String _convertElement(final VertexVariable variable) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("{");
    CharSequence _escapedName = this.stringEscaper.escapedName(variable);
    _builder.append(_escapedName, "");
    _builder.append("}{");
    VertexLabelSet _vertexLabelSet = variable.getVertexLabelSet();
    String _convertVertexLabelSet = this.convertVertexLabelSet(_vertexLabelSet);
    String _plus = (_builder.toString() + _convertVertexLabelSet);
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("}");
    return (_plus + _builder_1);
  }
  
  protected String _convertElement(final AbstractEdgeVariable variable) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("{");
    CharSequence _escapedName = this.stringEscaper.escapedName(variable);
    _builder.append(_escapedName, "");
    _builder.append("}{");
    EdgeLabelSet _edgeLabelSet = variable.getEdgeLabelSet();
    String _convertEdgeLabelSet = this.convertEdgeLabelSet(_edgeLabelSet);
    String _plus = (_builder.toString() + _convertEdgeLabelSet);
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("}");
    return (_plus + _builder_1);
  }
  
  public String convertVertexLabelSet(final VertexLabelSet ls) {
    return this.convertLabelSet(ls, " \\land ");
  }
  
  public String convertEdgeLabelSet(final EdgeLabelSet ls) {
    return this.convertLabelSet(ls, " \\lor ");
  }
  
  public String convertLabelSet(final LabelSet ls, final String separator) {
    String _switchResult = null;
    LabelSetStatus _status = null;
    if (ls!=null) {
      _status=ls.getStatus();
    }
    boolean _matched = false;
    if (Objects.equal(_status, LabelSetStatus.CONTRADICTING)) {
      _matched=true;
      _switchResult = "CONTRADICTING~LABEL~CONSTRAINTS";
    }
    if (!_matched) {
      if (Objects.equal(_status, null)) {
        _matched=true;
        _switchResult = "";
      }
    }
    if (!_matched) {
      EList<? extends Label> _switchResult_1 = null;
      boolean _matched_1 = false;
      if (ls instanceof VertexLabelSet) {
        _matched_1=true;
        _switchResult_1 = ((VertexLabelSet)ls).getVertexLabels();
      }
      if (!_matched_1) {
        if (ls instanceof EdgeLabelSet) {
          _matched_1=true;
          _switchResult_1 = ((EdgeLabelSet)ls).getEdgeLabels();
        }
      }
      final Function1<Label, CharSequence> _function = (Label it) -> {
        return this.stringEscaper.escapedName(it);
      };
      List<CharSequence> _map = ListExtensions.map(_switchResult_1, _function);
      _switchResult = IterableExtensions.join(_map, separator);
    }
    return _switchResult;
  }
  
  public String convertElement(final ElementVariable variable) {
    if (variable instanceof AbstractEdgeVariable) {
      return _convertElement((AbstractEdgeVariable)variable);
    } else if (variable instanceof VertexVariable) {
      return _convertElement((VertexVariable)variable);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(variable).toString());
    }
  }
}
