package hu.bme.mit.ire.nodes.unary

import akka.actor.{Actor, Stash}
import hu.bme.mit.ire._
import hu.bme.mit.ire.messages._

abstract class UnaryNode(val expectedTerminatorCount: Int = 1) extends Actor with Forwarder with Stash with TerminatorHandler {
  val log = context.system.log
  val name = self.path.name

  def onChangeSet(changeSet: Δ)

  override def receive: Actor.Receive = {
    case pause: ▌▌ => context.become({
      case resume: ▶ => {
        if (resume.messageID == pause.messageID) {
          context.unbecome()
          unstashAll()
        } else stash()
      }
      case terminator: ✝ => handleTerminator(terminator)
      case _ => stash()
    })
    case changeSet: Δ => onChangeSet(changeSet)
    case terminator: ✝ => handleTerminator(terminator)
    case Primary | Secondary =>
      throw new UnsupportedOperationException(s"$name received Beta-wrapped message")
    case _:SizeRequest => sender() ! onSizeRequest()
    case _ => throw new UnsupportedOperationException(s"$name received unknown message")
  }

  def onSizeRequest(): Long
}
