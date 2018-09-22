package ingraph.ire.nodes.binary

import ingraph.ire.datatypes._

abstract class AbstractJoinNode(val primaryMask: Mask,
                                val secondaryMask: Mask) extends BinaryNode {

  def extract(tuple: Tuple, mask: Mask): Tuple = {
    mask.map(i => tuple(i)).toVector
  }

}
