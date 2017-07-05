package ingraph.relalg.util

import com.google.common.collect.ImmutableList
import ingraph.relalg.collectors.CollectionHelper
import org.scalatest.FlatSpec
import relalg.{NamedElement, RelalgFactory, RelalgModelElement}

class CollectionHelperTest extends FlatSpec {

  "CollectionHelper" should "calculate unions" in {
    val ch = new CollectionHelper

    val a = ImmutableList.of(1, 2)
    val b = ImmutableList.of(2, 3)
    println(ch.union_n(a, b))

    val f = RelalgFactory.eINSTANCE
    val v1 : NamedElement = f.createVertexVariable()
    val v2 : NamedElement = f.createVertexVariable()
    v1.setName("x")
    v2.setName("x")

    val v1l = ImmutableList.of(v1)
    val v2l = ImmutableList.of(v2)

    val u = ch.uniqueUnion_n(v1l, v2l)
    println(u.size())
    println(u)
  }

}
