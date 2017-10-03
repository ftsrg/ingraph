package hu.bme.mit.nre.nodes.binary

import hu.bme.mit.ire._
import hu.bme.mit.ire.datatypes.{Mask, Tuple, TupleBag}
import hu.bme.mit.ire.messages.{BatchChangeSet, ReteMessage}

class JoinNode(val next: (ReteMessage) => Unit,
               val primaryTupleWidth: Int,
               val secondaryTupleWidth: Int,
               primaryMask: Mask,
               secondaryMask: Mask
              ) extends AbstractJoinNode(primaryMask, secondaryMask) with SingleForwarder {

  val secondaryMaskInverse: Mask = Vector.range(0, secondaryTupleWidth) filter (i => !secondaryMask.contains(i))

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

  def onUpdate(): Unit = {
    forwardAny(BatchChangeSet(joinTuples()))
  }
}
