package ingraph.relalg.util

import java.util

import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableList.Builder
import relalg.{EmptyListExpression, Expression, ListExpression}

object ListExpressionUtil {

  def toList(e: ListExpression): java.util.List[Expression] = {
    e match {
      case _ : EmptyListExpression => return new util.ArrayList[Expression]()
      case e : ListExpression => {
        val builder : Builder[Expression] = ImmutableList.builder()
        builder.add(e.getHead)
        builder.addAll(toList(e.getTail))
        return builder.build
      }
    }
  }

}
