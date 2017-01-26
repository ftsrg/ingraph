package ingraph.relalg.util

import com.google.common.collect.Iterables
import com.google.common.collect.Lists
import java.util.List

class ListUnionCalculator {

  /**
   * shorthand for creating the union of two lists
   */
  def <T> union(List<? extends T>... lists) {
    lists.forEach[]
    Lists.newArrayList(Iterables.concat(lists))
  }

}