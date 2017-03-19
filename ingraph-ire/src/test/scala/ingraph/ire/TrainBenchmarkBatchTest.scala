package ingraph.ire

import hu.bme.mit.ire.TransactionFactory
import org.scalatest.FlatSpec

import scala.io.Source

class TrainBenchmarkBatchTest extends FlatSpec {

  def modelPath(size: Int, lofasz: String) = s"../graphs/trainbenchmark/railway-repair-$size-$lofasz.csv"

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
      val adapter = new IngraphAdapter(query)
      val tf = new TransactionFactory(16)
      tf.subscribe(adapter.engine.inputLookup)
      val tran = tf.newBatchTransaction()
      val nodeFilenames: Map[String, List[String]] = Map(
        modelPath(t.size, "Region") -> List("Region"),
        modelPath(t.size, "Route") -> List("Route"),
        modelPath(t.size, "Segment") -> List("Segment", "TrackElement"),
        modelPath(t.size, "Semaphore") -> List("Semaphore"),
        modelPath(t.size, "Sensor") -> List("Sensor"),
        modelPath(t.size, "Switch") -> List("Switch", "TrackElement"),
        modelPath(t.size, "SwitchPosition") -> List("SwitchPosition")
      )

      val relationshipFilenames: Map[String, String] = Map(
        modelPath(t.size, "connectsTo") -> "connectsTo",
        modelPath(t.size, "entry") -> "entry",
        modelPath(t.size, "exit") -> "exit",
        modelPath(t.size, "follows") -> "follows",
        modelPath(t.size, "monitoredBy") -> "monitoredBy",
        modelPath(t.size, "requires") -> "requires",
        modelPath(t.size, "target") -> "target"
      )

      adapter.readCsv(nodeFilenames, relationshipFilenames, tran)
      tran.close()
      val results = adapter.engine.getResults().size
      assert(results == t.expectedResultSize)
    }
  )
}
