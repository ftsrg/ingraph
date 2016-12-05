package hu.bme.mit.ire.util

import hu.bme.mit.ire.datatypes.TupleType

object TestUtil {
  def tuple(elements: Any*): TupleType = elements.zipWithIndex.map(t => t._2 -> t._1).toMap
}
