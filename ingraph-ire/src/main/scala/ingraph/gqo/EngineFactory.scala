package ingraph.gqo

import akka.actor.{ActorRef, Props}
import hu.bme.mit.incqueryds._
import hu.bme.mit.incqueryds.trainbenchmark.TrainbenchmarkQuery
import hu.bme.mit.incqueryds.utils.conversions._
import org.eclipse.emf.common.util.EList
import relalg._

import scala.collection.mutable

object EngineFactory extends App {

  implicit def emfConversion[E](list: EList[E]): Vector[E] = {
    (for (v <- 0 until list.size)
      yield list.get(v)).toVector
  }

  case class ForwardConnection(parent: Operator, child: (ReteMessage) => Unit)
  case class EdgeTransformer(nick: String, source:String, target: String)
  def createQueryEngine(plan: ProductionOperator) =
    new TrainbenchmarkQuery {
      override val production = system.actorOf(Props(new Production("")))


      val remaining: mutable.ArrayBuffer[ForwardConnection] = mutable.ArrayBuffer()
      val inputs: mutable.HashMap[String, (ReteMessage) => Unit] = mutable.HashMap()
      val vertexConverters = new mutable.HashMap[String, mutable.Set[String]] with mutable.MultiMap[String, String]
      val edgeConverters = new mutable.HashMap[String, mutable.Set[EdgeTransformer]] with mutable.MultiMap[String, EdgeTransformer]

      remaining += ForwardConnection(plan.getInput, production)

      while (remaining.nonEmpty) {
        val expr = remaining.remove(0)
        expr.parent match {
          case op: AlphaOperator =>
            val node: ActorRef = op match {
              case op: SelectionOperator => newLocal(Props(new Checker(expr.child, (r: nodeType) => true)))
              case op: ProjectionOperator =>
                val variables = op.getVariables.map(_.getName)
                newLocal(Props(new Trimmer(expr.child, variables)))
              case op: DuplicateEliminationOperator => newLocal(Props(new Checker(expr.child, (r: nodeType) => true)))
              case op: AllDifferentOperator => newLocal(Props(new Checker(expr.child, (r: nodeType) => true)))
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
            remaining += ForwardConnection(op.getRightInput, node.secondary)

          case op: GetVerticesOperator =>
            val nick = op.getVertexVariable.getName
            val label= op.getVertexVariable.getVertexLabels.get(0).getName // TODO fix this for multiple labels
            vertexConverters.addBinding(label, nick)
            inputs += (nick -> expr.child)
          case op: GetEdgesOperator =>
            val nick = op.getEdgeVariable.getName
            val label = op.getEdgeVariable.getEdgeLabels.get(0).getName // TODO fix this for multiple labels
            val sourceNick = op.getSourceVertexVariable.getName
            val targetNick = op.getTargetVertexVariable.getName
            edgeConverters.addBinding(label, EdgeTransformer(nick, source = sourceNick, target = targetNick))
            inputs += (nick -> expr.child)
        }
      }

      override val inputLookup: Map[String, (ChangeSet) => Unit] = inputs.toMap
      println(inputs.values)
      override val terminator: Terminator = Terminator(inputs.values, production)
    }
}
