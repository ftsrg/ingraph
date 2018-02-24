package ldbc

import ingraph.driver.CypherDriverFactory
import ingraph.model.fplan.Production
import ingraph.tests.LdbcSnbTestCase
import org.scalatest.FunSuite
import org.supercsv.prefs.CsvPreference

import scala.collection.JavaConverters._

class LdbcToIngraphUpdateLoaderTest extends FunSuite {

  test("load") {
    val tc: LdbcSnbTestCase = new LdbcSnbTestCase("bi", 2)

    val driver = CypherDriverFactory.createIngraphDriver
    try {
      val session = driver.session
      val csvPreference = new CsvPreference.Builder('"', '|', "\n").build
      val queryHandler = session.registerQuery(tc.name, tc.query)
      queryHandler.readCsv(
        tc.nodeCSVPaths.mapValues(_.asJava).asJava,
        tc.relationshipCSVPaths.asJava,
        csvPreference
      )
      val res = queryHandler.adapter.result()
      val prod = queryHandler.adapter.plan.asInstanceOf[Production]
      res.map(t => t.zip(prod.outputNames).map(kv => kv._2 -> kv._1).toMap).toList

      val indexer = queryHandler.adapter.indexer
      val loader = new LdbcUpdateToIngraphLoader(indexer)
      loader.load()

    } finally if (driver != null) {
      driver.close()
    }
  }

}
