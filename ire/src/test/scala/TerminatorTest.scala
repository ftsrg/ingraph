import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActorRef, TestActors, TestKit}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

/**
 * Created by janosmaginecz on 10/05/15.
 */
class TerminatorTest (_system: ActorSystem) extends TestKit(_system) with ImplicitSender
with WordSpecLike with Matchers with BeforeAndAfterAll {
  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }
  "TerminatorInitializer" must {
    "return unique ranges" in {
      val range1 = TerminatorInitializer.getUniqueRange(5)
      val range2 = TerminatorInitializer.getUniqueRange(10)
      assert(!range1.containsSlice(range2))
      assert(!range2.containsSlice(range1))
    }
    "send unique ranges to the production nodes" in {

      val production1 = TestActorRef(new Production("1"))
      val production2 = TestActorRef(new Production("2"))

      TerminatorInitializer(Array( a=> {} ,a=> {} ), Array(production1 ! _,production2 ! _),"test", a => {} )
      val set1 = production1.underlyingActor.terminatorSets(0)
      val set2 = production2.underlyingActor.terminatorSets(0)

      assert(set1.intersect(set2).size == 0)
      assert(set2.intersect(set1).size == 0)

    }
    "send unique values to the inputs" in {
      val values = new collection.parallel.mutable.ParHashSet[Int]
      def testerFunction(msg: ReteMessage): Unit = {
        msg match {
          case t:Terminator => values.+=(t.id)
        }
      }

      val production1 = TestActorRef(new Production("1"))

      val inputs = for (i <- Array.range(0, 9)) //range is inclusive on both sides in scala, leading to confused pythonists
        yield testerFunction _
      TerminatorInitializer(inputs, Vector(production1 ! _,production1 ! _,production1 ! _), "", a=> {} )
      assert(values.size == 30)
    }
  }
  "alpha nodes" must {
    "propagate terminator messages" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val production = system.actorOf(Props(new Production("")))
      val intermediary = system.actorOf(Props(new Checker(production ! _, c => true)))
      val input = system.actorOf(Props(new Checker(intermediary ! _, c => true)))
      input ! ChangeSet(positive = Vector(Vector(15)))
      input ! ChangeSet(positive = Vector(Vector(19)))
      TerminatorInitializer(Array(input ! _), Array(production ! _), "", res => echoActor ! res)

      expectMsg(Set(Vector(15),Vector(19)))
    }
  }
  "beta nodes" must {
    "propagate terminator messages" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val production = system.actorOf(Props(new Production("")))
      val intermediary = system.actorOf(Props(new HashJoiner(production ! _,1,Vector(0),1,Vector(0))))
      val input = system.actorOf(Props(new HashJoiner(intermediary ! Secondary(_),1,Vector(0), 1,Vector(0))))
      input ! Primary(ChangeSet(positive = Vector(Vector(15))))
      input ! Secondary(ChangeSet(positive = Vector(Vector(15))))
      intermediary ! Primary(ChangeSet(positive = Vector(Vector(15))))
      TerminatorInitializer(Array(input ! _), Array(production ! _), "", res => echoActor ! res)

      expectMsg(Set(Vector(15)))
    }
  }
}
