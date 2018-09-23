package ingraph.ire.util

import java.util.Collection
import java.util.concurrent.atomic.AtomicInteger

import akka.actor.ActorRef
import ingraph.ire.datatypes.Tuple
import ingraph.ire.messages.{ChangeSet, Primary, ReteMessage, Secondary}

import scala.collection.mutable

object Utils {

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
  def countDeeper(containers: Iterable[Iterable[Tuple]]*): Long =
    containers.map(hashmap => hashmap.foldLeft(0)((sum, set) => sum + set.foldLeft(0)(_ + _.size))).sum

  def count(containers: Iterable[Iterable[Any]]*): Long = {
    containers.map(tuples => tuples.foldLeft(0)(_ + _.size)).sum
  }

  def count(containers: Collection[Tuple]): Long = {
    containers.size
  }

}
