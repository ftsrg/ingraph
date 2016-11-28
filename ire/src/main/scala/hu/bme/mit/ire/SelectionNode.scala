package hu.bme.mit.ire

/**
  * Created by wafle on 12/25/2015.
  */
class SelectionNode(override val next: (ReteMessage) => Unit,
                    val condition: (TupleType) => Boolean,
                    override val expectedTerminatorCount:Int = 1) extends UnaryNode with SingleForwarder  {
  def onChangeSet(changeSet: ChangeSet): Unit = {
    forward(ChangeSet(
      changeSet.positive.filter(condition),
      changeSet.negative.filter(condition)
    ))
  }
}
