package ingraph.testrunners

import java.util.concurrent.TimeUnit

import com.google.common.base.Stopwatch
import ingraph.driver.CypherDriverFactory
import ingraph.driver.data.{IngraphQueryHandler, ResultCollectingChangeListener}
import ingraph.ire.{Indexer, IngraphOneTimeAdapter}
import ingraph.tests.LdbcSnbTestCase
import org.supercsv.prefs.CsvPreference

class IngraphTestRunner(tc: LdbcSnbTestCase) {

  def run() : List[Map[String, Any]] = {
    val driver = CypherDriverFactory.createIngraphDriver()

    val session = driver.session()
    val csvPreference = new CsvPreference.Builder('"', '|', "\n").build
    val queryHandler = session.registerQuery(tc.name, tc.querySpecification)
    val sLoad = Stopwatch.createStarted()
    val listener = new ResultCollectingChangeListener(queryHandler.keys())
    queryHandler.registerDeltaHandler(listener)
    queryHandler.readCsv(
      tc.vertexCsvPaths,
      tc.edgeCsvPaths,
      csvPreference
    )
    val queryTime = sLoad.elapsed(TimeUnit.NANOSECONDS)

    val indexer = queryHandler.adapter.indexer
    val updateTimes = tc.updateQuerySpecifications.map { q =>
      val sUpdate = Stopwatch.createStarted()
      update(q, "upd", indexer, queryHandler)
      listener.terminated()
      sUpdate.elapsed(TimeUnit.NANOSECONDS)
    }.toList

    println(tc.sf + "," + tc.query + ",ingraph," + queryTime + "," + updateTimes.mkString(","))
    queryHandler.result()
  }

  def update(querySpecification: String, queryName: String, indexer: Indexer, queryHandler: IngraphQueryHandler): List[Map[String, Any]] = {
    val onetime = new IngraphOneTimeAdapter(querySpecification, "del", indexer)
    onetime.terminate()
    onetime.close()
    queryHandler.result
  }

}
