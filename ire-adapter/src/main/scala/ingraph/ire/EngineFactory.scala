package ingraph.ire

import akka.actor.{ActorRef, Props}
import hu.bme.mit.ire._
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.engine.RelationalEngine
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}
import hu.bme.mit.ire.nodes.binary.{AntiJoinNode, JoinNode, LeftOuterJoinNode, UnionNode}
import hu.bme.mit.ire.nodes.unary._
import hu.bme.mit.ire.util.BufferMultimap
import hu.bme.mit.ire.util.Utils.conversions._
import ingraph.expressionparser.ExpressionParser
import ingraph.model.expr.types.{EdgeLabel, VertexLabel}
import ingraph.model.expr.{EdgeAttribute, NavigationDescriptor, VertexAttribute}
import ingraph.model.fplan.{FNode, SchemaToMap, UnaryFNode, _}
import ingraph.model.jplan
import org.apache.spark.sql.catalyst.expressions.{Ascending, Attribute, Expression}

import scala.collection.mutable

abstract class AnnotatedRelationalEngine extends RelationalEngine {
  val vertexConverters: BufferMultimap[Seq[String], GetVertices]
  val edgeConverters: BufferMultimap[String, GetEdges]
}

object EngineFactory {

  def getSchema(node: FNode): Map[String, Int] = SchemaToMap.schemaToMapNames(node)

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
              case op: Dual => inputs += ("" -> expr.child)
              expr.child(ChangeSet(positive=Vector(Vector())))
            }

          case op: UnaryFNode =>
            val node: (ReteMessage) => Unit = op match {
              case op: Production => production
              //case op: Grouping => instantiateGrouping(op, expr)
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
                val leftTupleWidth = op.left.internalSchema.length
                val rightTupleWidth = op.right.internalSchema.length
                val leftMask = op.leftMask
                val rightMask: Seq[Int] = op.rightMask

                op match {
                  case op: AntiJoin => newLocal(Props(new AntiJoinNode(child, leftMask, rightMask)))
                  case op: Join => newLocal(Props(new JoinNode(child, leftTupleWidth, rightTupleWidth, leftMask, rightMask)))
                  case op: LeftOuterJoin => newLocal(Props(new LeftOuterJoinNode(child, leftTupleWidth, rightTupleWidth, leftMask, rightMask)))
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
      for (label <- labels) {
        edgeConverters.addBinding(label, op)
        if (!op.jnode.directed) {
          val reverse = GetEdges(op.extraAttributes,
            jplan.GetEdges(op.jnode.trg, op.jnode.src, op.jnode.edge, op.jnode.directed))
          edgeConverters.addBinding(label, reverse)
        }
      }
      inputs += (op.jnode.edge.name -> expr.child)
    }

    // unary nodes

//    private def instantiateGrouping(op: Grouping, expr: ForwardConnection): Unit = {
//      case op: Grouping =>
//        val variableLookup = getSchema(op.child)
//        val aggregates = op.getElements.flatMap(
//          e => ExpressionParser.parseAggregate(e.getExpression, variableLookup)
//        )
//        val functions = () => aggregates.map(
//          _._2() // GOOD LUCK UNDERSTANDING THIS
//        )
//        val aggregationCriteria = op.getAggregationCriteria.map(e => (e, ExpressionParser.parseValue(e, variableLookup)))
//        val projectionVariableLookup: Map[String, Integer] =
//          aggregationCriteria.zipWithIndex.map( a => a._1._1.fullName -> a._2.asInstanceOf[Integer] ).toMap ++
//          aggregates.zipWithIndex.map( az => az._1._1 -> (az._2 + op.getAggregationCriteria.size()).asInstanceOf[Integer])
//        val projectionExpressions = op.getInternalElements.map( e => ExpressionParser.parseValue(e.getExpression, projectionVariableLookup))
//        newLocal(Props(new AggregationNode(expr.child, aggregationCriteria.map(_._2), functions, projectionExpressions)))
//    }

    private def selection(op: Selection, expr: ForwardConnection) = {
      val variableLookup = getSchema(op.child)
      newLocal(Props(new SelectionNode(expr.child, ExpressionParser.parse(op.jnode.condition, variableLookup))))
    }

    private def projection(op: Projection, expr: ForwardConnection) = {
      val lookup = getSchema(op.child)
      val expressions = op.internalSchema.map(e => ExpressionParser.parseValue(e, lookup)).toVector
      newLocal(Props(new ProjectionNode(expr.child, expressions)))
    }

    private def sortAndTop(op: SortAndTop, expr: ForwardConnection) = {
      val variableLookup = getSchema(op.child)

      // This is the mighty EMF, so there are no default values, obviously
      def getInt(e: Expression) = ExpressionParser.parseValue(e, variableLookup)(Vector()).asInstanceOf[Long]

      val skip: Long = getInt(op.jnode.skipExpr)
      val limit: Long = getInt(op.jnode.limitExpr)
      val sortKeys = op.jnode.order.map(
        e => ExpressionParser.parseValue(e, variableLookup)).toVector
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
      val schema = getSchema(op.child)
      val indices = op.jnode.edges.map(v => schema(v.name))

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

    private def vertexCreator(v: VertexAttribute, lookup: Map[String, Int]): (Tuple) => IngraphVertex = {
      val props: Tuple => Map[String, Any] = (t: Tuple) =>
        v.properties.map { case (name, expr) =>
          name -> ExpressionParser.parseValue(expr, lookup)(t)}
      (t: Tuple) =>
        indexer.addVertex(IngraphVertex(
          indexer.newId(), v.labels.vertexLabels, props(t)))
    }

    type AlreadyCreated = mutable.HashMap[Attribute, Long]
    private def createOrLookup(v: VertexAttribute, lookup: Map[String, Int]
                              ): (Tuple,AlreadyCreated) => Long = {
      val creator: (Tuple) => IngraphVertex = vertexCreator(v, lookup)
      lookup.get(v.name) match {
        case Some(index: Int) => (t: Tuple, m) => t(index).asInstanceOf[Long]
        case None => (t: Tuple, m: AlreadyCreated) => m.get(v) match {
          case Some(index: Long) => index
          case None =>
            val id = creator(t).id
            m(v) = id
            id
        }
      }
    }

    private def create(op: Create, indexer: Indexer, expr: ForwardConnection) = {
      val lookup = getSchema(op.child)
      val creatorDefs: Seq[Attribute] = op.jnode.attributes
      val creatorFunctions: Seq[(Tuple, AlreadyCreated) => Any] = for (element <- creatorDefs)
        yield element match {
          case n: NavigationDescriptor =>
            // you've got to love the Law of Demeter
            val demeter = n.edge.labels.edgeLabels.head
            val sourceIndex = createOrLookup(n.src, lookup)
            val targetIndex = createOrLookup(n.trg, lookup)
            (t: Tuple, m: AlreadyCreated) => {
              indexer.addEdge(
                indexer.newId(),
                sourceIndex(t, m),
                targetIndex(t, m),
                demeter)
            }
          case variable: VertexAttribute =>
            val create = vertexCreator(variable, lookup)
            (t: Tuple, m: AlreadyCreated) =>
              if (!m.contains(variable))
                create(t)
        }

      (m: ReteMessage) => {
        m match {
          case cs: ChangeSet => cs.positive.foreach(
            tuple => creatorFunctions.foldLeft(new mutable.HashMap[Attribute, Long]()) { case (map, function) =>
                function(tuple, map)
                map
            })

          case _ =>
        }
        expr.child(m)
      }
    }

    private def delete(op: Delete, indexer: Indexer, expr: ForwardConnection) = {
      val lookup = getSchema(op.child)
      if (op.jnode.detach) {
        // detach delete not supported yet
        // and we should throw an exception in case the user tries to delete a nodes with existing
        // relationships
        // "To delete this node, you must first delete its relationships."
        ???
      }
      val removals: Seq[(Tuple) => Unit] = for (element <- op.jnode.attributes)
      // .getExpression.asInstanceOf[VariableExpression].getVariable TODO check
        yield element match {
          case e: EdgeAttribute =>
            (t: Tuple) => indexer.removeEdgeById(t(lookup(e.name)).asInstanceOf[Long])
          case v: VertexAttribute =>
            (t: Tuple) => indexer.removeVertexById(t(lookup(v.name)).asInstanceOf[Long], detach = true)
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

