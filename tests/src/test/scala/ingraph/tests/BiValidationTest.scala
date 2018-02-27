package ingraph.tests

import ingraph.testrunners.{IngraphTestRunner, Neo4jTestRunner}
import org.scalatest.FunSuite

class BiValidationTest extends FunSuite {

  def graphMLDir = f"../graphs/ldbc-snb-bi/graphmls/"
  def graphMLPostfix = ".graphml"

  def csvDir: String = f"../graphs/ldbc-snb-bi/"
  def csvPostfix = "_0_0.csv"

//  def graphMLPath = f"../graphs/ldbc-snb-bi/graphmls/sf-tiny.graphml"
//  def csvDir = f"../graphs/ldbc-snb-bi/sf-tiny/"

//  val sf = "03"
//  def graphMLPath = f"/home/szarnyasg/Dropbox/ldbc_test_data/social_network_sf" + sf + "/snb_" + sf + ".graphml"
//  def csvDir = f"/home/szarnyasg/Dropbox/ldbc_test_data/social_network_sf" + sf + "/"

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

  val testCases: Seq[LdbcSnbTestCase] = working map (i => new LdbcSnbTestCase("bi", i, f"${csvDir}/${i}%02d/", csvPostfix))

  testCases.foreach {
    tc =>
      test(s"${tc.name}") {
        println(tc.name)

        val ingraphResults = IngraphTestRunner.run(tc)
        println("ingraph results: " + ingraphResults.map(x => x.toSeq.sortBy(_._1)))

        val ntr = new Neo4jTestRunner
        ntr.load(graphMLDir + tc.name + graphMLPostfix)
        val neo4jResults = ntr.run(tc.query)
        ntr.close

        println("neo4j results: " + neo4jResults.map(x => x.toSeq.sortBy(_._1)))
        assert(ingraphResults == neo4jResults)
      }
  }

}
