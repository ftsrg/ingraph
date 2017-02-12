package hu.bme.mit.ire.nodes.ternary

import akka.actor.Actor
import hu.bme.mit.ire.Forwarder
import akka.actor.Stash
import hu.bme.mit.ire.TerminatorHandler
import hu.bme.mit.ire.messages.Pause
import hu.bme.mit.ire.messages.ChangeSet
import hu.bme.mit.ire.messages.ReteMessage
import hu.bme.mit.ire.messages.Primary
import hu.bme.mit.ire.messages.Resume
import hu.bme.mit.ire.messages.TerminatorMessage
import hu.bme.mit.ire.messages.Secondary
import hu.bme.mit.ire.messages.SizeRequest
import hu.bme.mit.ire.messages.Ternary

abstract class TernaryNode(val expectedTerminatorCount: Int = 3) extends Actor with Forwarder with Stash with TerminatorHandler {
  val name = self.path.name
  var primaryPause: Option[Pause] = None
  var secondaryPause: Option[Pause] = None
  var ternaryPause: Option[Pause] = None

  def onPrimary(changeSet: ChangeSet)

  def onSecondary(changeSet: ChangeSet)

  def onTernary(changeSet: ChangeSet)
  
  override def receive: Actor.Receive = {
    case Primary(reteMessage: ReteMessage) => {
      primaryPause match {
        case Some(pause) => {
          reteMessage match {
            case resume: Resume => {
              if (resume.messageID == pause.messageID)
                primaryPause = None
              if (secondaryPause.isEmpty && ternaryPause.isEmpty)
                unstashAll()
            }
            case terminator: TerminatorMessage => {
              if (terminator.messageID == pause.messageID)
                handleTerminator(terminator)
              else
                stash()
            }
            case other => stash()
          }
        }
        case None => reteMessage match {
          case pause: Pause => primaryPause = Some(pause)
          case cs: ChangeSet => onPrimary(cs); // printForwarding(cs)
          case t: TerminatorMessage => handleTerminator(t)
        }
      }
    }
    
    case Secondary(reteMessage: ReteMessage) => {
      secondaryPause match {
        case Some(pause) => {
          reteMessage match {
            case resume: Resume => {
              if (resume.messageID == pause.messageID)
                secondaryPause = None
              if (primaryPause.isEmpty && ternaryPause.isEmpty)
                unstashAll()
            }
            case terminator: TerminatorMessage => {
              if (terminator.messageID == pause.messageID)
                handleTerminator(terminator)
              else
                stash()
            }
            case other => stash()
          }
        }
        case None => reteMessage match {
          case pause: Pause => secondaryPause = Some(pause)
          case cs: ChangeSet => onSecondary(cs); //printForwarding(cs)
          case t: TerminatorMessage => handleTerminator(t)
        }

      }
    }
    
    case Ternary(reteMessage: ReteMessage) => {
      ternaryPause match {
        case Some(pause) => {
          reteMessage match {
            case resume: Resume => {
              if (resume.messageID == pause.messageID)
                ternaryPause = None
              if (primaryPause.isEmpty && secondaryPause.isEmpty)
                unstashAll()
            }
            case terminator: TerminatorMessage => {
              if (terminator.messageID == pause.messageID)
                handleTerminator(terminator)
              else
                stash()
            }
            case other => stash()
          }
        }
        case None => reteMessage match {
          case pause: Pause => ternaryPause = Some(pause)
          case cs: ChangeSet => onSecondary(cs); //printForwarding(cs)
          case t: TerminatorMessage => handleTerminator(t)
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