package hu.bme.mit.ire.nodes.unary.aggregation

import hu.bme.mit.ire.datatypes.Mask
import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.nodes.unary.UnaryNode

abstract class AggregationNode extends UnaryNode with SingleForwarder {
  val aggregationKeys: Mask
  val distinct: Boolean
}
