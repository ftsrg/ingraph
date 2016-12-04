package hu.bme.mit.ire.nodes.binary

import hu.bme.mit.ire.datatypes.TupleType
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage, TerminatorMessage}
import hu.bme.mit.ire.{ForkingForwarder, _}

import scala.collection.mutable

/**
  * Created by wafle on 12/25/2015.
  */
class JoinNode(override val next: (ReteMessage) => Unit,
               override val primarySelector: Vector[Any],
               override val secondarySelector: Vector[Any]
                ) extends JoinNodeImpl(primarySelector, secondarySelector) with SingleForwarder {
}

class NaturalJoinNode(override val next: (ReteMessage) => Unit, val selector: Vector[Any]
                   ) extends JoinNodeImpl(selector,selector) with SingleForwarder {}

class ParallelJoinNode(override val children: Vector[(ReteMessage) => Unit],
                       override val primarySelector: Vector[Any],
                       override val secondarySelector: Vector[Any],
                       hashFunction: (TupleType) => Int = n => n.hashCode()
                        ) extends JoinNodeImpl(primarySelector, secondarySelector) with ForkingForwarder {
  override def forwardHashFunction(n: TupleType): Int = hashFunction(n)

  override def forward(cs: ChangeSet) = super[ForkingForwarder].forward(cs)
  override def forward(t: TerminatorMessage) = super[ForkingForwarder].forward(t)
}

abstract class JoinNodeImpl(val primarySelector: Vector[Any],
                            val secondarySelector: Vector[Any])
  extends BinaryNode {
  val primaryValues = new mutable.HashMap[Vector[Any], mutable.Set[TupleType]]
    with mutable.MultiMap[Vector[Any], TupleType]
  val secondaryValues = new mutable.HashMap[Vector[Any], mutable.Set[TupleType]]
    with mutable.MultiMap[Vector[Any], TupleType]

  def onPrimary(changeSet: ChangeSet): Unit = {
    val positive = changeSet.positive
    val negative = changeSet.negative

    val joinedPositive = for {
      primary <- positive
      key = primarySelector.map(i => primary(i))
      if secondaryValues.contains(key)
      secondary <- secondaryValues(key)
    } yield primary ++ secondary.filterKeys( k => !primary.keySet.contains(k))

    val joinedNegative = for {
      primary <- negative
      key = primarySelector.map(i => primary(i))
      if secondaryValues.contains(key)
      secondary <- secondaryValues(key)
    } yield primary ++ secondary.filterKeys( k => !primary.keySet.contains(k))

    forward(ChangeSet(joinedPositive, joinedNegative))
    positive.foreach(
      m => {
        val key = primarySelector.map(i => m(i))
        primaryValues.addBinding(key, m)
      }
    )
    negative.foreach(
      m => {
        val key = primarySelector.map(i => m(i))
        primaryValues.removeBinding(key, m)
      }
    )
  }

  def onSecondary(changeSet: ChangeSet): Unit = {
    val positive = changeSet.positive
    val negative = changeSet.negative

    val joinedPositive = for {
      secondary <- positive
      key = secondarySelector.map(i => secondary(i))
      if primaryValues.contains(key)
      primary <- primaryValues(key)
    } yield primary ++ secondary.filterKeys( k => !primary.keySet.contains(k))


    val joinedNegative = for {
      secondary <- negative
      key = secondarySelector.map(i => secondary(i))
      if primaryValues.contains(key)
      primary <- primaryValues(key)
    } yield primary ++ secondary.filterKeys( k => !primary.keySet.contains(k))

    forward(ChangeSet(joinedPositive, joinedNegative))

    positive.foreach(
      m => {
        val key = secondarySelector.map(i => m(i))
        secondaryValues.addBinding(key, m) //must be used with multimaps
      }
    )
    negative.foreach(
      m => {
        val key = secondarySelector.map(i => m(i))
        secondaryValues.removeBinding(key, m)
      }
    )
  }
}
