package ingraph.ire

import akka.actor.{ActorRef, Props}
import com.google.common.collect.ImmutableMap
import hu.bme.mit.ire._
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}
import hu.bme.mit.ire.nodes.binary.{AntiJoinNode, JoinNode, LeftOuterJoinNode}
import hu.bme.mit.ire.nodes.unary.{DuplicateEliminationNode, ProductionNode, ProjectionNode, SelectionNode}
import hu.bme.mit.ire.trainbenchmark.TrainbenchmarkQuery
import hu.bme.mit.ire.util.Utils.conversions._
import ingraph.relalg.util.SchemaToMap
import org.eclipse.emf.common.util.EList
import relalg._

import scala.collection.mutable

object EngineFactory {

  implicit def emfConversion[E](list: EList[E]): Vector[E] = {
    val l = for (v <- 0 until list.size)
      yield list.get(v)
    l.toVector
  }

  implicit def emfToIntConversion(list: EList[Integer]): Vector[Int] = {
    var l = for (v <- 0 until list.size)
      yield list.get(v).toInt
    l.toVector
  }

  implicit def guavaToScala[K, V](map: ImmutableMap[K, V]): Map[K, V] = {
    val iterator = map.entrySet().iterator()
    val scalaMap = new mutable.HashMap[K, V]()
    while (iterator.hasNext) {
      val entry = iterator.next()
      scalaMap(entry.getKey) = entry.getValue
    }
    scalaMap.toMap
  }

  val schemaToMap = new SchemaToMap()

  case class ForwardConnection(parent: Operator, child: (ReteMessage) => Unit)
  case class EdgeTransformer(nick: String, source:String, target: String)
  def createQueryEngine(plan: Operator) =
    new TrainbenchmarkQuery {
      override val production = system.actorOf(Props(new ProductionNode("")))
      val remaining: mutable.ArrayBuffer[ForwardConnection] = mutable.ArrayBuffer()
      val inputs: mutable.HashMap[String, (ReteMessage) => Unit] = mutable.HashMap()

      val vertexConverters = new mutable.HashMap[String, mutable.Set[Tuple2[String, Vector[String]]]] with mutable.MultiMap[String, Tuple2[String, Vector[String]]]
      val edgeConverters  = new mutable.HashMap[String, mutable.Set[GetEdgesOperator]] with mutable.MultiMap[String, GetEdgesOperator]

      remaining += ForwardConnection(plan, production)

      while (remaining.nonEmpty) {
        val expr = remaining.remove(0)
        expr.parent match {
          case op: UnaryOperator =>
            val node: ActorRef = op match {
            case op: GroupingOperator =>
              op.getEntries.map { x => println(x.isDistinct) }
              ???
            case op: SelectionOperator =>
                val variableLookup = new SchemaToMap().schemaToMap(op)
                newLocal(Props(new SelectionNode(expr.child, ExpressionParser.parse(op.getCondition, variableLookup))))

              case op: ProjectionOperator =>
                val lookup = schemaToMap.schemaToMap(op)
                val mask = op.getElements.map(element => lookup.get(element.getExpression).toInt)
                newLocal(Props(new ProjectionNode(expr.child, mask)))
              case op: DuplicateEliminationOperator => newLocal(Props(new DuplicateEliminationNode(expr.child)))
              case op: AllDifferentOperator =>
                val indices = Vector(0) // TODO
                def allDifferent(r: Tuple): Boolean = {
                  val seen = mutable.HashSet[Any]()
                  for (value <- indices.map(r(_))) {
                    if (seen(value)) {
                      return false
                    } else {
                      seen += value
                    }
                  }
                  true
                }

                newLocal(Props(new SelectionNode(expr.child, allDifferent)))
            }
            remaining += ForwardConnection(op.getInput, node)

          case op: BinaryOperator =>
            val node: ActorRef = op match {
              case op: AntiJoinOperator =>
                newLocal(Props(new AntiJoinNode(expr.child, emfToIntConversion(op.getLeftMask), emfToIntConversion(op.getRightMask))))
              case op: JoinOperator =>
                val names = op.getCommonVariables.map(_.getName)
                newLocal(Props(new JoinNode(
                    expr.child,
                    op.getLeftInput.getDetailedSchema.length,
                    op.getRightInput.getDetailedSchema.length,
                    emfToIntConversion(op.getLeftMask),
                    emfToIntConversion(op.getRightMask)
                )))
              case op: LeftOuterJoinOperator =>
                val names = op.getCommonVariables.map(_.getName)
                newLocal(Props(new LeftOuterJoinNode(
                  expr.child,
                  op.getLeftInput.getDetailedSchema.length,
                  op.getRightInput.getDetailedSchema.length,
                  emfToIntConversion(op.getLeftMask),
                  emfToIntConversion(op.getRightMask)
                )))
            }
            remaining += ForwardConnection(op.getLeftInput, node.primary)
            remaining += ForwardConnection(op.getRightInput, node.secondary)

          case op: GetVerticesOperator =>
            val nick = op.getVertexVariable.getName
            val label= op.getVertexVariable.getVertexLabelSet.getVertexLabels.get(0).getName // TODO fix this for multiple labels
            vertexConverters.addBinding(label, (nick, op.getDetailedSchema.map(_.getName)))
            inputs += (nick -> expr.child)
          case op: GetEdgesOperator =>
            val nick = op.getEdgeVariable.getName
            val label = op.getEdgeVariable.getEdgeLabelSet.getEdgeLabels.get(0).getName // TODO fix this for multiple labels
            edgeConverters.addBinding(label, op)
            inputs += (nick -> expr.child)
        }
      }

      override val inputLookup: Map[String, (ChangeSet) => Unit] = inputs.toMap
      override val terminator: Terminator = Terminator(inputs.values, production)
    }
}
