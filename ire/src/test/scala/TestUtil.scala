import hu.bme.mit.ire.TupleType

object TestUtil {
  def tuple(elements: Int*): TupleType = elements.zipWithIndex.map(t => t._2 -> t._1 ).toMap
}
