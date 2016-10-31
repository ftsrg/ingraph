package ingraph.ire

import java.util

import com.tinkerpop.blueprints.util.wrappers.event.listener.GraphChangedListener
import hu.bme.mit.ire.{ChangeSet, TupleType}
import ingraph.ire.EngineFactory.EdgeTransformer
import org.apache.tinkerpop.gremlin.structure.{Edge, Element, Vertex}

import scala.collection.JavaConversions._
import scala.collection.mutable

class IngraphGraphChangedListener(
                                  vertexConverters: Map[String, mutable.Set[String]],
                                  edgeConverters: Map[String, mutable.Set[EdgeTransformer]],
                                  inputLookup: Map[String, (ChangeSet) => Unit]
                                ) extends GraphChangedListener {
  val vertices = mutable.HashMap[String, Set[TupleType]]()
  val edges = mutable.HashMap[String, Set[TupleType]]()

  def elementToNode(element: Element, nick: String): TupleType =
    Map[Any,Any](nick -> element.id) ++
      element.keys.map(k => s"${nick}_$k" -> element.value(k))

  def edgeToTupleType(edge: Edge, transformer: EdgeTransformer): TupleType =
    elementToNode(edge, transformer.nick) +
      (transformer.source -> edge.outVertex.id) + (transformer.target -> edge.inVertex.id) ++
      elementToNode(edge.outVertex, transformer.source) ++
      elementToNode(edge.inVertex, transformer.target)

  override def vertexAdded(vertex: Vertex): Unit = {
    for (nickSet <- vertexConverters.get(vertex.label);
         nick <- nickSet)
      inputLookup(nick)(ChangeSet(positive= Vector(elementToNode(vertex, nick))))
  }

  override def edgeAdded(edge: Edge): Unit = {
    for (transformerSet <- edgeConverters.get(edge.label);
         transformer <- transformerSet)
      inputLookup(transformer.nick)(ChangeSet(positive= Vector(edgeToTupleType(edge, transformer))))
  }

  override def vertexPropertyChanged(vertex: Vertex, key: String, oldValue: scala.Any, setValue: scala.Any): Unit = ???

  override def vertexPropertyRemoved(vertex: Vertex, key: String, removedValue: scala.Any): Unit = ???

  override def vertexRemoved(vertex: Vertex, props: util.Map[String, AnyRef]): Unit = ???

  override def edgePropertyChanged(edge: Edge, key: String, oldValue: scala.Any, setValue: scala.Any): Unit = ???

  override def edgePropertyRemoved(edge: Edge, key: String, removedValue: scala.Any): Unit = ???

  override def edgeRemoved(edge: Edge, props: util.Map[String, AnyRef]): Unit = ???
}
