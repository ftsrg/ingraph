package ingraph.tests

import ingraph.tests.runner.{IngraphTestRunner, Neo4jTestRunner}
import org.scalatest.FunSuite

class LdbcSnbBiTest extends FunSuite {

  val testCases: Seq[LdbcSnbTestCase] = 1 to 25 map (new LdbcSnbTestCase("bi", _))

  testCases.foreach {
    tc =>
      test(s"${tc.workload}-${tc.number}-size-1") {
//        println(
//          s"""Query:
//             |${tc.querySpecification}
//             |========================="""
//            .stripMargin
//        )
        println(tc.parameters.mkString("Parameters: (\n\t", "\n\t", "\n)"))
        val neo4jRunner = new Neo4jTestRunner(tc)
        val ingraphRunner = new IngraphTestRunner(tc)

        val neo4jResults = neo4jRunner.getResults
        println(neo4jResults.mkString("Neo4j Results: (\n\t", "\n\t", "\n)"))

        val ingraphResults = ingraphRunner.getResults
        println(ingraphResults.mkString("ingraph Results: (\n\t", "\n\t", "\n)"))
        //assert(neo4jResult.equals(ingraphResults))
      }
  }

}
