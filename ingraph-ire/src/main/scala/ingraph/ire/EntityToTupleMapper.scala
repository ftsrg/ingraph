package ingraph.ire

import java.util

import hu.bme.mit.ire.Transaction
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.ChangeSet

import relalg._

import scala.collection.mutable

class EntityToTupleMapper(vertexConverters: Map[Set[String], Set[GetVerticesOperator]],
                          edgeConverters: Map[String, Set[GetEdgesOperator]],
                          inputLookup: Map[String, (ChangeSet) => Unit]) extends IdParser {
  private val vertexLookup: Map[String, (Set[String], Set[GetVerticesOperator])] = for ((labels, operators) <- vertexConverters;
                                                                                        label <- labels)
    yield label -> (labels, operators)

  override def idParser(obj: Any): Any = obj

  import Conversions._

  var transaction: Transaction = _

  def elementToNode(element: IngraphVertex, required: Vector[String]): Tuple =
    Vector(idParser(element.id)) ++
      required.tail.map(key => element.properties(key).asInstanceOf[Any])

  def edgeToTupleType(edge: IngraphEdge, operator: GetEdgesOperator): Tuple = {
    Vector(idParser(edge.sourceVertex.id), idParser(edge.id), idParser(edge.targetVertex.id)) ++
      operator.getFullSchema.drop(3).map(a => {
        val element = a.asInstanceOf[AttributeVariable].getElement
        element match {
          case _ if element == operator.getSourceVertexVariable => edge.sourceVertex.properties(a.getName).asInstanceOf[Any]
          case _ if element == operator.getTargetVertexVariable => edge.targetVertex.properties(a.getName).asInstanceOf[Any]
          case _ if element == operator.getEdgeVariable => edge.properties(a.getName).asInstanceOf[Any]
        }
      }
      )
  }

  def addEdge(edge: IngraphEdge): Unit = {
    for (operators <- edgeConverters.get(edge.label); operator <- operators) {
      val sourceLabels = operator.getSourceVertexVariable.getVertexLabelSet.getVertexLabels.map(_.getName).toSet
      val targetLabels = operator.getTargetVertexVariable.getVertexLabelSet.getVertexLabels.map(_.getName).toSet
      if (sourceLabels.subsetOf(edge.sourceVertex.labels) &&
        targetLabels.subsetOf(edge.targetVertex.labels)) {
        val tuple = edgeToTupleType(edge, operator)
        transaction.add(operator.getEdgeVariable.getName, tuple)
      }
    }
  }

  def addVertex(vertex: IngraphVertex): Unit = {
    vertexLookup.get(vertex.labels.head).foreach(
     f => {
       val labels = f._1
       val operators = f._2
        if (labels.subsetOf(vertex.labels)) {
          for (operator <- operators) {
            val tuple = elementToNode(vertex, operator.getFullSchema.map(_.getName))
            transaction.add(operator.getVertexVariable.getName, tuple)
          }
        }
      }
      )

  }
}
