package hu.bme.mit.ire.nodes.binary

import hu.bme.mit.ire._
import hu.bme.mit.ire.datatypes.Slot._
import hu.bme.mit.ire.datatypes.{Indexer, Mask, Tuple}
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}

import scala.collection.mutable

class JoinNode(override val next: (ReteMessage) => Unit,
               override val primaryTupleWidth: Int,
               override val secondaryTupleWidth: Int,
               override val primaryMask: Mask,
               override val secondaryMask: Mask
              ) extends JoinNodeImpl(primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask) with SingleForwarder {
}

//class NaturalJoinNode(override val next: (ReteMessage) => Unit, val selector: Vector[Any]
//                     ) extends JoinNodeImpl(selector, selector) with SingleForwarder {}
//
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

abstract class JoinNodeImpl(val primaryTupleWidth: Int, val secondaryTupleWidth: Int,
                            val primaryMask: Mask, val secondaryMask: Mask) extends BinaryNode {
  val primaryIndexer:   Indexer = new mutable.HashMap[Tuple, mutable.Set[Tuple]] with mutable.MultiMap[Tuple, Tuple]
  val secondaryIndexer: Indexer = new mutable.HashMap[Tuple, mutable.Set[Tuple]] with mutable.MultiMap[Tuple, Tuple]

  val primaryMaskInverse:   Mask = Vector.range(0, primaryTupleWidth  ) filter (i => !primaryMask.contains(i)  )
  val secondaryMaskInverse: Mask = Vector.range(0, secondaryTupleWidth) filter (i => !secondaryMask.contains(i))

  def extract(tuple: Tuple, mask: Mask): Tuple = {
    mask.map(i => tuple(i))
  }

  def combine(tuple: Tuple, otherTuple: Tuple, slot: Slot): Tuple = {
    if (slot == Primary)
      tuple ++ extract(otherTuple, secondaryMaskInverse)
    else
      otherTuple ++ extract(tuple, secondaryMaskInverse)
  }

  def join(delta: ChangeSet, slotIndexer: Indexer, otherIndexer: Indexer, slotMask: Mask, otherMaskInverse: Mask, slot: Slot): Unit = {
    // calculate the tuples based on the other slot's indexer
    println(delta.positive)

    val joinedPositiveTuples: Vector[Tuple] = for {
      tuple <- delta.positive
      joinAttributes = slotMask.map(i => tuple(i))
      if otherIndexer.contains(joinAttributes)
        otherTupleFull <- otherIndexer(joinAttributes)
    } yield combine(tuple, otherTupleFull, slot)

    val joinedNegativeTuples: Vector[Tuple] = Vector[Tuple]()

    // maintain the content of the slot's indexer
    for (tuple <- delta.positive) {
      slotIndexer.addBinding(extract(tuple, slotMask), tuple)
    }
    for (tuple <- delta.negative) {
      slotIndexer.removeBinding(extract(tuple, slotMask), tuple)
    }

    forward(ChangeSet(joinedPositiveTuples, joinedNegativeTuples))
  }

  def onPrimary(changeSet: ChangeSet): Unit = {
    join(changeSet, primaryIndexer, secondaryIndexer, primaryMask, secondaryMaskInverse, Primary)

    //    val joinedPositive = for {
    //      primary <- positive
    //      key = primarySelector.map(i => primary(i))
    //      if secondaryIndexer.contains(key)
    //      secondary <- secondaryIndexer(key)
    //    } yield primary ++ secondary.filterKeys(k => !primary.keySet.contains(k))
    //
    //    val joinedNegative = for {
    //      primary <- negative
    //      key = primarySelector.map(i => primary(i))
    //      if secondaryIndexer.contains(key)
    //      secondary <- secondaryIndexer(key)
    //    } yield primary ++ secondary.filterKeys(k => !primary.keySet.contains(k))
    //
    //    forward(ChangeSet(joinedPositive, joinedNegative))
    //    positive.foreach(
    //      m => {
    //        val key = primarySelector.map(i => m(i))
    //        primaryIndexer.addBinding(key, m)
    //      }
    //    )
    //    negative.foreach(
    //      m => {
    //        val key = primarySelector.map(i => m(i))
    //        primaryIndexer.removeBinding(key, m)
    //      }
    //    )
  }

  def onSecondary(changeSet: ChangeSet): Unit = {
    join(changeSet, secondaryIndexer, primaryIndexer, secondaryMask, primaryMaskInverse, Secondary)

    //    val joinedPositive = for {
    //      secondary <- positive
    //      key = secondarySelector.map(i => secondary(i))
    //      if primaryIndexer.contains(key)
    //      primary <- primaryIndexer(key)
    //    } yield primary ++ secondary.filterKeys(k => !primary.keySet.contains(k))
    //
    //
    //    val joinedNegative = for {
    //      secondary <- negative
    //      key = secondarySelector.map(i => secondary(i))
    //      if primaryIndexer.contains(key)
    //      primary <- primaryIndexer(key)
    //    } yield primary ++ secondary.filterKeys(k => !primary.keySet.contains(k))
    //
    //    forward(ChangeSet(joinedPositive, joinedNegative))
    //
    //    positive.foreach(
    //      m => {
    //        val key = secondarySelector.map(i => m(i))
    //        secondaryIndexer.addBinding(key, m) //must be used with multimaps
    //      }
    //    )
    //    negative.foreach(
    //      m => {
    //        val key = secondarySelector.map(i => m(i))
    //        secondaryIndexer.removeBinding(key, m)
    //      }
    //    )
  }
}
