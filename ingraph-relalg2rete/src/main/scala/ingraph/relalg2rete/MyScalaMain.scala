package ingraph.relalg2rete

import relalg.RelalgFactory

object MyScalaMain extends App {
  println("Hello World")

  val factory = RelalgFactory.eINSTANCE
  
  val filterOperator = factory.createFilterOperator()
  filterOperator.setName("filter1")
  filterOperator.setParent(null)
}
