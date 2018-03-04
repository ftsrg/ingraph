package ingraph.tests

import ingraph.testrunners.{IngraphTestRunner, Neo4jTestRunner}
import org.scalatest.FunSuite

class BiValidationTest extends FunSuite {

  def graphMLDir = f"../graphs/ldbc-snb-bi/graphmls/"
  def graphMLPostfix = ".graphml"

//  def csvDir: String = f"../graphs/ldbc-snb-bi/"
  def csvPostfix = "_0_0.csv"

  val sf = "01"
  def graphMLPath = f"../graphs/ldbc-snb-bi/graphmls/sf${sf}.graphml"
  def csvDir = f"../graphs/ldbc-snb-bi/"

  val forumId = sf match {
    case "tiny" => 274877906944L
    case "01" => 893353197569L
    case "03" => 893353197569L
    case "1" => 1786706395137L
  }
//  val updateQuerySpecification =
//    s"""MATCH (f:Forum {id: ${forumId}})
//       |DETACH DELETE f
//    """.stripMargin

  val collects = Seq(10)
  val collectLists = Seq(13, 22)
  val transitives = Seq(14, 16, 18, 19, 20)
  val metaFeatures = Seq(1)
  val optionals = Seq(3, 15)
  val listComprehensions = Seq(11)
  val madness = Seq(25)

  val buggy = Seq(5, 6, 7, 8, 17, 19, 21)
  val slow = Seq(16)
  val working = Seq(2, 4, 8, 9, 12, 23, 24)
  val bench = Seq(24)

  val testCases: Seq[LdbcSnbTestCase] = Seq() map (i => new LdbcSnbTestCase("bi", i, f"${csvDir}/", csvPostfix, Seq(updateQuerySpecification)))

//  val ntr = new Neo4jTestRunner
//  ntr.load(graphMLPath)

  testCases.foreach {
    tc =>
      test(s"${tc.name}") {
        println(tc.name)

        val itr = new IngraphTestRunner(tc)
        val ingraphResults = itr.run()

        val ntr = new Neo4jTestRunner(tc, None)
        ntr.load(graphMLDir + tc.name + graphMLPostfix)
        val neo4jResults = ntr.run()
        ntr.close

        println("ingraph results: " + ingraphResults.map(x => x.toSeq.sortBy(_._1)))
        println("neo4j results: " + neo4jResults.map(x => x.toSeq.sortBy(_._1)))
        assert(ingraphResults == neo4jResults)
      }
  }

}
