package hu.bme.mit.incqueryds

/**
  * Created by wafle on 27/12/15.
  */
class ParallelTrimmer(override val children: Vector[(ReteMessage) => Unit],
                      override val selectionVector: Vector[Int],
                      hashFunction: (nodeType) => Int = n => n.hashCode()) extends TrimmerImpl(selectionVector) with ForkingForwarder {
  override def forwardHashFunction(n: nodeType): Int = hashFunction(n)
}
class Trimmer(override val next: (ReteMessage) => Unit,
              override val selectionVector: Vector[Int])
  extends TrimmerImpl(selectionVector) with SingleForwarder {

}
abstract class TrimmerImpl(val selectionVector: Vector[Int]) extends AlphaNode {
  override def onChangeSet(changeSet: ChangeSet) = {
    forward(ChangeSet(
      changeSet.positive.map(vec => selectionVector.map(i => vec(i))),
      changeSet.negative.map(vec => selectionVector.map(i => vec(i)))
    ))
  }
}
