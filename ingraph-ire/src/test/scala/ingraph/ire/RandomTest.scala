package ingraph.ire

import org.scalatest.FunSuite

import scala.io.Source

class RandomTest extends FunSuite {
  test("Optional match returns Vector(null) on empty input") {
    val query = "OPTIONAL MATCH (n) RETURN n"
    val adapter = new IngraphAdapter(query, "opt")
    assert(adapter.engine.getResults() == List(Vector(null)))
  }

  test("Constant returns work") {
    val query = "RETURN 1 + 1"
    val adapter = new IngraphAdapter(query, "opt")
    assert(adapter.engine.getResults() == List(Vector(2 )))
  }
}
