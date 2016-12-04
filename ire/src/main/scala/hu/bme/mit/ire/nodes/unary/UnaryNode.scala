package hu.bme.mit.ire.nodes.unary

import akka.actor.{Actor, Stash}
import hu.bme.mit.ire._
import hu.bme.mit.ire.messages._

/**
  * Created by wafle on 12/25/2015.
  */
abstract class UnaryNode(val expectedTerminatorCount: Int = 1) extends Actor with Forwarder with Stash with TerminatorHandler {
  val log = context.system.log
  val name = self.path.name
  def onChangeSet(changeSet: ChangeSet)

  override def receive: Actor.Receive = {
    case pause: Pause => context.become({
      case resume: Resume => {
        if (resume.messageID == pause.messageID) {
          context.unbecome()
          unstashAll()
        } else stash()
      }
      case terminator: TerminatorMessage => handleTerminator(terminator)
      case _ => stash()
    })
    case changeSet: ChangeSet => onChangeSet(changeSet)
    case terminator: TerminatorMessage => handleTerminator(terminator)
    case Primary | Secondary =>
      throw new UnsupportedOperationException(s"$name received Beta-wrapped message")
  }
}
