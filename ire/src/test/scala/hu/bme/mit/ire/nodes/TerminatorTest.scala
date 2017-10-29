package hu.bme.mit.ire.nodes

import akka.actor.{ActorSystem, Props, actorRef2Scala}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire._
import hu.bme.mit.ire.messages.{IncrementalChangeSet, Primary, Secondary}
import hu.bme.mit.ire.nodes.binary.JoinNode
import hu.bme.mit.ire.nodes.unary.{ProductionNode, SelectionNode}
import hu.bme.mit.ire.util.TestUtil._
import hu.bme.mit.ire.util.Utils.conversions._
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

import scala.concurrent.Await
import scala.concurrent.duration.{Duration, _}

class TerminatorTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {
  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "Unary nodes" must {
    "propagate terminator messages" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val production = system.actorOf(Props(new ProductionNode("alpha test", 2)))
      val intermediary = system.actorOf(Props(new SelectionNode(production ! _, c => true, expectedTerminatorCount = 2))) // TODO wtf
      //      val intermediary = system.actorOf(Props(new SelectionNode(production ! _, c => true)))
      val input1 = system.actorOf(Props(new SelectionNode(production ! _, c => true)))
      input1 ! IncrementalChangeSet(positive = tupleBag(tuple(15)))
      input1 ! IncrementalChangeSet(positive = tupleBag(tuple(19)))
      val input2 = system.actorOf(Props(new SelectionNode(intermediary ! _, c => true)))
      input2 ! IncrementalChangeSet(positive = tupleBag(tuple(25)))
      input2 ! IncrementalChangeSet(positive = tupleBag(tuple(29)))
      val input3 = system.actorOf(Props(new SelectionNode(intermediary ! _, c => true)))

      val terminator = Terminator(List(
        input1 ! _, input2 ! _, input3 ! _
      ), production)
      val future = terminator.send()
      input1 ! IncrementalChangeSet(positive = tupleBag(tuple(16)))
      input1 ! IncrementalChangeSet(positive = tupleBag(tuple(17)))
      input2 ! IncrementalChangeSet(positive = tupleBag(tuple(26)))
      input2 ! IncrementalChangeSet(positive = tupleBag(tuple(27)))
      val expected = Set(tuple(15), tuple(19), tuple(25), tuple(29))
      assert(Await.result(future, Duration(1, HOURS)).toSet == expected)
    }
  }
  "Binary nodes" must {
    "propagate terminator messages" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val production = system.actorOf(Props(new ProductionNode("")), "Production")
      val checker = system.actorOf(Props(new SelectionNode(production ! _, c => true)), "checker")
      val intermediary = system.actorOf(Props(new JoinNode(checker ! _, 1, 1, mask(0), mask(0))), "intermediary")
      val input1 = system.actorOf(Props(new JoinNode(intermediary ! Primary(_), 1, 1, mask(0), mask(0))), "inputBeta")
      val msg15 = IncrementalChangeSet(positive = tupleBag(tuple(15)))
      input1 ! Primary(msg15)
      input1 ! Secondary(msg15)
      intermediary ! Secondary(msg15)
      val msg25 = IncrementalChangeSet(positive = tupleBag(tuple(25)))
      input1 ! Primary(msg25)
      input1 ! Secondary(msg25)
      intermediary ! Secondary(msg25)

      val terminator = Terminator(List(input1.primary, input1.secondary, intermediary.secondary), production)
      val future = terminator.send()
      input1 ! Primary(IncrementalChangeSet(positive = tupleBag(tuple(16))))
      input1 ! Secondary(IncrementalChangeSet(positive = tupleBag(tuple(16))))
      intermediary ! Secondary(IncrementalChangeSet(positive = tupleBag(tuple(16))))

      assert(Await.result(future, Duration(1, HOURS)).toSet == Set(tuple(15), tuple(25)))
      assert(Await.result(terminator.send(), Duration(1, HOURS)).toSet == Set(tuple(15), tuple(25), tuple(16)))
      (1 to 500).foreach(i => {
        input1 ! Secondary(IncrementalChangeSet(negative = tupleBag(tuple(16))))
        assert(Await.result(terminator.send(), Duration(1, HOURS)).toSet == Set(tuple(15), tuple(25)))
        input1 ! Secondary(IncrementalChangeSet(positive = tupleBag(tuple(16))))
        intermediary ! Secondary(IncrementalChangeSet(negative = tupleBag(tuple(15))))
        assert(Await.result(terminator.send(), Duration(1, HOURS)).toSet == Set(tuple(25), tuple(16)))
        intermediary ! Secondary(IncrementalChangeSet(positive = tupleBag(tuple(15))))
        assert(Await.result(terminator.send(), Duration(1, HOURS)).toSet == Set(tuple(15), tuple(25), tuple(16)))
      })
    }
  }
  "Node splitting" should {
    "work" in {

    }
  }
}
