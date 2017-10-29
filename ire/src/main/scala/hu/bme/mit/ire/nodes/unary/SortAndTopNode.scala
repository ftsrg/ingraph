package hu.bme.mit.ire.nodes.unary

import java.util.Comparator

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.{IncrementalChangeSet, ReteMessage}
import hu.bme.mit.ire.util.{GenericMath, SizeCounter}

import scala.collection.immutable.VectorBuilder

class SortNode(override val next: (ReteMessage) => Unit,
                   tupleLength: Int,
                   selectionMask: Vector[(Tuple) => Any],
                   ascendingOrder: Vector[Boolean])
  extends UnaryNode[IncrementalChangeSet] with SingleForwarder {

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

//  val maskInverse: Mask = Vector.range(0, tupleLength).filter(i => !selectionMask.contains(i))

  def keyLookup(t: Tuple): Vector[Any] = selectionMask.map(m => m(t))
//  def inverseKeyLookup(t: Tuple): Vector[Any] = maskInverse.map(t(_))

  def getTuplesInOrder: Vector[Tuple] = {
    var total = 0
    //val iterator: Iterator[(Tuple, Int)] = data.iterator
    val iterator = data.entrySet.iterator
    val builder = new VectorBuilder[Tuple]
    while (iterator.hasNext) {
//      val (tuple, count) = iterator.next()
      val entry = iterator.next
      val tuple = entry.getKey
      val count = entry.getValue
      for (i <- 0 until count)
        builder += tuple
      total += count
    }
    builder.result()
  }

  override def onChangeSet(changeSet: IncrementalChangeSet): Unit = {
    // TODO maybe checking the changed elements against the lowest forwarded element would speed things up
    val prevTop = getTuplesInOrder
    for (tuple <- changeSet.positive) {
//      data(tuple) += 1
      val count : Int = Some(data.get(tuple)).getOrElse(0)
      data.put(tuple, count + 1)
    }
    for (tuple <- changeSet.negative) {
//      if (data(tuple) > 1) {
//        data(tuple) -= 1
//      } else {
//        data.remove(tuple)
//      }
      val count = data.get(tuple)
      if (count >  1) {
        data.put(tuple, count - 1)
      } else {
        data.remove(tuple)
      }
    }
    val topN = getTuplesInOrder
    if (topN != prevTop) {
      forward(IncrementalChangeSet(positive = topN, negative = prevTop))
    }
  }

  // TODO we can simplify this in 2.12
//  override def onSizeRequest(): Long = SizeCounter.count(data.keys)
  override def onSizeRequest(): Long = SizeCounter.count(data.keySet)
}

class SortAndTopNode(next: (ReteMessage) => Unit,
                     tupleLength: Int,
                     selectionMask: Vector[(Tuple) => Any],
                     skip: Long,
                     limit: Long,
                     ascendingOrder: Vector[Boolean])
  extends SortNode(next, tupleLength, selectionMask, ascendingOrder) {

  override def getTuplesInOrder: Vector[Tuple] = {
    var total: Long = 0
    //val iterator: Iterator[(Tuple, Int)] = data.iterator
    val iterator = data.entrySet.iterator
    val builder = new VectorBuilder[Tuple]
    while (total < limit + skip && iterator.hasNext) {
      //      val (tuple, count) = iterator.next()
      val entry = iterator.next
      val tuple = entry.getKey
      val count = entry.getValue
      for (i <- 0L until math.min(count, limit + skip - total))
        builder += tuple
      total += count
    }
    builder.result().drop(skip.toInt)
  }
}
