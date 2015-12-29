import java.util.Comparator

import akka.actor.{Props, ActorSystem}
import akka.testkit.{TestActors, ImplicitSender, TestKit}
import hu.bme.mit.incquerydcore._
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

/**
  * Created by wafle on 12/29/2015.
  */
class SortedMergeJoinerTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  class NodeComparator(primarySelector: Vector[Int], secondarySelector: Vector[Int]) extends Comparator[nodeType] {
    override def compare(prim: nodeType, sec: nodeType): Int = {
      primarySelector.map(i => prim(i)) match {
        case Vector(a1: Int, b1: String) => {
          secondarySelector.map(i => sec(i)) match {
            case Vector(a2: Int, b2: String) => {
              val a = a1.compareTo(a1)
              if (a != 0)
                return a
              else
                return b1.compareTo(b2)
            }
          }
        }
      }
    }
  }

  "A SortedMergeJoiner" must {
    "join primary values" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val primarySel = Vector(0, 1)
      val secondarySel = Vector(0, 1)
      val comparator = new NodeComparator(primarySel, secondarySel)
      val joiner = system.actorOf(Props(new SortedMergeJoiner(echoActor ! _, comparator, 2, primarySel, 2, secondarySel)))
      joiner ! Secondary(ChangeSet(positive = Vector(
        Vector(1, "1"),
        Vector(2, "2"),
        Vector(2, "3")
      )))

      joiner ! Primary(ChangeSet(positive = Vector(
        Vector(3, "5"),
        Vector(2, "2"),
        Vector(1, "1")
      )))
      expectMsgAnyOf(
        utils.changeSetPermutations(ChangeSet(positive = Vector(
          Vector(2, "2"),
          Vector(1, "1")
        ))):_*
      )

      joiner ! Secondary(ChangeSet(positive = Vector(Vector(3, "5"))))
      expectMsg(ChangeSet(positive = Vector(Vector(3, "5"))))
    }
  }
}
