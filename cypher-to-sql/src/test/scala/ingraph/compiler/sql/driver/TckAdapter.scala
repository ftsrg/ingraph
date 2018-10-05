package ingraph.compiler.sql.driver

import org.neo4j.driver.v1.{Session, Transaction}
import org.opencypher.tools.tck.api.{CypherValueRecords, Graph, QueryType, SideEffectQuery}
import org.opencypher.tools.tck.values.CypherValue

import scala.collection.JavaConverters._

class TckAdapter(session: Session) extends Graph {
  private val transaction: Transaction = session.beginTransaction()

  override def cypher(query: String, params: Map[String, CypherValue], meta: QueryType): Result = {
    // TODO
    if (meta == SideEffectQuery)
      CypherValueRecords.empty
    else {
      val result = transaction.run(query, params.map { case (k, v) => k -> TckValueToDriver(v) }.asJava)

      val header = result.keys.asScala.toList
      val rows = result.list().asScala
        .map { record =>
          record.asMap.asScala
            .map { case (k, v) => k -> DriverValueToTck(v) }
            .toMap
        }
        .toList

      CypherValueRecords(header, rows)
    }
  }

  override def close(): Unit = {
    transaction.close()
  }
}