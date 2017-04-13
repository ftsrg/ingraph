package ingraph.ire

import hu.bme.mit.ire.Transaction
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.ChangeSet
import ingraph.expressionparser.Conversions._
import relalg._
import ingraph.relalg.expressions.ExpressionUnwrapper

class EntityToTupleMapper(vertexConverters: Map[Set[String], Set[GetVerticesOperator]],
                          edgeConverters: Map[String, Set[GetEdgesOperator]],
                          inputLookup: Map[String, (ChangeSet) => Unit]) extends IdParser {
  private val vertexLookup: Map[String, (Set[String], Set[GetVerticesOperator])] = for ((labels, operators) <- vertexConverters;
                                                                                        label <- labels)
    yield label -> (labels, operators)

  override def idParser(obj: Any): Any = obj

  var transaction: Transaction = _

  def elementToNode(element: IngraphVertex, required: Vector[String]): Tuple =
    Vector(idParser(element.id)) ++
      required.tail.map(key => element.properties(key).asInstanceOf[Any])

  def edgeToTupleType(edge: IngraphEdge, operator: GetEdgesOperator): Tuple = {
    Vector(idParser(edge.sourceVertex.id), idParser(edge.id), idParser(edge.targetVertex.id)) ++
      operator.getInternalSchema.drop(3)
        .map(a => {
        val element = ExpressionUnwrapper.extractBaseVariable(a.asInstanceOf[AttributeVariable])
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
    vertex.labels.find(vertexLookup.contains).foreach(
      f => {
        val (labels, operators) = vertexLookup(f)
        if (labels.subsetOf(vertex.labels)) {
          for (operator <- operators) {
            val tuple = elementToNode(vertex, operator.getInternalSchema.map(_.getName))
            transaction.add(operator.getVertexVariable.getName, tuple)
          }
        }
      })
  }
}
