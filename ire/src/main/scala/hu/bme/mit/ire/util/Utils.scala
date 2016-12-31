package hu.bme.mit.ire.util

import java.util.concurrent.atomic.AtomicInteger

import akka.actor.ActorRef
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.{ChangeSet, Primary, ReteMessage, Secondary}

import scala.collection.mutable

object Utils {
  def nop(id: String, b: AnyRef) = {}

  def idStringToLong(in: String): Long = {
    in.drop(1).toLong
  }

  /**
    * Produces permutations based on the change set, e.g.
    * {+ <1, 2>, <3, 4>} -> {{+ <1, 2>, <3, 4>}, {+ <3, 4>, <1, 2>}}
    * @param cs
    * @return
    */
  def changeSetPermutations(cs: ChangeSet) = {
    val values = for (
      pos <- cs.positive.permutations;
      neg <- cs.negative.permutations
    ) yield ChangeSet(pos, neg)
    values.toSeq
  }

  object conversions {
    implicit def toSendingFunction(base: ActorRef): ReteMessage => Unit = base ! _

    implicit class ReteNode(base: ActorRef) extends Serializable {
      def primary(reteMessage: ReteMessage) = {
        base ! Primary(reteMessage)
      }

      def secondary(reteMessage: ReteMessage) = {
        base ! Secondary(reteMessage)
      }
    }

  }

}

class AtomicUniqueCounter {
  private val counter: AtomicInteger = new AtomicInteger(0)

  def getNext = counter.getAndIncrement()
}

trait IterableMultiMap[A, B] extends mutable.MultiMap[A, B] {
  def multiUnzip: (Iterable[A], Iterable[B]) = {
    val b1 = genericBuilder[A]
    val b2 = genericBuilder[B]
    this.foreach(keyValueSet => {
      keyValueSet._2.foreach(value => {
        b1 += keyValueSet._1
        b2 += value
      })
    })
    (b1.result(), b2.result())
  }
}

object SizeCounter {
  def countDeeper(containers: Iterable[Iterable[Tuple]]*): Long = {
    println(containers)
    var sum = 0L
    for {
      hashmap <- containers
      set: Iterable[Tuple] <- hashmap
      tuple: Tuple <- set
    }
      sum += tuple.size
    sum
  }

  def count(containers: Iterable[Iterable[Any]]*): Long = {
    containers.map(tuples => tuples.foldLeft(0)(_ + _.size)).sum
  }
}
