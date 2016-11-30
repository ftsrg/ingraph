package ingraph.ire

import org.scalatest.FlatSpec

import hu.bme.mit.ire.TransactionFactory
import relalg.RelalgContainer


class IntegrationTest extends FlatSpec {
  val modelPath = "../trainbenchmark/models/railway-repair-1-tinkerpop.graphml"
  case class TestCase(name: String, network: RelalgContainer, expectedResultSize: Int)

//  Vector(
//    TestCase("PosLength", TrainBenchmarkUtil.posLength(), 95),
//    TestCase("RouteSensor", TrainBenchmarkUtil.routeSensor(), 18),
//    TestCase("SemaphoreNeighbor", TrainBenchmarkUtil.semaphoreNeighbor(), 3),
//    TestCase("SwitchMonitored", TrainBenchmarkUtil.switchMonitored(), 0),
//    TestCase("SwitchSet", TrainBenchmarkUtil.switchSet(), 5)
//  ).foreach(
//    t => t.name should "work" in {
//      val adapter = new IngraphAdapter(t.network)
//      val tf = new TransactionFactory(16)
//      tf.subscribe(adapter.engine.inputLookup)
//      val tran = tf.newBatchTransaction()
//
//      adapter.readGraph(modelPath, tran)
//      tran.close()
//      val results = adapter.engine.getResults().size
//      assert(results == t.expectedResultSize)
//    }
//  )
}
