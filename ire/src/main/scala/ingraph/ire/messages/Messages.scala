package ingraph.ire.messages

import akka.actor.ActorRef
import ingraph.ire.datatypes.{Tuple, TupleBag}

import scala.concurrent.Promise

class ReteMessage() {}

case class ChangeSet(positive: TupleBag = Vector(), negative: TupleBag = Vector()) extends ReteMessage

case class ExpectMoreTerminators(id: Int, inputs: Iterable[ReteMessage => Unit])

case class TerminatorMessage(terminatorID: Int, messageID: Int, route: List[ActorRef] = List.empty) extends ReteMessage

case class ExpectTerminator(terminatorID: Int, messageID: Int, promise: Promise[Iterable[Tuple]]) extends ReteMessage

case class Pause(messageID: Int) extends ReteMessage

case class Resume(messageID: Int) extends ReteMessage

case class SizeRequest()

case class Primary(value: ReteMessage) extends ReteMessage
case class Secondary(value: ReteMessage) extends ReteMessage
case class Ternary(value: ReteMessage) extends ReteMessage
