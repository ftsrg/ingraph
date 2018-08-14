package hu.bme.mit.ire.nodes

import akka.actor.{ActorSystem, actorRef2Scala}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire.DataSourceFactory
import hu.bme.mit.ire.messages.ChangeSet
import hu.bme.mit.ire.util.TestUtil._
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class DataSourceFactoryTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {
  def this() = this(ActorSystem())

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "DataSourceFactory" must {

    "send incoming data after subscription" in {
      val input = new DataSourceFactory
      val echoActor = system.actorOf(TestActors.echoActorProps)
      input.subscribe(Map("test" -> (echoActor ! _)))
      val dataSource = input.newDataSource
      dataSource.add("test", tuple(6, 1L))
      dataSource.add("test", tuple(6, 2L))
      dataSource.close
      expectMsg(ChangeSet(positive = tupleBag(tuple(6, 2), tuple(6, 1))))
    }

    "do no splitting in batch" in {
      val input = new DataSourceFactory
      val echoActor = system.actorOf(TestActors.echoActorProps)
      input.subscribe(Map("test" -> (echoActor ! _)))
      val dataSource = input.newDataSource
      for (i <- 1 to 3) {
        dataSource.add("test", tuple(6, i))
      }
      dataSource.close
      expectMsg(ChangeSet(positive = tupleBag(tuple(6, 3), tuple(6, 2), tuple(6, 1))))
    }

  }

}
