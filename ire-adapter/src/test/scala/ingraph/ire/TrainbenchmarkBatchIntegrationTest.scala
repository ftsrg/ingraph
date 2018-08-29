package ingraph.ire

import org.scalatest.FunSuite

class TrainbenchmarkBatchIntegrationTest extends FunSuite {

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
    TestCase("ConnectedSegments", 2, 16),
    null
  ).filter(_ != null).foreach(
    t => test(s"${t.name}-size-${t.size}") {
      val querySpec = TrainbenchmarkUtils.readQueryFromResources(t.name)
      assert(TrainbenchmarkUtils.readModelAndGetResults(querySpec, t.size).size == t.expectedResultSize)
    }
  )

  test("Sort with Limit works") {
    val query = "MATCH (n: Segment) RETURN n ORDER BY n DESC SKIP 5 LIMIT 10"
    val results = TrainbenchmarkUtils.readModelAndGetResults(query, 1)
    val expected = ((1400 to 1410).toSet - 1405).toList.sorted.reverse.map(n => Vector(n.toLong))
    assert(results == expected)
  }

  test("Sort without Limit works") {
    val query = "MATCH (n: Segment) RETURN n ORDER BY n DESC SKIP 1100"
    val results = TrainbenchmarkUtils.readModelAndGetResults(query, 1)
    val expected = (7 to 9).reverse.map(n => Vector(n.toLong))
    assert(results == expected)
  }

  test("basic aggregations") {
    val query = "MATCH (s: Switch) RETURN count(s)"
    val results = TrainbenchmarkUtils.readModelAndGetResults(query, 1)
    assert(results == List(Vector(40)))
  }
}
