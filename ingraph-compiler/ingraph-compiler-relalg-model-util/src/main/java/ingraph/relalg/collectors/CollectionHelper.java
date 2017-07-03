package ingraph.relalg.collectors;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.function.Consumer;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import relalg.RelalgModelElement;

@SuppressWarnings("all")
public class CollectionHelper {
  public <T extends Object> List<T> union(final Iterable<? extends T>... lists) {
    Iterable<T> _concat = Iterables.<T>concat(lists);
    return Lists.<T>newArrayList(_concat);
  }
  
  public <T extends RelalgModelElement> List<T> uniqueUnion(final Iterable<? extends T>... lists) {
    ArrayList<T> _xblockexpression = null;
    {
      final ArrayList<T> result = new ArrayList<T>();
      List<T> _union = this.<T>union(lists);
      final Consumer<T> _function = new Consumer<T>() {
        public void accept(final T it) {
          final Function1<T, String> _function = new Function1<T, String>() {
            public String apply(final T it) {
              return it.fullName();
            }
          };
          List<String> _map = ListExtensions.<T, String>map(result, _function);
          String _fullName = it.fullName();
          boolean _contains = _map.contains(_fullName);
          boolean _not = (!_contains);
          if (_not) {
            result.add(it);
          }
        }
      };
      _union.forEach(_function);
      _xblockexpression = result;
    }
    return _xblockexpression;
  }
  
  public <T extends Object> List<T> minus(final Iterable<? extends T> iterable1, final Iterable<? extends T> iterable2) {
    List<T> _xblockexpression = null;
    {
      final LinkedHashSet<T> set1 = Sets.<T>newLinkedHashSet(iterable1);
      final LinkedHashSet<T> set2 = Sets.<T>newLinkedHashSet(iterable2);
      Sets.SetView<T> _difference = Sets.<T>difference(set1, set2);
      _xblockexpression = IterableExtensions.<T>toList(_difference);
    }
    return _xblockexpression;
  }
}
