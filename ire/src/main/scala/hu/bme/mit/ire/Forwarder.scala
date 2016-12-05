package hu.bme.mit.ire

import hu.bme.mit.ire.datatypes.TupleType
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage, TerminatorMessage}

trait ForkingForwarder extends Forwarder {
  val children: Vector[ReteMessage => Unit]

  if (children.size < 2)
    throw new IllegalArgumentException("use base class for 1 child node")

  def forwardHashFunction(n: TupleType): Int

  def forward(cs: ChangeSet) = {
    cs.positive.groupBy(
      node => Math.abs(forwardHashFunction(node)) % children.size).foreach(
      kv => if (kv._2.nonEmpty) children(kv._1)(ChangeSet(positive = kv._2)))
    cs.negative.groupBy(
      node => Math.abs(forwardHashFunction(node)) % children.size).foreach(
      kv => if (kv._2.nonEmpty) children(kv._1)(ChangeSet(negative = kv._2)))
  }

  def forward(t: TerminatorMessage) = children.foreach(_ (t))

}

trait SingleForwarder extends Forwarder {
  val next: ReteMessage => Unit

  def forward(cs: ChangeSet) = {
    if (cs.positive.nonEmpty || cs.negative.nonEmpty)
      next(cs)
  }

  def forward(terminator: TerminatorMessage) = next(terminator)
}

trait Forwarder {
  val name: String

  def forward(cs: ChangeSet)

  def forward(terminator: TerminatorMessage)
}
