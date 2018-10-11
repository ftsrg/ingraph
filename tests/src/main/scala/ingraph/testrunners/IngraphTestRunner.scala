package ingraph.testrunners

import java.util.concurrent.TimeUnit

import com.google.common.base.Stopwatch
import ingraph.driver.CypherDriverFactory
import ingraph.driver.data.{IngraphQueryHandler, ResultCollectingChangeListener}
import ingraph.ire.{Indexer, OneTimeQueryAdapter}
import ingraph.tests.LdbcSnbTestCase
import org.supercsv.prefs.CsvPreference

class IngraphTestRunner(tc: LdbcSnbTestCase) {

  val driver = CypherDriverFactory.createIngraphDriver()
  val session = driver.session()

  def run(): (Iterable[Seq[Map[String, Any]]], Iterable[Long]) = {
    val csvPreference = new CsvPreference.Builder('"', '|', "\n").build
    val queryHandler = session.registerQuery(tc.name, tc.querySpecification)

    // initial
    val iStopwatch = Stopwatch.createStarted()
    val listener = new ResultCollectingChangeListener(queryHandler.keys())
    queryHandler.registerDeltaHandler(listener)
    queryHandler.readCsv(tc.vertexCsvPaths, tc.edgeCsvPaths, csvPreference)
    val iResult = queryHandler.result()
    val iTime = iStopwatch.elapsed(TimeUnit.NANOSECONDS)

    val indexer = queryHandler.adapter.indexer

    // updates: append
    val aStopwatch = Stopwatch.createStarted()
    tc.updates.take(4).map { u => update(u, "upd", indexer, queryHandler, listener) }
    val aResult = queryHandler.result()
    val aTime = aStopwatch.elapsed(TimeUnit.NANOSECONDS)

    // updates: delete
    val dStopwatch = Stopwatch.createStarted()
    tc.updates.takeRight(3).map { u => update(u, "upd", indexer, queryHandler, listener) }
    val dResult = queryHandler.result()
    val dTime = dStopwatch.elapsed(TimeUnit.NANOSECONDS)

    val results = Seq(iResult, aResult, dResult)
    val times = Seq(iTime, aTime, dTime)

    println(tc.sf + "," + tc.query + ",ingraph,times," + times.mkString(","))
    println(tc.sf + "," + tc.query + ",ingraph,results," + results.map(_.length).mkString(","))

    return (results, times)
  }

  def update(querySpecification: String,
             queryName: String,
             indexer: Indexer,
             queryHandler: IngraphQueryHandler,
             listener: ResultCollectingChangeListener): List[Map[String, Any]] = {
    val adapter = new OneTimeQueryAdapter(querySpecification, queryName, indexer)
    adapter.results()
    adapter.close()
    val results = queryHandler.result
    listener.terminated()
    return results
  }

  def close(): Unit = {
    session.close()
    driver.close()
  }

}
