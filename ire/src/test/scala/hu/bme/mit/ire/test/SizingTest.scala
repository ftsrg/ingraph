package hu.bme.mit.ire.test

import akka.actor.{ActorRef, Props, actorRef2Scala}
import hu.bme.mit.ire.TransactionFactory;
import hu.bme.mit.ire._
import hu.bme.mit.ire.datatypes.{Indexer, Tuple}
import hu.bme.mit.ire.messages.{ChangeSet, Primary, Secondary}
import hu.bme.mit.ire.nodes.binary.JoinNode
import hu.bme.mit.ire.nodes.unary.{ProductionNode, SelectionNode}
import hu.bme.mit.ire.trainbenchmark._
import hu.bme.mit.ire.util.{BufferMultimap, SizeCounter}
import hu.bme.mit.ire.util.TestUtil._
import org.scalatest.WordSpec
import org.scalatest.concurrent.TimeLimits
import scala.collection.mutable
import scala.Vector

class SizingTest extends WordSpec with TimeLimits {

  import util.Utils.conversions._
  class TestQuery1 extends TrainbenchmarkQuery {
    override val production: ActorRef = system.actorOf(Props(new ProductionNode("TestQuery")))
    override val inputLookup: Map[String, (ChangeSet) => Unit] = Map(
      "testval" -> ((cs: ChangeSet) => { joiner ! Primary(cs); joiner ! Secondary(cs) })
    )
    override val terminator: Terminator = Terminator(Vector(forwarder ! _), production)
    val forwarder = newLocal(Props(new SelectionNode(production, a => true)))
    val joiner = newLocal(Props(new JoinNode(forwarder, 2, 2, mask(0), mask(0))), "joiner")
  }

  "SizeCounter" should {
    "count" in {
      val data = mutable.HashMap[Tuple, Int]()
      data(tuple(5, 6, 7)) = 8
      data(tuple(5, 6, 9)) = 10
      assert(SizeCounter.count(data.keys) == 6)
    }

    "count deeper" in {
      val data: Indexer = new BufferMultimap[Tuple, Tuple]
      data.addBinding(tuple(2, 3), tuple(3, 4))
      data.addBinding(tuple(2, 3), tuple(3, 5))
      data.addBinding(tuple(2, 3), tuple(3, 6))
      data.addBinding(tuple(3, 2), tuple(2, 5))
      assert(SizeCounter.countDeeper(data.values) == 8)
    }

    "measure size" in {
      val input = new TransactionFactory(10)
      val query = new TestQuery1
      input.subscribe(query.inputLookup)
      val tran0 = input.newBatchTransaction()
      tran0.add("testval", tuple(5, 5))
      tran0.add("testval", tuple(5, 6))
      tran0.add("testval", tuple(5, 7))
      tran0.close()
      assert(query.getCounts == 12)
    }
  }
}
