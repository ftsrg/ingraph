package ingraph.ire

import akka.actor.{ActorRef, Props}
import hu.bme.mit.ire._
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}
import hu.bme.mit.ire.nodes.binary.{AntiJoinNode, JoinNode, LeftOuterJoinNode}
import hu.bme.mit.ire.nodes.unary._
import hu.bme.mit.ire.nodes.unary.aggregation.AggregationNode
import hu.bme.mit.ire.trainbenchmark.TrainbenchmarkQuery
import hu.bme.mit.ire.util.BufferMultimap
import hu.bme.mit.ire.util.Utils.conversions._
import ingraph.expressionparser.Conversions._
import ingraph.expressionparser.ExpressionParser
import ingraph.relalg.util.SchemaToMap
import relalg._

import scala.collection.mutable

object EngineFactory {

  val schemaToMap = new SchemaToMap()
  import scala.collection.JavaConverters._
  def getSchema(operator: Operator): Map[Variable, Integer] = schemaToMap.schemaToMap(operator).asScala.toMap

  case class ForwardConnection(parent: Operator, child: (ReteMessage) => Unit)
  case class EdgeTransformer(nick: String, source:String, target: String)

  def createQueryEngine(plan: Operator) =
    new TrainbenchmarkQuery {
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
            val node: ActorRef = op match {
            case op: ProductionOperator => production
            case op: GroupingAndProjectionOperator =>
              val variableLookup = getSchema(op.getInput)
              val functions = () => op.getAggregations.map(
                e => ExpressionParser.parseAggregate(e.getExpression, variableLookup)).map(
                _() // GOOD LUCK UNDERSTANDING THIS
              )
              val mask = op.getElements.map(e => ExpressionParser.parseValue(e.getExpression, variableLookup))
              newLocal(Props(new AggregationNode(expr.child, mask, functions)))

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
                  op.getFullSchema.length,
                  sortKeys,
                  skip,
                  limit,
                  op.getEntries.map(_.getDirection == OrderDirection.ASCENDING) // WHAT THE FUCK
              )))

            case op: SortOperator =>
              val variableLookup = getSchema(op.getInput)
              val sortKeys = op.getEntries.map(
                e=> ExpressionParser.parseValue(e.getExpression, variableLookup))
              newLocal(Props(new SortNode(
                expr.child,
                op.getFullSchema.length,
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
              val mask = op.getTupleIndices
              newLocal(Props(new ProjectionNode(expr.child, mask)))
            case op: DuplicateEliminationOperator => newLocal(Props(new DuplicateEliminationNode(expr.child)))
            case op: AllDifferentOperator =>
              val schema = getSchema(op.getInput)
              val indices = op.getEdgeVariables.map(v => schema(v))
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
                newLocal(Props(new AntiJoinNode(expr.child, emfToInt(op.getLeftMask), emfToInt(op.getRightMask))))
              case op: JoinOperator =>
                val leftMask = emfToInt(op.getLeftMask)
                val rightMask = emfToInt(op.getRightMask)
                newLocal(Props(new JoinNode(
                    expr.child,
                    op.getLeftInput.getFullSchema.length,
                    op.getRightInput.getFullSchema.length,
                    leftMask,
                    rightMask
                )))
              case op: LeftOuterJoinOperator =>
                val leftMask = emfToInt(op.getLeftMask)
                val rightMask = emfToInt(op.getRightMask)
                newLocal(Props(new LeftOuterJoinNode(
                  expr.child,
                  op.getLeftInput.getFullSchema.length,
                  op.getRightInput.getFullSchema.length,
                  leftMask,
                  rightMask
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
        }
      }

      override val inputLookup: Map[String, (ChangeSet) => Unit] = inputs.toMap
      override val terminator: Terminator = Terminator(inputs.values, production)
    }
}
