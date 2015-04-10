/**
 * Created by Maginecz on 3/21/2015.
 */
import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class HashJoinerTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "A HasJoin" must {
    "join the values" in {
      val prim = ChangeSet(
        positive = Vector(Vector(15, 16, 17, 18), Vector(4, 5, 6, 7))
      )
      val sec = ChangeSet(
        positive = Vector(Vector(13,15,16))
      )
      val primarySel = Vector(0, 1)
      val secondarySel = Vector(1,2)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new HashJoiner(echoActor ! _,4, primarySel, 3, secondarySel)), name = "testSelector")

      joiner ! Primary(prim)
      expectMsg(ChangeSet())
      joiner ! Secondary(sec)
      expectMsg(ChangeSet(
        positive = Vector(Vector(15, 16, 17, 18, 13))
      ))


    }
  }
}
