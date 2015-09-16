import java.util.concurrent.Semaphore

import org.scalatest.concurrent.Timeouts

import scala.Vector
import org.scalatest.FlatSpec
import org.scalatest.time.SpanSugar._
import org.scalatest
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.actorRef2Scala
import hu.bme.mit.incquerydcore.ChangeSet
import hu.bme.mit.incquerydcore.Checker
import hu.bme.mit.incquerydcore.Production
import hu.bme.mit.incquerydcore.Terminator
import hu.bme.mit.incquerydcore.WildcardInput
import hu.bme.mit.incquerydcore.trainbenchmark._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
 * Created by wafle on 12/09/15.
 */
class Wildcard_QueryIntegrationTest extends FlatSpec with Timeouts{
  class TestQuery extends TrainbenchmarkQuery {
    val system = ActorSystem()
    override val production: ActorRef = system.actorOf(Props(new Production("TestQuery")))
    val forwarder = system.actorOf(Props(new Checker(production ! _, a => true)))
    override val inputLookup: Map[String, (ChangeSet) => Unit] = Map(
      "testval" -> ((cs:ChangeSet) => forwarder ! (cs))
    )
    override val terminator: Terminator = Terminator( Vector(forwarder ! _), production)
  }
  "integartion" should "work" in {
    val input = new WildcardInput(10)
    val query = new TestQuery
    val tran0 = input.newTransaction()
    tran0.add(5, "testval", 5)
    tran0.add(5, "testval", 6)
    tran0.add(5, "testval", 7)
    input.processTransaction(tran0)
    input.subscribe(query.inputLookup)
    val res0 = query.getResults()
    assert(res0 == Set(Vector(5,5), Vector(5,6), Vector(5,7)))
    val tran1 = input.newTransaction()
    tran1.remove(5, "testval", 5)
    tran1.remove(5, "testval", 6)
    tran1.add(3, "tesval2", 2)
    tran1.add(5, "testval", 8)
    input.processTransaction(tran1)
    val res1 = query.getResults()
    assert(res1 == Set(Vector(5,7), Vector(5,8)))
    (1 to 500).foreach( i => {
      val tranR = input.newTransaction()
      tranR.remove(5, "testval", 7)
      input.processTransaction(tranR)
      assert(query.getResults() == Set(Vector(5,8)))
      val tranA = input.newTransaction()
      tranA.add(5, "testval", 7)
      input.processTransaction(tranA)
      assert(query.getResults() == Set(Vector(5,7), Vector(5,8)))
    } )
  }
  val queries = List(
    new RouteSensor(),
    new PosLength(),
    new SemaphoreNeighbor(),
    new ConnectedSegments(),
    new SwitchSet(),
    new SwitchSensor()
  )
  queries.foreach(
    query => query.getClass.getName should "finish" in {
      failAfter(500 milliseconds) {
        query.getResults()
      }
    }
  )
}
