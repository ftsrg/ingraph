package ingraph.relalg2rete

import relalg.RelalgFactory

object MyScalaMain extends App {
  println("Hello World")

  val factory = RelalgFactory.eINSTANCE
  
  val getNodesOperator = factory.createGetNodesOperator()
  getNodesOperator.setName("getnodes1")
  
  val filterOperator = factory.createFilterOperator()
  filterOperator.setName("filter")
  filterOperator.setParent(getNodesOperator)  
}
