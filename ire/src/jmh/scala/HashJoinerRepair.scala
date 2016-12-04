package x
import java.util.concurrent.TimeUnit

import akka.actor.{ActorRef, ActorSystem, Props}
import hu.bme.mit.ire._
import hu.bme.mit.ire.messages.{ChangeSet, Primary, Secondary}
import hu.bme.mit.ire.nodes.binary.NaturalJoinNode
import hu.bme.mit.ire.nodes.unary.ProductionNode
import hu.bme.mit.ire.util.TestUtil._
import hu.bme.mit.ire.util.utils.conversions._
import org.openjdk.jmh.annotations._

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.util.Random

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Array(Mode.AverageTime))
class HashJoinerRepair {
  var primary: ChangeSet = _
  var secondary: ChangeSet = _
  var joiner: ActorRef = _
  var repairSet: ChangeSet = _

  var terminator: Terminator = _
  var system: ActorSystem = _

  @Param(Array("1000", "10000", "100000"))
  var size: Int = _

  @Setup(Level.Trial)
  def generateData(): Unit = {
    val rnd = new Random(256)
    primary = ChangeSet(positive = Vector.fill(size)(tuple(rnd.nextInt(size / 2), rnd.nextDouble())))
    secondary = ChangeSet(positive = Vector.fill(size)(tuple(rnd.nextInt(size / 2), rnd.nextDouble())))
    val secondaryKeys = secondary.positive.map( s => s(0)).toSet
    repairSet = ChangeSet(negative = rnd.shuffle(primary.positive.filter( p => secondaryKeys.contains(p(0)))).take(100))
  }

  @Setup(Level.Iteration)
  def createNetwork(): Unit = {
    system = ActorSystem()
    val production = system.actorOf(Props(new ProductionNode("")))
    joiner = system.actorOf(Props(
      new NaturalJoinNode(production, Vector(0))))
    terminator = Terminator(Seq(joiner.primary, joiner.secondary), production)
    joiner ! Primary(primary)
    joiner ! Secondary(secondary)
    Await.result(terminator.send(), Duration(1, DAYS))
  }

  @Benchmark
  def repair() = {
    joiner ! Primary(repairSet)
    Await.result(terminator.send(), Duration(1, DAYS))
  }

  @TearDown(Level.Iteration)
  def tearDown(): Unit = {
   system.terminate()
  }
}
