package ingraph.sandbox

import ingraph.compiler.CypherToQPlan
import ingraph.compiler.cypher2qplan.{CypherParser, QPlanResolver}
import ingraph.compiler.qplan2jplan.{QPlanToJPlan, SchemaInferencer}
import ingraph.model.fplan.{FNode, LeafFNode}
import ingraph.model.jplan.JNode
import ingraph.model.qplan.QNode
import org.scalatest.FunSuite

class CompilerTest extends FunSuite {

  val log = true

  case class CompilationStages(qplan: QNode,
                               jplan: JNode,
                               fplan: FNode)

  def compile(query: String): CompilationStages = {
    val cypher = CypherParser.parseString(query)
    val qplan = CypherToQPlan.build(cypher)
    val jplan = QPlanToJPlan.transform(qplan)
    val fplan = SchemaInferencer.transform(jplan)


    if (log) {
      println("=============================================================================")
      println("Query:")
      println(query)
      println("-----------------------------------------------------------------------------")
      println("QPlan:")
      println(qplan)
      println("-----------------------------------------------------------------------------")
      println("JPlan:")
      println(jplan)
      println("-----------------------------------------------------------------------------")
      println("FPlan:")
      println(fplan)
    }

    return CompilationStages(qplan, jplan, fplan)
  }

  def getLeafNodes(plan: FNode): Seq[FNode] = {
    if (plan.isInstanceOf[LeafFNode]) return plan :: Nil
    return plan.children.flatMap(x => getLeafNodes(x))
  }

}
