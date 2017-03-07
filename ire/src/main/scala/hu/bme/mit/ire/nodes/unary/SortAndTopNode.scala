package hu.bme.mit.ire.nodes.unary

import java.util.Comparator
import java.util.TreeMap

import scala.collection.immutable.VectorBuilder

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.messages.ChangeSet
import hu.bme.mit.ire.messages.ReteMessage
import hu.bme.mit.ire.util.GenericMath
import hu.bme.mit.ire.util.SizeCounter
import hu.bme.mit.ire.datatypes.Mask
import hu.bme.mit.ire.datatypes.Tuple

// TODO implement skip
class SortAndTopNode(override val next: (ReteMessage) => Unit,
                   tupleLength: Int,
                   selectionMask: Mask,
                   skip: Int,
                   limit: Int,
                   ascendingOrder: Vector[Boolean])
  extends UnaryNode with SingleForwarder {

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
      for ((x, y) <- inverseKeyLookup(x).zip(inverseKeyLookup(y))) {
        val cmp = GenericMath.compare(x, y)
        if (cmp != 0)
          return cmp
      }
      0
    }
  }

  // we can use Scala Tree once we migrate to 2.12
  val data: java.util.Map[Tuple, Int] = new TreeMap(comparator)
  //null //mutable.TreeMap[Tuple, Int]().withDefault(t => 0) 

  val maskInverse: Mask = Vector.range(0, tupleLength).filter(i => !selectionMask.contains(i))

  def keyLookup(t: Tuple): Vector[Any] = selectionMask.map(t(_))
  def inverseKeyLookup(t: Tuple): Vector[Any] = maskInverse.map(t(_))

  def getTopN(): Vector[Tuple] = {
    var total = 0
    //val iterator: Iterator[(Tuple, Int)] = data.iterator
    val iterator = data.entrySet.iterator
    val builder = new VectorBuilder[Tuple]
    while (total < limit && iterator.hasNext) {
//      val (tuple, count) = iterator.next()
      val entry = iterator.next
      val tuple = entry.getKey
      val count = entry.getValue
      for (i <- 0 until Math.min(count, limit - total))
        builder += tuple
      total += count
    }
    builder.result()
  }

  override def onChangeSet(changeSet: ChangeSet): Unit = {
    // TODO maybe checking the changed elements against the lowest forwarded element would speed things up
    val prevTop = getTopN()
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
    val topN = getTopN()
    if (topN != prevTop) {
      forward(ChangeSet(positive = topN, negative = prevTop))
    }
  }

  // TODO we can simplify this in 2.12
//  override def onSizeRequest(): Long = SizeCounter.count(data.keys)
  override def onSizeRequest(): Long = SizeCounter.count(data.keySet)
}
