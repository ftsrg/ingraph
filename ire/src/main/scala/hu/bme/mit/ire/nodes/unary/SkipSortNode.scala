package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.{Mask, Tuple}
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}
import hu.bme.mit.ire.util.{GenericMath, SizeCounter}

import scala.collection.immutable.VectorBuilder
import scala.collection.mutable

class SkipSortNode(override val next: (ReteMessage) => Unit,
                   tupleLength: Int, selectionMask: Mask,
                   limit: Int, ascendingOrder: Vector[Boolean])
  extends UnaryNode with SingleForwarder {

  implicit val order = new Ordering[Tuple] {
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

  val data = mutable.TreeMap[Tuple, Int]().withDefault(t => 0)

  def keyLookup(t: Tuple): Vector[Any] = selectionMask.map(t(_))

  val maskInverse: Mask = Vector.range(0, tupleLength).filter(i => !selectionMask.contains(i))

  def inverseKeyLookup(t: Tuple): Vector[Any] = maskInverse.map(t(_))

  def getTopN(): Vector[Tuple] = {
    var total = 0
    val iterator: Iterator[(Tuple, Int)] = data.iterator
    val builder = new VectorBuilder[Tuple]
    while (total < limit && iterator.hasNext) {
      val (tuple, count) = iterator.next()
      for (i <- 0 until Math.min(count, limit - total))
        builder += tuple
      total += count
    }
    builder.result()
  }

  override def onChangeSet(changeSet: ChangeSet): Unit = {
    // TODO maybe checking the changed elements against the lowest forwarded element would speed thins up
    val prevTop = getTopN()
    for (tuple <- changeSet.positive) {
      data(tuple) += 1
    }
    for (tuple <- changeSet.negative) {
      if (data(tuple) > 1) {
        data(tuple) -= 1
      } else {
        data.remove(tuple)
      }
    }
    val topN = getTopN()
    if (topN != prevTop) {
      forward(ChangeSet(positive = topN, negative = prevTop))
    }
  }

  override def onSizeRequest(): Long = SizeCounter.count(data.keys)
}
