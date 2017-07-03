package ingraph.relalg.util;

import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import relalg.EmptyListExpression;
import relalg.Expression;
import relalg.ListExpression;

@SuppressWarnings("all")
public class ListExpressionUtil {
  protected static List<Expression> _toList(final ListExpression e) {
    ImmutableList<Expression> _xblockexpression = null;
    {
      final ImmutableList.Builder<Expression> builder = ImmutableList.<Expression>builder();
      Expression _head = e.getHead();
      builder.add(_head);
      ListExpression _tail = e.getTail();
      List<Expression> _list = ListExpressionUtil.toList(_tail);
      builder.addAll(_list);
      _xblockexpression = builder.build();
    }
    return _xblockexpression;
  }
  
  protected static List<Expression> _toList(final EmptyListExpression e) {
    return Collections.<Expression>unmodifiableList(CollectionLiterals.<Expression>newArrayList());
  }
  
  public static List<Expression> toList(final ListExpression e) {
    if (e instanceof EmptyListExpression) {
      return _toList((EmptyListExpression)e);
    } else if (e != null) {
      return _toList(e);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(e).toString());
    }
  }
}
