package ingraph.ire

import org.scalatest.FunSuite

import scala.io.Source

class DualTest extends FunSuite {
  test("Optional match returns Vector(null) on empty input") {
    val query = "OPTIONAL MATCH (n) RETURN n"
    val adapter = new IngraphAdapter(query, "opt")
    assert(adapter.engine.getResults() == List(Vector(null)))
  }

}
