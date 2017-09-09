package hu.bme.mit.ire.nodes.unary.aggregation

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.{Mask, Tuple, TupleBag}
import hu.bme.mit.ire.messages.{IncrementalChangeSet, ReteMessage}
import hu.bme.mit.ire.nodes.unary.UnaryNode

import scala.collection.immutable.VectorBuilder
import scala.collection.{immutable, mutable}

class AggregationNode(override val next: (ReteMessage) => Unit,
                      mask: Vector[Tuple => Any], functions: () => Vector[StatefulAggregate],
                     projection: Vector[Tuple => Any]) extends UnaryNode[IncrementalChangeSet] with SingleForwarder {
  private val keyCount = mutable.Map[Tuple, Int]().withDefault(f => 0)
  private val data = mutable.Map[Tuple, Vector[StatefulAggregate]]().withDefault(f => functions())

  override def onChangeSet(changeSet: IncrementalChangeSet): Unit = {
    val oldValues = mutable.Map[Tuple, (Tuple, Int)]()
    for ((key, tuples) <- changeSet.positive.groupBy(t => mask.map(m => m(t)))) {
      val aggregators = data.getOrElseUpdate(key, functions())

      oldValues.getOrElseUpdate(key, (aggregators.map(_.value()), keyCount(key)))
      for (aggregator <- aggregators)
        aggregator.maintainPositive(tuples)
      keyCount(key) += tuples.size
    }
    for ((key, tuples) <- changeSet.negative.groupBy(t => mask.map(m => m(t)))) {
      val aggregators = data.getOrElseUpdate(key, functions())
      oldValues.getOrElseUpdate(key, (aggregators.map(_.value()), keyCount(key)))
      for (aggregator <- aggregators)
        aggregator.maintainNegative(tuples)
      keyCount(key) -= tuples.size
    }

    val positive = new VectorBuilder[Tuple]
    val negative = new VectorBuilder[Tuple]

    for ((key, (oldValues, oldCount)) <- oldValues) {
      val newValues = data(key).map(_.value())
      if (oldValues != newValues) {
        if (keyCount(key) != 0)
          positive += key ++ newValues
        if (oldCount != 0)
          negative += key ++ oldValues
      }
    }
    val positiveBag: TupleBag = positive.result().map((t: Tuple) => projection.map(_(t)))
    val negativeBag: TupleBag = negative.result().map((t: Tuple) => projection.map(_(t)))
    forward(IncrementalChangeSet(
      positive = positiveBag,
        negative = negativeBag))
  }

  override def onSizeRequest(): Long = ???
}
