package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}
import hu.bme.mit.ire.util.GenericMath

import scala.collection.immutable.VectorBuilder
import scala.collection.mutable

class MinNode(override val next: (ReteMessage) => Unit,
        override val aggregationKeys: Vector[Int],
        override val extremeKey: Int,
        override val expectedTerminatorCount: Int = 1)
  extends ExtremeNode(next, aggregationKeys, extremeKey, expectedTerminatorCount) {
  override implicit val order = new Ordering[Tuple] {
  override def compare(x: Tuple, y: Tuple): Int = GenericMath.compare(y(extremeKey), x(extremeKey))
  }
}
