package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}

import scala.collection.immutable.VectorBuilder
import scala.collection.mutable

class CountNode(override val next: (ReteMessage) => Unit,
                val keys: Vector[Any], as: String) extends UnaryNode with SingleForwarder {
  val counts = new mutable.HashMap[Vector[Any], Int].withDefault(d => 0)

  override def onChangeSet(changeSet: ChangeSet): Unit = {
    val oldValues = new mutable.HashMap[Vector[Any], Int]

//    for (tuple <- changeSet.positive;
//         key = keys.map(tuple(_))) {
//      if (!oldValues.contains(key)) {
//        oldValues(key) = counts(key)
//      }
//      counts(key) += 1
//    }
//
//    for (tuple <- changeSet.negative;
//         key = keys.map(tuple(_))) {
//      if (!oldValues.contains(key)) {
//        oldValues(key) = counts(key)
//      }
//      counts(key) -= 1
//    }
//
//
//    val positive = new VectorBuilder[TupleType]
//    val negative = new VectorBuilder[TupleType]
//    for ((key, oldValue) <- oldValues) {
//      if (oldValue != 0) {
//        negative += Map(as -> oldValues(key)) ++ key.zipWithIndex.map(kv => keys(kv._2) -> kv._1).toMap
//      }
//      if (counts(key) != 0) {
//        positive += Map(as -> counts(key)) ++ key.zipWithIndex.map(kv => keys(kv._2) -> kv._1).toMap
//      }
//    }
//
//    forward(ChangeSet(positive = positive.result(), negative = negative.result()))
  }
}
