package ingraph.expressionparser

import com.google.common.collect.ImmutableMap
import org.eclipse.emf.common.util.EList

import scala.collection.mutable

object Conversions {
  implicit def emf[E](list: EList[E]): Vector[E] = {
    val l = for (v <- 0 until list.size)
      yield list.get(v)
    l.toVector
  }

  implicit def emfToInt(list: EList[Integer]): Vector[Int] = {
    var l = for (v <- 0 until list.size)
      yield list.get(v).toInt
    l.toVector
  }

  implicit def guava[K, V](map: ImmutableMap[K, V]): Map[K, V] = {
    val iterator = map.entrySet().iterator()
    val scalaMap = new mutable.HashMap[K, V]()
    while (iterator.hasNext) {
      val entry = iterator.next()
      scalaMap(entry.getKey) = entry.getValue
    }
    scalaMap.toMap
  }
}
