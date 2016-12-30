package hu.bme.mit.ire

import java.io.{IOException, ObjectInputStream, ObjectOutputStream}

import akka.actor.ActorRef
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages._
import hu.bme.mit.ire.util.AtomicUniqueCounter

import scala.collection.mutable
import scala.concurrent.{Future, Promise}

class Terminator private(terminatorID: Int, val inputs: Iterable[ReteMessage => Unit], production: ActorRef) extends ReteMessage with Serializable {
  var lastMessageID = -1

  def send(): Future[Set[Tuple]] = {
  val messageID = Terminator.idCounter.getNext
  lastMessageID = messageID
  val promise = Promise[Set[Tuple]]
  production ! ExpectTerminator(terminatorID, messageID, promise)
  val future = promise.future
  inputs.foreach(input => {
    input(Pause(messageID))
    input(TerminatorMessage(terminatorID, messageID))
  })
  future
  }

  def resend(): Future[Set[Tuple]] = {
  val promise = Promise[Set[Tuple]]
  production ! ExpectTerminator(terminatorID, lastMessageID, promise)
  val future = promise.future
  inputs.foreach(input => {
    input(TerminatorMessage(terminatorID, lastMessageID))
  })
  future
  }

  @throws(classOf[IOException])
  private def writeObject(out: ObjectOutputStream): Unit = {}

  @throws(classOf[IOException])
  private def readObject(in: ObjectInputStream): Unit = {}
}

object Terminator {
  val idCounter = new AtomicUniqueCounter

  def apply(inputs: Iterable[ReteMessage => Unit], productionNode: ActorRef): Terminator = {
  val id = idCounter.getNext
  productionNode ! ExpectMoreTerminators(id, inputs)
  new Terminator(id, inputs, productionNode)
  }
}


trait TerminatorHandler {
  val expectedTerminatorCount: Int
  val terminatorCount = new mutable.HashMap[Int, Int]

  def forward(terminator: TerminatorMessage)

  def handleTerminator(terminator: TerminatorMessage): Unit = {
  val count = terminatorCount.getOrElse(terminator.messageID, 0) + 1
  if (count >= expectedTerminatorCount) {
    forward(terminator)
    terminatorCount -= terminator.messageID
  }
  terminatorCount(terminator.messageID) = count
  }
}
