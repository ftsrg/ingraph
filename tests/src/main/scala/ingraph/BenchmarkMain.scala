package ingraph

import ingraph.compiler.sql.Util._
import ingraph.testrunners.{C2STestRunner, IngraphTestRunner, Neo4jTestRunner}
import ingraph.tests.LdbcSnbTestCase

object BenchmarkMain {

  /**
    * args(0): number of runs
    * args(1): scale factor
    * args(2): query
    * args(3): tool
    *
    * @param args
    */
  def main(args: Array[String]): Unit = {
    val runs = args(0).toInt
    val sf = args(1)
    val query = args(2).toInt
    val tool = args(3)

    val csvDir = s"../graphs/ldbc-snb-bi/csv-sf${sf}/"
    val dbDir = s"../graphs/ldbc-snb-bi/db-sf${sf}/graph.db"
    val tc = new LdbcSnbTestCase("bi", sf, query, csvDir)

    for (run <- 1 to runs) {
      val testRunner = tool.toLowerCase match {
        case "neo4j" => new Neo4jTestRunner(tc, Some(dbDir))
        case "ingraph" => new IngraphTestRunner(tc)
        case "c2s" => new C2STestRunner(tc)
      }

      withResources(testRunner) {
        _.run()
      }
    }
  }

}
