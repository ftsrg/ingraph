package ingraph.ire

import hu.bme.mit.ire.TransactionFactory
import org.scalatest.FlatSpec

import scala.io.Source

class SkipAndTopNodeIntegrationTest extends FlatSpec {
  "SkipAndTopNode" should "work" in {
    val query = "MATCH (n) RETURN n ORDER BY n.id LIMIT 10"
    //assert(results == 10)
    println(results)
    val results = TrainbenchmarkUtils.readModelAndGetResults(query, 1)
  }
}
