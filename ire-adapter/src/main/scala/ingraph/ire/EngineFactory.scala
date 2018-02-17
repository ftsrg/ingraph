package ingraph.ire

import akka.actor.{ActorRef, Props}
import hu.bme.mit.ire._
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.engine.RelationalEngine
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}
import hu.bme.mit.ire.nodes.binary._
import hu.bme.mit.ire.nodes.unary._
import hu.bme.mit.ire.nodes.unary.aggregation.{AggregationNode, StatefulAggregate}
import hu.bme.mit.ire.util.BufferMultimap
import hu.bme.mit.ire.util.Utils.conversions._
import ingraph.model.expr._
import ingraph.model.expr.types.{EdgeLabel, VertexLabel}
import ingraph.model.fplan
import ingraph.model.fplan._
import ingraph.model.jplan
import ingraph.parse.ExpressionParser
import org.apache.spark.sql.catalyst.expressions.{Ascending, Attribute, Expression}

import scala.collection.mutable

abstract class AnnotatedRelationalEngine extends RelationalEngine {
  val vertexConverters: BufferMultimap[Seq[String], GetVertices]
  val edgeConverters: BufferMultimap[String, GetEdges]
}

object EngineFactory {

  case class ForwardConnection(parent: FNode, child: (ReteMessage) => Unit)
  case class EdgeTransformer(nick: String, source:String, target: String)

  def createQueryEngine(plan: FNode, indexer: Indexer): AnnotatedRelationalEngine =
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
          case op: LeafFNode =>
            op match {
              case op: GetVertices => getVertices(op, expr)
              case op: GetEdges => getEdges(op, expr)
              case op: Dual =>
                inputs += ("" -> expr.child)
                expr.child(ChangeSet(positive=Vector(Vector())))
            }

          case op: UnaryFNode =>
            val node: (ReteMessage) => Unit = op match {
              case op: Production => production
              case op: Grouping => grouping(op, expr)
              case op: SortAndTop => sortAndTop(op, expr)
              case op: Selection => selection(op, expr)
              case op: Projection => projection(op, expr)
              case op: DuplicateElimination => newLocal(Props(new DuplicateEliminationNode(expr.child)))
              case op: AllDifferent => allDifferent(op, expr)
              case op: Create => create(op, indexer, expr)
              case op: Delete => delete(op, indexer, expr)
              case op: Merge => merge(op, indexer, expr)
              case op: Remove => remove(op, indexer, expr)
              case op: SetNode => set(op, indexer, expr)
            }
            remaining += ForwardConnection(op.child, node)

          case op: BinaryFNode =>
            val node: ActorRef = op match {
              case op: Union => newLocal(Props(new UnionNode(expr.child, op.jnode.bag)))
              case op: JoinLike =>
                val child = expr.child
                val leftTupleWidth  = op.left. internalSchema.length
                val rightTupleWidth = op.right.internalSchema.length
                val leftMask:  Seq[Int] = op.leftMask
                val rightMask: Seq[Int] = op.rightMask

                op match {
                  case op: AntiJoin => newLocal(Props(new AntiJoinNode(child, leftMask, rightMask)))
                  case op: Join => newLocal(Props(new JoinNode(
                    child, leftTupleWidth, rightTupleWidth, leftMask, rightMask)))
                  case op: LeftOuterJoin => newLocal(Props(new LeftOuterJoinNode(
                    child, leftTupleWidth, rightTupleWidth, leftMask, rightMask)))
                  case op: ThetaLeftOuterJoin =>
                    val theta = ExpressionParser[Boolean](op.condition)
                    newLocal(Props(new ThetaLeftOuterJoinNode(
                      child, leftTupleWidth, rightTupleWidth, leftMask, rightMask, theta)))
//              case op: TransitiveClosureJoin =>
//                val minHops = op.getEdgeListVariable.getMinHops
//                val maxHops = op.getEdgeListVariable.getMaxHops.getMaxHopsType match {
//                  case MaxHopsType.LIMITED => op.getEdgeListVariable.getMaxHops.getHops
//                  case MaxHopsType.UNLIMITED => Long.MaxValue
//                }
//              newLocal(Props(new TransitiveClosureJoinNode(child, leftTupleWidth, rightTupleWidth, leftMask,
//                rightMask, op.internalSchema.length, mixHops, maxHops)))
//                )))
                }
              }
              remaining += ForwardConnection(op.left, node.primary)
              remaining += ForwardConnection(op.right, node.secondary)
        }
      }

    // leaf nodes
    private def getVertices(op: GetVertices, expr: ForwardConnection) = {
      val labels = op.jnode.v.labels.vertexLabels.toSeq
      vertexConverters.addBinding(labels, op)
      inputs += (op.jnode.v.name -> expr.child)
    }

    private def getEdges(op: GetEdges, expr: ForwardConnection) = {
      val labels = op.jnode.edge.labels.edgeLabels.toSeq
      assert(labels.nonEmpty, s"Querying all edges is prohibitively exepensive, please use edge labels on $op")
      for (label <- labels) {
        edgeConverters.addBinding(label, op)
        if (!op.jnode.directed) {
          val reverse =
            fplan.GetEdges(op.extraAttributes,
              jplan.GetEdges(
                op.jnode.trg,
                op.jnode.src,
                op.jnode.edge,
                op.jnode.directed
              )
          )
          edgeConverters.addBinding(label, reverse)
        }
      }
      inputs += (op.jnode.edge.name -> expr.child)
    }

    // unary nodes

    private def grouping(op: Grouping, expr: ForwardConnection) = {
      val aggregates = op.projectionTuple.map(e => ExpressionParser.parseAggregate(e))
      val factories = aggregates.flatten.map(_._2()).toVector
      val nonAggregates = op.projectionTuple.filter(op.aggregationCriteria.contains)
      var normalIndex = 0
      var aggregateIndex = op.projectionTuple.size - op.aggregationCriteria.size - 1
      val projections = aggregates.map {
        case None => normalIndex += 1; normalIndex - 1
        case Some(_) => aggregateIndex += 1; aggregateIndex - 1
      }
      val aggregationMask = op.aggregationCriteria.map(e => ExpressionParser[Any](e)).toVector

      newLocal(Props(new AggregationNode(expr.child, aggregationMask, () => factories, projections.toVector)))
    }

    private def selection(op: Selection, expr: ForwardConnection) = {
      val condition = ExpressionParser[Boolean](op.condition)
      newLocal(Props(new SelectionNode(expr.child, condition)))
    }

    private def projection(op: Projection, expr: ForwardConnection) = {
      val expressions = op.projectionTuple.map(e => ExpressionParser[Any](e)).toVector
      newLocal(Props(new ProjectionNode(expr.child, expressions)))
    }

    private def sortAndTop(op: SortAndTop, expr: ForwardConnection) = {

      def getInt(e: Expression) = ExpressionParser[Long](e)(Vector())

      val skip: Option[Long] = op.skipExpr.map(getInt)
      val limit: Option[Long] = op.limitExpr.map(getInt)
      val sortKeys = op.order.map(
        e => ExpressionParser[Any](e.child)).toVector
      newLocal(Props(new SortAndTopNode(
        expr.child,
        op.jnode.order.length,
        sortKeys,
        skip,
        limit,
        op.jnode.order.map(_.direction == Ascending).toVector
      )))
    }

    private def allDifferent(op: AllDifferent, expr: ForwardConnection) = {
      // TODO
      val indices = op.schema.indices

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

    private def propsParser(v: ElementAttribute): Tuple => Map[String, Any] = {
      val parsed = v.properties.map { case (name, expr) =>
        name -> ExpressionParser[Any](expr)
      }
      (t: Tuple) => parsed.mapValues(_(t))
    }

    private def create(op: Create, indexer: Indexer, expr: ForwardConnection) = {
        val func = op.attribute match {
          case n: TupleEdgeAttribute =>
            // you've got to love the Law of Demeter
            val demeter = n.edge.labels.edgeLabels.head
            val sourceIndex = ExpressionParser[Long](n.src)
            val targetIndex = ExpressionParser[Long](n.trg)
            val props = propsParser(n)
            (t: Tuple) => {
              indexer.addEdge(
                indexer.newId(),
                sourceIndex(t),
                targetIndex(t),
                demeter,
                props(t)
              ).id
            }
          case variable: VertexAttribute =>
            val props = propsParser(variable)
            (t: Tuple) =>
              indexer.addVertex(IngraphVertex(indexer.newId(), variable.labels.vertexLabels, props(t))).id
        }

      (m: ReteMessage) => {
        val newMessage = m match {
          case cs: ChangeSet => ChangeSet(positive=cs.positive.map(
            tuple => tuple :+ func(tuple)))
          case _ => m
        }
        expr.child(newMessage)
      }
    }

    private def delete(op: Delete, indexer: Indexer, expr: ForwardConnection) = {
      val removals: Seq[(Tuple) => Unit] = op.attributes.map {
        index =>
          val expr = ExpressionParser[Long](index)
          if (index.isVertex) {
            (t: Tuple) => indexer.removeVertexById(expr(t), op.jnode.detach)
          } else {
            (t: Tuple) => indexer.removeEdgeById(expr(t))
          }
      }
      (m: ReteMessage) => {
        m match {
          case cs: ChangeSet => removals.foreach(r => cs.positive.foreach(r))
          case _ =>
        }
        expr.child(m)
      }
    }

    private def merge(op: Merge, indexer: Indexer, expr: ForwardConnection) = {
      ???
    }

    private def remove(op: Remove, indexer: Indexer, expr: ForwardConnection) = {
      ???
    }

    private def set(op: SetNode, indexer: Indexer, expr: ForwardConnection) = {
      ???
    }

    override val inputLookup: Map[String, (ChangeSet) => Unit] = inputs.toMap

    override val terminator: Terminator = Terminator(inputs.values, production)
  }
}

