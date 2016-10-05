package hu.bme.mit.ire

/**
  * Created by wafle on 27/12/15.
  */
class ParallelTrimmer(override val children: Vector[(ReteMessage) => Unit],
                      override val selectionVector: Vector[Any],
                      hashFunction: (TupleType) => Int = n => n.hashCode()) extends TrimmerImpl(selectionVector) with ForkingForwarder {
  override def forwardHashFunction(n: TupleType): Int = hashFunction(n)
}
class Trimmer(override val next: (ReteMessage) => Unit,
              override val selectionVector: Vector[Any])
  extends TrimmerImpl(selectionVector) with SingleForwarder {}

class Projection(override val next: (ReteMessage) => Unit,
                 override val selectionVector: Vector[Int]
                ) extends Trimmer(next, selectionVector) {}

abstract class TrimmerImpl(val selectionVector: Vector[Any]) extends AlphaNode {
  override def onChangeSet(changeSet: ChangeSet) = {
    forward(ChangeSet(
      changeSet.positive.map(m => m.filterKeys(selectionVector.contains)),
      changeSet.negative.map(m => m.filterKeys(selectionVector.contains))
    ))
  }
}
