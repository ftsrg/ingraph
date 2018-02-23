package ingraph.tests

import org.scalatest.FunSuite

class BiValidationTest extends FunSuite {

  val collects = Seq(5, 10, 21)
  val collectLists = Seq(13, 22)
  val transitives = Seq(14, 16, 18, 19, 20)
  val metaFeatures = Seq(1)
  val optionals = Seq(3, 15)
  val listComprehensions = Seq(11)
  val madness = Seq(25)

  val testCases: Seq[LdbcSnbTestCase] = Seq(2, 4, 6, 7, 8, 9, 12, 17, 23, 24) map (new LdbcSnbTestCase("bi", _))

  testCases.foreach {
    tc =>
      test(s"${tc.name}") {
        val neo4jResults   = TestRunners.neo4jTestRunner(tc)
        val ingraphResults = TestRunners.ingraphTestRunner(tc)

        println("neo4j results:   " + neo4jResults  .map(x => x.toSeq.sortBy(_._1)))
        println("ingraph results: " + ingraphResults.map(x => x.toSeq.sortBy(_._1)))

//        println(tc.parameters.mkString("Parameters: (\n\t", "\n\t", "\n)"))
//        println(neo4jResult.mkString("Results: (\n\t", "\n\t", "\n)"))
        assert(ingraphResults == neo4jResults)
      }
  }

}
