package ingraph.sandbox

import ingraph.compiler.cypher2qplan
import ingraph.compiler.cypher2qplan.CypherParser
import ingraph.emf.util.PrettyPrinter
import org.scalatest.FunSuite

class TrainBenchmarkCypherTest extends FunSuite {

  test("Random test from cypher string") {
    TrainBenchmarkCypherTest.testQueryString(
      """MATCH (segment:Segment)
        |WHERE segment.length <= 0
        |WITH segment
        |OPTIONAL MATCH (v)
        |WHERE v.foo = segment.length
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

  def testQueryFile(queryName: String): Unit = {
    val cypher = CypherParser.parseFile(queryPackPath + queryName)

    if (printCypher) println(PrettyPrinter.format(cypher))

    val qplan = cypher2qplan.build(cypher, queryName)

    if (printQPlan) println(qplan)
  }

  def testQueryString(queryString: String, queryName: String = "ad-hoc query"): Unit = {
    val cypher = CypherParser.parseString(queryString)

    if (printCypher) println(PrettyPrinter.format(cypher))

    val qplan = cypher2qplan.build(cypher, queryName)

    if (printQPlan) println(qplan)
  }
}
