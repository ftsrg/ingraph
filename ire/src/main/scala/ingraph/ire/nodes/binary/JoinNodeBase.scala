package ingraph.ire.nodes.binary

import ingraph.ire.datatypes.Slot._
import ingraph.ire.datatypes.{Mask, Tuple, _}
import ingraph.ire.messages.ChangeSet
import ingraph.ire.util.SizeCounter

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

  def extract(tuple: Tuple, mask: Mask): Seq[Any] = {
    mask.map(i => tuple(i))
  }

  def combine(tuple: Tuple, otherTuple: Tuple, slot: Slot): Tuple = {
    if (slot == Primary)
      tuple ++ extract(otherTuple, secondaryMaskInverse)
    else
      otherTuple ++ extract(tuple, secondaryMaskInverse)
  }

  def joinTuples(tuples: Vector[Tuple], otherIndexer: JoinCache, slotMask: Mask, slot: Slot): Iterable[Tuple] = {
    for {
      tuple <- tuples
      joinAttributes = slotMask.map(i => tuple(i)).toVector
      if otherIndexer.contains(joinAttributes)
      otherTupleFull <- otherIndexer(joinAttributes)
    } yield combine(tuple, otherTupleFull, slot)
  }

  def join(delta: ChangeSet, slotIndexer: JoinCache, otherIndexer: JoinCache, slotMask: Mask, otherMaskInverse: Mask, slot: Slot): Unit = {
    // join the tuples based on the other slot's indexer
    val joinedPositiveTuples = joinTuples(delta.positive, otherIndexer, slotMask, slot)
    val joinedNegativeTuples = joinTuples(delta.negative, otherIndexer, slotMask, slot)

    forward(ChangeSet(joinedPositiveTuples.toVector, joinedNegativeTuples.toVector))

    // maintain the content of the slot's indexer
    for (tuple <- delta.positive)
      slotIndexer.addBinding(extract(tuple, slotMask), tuple)
    for (tuple <- delta.negative)
      slotIndexer.removeBinding(extract(tuple, slotMask), tuple)

  }

  def onPrimary(changeSet: ChangeSet): Unit = {
    join(changeSet, primaryIndexer, secondaryIndexer, primaryMask, secondaryMaskInverse, Primary)
  }

  def onSecondary(changeSet: ChangeSet): Unit = {
    join(changeSet, secondaryIndexer, primaryIndexer, secondaryMask, primaryMaskInverse, Secondary)
  }

}
