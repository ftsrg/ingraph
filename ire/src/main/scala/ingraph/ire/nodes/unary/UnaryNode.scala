package ingraph.ire.nodes.unary

import akka.actor.{Actor, Stash}
import ingraph.ire.messages._
import ingraph.ire.messages.{Forwarder, TerminatorHandler}

abstract class UnaryNode(val expectedTerminatorCount: Int = 1) extends Actor with Forwarder with Stash with TerminatorHandler {
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
    case _:SizeRequest => sender() ! onSizeRequest()
    case _ => throw new UnsupportedOperationException(s"$name received unknown message")
  }

  def onSizeRequest(): Long
}
