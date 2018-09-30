package ingraph.tests

import ingraph.testrunners.{IngraphTestRunner, Neo4jTestRunner}
import org.scalatest.FunSuite

class BiValidationTest extends FunSuite {

  val sf = "01"
  def csvDir = f"../graphs/ldbc-snb-bi"
  def csvPostfix = "_0_0.csv"

  val forumId = sf match {
    case "tiny" => 274877906944L
    case "01" => 893353197569L
    case "03" => 893353197569L
    case "1" => 1786706395137L
  }
//  val updateQuerySpecification =
//    s"""MATCH (f:Forum {id: ${forumId}})
//       |DETACH DELETE f
//    """.stripMargin

  val meta = Seq(1)
  val listComprehensions = Seq(11, 13) //2
  val madness = Seq(25)
  val orderby = Seq(10)
  val hangs = Seq(19)
  val startWithWith = Seq(20)

  val transitives = Seq(14, 16, 18) //3
  val buggy = Seq(3, 5, 6, 8, 15, 17, 21) //7
  //  val targetQueries = Seq(1, 2, 3, 4, 6, 7, 8, 9, 12, 13, 15, 17, 21, 22, 23, 24)
  val working = Seq(21)//, 3, 4, 6, 7, 8, 9, 12, 15, 21, 22, 23, 24)

  val testCases: Seq[LdbcSnbTestCase] = (working).sorted map (
      query => new LdbcSnbTestCase("bi", sf, query, s"${csvDir}/sf${sf}/", csvPostfix)
    )

  testCases.foreach {
    tc =>
      test(s"${tc.name}") {
        val itr = new IngraphTestRunner(tc)
        val ingraphResults = itr.run()

        val ntr = new Neo4jTestRunner(tc, Some(s"${csvDir}/db-sf${sf}/graph.db"))
        val neo4jResults = ntr.run()
        ntr.close

        println("ingraph results: ")
        ingraphResults.map(x => x.toSeq.sortBy(_._1)).foreach(println)
        println("neo4j   results: ")
        neo4jResults.map(x => x.toSeq.sortBy(_._1)).foreach(println)
        assert(ingraphResults == neo4jResults)
      }
  }

}
