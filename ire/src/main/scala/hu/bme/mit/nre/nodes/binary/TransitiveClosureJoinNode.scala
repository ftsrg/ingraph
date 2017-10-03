package hu.bme.mit.nre.nodes.binary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.{Mask, Path, Tuple, TupleBag}
import hu.bme.mit.ire.messages.{BatchChangeSet, IncrementalChangeSet, ReteMessage}
import hu.bme.mit.ire.util.TestUtil.tuple
import hu.bme.mit.ire.util.{BufferMultimap, CounterMultimap, SizeCounter}

import scala.collection.immutable.VectorBuilder
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
    forward(BatchChangeSet(transitiveClosure()))
  }

  override def onSecondary(changeSet: BatchChangeSet): Unit = {
    this.targetLookup = changeSet
    forward(BatchChangeSet(transitiveClosure()))
  }

  def transitiveClosure(): TupleBag = {
    sourceLookup.changeSet.flatMap(t => {
      val sourceIndex = t(sourceVertexIndex).asInstanceOf[Number].longValue
      val visitedPaths = new mutable.HashMap[Path, Long]
      depthSearch(sourceIndex, sourceIndex, Path(), visitedPaths)
      visitedPaths
        .filter { case (k, _) => (k.length + 1) >= minHops }
        .map { case (k, v) => tuple(sourceIndex, k, v) }
        .toVector
    })
  }

  def depthSearch(from: Long, to: Long, path: Path, visitedPaths: mutable.HashMap[Path, Long]): Unit = {
    if (path.length <= (maxHops + 1) && !visitedPaths.contains(path)) {
      visitedPaths += (path -> to)
      targetLookup.changeSet
        .filter(t => t(sourceVertexIndex) == to)
        .foreach(t => {
          val edge = t(1).asInstanceOf[Number].longValue
          val target = t(2).asInstanceOf[Number].longValue
          depthSearch(to, target, path :+ edge, visitedPaths)
        })
    }
  }

  //  def transitiveClosure(): TupleBag = {
  //    val result = sourceLookup.changeSet.toVector.flatMap(t => {
  //      val sourceIndex = t(sourceVertexIndex).asInstanceOf[Number].longValue
  //      val visitedPaths = new mutable.HashSet[Path]
  //      depthSearch(sourceIndex, sourceIndex, Path(), visitedPaths)
  //    })
  //    result
  //  }
  //
  //  def depthSearch(from: Long, to: Long, path: Path, visitedPaths: mutable.Set[Path]): TupleBag = {
  //    if (!visitedPaths.contains(path)) {
  //      visitedPaths.add(path)
  //      targetLookup.changeSet
  //        .filter(t => t(sourceVertexIndex) == to)
  //        .flatMap(t => {
  //          val edge = t(1).asInstanceOf[Number].longValue
  //          val target = t(2).asInstanceOf[Number].longValue
  //          depthSearch(to, target, path :+ edge, visitedPaths)
  //        })
  //    } else {
  //      IndexedSeq[Tuple](IndexedSeq(from, path, to))
  //    }

}
