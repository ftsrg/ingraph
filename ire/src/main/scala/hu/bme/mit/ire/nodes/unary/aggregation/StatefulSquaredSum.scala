package hu.bme.mit.ire.nodes.unary.aggregation

import hu.bme.mit.ire.datatypes._
import hu.bme.mit.ire.util.GenericMath


class StatefulSquaredSum(sumKey: Int) extends StatefulAggregate {
  var sum: Any = 0

  override def maintainPositive(values: Iterable[Tuple]): Unit = {
    for (tuple <- values) {
      sum = GenericMath.add(sum, GenericMath.power(tuple(sumKey), 2))
    }
  }

  override def maintainNegative(values: Iterable[Tuple]): Unit = {
    for (tuple <- values) {
      sum = GenericMath.subtract(sum, GenericMath.power(tuple(sumKey), 2))
    }
  }

  override def value(): Any = sum
}
