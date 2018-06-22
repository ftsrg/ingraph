package ingraph.compiler

import ingraph.compiler.cypher2qplan.CypherParser
import ingraph.compiler.qplan2nplan.{NPlanToFPlan, QPlanToNPlan}
import ingraph.model.fplan.FNode

object FPlanParser {
  def parse(query: String): FNode = {
    NPlanToFPlan.transform(
      QPlanToNPlan.transform(
        CypherToQPlan.build(
          CypherParser.parseString(query), Some("test")
        )
      )
    )
  }
}
