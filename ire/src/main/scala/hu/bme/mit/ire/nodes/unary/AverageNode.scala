package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}
import hu.bme.mit.ire.util.GenericMath

class AverageNode(override val next: (ReteMessage) => Unit,
                  val aggregationKeys: Vector[Int],
                  val sumKey: Int) extends UnaryNode with SumLike with CountLike with SingleForwarder {

  def average(sums: Vector[Tuple], counts: Vector[Tuple]): Vector[Tuple] = {
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

