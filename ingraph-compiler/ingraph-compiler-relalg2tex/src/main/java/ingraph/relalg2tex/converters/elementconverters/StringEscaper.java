package ingraph.relalg2tex.converters.elementconverters;

import org.eclipse.xtend2.lib.StringConcatenation;
import relalg.NamedElement;

@SuppressWarnings("all")
public class StringEscaper {
  public String escape(final String s) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\b");
    String _replace = s.replace("\b", _builder);
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("\\f");
    String _replace_1 = _replace.replace("\f", _builder_1);
    StringConcatenation _builder_2 = new StringConcatenation();
    _builder_2.append("\\n");
    String _replace_2 = _replace_1.replace("\n", _builder_2);
    StringConcatenation _builder_3 = new StringConcatenation();
    _builder_3.append("\\r");
    String _replace_3 = _replace_2.replace("\r", _builder_3);
    StringConcatenation _builder_4 = new StringConcatenation();
    _builder_4.append("\\t");
    String _replace_4 = _replace_3.replace("\t", _builder_4);
    StringConcatenation _builder_5 = new StringConcatenation();
    _builder_5.append("{");
    StringConcatenation _builder_6 = new StringConcatenation();
    _builder_6.append("\\string{");
    String _replace_5 = _replace_4.replace(_builder_5, _builder_6);
    StringConcatenation _builder_7 = new StringConcatenation();
    _builder_7.append("}");
    StringConcatenation _builder_8 = new StringConcatenation();
    _builder_8.append("\\string}");
    String _replace_6 = _replace_5.replace(_builder_7, _builder_8);
    StringConcatenation _builder_9 = new StringConcatenation();
    _builder_9.append("\\\\(?!string[{}])");
    StringConcatenation _builder_10 = new StringConcatenation();
    _builder_10.append("\\\\backslash{}");
    String _replaceAll = _replace_6.replaceAll(_builder_9.toString(), _builder_10.toString());
    StringConcatenation _builder_11 = new StringConcatenation();
    _builder_11.append("_");
    StringConcatenation _builder_12 = new StringConcatenation();
    _builder_12.append("\\_");
    String _replace_7 = _replaceAll.replace(_builder_11, _builder_12);
    StringConcatenation _builder_13 = new StringConcatenation();
    _builder_13.append(" ");
    StringConcatenation _builder_14 = new StringConcatenation();
    _builder_14.append("\\ ");
    String _replace_8 = _replace_7.replace(_builder_13, _builder_14);
    StringConcatenation _builder_15 = new StringConcatenation();
    _builder_15.append("[");
    StringConcatenation _builder_16 = new StringConcatenation();
    _builder_16.append("{[}");
    String _replace_9 = _replace_8.replace(_builder_15, _builder_16);
    StringConcatenation _builder_17 = new StringConcatenation();
    _builder_17.append("]");
    StringConcatenation _builder_18 = new StringConcatenation();
    _builder_18.append("{]}");
    return _replace_9.replace(_builder_17, _builder_18);
  }
  
  public CharSequence escapedName(final NamedElement element) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = null;
    if (element!=null) {
      _name=element.getName();
    }
    String _escape = null;
    if (_name!=null) {
      _escape=this.escape(_name);
    }
    _builder.append(_escape, "");
    return _builder;
  }
}
