package ingraph.compiler

import ingraph.compiler.cypher2qplan.CypherParser
import ingraph.compiler.qplan2iplan.QPlanToSqlPlan
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan

object IPlanParser {
  def parse(query: String): LogicalPlan = {
    QPlanToSqlPlan.transform(
      CypherToQPlan.build(
        CypherParser.parseString(query), "test"
      )
    )
  }
}
