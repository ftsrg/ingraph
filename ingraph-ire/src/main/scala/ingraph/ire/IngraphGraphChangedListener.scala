package ingraph.ire

import java.util

import com.tinkerpop.blueprints.util.wrappers.event.listener.GraphChangedListener
import hu.bme.mit.ire.Transaction
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.ChangeSet
import org.apache.tinkerpop.gremlin.structure.{Edge, Element, Vertex}
import relalg.GetEdgesOperator

import scala.collection.JavaConversions._
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

  def elementToNode(element: Element, required: Vector[String]): Tuple =
    Vector(idParser(element.id)) ++
      required.map(key => element.property(key))

  def edgeToTupleType(edge: Edge, operator: GetEdgesOperator): Tuple = {
    val nick = operator.getEdgeVariable.getName
    operator.getSourceVertexVariable
    println(Vector(edge.inVertex.id, edge.id, edge.outVertex().id) ++ operator.getDetailedSchema.map(variable => variable.getName))
    Vector()
  }

  override def vertexAdded(vertex: Vertex): Unit = {
    for (set <- vertexConverters.get(vertex.label);
    (nick, vector) <- set)
      transaction.add(nick, elementToNode(vertex, vector))
  }

  override def edgeAdded(edge: Edge): Unit = {
    for (set <- edgeConverters.get(edge.label);
         operator <- set)
      transaction.add(operator.getEdgeVariable.getName, edgeToTupleType(edge, operator))
  }

  override def vertexPropertyChanged(vertex: Vertex, key: String, oldValue: scala.Any, setValue: scala.Any): Unit = ???

  override def vertexPropertyRemoved(vertex: Vertex, key: String, removedValue: scala.Any): Unit = ???

  override def vertexRemoved(vertex: Vertex, props: util.Map[String, AnyRef]): Unit = ???

  override def edgePropertyChanged(edge: Edge, key: String, oldValue: scala.Any, setValue: scala.Any): Unit = ???

  override def edgePropertyRemoved(edge: Edge, key: String, removedValue: scala.Any): Unit = ???

  override def edgeRemoved(edge: Edge, props: util.Map[String, AnyRef]): Unit = ???
}
