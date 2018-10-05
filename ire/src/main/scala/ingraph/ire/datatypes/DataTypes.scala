package ingraph.ire

import ingraph.ire.collections.BufferMultimap

package object datatypes {
  type Tuple = IndexedSeq[Any]
  type TupleBag = Vector[Tuple]
  type Mask = Seq[Int]
  type JoinCache = BufferMultimap[Tuple, Tuple]

  type Path = Vector[Long]
  val Path = scala.collection.immutable.Vector

  object Slot extends Enumeration {
    type Slot = Value
    val Primary, Secondary = Value
  }
}
