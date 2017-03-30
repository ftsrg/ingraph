package ingraph.ire

import org.scalatest.FunSuite

import scala.io.Source

class TrainbenchmarkBatchIntegrationTest extends FunSuite {

  def queryPath(query: String): String = s"../queries/trainbenchmark/$query.cypher"

  case class TestCase(name: String, size: Int, expectedResultSize: Int)

  Vector(
    TestCase("PosLength", 1, 95),
    TestCase("RouteSensor", 1, 18),
    TestCase("SemaphoreNeighbor", 1, 3),
    TestCase("SwitchMonitored", 1, 0),
    TestCase("SwitchSet", 1, 5),
    TestCase("ConnectedSegments", 1, 8),
    //
    TestCase("PosLength", 2, 208),
    TestCase("RouteSensor", 2, 33),
    TestCase("SemaphoreNeighbor", 2, 6),
    TestCase("SwitchMonitored", 2, 2),
    TestCase("SwitchSet", 2, 8),
    TestCase("ConnectedSegments", 2, 16)
  ).foreach(
    t => test(s"${t.name}-size-${t.size}") {
      val query = Source.fromFile(queryPath(t.name)).getLines().mkString(" ")
      TrainbenchmarkUtils.readModelAndGetResults(query, t.size).size == t.expectedResultSize
    }
  )

  "SortAndTopNode" should "work" in {
    val query = "MATCH (n: Segment) RETURN n ORDER BY n DESC SKIP 5 LIMIT 10"
    val results = TrainbenchmarkUtils.readModelAndGetResults(query, 1)
    val expected = ((1400 to 1410).toSet - 1405).toList.sorted.reverse.map(n => Vector(n.toLong))
    assert(results == expected)
  }
}
