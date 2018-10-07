package ingraph

import ingraph.testrunners.{IngraphTestRunner, Neo4jTestRunner}
import ingraph.tests.LdbcSnbTestCase

object BenchmarkMain {

  /**
    * args(0): number of runs
    * args(1): scale factor
    * args(2): query
    * args(3): tool
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
      tool.toLowerCase match {
        case "neo4j" =>
          val ntr = new Neo4jTestRunner(tc, Some(csvDir))
          val neo4jResults = ntr.run()
          ntr.close
        case "ingraph" =>
          val itr = new IngraphTestRunner(tc)
          val ingraphResults = itr.run()
          itr.close
      }
    }
  }

}
