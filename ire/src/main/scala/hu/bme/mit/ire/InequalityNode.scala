package hu.bme.mit.ire

/**
  * Created by wafle on 12/25/2015.
  */
class InequalityNode(override val next: (ReteMessage) => Unit,
                     val nodeKey: Any, val inequals: Vector[Any],
                     override val expectedTerminatorCount:Int = 1) extends
SelectionNode(next, condition = (node: TupleType)
  => !inequals.map { i => node(i) }.contains(node(nodeKey)))  with SingleForwarder
