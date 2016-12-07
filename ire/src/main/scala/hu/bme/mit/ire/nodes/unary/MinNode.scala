package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}
import hu.bme.mit.ire.util.GenericMath

import scala.collection.immutable.VectorBuilder
import scala.collection.mutable

class MinNode(override val next: (ReteMessage) => Unit,
              val aggregationKeys: Vector[Int], val minKey: Int,
              override val expectedTerminatorCount: Int = 1
                   ) extends UnaryNode with SingleForwarder {
  implicit val order = new Ordering[Tuple] {
    override def compare(x: Tuple, y: Tuple): Int = GenericMath.compare(y(minKey), x(minKey))
  }
  val data = new mutable.HashMap[Vector[Any], mutable.SortedSet[Tuple]]()

  override def onChangeSet(changeSet: ChangeSet): Unit = {

    val oldValues = new mutable.HashMap[Vector[Any], Option[Any]]

    for (tuple <- changeSet.positive;
         key = aggregationKeys.map(tuple(_))) {
      if (!oldValues.contains(key)) {
        oldValues(key) = data.get(key).map(_.last(minKey))
      }
      data.getOrElseUpdate(key, mutable.SortedSet[Tuple]()) += tuple
    }

    for (tuple <- changeSet.negative;
         key = aggregationKeys.map(tuple(_))) {
      if (!oldValues.contains(key)) {
        oldValues(key) = data.get(key).map(_.last(minKey))
        println(oldValues)
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

      positive += key :+ data(key).last(minKey)
    }

    forward(ChangeSet(positive = positive.result(), negative = negative.result()))
  }

}
