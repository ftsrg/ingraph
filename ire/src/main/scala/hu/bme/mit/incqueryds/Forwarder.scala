package hu.bme.mit.incqueryds

/**
  * Created by wafle on 12/25/2015.
  */
trait ForkingForwarder extends Forwarder {
  val children: Vector[ReteMessage => Unit]

  if (children.size < 2)
  throw new IllegalArgumentException("use base class for 1 child node")

  def forwardHashFunction(n: nodeType): Int

  def forward(cs: ChangeSet) = {
  cs.positive.groupBy( node => Math.abs(forwardHashFunction(node)) % children.size).foreach(kv => if (kv._2.nonEmpty) children(kv._1)(ChangeSet(positive = kv._2)))
  cs.negative.groupBy( node => Math.abs(forwardHashFunction(node)) % children.size).foreach(kv => if (kv._2.nonEmpty) children(kv._1)(ChangeSet(negative = kv._2)))
  }

  def forward(t: TerminatorMessage) = children.foreach(_ (t))

}

trait SingleForwarder extends Forwarder {
  val next: ReteMessage => Unit

  def forward(cs: ChangeSet) = {
  if (cs.positive.nonEmpty || cs.negative.nonEmpty)
    next(cs)
  //printForwarding(cs)
  }

  def forward(terminator: TerminatorMessage) = next(terminator)
}

abstract trait Forwarder {
  val name: String
  def forward(cs: ChangeSet)

  def forward(terminator: TerminatorMessage)

  def printForwarding(cs: ChangeSet) = println(s"${name} sends [${cs.positive.size + cs.negative.size}]")
}
