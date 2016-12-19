package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes._
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}

import scala.collection.immutable.VectorBuilder
import scala.collection.mutable

abstract class ExtremeNode(override val next: (ReteMessage) => Unit,
                           val aggregationKeys: Vector[Int],
                           val extremeKey: Int,
                           override val expectedTerminatorCount: Int = 1
                          ) extends UnaryNode with SingleForwarder {
  val data = new mutable.HashMap[Vector[Any], mutable.SortedSet[Tuple]]()
  implicit val order : Ordering[Tuple]

  override def onChangeSet(changeSet: ChangeSet): Unit = {

    val oldValues = new mutable.HashMap[Vector[Any], Option[Any]]

    for (tuple <- changeSet.positive;
         key = aggregationKeys.map(tuple(_))) {
      if (!oldValues.contains(key)) {
        oldValues(key) = data.get(key).map(_.last(extremeKey))
      }
      data.getOrElseUpdate(key, mutable.SortedSet[Tuple]()) += tuple
    }

    for (tuple <- changeSet.negative;
         key = aggregationKeys.map(tuple(_))) {
      if (!oldValues.contains(key)) {
        oldValues(key) = data.get(key).map(_.last(extremeKey))
      }
      data(key) -= tuple
    }
    val positive = new VectorBuilder[Tuple]
    val negative = new VectorBuilder[Tuple]
    for ((key, oldValue) <- oldValues) {
      if (oldValue.isEmpty || data(key) != oldValue.get)
        if (oldValue.isDefined) {
          negative += key :+ oldValues(key).get
        }

      positive += key :+ data(key).last(extremeKey)
    }

    forward(ChangeSet(positive = positive.result(), negative = negative.result()))
  }

}
