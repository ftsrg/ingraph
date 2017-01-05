package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.datatypes.Mask
import hu.bme.mit.ire.messages.ReteMessage
import hu.bme.mit.ire.SingleForwarder

abstract class AggregationNode extends UnaryNode with SingleForwarder {
  val aggregationKeys: Mask
  val distinct: Boolean
}
