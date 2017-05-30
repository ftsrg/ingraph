package ingraph.ire

import akka.actor.{ActorRef, Props}
import hu.bme.mit.ire._
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}
import hu.bme.mit.ire.nodes.binary.{AntiJoinNode, JoinNode, LeftOuterJoinNode}
import hu.bme.mit.ire.nodes.unary._
import hu.bme.mit.ire.nodes.unary.aggregation.AggregationNode
import hu.bme.mit.ire.util.BufferMultimap
import hu.bme.mit.ire.util.Utils.conversions._
import ingraph.expressionparser.Conversions._
import ingraph.expressionparser.ExpressionParser
import ingraph.relalg.util.SchemaToMap
import relalg._


import scala.collection.{immutable, mutable}
import hu.bme.mit.ire.engine.RelationalEngine

import scala.collection.mutable
import hu.bme.mit.ire.nodes.binary.TransitiveClosureJoinNode

object EngineFactory {

  val schemaToMap = new SchemaToMap()
  import scala.collection.JavaConverters._
  def getSchema(operator: Operator): Map[String, Integer] = schemaToMap.schemaToMapNames(operator).asScala.toMap

  case class ForwardConnection(parent: Operator, child: (ReteMessage) => Unit)
  case class EdgeTransformer(nick: String, source:String, target: String)

  def createQueryEngine(plan: Operator, indexer: Indexer) =
    new RelationalEngine {
      override val production = system.actorOf(Props(new ProductionNode("")))
      val remaining: mutable.ArrayBuffer[ForwardConnection] = mutable.ArrayBuffer()
      val inputs: mutable.HashMap[String, (ReteMessage) => Unit] = mutable.HashMap()

      val vertexConverters = new BufferMultimap[Vector[String], GetVerticesOperator]
      val edgeConverters  = new BufferMultimap[String, GetEdgesOperator]

      remaining += ForwardConnection(plan, production)

      while (remaining.nonEmpty) {
        val expr = remaining.remove(0)
        expr.parent match {
          case op: UnaryOperator =>
            val node: (ReteMessage) => Unit = op match {
            case op: ProductionOperator => production
            case op: GroupingOperator =>
              val variableLookup = getSchema(op.getInput)
              val aggregates = op.getElements.flatMap(
                e => ExpressionParser.parseAggregate(e.getExpression, variableLookup)
              )
              val functions = () => aggregates.map(
                _._2() // GOOD LUCK UNDERSTANDING THIS
              )
              val aggregationCriteria = op.getAggregationCriteria.map(e => (e, ExpressionParser.parseValue(e, variableLookup)))
              val projectionVariableLookup: Map[String, Integer] =
                aggregationCriteria.zipWithIndex.map( a => a._1._1.toString -> a._2.asInstanceOf[Integer] ).toMap ++
                aggregates.zipWithIndex.map( az => az._1._1 -> (az._2 + op.getAggregationCriteria.size()).asInstanceOf[Integer])
              val projectionExpressions = op.getInternalElements.map( e => ExpressionParser.parseValue(e.getExpression, projectionVariableLookup))
              newLocal(Props(new AggregationNode(expr.child, aggregationCriteria.map(_._2), functions, projectionExpressions)))
            case op: SortAndTopOperator =>
              val variableLookup = getSchema(op.getInput)
              // This is the mighty EMF, so there are no default values, obviously
              def getInt(e: Expression) = ExpressionParser.parseValue(e, variableLookup)(Vector()).asInstanceOf[Int]
              val skip = if (op.getSkip == null) 0 else getInt(op.getSkip)
              val limit = if (op.getLimit == null) 0 else getInt(op.getLimit)
              val sortKeys = op.getEntries.map(
                e=> ExpressionParser.parseValue(e.getExpression, variableLookup))
              newLocal(Props(new SortAndTopNode(
                  expr.child,
                  op.getInternalSchema.length,
                  sortKeys,
                  skip,
                  limit,
                  op.getEntries.map(_.getDirection == OrderDirection.ASCENDING)
              )))

            case op: SortOperator =>
              val variableLookup = getSchema(op.getInput)
              val sortKeys = op.getEntries.map(
                e => ExpressionParser.parseValue(e.getExpression, variableLookup))
              newLocal(Props(new SortNode(
                expr.child,
                op.getInternalSchema.length,
                sortKeys,
                op.getEntries.map(_.getDirection == OrderDirection.ASCENDING) // WHAT THE FUCK
              )))

            case op: TopOperator =>
              throw new IllegalStateException("Incremental query plan should not contain TopOperators, only SortAndTopOperators are allowed.")

            case op: SelectionOperator =>
              val variableLookup = getSchema(op.getInput)
              newLocal(Props(new SelectionNode(expr.child, ExpressionParser.parse(op.getCondition, variableLookup))))

            case op: ProjectionOperator =>
              val lookup = getSchema(op.getInput)
              val expressions = op.getInternalElements.map( e => ExpressionParser.parseValue(e.getExpression, lookup))
              newLocal(Props(new ProjectionNode(expr.child, expressions)))
            case op: DuplicateEliminationOperator => newLocal(Props(new DuplicateEliminationNode(expr.child)))
            case op: AllDifferentOperator =>
              val schema = getSchema(op.getInput)
              val indices = op.getEdgeVariables.map(v => schema(v.toString))
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
            case op: CreateOperator =>
              val lookup = getSchema(op.getInput)
              val creations: Seq[(datatypes.Tuple) => IngraphEdge] = (for (element <- op.getElements)
                yield element.asInstanceOf[ExpressionVariable].getExpression match {
                  case n: NavigationDescriptor =>
                    val demeter = n.getEdgeVariable.getEdgeLabelSet.getEdgeLabels.get(0).getName
                    val sourceIndex = lookup(n.getSourceVertexVariable.getName)
                    val targetIndex = lookup(n.getTargetVertexVariable.getName)
                    Some((t: Tuple) => {
                      indexer.addEdge(
                        indexer.newId(),
                        t(sourceIndex).asInstanceOf[Long],
                        t(targetIndex).asInstanceOf[Long],
                        demeter)
                    })
                  case _ => None
                }).flatten

              (m: ReteMessage) => {
                m match {
                  case cs: ChangeSet => creations.foreach(r => cs.positive.foreach(r(_)))
                  case _ =>
                }
                expr.child(m)
              }
            case op: DeleteOperator =>
              val lookup = getSchema(op.getInput)
              val removals: Seq[(Tuple) => Unit] = for (element <- op.getElements)
                yield element.getExpression.asInstanceOf[VariableExpression].getVariable match {
                  case e: EdgeVariable =>
                    (t: Tuple) => indexer.removeEdgeById(t(lookup(e.getName)).asInstanceOf[Long])
                  case v: VertexVariable =>
                    (t: Tuple) => indexer.removeVertexById(t(lookup(v.getName)).asInstanceOf[Long])
                }
              (m: ReteMessage) => {
                m match {
                  case cs: ChangeSet => removals.foreach(r => cs.positive.foreach(r(_)))
                  case _ =>
                }
                expr.child(m)
              }
            }
            remaining += ForwardConnection(op.getInput, node)

          case op: BinaryOperator =>
            val node: ActorRef = op match {
              case op: AntiJoinOperator =>
                newLocal(Props(new AntiJoinNode(expr.child, emfToInt(op.getLeftMask), emfToInt(op.getRightMask))))
              case op: JoinOperator =>
                val leftMask = emfToInt(op.getLeftMask)
                val rightMask = emfToInt(op.getRightMask)
                newLocal(Props(new JoinNode(
                    expr.child,
                    op.getLeftInput.getInternalSchema.length,
                    op.getRightInput.getInternalSchema.length,
                    leftMask,
                    rightMask
                )))
              case op: LeftOuterJoinOperator =>
                val leftMask = emfToInt(op.getLeftMask)
                val rightMask = emfToInt(op.getRightMask)
                newLocal(Props(new LeftOuterJoinNode(
                  expr.child,
                  op.getLeftInput.getInternalSchema.length,
                  op.getRightInput.getInternalSchema.length,
                  leftMask,
                  rightMask
                )))
              case op: TransitiveClosureJoinOperator =>
                val leftMask = emfToInt(op.getLeftMask)
                val rightMask = emfToInt(op.getRightMask)
                val minHops = op.getEdgeListVariable.getMinHops
                val maxHops = op.getEdgeListVariable.getMaxHops.getMaxHopsType match {
                  case MaxHopsType.LIMITED => op.getEdgeListVariable.getMaxHops.getHops
                  case MaxHopsType.UNLIMITED => Long.MaxValue
                }
                newLocal(Props(new TransitiveClosureJoinNode(
                  expr.child,
                  op.getLeftInput.getInternalSchema.length,
                  op.getRightInput.getInternalSchema.length,
                  leftMask,
                  rightMask,
                  minHops,
                  maxHops
                )))
            }
            remaining += ForwardConnection(op.getLeftInput, node.primary)
            remaining += ForwardConnection(op.getRightInput, node.secondary)

          case op: GetVerticesOperator =>
            val nick = op.getVertexVariable.getName
            val labels = op.getVertexVariable.getVertexLabelSet.getVertexLabels.map(_.getName)
            vertexConverters.addBinding(labels, op)
            inputs += (nick -> expr.child)
          case op: GetEdgesOperator =>
            val nick = op.getEdgeVariable.getName
            val labels = op.getEdgeVariable.getEdgeLabelSet.getEdgeLabels.map(_.getName)
            for (label <- labels)
              edgeConverters.addBinding(label, op)
            inputs += (nick -> expr.child)
          case op: DualObjectSourceOperator =>
            inputs += ("" -> expr.child)
            expr.child(ChangeSet(positive=Vector(Vector())))

        }
      }

      override val inputLookup: Map[String, (ChangeSet) => Unit] = inputs.toMap
      override val terminator: Terminator = Terminator(inputs.values, production)
    }
}
