package ingraph.compiler

import ingraph.compiler.cypher2gplan.CypherParser
import ingraph.compiler.plantransformers.{NPlanToFPlan, GPlanToNPlan}
import ingraph.model.fplan.FNode

object FPlanParser {
  def parse(query: String): FNode = {
    NPlanToFPlan.transform(
      GPlanToNPlan.transform(
        CypherToGPlan.build(
          CypherParser.parseString(query), Some("test")
        )
      )
    )
  }
}
