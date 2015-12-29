package hu.bme.mit.incqueryds

/**
  * Created by wafle on 12/25/2015.
  */
trait ForkingForwarder {
  val children: Vector[ReteMessage => Unit]

  if (children.size < 2)
    throw new IllegalArgumentException("use base class for 1 child node")

  def forwardHashFunction(n: nodeType): Int

  def forward(cs: ChangeSet) = {
    cs.positive.groupBy( node => Math.abs(forwardHashFunction(node)) % children.size).foreach(kv => if (kv._2.size > 0) children(kv._1)(ChangeSet(positive = kv._2.toVector)))
    cs.negative.groupBy( node => Math.abs(forwardHashFunction(node)) % children.size).foreach(kv => if (kv._2.size > 0) children(kv._1)(ChangeSet(negative = kv._2.toVector)))
  }

  def forward(t: TerminatorMessage) = children.foreach(_ (t))

}

trait SingleForwarder {
  val next: ReteMessage => Unit

  def forward(cs: ChangeSet) = if (cs.positive.size > 0 || cs.negative.size > 0) next(cs)

  def forward(terminator: TerminatorMessage) = next(terminator)
}

abstract trait Forwarder {
  def forward(cs: ChangeSet)

  def forward(terminator: TerminatorMessage)
}
