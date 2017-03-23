package hu.bme.mit.ire.nodes.unary.aggregation

import hu.bme.mit.ire.datatypes._

import scala.collection.mutable

class StatefulCollect(collectKey: Int) extends StatefulAggregate {
  val collection = new mutable.ListBuffer[Any]()

  def maintainPositive(values: Iterable[Tuple]): Unit = {
    for (tuple <- values)
      collection += tuple(collectKey)
  }

  def maintainNegative(values: Iterable[Tuple]): Unit = {
    for (tuple <- values)
      collection -= tuple(collectKey)
  }

  override def value(): Any = {
    println(collection)
    collection.toVector
  }
}
