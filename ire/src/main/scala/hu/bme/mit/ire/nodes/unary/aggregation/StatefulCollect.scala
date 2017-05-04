package hu.bme.mit.ire.nodes.unary.aggregation

import hu.bme.mit.ire.datatypes._

import scala.collection.mutable

class StatefulCollect(collectKey: Vector[Int]) extends StatefulAggregate {
  val collection = new mutable.ListBuffer[Any]()

  def maintainPositive(values: Iterable[Tuple]): Unit = {
    for (tuple <- values)
      collection += collectKey.map(tuple)
  }

  def maintainNegative(values: Iterable[Tuple]): Unit = {
    for (tuple <- values)
      collection -= collectKey.map(tuple)
  }

  override def value(): Any = {
    collection.toVector
  }
}
