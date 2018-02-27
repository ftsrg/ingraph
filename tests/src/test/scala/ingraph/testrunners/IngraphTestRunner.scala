package ingraph.testrunners

import java.util.concurrent.TimeUnit

import com.google.common.base.Stopwatch
import ingraph.driver.CypherDriverFactory
import ingraph.driver.data.IngraphQueryHandler
import ingraph.ire.{Indexer, IngraphOneTimeAdapter}
import ingraph.tests.LdbcSnbTestCase
import org.supercsv.prefs.CsvPreference

object IngraphTestRunner {

  def update(querySpecification: String, queryName: String, indexer: Indexer, queryHandler: IngraphQueryHandler): List[Map[String, Any]] = {
    val s = Stopwatch.createStarted()
    val onetime = new IngraphOneTimeAdapter(querySpecification, "del", indexer)
    println("Update time 1: " + s.elapsed(TimeUnit.MILLISECONDS))
    s.reset()
    onetime.terminate()
    println("Update time 2: " + s.elapsed(TimeUnit.MILLISECONDS))
    s.reset()
    val res = queryHandler.result
    println("Update time 3: " + s.elapsed(TimeUnit.MILLISECONDS))
    res
  }

  def run(tc: LdbcSnbTestCase) : List[Map[String, Any]] = {
    val driver = CypherDriverFactory.createIngraphDriver()
    try {
      val session = driver.session()
      val csvPreference = new CsvPreference.Builder('"', '|', "\n").build
      val queryHandler = session.registerQuery(tc.name, tc.query)
      queryHandler.readCsv(
        tc.vertexCsvPaths,
        tc.edgeCsvPaths,
        csvPreference
      )

      val res1 = queryHandler.result
      val indexer = queryHandler.adapter.indexer

      // val loader = new LdbcUpdateToIngraphLoader(indexer, "../graphs/ldbc-snb-bi/sf-tiny/")
      // loader.load()

      // sf-tiny: 8796093022246
      // sf01: 32985348834423
      // sf03: 13194139533500
      // sf1: 4398046516185
//      val results = update(
//        """MATCH (p:Person {id: 13194139533500})
//          |DETACH DELETE p
//        """.stripMargin, "del", indexer, queryHandler)
      val results = res1
      session.close()
      results
    } finally {
      driver.close()
    }
  }

}
