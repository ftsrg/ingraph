package hu.bme.mit.incqueryds

/**
  * Created by wafle on 12/25/2015.
  */
class Inequality(override val next: (ReteMessage) => Unit,
                 val nodeIndex: Int, val inequals: Vector[Int],
                 override val expectedTerminatorCount:Int = 1) extends
Checker(next, condition = (node: nodeType) => {
  !inequals.map { i => node(i) }.exists { value => value == node(nodeIndex) }
})  with SingleForwarder
