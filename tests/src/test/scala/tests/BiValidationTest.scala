package ingraph.tests

import ingraph.compiler.sql.Util._
import ingraph.testrunners.{C2STestRunner, IngraphTestRunner, Neo4jTestRunner}
import org.scalatest.FunSuite

class BiValidationTest extends FunSuite {

  val sf = "tiny"
  val csvDir = s"../graphs/ldbc-snb-bi/csv-sf${sf}/"
  val dbDir = s"../graphs/ldbc-snb-bi/db-sf${sf}/graph.db"

  val queryRange = 1 to 25

  // val targets = Seq(1, 2, 3, 4, 6, 7, 8, 9, 12, 13, 15, 17, 21, 22, 23, 24)
  // formatter markers: https://stackoverflow.com/a/19492318
  // @formatter:off
  val ingraphQueries = Set(2, 3, 4, 6, 7, 8, 9, 12, 15,             22, 23, 24)
  val c2sQueries     = Set(2, 3, 4, 6, 7, 8, 9, 12, 15, 16, 17, 19,     23, 24)
  // @formatter:on

  val testCases: Seq[LdbcSnbTestCase] = queryRange
    .map(query => new LdbcSnbTestCase(
      "bi", sf, query, csvDir, ingraphQueries.contains(query), c2sQueries.contains(query)))
    .filter(tc => tc.testIngraph || tc.testC2S)

  testCases.foreach {
    tc =>
      test(s"${tc.name}") {
        val neo4jResults =
          withResources(new Neo4jTestRunner(tc, Some(dbDir))) {
            _.run()._1
          }

        val ingraphResultsOpt = tc.testIngraph.toOption(
          withResources(new IngraphTestRunner(tc)) {
            _.run()._1
          })

        val c2sResultsOpt = tc.testC2S.toOption(
          withResources(new C2STestRunner(tc)) {
            _.run()._1
          })

        neo4jResults.foreach(
          r => println("neo4j   results: " + r.map(_.toSeq.sortBy(_._1)))
        )

        ingraphResultsOpt.foreach(
          _.foreach(r => println("ingraph results: " + r.map(_.toSeq.sortBy(_._1)))))
        c2sResultsOpt.foreach(
          _.foreach(r => println("c2s     results: " + r.map(_.toSeq.sortBy(_._1)))))

        if (ingraphResultsOpt.isDefined)
          assert(ingraphResultsOpt.get == neo4jResults)

        if (c2sResultsOpt.isDefined) {
          // compare only initial run
          assert(c2sResultsOpt.get.head == neo4jResults.head)
        }
      }
  }

}
