package hu.bme.mit.ire.nodes.unary.aggregation

import hu.bme.mit.ire.datatypes.Tuple

abstract class StatefulAggregate {
  def maintainPositive(values: Iterable[Tuple])

  def maintainNegative(values: Iterable[Tuple])

  def value(): Any
}
