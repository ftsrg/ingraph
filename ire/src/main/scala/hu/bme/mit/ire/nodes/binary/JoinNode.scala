package hu.bme.mit.ire.nodes.binary

import hu.bme.mit.ire.datatypes.{Mask, Tuple}
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage, TerminatorMessage}
import hu.bme.mit.ire.{ForkingForwarder, _}

import scala.collection.mutable

class JoinNode(override val next: (ReteMessage) => Unit,
               override val primaryMask: Mask,
               override val secondaryMask: Mask
              ) extends JoinNodeImpl(primaryMask, secondaryMask) with SingleForwarder {
}

//class NaturalJoinNode(override val next: (ReteMessage) => Unit, val selector: Vector[Any]
//                     ) extends JoinNodeImpl(selector, selector) with SingleForwarder {}

class ParallelJoinNode(override val children: Vector[(ReteMessage) => Unit],
                       override val primaryMask: Mask,
                       override val secondaryMask: Mask,
                       hashFunction: (Tuple) => Int = n => n.hashCode()
                      ) extends JoinNodeImpl(primaryMask, secondaryMask) with ForkingForwarder {
  override def forwardHashFunction(n: Tuple): Int = hashFunction(n)

  override def forward(cs: ChangeSet) = super[ForkingForwarder].forward(cs)

  override def forward(t: TerminatorMessage) = super[ForkingForwarder].forward(t)
}

abstract class JoinNodeImpl(val primaryMask: Mask,
                            val secondaryMask: Mask)
  extends BinaryNode {
//  val primaryIndexer =
//    new mutable.HashMap[Vector[Any], mutable.Set[TupleType]]
//      with mutable.MultiMap[Vector[Any], TupleType]
//  val secondaryIndexer =
//    new mutable.HashMap[Vector[Any], mutable.Set[TupleType]]
//      with mutable.MultiMap[Vector[Any], TupleType]

  val inverseSecondaryMask = Vector.range(0, secondaryMask.length) filter (i => !secondaryMask.contains(i))

  def join(left: Set[Tuple], right: Set[Tuple]): Set[Tuple] = {
    val joined = for {
      primaryVec <- left
      key = primaryMask.map(i => primaryVec(i))
      if right.contains(key)
//      secondaryVec <- right(key)
    } yield primaryVec //++ inverseSecondaryMask.map(i => right(i))

    return joined

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
  }

  def onPrimary(changeSet: ChangeSet): Unit = {
    val positive = changeSet.positive
    val negative = changeSet.negative

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
    val positive = changeSet.positive
    val negative = changeSet.negative

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
