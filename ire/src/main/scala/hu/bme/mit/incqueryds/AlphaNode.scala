package hu.bme.mit.incqueryds

import akka.actor.{Stash, Actor}

/**
  * Created by wafle on 12/25/2015.
  */
abstract class AlphaNode(val expectedTerminatorCount: Int = 1) extends Actor with Forwarder with Stash with TerminatorHandler {
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
