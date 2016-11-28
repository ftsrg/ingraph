package hu.bme.mit.ire

/**
  * Created by wafle on 12/25/2015.
  */
class Equality(override val next: (ReteMessage) => Unit,
               val nodeIndex: Int, val equals: Vector[Int],
               override val expectedTerminatorCount:Int = 1) extends
SelectionNode(next, condition = (node: TupleType) => {
  equals.map { i => node(i) }.forall { value => value == node(nodeIndex) }
}
)  with SingleForwarder
