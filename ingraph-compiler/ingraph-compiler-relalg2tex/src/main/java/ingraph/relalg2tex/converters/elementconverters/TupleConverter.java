package ingraph.relalg2tex.converters.elementconverters;

import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class TupleConverter {
  public CharSequence convertTuple(final List<Integer> tuple) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("[ \\var{");
    final Function1<Integer, Object> _function = (Integer it) -> {
      Object _xifexpression = null;
      if (((it).intValue() == (-1))) {
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append("\\#");
        _xifexpression = _builder_1.toString();
      } else {
        _xifexpression = it;
      }
      return ((Object)_xifexpression);
    };
    List<Object> _map = ListExtensions.<Integer, Object>map(tuple, _function);
    String _join = IterableExtensions.join(_map, ", ");
    _builder.append(_join, "");
    _builder.append("} ]");
    return _builder;
  }
  
  public Object convertTupleIndex(final Integer i) {
    Object _xifexpression = null;
    if (((i).intValue() == (-1))) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("\\#");
      _xifexpression = _builder;
    } else {
      _xifexpression = i;
    }
    return _xifexpression;
  }
}
