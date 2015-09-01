import java.util.concurrent.atomic.AtomicInteger

import org.apache.log4j.LogManager
package hu.bme.mit.IQDcore {

import scala.collection.mutable
/**
 * Created by Maginecz on 4/28/2015.
 */
object utils {
  def nop(id:String, b:AnyRef) = {}
  def idStringToLong(in: String): Long = {
    in.drop(1).toLong
  }
}

trait ResultLogger {
  private def logger = LogManager.getLogger(this.getClass)
  def logResult(query:String, phase:String, timeNano: Long): Unit = {
    logger.info(s"fixed\t1\tnaive-rete\t${System.getProperty("inputSize")}\t$query\t$phase\t1\ttime\t$timeNano")
  }
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
