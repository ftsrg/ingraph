package hu.bme.mit.ire

/**
  * Created by wafle on 27/12/15.
  */
abstract class ProjectionImpl(val selectionVector: Vector[Any]) extends UnaryNode {
  override def onChangeSet(changeSet: ChangeSet) = {
    forward(ChangeSet(
      changeSet.positive.map(m => m.filterKeys(selectionVector.contains)),
      changeSet.negative.map(m => m.filterKeys(selectionVector.contains))
    ))
  }
}

class ProjectionNode(override val next: (ReteMessage) => Unit,
                     override val selectionVector: Vector[Any])
  extends ProjectionImpl(selectionVector) with SingleForwarder {}

