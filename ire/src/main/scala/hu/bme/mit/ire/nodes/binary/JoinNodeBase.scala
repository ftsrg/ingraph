package hu.bme.mit.ire.nodes.binary

import hu.bme.mit.ire._
import hu.bme.mit.ire.datatypes.Slot._
import hu.bme.mit.ire.datatypes.{Mask, Tuple, _}
import hu.bme.mit.ire.messages.{IncrementalChangeSet, ReteMessage, TerminatorMessage}
import hu.bme.mit.ire.util.{BufferMultimap, SizeCounter}

abstract class JoinNodeBase extends BinaryNode {
  val primaryTupleWidth: Int
  val secondaryTupleWidth: Int
  val primaryMask: Mask
  val secondaryMask: Mask

  val primaryIndexer = new JoinCache
  val secondaryIndexer = new JoinCache

  val primaryMaskInverse: Mask = Vector.range(0, primaryTupleWidth) filter (i => !primaryMask.contains(i))
  val secondaryMaskInverse: Mask = Vector.range(0, secondaryTupleWidth) filter (i => !secondaryMask.contains(i))

  override def onSizeRequest(): Long = SizeCounter.countDeeper(primaryIndexer.values, secondaryIndexer.values)

  def extract(tuple: Tuple, mask: Mask): Tuple = {
    mask.map(i => tuple(i)).toVector
  }

  def combine(tuple: Tuple, otherTuple: Tuple, slot: Slot): Tuple = {
    if (slot == Primary)
      tuple ++ extract(otherTuple, secondaryMaskInverse)
    else
      otherTuple ++ extract(tuple, secondaryMaskInverse)
  }

  def joinTuples(tuples: Iterable[Tuple], otherIndexer: JoinCache, slotMask: Mask, slot: Slot): TupleBag = {
    for {
      tuple <- tuples
      joinAttributes = slotMask.map(i => tuple(i)).toVector
      if otherIndexer.contains(joinAttributes)
      otherTupleFull <- otherIndexer(joinAttributes)
    } yield combine(tuple, otherTupleFull, slot)
  }

  def join(delta: IncrementalChangeSet, slotIndexer: JoinCache, otherIndexer: JoinCache, slotMask: Mask, otherMaskInverse: Mask, slot: Slot): Unit = {
    // join the tuples based on the other slot's indexer
    val joinedPositiveTuples: TupleBag = joinTuples(delta.positive, otherIndexer, slotMask, slot)
    val joinedNegativeTuples: TupleBag = joinTuples(delta.negative, otherIndexer, slotMask, slot)

    // maintain the content of the slot's indexer
    for (tuple <- delta.positive)
      slotIndexer.addBinding(extract(tuple, slotMask), tuple)
    for (tuple <- delta.negative)
      slotIndexer.removeBinding(extract(tuple, slotMask), tuple)

    forward(IncrementalChangeSet(joinedPositiveTuples, joinedNegativeTuples))
  }

  def onPrimary(changeSet: IncrementalChangeSet): Unit = {
    join(changeSet, primaryIndexer, secondaryIndexer, primaryMask, secondaryMaskInverse, Primary)
  }

  def onSecondary(changeSet: IncrementalChangeSet): Unit = {
    join(changeSet, secondaryIndexer, primaryIndexer, secondaryMask, primaryMaskInverse, Secondary)
  }

}
