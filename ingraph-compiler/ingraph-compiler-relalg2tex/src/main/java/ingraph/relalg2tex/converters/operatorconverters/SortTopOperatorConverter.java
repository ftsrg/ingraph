package ingraph.relalg2tex.converters.operatorconverters;

import ingraph.relalg2tex.converters.elementconverters.ExpressionConverter;
import ingraph.relalg2tex.converters.elementconverters.MiscConverters;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import relalg.Expression;
import relalg.SortEntry;
import relalg.SortOperator;
import relalg.TopOperator;

@SuppressWarnings("all")
public class SortTopOperatorConverter {
  @Extension
  private MiscConverters miscConverters = new MiscConverters();
  
  @Extension
  private ExpressionConverter expressionConverter = new ExpressionConverter();
  
  public String topOperatorToTex(final TopOperator op) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\topp{");
    CharSequence _xifexpression = null;
    Expression _limit = op.getLimit();
    boolean _tripleNotEquals = (_limit != null);
    if (_tripleNotEquals) {
      Expression _limit_1 = op.getLimit();
      _xifexpression = this.expressionConverter.convertExpression(_limit_1);
    } else {
      _xifexpression = "";
    }
    _builder.append(_xifexpression, "");
    _builder.append("}{");
    CharSequence _xifexpression_1 = null;
    Expression _skip = op.getSkip();
    boolean _tripleNotEquals_1 = (_skip != null);
    if (_tripleNotEquals_1) {
      Expression _skip_1 = op.getSkip();
      _xifexpression_1 = this.expressionConverter.convertExpression(_skip_1);
    } else {
      _xifexpression_1 = "";
    }
    _builder.append(_xifexpression_1, "");
    _builder.append("}");
    return _builder.toString();
  }
  
  public String sortOperatorToTex(final SortOperator op) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\sort{");
    EList<SortEntry> _entries = op.getEntries();
    final Function1<SortEntry, CharSequence> _function = (SortEntry it) -> {
      return this.miscConverters.sortEntryToTex(it);
    };
    List<CharSequence> _map = ListExtensions.<SortEntry, CharSequence>map(_entries, _function);
    String _join = IterableExtensions.join(_map, ", ");
    _builder.append(_join, "");
    _builder.append("}");
    return _builder.toString();
  }
}
