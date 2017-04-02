package ingraph.relalg.util

import java.util.List
import relalg.EmptyListExpression
import relalg.Expression
import relalg.ListExpression
import com.google.common.collect.ImmutableList

class ListExpressionUtil {

  def static dispatch List<Expression> toList(ListExpression e) {
    val builder = ImmutableList.builder
    builder.add(e.head)
    builder.addAll(toList(e.tail))
    builder.build
  }

  def static dispatch List<Expression> toList(EmptyListExpression e) {
    #[]
  }

}