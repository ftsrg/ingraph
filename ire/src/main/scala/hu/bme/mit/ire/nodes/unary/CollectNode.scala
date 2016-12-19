package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}
import hu.bme.mit.ire.util.GenericMath

import scala.collection.immutable.VectorBuilder
import scala.collection.mutable

class CollectNode(override val next: (ReteMessage) => Unit,
                  val aggregationKeys: Vector[Int],
                  val collectKey: Int) extends UnaryNode with SingleForwarder {

  override def onChangeSet(changeSet: ChangeSet): Unit = {



  }
}
