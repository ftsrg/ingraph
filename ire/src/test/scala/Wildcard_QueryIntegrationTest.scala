import akka.actor.{ActorRef, Props, actorRef2Scala}
import hu.bme.mit.incqueryds._
import hu.bme.mit.incqueryds.trainbenchmark._
import org.scalatest.FlatSpec
import org.scalatest.concurrent.Timeouts
import org.scalatest.time.SpanSugar._

/**
 * Created by wafle on 12/09/15.
 */
class Wildcard_QueryIntegrationTest extends FlatSpec with Timeouts{
  class TestQuery extends TrainbenchmarkQuery {
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
    input.subscribe(query.inputLookup)
    val tran0 = input.newBatchTransaction()
    tran0.add(5, "testval", 5)
    tran0.add(5, "testval", 6)
    tran0.add(5, "testval", 7)
    tran0.close()
    val res0 = query.getResults()
    assert(res0 == Set(Vector(5,5), Vector(5,6), Vector(5,7)))
    val tran1 = input.newBatchTransaction()
    tran1.remove(5, "testval", 5)
    tran1.remove(5, "testval", 6)
    tran1.add(3, "tesval2", 2)
    tran1.add(5, "testval", 8)
    tran1.close()
    val res1 = query.getResults()
    assert(res1 == Set(Vector(5,7), Vector(5,8)))
    val beginTime : Long = System.nanoTime()
    (1 to 500).foreach( i => {
      val tranRemove7 = input.newBatchTransaction()
      tranRemove7.remove(5, "testval", 7)
      tranRemove7.close()
      assert(query.getResults() == Set(Vector(5,8)))
      val tranRemove8 = input.newBatchTransaction()
      tranRemove8.remove(5, "testval", 8)
      tranRemove8.close()
      assert(query.getResults() == Set())
      val tranAdd78 = input.newBatchTransaction()
      tranAdd78.add(5, "testval", 7)
      tranAdd78.add(5, "testval", 8)
      tranAdd78.close()
    } )
    val endTime : Long = System.nanoTime() - beginTime
    val avg = endTime / 500 / 3
    println(s"integration avg: $avg")
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
