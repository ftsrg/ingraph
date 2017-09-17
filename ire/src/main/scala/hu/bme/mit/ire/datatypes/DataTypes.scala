package hu.bme.mit.ire

import hu.bme.mit.ire.util.BufferMultimap

package object datatypes {
  type Tuple = IndexedSeq[Any]
  type TupleBag = Iterable[Tuple]
  type Mask = Seq[Int]
  type JoinCache = BufferMultimap[Tuple, Tuple]

  type Path = Vector[Long]
  val Path = scala.collection.immutable.Vector

  object Slot extends Enumeration {
    type Slot = Value
    val Primary, Secondary = Value
  }
}
