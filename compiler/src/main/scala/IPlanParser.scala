package iplan
import ingraph.compiler.CypherToQPlan
import ingraph.compiler.cypher2qplan.CypherParser
import ingraph.compiler.qplan2iplan.{QPlanToIPlan, SchemaInferencer}
import ingraph.model.iplan.INode

object IPlanParser {
  def parse(query: String): INode = {
    SchemaInferencer.transform(
      QPlanToIPlan.transform(
        CypherToQPlan.build(
          CypherParser.parseString(query), "test"
        )
      )
    )
  }
}
