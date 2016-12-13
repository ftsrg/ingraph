package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}

import scala.collection.immutable.{Seq, VectorBuilder}
import scala.collection.mutable

// the CountNode follows the COUNT(*) semantics, i.e. you do not have to specify the attribute to count
class CountNode(override val next: (ReteMessage) => Unit,
                val keys: Vector[Int]) extends UnaryNode with SingleForwarder {
  val counts = new mutable.HashMap[Vector[Any], Int].withDefault(d => 0)

  override def onChangeSet(changeSet: ChangeSet): Unit = {
    val oldValues = new mutable.HashMap[Vector[Any], Int]

    for (tuple <- changeSet.positive;
         key = keys.map(tuple(_))) {
      if (!oldValues.contains(key)) {
        oldValues(key) = counts(key)
      }
      counts(key) += 1
    }

    for (tuple <- changeSet.negative;
         key = keys.map(tuple(_))) {
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

    forward(ChangeSet(positive = positive.result(), negative = negative.result()))
  }
}
