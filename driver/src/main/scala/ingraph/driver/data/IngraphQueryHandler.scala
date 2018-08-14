package ingraph.driver.data

import hu.bme.mit.ire.DataSource
import ingraph.ire.IncrementalQueryAdapter
import ingraph.model.fplan.Production
import org.supercsv.prefs.CsvPreference

class IngraphQueryHandler(val adapter: IncrementalQueryAdapter) extends AutoCloseable {

  val productionNode = adapter.plan.asInstanceOf[Production]

  def registerDeltaHandler(listener: IngraphDeltaHandler) {
    adapter.addListener(listener)
  }

  def readCsv(vertexFilenames: Map[String, List[String]],
              edgeFilenames: Map[String, (String, String, String)],
              csvPreference: CsvPreference) {
    adapter.readCsv2(vertexFilenames, edgeFilenames, csvPreference)
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
