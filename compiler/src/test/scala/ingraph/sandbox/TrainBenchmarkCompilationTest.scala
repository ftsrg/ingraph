package ingraph.sandbox

import ingraph.compiler.CypherToQPlan
import ingraph.compiler.cypher2qplan.CypherParser
import ingraph.emf.util.PrettyPrinter
import org.scalatest.FunSuite
import org.slizaa.neo4j.opencypher.{openCypher => oc}

/**
  * Test queries without invoking the inferencer.
  */
class TrainBenchmarkCompilationTest extends FunSuite {

  test("Random test from cypher string") {
    TrainBenchmarkCompilationTest.testQueryString(
      """MATCH (segment:Segment:Vertex)-[e2:Foo|Boo]-(v2:Bar)-[e3:Foo]-(v3:Bar), (a)-[ee]->(b)
        |WHERE segment.length < sin(0 + 2*0 % 6)
        |WITH *, segment, "foo" as foo
        |OPTIONAL MATCH (v)
        |WHERE foo = "foo"
        |RETURN DISTINCT segment, segment.length AS length, 2*5 as ten, sum(foo.size)""".stripMargin)

  }

  test("Random double edge variable in the same MATCH") {
    TrainBenchmarkCompilationTest.testQueryString(
      """MATCH
        |  (a)-[e]->(b),
        |  (a)-[e]->(b)
        |RETURN a, e, b""".stripMargin)

  }

  test("Random double edge variable in separate MATCHes") {
    TrainBenchmarkCompilationTest.testQueryString(
      """MATCH (a)-[e]->(b)
        |MATCH (a)-[e]->(b)
        |RETURN a, e, b""".stripMargin)

  }

//  for (queryFile <- new File("queries/trainbenchmark-simple").listFiles()) {
//    test(queryFile.getName.dropRight(".cypher".length)) {
//      val query = FileUtils.readFileToString(queryFile)
//      val ep = IPlanParser.parse(query)
//    }
//  }


  test("PosLength.cypher") {
    TrainBenchmarkCompilationTest.testQueryFile("PosLength")
  }

  test("ConnectedSegments.cypher") {
    TrainBenchmarkCompilationTest.testQueryFile("ConnectedSegments")
  }

  test("RouteSensor.cypher") {
    TrainBenchmarkCompilationTest.testQueryFile("RouteSensor")
  }

  test("SwitchMonitored.cypher") {
    TrainBenchmarkCompilationTest.testQueryFile("SwitchMonitored")
  }

  test("SwitchSet.cypher") {
    TrainBenchmarkCompilationTest.testQueryFile("SwitchSet")
  }

  test("SemaphoreNeighbor.cypher") {
    TrainBenchmarkCompilationTest.testQueryFile("SemaphoreNeighbor")
  }
}

object TrainBenchmarkCompilationTest {
  val queryPackPath = "trainbenchmark/"
  val printCypher = true
  val printQPlan = true
  val skipResolve = false
  val skipBeautify = false

  def testQueryFile(queryName: String): Unit = {
    val cypher = CypherParser.parseFile(queryPackPath + queryName)

    testQueryCommon(cypher, queryName)
  }

  def testQueryString(queryString: String, queryName: String = "ad-hoc query"): Unit = {
    val cypher = CypherParser.parseString(queryString)

    testQueryCommon(cypher, queryName)
  }

  def testQueryCommon(cypher: oc.Cypher, queryName: String): Unit = {
    if (printCypher) println(PrettyPrinter.format(cypher))

    val qplan = CypherToQPlan.build_IKnowWhatImDoing(cypher, queryName, skipResolve = skipResolve, skipBeautify = skipBeautify)

    if (printQPlan) println(qplan)
  }
}
