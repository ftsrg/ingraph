package ingraph.ire.util

import ingraph.ire.datatypes.{Mask, Tuple, TupleBag}
import ingraph.ire.messages.ChangeSet

object TestUtil {
  def tuple(elements: Any*): Tuple = elements.toIndexedSeq
  def cypherList(elements: Any*): Tuple = elements.toIndexedSeq
  def mask(elements: Int*): Mask = elements.toVector //.toIndexedSeq
  def functionMask(elements: Int*): Vector[Tuple => Any] = elements.map(f => (t: Tuple) => t(f)).toVector
  def tupleBag(elements: Tuple*): TupleBag = elements.toVector

  /**
    * Produces permutations based on the change set, e.g.
    * {+ <1, 2>, <3, 4>} -> {{+ <1, 2>, <3, 4>}, {+ <3, 4>, <1, 2>}}
    * @param cs
    * @return
    */
  def changeSetPermutations(cs: ChangeSet) = {
    val values = for (
      pos <- cs.positive.permutations;
      neg <- cs.negative.permutations
    ) yield ChangeSet(pos, neg)
    values.toSeq
  }
}
