package hu.bme.mit.ire.stateless.binary

import hu.bme.mit.ire.datatypes.Slot._
import hu.bme.mit.ire.datatypes.{Mask, Tuple, _}
import hu.bme.mit.ire.messages.{BatchChangeSet, IncrementalChangeSet}
import hu.bme.mit.ire.util.SizeCounter

abstract class JoinNodeBase extends StatelessBinaryNode {
  val primaryTupleWidth: Int
  val secondaryTupleWidth: Int
  val primaryMask: Mask
  val secondaryMask: Mask

  var primaryChangeSet = new BatchChangeSet
  var secondaryIndexer = new JoinCache

  val primaryMaskInverse: Mask = Vector.range(0, primaryTupleWidth) filter (i => !primaryMask.contains(i))
  val secondaryMaskInverse: Mask = Vector.range(0, secondaryTupleWidth) filter (i => !secondaryMask.contains(i))

  override def onSizeRequest(): Long = 0

  def extract(tuple: Tuple, mask: Mask): Tuple = {
    mask.map(i => tuple(i))
  }

  def combine(tuple: Tuple, otherTuple: Tuple): Tuple = {
    tuple ++ extract(otherTuple, secondaryMaskInverse)
  }

  def joinTuples(): TupleBag = {
    for {
      tuple <- primaryChangeSet.changeSet
      joinAttributes = primaryMask.map(i => tuple(i))
      if secondaryIndexer.contains(joinAttributes)
      otherTupleFull <- secondaryIndexer(joinAttributes)
    } yield combine(tuple, otherTupleFull)
  }

  def onPrimary(cs: BatchChangeSet): Unit = {
    primaryChangeSet = cs
    onUpdate()
  }

  def onSecondary(cs: BatchChangeSet): Unit = {
    secondaryIndexer = new JoinCache
    cs.changeSet.foreach(
      t => secondaryIndexer.addBinding(extract(t, secondaryMask), t)
    )
    onUpdate()
  }

  def onUpdate(): Unit = {
    val bcs = BatchChangeSet(joinTuples)
    forward(bcs)
  }

}
