package ingraph.driver.data

import ingraph.ire.IncrementalQueryAdapter
import org.supercsv.prefs.CsvPreference

class IngraphQueryHandler(val adapter: IncrementalQueryAdapter) extends AutoCloseable {

  val productionNode = adapter.getProductionNode

  def registerDeltaHandler(listener: IngraphDeltaHandler) {
    adapter.addListener(listener)
  }

  def readCsv(vertexFilenames: Map[String, List[String]],
              edgeFilenames: Map[String, (String, String, String)],
              csvPreference: CsvPreference) {
    adapter.readCsv(vertexFilenames, edgeFilenames, csvPreference)
    adapter.results
  }

  def result(): List[Map[String, Any]] = {
    adapter.results.map(t => t.zip(productionNode.outputNames).map(kv => kv._2 -> kv._1).toMap).toList
  }

  def keys() = productionNode.outputNames

  override def close(): Unit = {
    adapter.close()
  }

}
