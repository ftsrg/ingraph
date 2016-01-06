package hu.bme.mit.incqueryds

import java.util
import java.util.Comparator

import com.gs.collections.api.multimap.MutableMultimap
import com.gs.collections.impl.map.sorted.mutable.TreeSortedMap
import com.gs.collections.impl.multimap.bag.sorted.mutable.TreeBagMultimap
import com.gs.collections.impl.multimap.set.sorted.TreeSortedSetMultimap
import com.gs.collections.impl.set.mutable.UnifiedSet

import scala.collection.mutable
import scala.util.control.Breaks._

/**
  * Created by wafle on 12/28/2015.
  */
class SortedMergeJoiner(override val next: (ReteMessage) => Unit,
                        comparator: Comparator[nodeType],
                        val primaryLength: Int, val primarySelector: Vector[Int],
                        val secondaryLength: Int, val secondarySelector: Vector[Int],
                        override val expectedTerminatorCount: Int = 2)
  extends BetaNode with SingleForwarder {
  val inversePrimarySelector = Vector.range(0, primaryLength) filter (i => !primarySelector.contains(i))
  val inverseSecondarySelector = Vector.range(0, secondaryLength) filter (i => !secondarySelector.contains(i))

  val primaryValues = new util.TreeMap[nodeType, mutable.Set[nodeType]](comparator)
  val secondaryValues = new util.TreeMap[nodeType, mutable.Set[nodeType]](comparator)

  override def onPrimary(changeSet: ChangeSet): Unit = {
    val incoming = changeSet.positive.sortWith((a, b) => comparator.compare(a, b) < 0).iterator
    val secondary = secondaryValues.keySet().iterator

    val matches = new mutable.HashSet[nodeType]

    breakable {
      if (!incoming.hasNext || !secondary.hasNext)
        break
      var nextIn = incoming.next()
      var nextSec = secondary.next()
      while (true) {
        val cmp = comparator.compare(nextIn, nextSec)
        if (cmp == 0) {
          secondaryValues.get(nextSec).foreach(m => matches += nextIn ++ inverseSecondarySelector.map(i => m(i)))
          if (!incoming.hasNext || !secondary.hasNext) {
            break
          }
          nextIn = incoming.next()
          nextSec = secondary.next()
        } else if (cmp > 0) {
          if (!secondary.hasNext) {
            break
          }
          nextSec = secondary.next()
        } else if (cmp < 0) {
          if (!incoming.hasNext) { //nextIn is smaller
            break
          }
          nextIn = incoming.next()
        }
      }
    }

    forward(ChangeSet(positive = matches.toVector))

    changeSet.positive.foreach(
      vec => {
        val key = primarySelector.map(i => vec(i))
        val value = primaryValues.get(key)
        if (value == null) {
          primaryValues.put(key, mutable.HashSet(vec))
        } else value.add(vec)
      }
    )
  }

  override def onSecondary(changeSet: ChangeSet): Unit = {
    val incoming = changeSet.positive.sortWith((a, b) => comparator.compare(a, b) < 0).iterator
    val primary = primaryValues.keySet().iterator

    val matches = new mutable.HashSet[nodeType]

    breakable {
      if (!(incoming.hasNext && primary.hasNext)) {
        break
      }

      var nextIn = incoming.next()
      var nextPrim = primary.next()
      while (true) {
        val cmp = comparator.compare(nextPrim, nextIn)
        if (cmp == 0) {
          primaryValues.get(nextPrim).foreach(m => matches += nextIn ++ inversePrimarySelector.map(i => m(i)))
          if (!(incoming.hasNext && primary.hasNext)) {
            break
          }
          nextIn = incoming.next()
          nextPrim = primary.next()
        } else if (cmp < 0) {
          if (!primary.hasNext) {
            break
          }
          nextPrim = primary.next()
        } else if (cmp > 0) {
          if (!incoming.hasNext) {
            break
          }
          nextIn = incoming.next()
        }
      }
    }

    forward(ChangeSet(positive = matches.toVector))

    changeSet.positive.foreach(
      vec => {
        val key = secondarySelector.map(i => vec(i))
        val value = secondaryValues.get(key)
        if (value == null) {
          secondaryValues.put(key, mutable.HashSet(vec))
        } else value.add(vec)
      }
    )
  }
}

object Helper {
  // A "singleton" object to hold the implicit conversion methods
  /*
  * These three methods each take a closure and return an anonymous instance
  * of the corresponding GS Collections interface
  */

  import com.gs.collections.api.block.function.primitive.{DoubleFunction, FloatFunction, IntFunction, LongFunction}
  import com.gs.collections.api.block.function.{Function, Function0, Function2, Function3}
  import com.gs.collections.api.block.predicate.{Predicate, Predicate2}
  import com.gs.collections.api.block.procedure.primitive.ObjectIntProcedure
  import com.gs.collections.api.block.procedure.{Procedure, Procedure2}

  implicit def closure2Procedure[T](closure: (T) => Unit): Procedure[T] =
    new Procedure[T] {
      def value(each: T) = closure(each)
    }

  implicit def closure2Procedure2[T1, T2](closure: (T1, T2) => Unit): Procedure2[T1, T2] =
    new Procedure2[T1, T2] {
      def value(t1: T1, t2: T2) = closure(t1, t2)
    }

  implicit def closure2Function[T, V](closure: (T) => V): Function[T, V] =
    new Function[T, V] {
      def valueOf(t: T) = closure(t)
    }

  implicit def closure2Function2[T1, T2, V](closure: (T1, T2) => V): Function2[T1, T2, V] =
    new Function2[T1, T2, V] {
      def value(t1: T1, t2: T2) = closure(t1, t2)
    }

  implicit def closure2Function3[T1, T2, T3, V](closure: (T1, T2, T3) => V): Function3[T1, T2, T3, V] =
    new Function3[T1, T2, T3, V] {
      def value(t1: T1, t2: T2, t3: T3) = closure(t1, t2, t3)
    }

  implicit def closure2Predicate[T](closure: (T) => Boolean): Predicate[T] =
    new Predicate[T] {
      def accept(each: T) = closure(each)
    }

  implicit def closure2Predicate2[T1, T2](closure: (T1, T2) => Boolean): Predicate2[T1, T2] =
    new Predicate2[T1, T2] {
      def accept(t1: T1, t2: T2) = closure(t1, t2)
    }

  implicit def closure2ObjectIntProcedure[T](closure: (T, Int) => Unit): ObjectIntProcedure[T] =
    new ObjectIntProcedure[T] {
      def value(each: T, index: Int) = closure(each, index)
    }

  implicit def closure2Runnable(closure: () => Unit): Runnable =
    new Runnable {
      def run() = closure()
    }

  implicit def closure2CodeBlock[T](closure: () => T): Function0[T] =
    new Function0[T] {
      def value = closure()
    }

  implicit def closure2Comparator[T](closure: (T, T) => Int): Comparator[T] =
    new Comparator[T] {
      def compare(o1: T, o2: T) = closure(o1, o2)
    }

  implicit def closure2IntFunction[T](closure: (T) => Int): IntFunction[T] =
    new IntFunction[T] {
      def intValueOf(each: T) = closure(each)
    }

  implicit def closure2LongFunction[T](closure: (T) => Long): LongFunction[T] =
    new LongFunction[T] {
      def longValueOf(each: T) = closure(each)
    }

  implicit def closure2DoubleFunction[T](closure: (T) => Double): DoubleFunction[T] =
    new DoubleFunction[T] {
      def doubleValueOf(each: T) = closure(each)
    }

  implicit def closure2FloatFunction[T](closure: (T) => Float): FloatFunction[T] =
    new FloatFunction[T] {
      def floatValueOf(each: T) = closure(each)
    }
}

class GSSortedMergeJoiner(override val next: (ReteMessage) => Unit,
                          val comparator: Comparator[nodeType],
                          val primaryLength: Int, val primarySelector: Vector[Int],
                          val secondaryLength: Int, val secondarySelector: Vector[Int],
                          override val expectedTerminatorCount: Int = 2)
  extends BetaNode with SingleForwarder {
  val inversePrimarySelector = Vector.range(0, primaryLength) filter (i => !primarySelector.contains(i))
  val inverseSecondarySelector = Vector.range(0, secondaryLength) filter (i => !secondarySelector.contains(i))

  val primaryValues = new TreeSortedMap[nodeType, UnifiedSet[nodeType]](comparator)
  val secondaryValues = new TreeSortedMap[nodeType, UnifiedSet[nodeType]](comparator)

  override def onPrimary(changeSet: ChangeSet): Unit = {
    val incoming = changeSet.positive.sortWith((a, b) => comparator.compare(a, b) < 0).iterator
    val secondary = secondaryValues.keysView().iterator()

    val matches = new mutable.HashSet[nodeType]

    breakable {
      if (!incoming.hasNext || !secondary.hasNext)
        break
      var nextIn = incoming.next()
      var nextSec = secondary.next()
      while (true) {
        val cmp = comparator.compare(nextIn, nextSec)
        if (cmp == 0) {
          //scala cannot handle java lambdas, so we convert the closures implicitly:
          import Helper._
          secondaryValues.get(nextSec).each { (m: nodeType) => (matches += nextIn ++ inverseSecondarySelector.map(i => m(i))): Unit }
          if (!incoming.hasNext || !secondary.hasNext) {
            break
          }
          nextIn = incoming.next()
          nextSec = secondary.next()
        } else if (cmp > 0) {
          if (!secondary.hasNext) {
            break
          }
          nextSec = secondary.next()
        } else if (cmp < 0) { //nextIn is smaller
          if (!incoming.hasNext) {
            break
          }
          nextIn = incoming.next()
        }
      }
    }

    forward(ChangeSet(positive = matches.toVector))

    changeSet.positive.foreach(
      vec => {
        val key = primarySelector.map(i => vec(i))
        val value = primaryValues.get(key)
        if (value == null) {
          val set = new UnifiedSet[nodeType]
          set.add(vec)
          primaryValues.put(key, set)
        } else value.add(vec)
      }
    )
  }

  override def onSecondary(changeSet: ChangeSet): Unit = {
    val incoming = changeSet.positive.sortWith((a, b) => comparator.compare(a, b) < 0).iterator
    val primary = primaryValues.keysView().iterator

    val matches = new mutable.HashSet[nodeType]

    breakable {
      if (!(incoming.hasNext && primary.hasNext)) {
        break
      }

      var nextIn = incoming.next()
      var nextPrim = primary.next()
      while (true) {
        val cmp = comparator.compare(nextPrim, nextIn)
        if (cmp == 0) {
          import Helper._
          primaryValues.get(nextPrim).forEach((m: nodeType) => (matches += nextIn ++ inversePrimarySelector.map(i => m(i))): Unit)
          if (!(incoming.hasNext && primary.hasNext)) {
            break
          }
          nextIn = incoming.next()
          nextPrim = primary.next()
        } else if (cmp < 0) { //nextPrim is smaller
          if (!primary.hasNext) {
            break
          }
          nextPrim = primary.next()
        } else if (cmp > 0) { //nextIn is smaller
          if (!incoming.hasNext) {
            break
          }
          nextIn = incoming.next()
        }
      }
    }

    forward(ChangeSet(positive = matches.toVector))

    changeSet.positive.foreach(
      vec => {
        val key = secondarySelector.map(i => vec(i))
        val value = secondaryValues.get(key)
        if (value == null) {
          val set = new UnifiedSet[nodeType]
          set.add(vec)
          secondaryValues.put(key, set)
        } else value.add(vec)
      }
    )
  }
}
