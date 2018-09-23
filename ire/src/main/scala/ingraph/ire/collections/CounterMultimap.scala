package ingraph.ire.collections

import scala.collection.mutable

class CounterMultimap[K, V] extends mutable.HashMap[K, mutable.HashMap[V, Int]] {

  /** Assigns the specified `value` to a specified `key`.  If the key
    *  already has a binding to equal to `value`, nothing is changed;
    *  otherwise a new binding is added for that `key`.
    *
    *  @param key    The key to which to bind the new value.
    *  @param value  The value to bind to the key.
    *  @return       A reference to this multimap.
    */
  def addBinding(key: K, value: V): this.type = {
    get(key) match {
      case None =>
        this(key) = mutable.HashMap[V, Int](value -> 1)
      case Some(map) =>
        map.get(value) match {
          case None =>
            map += value -> 1
          case Some(counter) =>
            map(value) = counter + 1
        }
    }
    this
  }


  /** Removes the binding of `value` to `key` if it exists, otherwise this
    *  operation doesn't have any effect.
    *
    *  If this was the last value assigned to the specified key, the
    *  set assigned to that key will be removed as well.
    *
    *  @param key     The key of the binding.
    *  @param value   The value to remove.
    *  @return        A reference to this multimap.
    */
  def removeBinding(key: K, value: V): this.type = {
    get(key) match {
      case None =>
      case Some(map) =>
        map.get(value) match {
          case None =>
          case Some(counter) =>
            counter match {
              case 1 => map -= value
              case _ => map(value) = counter - 1
            }
        }
        if (map.isEmpty) this -= key
    }
    this
  }

  def getCount(key: K, value: V): Int = {
    get(key) match {
      case None => 0
      case Some(map) =>
        map.get(value) match {
          case None => 0
          case Some(counter) => counter
        }
    }
  }

  def values(key: K) = {
    getOrElse(key, mutable.HashMap.empty[V, Int]).keysIterator
  }

  def containsEntry(key: K, p: V): Boolean = get(key) match {
    case None => false
    case Some(map) => map contains p
  }

}
