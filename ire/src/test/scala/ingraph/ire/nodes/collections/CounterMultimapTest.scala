package ingraph.ire.nodes.collections

import ingraph.ire.collections.CounterMultimap
import org.scalatest.{Matchers, WordSpecLike}

class CounterMultimapTest extends WordSpecLike with Matchers {

  "A CounterMultimap" must {
    "add and remove" in {
      val map = new CounterMultimap[Int, Int]

      map.addBinding(1, 1)
      map.addBinding(1, 2)

      map.getCount(1, 1) shouldBe 1
      map.getCount(1, 2) shouldBe 1

      map.addBinding(1, 1)
      map.addBinding(1, 2)

      map.getCount(1, 1) shouldBe 2
      map.getCount(1, 2) shouldBe 2

      map.addBinding(1, 1)
      map.getCount(1, 1) shouldBe 3

      map.values(1).toSet should equal(Set(1, 2))

      map.removeBinding(1, 2)
      map.getCount(1, 2) shouldBe 1
      map.removeBinding(1, 2)
      map.getCount(1, 2) shouldBe 0

      map.values(1).toSet should equal(Set(1))

      map.removeBinding(1, 1)
      map.getCount(1, 1) shouldBe 2
      map.removeBinding(1, 1)
      map.getCount(1, 1) shouldBe 1
      map.removeBinding(1, 1)
      map.getCount(1, 1) shouldBe 0

      map.values(1).toSet should equal(Set())
      map.values(2).toSet should equal(Set())
    }
  }

}
