package hu.bme.mit.ire.nodes.unary.aggregation

import hu.bme.mit.ire.datatypes._
import hu.bme.mit.ire.math.GenericMath

class StatefulSum(sumKey: Int) extends StatefulAggregate {
  var sum: Any = 0

  override def maintainPositive(values: Iterable[Tuple]): Unit = {
    for (tuple <- values) {
      sum = GenericMath.add(sum, tuple(sumKey))
    }
  }

  override def maintainNegative(values: Iterable[Tuple]): Unit = {
    for (tuple <- values) {
      sum = GenericMath.subtract(sum, tuple(sumKey))
    }
  }

  override def value(): Any = sum
}
