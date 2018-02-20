package ingraph.tests

import org.scalatest.FunSuite

class BiValidationTest extends FunSuite {

  val testCases: Seq[LdbcSnbTestCase] = Seq(2, 3, 4, 6, 7, 8, 9, 12, 15, 17, 23, 24) map (new LdbcSnbTestCase("bi", _))

  testCases.foreach {
    tc =>
      test(s"${tc.name}") {
        val neo4jResults   = TestRunners.neo4jTestRunner(tc)
        val ingraphResults = TestRunners.ingraphTestRunner(tc)

        println("neo4j results:   " + neo4jResults)
        println("ingraph results: " + ingraphResults)

//        println(tc.parameters.mkString("Parameters: (\n\t", "\n\t", "\n)"))
//        println(neo4jResult.mkString("Results: (\n\t", "\n\t", "\n)"))
//        assert(neo4jResults.equals(ingraphResults))
      }
  }

}
