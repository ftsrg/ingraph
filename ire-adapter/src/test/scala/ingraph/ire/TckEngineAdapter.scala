package ingraph.ire

import hu.bme.mit.ire.datatypes.Tuple
import ingraph.model.expr.{AbstractReturnItem, EdgeListAttribute, ResolvableName, VertexAttribute}
import ingraph.model.fplan.Production
import org.opencypher.tools.tck.api._
import org.opencypher.tools.tck.values._

import scala.collection.breakOut

class TckEngineAdapter extends Graph {
  val indexer = new Indexer()

  def toCypherPropertyMap(map: Map[String, Any]): CypherPropertyMap = {
    CypherPropertyMap(map.map { case (columnName, value) => columnName -> toCypherValue(value) })
  }

  def toCypherRelationship(id: Long, indexer: Indexer) = {
    val edge = indexer.edgeLookup(id)
    CypherRelationship(edge.`type`, toCypherPropertyMap(edge.properties))
  }

  def toCypherValue(value: Any, attribute: Option[ResolvableName] = None, indexer: Option[Indexer] = None): CypherValue = {
    val isVertex = attribute
      .collect { case a: AbstractReturnItem => a.toAttribute }
      .collect { case a: VertexAttribute => a }
      .isDefined
    val isEdgeListAttribute = attribute
      .collect { case a: AbstractReturnItem => a.toAttribute }
      .collect { case a: EdgeListAttribute => a }
      .isDefined

    value match {
      case null => CypherNull
      case value: String => CypherString(value)
      case value: Long if !isVertex => CypherInteger(value)
      case value: Long if isVertex => {
        val vertex = indexer.get.vertexLookup(value)
        val properties = toCypherPropertyMap(vertex.properties)

        CypherNode(vertex.labels, properties)
      }
      case value: Seq[Any] if !isEdgeListAttribute => CypherOrderedList(value.map(toCypherValue(_)).toList)
      case value: Seq[Long] if isEdgeListAttribute => CypherOrderedList(value.map(toCypherRelationship(_, indexer.get)).toList)
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

        val plan = readAdapter.plan.asInstanceOf[Production]
        val columnNames = plan.outputNames.toSeq
        val resultTuples = readAdapter.result

        val tupleConversion: Tuple => Map[String, CypherValue] = tuple => {
          val cellInfos = columnNames zip plan.output zip tuple
          cellInfos.map { case ((columnName, attribute), value) => columnName -> toCypherValue(value, Some(attribute), Some(readAdapter.indexer)) }(breakOut)
        }
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
