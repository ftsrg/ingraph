package hu.bme.mit.ire.util

import hu.bme.mit.ire.datatypes.{Mask, Tuple, TupleBag}

object TestUtil {
  def tuple(elements: Any*): Tuple = elements.toIndexedSeq
  def cypherList(elements: Any*): Tuple = elements.toIndexedSeq
  def mask(elements: Int*): Mask = elements.toIndexedSeq
  def tupleBag(elements: Tuple*): TupleBag = elements.toVector 
}
