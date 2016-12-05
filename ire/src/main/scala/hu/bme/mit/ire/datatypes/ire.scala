package hu.bme.mit.ire

import scala.collection.mutable

package object datatypes {
  type Tuple = IndexedSeq[Any]
  type Mask = IndexedSeq[Int]
  type Indexer = mutable.HashMap[Tuple, mutable.Set[Tuple]] with mutable.MultiMap[Tuple, Tuple]

  object Slot extends Enumeration {
    type Slot = Value
    val Primary, Secondary = Value
  }
}
