package ingraph.tests

import org.scalatest.FunSuite

class LdbcSnbBiTest extends FunSuite {

  val testCases: Seq[LdbcSnbTestCase] = 1 to 25 map (new LdbcSnbTestCase("bi", _))

  testCases.foreach {
    tc =>
      test(tc.name) {
        try {
          //println(tc.parameters.mkString("Parameters: (\n\t", "\n\t", "\n)"))

          val neo4jResults = TestRunners.neo4jTestRunner(tc)
          println(neo4jResults.mkString("Neo4j Results: (\n\t", "\n\t", "\n)"))

          assert(neo4jResults.nonEmpty)

          //val ingraphResults = TestRunners.ingraphTestRunner(tc)
          //println(ingraphResults.mkString("ingraph Results: (\n\t", "\n\t", "\n)"))

          //assert(neo4jResults.equals(ingraphResults))
        } catch {
          case e: Exception => {
            e.printStackTrace()
            System.err.println(
              s"""Query:
                 |${tc.query}
                 |========================="""
                .stripMargin
            )
            fail(e)
          }
        }
      }
  }

}
