import akka.actor.{ActorRef, Props, ActorSystem, Actor}
import akka.actor.Actor.Receive

/**
 * Created by Maginecz on 3/4/2015.
 */
object main {
  def main(args: Array[String]) = {
    // an actor needs an ActorSystem
    val system = ActorSystem("HelloSystem")

    // create and start the actor
    val helloActor = system.actorOf(Props[HelloActor], name = "helloactor")
    val myActor1 = system.actorOf(Props(new MyActor(helloActor)),name="myActor1")
    val myActor2 = system.actorOf(Props(new MyActor(helloActor)),name="myActor2")
    // send the actor two messages
    myActor1 ! "hi"
    myActor2 ! "hy"

    // shut down the system
    system.terminate
  }
}
class MyActor(next: ActorRef) extends Actor{
  override def receive: Receive =  {
    case "hi" => {
      println("calling next hello")
      next ! "hello"
    }
    case "hy" => {
      println("calling next yello")
      next ! "yello"
    }
  }
}
class HelloActor extends Actor {
  def receive = {
    case "hello" => println("hello back at you")
    case "yello" => println("yello back at you")
    case _       => println("huh?")
  }
}
