package ingraph.tests

import org.scalatest.FunSuite

class LdbcSnbBiTest extends FunSuite {

  val testCases: Seq[LdbcSnbTestCase] = 1 to 25 map (new LdbcSnbTestCase("bi", _))

  testCases.foreach {
    tc =>
      test(s"${tc.name}") {
        val neo4jResults = TestRunners.neo4jTestRunner(tc)
        val ingraphResults = TestRunners.ingraphTestRunner(tc)

        println("neo4j results:   " + neo4jResults)
        println("ingraph results: " + ingraphResults)

        //val ingraphResult = ingraphRunner.getResults()

        //println(tc.parameters.mkString("Parameters: (\n\t", "\n\t", "\n)"))
        //println(neo4jResult.mkString("Results: (\n\t", "\n\t", "\n)"))

        //assert(neo4jResult.equals(ingraphResults))
      }
  }

}
