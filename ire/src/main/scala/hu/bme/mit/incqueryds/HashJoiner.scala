package hu.bme.mit.incqueryds

import com.gs.collections.api.multimap.MutableMultimap
import com.gs.collections.impl.multimap.AbstractMutableMultimap
import com.gs.collections.impl.multimap.bag.HashBagMultimap
import com.gs.collections.impl.multimap.list.FastListMultimap
import com.gs.collections.impl.multimap.set.UnifiedSetMultimap

import scala.collection.mutable
import scala.collection.mutable.MultiMap

/**
  * Created by wafle on 12/25/2015.
  */
class HashJoiner(override val next: (ReteMessage) => Unit,
                 override val primaryLength: Int, override val primarySelector: Vector[Int],
                 override val secondaryLength: Int, override val secondarySelector: Vector[Int]
                ) extends HashJoinerImpl(primaryLength, primarySelector, secondaryLength, secondarySelector) with SingleForwarder {
}

class ParallelHashJoiner(override val children: Vector[(ReteMessage) => Unit],
                         override val primaryLength: Int, override val primarySelector: Vector[Int],
                         override val secondaryLength: Int, override val secondarySelector: Vector[Int],
                         hashFunction: (nodeType) => Int = n => n.hashCode()
                        ) extends HashJoinerImpl(primaryLength, primarySelector, secondaryLength, secondarySelector) with ForkingForwarder {
  override def forwardHashFunction(n: nodeType): Int = hashFunction(n)

  override def forward(cs: ChangeSet) = super[ForkingForwarder].forward(cs)
  override def forward(t: TerminatorMessage) = super[ForkingForwarder].forward(t)
}

abstract class HashJoinerImpl(val primaryLength: Int, val primarySelector: Vector[Int],
                              val secondaryLength: Int, val secondarySelector: Vector[Int])
  extends BetaNode {
  val primaryValues = new mutable.HashMap[nodeType, mutable.Set[nodeType]] with MultiMap[nodeType, nodeType]
  val secondaryValues = new mutable.HashMap[nodeType, mutable.Set[nodeType]] with mutable.MultiMap[nodeType, nodeType]

  val inversePrimarySelector = Vector.range(0, primaryLength) filter (i => !primarySelector.contains(i))
  val inverseSecondarySelector = Vector.range(0, secondaryLength) filter (i => !secondarySelector.contains(i))


  def onPrimary(changeSet: ChangeSet): Unit = {
    val positive = changeSet.positive
    val negative = changeSet.negative

    val joinedPositive = for {
      primaryVec <- positive
      key = primarySelector.map(i => primaryVec(i))
      if secondaryValues.contains(key)
      secondaryVec <- secondaryValues(key)
    } yield primaryVec ++ inverseSecondarySelector.map(i => secondaryVec(i))

    val joinedNegative = for {
      primaryVec <- negative
      key = primarySelector.map(i => primaryVec(i))
      if secondaryValues.contains(key)
      secondaryVec <- secondaryValues(key)
    } yield primaryVec ++ inverseSecondarySelector.map(i => secondaryVec(i))

    forward(ChangeSet(joinedPositive, joinedNegative))
    positive.foreach(
      vec => {
        val key = primarySelector.map(i => vec(i))
        primaryValues.addBinding(key, vec)
      }
    )
    negative.foreach(
      vec => {
        val key = primarySelector.map(i => vec(i))
        primaryValues.removeBinding(key, vec)
      }
    )
  }

  def onSecondary(changeSet: ChangeSet): Unit = {
    val positive = changeSet.positive
    val negative = changeSet.negative

    val joinedPositive = for {
      secondaryVec <- positive
      key = secondarySelector.map(i => secondaryVec(i))
      if primaryValues.contains(key)
      primaryVec <- primaryValues(key)
    } yield primaryVec ++ inverseSecondarySelector.map(i => secondaryVec(i))


    val joinedNegative = for {
      secondaryVec <- negative
      key = secondarySelector.map(i => secondaryVec(i))
      if primaryValues.contains(key)
      primaryVec <- primaryValues(key)
    } yield primaryVec ++ inverseSecondarySelector.map(i => secondaryVec(i))

    forward(ChangeSet(joinedPositive, joinedNegative))

    positive.foreach(
      vec => {
        val key = secondarySelector.map(i => vec(i))
        secondaryValues.addBinding(key, vec) //must be used with multimaps
      }
    )
    negative.foreach(
      vec => {
        val key = secondarySelector.map(i => vec(i))
        secondaryValues.removeBinding(key, vec)
      }
    )
  }
}

abstract class GSHashJoiner()
  extends BetaNode with SingleForwarder {
  val primaryValues: MutableMultimap[nodeType, nodeType]
  val secondaryValues: MutableMultimap[nodeType, nodeType]

  val primaryLength: Int
  val primarySelector: Vector[Int]
  val secondaryLength: Int
  val secondarySelector: Vector[Int]

  val inversePrimarySelector = Vector.range(0, primaryLength) filter (i => !primarySelector.contains(i))
  val inverseSecondarySelector = Vector.range(0, secondaryLength) filter (i => !secondarySelector.contains(i))

  import scala.collection.JavaConversions._

  def onPrimary(changeSet: ChangeSet): Unit = {
    val positive = changeSet.positive
    val negative = changeSet.negative

    val joinedPositive = for {
      primaryVec <- positive
      key = primarySelector.map(i => primaryVec(i))
      if secondaryValues.containsKey(key)
      secondaryVec <- secondaryValues.get(key)
    } yield primaryVec ++ inverseSecondarySelector.map(i => secondaryVec(i))


    val joinedNegative = for {
      primaryVec <- negative
      key = primarySelector.map(i => primaryVec(i))
      if secondaryValues.containsKey(key)
      secondaryVec <- secondaryValues.get(key)
    } yield primaryVec ++ inverseSecondarySelector.map(i => secondaryVec(i))

    forward(ChangeSet(joinedPositive, joinedNegative))
    positive.foreach(
      vec => {
        val key = primarySelector.map(i => vec(i))
        primaryValues.put(key, vec)
      }
    )
    negative.foreach(
      vec => {
        val key = primarySelector.map(i => vec(i))
        primaryValues.remove(key, vec)
      }
    )
  }

  def onSecondary(changeSet: ChangeSet): Unit = {
    val positive = changeSet.positive
    val negative = changeSet.negative

    val joinedPositive = for {
      secondaryVec <- positive
      key = secondarySelector.map(i => secondaryVec(i))
      if primaryValues.containsKey(key)
      primaryVec <- primaryValues.get(key)
    } yield primaryVec ++ inverseSecondarySelector.map(i => secondaryVec(i))


    val joinedNegative = for {
      secondaryVec <- negative
      key = secondarySelector.map(i => secondaryVec(i))
      if primaryValues.containsKey(key)
      primaryVec <- primaryValues.get(key)
    } yield primaryVec ++ inverseSecondarySelector.map(i => secondaryVec(i))

    forward(ChangeSet(joinedPositive, joinedNegative))

    positive.foreach(
      vec => {
        val key = secondarySelector.map(i => vec(i))
        secondaryValues.put(key, vec) //must be used with multimaps
      }
    )
    negative.foreach(
      vec => {
        val key = secondarySelector.map(i => vec(i))
        secondaryValues.remove(key, vec)
      }
    )
  }
}

class FastMultimapHashJoiner(override val next: (ReteMessage) => Unit,
                             override val primaryLength: Int, override val primarySelector: Vector[Int],
                             override val secondaryLength: Int, override val secondarySelector: Vector[Int]) extends GSHashJoiner {
  override val primaryValues = new FastListMultimap[nodeType, nodeType]
  override val secondaryValues = new FastListMultimap[nodeType, nodeType]
}

class UnifiedSetMultimapHashJoiner(override val next: (ReteMessage) => Unit,
                             override val primaryLength: Int, override val primarySelector: Vector[Int],
                             override val secondaryLength: Int, override val secondarySelector: Vector[Int]) extends GSHashJoiner {
  override val primaryValues = new UnifiedSetMultimap[nodeType, nodeType]
  override val secondaryValues = new UnifiedSetMultimap[nodeType, nodeType]
}

class HashBagMultimapHashJoiner(override val next: (ReteMessage) => Unit,
                                   override val primaryLength: Int, override val primarySelector: Vector[Int],
                                   override val secondaryLength: Int, override val secondarySelector: Vector[Int]) extends GSHashJoiner {
  override val primaryValues = new HashBagMultimap[nodeType, nodeType]
  override val secondaryValues = new HashBagMultimap[nodeType, nodeType]
}
