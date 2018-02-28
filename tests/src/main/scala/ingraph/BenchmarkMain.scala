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

    val postId = sf match {
      case "tiny" => 137438953796L
      case   "01" => 481036337184L
      case   "03" => 412316860440L
      case    "1" => 962072674360L
      case    "3" => 3573412790304L
    }
    val forumId = sf match {
      case "tiny" => 274877906944L
      case   "01" => 893353199216L
      case   "03" => 893353197569L
      case    "1" => 1786706395137L
      case    "3" => 3573412790338L
    }
    val removePost =
      s"""MATCH (n:Message:Post {id: ${postId}})
         |DETACH DELETE n
    """.stripMargin
    val removeForum =
      s"""MATCH (n:Forum {id: ${forumId}})
         |DETACH DELETE n
    """.stripMargin

    val tc = new LdbcSnbTestCase("bi", query, f"${csvDir}/", csvPostfix, Seq(removePost, removeForum))

    println(tc.name)

    val itr = new IngraphTestRunner(tc)
    val ingraphResults = itr.run()

    val ntr = new Neo4jTestRunner(tc, Some(neo4jDir))
    val neo4jResults = ntr.run()
    ntr.close

//    assert(ingraphResults == neo4jResults)

    // some Akka stuff gets stuck despite my best efforts to shutdown
    // System.exit(0) was not sufficient
    Runtime.getRuntime.halt(0)
  }

}
