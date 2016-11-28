package hu.bme.mit.ire

import akka.actor.{Actor, Stash}

/**
  * Created by wafle on 12/25/2015.
  */

case class Primary(value: ReteMessage) extends ReteMessage

case class Secondary(value: ReteMessage) extends ReteMessage

abstract class BinaryNode(val expectedTerminatorCount: Int = 2) extends Actor with Forwarder with Stash with TerminatorHandler {
  val name = self.path.name
  def onPrimary(changeSet: ChangeSet)

  def onSecondary(changeSet: ChangeSet)

  var primaryPause: Option[Pause] = None
  var secondaryPause: Option[Pause] = None
  override def receive: Actor.Receive = {
    case Primary(reteMessage: ReteMessage) => {
      primaryPause match {
        case Some(pause) => {
          reteMessage match {
            case resume: Resume =>{
              if (resume.messageID == pause.messageID)
                primaryPause = None
              if (secondaryPause.isEmpty)
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
      secondaryPause match  {
        case Some(pause) => {
          reteMessage match {
            case resume: Resume =>{
              if (resume.messageID == pause.messageID)
                secondaryPause = None
              if (primaryPause.isEmpty)
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
    case other: ReteMessage =>
      throw new UnsupportedOperationException(
        s"$name received raw message, needs to be wrapped as Primary or Secondary")
  }
}
