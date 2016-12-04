import hu.bme.mit.ire.Union
import org.scalatest.{FlatSpec, Matchers}

class UnionTest extends FlatSpec with Matchers {

  "Union node" should "work" in {
    val u = new Union()
    u.union()
  }

}
