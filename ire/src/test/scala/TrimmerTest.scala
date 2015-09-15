import scala.Vector

import org.scalatest.BeforeAndAfterAll
import org.scalatest.Matchers
import org.scalatest.WordSpecLike

import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.actorRef2Scala
import akka.testkit.ImplicitSender
import akka.testkit.TestActors
import akka.testkit.TestKit
import hu.bme.mit.incquerydcore.ChangeSet
import hu.bme.mit.incquerydcore.Trimmer

/**
 * Created by Maginecz on 3/16/2015.
 */

class TrimmerTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
  TestKit.shutdownActorSystem(system)
  }

  "A Trimmer" must {
  "select the values" in {
    val changes = ChangeSet(
    positive = Vector(Vector(15, 16, 17, 18), Vector(4, 5, 6, 7)),
    negative = Vector(Vector(-0, -1, -2, -3), Vector(-10, -11, -12, -13))
    )
    val selectionVector = Vector(0, 2)
    val expectedChanges = ChangeSet(
    positive = Vector(Vector(15, 17), Vector(4, 6)),
    negative = Vector(Vector(-0, -2), Vector(-10, -12))
    )
    val echoActor = system.actorOf(TestActors.echoActorProps)
    val selector = system.actorOf(Props(new Trimmer(echoActor ! _, selectionVector)), name = "testSelector")

    selector ! changes
    expectMsg(expectedChanges)
    selector ! changes
    expectMsg(expectedChanges)
  }
  }
}
