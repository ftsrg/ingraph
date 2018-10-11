package ingraph.tests

import ingraph.testrunners.{IngraphTestRunner, Neo4jTestRunner}
import ldbc.LdbcUpdateLoader
import org.scalatest.FunSuite

class BiValidationTest extends FunSuite {

  val sf = "01"
  val csvDir = s"../graphs/ldbc-snb-bi/csv-sf${sf}/"
  val dbDir = s"../graphs/ldbc-snb-bi/db-sf${sf}/graph.db"

  // val targets = Seq(1, 2, 3, 4, 6, 7, 8, 9, 12, 13, 15, 17, 21, 22, 23, 24)
  val queries = Seq(2, 3, 4, 6, 7, 8, 9, 12, 15, 22, 23, 24)

  val testCases: Seq[LdbcSnbTestCase] = queries.sorted map (query => new LdbcSnbTestCase(
    "bi", sf, query, csvDir
  ))

  testCases.foreach {
    tc =>
      test(s"${tc.name}") {
        val ntr = new Neo4jTestRunner(tc, Some(dbDir))
        val neo4jRunInfo: (Iterable[Seq[Map[String, Any]]], Iterable[Long]) = ntr.run()
        val neo4jResults: Iterable[Seq[Map[String, Any]]] = neo4jRunInfo._1
        ntr.close()

        val itr = new IngraphTestRunner(tc)
        val ingraphRunInfo: (Iterable[Seq[Map[String, Any]]], Iterable[Long]) = itr.run()
        val ingraphResults: Iterable[Seq[Map[String, Any]]] = ingraphRunInfo._1
        itr.close()

        neo4jResults.foreach(
          r => println("neo4j   results: " + r.map(x => x.toSeq.sortBy(_._1)))
        )
        ingraphResults.foreach(
          r => println("ingraph results: " + r.map(x => x.toSeq.sortBy(_._1)))
        )
        assert(ingraphResults == neo4jResults)
      }
  }

}
