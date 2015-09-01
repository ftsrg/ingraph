import java.io.ByteArrayOutputStream

import akka.actor.ActorSystem
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import com.esotericsoftware.kryo.io.Output
import com.twitter.chill.{Input, ScalaKryoInstantiator}
import hu.bme.mit.IQDcore.{ChangeSet, WildcardInput}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

/**
 * Created by janosmaginecz on 05/05/15.
 */
class WildcardInputTest (_system: ActorSystem) extends TestKit(_system) with ImplicitSender
with WordSpecLike with Matchers with BeforeAndAfterAll {
  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "WildcardInput" must {
    "process transactions" in {
      val input = new WildcardInput
      val tran = input.newTransaction()
      tran.add(6L, "multiWhatever", 1L)
      tran.add(6L, "multiWhatever", 2L)
      input.processTransaction(tran)
      assert(input.multiValueAttributes("multiWhatever")(6) == Set(1L, 2L))
    }
//    "store plain attributes" in {
//      val input = new WildcardInput()
//      input.addAttribute("_6", "whatever", 2L)
//      assert(input.attributes("whatever")(6) == 2L)
//    }
//    "do special formatting" in {
//      val input = new WildcardInput(specialFunction = Map("whatever" -> ((v:Any) => v.toString.toLong)))
//      input.addAttribute("_6", "whatever", "2")
//      assert(input.attributes("whatever")(6) == 2L)
//    }
//    "store types" in {
//      val input = new WildcardInput
//      input.addAttribute("_6", "type", "train")
//      input.addAttribute("_7", "type", "elephant")
//      assert(input.types("train") == Set(6L))
//    }
//    "throw exception when plain value is set twice"  in intercept[RuntimeException]{
//      val input = new WildcardInput
//      input.addAttribute("_6", "whatever", 1L)
//      input.addAttribute("_6", "whatever", 2L)
//    }
    "send all data on subscription" in {
      val input = new WildcardInput(messageSize = 4)
      val tran = input.newTransaction()
      tran.add(6L, "multiWhatever", 1L)
      tran.add(6L, "multiWhatever", 2L)
      input.processTransaction(tran)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      input.subscribe(Map("multiWhatever" -> (echoActor ! _ ) ))
      expectMsg(ChangeSet(positive= Vector(Vector(6, 1), Vector(6, 2))))
    }
    "send incoming data after subscription" in {
      val input = new WildcardInput(messageSize = 4)
      val tran = input.newTransaction()
      tran.add(6L, "test", 1L)
      tran.add(6L, "test", 2L)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      input.subscribe(Map("test" -> (echoActor ! _ ) ))
      input.processTransaction(tran)
      expectMsg(ChangeSet(positive= Vector(Vector(6, 1), Vector(6, 2))))
    }
    "do grouping" in {
      val input = new WildcardInput(messageSize = 2)
      val tran = input.newTransaction()
      for ( i <- 1 to 3 ) {
        tran.add(6L, "test", i)
      }
      input.processTransaction(tran)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      input.subscribe(Map("test" -> (echoActor ! _ ) ))
      expectMsg(ChangeSet(positive= Vector(Vector(6,1),Vector(6,2))))
      expectMsg(ChangeSet(positive= Vector(Vector(6,3))))
    }
    "be able to serialize and deserialize" in {
      val input = new WildcardInput
      val tran = input.newTransaction()
      tran.add(6L, "multiWhatever", 1L)
      tran.add(6L, "multiWhatever", 2L)
      tran.add(6L, "type", "train")
      val echoActor = system.actorOf(TestActors.echoActorProps)
      input.subscribe(Map("test" -> (echoActor ! _ ) ))
      input.processTransaction(tran)
      val instantiator = new ScalaKryoInstantiator
      instantiator.setRegistrationRequired(false)
      val kryo = instantiator.newKryo()
      val baos = new ByteArrayOutputStream
      val output = new Output(baos, 8192)
      kryo.writeObject(output, input)
      val deserialized = WildcardInput(new Input(baos.toByteArray))
      assert(deserialized.multiValueAttributes("multiWhatever")(6L).size == 2)
      assert(deserialized.multiValueAttributes("type").size == 1)
    }
  }
}
