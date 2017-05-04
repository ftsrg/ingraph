package hu.bme.mit.ire.nodes.unary.aggregation

import hu.bme.mit.ire.datatypes._

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
