package hu.bme.mit.ire.util

import hu.bme.mit.ire.datatypes.{Mask, TupleType}

object TestUtil {
  def tuple(elements: Any*): TupleType = elements.toIndexedSeq
  def mask(elements: Int*): Mask = elements.toIndexedSeq
}
