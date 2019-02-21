package ingraph.compiler.sql

import java.sql.Connection

import ingraph.compiler.sql.Util.withResources
import ingraph.compiler.sql.driver.ValueJsonConversion
import org.neo4j.driver.v1.{Session, Value}

import scala.collection.JavaConverters._

class ExportStep(val exportCypherQuery: String, val tableName: String) {

  def exportToTable(cypherSession: Session, sqlConnection: Connection): Unit = {
    val cypherResult = cypherSession.run(exportCypherQuery)

    val keysInRecord = cypherResult.keys.size
    val valueParameters = cypherResult.keys.asScala
      .map(key =>
        if (key == "value")
          "?::jsonb"
        else
          "?")
      .mkString(", ")
    val insertQueryString = s"INSERT INTO $tableName VALUES ($valueParameters)"

    withResources(sqlConnection.prepareStatement(insertQueryString))(insertStatement => {
      for (cypherRecord <- cypherResult.asScala) {
        for (keyIndex <- 0 until keysInRecord) {
          val columnIndex = keyIndex + 1
          val cypherValue = cypherRecord.get(keyIndex)
          val cypherValueObject = cypherValue.asObject

          val value =
            if (cypherRecord.keys().get(keyIndex) == "value")
              ValueJsonConversion.gson.toJson(cypherValue, classOf[Value])
            else
              cypherValueObject

          insertStatement.setObject(columnIndex, value)
        }
        insertStatement.addBatch()
      }

      insertStatement.executeBatch()
    })
  }
}
