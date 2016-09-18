import akka.actor.{ActorRef, ActorSystem, Props}
import hu.bme.mit.incqueryds._
import hu.bme.mit.incqueryds.trainbenchmark.TrainbenchmarkQuery
import ingraph.trainbenchmark.TrainBenchmarkUtil

import scala.collection.mutable
import hu.bme.mit.incqueryds.utils.conversions._
import org.eclipse.emf.common.util.EList
import relalg._

object Wrapper extends App {

  implicit def emfConversion[E](list: EList[E]): Vector[E] = {
    (for (v <- 0 to list.size)
      yield list.get(v)).toVector
  }

  case class ForwardConnection(parent: Operator, child: (ReteMessage) => Unit)

  def createQueryEngine(plan: ProductionOperator) {
    new TrainbenchmarkQuery {
      override val production = system.actorOf(Props(new Production("")))


      val remaining: mutable.ArrayBuffer[ForwardConnection] = mutable.ArrayBuffer()
      val inputs: mutable.HashMap[String, (ReteMessage) => Unit] = mutable.HashMap()

      remaining += ForwardConnection(plan, production)

      while (remaining.nonEmpty) {
        val expr = remaining.remove(0)
        expr.parent match {

          case op: AlphaOperator =>
            val node: ActorRef = op match {
              case op: SelectionOperator => newLocal(Props(new Checker(expr.child, (r: nodeType) => true)))
              case op: ProjectionOperator =>
                val variables = op.getVariables.map(_.getName)
                newLocal(Props(new Trimmer(expr.child, variables)))
            }
            remaining += ForwardConnection(op.getInput, node)

          case op: BetaOperator =>
            val node: ActorRef = op match {
              case op: AntiJoinOperator =>
                val names = op.getMutualVariables.map(_.getName)
                newLocal(Props(new HashLeftAntijoiner(expr.child, names, names)))
              case op: JoinOperator =>
                val names = op.getMutualVariables.map(_.getName)
                newLocal(Props(new NaturalJoiner(expr.child, names)))
            }
            remaining += ForwardConnection(op.getLeftInput, node.primary)
            remaining += ForwardConnection(op.getLeftInput, node.secondary)

          case op: GetVerticesOperator =>
            val name = op.getVertexVariable.getVertexLabel.getName
            inputs += (name -> expr.child)
          case op: GetEdgesOperator =>
            val name = op.getEdgeVariable.getEdgeLabel.getName
            inputs += (name -> expr.child)
        }
      }

      override val inputLookup: Map[String, (ChangeSet) => Unit] = inputs.toMap
      override val terminator: Terminator = Terminator(inputs.values, production)
    }
  }
}
