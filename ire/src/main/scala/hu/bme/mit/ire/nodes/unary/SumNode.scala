package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}
import hu.bme.mit.ire.util.GenericMath

import scala.collection.immutable.VectorBuilder
import scala.collection.mutable

class SumNode(override val next: (ReteMessage) => Unit,
              val aggregationKeys: Vector[Int], val sumKey: Int) extends UnaryNode with SingleForwarder {
  val sums = new mutable.HashMap[Vector[Any], Any].withDefault(d => 0)

  override def onChangeSet(changeSet: ChangeSet): Unit = {
    val oldValues = new mutable.HashMap[Vector[Any], Any]

    for (tuple <- changeSet.positive;
         key = aggregationKeys.map(tuple(_))) {
      if (!oldValues.contains(key)) {
        oldValues(key) = sums(key)
      }
      sums(key) = GenericMath.plus(sums(key), tuple(sumKey))
    }

    for (tuple <- changeSet.negative;
         key = aggregationKeys.map(tuple(_))) {
      if (!oldValues.contains(key)) {
        oldValues(key) = sums(key)
      }
      sums(key) = GenericMath.minus(sums(key), tuple(sumKey))
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

    forward(ChangeSet(positive = positive.result(), negative = negative.result()))
  }
}
