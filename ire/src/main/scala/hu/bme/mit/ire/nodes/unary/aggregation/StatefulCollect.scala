package hu.bme.mit.ire.nodes.unary.aggregation

import hu.bme.mit.ire.datatypes._

import scala.collection.mutable

class StatefulCollect(index: Int) extends StatefulAggregate {
  val collection = new mutable.ListBuffer[Any]()

  def maintainPositive(values: Iterable[Tuple]): Unit = {
    for (tuple <- values)
      collection += tuple(index)
  }

  def maintainNegative(values: Iterable[Tuple]): Unit = {
    for (tuple <- values)
      collection -= tuple(index)
  }

  override def value(): Any = {
    collection.toVector
  }
}

class StatefulDistinctCollect(index: Int) extends StatefulCollect(index) {
  private var elements = mutable.Map[Any, Int]().withDefault(f => 0)

  override def maintainPositive(values: Iterable[Tuple]): Unit = {
    for (tuple <- values) {
      val value = tuple(index)
      if (elements(value) == 0)
        collection += value
      elements(value) += 1
    }
  }

  override def maintainNegative(values: Iterable[Tuple]): Unit = {
    for (tuple <- values) {
      val value = tuple(index)
      elements(value) -= 1
      if (elements(value) == 0)
        collection -= value
    }
  }

}
