import hu.bme.mit.ire.TupleType

object TestUtil {
  def tuple(elements: Any*): TupleType = elements.zipWithIndex.map(t => t._2 -> t._1 ).toMap
}
