package ingraph

import ingraph.compiler.FPlanParser
import ingraph.model.fplan.FNode

/**
  * This class compiles an openCypher query into a
  * docker compose file
  *
  * @param querySpecification the query to be compiled
  *
  * @author hegyibalint
  */
class ContainerCompiler(querySpecification: String) {

  def collectNodes(node: FNode): List[FNode] = {
    List(node) ++ node.children.flatMap(n => collectNodes(n))
  }

  def compile(): Unit = {
    val prod = FPlanParser.parse(querySpecification)
    val nodes = collectNodes(prod)


  }

}
