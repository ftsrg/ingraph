package ingraph.sandbox

import ingraph.compiler.cypher2qplan.CypherParser
import ingraph.emf.util.PrettyPrinter
import org.scalatest.FunSuite

class TrainBenchmarkCypherTest extends FunSuite {

  test("Random test from cypher string") {
    TrainBenchmarkCypherTest.testQueryString(
      """MATCH (segment:Segment)
        |WHERE segment.length <= 0
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

  def testQueryFile(queryName: String): Unit = {
    val cypher = CypherParser.parseFile(queryPackPath + queryName)

    if (printCypher) println(PrettyPrinter.format(cypher))
  }

  def testQueryString(queryString: String): Unit = {
    val cypher = CypherParser.parseString(queryString)

    if (printCypher) println(PrettyPrinter.format(cypher))
  }
}
