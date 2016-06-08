package hu.bme.mit.incqueryds

/**
  * Created by wafle on 12/25/2015.
  */
class Projection(override val next: (ReteMessage) => Unit,
                 val projectionVector: Vector[Int],
                 override val expectedTerminatorCount:Int = 1) extends AlphaNode with SingleForwarder  {
  def onChangeSet(changeSet: ChangeSet): Unit = {
    forward(ChangeSet(
      changeSet.positive.map(f => projectionVector.map(i => f(i))),
      changeSet.negative.map(f => projectionVector.map(i => f(i)))
    ))
  }
}
