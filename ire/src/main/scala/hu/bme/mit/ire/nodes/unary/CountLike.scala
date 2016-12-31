package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.datatypes._
import hu.bme.mit.ire.messages.ChangeSet

import scala.collection.immutable.VectorBuilder
import scala.collection.mutable

trait CountLike {
  val counts = new mutable.HashMap[Tuple, Int].withDefault(d => 0)
  def maintainCount(changeSet: ChangeSet, aggregationKeys: Vector[Int]): ChangeSet = {
    val oldValues = new mutable.HashMap[Tuple, Int]

    for (tuple <- changeSet.positive;
         key = aggregationKeys.map(tuple(_))) {
      if (!oldValues.contains(key)) {
        oldValues(key) = counts(key)
      }
      counts(key) += 1
    }

    for (tuple <- changeSet.negative;
         key = aggregationKeys.map(tuple(_))) {
      if (!oldValues.contains(key)) {
        oldValues(key) = counts(key)
      }
      counts(key) -= 1
    }

    val positive = new VectorBuilder[Tuple]
    val negative = new VectorBuilder[Tuple]
    for ((key, oldValue) <- oldValues) {
      if (oldValue != 0) {
        negative += key :+ oldValues(key)
      }
      if (counts(key) != 0) {
        positive += key :+ counts(key)
      }
    }

    return ChangeSet(positive = positive.result(), negative = negative.result())
  }
}
