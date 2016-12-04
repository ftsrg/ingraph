package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.TupleType
import hu.bme.mit.ire.messages.ReteMessage

class InequalityNode(override val next: (ReteMessage) => Unit,
                     val nodeKey: Any, val inequals: Vector[Any]) extends
SelectionNode(next, condition = (node: TupleType)
  => !inequals.map { i => node(i) }.contains(node(nodeKey)))  with SingleForwarder
