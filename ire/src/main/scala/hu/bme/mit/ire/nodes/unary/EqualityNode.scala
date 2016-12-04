package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.TupleType
import hu.bme.mit.ire.messages.ReteMessage

class EqualityNode(override val next: (ReteMessage) => Unit,
                   val nodeIndex: Int, val equals: Vector[Int],
                   override val expectedTerminatorCount:Int = 1) extends
SelectionNode(next, condition = (node: TupleType) => {
  equals.map { i => node(i) }.forall { value => value == node(nodeIndex) }
}
)  with SingleForwarder
