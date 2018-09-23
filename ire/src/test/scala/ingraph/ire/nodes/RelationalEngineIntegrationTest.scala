package ingraph.ire.nodes

import akka.actor.{ActorRef, ActorSystem, Props, actorRef2Scala}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import ingraph.ire.datatypes.Tuple
import ingraph.ire.engine.RelationalEngine
import ingraph.ire.inputs.InputTransactionFactory
import ingraph.ire.listeners.ConsistentChangeListener
import ingraph.ire.messages
import ingraph.ire.messages.{ChangeSet, Terminator}
import ingraph.ire.nodes.unary.{ProductionNode, SelectionNode}
import ingraph.ire.util.TestUtil._
import ingraph.ire.messages.Terminator
import ingraph.ire.nodes.unary.SelectionNode
import org.scalatest._

class RelationalEngineIntegrationTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with FlatSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  class TestQuery1 extends RelationalEngine {
    override val production: ActorRef = system.actorOf(Props(new ProductionNode("TestQuery1")))
    override val inputLookup: Map[String, (ChangeSet) => Unit] = Map(
      "testval" -> ((cs: ChangeSet) => forwarder ! cs)
    )
    override val terminator: Terminator = Terminator(Vector(forwarder ! _), production)
    val forwarder = system.actorOf(Props(new SelectionNode(production ! _, a => true)))
  }

  class TestQuery2 extends RelationalEngine {
    override val production: ActorRef = system.actorOf(Props(new ProductionNode("TestQuery2", 2)))
    override val inputLookup: Map[String, (ChangeSet) => Unit] = Map(
      "testval" -> ((cs: ChangeSet) => forwarder ! cs),
      "testval2" -> ((cs: ChangeSet) => forwarder2 ! cs)
    )
    override val terminator: Terminator = Terminator(Vector(forwarder ! _, forwarder2 ! _), production)
    val forwarder = system.actorOf(Props(new SelectionNode(production ! _, a => true)))
    val forwarder2 = system.actorOf(Props(new SelectionNode(production ! _, a => true)))
  }

  "multiple queries" should "work" in {
    for (i <- 1 to 1000) {
      val input = new InputTransactionFactory
      val query1 = new TestQuery1
      val query2 = new TestQuery2
      input.subscribe(query1.inputLookup)
      input.subscribe(query2.inputLookup)
      val inputTransaction1 = input.newInputTransaction
      inputTransaction1.add("testval", tuple(5, 5))
      inputTransaction1.add("testval", tuple(5, 6))
      inputTransaction1.add("testval2", tuple(5, 7))
      inputTransaction1.sendAll()
      val res1 = query1.getResults()
      assert(res1.toSet == Set(tuple(5, 5), tuple(5, 6)))
      val res2 = query2.getResults()
      assert(res2.toSet == Set(tuple(5, 5), tuple(5, 6), tuple(5, 7)))
    }
  }

  "integration" should "work" in {
    val input = new InputTransactionFactory
    val query = new TestQuery1
    input.subscribe(query.inputLookup)
    val inputTransaction1 = input.newInputTransaction
    inputTransaction1.add("testval", tuple(5, 5))
    inputTransaction1.add("testval", tuple(5, 6))
    inputTransaction1.add("testval", tuple(5, 7))
    inputTransaction1.sendAll()
    val res0 = query.getResults()
    assert(res0.toSet == Set(tuple(5, 5), tuple(5, 6), tuple(5, 7)))
    val inputTransaction2 = input.newInputTransaction
    inputTransaction2.remove("testval", tuple(5, 5))
    inputTransaction2.remove("testval", tuple(5, 6))
    inputTransaction2.add("tesval2", tuple(3, 2))
    inputTransaction2.add("testval", tuple(5, 8))
    inputTransaction2.sendAll()
    assert(query.getResults().toSet == Set(tuple(5, 7), tuple(5, 8)))
    val beginTime: Long = System.nanoTime()
    (1 to 5000).foreach(i => {
      val inputTransactionRemove7 = input.newInputTransaction
      inputTransactionRemove7.remove("testval", tuple(5, 7))
      inputTransactionRemove7.sendAll()
      assert(query.getResults().toSet == Set(tuple(5, 8)))
      val inputTransactionRemove8 = input.newInputTransaction
      inputTransactionRemove8.remove("testval", tuple(5, 8))
      inputTransactionRemove8.sendAll()
      assert(query.getResults().toSet == Set())
      val inputTransactionAdd78 = input.newInputTransaction
      inputTransactionAdd78.add("testval", tuple(5, 7))
      inputTransactionAdd78.add("testval", tuple(5, 8))
      inputTransactionAdd78.sendAll()
    })
    val endTime: Long = System.nanoTime() - beginTime
    val avg = endTime / 500 / 3
  }

  "listeners" should "listen" in {
    (1 to 1000).foreach(i => {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val input = new InputTransactionFactory
      val query = new TestQuery1
      input.subscribe(query.inputLookup)
      val inputTransaction1 = input.newInputTransaction
      inputTransaction1.add("testval", tuple(5, 5))
      inputTransaction1.sendAll()
      query.getResults()
      query.addListener(new ConsistentChangeListener {
        override def listener(positive: Vector[Tuple], negative: Vector[Tuple]): Unit = {
          echoActor ! ChangeSet(positive, negative)
        }
      })
      expectMsg(ChangeSet(positive = Vector(tuple(5, 5))))
      val inputTransaction2 = input.newInputTransaction
      inputTransaction2.remove("testval", tuple(5, 5))
      inputTransaction2.add("testval", tuple(6, 6))
      inputTransaction2.sendAll()
      query.getResults()
      expectMsg(ChangeSet(positive = Vector(tuple(6, 6)), negative = Vector(tuple(5, 5))))
    })
  }
}
