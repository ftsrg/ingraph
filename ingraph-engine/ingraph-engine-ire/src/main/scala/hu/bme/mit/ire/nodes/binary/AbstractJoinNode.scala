package hu.bme.mit.ire.nodes.binary

import hu.bme.mit.ire.datatypes._

abstract class AbstractJoinNode(val primaryMask: Mask,
                                val secondaryMask: Mask) extends BinaryNode {

  def extract(tuple: Tuple, mask: Mask): Tuple = {
    mask.map(i => tuple(i))
  }

}
