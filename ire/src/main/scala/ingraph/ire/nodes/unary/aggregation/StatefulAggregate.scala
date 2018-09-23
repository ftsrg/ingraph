package ingraph.ire.nodes.unary.aggregation

import ingraph.ire.datatypes.Tuple

abstract class StatefulAggregate {
  def maintainPositive(values: Iterable[Tuple])

  def maintainNegative(values: Iterable[Tuple])

  def value(): Any
}
