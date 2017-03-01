package hu.bme.mit.ire.nodes.unary.aggregation

import hu.bme.mit.ire.datatypes._
import hu.bme.mit.ire.messages.ChangeSet
import hu.bme.mit.ire.util.GenericMath
import scala.collection.immutable.VectorBuilder
import scala.collection.mutable

trait SumLike {
  val sums = new mutable.HashMap[Tuple, Any].withDefault(d => 0)

  def maintainSum(changeSet: ChangeSet, aggregationKeys: Mask, sumKey: Int): ChangeSet = {
    val oldValues = new mutable.HashMap[Tuple, Any]

    for (tuple <- changeSet.positive;
         key = aggregationKeys.map(tuple(_))) {
      if (!oldValues.contains(key)) {
        oldValues(key) = sums(key)
      }
      sums(key) = GenericMath.add(sums(key), tuple(sumKey))
    }

    for (tuple <- changeSet.negative;
         key = aggregationKeys.map(tuple(_))) {
      if (!oldValues.contains(key)) {
        oldValues(key) = sums(key)
      }
      sums(key) = GenericMath.subtract(sums(key), tuple(sumKey))
    }

    val positive = new VectorBuilder[Tuple]
    val negative = new VectorBuilder[Tuple]
    for ((key, oldValue) <- oldValues) {
      if (oldValue != 0) {
        negative += key :+ oldValues(key)
      }
      if (sums(key) != 0) {
        positive += key :+ sums(key)
      }
    }

    return ChangeSet(positive = positive.result(), negative = negative.result())
  }
}
