package ingraph.driver.data

import hu.bme.mit.ire.Transaction
import ingraph.ire.IngraphIncrementalAdapter
import ingraph.model.fplan.Production
import org.supercsv.prefs.CsvPreference

class IngraphQueryHandler(val adapter: IngraphIncrementalAdapter) extends AutoCloseable {

  val prod = adapter.plan.asInstanceOf[Production]

  def registerDeltaHandler(listener: IngraphDeltaHandler) {
    adapter.addListener(listener)
  }

  def readCsv(vertexFilenames: Map[String, List[String]],
              edgeFilenames: Map[String, (String, String, String)],
              csvPreference: CsvPreference) {
    val transaction: Transaction = adapter.newTransaction
    adapter.readCsv(vertexFilenames, edgeFilenames, transaction, csvPreference)
    transaction.close()
    adapter.engine.getResults()
  }

  def result(): List[Map[String, Any]] = {
    val res = adapter.result()
    res.map(t => t.zip(prod.outputNames).map(kv => kv._2 -> kv._1).toMap).toList
  }

  def keys() = prod.outputNames

  override def close(): Unit = {
    adapter.close()
  }

}
