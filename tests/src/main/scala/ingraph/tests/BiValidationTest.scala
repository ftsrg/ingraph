package ingraph.tests

import org.scalatest.FunSuite

class BiValidationTest extends FunSuite {

  val collects = Seq(10)
  val collectLists = Seq(13, 22)
  val transitives = Seq(14, 16, 18, 19, 20)
  val metaFeatures = Seq(1)
  val optionals = Seq(3, 15)
  val listComprehensions = Seq(11)
  val madness = Seq(25)

//  val working = Seq(2, 4, 5, 6, 7, 8, 9, 12, 16, 17, 19, 21, 23, 24)
  val working = Seq(24)

  val testCases: Seq[LdbcSnbTestCase] = working map (new LdbcSnbTestCase("bi", _))

  testCases.foreach {
    tc =>
      test(s"${tc.name}") {
        val neo4jResults   = TestRunners.neo4jTestRunner(tc)
        val ingraphResults = TestRunners.ingraphTestRunner(tc)

        println("neo4j results:   " + neo4jResults  .map(x => x.toSeq.sortBy(_._1)))
        println("ingraph results: " + ingraphResults.map(x => x.toSeq.sortBy(_._1)))

        assert(ingraphResults == neo4jResults)
      }
  }

}
