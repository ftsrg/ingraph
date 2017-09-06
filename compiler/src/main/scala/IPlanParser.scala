package iplan
import ingraph.compiler.CypherToQPlan
import ingraph.compiler.cypher2qplan.CypherParser
import ingraph.compiler.qplan2iplan.{QPlanToIPlan, SchemaInferencer}
import ingraph.model.eplan.ENode

object IPlanParser {
  def parse(query: String): ENode = {
    SchemaInferencer.transform(
      QPlanToIPlan.transform(
        CypherToQPlan.build(
          CypherParser.parseString(query), "test"
        )
      )
    )
  }
}
