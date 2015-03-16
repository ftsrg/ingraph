import akka.actor.{ActorSystem, Props}
import org.scalatest.{BeforeAndAfterAll, FlatSpec}

/**
 * Created by Maginecz on 3/16/2015.
 */

class SelectTest extends FlatSpec with BeforeAndAfterAll {
  val system = ActorSystem("TestSystem")

  "A Selector" should "select the values" in {
    val changes = ChangeSet(
      positive = Vector(Vector(15, 16, 17, 18), Vector(4, 5, 6, 7)),
      negative = Vector(Vector(-0, -1, -2, -3), Vector(-10, -11, -12, -13))
    )
    val selectionVector = Vector(0, 2)
    val expectedChanges = ChangeSet(
      positive = Vector(Vector(15, 17), Vector(4, 6)),
      negative = Vector(Vector(-0, -2), Vector(-10, -12))
    )
    val assertActor = system.actorOf(Props(new TestActor(expectedChanges)), name = "assertActor")
    val selector = system.actorOf(Props(new Trimmer(assertActor, selectionVector)), name = "testSelector")

    selector ! changes
  }

  override def afterAll = {
    system.terminate()
  }
}
