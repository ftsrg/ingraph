package ingraph.ire.nodes.unary.aggregation

import ingraph.ire.datatypes._

import scala.collection.mutable

class StatefulCount extends StatefulAggregate {
  private var count: Long = 0

  override def value(): Any = count

  override def maintainPositive(values: Iterable[Tuple]): Unit = {
    count += values.size
  }

  override def maintainNegative(values: Iterable[Tuple]): Unit = {
    count -= values.size
  }
}

class NullAwareStatefulCount(val index: Int) extends StatefulCount {
  override def maintainPositive(values: Iterable[Tuple]): Unit =
    super.maintainPositive(values.filter(t => t(index) != null))

  override def maintainNegative(values: Iterable[Tuple]): Unit =
    super.maintainNegative(values.filter(t => t(index) != null))
}

class StatefulDistinctCount(index: Int) extends StatefulAggregate {
  private var elements = mutable.Map[Any, Int]().withDefault(f => 0)

  override def maintainPositive(values: Iterable[Tuple]): Unit = {
    for (tuple <- values.filter(t => t(index) != null))
      elements(tuple(index)) += 1
  }

  override def maintainNegative(values: Iterable[Tuple]): Unit = {
    for (tuple <- values.filter(t => t(index) != null)) {
      val value = tuple(index)
      elements(value) -= 1
      if (elements(value) == 0)
        elements.remove(value)
    }
  }

  override def value(): Any = elements.keySet.size
}
