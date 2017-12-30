package ingraph.compiler

import ingraph.compiler.cypher2qplan.CypherParser
import ingraph.compiler.qplan2jplan.{QPlanToJPlan, SchemaInferencer}
import ingraph.model.fplan.FNode

object FPlanParser {
  def parse(query: String): FNode = {
    SchemaInferencer.transform(
      QPlanToJPlan.transform(
        CypherToQPlan.build(
          CypherParser.parseString(query), Some("test")
        )
      )
    )
  }
}
