package ingraph.driver.data

import hu.bme.mit.ire.Transaction
import ingraph.ire.IngraphAdapter
import org.supercsv.prefs.CsvPreference

class IngraphQueryHandler(val adapter : IngraphAdapter) {

  def registerDeltaHandler(listener: IngraphDeltaHandler) {
    adapter.addListener(listener)
  }

  def readCsv(nodeFilenames: java.util.Map[String, java.util.List[String]], relationshipFilenames: java.util.Map[String, String], csvPreference: CsvPreference) {
    val transaction: Transaction = adapter.newTransaction
    adapter.readCsvJava(nodeFilenames, relationshipFilenames, transaction, csvPreference)
    transaction.close()
    adapter.engine.getResults()
  }

}
