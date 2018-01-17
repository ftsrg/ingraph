package ingraph.compiler

import ingraph.compiler.cypher2qplan.CypherParser
import ingraph.compiler.qplan2jplan.{QPlanToJPlan, JPlanToFPlan}
import ingraph.model.fplan.FNode

object FPlanParser {
  def parse(query: String): FNode = {
    JPlanToFPlan.transform(
      QPlanToJPlan.transform(
        CypherToQPlan.build(
          CypherParser.parseString(query), Some("test")
        )
      )
    )
  }
}
