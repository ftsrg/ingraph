package hu.bme.mit.ire.nodes

import akka.actor.{ActorRef, ActorSystem, Props, actorRef2Scala}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire.{DataSourceFactory, _}
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.engine.RelationalEngine
import hu.bme.mit.ire.listeners.{ChangeListener, ConsistentChangeListener}
import hu.bme.mit.ire.messages.ChangeSet
import hu.bme.mit.ire.nodes.unary.{ProductionNode, SelectionNode}
import hu.bme.mit.ire.util.TestUtil._
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
      val input = new DataSourceFactory
      val query1 = new TestQuery1
      val query2 = new TestQuery2
      input.subscribe(query1.inputLookup)
      input.subscribe(query2.inputLookup)
      val dataSource1 = input.newDataSource
      dataSource1.add("testval", tuple(5, 5))
      dataSource1.add("testval", tuple(5, 6))
      dataSource1.add("testval2", tuple(5, 7))
      dataSource1.close()
      val res1 = query1.getResults()
      assert(res1.toSet == Set(tuple(5, 5), tuple(5, 6)))
      val res2 = query2.getResults()
      assert(res2.toSet == Set(tuple(5, 5), tuple(5, 6), tuple(5, 7)))
    }
  }

  "integration" should "work" in {
    val input = new DataSourceFactory
    val query = new TestQuery1
    input.subscribe(query.inputLookup)
    val dataSource1 = input.newDataSource
    dataSource1.add("testval", tuple(5, 5))
    dataSource1.add("testval", tuple(5, 6))
    dataSource1.add("testval", tuple(5, 7))
    dataSource1.close()
    val res0 = query.getResults()
    assert(res0.toSet == Set(tuple(5, 5), tuple(5, 6), tuple(5, 7)))
    val dataSource2 = input.newDataSource
    dataSource2.remove("testval", tuple(5, 5))
    dataSource2.remove("testval", tuple(5, 6))
    dataSource2.add("tesval2", tuple(3, 2))
    dataSource2.add("testval", tuple(5, 8))
    dataSource2.close()
    assert(query.getResults().toSet == Set(tuple(5, 7), tuple(5, 8)))
    val beginTime: Long = System.nanoTime()
    (1 to 5000).foreach(i => {
      val dataSourceRemove7 = input.newDataSource
      dataSourceRemove7.remove("testval", tuple(5, 7))
      dataSourceRemove7.close()
      assert(query.getResults().toSet == Set(tuple(5, 8)))
      val dataSourceRemove8 = input.newDataSource
      dataSourceRemove8.remove("testval", tuple(5, 8))
      dataSourceRemove8.close()
      assert(query.getResults().toSet == Set())
      val dataSourceAdd78 = input.newDataSource
      dataSourceAdd78.add("testval", tuple(5, 7))
      dataSourceAdd78.add("testval", tuple(5, 8))
      dataSourceAdd78.close()
    })
    val endTime: Long = System.nanoTime() - beginTime
    val avg = endTime / 500 / 3
  }

  "listeners" should "listen" in {
    (1 to 1000).foreach(i => {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val input = new DataSourceFactory
      val query = new TestQuery1
      input.subscribe(query.inputLookup)
      val dataSource = input.newDataSource
      dataSource.add("testval", tuple(5, 5))
      dataSource.close()
      query.getResults()
      query.addListener(new ConsistentChangeListener {
        override def listener(positive: Vector[Tuple], negative: Vector[Tuple]): Unit = {
          echoActor ! ChangeSet(positive, negative)
        }
      })
      expectMsg(ChangeSet(positive = Vector(tuple(5, 5))))
      val tran1 = input.newDataSource
      tran1.remove("testval", tuple(5, 5))
      tran1.add("testval", tuple(6, 6))
      tran1.close()
      query.getResults()
      expectMsg(ChangeSet(positive = Vector(tuple(6, 6)), negative = Vector(tuple(5, 5))))
    })
  }
}
