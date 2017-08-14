package ingraph.compiler

import ingraph.compiler.cypher2qplan.builders.StatementBuilder
import ingraph.model.qplan
import org.slizaa.neo4j.opencypher.openCypher.Cypher

package object cypher2qplan {
  def build(cypher: Cypher, queryName: String): qplan.Production = {
    val statement = StatementBuilder.build(cypher.getStatement)

    qplan.Production(statement)
  }
}
