package ingraph.tests

import ingraph.tests.runner.{IngraphTestRunner, Neo4jTestRunner}
import org.scalatest.FunSuite

class LdbcSnbBiTest extends FunSuite {

  val testCases: Seq[LdbcSnbTestCase] = 1 to 25 map (new LdbcSnbTestCase("bi", _))

  testCases.foreach {
    tc =>
      test(s"${tc.workload}-${tc.number}-size-1") {
        val neo4jRunner = new Neo4jTestRunner(tc)
        val ingraphRunner = new IngraphTestRunner(tc)

        val neo4jResults = neo4jRunner.getResults
        val ingraphResults = ingraphRunner.getResults

        println(tc.parameters.mkString("Parameters: (\n\t", "\n\t", "\n)"))
        println(neo4jResults.mkString("Neo4j Results: (\n\t", "\n\t", "\n)"))
        println(ingraphResults.mkString("ingraph Results: (\n\t", "\n\t", "\n)"))

        //assert(neo4jResult.equals(ingraphResults))
      }
  }

}
