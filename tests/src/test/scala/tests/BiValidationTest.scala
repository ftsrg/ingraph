package ingraph.tests

import ingraph.testrunners.{IngraphTestRunner, Neo4jTestRunner}
import ldbc.LdbcUpdateLoader
import org.scalatest.FunSuite

class BiValidationTest extends FunSuite {

  val sf = "tiny"
  val csvDir = s"../graphs/ldbc-snb-bi/csv-sf${sf}/"
  val dbDir = s"../graphs/ldbc-snb-bi/db-sf${sf}/graph.db"

  // val targets = Seq(1, 2, 3, 4, 6, 7, 8, 9, 12, 13, 15, 17, 21, 22, 23, 24)
  val queries = Seq(2, 3, 4, 6, 7, 8, 9, 12, 15, 22, 23, 24)

  val testCases: Seq[LdbcSnbTestCase] = queries.sorted map (query => new LdbcSnbTestCase(
    "bi", sf, query, csvDir
  ))

  testCases.foreach {
    tc =>
      ignore(s"${tc.name}") {
        val ntr = new Neo4jTestRunner(tc, Some(dbDir))
        val neo4jResults = ntr.run()
        ntr.close()

        val itr = new IngraphTestRunner(tc)
        val ingraphResults = itr.run()

        println("neo4j   results: " + neo4jResults.map(x => x.toSeq.sortBy(_._1)))
        println("ingraph results: " + ingraphResults.map(x => x.toSeq.sortBy(_._1)))
        assert(ingraphResults == neo4jResults)
      }
  }

}
