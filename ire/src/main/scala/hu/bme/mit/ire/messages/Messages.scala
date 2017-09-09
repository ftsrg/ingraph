package hu.bme.mit.ire.messages

import akka.actor.ActorRef
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.datatypes.TupleBag

import scala.concurrent.Promise
import scala.collection.mutable

class ReteMessage() {}

trait DataMessage extends ReteMessage {
  def changeSets: Seq[TupleBag]
  def createNew(bags: Seq[TupleBag]): DataMessage
  def nonEmpty(): Boolean = {
    changeSets.filter(_.nonEmpty).nonEmpty
  }
}

case class BatchChangeSet(changeSet: TupleBag) extends DataMessage {
  override def changeSets: Seq[TupleBag] = Seq(changeSet)
  override def createNew(bags: Seq[TupleBag]): BatchChangeSet = BatchChangeSet(bags(0))
}

case class ChangeSet(positive: TupleBag = Vector(), negative: TupleBag = Vector()) extends DataMessage {
  override def changeSets: Seq[TupleBag] = Seq(positive, negative)
  override def createNew(bags: Seq[TupleBag]): ChangeSet = ChangeSet(bags(0), bags(1))
}

case class ExpectMoreTerminators(id: Int, inputs: Iterable[ReteMessage => Unit])

case class TerminatorMessage(terminatorID: Int, messageID: Int, route: List[ActorRef] = List.empty) extends ReteMessage

case class ExpectTerminator(terminatorID: Int, messageID: Int, promise: Promise[Iterable[Tuple]]) extends ReteMessage

case class Pause(messageID: Int) extends ReteMessage

case class Resume(messageID: Int) extends ReteMessage

case class SizeRequest()

case class Primary(value: ReteMessage) extends ReteMessage
case class Secondary(value: ReteMessage) extends ReteMessage
case class Ternary(value: ReteMessage) extends ReteMessage
