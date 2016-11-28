package x
import java.util.concurrent.TimeUnit

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.testkit.{TestActorRef, TestKit}
import hu.bme.mit.ire._
import hu.bme.mit.ire.utils.conversions._
import org.openjdk.jmh.annotations._

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.util.Random

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Array(Mode.AverageTime))
class HashJoinerBatch {
  var primary: ChangeSet = _
  var secondary: ChangeSet = _
  var joiner: ActorRef = _

  var terminator: Terminator = _
  var system: ActorSystem = _

  @Param(Array("1000", "10000", "100000"))
  var size: Int = _

  @Setup(Level.Trial)
  def generateData(): Unit = {
    val rnd = new Random(256)
    primary = ChangeSet(positive = Vector.fill(size)(Map(0 -> rnd.nextInt(size / 2), 1 -> rnd.nextDouble())))
    secondary = ChangeSet(positive = Vector.fill(size)(Map(0 -> rnd.nextInt(size / 2), 1 -> rnd.nextDouble())))
  }

  @Setup(Level.Iteration)
  def createNetwork(): Unit = {
    system = ActorSystem()
    val production = system.actorOf(Props(new ProductionNode("")))
    joiner = system.actorOf(Props(
      new NaturalJoinNode(production, Vector(0))))
    terminator = Terminator(Seq(joiner.primary, joiner.secondary), production)
    Await.result(terminator.send(), Duration(1, DAYS))
  }

  @Benchmark
  def batch() = {
    joiner ! Primary(primary)
    joiner ! Secondary(secondary)
    Await.result(terminator.send(), Duration(1, DAYS))
  }

  @TearDown(Level.Iteration)
  def tearDown(): Unit = {
   system.terminate()
  }

}
