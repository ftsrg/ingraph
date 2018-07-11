package ingraph.ire

import hu.bme.mit.ire.datatypes.Tuple
import ingraph.model.fplan.Production
import org.opencypher.tools.tck.api._
import org.opencypher.tools.tck.values._

import scala.collection.breakOut

class TckEngineAdapter extends Graph {
  val indexer = new Indexer()

  def toCypherValue(value: Any, adapter: AbstractIngraphAdapter): CypherValue = {
    val indexer = adapter.indexer
    value match {
      case value: String => CypherString(value)
      //      case value: Long => CypherInteger(value)
      case value: Long => {
        val vertex = indexer.vertexLookup(value)
        val properties = CypherPropertyMap(vertex.properties.map(pair => pair._1 -> toCypherValue(pair._2, adapter)))

        CypherNode(vertex.labels, properties)
      }
    }
  }

  override def cypher(query: String, params: Map[String, CypherValue], meta: QueryType): Result = {
    println(meta)
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
        println("++++++++++++++++")

        val readAdapter = new IngraphIncrementalAdapter(query, meta.toString, indexer)

        val columnNames = readAdapter.plan.asInstanceOf[Production].outputNames.toSeq
        val resultTuples = readAdapter.result

        val tupleConversion: Tuple => Map[String, CypherValue] = tuple => columnNames.zip(tuple.map(toCypherValue(_, readAdapter)))(breakOut)
        val result = resultTuples.map(tupleConversion).toList
        result.foreach(println)

        readAdapter.close()

        CypherValueRecords(columnNames.toList, result)
      }
    }
    println()

    result
  }

  // TODO lifecycle
  override def close(): Unit = super.close()
}
