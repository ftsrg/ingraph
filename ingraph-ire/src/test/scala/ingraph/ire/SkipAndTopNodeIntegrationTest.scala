package ingraph.ire

import hu.bme.mit.ire.TransactionFactory
import org.scalatest.FlatSpec

import scala.io.Source

class SkipAndTopNodeIntegrationTest extends FlatSpec {
  "SkipAndTopNode" should "work" in {
    val query = "MATCH (n: Segment) RETURN n ORDER BY n SKIP 5 LIMIT 10"
    val results = TrainbenchmarkUtils.readModelAndGetResults(query, 1)
    val expected = ((1400 to 1410).toSet - 1405).map(n => Vector(n.toLong))
    assert(results == expected)
  }
}
