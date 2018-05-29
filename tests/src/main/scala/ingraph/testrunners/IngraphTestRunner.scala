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
    print("," + sLoad.elapsed(TimeUnit.MILLISECONDS))

    val indexer = queryHandler.adapter.indexer
    tc.updateQuerySpecifications.foreach { q =>
      val sUpdate = Stopwatch.createStarted()
      update(q, "upd", indexer, queryHandler)
      listener.terminated()
      print("," + sUpdate.elapsed(TimeUnit.MILLISECONDS))
    }

    queryHandler.result()
  }

  def update(querySpecification: String, queryName: String, indexer: Indexer, queryHandler: IngraphQueryHandler): List[Map[String, Any]] = {
    val s = Stopwatch.createStarted()
    val onetime = new IngraphOneTimeAdapter(querySpecification, "del", indexer)
//    println("Update time 1: " + s.elapsed(TimeUnit.MILLISECONDS))
    s.reset()
    s.start()
    onetime.terminate()
    onetime.close()
//    println("Update time 2: " + s.elapsed(TimeUnit.MILLISECONDS))
    s.reset()
    s.start()
    val res = queryHandler.result
//    println("Update time 3: " + s.elapsed(TimeUnit.MILLISECONDS))
    res
  }

}
