package ingraph.ire

import hu.bme.mit.ire.TransactionFactory
import org.scalatest.FlatSpec

import scala.io.Source

class TrainBenchmarkBatchTest extends FlatSpec {

  def modelPath(size: Int, entityName: String) = s"../graphs/trainbenchmark/railway-repair-$size-$entityName.csv"

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
    t => s"${t.name}-size-${t.size}" should "work" in {
      val query = Source.fromFile(queryPath(t.name)).getLines().mkString(" ")
      TrainbenchmarkUtils.readModelAndGetResults(query, t.size).size == t.expectedResultSize
    }
  )
}
