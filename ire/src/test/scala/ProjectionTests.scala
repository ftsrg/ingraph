import akka.actor.{ActorSystem, Props}
import hu.bme.mit.incqueryds.{Checker, Equality, Inequality, _}

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}
import hu.bme.mit.incqueryds.Inequality
import hu.bme.mit.incqueryds.Equality
import hu.bme.mit.incqueryds.ChangeSet
import hu.bme.mit.incqueryds.Checker
import hu.bme.mit.incqueryds.nodeType

/**
 * Created by Maginecz on 4/10/2015.
 */
class ProjectionTests(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
  TestKit.shutdownActorSystem(system)
  }
  val changeSet = ChangeSet(positive = Vector(Vector(0, "something")), negative = Vector(Vector(0, "something else")))
  "Projection" must {
  "do projection with equal length" in {
    val echoActor = system.actorOf(TestActors.echoActorProps)
    val checker = system.actorOf(Props(new Projection(echoActor ! _, Vector(1, 0))))

    checker ! changeSet
    expectMsg(ChangeSet(positive = Vector(Vector("something", 0)), negative = Vector(Vector("something else", 0))))
  }
  "do projection with lesser length" in {
    val echoActor = system.actorOf(TestActors.echoActorProps)
    val checker = system.actorOf(Props(new Projection(echoActor ! _, Vector(1))))

    checker ! changeSet
    expectMsg(ChangeSet(positive = Vector(Vector("something")), negative = Vector(Vector("something else"))))
  }
  }
}
