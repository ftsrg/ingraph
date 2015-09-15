import scala.collection.mutable

import org.scalatest.WordSpec

import hu.bme.mit.incquerydcore.IterableMultiMap

/**
 * Created by wafle on 8/30/2015.
 */
class IterableMultiMapTest extends WordSpec{
  "IterableMultiMap.unzip" should {
    "return the right number of values" in {
      val map = new mutable.HashMap[Int, mutable.Set[Int]] with IterableMultiMap[Int, Int]
      map.addBinding(1,3)
      map.addBinding(1,4)
      map.addBinding(5,6)
      val (keys, values) = map.multiUnzip
      assert(keys.size == 3)
      assert(values.size == 3)
    }
    "return the right values" in {
      val map = new mutable.HashMap[Int, mutable.Set[Int]] with IterableMultiMap[Int, Int]
      map.addBinding(1,3)
      map.addBinding(1,4)
      map.addBinding(5,6)
      val (keys, values) = map.multiUnzip
      val checkMap = mutable.Map(
        1->mutable.Set(3,4),
        5->mutable.Set(6)
      )
      (keys zip values).foreach(
        kv => checkMap(kv._1).remove(kv._2)
      )
      assert(checkMap == mutable.Map(
        1->mutable.Set.empty,
        5-> mutable.Set.empty)
      )
    }
  }
}
