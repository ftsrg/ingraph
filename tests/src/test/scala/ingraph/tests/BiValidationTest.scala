package ingraph.tests

import ingraph.testrunners.{IngraphTestRunner, Neo4jTestRunner}
import org.scalatest.FunSuite

class BiValidationTest extends FunSuite {

  def csvPostfix: String = "_0_0.csv"

  //  override def graphMLPath: String = f"../graphs/ldbc-snb-${workload}/graphmls/${workload}-${number}%02d.graphml"
  //  def csvDir: String = f"../graphs/ldbc-snb-${workload}/${number}%02d"

  def graphMLPath: String = f"../graphs/ldbc-snb-bi/graphmls/sf-tiny.graphml"
  def csvDir: String = f"../graphs/ldbc-snb-bi/sf-tiny/"

  //  val sf = "03"
  //  override def graphMLPath: String = f"/home/szarnyasg/Dropbox/ldbc_test_data/social_network_sf"+sf+"/snb_"+sf+".graphml"
  //  def csvDir: String = f"/home/szarnyasg/Dropbox/ldbc_test_data/social_network_sf" + sf

  val collects = Seq(10)
  val collectLists = Seq(13, 22)
  val transitives = Seq(14, 16, 18, 19, 20)
  val metaFeatures = Seq(1)
  val optionals = Seq(3, 15)
  val listComprehensions = Seq(11)
  val madness = Seq(25)

  val buggy = Seq(2, 5, 7, 8, 17, 19, 21, 23, 24)

  val working = Seq(4, 6, 9, 12, 16)

  val testCases: Seq[LdbcSnbTestCase] = working map (new LdbcSnbTestCase("bi", _, csvDir, csvPostfix))


  val ntr = new Neo4jTestRunner
  ntr.load(graphMLPath)


  testCases.foreach {
    tc =>
      test(s"${tc.name}") {
        val ingraphResults = IngraphTestRunner.run(tc)
        println("ingraph results: " + ingraphResults.map(x => x.toSeq.sortBy(_._1)))

        val neo4jResults   = ntr.run(tc.query)
        println("neo4j results:   " + neo4jResults.map(x => x.toSeq.sortBy(_._1)))
        assert(ingraphResults == neo4jResults)
      }
  }

}
