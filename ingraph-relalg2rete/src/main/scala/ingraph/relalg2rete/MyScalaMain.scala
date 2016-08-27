package ingraph.relalg2rete

import relalg.RelalgFactory

object MyScalaMain extends App {
  println("Hello World")

  val factory = RelalgFactory.eINSTANCE
  
  val getNodesOperator = factory.createGetVerticesOperator()  
}
