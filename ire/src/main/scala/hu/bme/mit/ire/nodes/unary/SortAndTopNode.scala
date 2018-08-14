package hu.bme.mit.ire.nodes.unary

import java.util.Comparator

import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.math.GenericMath
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage, SingleForwarder}
import hu.bme.mit.ire.util.SizeCounter

import scala.collection.immutable.VectorBuilder

class SortAndTopNode(override val next: (ReteMessage) => Unit,
                     tupleLength: Int,
                     selectionMask: Vector[(Tuple) => Any],
                     skip: Option[Long],
                     limit: Option[Long],
                     ascendingOrder: Vector[Boolean])
  extends UnaryNode with SingleForwarder {

  val longSkip = skip.getOrElse(0L)
  val longLimit = limit.getOrElse(Long.MaxValue / 2L)
  //implicit val order = new Ordering[Tuple] {
  val comparator = new Comparator[Tuple] {
    override def compare(x: Tuple, y: Tuple): Int = {
      for ((x, y, ascending) <- (keyLookup(x), keyLookup(y), ascendingOrder).zipped) {
        val cmp = GenericMath.compare(x, y)
        if (cmp != 0) {
          return if (ascending) cmp else -cmp
        }
      }
      // Treemap uses the ordering function equality for detecting duplicate keys, so we need to make sure
      // that tuples with same keys are compared as different
      // TODO check if returning -1 for non-equal tuples would mess things up
//      for ((x, y) <- inverseKeyLookup(x).zip(inverseKeyLookup(y))) {
//        val cmp = GenericMath.compare(x, y)
//        if (cmp != 0)
//          return cmp
//      }
      // TODO i haven't really checked because it's 10PM
      if (x != y) {
        1
      } else {
        0
      }
    }
  }

  // we can use Scala Tree once we migrate to 2.12
  val data: java.util.Map[Tuple, Int] = new java.util.TreeMap(comparator)
  //null //mutable.TreeMap[Tuple, Int]().withDefault(t => 0)

  def keyLookup(t: Tuple): Vector[Any] = selectionMask.map(m => m(t))

  def getTuplesInOrder: Vector[Tuple] = {
    var total: Long = 0
    val iterator = data.entrySet.iterator
    val builder = new VectorBuilder[Tuple]
    while (total < longLimit + longSkip && iterator.hasNext) {
      val entry = iterator.next
      val tuple = entry.getKey
      val count = entry.getValue
      for (i <- 0L until math.min(count, longLimit + longSkip - total))
        builder += tuple
      total += count
    }
    builder.result().drop(longSkip.toInt)
  }

  override def onChangeSet(changeSet: ChangeSet): Unit = {
    // TODO maybe checking the changed elements against the lowest forwarded element would speed things up
    val prevTop = getTuplesInOrder
    for (tuple <- changeSet.positive) {
      val count : Int = Some(data.get(tuple)).getOrElse(0)
      data.put(tuple, count + 1)
    }
    for (tuple <- changeSet.negative) {
      val count = data.get(tuple)
      if (count >  1) {
        data.put(tuple, count - 1)
      } else {
        data.remove(tuple)
      }
    }
    val topN = getTuplesInOrder
    if (topN != prevTop) {
      forward(ChangeSet(positive = topN, negative = prevTop))
    }
  }

  // TODO we can simplify this in 2.12
  override def onSizeRequest(): Long = SizeCounter.count(data.keySet)
}
