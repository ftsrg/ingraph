package ingraph.ire

import ingraph.tests.LdbcSnbTest
import org.supercsv.prefs.CsvPreference

class LdbcSnbEngineTest extends LdbcSnbTest {

  override def runQuery(workload: String, queryNumber: String, queryName: String, querySpecification: String, parameters: Map[String, Object]): Unit = {
    val indexer = new Indexer()
    val adapter = new IngraphIncrementalAdapter(querySpecification, queryName, indexer)

    val tran = adapter.newTransaction()
    val csvPreference = new CsvPreference.Builder('"', '|', "\n").build()
//    adapter.readCsv(nodeFilenames, relationshipFilenames, tran, csvPreference)
    tran.close()

    val actualResults = adapter.engine.getResults()
    println(actualResults)
  }

}
