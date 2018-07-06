package ingraph.ire

import hu.bme.mit.ire.datatypes.Tuple
import ingraph.model.fplan.Production
import org.opencypher.tools.tck.api._
import org.opencypher.tools.tck.values.CypherValue

import scala.collection.breakOut

class TckEngineAdapter extends Graph {
  val indexer = new Indexer()

  override def cypher(query: String, params: Map[String, CypherValue], meta: QueryType): Result = {
    println(query)
    val result = meta match {
      case InitQuery => {
        val createAdapter = new IngraphOneTimeAdapter(query, meta.toString, indexer)
        createAdapter.terminate()

        CypherValueRecords.empty
      }
      // TODO fix id()
      case SideEffectQuery => CypherValueRecords.empty
      case _ => {
        val readAdapter = new IngraphIncrementalAdapter(query, meta.toString, indexer)

        val columnNames = readAdapter.plan.asInstanceOf[Production].outputNames.toSeq
        val resultTuples = readAdapter.result

        val tupleConversion: Tuple => Map[String, String] = tuple => columnNames.zip(tuple.map(_.toString))(breakOut)
        val result = resultTuples.map(tupleConversion).toList
        result.foreach(println)

        // TODO use orderedLists parameter
        CypherValueRecords.fromRows(columnNames.toList, result, false)
      }
    }
    println()

    result
  }

  // TODO lifecycle
  override def close(): Unit = super.close()
}
