package ingraph.relalg.collectors

import java.{lang, util}

import com.google.common.collect.{ImmutableList, Iterables, Lists, Sets}
import relalg.RelalgModelElement

import scala.collection.JavaConverters._
import scala.collection.mutable.ListBuffer

class CollectionHelper {

  def union_n[T](lists: java.lang.Iterable[_ <: T]*): java.util.List[T] = {
    new util.ArrayList(lists.map(x => x.asScala).flatten.toList.asJava)
  }

  def union[T](l1: java.lang.Iterable[_ <: T], l2: java.lang.Iterable[_ <: T]): java.util.List[T] = {
    union_n(l1, l2)
  }

  def union[T](l1: java.lang.Iterable[_ <: T], l2: java.lang.Iterable[_ <: T], l3: java.lang.Iterable[_ <: T]): java.util.List[T] = {
    union_n(l1, l2, l3)
  }

  def uniqueUnion_n[T <: RelalgModelElement](lists: java.lang.Iterable[_ <: T]*): java.util.List[T] = {
    val result = new ListBuffer[T]

    union_n(lists: _*).asScala.foreach(x =>
      if (!result.map(_.fullName).contains(x.fullName)) {
        result += x
      }
    )

    new util.ArrayList(result.asJava)
  }

  def uniqueUnion[T <: RelalgModelElement](l1: java.lang.Iterable[_ <: T], l2: java.lang.Iterable[_ <: T]): java.util.List[T] = {
    uniqueUnion_n(l1, l2)
  }

  def uniqueUnion[T <: RelalgModelElement](l1: java.lang.Iterable[_ <: T], l2: java.lang.Iterable[_ <: T], l3: java.lang.Iterable[_ <: T]): java.util.List[T] = {
    uniqueUnion_n(l1, l2, l3)
  }

  def minus[T](i1: lang.Iterable[_ <: T], i2: lang.Iterable[_ <: T]): java.util.List[T] = {
    val x : java.util.Set[T] = Sets.difference(
      Sets.newLinkedHashSet(i1),
      Sets.newLinkedHashSet(i2)
    )

    new util.ArrayList(x)
  }

}
