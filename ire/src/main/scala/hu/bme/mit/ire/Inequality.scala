package hu.bme.mit.ire

/**
  * Created by wafle on 12/25/2015.
  */
class Inequality(override val next: (ReteMessage) => Unit,
         val nodeKey: Any, val inequals: Vector[Any],
         override val expectedTerminatorCount:Int = 1) extends
Checker(next, condition = (node: nodeType) => {
  !inequals.map { i => node(i) }.contains(node(nodeKey))
})  with SingleForwarder
