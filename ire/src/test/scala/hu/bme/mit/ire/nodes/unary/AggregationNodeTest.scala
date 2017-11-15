package hu.bme.mit.ire.nodes.unary

import akka.actor.{ActorSystem, Props, actorRef2Scala}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.ChangeSet
import hu.bme.mit.ire.nodes.unary.aggregation._
import hu.bme.mit.ire.util.TestUtil.{cypherList, functionMask, tuple, tupleBag}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

import scala.concurrent.duration.Duration

class AggregationNodeTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  // 0: city, 1: name, 2: weapon, 3: sex, 4: height
  val odin: Tuple = tuple("Asgard", "Odin", "Gungnir", "male", 1)
  val thor: Tuple = tuple("Asgard", "Thor", "Mjölnir", "male", 1.1f)
  val freya: Tuple = tuple("Asgard", "Freya", "N/A", "female", 1.1)
  val geirröd: Tuple = tuple("Jötunheimr", "Geirröd ", "N/A", "male", 2L)
  val ragnar: Tuple = tuple("Midgard", "Ragnarr Loðbrók", "N/A", "male", 0.8)

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "Count" should {
    "count with complex keys" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val counter = system.actorOf(Props(new AggregationNode(echoActor ! _, functionMask(3, 0),
        () => Vector(new StatefulCount()), functionMask(0, 1, 2)))) // sex and the city
      counter ! ChangeSet(positive = tupleBag(odin))
      expectMsg(ChangeSet(positive = tupleBag(tuple("male", "Asgard", 1))))
      counter ! ChangeSet(positive = tupleBag(thor))
      expectMsg(ChangeSet(
        positive = tupleBag(tuple("male", "Asgard", 2)),
        negative = tupleBag(tuple("male", "Asgard", 1))
      ))
      counter ! ChangeSet(negative = tupleBag(odin))
      expectMsg(ChangeSet(
        positive = tupleBag(tuple("male", "Asgard", 1)),
        negative = tupleBag(tuple("male", "Asgard", 2))
      ))
      counter ! ChangeSet(positive = tupleBag(freya))
      expectMsg(ChangeSet(positive = tupleBag(tuple("female", "Asgard", 1))))
      counter ! ChangeSet(negative = tupleBag(freya))
      expectMsg(ChangeSet(negative = tupleBag(tuple("female", "Asgard", 1))))
    }
  }

  "Collect" should {
    "collect with complex keys" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val counter = system.actorOf(Props(new AggregationNode(echoActor ! _, functionMask(3, 0),
        () => Vector(new StatefulCollect(Vector(2))), functionMask(0, 1, 2)))) // (sex, city): (weapon)
      counter ! ChangeSet(positive = tupleBag(odin))
      expectMsg(ChangeSet(positive = tupleBag(tuple("male", "Asgard", cypherList(Vector("Gungnir"))))))
      counter ! ChangeSet(positive = tupleBag(thor))
      expectMsg(ChangeSet(
        positive = tupleBag(tuple("male", "Asgard", cypherList(Vector("Gungnir"), Vector("Mjölnir")))),
        negative = tupleBag(tuple("male", "Asgard", cypherList(Vector("Gungnir"))))
      ))
      counter ! ChangeSet(negative = tupleBag(odin))
      expectMsg(ChangeSet(
        positive = tupleBag(tuple("male", "Asgard", cypherList(Vector("Mjölnir")))),
        negative = tupleBag(tuple("male", "Asgard", cypherList(Vector("Gungnir"), Vector("Mjölnir"))))
      ))
      counter ! ChangeSet(positive = tupleBag(freya))
      expectMsg(ChangeSet(positive = tupleBag(tuple("female", "Asgard", cypherList(Vector("N/A"))))))
      counter ! ChangeSet(negative = tupleBag(freya))
      expectMsg(ChangeSet(negative = tupleBag(tuple("female", "Asgard", cypherList(Vector("N/A"))))))
    }
  }

  def assertNextChangeSetWithTolerance(key: Int, positive: Option[Any] = None, negative: Option[Any] = None): Unit = {
    val cs = receiveOne(Duration("1 s")).asInstanceOf[ChangeSet]

    def assertEquals(actual: Any, expected: Any) {
      (actual, expected) match {
        case (actual: Double, expected: Double) => actual should be(expected +- 0.01)
        case (actual: Float, expected: Float) => actual should be(expected +- 0.01f)
        case (actual: Float, expected: Double) => actual.toDouble should be(expected +- 0.01d)
        case (actual: Double, expected: Float) => actual should be(expected.toDouble +- 0.01d)
        case _ => actual should be(expected)
      }
    }
    if (positive.isDefined) {
      assertEquals(cs.positive.toVector(0)(key), positive.get)
    }

    if (negative.isDefined) {
      assertEquals(cs.negative.toVector(0)(key), negative.get)
    }
  }

  "Sum" should {
    "sum with complex keys" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val counter = system.actorOf(Props(new AggregationNode(echoActor ! _, functionMask(3),
        () => Vector(new StatefulSum(4)), functionMask(0, 1)))) // sex, sum for height
      counter ! ChangeSet(positive = tupleBag(odin))
      assertNextChangeSetWithTolerance(key = 1, positive = Some(1))
      counter ! ChangeSet(positive = tupleBag(thor))
      assertNextChangeSetWithTolerance(key = 1, positive = Some(2.1f), negative = Some(1))
      counter ! ChangeSet(positive = tupleBag(ragnar))
      assertNextChangeSetWithTolerance(key = 1, positive = Some(2.9), negative = Some(2.1f))
    }
  }

  "Average" should {
    "average with complex keys" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val counter = system.actorOf(Props(new AggregationNode(echoActor ! _, functionMask(3),
        () => Vector(new StatefulAverage(4)), functionMask(0, 1)))) // sex, sum for height
      counter ! ChangeSet(positive = tupleBag(odin))
      assertNextChangeSetWithTolerance(key = 1, positive = Some(1))
      counter ! ChangeSet(positive = tupleBag(thor))
      assertNextChangeSetWithTolerance(key = 1, positive = Some(2.1f/2), negative = Some(1))
      counter ! ChangeSet(positive = tupleBag(ragnar))
      assertNextChangeSetWithTolerance(key = 1, positive = Some(2.9/3), negative = Some(2.1f/2))
    }
  }

  "StandardDeviation" should {
    "stddev with complex keys" in {
      val t1: Tuple = tuple("Asgard", "Odin", "Gungnir", "male", 11)
      val t2: Tuple = tuple("Asgard", "Odin", "Gungnir", "male", 6)
      val t3: Tuple = tuple("Asgard", "Odin", "Gungnir", "male", 7.2)
      val t4: Tuple = tuple("Asgard", "Odin", "Gungnir", "male", 8)
      val t5: Tuple = tuple("Asgard", "Odin", "Gungnir", "male", 13.1)
      val t6: Tuple = tuple("Asgard", "Odin", "Gungnir", "male", 111.222)
      val t7: Tuple = tuple("Asgard", "Odin", "Gungnir", "male", -188.99)

      val echoActor = system.actorOf(TestActors.echoActorProps)
      val stddev = system.actorOf(Props(new AggregationNode(echoActor ! _, functionMask(3),
        () => Vector(new StatefulStandardDeviation(4)), functionMask(0, 1)))) // sex, sum for height
      stddev ! ChangeSet(positive = tupleBag(t1))
      assertNextChangeSetWithTolerance(key = 1, positive = Some(0))
      stddev ! ChangeSet(positive = tupleBag(t2))
      assertNextChangeSetWithTolerance(key = 1, positive = Some(2.5f), negative = Some(0))
      stddev ! ChangeSet(positive = tupleBag(t3))
      assertNextChangeSetWithTolerance(key = 1, positive = Some(2.131d), negative = Some(2.5f))
      stddev ! ChangeSet(positive = tupleBag(t4))
      assertNextChangeSetWithTolerance(key = 1, positive = Some(1.845d), negative = Some(2.131d))
      stddev ! ChangeSet(positive = tupleBag(t5))
      assertNextChangeSetWithTolerance(key = 1, positive = Some(2.6d), negative = Some(1.845d))
      stddev ! ChangeSet(positive = tupleBag(t6))
      assertNextChangeSetWithTolerance(key = 1, positive = Some(38.147d), negative = Some(2.6d))
      stddev ! ChangeSet(positive = tupleBag(t7))
      assertNextChangeSetWithTolerance(key = 1, positive = Some(83.136d), negative = Some(38.147d))
    }
  }

  "StandardDeviationSample" should {
    "stddev sample with complex keys" in {
      val t1: Tuple = tuple("Asgard", "Odin", "Gungnir", "male", 11)
      val t2: Tuple = tuple("Asgard", "Odin", "Gungnir", "male", 6)
      val t3: Tuple = tuple("Asgard", "Odin", "Gungnir", "male", 7.2)
      val t4: Tuple = tuple("Asgard", "Odin", "Gungnir", "male", 8)
      val t5: Tuple = tuple("Asgard", "Odin", "Gungnir", "male", 13.1)
      val t6: Tuple = tuple("Asgard", "Odin", "Gungnir", "male", 111.222)
      val t7: Tuple = tuple("Asgard", "Odin", "Gungnir", "male", -188.99)

      val echoActor = system.actorOf(TestActors.echoActorProps)
      val stddev = system.actorOf(Props(new AggregationNode(echoActor ! _, functionMask(3),
        () => Vector(new StatefulStandardDeviationSample(4)), functionMask(0, 1)))) // sex, sum for height
      stddev ! ChangeSet(positive = tupleBag(t1))
      assertNextChangeSetWithTolerance(key = 1, positive = Some(0))
      stddev ! ChangeSet(positive = tupleBag(t2))
      assertNextChangeSetWithTolerance(key = 1, positive = Some(3.535f), negative = Some(0))
      stddev ! ChangeSet(positive = tupleBag(t3))
      assertNextChangeSetWithTolerance(key = 1, positive = Some(2.61f), negative = Some(3.535f))
      stddev ! ChangeSet(positive = tupleBag(t4))
      assertNextChangeSetWithTolerance(key = 1, positive = Some(2.131f), negative = Some(2.61f))
      stddev ! ChangeSet(positive = tupleBag(t5))
      assertNextChangeSetWithTolerance(key = 1, positive = Some(2.916f), negative = Some(2.131f))
      stddev ! ChangeSet(positive = tupleBag(t6))
      assertNextChangeSetWithTolerance(key = 1, positive = Some(41.788f), negative = Some(2.916f))
      stddev ! ChangeSet(positive = tupleBag(t7))
      assertNextChangeSetWithTolerance(key = 1, positive = Some(89.797f), negative = Some(41.788f))
    }
  }

}
