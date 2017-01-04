package ingraph.ire

import hu.bme.mit.ire.TransactionFactory
import org.scalatest.FlatSpec

import scala.io.Source

class IntegrationTest extends FlatSpec {
  
  def modelPath(size: Int) = s"src/test/resources/railway-repair-$size-tinkerpop.graphml"
  def queryPath(query: String): String = s"../queries/trainbenchmark/$query.cypher"
  case class TestCase(name: String, size: Int, expectedResultSize: Int)

  Vector(
    TestCase("PosLength",         1, 95),
    TestCase("RouteSensor",       1, 18),
    TestCase("SemaphoreNeighbor", 1, 3),
    TestCase("SwitchMonitored",   1, 0),
    TestCase("SwitchSet",         1, 5),
    //
    TestCase("PosLength",         2, 208), 
    TestCase("RouteSensor",       2, 33),
    TestCase("SemaphoreNeighbor", 2, 6),
    TestCase("SwitchMonitored",   2, 2),
    TestCase("SwitchSet",         2, 8)    
  ).foreach(
    t => t.name should "work" in {
      val query = Source.fromFile(queryPath(t.name)).getLines().mkString
      val adapter = new IngraphAdapter(query)
      val tf = new TransactionFactory(16)
      tf.subscribe(adapter.engine.inputLookup)
      val tran = tf.newBatchTransaction()

      adapter.readGraph(modelPath(t.size), tran)
      tran.close()
      val results = adapter.engine.getResults().size
      assert(results == t.expectedResultSize)
    }
  )
}
