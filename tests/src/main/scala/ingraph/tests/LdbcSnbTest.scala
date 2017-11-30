package ingraph.tests

import ingraph.tests.runner.Neo4jTestRunner
import org.scalatest.FunSuite

class LdbcSnbTest extends FunSuite {

  val testCases: Seq[LdbcSnbTestCase] = 1 to 25 map (new LdbcSnbTestCase("bi", _))

  testCases.foreach {
    tc =>
      test(s"${tc.workload}-${tc.number}-size-1") {
        val neo4jRunner = new Neo4jTestRunner(tc)
        //val ingraphRunner = new IngraphTestRunner(tc)

        val neo4jResult = neo4jRunner.getResults()
        //val ingraphResult = ingraphRunner.getResults()

        println(tc.parameters.mkString("Parameters: (\n\t", "\n\t", "\n)"))
        println(neo4jResult.mkString("Results: (\n\t", "\n\t", "\n)"))

        //assert(neo4jResult.equals(ingraphResults))
      }
  }

}
