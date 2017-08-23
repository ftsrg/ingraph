package ingraph.sandbox

import ingraph.compiler.cypher2qplan
import ingraph.compiler.cypher2qplan.CypherParser
import ingraph.emf.util.PrettyPrinter
import org.scalatest.FunSuite
import org.slizaa.neo4j.opencypher.{openCypher => oc}

class TrainBenchmarkCypherTest extends FunSuite {

  test("Random test from cypher string") {
    TrainBenchmarkCypherTest.testQueryString(
      """MATCH (segment:Segment:Vertex)-[e2:Foo|Boo]-(v2:Bar)-[e3:Foo]-(v3:Bar), (a)-[ee]->(b)
        |WHERE segment.length < 0 + 2*0 % 6
        |WITH *, segment, "foo" as foo
        |OPTIONAL MATCH (v)
        |WHERE foo = "foo"
        |RETURN DISTINCT segment, segment.length AS length""".stripMargin)

  }

  test("PosLength.cypher") {
    TrainBenchmarkCypherTest.testQueryFile("PosLength")
  }

  test("ConnectedSegments.cypher") {
    TrainBenchmarkCypherTest.testQueryFile("ConnectedSegments")
  }

  test("RouteSensor.cypher") {
    TrainBenchmarkCypherTest.testQueryFile("RouteSensor")
  }

  test("SwitchMonitored.cypher") {
    TrainBenchmarkCypherTest.testQueryFile("SwitchMonitored")
  }

  test("SwitchSet.cypher") {
    TrainBenchmarkCypherTest.testQueryFile("SwitchSet")
  }

  test("SemaphoreNeighbor.cypher") {
    TrainBenchmarkCypherTest.testQueryFile("SemaphoreNeighbor")
  }
}

object TrainBenchmarkCypherTest {
  val queryPackPath = "trainbenchmark/"
  val printCypher = true
  val printQPlan = true
  val skipResolve = true
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

    val qplan = cypher2qplan.build_IKnowWhatImDoing(cypher, queryName, skipResolve = skipResolve, skipBeautify = skipBeautify)

    if (printQPlan) println(qplan)
  }
}
