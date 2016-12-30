package ingraph.ire

import org.scalatest.FlatSpec
import hu.bme.mit.ire.TransactionFactory
import hu.bme.mit.ire.trainbenchmark.TrainbenchmarkQuery
import relalg.RelalgContainer

import scala.io.Source


class IntegrationTest extends FlatSpec {
  val modelPath = "../trainbenchmark/models/railway-repair-1-tinkerpop.graphml"
  def queryPath(query: String): String = s"queries/trainbenchmark/$query.cypher"
  case class TestCase(name: String, expectedResultSize: Int)

  Vector(
    TestCase("PosLength", 95),
    TestCase("RouteSensor", 18),
    TestCase("SemaphoreNeighbor", 3),
    TestCase("SwitchMonitored", 0),
    TestCase("SwitchSet", 5)
  ).foreach(
    t => t.name should "work" in {
      val query = Source.fromFile(queryPath(t.name)).getLines().mkString
      val adapter = new IngraphAdapter(query)
      val tf = new TransactionFactory(16)
      tf.subscribe(adapter.engine.inputLookup)
      val tran = tf.newBatchTransaction()

      adapter.readGraph(modelPath, tran)
      tran.close()
      val results = adapter.engine.getResults().size
      assert(results == t.expectedResultSize)
    }
  )
}
