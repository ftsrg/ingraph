package hu.bme.mit.incqueryds

import scala.collection.mutable
import scala.collection.mutable.MultiMap

/**
  * Created by wafle on 12/25/2015.
  */
class HashJoiner(override val next: (ReteMessage) => Unit,
         override val primarySelector: Vector[Any],
         override val secondarySelector: Vector[Any]
        ) extends HashJoinerImpl(primarySelector, secondarySelector) with SingleForwarder {
}

class NaturalJoiner(override val next: (ReteMessage) => Unit, val selector: Vector[Any]
           ) extends HashJoinerImpl(selector,selector) with SingleForwarder {}

class ParallelHashJoiner(override val children: Vector[(ReteMessage) => Unit],
             override val primarySelector: Vector[Any],
             override val secondarySelector: Vector[Any],
             hashFunction: (nodeType) => Int = n => n.hashCode()
            ) extends HashJoinerImpl(primarySelector, secondarySelector) with ForkingForwarder {
  override def forwardHashFunction(n: nodeType): Int = hashFunction(n)

  override def forward(cs: ChangeSet) = super[ForkingForwarder].forward(cs)
  override def forward(t: TerminatorMessage) = super[ForkingForwarder].forward(t)
}

abstract class HashJoinerImpl(val primarySelector: Vector[Any],
                val secondarySelector: Vector[Any])
  extends BetaNode {
  val primaryValues = new mutable.HashMap[Vector[Any], mutable.Set[nodeType]] with MultiMap[Vector[Any], nodeType]
  val secondaryValues = new mutable.HashMap[Vector[Any], mutable.Set[nodeType]] with mutable.MultiMap[Vector[Any], nodeType]

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
