package iplan
import ingraph.compiler.cypher2qplan
import ingraph.compiler.cypher2qplan.CypherParser
import ingraph.compiler.qplan2iplan.{QPlanToIPlan, SchemaInferencer}
import ingraph.model.iplan.INode

object IPlanParser {
  def parse(query: String): INode = {
    SchemaInferencer.transform(
      QPlanToIPlan.transform(
        cypher2qplan.build(
          CypherParser.parseString(query), "test"
        )
      )
    )
  }
}
