package ingraph.compiler.sql

import java.sql.Connection

import ingraph.compiler.sql.Util.withResources
import org.neo4j.driver.v1.Transaction

import scala.collection.JavaConverters._

class ExportStep(val exportCypherQuery: String, val tableName: String) {

  def exportToTable(cypherTransaction: Transaction, sqlConnection: Connection): Unit = {
    val cypherResult = cypherTransaction.run(exportCypherQuery)

    val keysInRecord = cypherResult.keys.size
    val valueParameters = cypherResult.keys.asScala
      .map(key =>
        if (key == "value")
          "to_variant(?)"
        else
          "?")
      .mkString(", ")
    val insertQueryString = s"INSERT INTO $tableName VALUES ($valueParameters)"

    withResources(sqlConnection.prepareStatement(insertQueryString))(insertStatement => {
      for (cypherRecord <- cypherResult.asScala) {
        for (keyIndex <- 0 until keysInRecord) {
          val columnIndex = keyIndex + 1
          insertStatement.setObject(columnIndex, cypherRecord.get(keyIndex).asObject)
        }
        insertStatement.addBatch()
      }

      insertStatement.executeBatch()
    })
  }
}
