package ingraph.ire.nodes.unary.aggregation

import ingraph.ire.datatypes.Tuple
import ingraph.ire.math.GenericMath

class StatefulAverage(val sumKey: Int) extends StatefulAggregate {
  val summer = new StatefulSum(sumKey)
  val counter = new StatefulCount()

  override def value(): Any = try {
    GenericMath.divide(summer.value(), counter.value())
  } catch {
    case _: ArithmeticException => 0
  }

  override def maintainPositive(values: Iterable[Tuple]): Unit = {
    summer.maintainPositive(values)
    counter.maintainPositive(values)
  }

  override def maintainNegative(values: Iterable[Tuple]): Unit = {
    summer.maintainNegative(values)
    counter.maintainNegative(values)
  }
}
