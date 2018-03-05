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

  val meta = Seq(1)
  val listComprehensions = Seq(11, 13) //2
  val madness = Seq(25)
  val orderby = Seq(10)
  val hangs = Seq(19)
  val startWithWith = Seq(20)

  val transitives = Seq(14, 16, 18) //3
  val buggy =   Seq(3, 5, 6, 8, 15, 17, 21) //7
  val working = Seq(2, 4, 7, 9, 12, 22, 23, 24) //8

  val testCases: Seq[LdbcSnbTestCase] = (transitives++buggy++working).sorted map (i => new LdbcSnbTestCase("bi", i, f"${csvDir}/$i%02d/", csvPostfix, Seq()))

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
