package hu.bme.mit.ire.nodes.unary.aggregation

import hu.bme.mit.ire.datatypes.Mask
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.ChangeSet
import hu.bme.mit.ire.messages.ReteMessage
import hu.bme.mit.ire.util.GenericMath
import hu.bme.mit.ire.util.SizeCounter

class AverageNode(override val next: (ReteMessage) => Unit,
                  override val aggregationKeys: Mask,
                  override val distinct: Boolean,
                  val sumKey: Int) extends AggregationNode with SumLike with CountLike {

  override def onSizeRequest(): Long = SizeCounter.count(counts.keys, sums.keys)

  def average(sums: Iterable[Tuple], counts: Iterable[Tuple]): Iterable[Tuple] = {
    for ( (sum, count) <- (sums zip counts) )
      yield sum.slice(0, sum.size - 1) :+ GenericMath.divide(sum.last, count.last)
  }

  override def onChangeSet(changeSet: ChangeSet): Unit = {
    val sumChangeSet = maintainSum(changeSet, aggregationKeys, sumKey)
    val countChangeSet = maintainCount(changeSet, aggregationKeys)

    val averagePositive = average(sumChangeSet.positive, countChangeSet.positive)
    val averageNegative = average(sumChangeSet.negative, countChangeSet.negative)

    forward(ChangeSet(positive = averagePositive, negative = averageNegative))
  }

}

