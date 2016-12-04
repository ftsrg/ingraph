package hu.bme.mit.ire.messages

import akka.actor.ActorRef
import hu.bme.mit.ire.datatypes.TupleType

import scala.concurrent.Promise

class ReteMessage() {}

case class ChangeSet(positive: Vector[TupleType] = Vector(), negative: Vector[TupleType] = Vector())
  extends ReteMessage()

case class ExpectMoreTerminators(id: Int, inputs: Iterable[ReteMessage => Unit])

case class TerminatorMessage(terminatorID: Int, messageID: Int, route: List[ActorRef] = List.empty) extends ReteMessage()

case class ExpectTerminator(terminatorID: Int, messageID: Int, promise: Promise[Set[TupleType]]) extends ReteMessage()

case class Pause(messageID: Int) extends ReteMessage()

case class Resume(messageID: Int) extends ReteMessage()
