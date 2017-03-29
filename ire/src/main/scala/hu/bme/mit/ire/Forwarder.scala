package hu.bme.mit.ire

import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.{Δ, ReteMessage, ✝}

trait ForkingForwarder extends Forwarder {
  val children: Vector[ReteMessage => Unit]

  if (children.size < 2)
    throw new IllegalArgumentException("use base class for 1 child node")

  def forwardHashFunction(n: Tuple): Int

  def forward(cs: Δ) = {
    cs.positive.groupBy(
      node => Math.abs(forwardHashFunction(node)) % children.size).foreach(
      kv => if (kv._2.nonEmpty) children(kv._1)(Δ(positive = kv._2)))
    cs.negative.groupBy(
      node => Math.abs(forwardHashFunction(node)) % children.size).foreach(
      kv => if (kv._2.nonEmpty) children(kv._1)(Δ(negative = kv._2)))
  }

  def forward(t: ✝) = children.foreach(_ (t))

}

trait SingleForwarder extends Forwarder {
  val next: ReteMessage => Unit

  def forward(cs: Δ) = {
    if (cs.positive.nonEmpty || cs.negative.nonEmpty)
      next(cs)
  }

  def forward(terminator: ✝) = next(terminator)
}

trait Forwarder {
  val name: String

  def forward(cs: Δ)

  def forward(terminator: ✝)
}
