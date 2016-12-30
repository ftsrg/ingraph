package hu.bme.mit.incquerydcore
import java.util.concurrent.atomic.AtomicInteger
import akka.actor.ActorRef
import org.apache.log4j.LogManager
import scala.collection.mutable
import kamon.Kamon

/**
 * Created by Maginecz on 4/28/2015.
 */
object utils {
  def nop(id: String, b: AnyRef) = {}

  def idStringToLong(in: String): Long = {
  in.drop(1).toLong
  }

  def changeSetPermutations(cs: ChangeSet) = {
  val values = for (
    pos <- cs.positive.permutations;
    neg <- cs.negative.permutations
  ) yield ChangeSet(pos, neg)
   values.toSeq
  }

  implicit class ReteNode(base: ActorRef) {
  def apply(): ReteMessage => Unit = {
    return base ! _
  }

  def primary(reteMessage: ReteMessage) = {
    base ! Primary(reteMessage)
  }

  def secondary(reteMessage: ReteMessage) = {
      base ! Secondary(reteMessage)
  }
  }

}

trait ResultLogger {
  private def logger = LogManager.getLogger(this.getClass)

  def logResult(query: String, phase: String, timeNano: Long): Unit = {
  logger.info(s"fixed\t1\tnaive-rete\t${System.getProperty("inputSize")}\t$query\t$phase\t1\ttime\t$timeNano")
  }
}
object KamonInitializer {
  Kamon.start();
  def ping = true
}
class AtomicUniqueCounter {
  private val counter: AtomicInteger = new AtomicInteger(0)

  def getNext() = counter.getAndIncrement()
}

trait IterableMultiMap[A, B] extends mutable.MultiMap[A, B]{
  def multiUnzip : (Iterable[A], Iterable[B]) = {
  val b1 = genericBuilder[A]
  val b2 = genericBuilder[B]
  this.foreach ( keyValueSet => {
    keyValueSet._2.foreach(value => {
    b1 += keyValueSet._1
    b2 += value
    })
  })
  (b1.result(), b2.result())
  }
}
