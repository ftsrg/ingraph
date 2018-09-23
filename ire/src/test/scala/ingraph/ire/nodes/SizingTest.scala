package ingraph.ire.nodes

import akka.actor.{ActorRef, Props, actorRef2Scala}
import ingraph.ire.datatypes.{JoinCache, Tuple}
import ingraph.ire.engine.RelationalEngine
import ingraph.ire.inputs.InputTransactionFactory
import ingraph.ire.messages.{ChangeSet, Primary, Secondary, Terminator}
import ingraph.ire.nodes.binary.JoinNode
import ingraph.ire.nodes.unary.{ProductionNode, SelectionNode}
import ingraph.ire.util.SizeCounter
import ingraph.ire.util.TestUtil.{mask, tuple}
import ingraph.ire.messages.Terminator
import ingraph.ire.nodes.unary.SelectionNode
import org.scalatest.WordSpec
import org.scalatest.concurrent.TimeLimits

import scala.collection.mutable

class SizingTest extends WordSpec with TimeLimits {

  import ingraph.ire.util.Utils.conversions._
  class TestQuery1 extends RelationalEngine {
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
      val data = new JoinCache
      data.addBinding(tuple(2, 3), tuple(3, 4))
      data.addBinding(tuple(2, 3), tuple(3, 5))
      data.addBinding(tuple(2, 3), tuple(3, 6))
      data.addBinding(tuple(3, 2), tuple(2, 5))
      assert(SizeCounter.countDeeper(data.values) == 8)
    }

    "measure size" in {
      val input = new InputTransactionFactory
      val query = new TestQuery1
      input.subscribe(query.inputLookup)
      val inputTransaction = input.newInputTransaction
      inputTransaction.add("testval", tuple(5, 5))
      inputTransaction.add("testval", tuple(5, 6))
      inputTransaction.add("testval", tuple(5, 7))
      inputTransaction.sendAll()
      assert(query.getCounts == 12)
    }
  }
}
