package ingraph.ire.nodes.unary.aggregation

import ingraph.ire.datatypes._
import ingraph.ire.math.GenericMath

import scala.collection.mutable

abstract class StatefulExtreme extends StatefulAggregate {
  val extremeKey: Int
  implicit val order: Ordering[Tuple]
  private val data = mutable.SortedSet[Tuple]()

  override def maintainPositive(values: Iterable[Tuple]): Unit = {
    for (tuple <- values) {
      data += tuple
    }
  }

  override def maintainNegative(values: Iterable[Tuple]): Unit = {
    for (tuple <- values) {
      data -= tuple
    }
  }

  override def value(): Any = data.lastOption.map(t => t(extremeKey)).getOrElse(None)
}

class StatefulMin(override val extremeKey: Int) extends {
  implicit val order = new Ordering[Tuple] {
    override def compare(x: Tuple, y: Tuple): Int = GenericMath.compare(y(extremeKey), x(extremeKey))
  }
} with StatefulExtreme

class StatefulMax(override val extremeKey: Int) extends {
  implicit val order = new Ordering[Tuple] {
    override def compare(x: Tuple, y: Tuple): Int = GenericMath.compare(x(extremeKey), y(extremeKey))
  }
} with StatefulExtreme
