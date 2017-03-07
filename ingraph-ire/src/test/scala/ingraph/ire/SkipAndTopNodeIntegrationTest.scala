package ingraph.ire

import hu.bme.mit.ire.TransactionFactory
import org.scalatest.FlatSpec

import scala.io.Source

class SkipAndTopNodeIntegrationTest extends FlatSpec {
  
  def modelPath(size: Int) = s"src/test/resources/railway-repair-$size-tinkerpop.graphml"

  "SkipAndTopNode" should "work" in {
    val query = "MATCH (n) RETURN n ORDER BY n.id LIMIT 10"
    val adapter = new IngraphAdapter(query)
    val tf = new TransactionFactory()
    tf.subscribe(adapter.engine.inputLookup)
    val tran = tf.newBatchTransaction()

    adapter.readGraph(modelPath(1), tran)
    tran.close()
    val results = adapter.engine.getResults().size
    //assert(results == 10)
    println(results)
  }

}
