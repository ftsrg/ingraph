package hu.bme.mit.ire.util

import hu.bme.mit.ire.datatypes.{Mask, Tuple, TupleBag}

object TestUtil {
  def tuple(elements: Any*): Tuple = elements.toIndexedSeq
  def cypherList(elements: Any*): Tuple = elements.toIndexedSeq
  def mask(elements: Int*): Mask = elements.toVector //.toIndexedSeq
  def functionMask(elements: Int*): Vector[Tuple => Any] = elements.map(f => (t: Tuple) => t(f)).toVector
  def tupleBag(elements: Tuple*): TupleBag = elements.toVector
}
