package ingraph.relalg.util;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.ExclusiveRange;
import relalg.Operator;
import relalg.Variable;

@SuppressWarnings("all")
public class SchemaToMap {
  public Map<String, Integer> schemaToMapNames(final Operator op) {
    final ImmutableMap.Builder<String, Integer> mapBuilder = new ImmutableMap.Builder<String, Integer>();
    EList<Variable> _internalSchema = op.getInternalSchema();
    int _length = ((Object[])Conversions.unwrapArray(_internalSchema, Object.class)).length;
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _length, true);
    for (final Integer i : _doubleDotLessThan) {
      EList<Variable> _internalSchema_1 = op.getInternalSchema();
      Variable _get = _internalSchema_1.get((i).intValue());
      String _fullName = _get.fullName();
      mapBuilder.put(_fullName, i);
    }
    return mapBuilder.build();
  }
}
