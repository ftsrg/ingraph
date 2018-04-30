package ingraph.compiler.sql

import java.sql.Connection

import org.neo4j.driver.v1.Transaction

object ExportSteps {
  private val exportVertex = new ExportStep(
    """// vertex
      |MATCH (n)
      |RETURN id(n) AS vertex_id""".stripMargin, "vertex")

  private val exportEdge = new ExportStep(
    """// edge
      |MATCH (from)-[edge]->(to)
      |RETURN id(edge) AS edge_id, id(from) AS from, id(to) AS to, type(edge) AS type""".stripMargin, "edge")

  private val exportLabel = new ExportStep(
    """// label
      |MATCH (n)
      |UNWIND labels(n) AS name
      |RETURN id(n) AS parent, name""".stripMargin, "label")

  private val exportVertex_property = new ExportStep(
    """// vertex_property
      |MATCH (n)
      |UNWIND keys(n) AS key
      |RETURN id(n) AS parent, key, properties(n)[key] AS value""".stripMargin, "vertex_property")

  private val exportEdge_property = new ExportStep(
    """// edge_property
      |MATCH ()-[e]->()
      |UNWIND keys(e) AS key
      |RETURN id(e) AS parent, key, properties(e)[key] AS value""".stripMargin, "edge_property")

  private val steps = Array(exportVertex, exportEdge, exportLabel, exportVertex_property, exportEdge_property)

  def execute(cypherTransaction: Transaction, sqlConnection: Connection): Unit = {
    for (step <- steps)
      step.exportToTable(cypherTransaction, sqlConnection)
  }
}
