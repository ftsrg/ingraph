package ingraph

import ingraph.testrunners.{IngraphTestRunner, Neo4jTestRunner}
import ingraph.tests.LdbcSnbTestCase

object BenchmarkMain {

  def main(args: Array[String]): Unit = {
    val sf = args(0)
    val query = args(1).toInt
    val neo4jDir = args(2)

    def csvDir = f"../graphs/ldbc-snb-bi/sf${sf}/"
    def csvPostfix = "_0_0.csv"

    val forumId = sf match {
      case "tiny" => 274877906944L
      case "01" => 893353197569L
      case "03" => 893353197569L
      case "1" => 1786706395137L
    }
    val updateQuerySpecification =
      s"""MATCH (f:Forum {id: ${forumId}})
         |DETACH DELETE f
    """.stripMargin

    val tc = new LdbcSnbTestCase("bi", query, f"${csvDir}/", csvPostfix, Some(updateQuerySpecification))

    println(tc.name)

    val itr = new IngraphTestRunner(tc)
    val ingraphResults = itr.run()

    val ntr = new Neo4jTestRunner(tc, Some(neo4jDir))
    val neo4jResults = ntr.run()
    ntr.close

    // some Akka stuff gots stuck despite my best efforts to shutdown
    // System.exit(0) was not sufficient
    Runtime.getRuntime.halt(0)
  }

}
