package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes._
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}
import hu.bme.mit.ire.util.SizeCounter

import scala.collection.immutable.VectorBuilder
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class CollectNode(override val next: (ReteMessage) => Unit,
                  val aggregationKeys: Vector[Int],
                  val collectKey: Int) extends UnaryNode with SingleForwarder {
  val collections = new mutable.HashMap[Tuple, ListBuffer[Any]].withDefault(d => new ListBuffer())

  override def onSizeRequest(): Long = SizeCounter.count(collections.values)

  def maintainCollections(changeSet: ChangeSet, aggregationKeys: Vector[Int], collectKey: Int): ChangeSet = {
    val oldValues = new mutable.HashMap[Tuple, ListBuffer[Any]]

    for (tuple <- changeSet.positive;
         key = aggregationKeys.map(tuple(_))) {
      if (!oldValues.contains(key)) {
        oldValues(key) = collections(key)
      }
      collections(key) = collections(key) :+ tuple(collectKey)
    }

    for (tuple <- changeSet.negative;
         key = aggregationKeys.map(tuple(_))) {
      if (!oldValues.contains(key)) {
        oldValues(key) = collections(key)
      }
      collections(key) = collections(key) - tuple(collectKey)
    }

    val positive = new VectorBuilder[Tuple]
    val negative = new VectorBuilder[Tuple]
    for ((key, oldValue) <- oldValues) {
      if (!oldValue.isEmpty) {
        negative += key :+ oldValues(key).toVector
      }
      if (!collections(key).isEmpty) {
        positive += key :+ collections(key).toVector
      }
    }

    return ChangeSet(positive = positive.result(), negative = negative.result())
  }

  override def onChangeSet(changeSet: ChangeSet): Unit = {
    val newChangeSet = maintainCollections(changeSet, aggregationKeys, collectKey)
    forward(newChangeSet)
  }

}
