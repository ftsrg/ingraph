import akka.actor.{ActorRef, Props, actorRef2Scala}
import hu.bme.mit.ire._
import hu.bme.mit.ire.datatypes.{Indexer, Tuple}
import hu.bme.mit.ire.messages.{ChangeSet, Primary, Secondary}
import hu.bme.mit.ire.nodes.binary.JoinNode
import hu.bme.mit.ire.nodes.unary.{ProductionNode, SelectionNode}
import hu.bme.mit.ire.trainbenchmark._
import hu.bme.mit.ire.util.SizeCounter
import hu.bme.mit.ire.util.TestUtil._
import org.scalatest.WordSpec
import org.scalatest.concurrent.TimeLimits

import scala.collection.mutable

class SizingTest extends WordSpec with TimeLimits {

  import util.Utils.conversions._
  class TestQuery1 extends TrainbenchmarkQuery {
    override val production: ActorRef = system.actorOf(Props(new ProductionNode("TestQuery")))
    override val inputLookup: Map[String, (ChangeSet) => Unit] = Map(
      "testval" -> ((cs: ChangeSet) => { joiner ! Primary(cs); joiner ! Secondary(cs) })
    )
    override val terminator: Terminator = Terminator(Vector(forwarder ! _), production)
    val forwarder = newLocal(Props(new SelectionNode(production, a => true)))
    val joiner = newLocal(Props(new JoinNode(forwarder, 2, 2, Vector(0), Vector(0))), "joiner")
  }

  "SizeCounter" should {
    "count" in {
      val data = mutable.HashMap[Tuple, Int]()
      data(Vector(5, 6, 7)) = 8
      data(Vector(5, 6, 9)) = 10
      assert(SizeCounter.count(data.keys) == 6)
    }

    "count deeper" in {
      val data: Indexer = new mutable.HashMap[Tuple, mutable.Set[Tuple]] with mutable.MultiMap[Tuple, Tuple]
      data.addBinding(Vector(2, 3), Vector(3, 4))
      data.addBinding(Vector(2, 3), Vector(3, 5))
      data.addBinding(Vector(2, 3), Vector(3, 6))
      data.addBinding(Vector(3, 2), Vector(2, 5))
      assert(SizeCounter.countDeeper(data.values) == 8)
    }
  }




  "measuring size" {
    val input = new TransactionFactory(10)
    val query = new TestQuery1
    input.subscribe(query.inputLookup)
    val tran0 = input.newBatchTransaction()
    tran0.add("testval", tuple(5, 5))
    tran0.add("testval", tuple(5, 6))
    tran0.add("testval", tuple(5, 7))
    tran0.close()
    println(query.getCounts)
    0
  }
}
