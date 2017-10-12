package hu.bme.mit.nre.nodes.binary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.{Mask, Path, Tuple, TupleBag}
import hu.bme.mit.ire.messages.{BatchChangeSet, IncrementalChangeSet, ReteMessage}
import hu.bme.mit.ire.util.TestUtil.tuple
import hu.bme.mit.ire.util.{BufferMultimap, CounterMultimap, SizeCounter}

import scala.collection.immutable.{HashMap, VectorBuilder}
import scala.collection.mutable


class TransitiveClosureJoinNode(override val next: (ReteMessage) => Unit,
                                val primaryTupleWidth: Int,
                                val secondaryTupleWidth: Int,
                                primaryMask: Mask,
                                secondaryMask: Mask,
                                val outputTupleWidth: Int,
                                val minHops: Long = 1,
                                var maxHops: Long = Long.MaxValue
                               ) extends StatelessBinaryNode with SingleForwarder {

  maxHops = if (maxHops >= 1) maxHops else Long.MaxValue

  val sourceVertexIndex = primaryMask(0)
  val targetVertexIndex = 2 - secondaryMask(0)

  var sourceLookup = new BatchChangeSet
  var targetLookup = new BatchChangeSet

  def onSizeRequest = 0

  def extract(tuple: Tuple, mask: Mask): Tuple = {
    mask.map(i => tuple(i))
  }

  override def onPrimary(changeSet: BatchChangeSet): Unit = {
    this.sourceLookup = changeSet
    val result = BatchChangeSet(transitiveClosure())
    forward(result)
  }

  override def onSecondary(changeSet: BatchChangeSet): Unit = {
    this.targetLookup = changeSet
    val result = BatchChangeSet(transitiveClosure())
    forward(result)
  }

  def transitiveClosure(): TupleBag = {

    val result = sourceLookup.changeSet.flatMap(t => {
      val sourceIndex = t(sourceVertexIndex).asInstanceOf[Number].longValue
      depthSearch(-1, sourceIndex, Path())
        .filter { case (k, _) => k.length >= minHops }
        .map { case (k, v) => tuple(sourceIndex, k, v) }
    })
    println(result.size)
    println(result.mkString("\n"))
    result
  }

  def depthSearch(via: Long, currentVertex: Long, leadingPath: Path): Map[Path, Long] = {
    if (leadingPath.length < maxHops && !leadingPath.contains(via)) {
      val currentPath = if (via >= 0) leadingPath :+ via else leadingPath
      val reachables: Map[Path, Long] =
        targetLookup.changeSet
          .filter { t => t(sourceVertexIndex) == currentVertex }
          .flatMap { t =>
            depthSearch(
              t(1).asInstanceOf[Number].longValue,
              t(2).asInstanceOf[Number].longValue,
              currentPath
            )
          }
          .toMap
      reachables + (currentPath -> currentVertex)
    } else {
      Map()
    }
  }

}
