package ingraph.driver.data

class IngraphQueryHandler(val adapter: IngraphIncrementalAdapter) {

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
