package ingraph.driver.data

import hu.bme.mit.ire.Transaction
import ingraph.ire.IngraphIncrementalAdapter
import ingraph.model.fplan.Production
import org.supercsv.prefs.CsvPreference

class IngraphQueryHandler(val adapter: IngraphIncrementalAdapter) {

  val prod = adapter.plan.asInstanceOf[Production]

  def registerDeltaHandler(listener: IngraphDeltaHandler) {
    adapter.addListener(listener)
  }

  def readCsv(nodeFilenames: java.util.Map[String, java.util.List[String]],
              relationshipFilenames: java.util.Map[String, String],
              csvPreference: CsvPreference) {
    val transaction: Transaction = adapter.newTransaction
    adapter.readCsvJava(nodeFilenames, relationshipFilenames, transaction, csvPreference)
    transaction.close()
    adapter.engine.getResults()
  }

  def result(): List[Map[String, Any]] = {
    val res = adapter.result()
    res.map(t => t.zip(prod.outputNames).map(kv => kv._2 -> kv._1).toMap).toList
  }

  def keys() = prod.outputNames

}
