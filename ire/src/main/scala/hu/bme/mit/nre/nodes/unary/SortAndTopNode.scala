package hu.bme.mit.nre.nodes.unary

import java.util.Comparator

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.{BatchChangeSet, IncrementalChangeSet, ReteMessage}
import hu.bme.mit.ire.nodes.unary.UnaryNode
import hu.bme.mit.ire.util.{GenericMath, SizeCounter}

import scala.collection.immutable.VectorBuilder

class SortNode(override val next: (ReteMessage) => Unit,
               tupleLength: Int,
               selectionMask: Vector[(Tuple) => Any],
               ascendingOrder: Vector[Boolean])
  extends UnaryNode[BatchChangeSet] with SingleForwarder {

  override def onSizeRequest(): Long = 0

  def comparator(a: Tuple, b: Tuple): Boolean =
    selectionMask zip ascendingOrder map {case (selector, asc) => (if (asc) -1 else 1) * GenericMath.compare(selector(a), selector(b))} find (_ != 0) exists (_ > 0)

  override def onChangeSet(changeSet: BatchChangeSet): Unit =
    forward(BatchChangeSet(changeSet.changeSet.toVector.sortWith(comparator)))
}

class SortAndTopNode(next: (ReteMessage) => Unit,
                     tupleLength: Int,
                     selectionMask: Vector[(Tuple) => Any],
                     skip: Long,
                     limit: Long,
                     ascendingOrder: Vector[Boolean])
  extends SortNode(next, tupleLength, selectionMask, ascendingOrder) {

  override def onChangeSet(changeSet: BatchChangeSet): Unit =
    forward(BatchChangeSet(changeSet.changeSet.toVector.sortWith(comparator).slice(skip.toInt, (skip+limit).toInt)))


}
