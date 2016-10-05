package hu.bme.mit.ire

/**
  * Created by wafle on 12/25/2015.
  */
class Checker(override val next: (ReteMessage) => Unit,
              val condition: (nodeType) => Boolean,
              override val expectedTerminatorCount:Int = 1) extends AlphaNode with SingleForwarder  {
  def onChangeSet(changeSet: ChangeSet): Unit = {
    forward(ChangeSet(
      changeSet.positive.filter(condition),
      changeSet.negative.filter(condition)
    ))
  }
}

class Selection(override val next: (ReteMessage) => Unit,
                override val condition: (nodeType) => Boolean,
                override val expectedTerminatorCount:Int = 1
               ) extends Checker(next, condition, expectedTerminatorCount) {}
