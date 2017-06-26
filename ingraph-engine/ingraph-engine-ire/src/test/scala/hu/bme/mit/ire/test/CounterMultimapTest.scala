package hu.bme.mit.ire.test

import org.scalatest.Matchers
import org.scalatest.WordSpecLike
import hu.bme.mit.ire.util.CounterMultimap

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
