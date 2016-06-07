package hu.bme.mit.incqueryds

import java.io.{IOException, ObjectInputStream, ObjectOutputStream}

import akka.actor.ActorRef

import scala.collection.mutable
import scala.concurrent.{Future, Promise}

/**
  * Created by wafle on 27/12/15.
  */
class Terminator private(terminatorID: Int, val inputs: Iterable[ReteMessage => Unit], production: ActorRef) extends ReteMessage with Serializable {
  var lastMessageID = -1

  def send(): Future[Set[nodeType]] = {
    val messageID = Terminator.idCounter.getNext()
    lastMessageID = messageID
    val promise = Promise[Set[nodeType]]
    production ! ExpectTerminator(terminatorID, messageID, promise)
    val future = promise.future
    inputs.foreach(input => {
      input(Pause(messageID))
      input(TerminatorMessage(terminatorID, messageID))
    })
    future
  }

  def resend(): Future[Set[nodeType]] = {
    val promise = Promise[Set[nodeType]]
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
    val id = idCounter.getNext()
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
    if ( count >= expectedTerminatorCount) {
      forward(terminator)
      terminatorCount -= terminator.messageID
    }
    terminatorCount(terminator.messageID) = count
  }
}

