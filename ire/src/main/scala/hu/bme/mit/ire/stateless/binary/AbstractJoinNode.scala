package hu.bme.mit.ire.stateless.binary

import hu.bme.mit.ire.datatypes.{Mask, Tuple, _}
import hu.bme.mit.ire.messages.BatchChangeSet

abstract class AbstractJoinNode(val primaryMask: Mask,
                                val secondaryMask: Mask
                               ) extends StatelessBinaryNode {

  var primaryChangeSet = new BatchChangeSet
  var secondaryIndexer = new JoinCache

  override def onSizeRequest(): Long = 0

  def extract(tuple: Tuple, mask: Mask): Tuple = {
    mask.map(i => tuple(i))
  }

  def onPrimary(cs: BatchChangeSet): Unit = {
    primaryChangeSet = cs
    onUpdate()
  }

  def onSecondary(cs: BatchChangeSet): Unit = {
    secondaryIndexer = new JoinCache
    cs.changeSet.foreach(
      t => secondaryIndexer.addBinding(extract(t, secondaryMask), t)
    )
    onUpdate()
  }

  def onUpdate(): Unit

}
