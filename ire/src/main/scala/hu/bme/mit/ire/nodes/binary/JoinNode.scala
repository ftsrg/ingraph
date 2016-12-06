package hu.bme.mit.ire.nodes.binary

import hu.bme.mit.ire._
import hu.bme.mit.ire.datatypes.Slot._
import hu.bme.mit.ire.datatypes.{Indexer, Mask, Tuple}
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}

import scala.collection.mutable

abstract class JoinNodeImpl(val primaryTupleWidth: Int,
                            val secondaryTupleWidth: Int,
                            override val primaryMask: Mask,
                            override val secondaryMask: Mask)
  extends AbstractJoinNode(primaryMask, secondaryMask) {

  val primaryMaskInverse:   Mask = Vector.range(0, primaryTupleWidth  ) filter (i => !primaryMask.contains(i)  )
  val secondaryMaskInverse: Mask = Vector.range(0, secondaryTupleWidth) filter (i => !secondaryMask.contains(i))

  val primaryIndexer:   Indexer = new mutable.HashMap[Tuple, mutable.Set[Tuple]] with mutable.MultiMap[Tuple, Tuple]
  val secondaryIndexer: Indexer = new mutable.HashMap[Tuple, mutable.Set[Tuple]] with mutable.MultiMap[Tuple, Tuple]

  def onPrimary(changeSet: ChangeSet): Unit = {
    join(changeSet, primaryIndexer, secondaryIndexer, primaryMask, secondaryMaskInverse, Primary)
  }

  def onSecondary(changeSet: ChangeSet): Unit = {
    join(changeSet, secondaryIndexer, primaryIndexer, secondaryMask, primaryMaskInverse, Secondary)
  }

  def join(changeSet: ChangeSet, slotIndexer: Indexer, otherIndexer: Indexer, slotMask: Mask, otherMaskInverse: Mask, slot: Slot): Unit = {
    // join the tuples based on the other slot's indexer
    val resultPositiveTuples: Vector[Tuple] = joinTuples(changeSet.positive, otherIndexer, slotMask, slot)
    val resultNegativeTuples: Vector[Tuple] = joinTuples(changeSet.negative, otherIndexer, slotMask, slot)

    // maintain the content of the slot's indexer
    for (tuple <- changeSet.positive) slotIndexer.addBinding   (extract(tuple, slotMask), tuple)
    for (tuple <- changeSet.negative) slotIndexer.removeBinding(extract(tuple, slotMask), tuple)

    forward(ChangeSet(resultPositiveTuples, resultNegativeTuples))
  }

  def combine(tuple: Tuple, otherTuple: Tuple, slot: Slot): Tuple = {
    if (slot == Primary)
      tuple ++ extract(otherTuple, secondaryMaskInverse)
    else
      otherTuple ++ extract(tuple, secondaryMaskInverse)
  }

  def joinTuples(tuples: Vector[Tuple], otherIndexer: Indexer, slotMask: Mask, slot: Slot): Vector[Tuple] = {
    for {
      tuple <- tuples
      joinAttributes = slotMask.map(i => tuple(i))
      if otherIndexer.contains(joinAttributes)
      otherTupleFull <- otherIndexer(joinAttributes)
    } yield combine(tuple, otherTupleFull, slot)
  }

}

class JoinNode(override val next: (ReteMessage) => Unit,
               override val primaryTupleWidth: Int,
               override val secondaryTupleWidth: Int,
               override val primaryMask: Mask,
               override val secondaryMask: Mask
              ) extends JoinNodeImpl(primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask) with SingleForwarder {
}

//class ParallelJoinNode(override val children: Vector[(ReteMessage) => Unit],
//                       override val primaryMask: Mask,
//                       override val secondaryMask: Mask,
//                       hashFunction: (Tuple) => Int = n => n.hashCode()
//                      ) extends JoinNodeImpl(primaryMask, secondaryMask) with ForkingForwarder {
//  override def forwardHashFunction(n: Tuple): Int = hashFunction(n)
//
//  override def forward(cs: ChangeSet) = super[ForkingForwarder].forward(cs)
//
//  override def forward(t: TerminatorMessage) = super[ForkingForwarder].forward(t)
//}
