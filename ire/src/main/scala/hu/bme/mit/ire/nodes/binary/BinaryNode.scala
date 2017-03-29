package hu.bme.mit.ire.nodes.binary

import akka.actor.{Actor, Stash}
import hu.bme.mit.ire._
import hu.bme.mit.ire.messages._

abstract class BinaryNode(val expectedTerminatorCount: Int = 2) extends Actor with Forwarder with Stash with TerminatorHandler {
  val name = self.path.name
  var primaryPause: Option[▌▌] = None
  var secondaryPause: Option[▌▌] = None

  def onPrimary(changeSet: Δ)

  def onSecondary(changeSet: Δ)

  override def receive: Actor.Receive = {
    case Primary(reteMessage: ReteMessage) => {
      primaryPause match {
        case Some(pause) => {
          reteMessage match {
            case resume: ▶ => {
              if (resume.messageID == pause.messageID)
                primaryPause = None
              if (secondaryPause.isEmpty)
                unstashAll()
            }
            case terminator: ✝ => {
              if (terminator.messageID == pause.messageID)
                handleTerminator(terminator)
              else
                stash()
            }
            case other => stash()
          }
        }
        case None => reteMessage match {
          case pause: ▌▌ => primaryPause = Some(pause)
          case cs: Δ => onPrimary(cs); // printForwarding(cs)
          case t: ✝ => handleTerminator(t)
        }
      }
    }
    case Secondary(reteMessage: ReteMessage) => {
      secondaryPause match {
        case Some(pause) => {
          reteMessage match {
            case resume: ▶ => {
              if (resume.messageID == pause.messageID)
                secondaryPause = None
              if (primaryPause.isEmpty)
                unstashAll()
            }
            case terminator: ✝ => {
              if (terminator.messageID == pause.messageID)
                handleTerminator(terminator)
              else
                stash()
            }
            case other => stash()
          }
        }
        case None => reteMessage match {
          case pause: ▌▌ => secondaryPause = Some(pause)
          case cs: Δ => onSecondary(cs); //printForwarding(cs)
          case t: ✝ => handleTerminator(t)
        }

      }
    }
    case _: SizeRequest => sender() ! onSizeRequest()
    case other: ReteMessage =>
      throw new UnsupportedOperationException(
        s"$name received raw message, needs to be wrapped as Primary or Secondary")
  }

  def onSizeRequest(): Long
}
