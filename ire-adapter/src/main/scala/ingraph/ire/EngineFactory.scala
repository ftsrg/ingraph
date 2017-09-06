package ingraph.ire

import akka.actor.{ActorRef, Props}
import hu.bme.mit.ire._
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.engine.RelationalEngine
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}
import hu.bme.mit.ire.nodes.binary.JoinNode
import hu.bme.mit.ire.nodes.unary._
import hu.bme.mit.ire.util.BufferMultimap
import hu.bme.mit.ire.util.Utils.conversions._
import ingraph.expressionparser.ExpressionParser
import ingraph.model.eplan.{ENode, SchemaToMap, UnaryENode}
import ingraph.model.expr.datatypes.{EdgeLabel, VertexLabel}
import ingraph.model.eplan._
import org.apache.spark.sql.catalyst.expressions.{Ascending, Expression}

import scala.collection.mutable

abstract class AnnotatedRelationalEngine extends RelationalEngine {
  val vertexConverters: BufferMultimap[Seq[String], GetVertices]
  val edgeConverters: BufferMultimap[String, GetEdges]
}

object EngineFactory {

  def getSchema(node: ENode): Map[String, Int] = SchemaToMap.schemaToMapNames(node)

  case class ForwardConnection(parent: ENode, child: (ReteMessage) => Unit)
  case class EdgeTransformer(nick: String, source:String, target: String)

  def createQueryEngine(plan: ENode, indexer: Indexer): AnnotatedRelationalEngine =
    new AnnotatedRelationalEngine {
      override val production: ActorRef = system.actorOf(Props(new ProductionNode("")))
      val remaining: mutable.ArrayBuffer[ForwardConnection] = mutable.ArrayBuffer()
      val inputs: mutable.HashMap[String, (ReteMessage) => Unit] = mutable.HashMap()

      override val vertexConverters = new BufferMultimap[Seq[VertexLabel], GetVertices]
      override val edgeConverters  = new BufferMultimap[EdgeLabel, GetEdges]

      remaining += ForwardConnection(plan, production)

      while (remaining.nonEmpty) {
        val expr = remaining.remove(0)
        expr.parent match {
          case op: UnaryENode =>
            val node: (ReteMessage) => Unit = op match {
              case op: Production => production
              //            case op: Grouping =>
              //              val variableLookup = getSchema(op.child)
              //              val aggregates = op.getElements.flatMap(
              //                e => ExpressionParser.parseAggregate(e.getExpression, variableLookup)
              //              )
              //              val functions = () => aggregates.map(
              //                _._2() // GOOD LUCK UNDERSTANDING THIS
              //              )
              //              val aggregationCriteria = op.getAggregationCriteria.map(e => (e, ExpressionParser.parseValue(e, variableLookup)))
              //              val projectionVariableLookup: Map[String, Integer] =
              //                aggregationCriteria.zipWithIndex.map( a => a._1._1.fullName -> a._2.asInstanceOf[Integer] ).toMap ++
              //                aggregates.zipWithIndex.map( az => az._1._1 -> (az._2 + op.getAggregationCriteria.size()).asInstanceOf[Integer])
              //              val projectionExpressions = op.getInternalElements.map( e => ExpressionParser.parseValue(e.getExpression, projectionVariableLookup))
              //              newLocal(Props(new AggregationNode(expr.child, aggregationCriteria.map(_._2), functions, projectionExpressions)))
              case op: SortAndTop =>
                val variableLookup = getSchema(op.child)

                // This is the mighty EMF, so there are no default values, obviously
                def getInt(e: Expression) = ExpressionParser.parseValue(e, variableLookup)(Vector()).asInstanceOf[Long]

                val skip: Long = getInt(op.inode.skipExpr)
                val limit: Long = getInt(op.inode.limitExpr)
                val sortKeys = op.inode.order.map(
                  e => ExpressionParser.parseValue(e, variableLookup)).toVector
                newLocal(Props(new SortAndTopNode(
                  expr.child,
                  op.inode.order.length,
                  sortKeys,
                  skip,
                  limit,
                  op.inode.order.map(_.direction == Ascending).toVector
                )))

              case op: Selection =>
                val variableLookup = getSchema(op.child)
                newLocal(Props(new SelectionNode(expr.child, ExpressionParser.parse(op.inode.condition, variableLookup))))

              case op: Projection =>
                val lookup = getSchema(op.child)
                val expressions = op.internalSchema.map(e => ExpressionParser.parseValue(e, lookup)).toVector
                newLocal(Props(new ProjectionNode(expr.child, expressions)))
              case op: DuplicateElimination => newLocal(Props(new DuplicateEliminationNode(expr.child)))
              case op: AllDifferent =>
                val schema = getSchema(op.child)
                val indices = op.inode.edges.map(v => schema(v.name))

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
              //            case op: Create =>
              //              val lookup = getSchema(op.child)
              //              val creations: Vector[(Tuple) => Any] = for (element <- op.getElements)
              //                yield element.asInstanceOf[ExpressionVariable].getExpression match {
              //                  case n: NavigationDescriptor =>
              //                    val demeter = n.getEdgeVariable.getEdgeLabelSet.getEdgeLabels.get(0).getName
              //                    val sourceIndex = lookup(n.getSourceVertexVariable.getName)
              //                    val targetIndex = lookup(n.getTargetVertexVariable.getName)
              //                    (t: Tuple) => {
              //                      indexer.addEdge(
              //                        indexer.newId(),
              //                        t(sourceIndex).asInstanceOf[Long],
              //                        t(targetIndex).asInstanceOf[Long],
              //                        demeter)
              //                    }
              //                  case v: VariableExpression =>
              //                    val variable = v.getVariable.asInstanceOf[VertexVariable]
              //                    (t: Tuple) => indexer.addVertex(IngraphVertex(
              //                      indexer.newId(), variable.getVertexLabelSet.getVertexLabels.map(l => l.getName).toSet))
              //                }
              //
              //              (m: ReteMessage) => {
              //                m match {
              //                  case cs: ChangeSet => creations.foreach(r => cs.positive.foreach(r))
              //                  case _ =>
              //                }
              //                expr.child(m)
              //              }
              //            case op: Delete =>
              //              val lookup = getSchema(op.child)
              //              val removals: Seq[(Tuple) => Unit] = for (element <- op.getElements)
              //                yield element.getExpression.asInstanceOf[VariableExpression].getVariable match {
              //                  case e: EdgeVariable =>
              //                    (t: Tuple) => indexer.removeEdgeById(t(lookup(e.getName)).asInstanceOf[Long])
              //                  case v: VertexVariable =>
              //                    (t: Tuple) => indexer.removeVertexById(t(lookup(v.getName)).asInstanceOf[Long])
              //                }
              //              (m: ReteMessage) => {
              //                m match {
              //                  case cs: ChangeSet => removals.foreach(r => cs.positive.foreach(r))
              //                  case _ =>
              //                }
              //                expr.child(m)
              //              }
              //            }
            }
        remaining += ForwardConnection(op.child, node)

      case op: BinaryENode =>
        val node: ActorRef = op match {
//          case op: AntiJoin =>
//            newLocal(Props(new AntiJoinNode(expr.child, emfToInt(op.getLeftMask), emfToInt(op.getRightMask))))
          case op: Join =>

            newLocal(Props(new JoinNode(
                expr.child,
                op.left.internalSchema.length,
                op.right.internalSchema.length,
                Vector(0), // TODO
                Vector(0)
            )))
//          case op: LeftOuterJoin =>
//            val leftMask = emfToInt(op.getLeftMask)
//            val rightMask = emfToInt(op.getRightMask)
//            newLocal(Props(new LeftOuterJoinNode(
//              expr.child,
//              op.getLeftInput.getInternalSchema.length,
//              op.getRightInput.getInternalSchema.length,
//              leftMask,
//              rightMask
//            )))
//          case op: TransitiveClosureJoin =>
//            val leftMask = emfToInt(op.getLeftMask)
//            val rightMask = emfToInt(op.getRightMask)
//            val minHops = op.getEdgeListVariable.getMinHops
//            val maxHops = op.getEdgeListVariable.getMaxHops.getMaxHopsType match {
//              case MaxHopsType.LIMITED => op.getEdgeListVariable.getMaxHops.getHops
//              case MaxHopsType.UNLIMITED => Long.MaxValue
//            }
//            newLocal(Props(new TransitiveClosureJoinNode(
//              expr.child,
//              op.getLeftInput.getInternalSchema.length,
//              op.getRightInput.getInternalSchema.length,
//              leftMask,
//              rightMask,
//              op.getInternalSchema.length,
//              minHops,
//              maxHops
//            )))
        }
        remaining += ForwardConnection(op.left, node.primary)
        remaining += ForwardConnection(op.right, node.secondary)

        case op: GetVertices =>
          val nick = op.nodeName
          val labels = op.inode.v.labels.vertexLabels.toSeq
          vertexConverters.addBinding(labels, op)
          inputs += (nick -> expr.child)
        case op: GetEdges =>
          val nick = op.nodeName
          val labels = op.inode.edge.labels.edgeLabels.toSeq
          for (label <- labels)
            edgeConverters.addBinding(label, op)
          inputs += (nick -> expr.child)
        case op: Dual =>
          inputs += ("" -> expr.child)
          expr.child(ChangeSet(positive=Vector(Vector())))

      }
      }

      override val inputLookup: Map[String, (ChangeSet) => Unit] = inputs.toMap
      override val terminator: Terminator = Terminator(inputs.values, production)
    }
}
