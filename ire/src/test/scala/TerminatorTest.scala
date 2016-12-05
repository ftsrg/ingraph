//import akka.actor.{ActorSystem, Props}
//import akka.testkit.{ImplicitSender, TestActors, TestKit}
//import hu.bme.mit.ire._
//import hu.bme.mit.ire.messages.{ChangeSet, Primary, Secondary}
//import hu.bme.mit.ire.nodes.binary.JoinNode
//import hu.bme.mit.ire.nodes.unary.{ProductionNode, SelectionNode}
//import hu.bme.mit.ire.util.TestUtil._
//import hu.bme.mit.ire.util.utils.conversions._
//import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}
//
//import scala.concurrent.Await
//import scala.concurrent.duration.{Duration, _}
//
//class TerminatorTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
//  with WordSpecLike with Matchers with BeforeAndAfterAll {
//  def this() = this(ActorSystem("MySpec"))
//
//  override def afterAll {
//    TestKit.shutdownActorSystem(system)
//  }
//
//  "Unary nodes" must {
//    "propagate terminator messages" in {
//      val echoActor = system.actorOf(TestActors.echoActorProps)
//      val production = system.actorOf(Props(new ProductionNode("alpha test", 2)))
//      val intermediary = system.actorOf(Props(new SelectionNode(production ! _, c => true, expectedTerminatorCount = 2))) // TODO wtf
//      //      val intermediary = system.actorOf(Props(new SelectionNode(production ! _, c => true)))
//      val input1 = system.actorOf(Props(new SelectionNode(production ! _, c => true)))
//      input1 ! ChangeSet(positive = Vector(tuple(15)))
//      input1 ! ChangeSet(positive = Vector(tuple(19)))
//      val input2 = system.actorOf(Props(new SelectionNode(intermediary ! _, c => true)))
//      input2 ! ChangeSet(positive = Vector(tuple(25)))
//      input2 ! ChangeSet(positive = Vector(tuple(29)))
//      val input3 = system.actorOf(Props(new SelectionNode(intermediary ! _, c => true)))
//
//      val terminator = Terminator(List(
//        input1 ! _, input2 ! _, input3 ! _
//      ), production)
//      val future = terminator.send()
//      input1 ! ChangeSet(positive = Vector(tuple(16)))
//      input1 ! ChangeSet(positive = Vector(tuple(17)))
//      input2 ! ChangeSet(positive = Vector(tuple(26)))
//      input2 ! ChangeSet(positive = Vector(tuple(27)))
//      val expected = Set(tuple(15), tuple(19), tuple(25), tuple(29))
//      assert(Await.result(future, Duration(1, HOURS)) == expected)
//    }
//  }
//  "Binary nodes" must {
//    "propagate terminator messages" in {
//      val echoActor = system.actorOf(TestActors.echoActorProps)
//      val production = system.actorOf(Props(new ProductionNode("")), "Production")
//      val checker = system.actorOf(Props(new SelectionNode(production ! _, c => true)), "checker")
//      val intermediary = system.actorOf(Props(new JoinNode(checker ! _, Vector(0), Vector(0))), "intermediary")
//      val input1 = system.actorOf(Props(new JoinNode(intermediary ! Primary(_), Vector(0), Vector(0))), "inputBeta")
//      val msg15 = ChangeSet(positive = Vector(tuple(15)))
//      input1 ! Primary(msg15)
//      input1 ! Secondary(msg15)
//      intermediary ! Secondary(msg15)
//      val msg25 = ChangeSet(positive = Vector(tuple(25)))
//      input1 ! Primary(msg25)
//      input1 ! Secondary(msg25)
//      intermediary ! Secondary(msg25)
//
//      val terminator = Terminator(List(input1.primary, input1.secondary, intermediary.secondary), production)
//      val future = terminator.send()
//      input1 ! Primary(ChangeSet(positive = Vector(tuple(16))))
//      input1 ! Secondary(ChangeSet(positive = Vector(tuple(16))))
//      intermediary ! Secondary(ChangeSet(positive = Vector(tuple(16))))
//
//      assert(Await.result(future, Duration(1, HOURS)) == Set(tuple(15), tuple(25)))
//      assert(Await.result(terminator.send(), Duration(1, HOURS)) == Set(tuple(15), tuple(25), tuple(16)))
//      (1 to 500).foreach(i => {
//        input1 ! Secondary(ChangeSet(negative = Vector(tuple(16))))
//        assert(Await.result(terminator.send(), Duration(1, HOURS)) == Set(tuple(15), tuple(25)))
//        input1 ! Secondary(ChangeSet(positive = Vector(tuple(16))))
//        intermediary ! Secondary(ChangeSet(negative = Vector(tuple(15))))
//        assert(Await.result(terminator.send(), Duration(1, HOURS)) == Set(tuple(25), tuple(16)))
//        intermediary ! Secondary(ChangeSet(positive = Vector(tuple(15))))
//        assert(Await.result(terminator.send(), Duration(1, HOURS)) == Set(tuple(15), tuple(25), tuple(16)))
//      })
//    }
//  }
//  "Node splitting" should {
//    "work" in {
//
//    }
//  }
//}
