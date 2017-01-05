package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.datatypes.Mask
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.ReteMessage
import hu.bme.mit.ire.util.GenericMath

class MinNode(override val next: (ReteMessage) => Unit,
              override val aggregationKeys: Mask,
              override val distinct: Boolean,
              override val extremeKey: Int)
  extends ExtremeNode {
  override implicit val order = new Ordering[Tuple] {
    override def compare(x: Tuple, y: Tuple): Int = GenericMath.compare(y(extremeKey), x(extremeKey))
  }
}
