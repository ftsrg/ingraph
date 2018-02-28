package ingraph.testrunners

import java.util.concurrent.TimeUnit

import com.google.common.base.Stopwatch
import ingraph.driver.CypherDriverFactory
import ingraph.driver.data.IngraphQueryHandler
import ingraph.ire.{Indexer, IngraphOneTimeAdapter}
import ingraph.tests.LdbcSnbTestCase
import org.supercsv.prefs.CsvPreference

class IngraphTestRunner(tc: LdbcSnbTestCase) {

  def run() : List[Map[String, Any]] = {
    val driver = CypherDriverFactory.createIngraphDriver()

    val session = driver.session()
    val csvPreference = new CsvPreference.Builder('"', '|', "\n").build
    val queryHandler = session.registerQuery(tc.name, tc.querySpecification)
    queryHandler.readCsv(
      tc.vertexCsvPaths,
      tc.edgeCsvPaths,
      csvPreference
    )

    val results1 = queryHandler.result
    val indexer = queryHandler.adapter.indexer

    // val loader = new LdbcUpdateToIngraphLoader(indexer, "../graphs/ldbc-snb-bi/sf-tiny/")
    // loader.load()

    val s = Stopwatch.createStarted()
    tc.updateQuerySpecification.foreach(update(_, "upd", indexer, queryHandler))
    val results2 = queryHandler.result
    println("ingraph => total update time: " + s.elapsed(TimeUnit.MILLISECONDS))

    session.close()
    results2
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
