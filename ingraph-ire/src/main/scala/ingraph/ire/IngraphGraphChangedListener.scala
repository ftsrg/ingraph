package ingraph.ire

import java.util

import com.tinkerpop.blueprints.util.wrappers.event.listener.GraphChangedListener
import hu.bme.mit.ire.Transaction
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.ChangeSet
import org.apache.tinkerpop.gremlin.structure.{Edge, Element, Vertex}
import relalg.{AttributeVariable, GetEdgesOperator, Variable, VertexVariable}

import scala.collection.mutable

class IngraphGraphChangedListener(
                                  vertexConverters: Map[String, mutable.Set[Tuple2[String, Vector[String]]]],
                                  edgeConverters: Map[String, mutable.Set[GetEdgesOperator]],
                                  inputLookup: Map[String, (ChangeSet) => Unit]
                                ) extends GraphChangedListener with IdParser {
  val vertices = mutable.HashMap[String, Set[Tuple]]()
  val edges = mutable.HashMap[String, Set[Tuple]]()

  override def idParser(obj: Any): Any = obj
  var transaction: Transaction = _
  import Conversions._

  def elementToNode(element: Element, required: Vector[String]): Tuple =
    Vector(idParser(element.id)) ++
      required.tail.map(key => element.value(key).asInstanceOf[Any])

  def edgeTypeFilter(edge: Edge, operator: GetEdgesOperator): Boolean = {
    if (operator.getSourceVertexVariable.getVertexLabelSet != null) {
      val sourceNames = operator.getSourceVertexVariable.getVertexLabelSet.getVertexLabels.map(f => f.getName)
      if (!sourceNames.contains(edge.outVertex().label())) return false
    }
    if (operator.getTargetVertexVariable.getVertexLabelSet != null) {
      val targetNames = operator.getTargetVertexVariable.getVertexLabelSet.getVertexLabels.map(f => f.getName)
      if (!targetNames.contains(edge.inVertex().label())) return false
    }
    true
  }

  def edgeToTupleType(edge: Edge, operator: GetEdgesOperator): Tuple = {
    Vector(idParser(edge.outVertex.id), idParser(edge.id), idParser(edge.inVertex.id)) ++
      operator.getFullSchema.drop(3).map(
      a =>
        if (a.asInstanceOf[AttributeVariable].getElement == operator.getSourceVertexVariable)
          edge.outVertex().value(a.getName).asInstanceOf[Any]
        else
          edge.inVertex().value(a.getName).asInstanceOf[Any]
    )
  }

  override def vertexAdded(vertex: Vertex): Unit = {
    for (set <- vertexConverters.get(vertex.label);
    (nick, vector) <- set)
      transaction.add(nick, elementToNode(vertex, vector))
  }

  override def edgeAdded(edge: Edge): Unit = {
    for (set <- edgeConverters.get(edge.label);
         operator <- set)
      if (edgeTypeFilter(edge, operator))
        transaction.add(operator.getEdgeVariable.getName, edgeToTupleType(edge, operator))
  }

  override def vertexPropertyChanged(vertex: Vertex, key: String, oldValue: scala.Any, setValue: scala.Any): Unit = ???

  override def vertexPropertyRemoved(vertex: Vertex, key: String, removedValue: scala.Any): Unit = ???

  override def vertexRemoved(vertex: Vertex, props: util.Map[String, AnyRef]): Unit = ???

  override def edgePropertyChanged(edge: Edge, key: String, oldValue: scala.Any, setValue: scala.Any): Unit = ???

  override def edgePropertyRemoved(edge: Edge, key: String, removedValue: scala.Any): Unit = ???

  override def edgeRemoved(edge: Edge, props: util.Map[String, AnyRef]): Unit = ???
}
