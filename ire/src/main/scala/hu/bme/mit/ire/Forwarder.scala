package hu.bme.mit.ire

import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages._

trait ForkingForwarder extends Forwarder {
  val children: Vector[ReteMessage => Unit]

  if (children.size < 2)
    throw new IllegalArgumentException("use base class for 1 child node")

  def forwardHashFunction(n: Tuple): Int

  def forward(cs: DataMessage) = {
    cs match {
      case cs: IncrementalChangeSet => {
        cs.positive.groupBy(
          node => Math.abs(forwardHashFunction(node)) % children.size).foreach(
          kv => if (kv._2.nonEmpty) children(kv._1)(IncrementalChangeSet(positive = kv._2)))
        cs.negative.groupBy(
          node => Math.abs(forwardHashFunction(node)) % children.size).foreach(
          kv => if (kv._2.nonEmpty) children(kv._1)(IncrementalChangeSet(negative = kv._2)))
      }
      case sl: BatchChangeSet => {
        sl.changeSet.groupBy(
          node => Math.abs(forwardHashFunction(node)) % children.size).foreach(
          kv => if (kv._2.nonEmpty) children(kv._1)(IncrementalChangeSet(positive = kv._2)))
      }
    }
  }

  def forward(t: TerminatorMessage) = children.foreach(_(t))

}

trait SingleForwarder extends Forwarder {
  val next: ReteMessage => Unit

  def forward(cs: DataMessage) = if (cs.nonEmpty()) next(cs)
  def forwardAny(cs: DataMessage) = next(cs)
  def forward(terminator: TerminatorMessage) = next(terminator)
}

trait Forwarder {
  val name: String

  def forward(cs: DataMessage)

  def forward(terminator: TerminatorMessage)
}
