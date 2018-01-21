package ingraph.compiler

import ingraph.compiler.cypher2qplan.CypherParser
import ingraph.compiler.qplan2jplan.{FPlanToTPlan, JPlanToFPlan, QPlanToJPlan}
import ingraph.model.tplan.TNode

object TPlanParser {
  def parse(query: String): TNode = {
    FPlanToTPlan.transform(
      JPlanToFPlan.transform(
        QPlanToJPlan.transform(
          CypherToQPlan.build(
            CypherParser.parseString(query), Some("test")
          )
        )
      )
    )
  }
}
