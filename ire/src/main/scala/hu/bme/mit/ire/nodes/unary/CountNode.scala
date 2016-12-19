package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}

import scala.collection.immutable.VectorBuilder
import scala.collection.mutable

// the CountNode follows the COUNT(*) semantics, i.e. you do not have to specify the attribute to count
class CountNode(override val next: (ReteMessage) => Unit,
                val aggregationKeys: Vector[Int]) extends UnaryNode with CountLike with SingleForwarder {


  override def onChangeSet(changeSet: ChangeSet): Unit = {
    val newChangeSet = maintainCount(changeSet, aggregationKeys)
    forward(newChangeSet)
  }

}
