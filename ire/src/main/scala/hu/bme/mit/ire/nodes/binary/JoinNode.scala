package hu.bme.mit.ire.nodes.binary

import hu.bme.mit.ire._
import hu.bme.mit.ire.datatypes.Slot._
import hu.bme.mit.ire.datatypes.{Indexer, Mask, Tuple}
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage, TerminatorMessage}

import scala.collection.mutable

class JoinNode(override val next: (ReteMessage) => Unit,
               override val primaryTupleWidth: Int,
               override val secondaryTupleWidth: Int,
               override val primaryMask: Mask,
               override val secondaryMask: Mask
              ) extends JoinNodeBase with SingleForwarder {
}

class ParallelJoinNode(override val children: Vector[(ReteMessage) => Unit],
                       override val primaryTupleWidth: Int,
                       override val secondaryTupleWidth: Int,
                       override val primaryMask: Mask,
                       override val secondaryMask: Mask,
                       hashFunction: (Tuple) => Int = n => n.hashCode()
                      ) extends JoinNodeBase with ForkingForwarder {

  override def forwardHashFunction(n: Tuple): Int = hashFunction(n)

  override def forward(cs: ChangeSet) = super[ForkingForwarder].forward(cs)

  override def forward(t: TerminatorMessage) = super[ForkingForwarder].forward(t)
}
