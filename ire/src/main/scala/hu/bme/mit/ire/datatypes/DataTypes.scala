package hu.bme.mit.ire

import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.util.BufferMultimap

import scala.collection.mutable

package object datatypes {
  type Tuple = IndexedSeq[Any]
  type TupleBag = Iterable[Tuple] //mutable.Map[Tuple, Integer]
  type Mask = Vector[Int]
  type CypherList = IndexedSeq[Any] // TODO Cypher is not a good name on this level of abstraction
  type JoinCache = BufferMultimap[Tuple, Tuple]

  type Path = Vector[Long]
  val Path = scala.collection.immutable.Vector

  object Slot extends Enumeration {
    type Slot = Value
    val Primary, Secondary = Value
  }
}
