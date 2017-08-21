package ingraph.ire

import hu.bme.mit.ire.util.BufferMultimap
import org.neo4j.driver.v1.types.{Node, Relationship}

import java.util.{Iterator => JIterator}

import scala.collection.JavaConversions._
import scala.collection.mutable
import scala.util.Random

trait IngraphElement {
  def properties: Map[String, Any]
}

case class IngraphVertex(id: Long,
                         labels: Set[String],
                         override val properties: Map[String, Any] = Map()) extends IngraphElement {
  val edgesOut: BufferMultimap[String, IngraphEdge] = new BufferMultimap[String, IngraphEdge]()
  val edgesIn: BufferMultimap[String, IngraphEdge] = new BufferMultimap[String, IngraphEdge]()

  def edgesOutByTypeJavaIterator(key: String): JIterator[IngraphEdge] = edgesOut.get(key).iterator.flatten
  def edgesOutJavaIterator: JIterator[IngraphEdge] = edgesOut.valuesIterator.flatten

  def edgesInByTypeJavaIterator(key: String): JIterator[IngraphEdge] = edgesIn.get(key).iterator.flatten
  def edgesInJavaIterator: JIterator[IngraphEdge] = edgesIn.valuesIterator.flatten

  override def toString: String = s"Vertex($id, $properties)"
}

case class IngraphEdge(id: Long,
                       sourceVertex: IngraphVertex,
                       targetVertex: IngraphVertex,
                       `type`: String,
                       override val properties: Map[String, Any] = Map()) extends IngraphElement {
  override def toString: String = s"Edge(${sourceVertex.id} -[${`type`}]-> ${targetVertex.id}, $properties)"
  def inverse(): IngraphEdge = IngraphEdge(id, targetVertex, sourceVertex, `type`, properties)
}

class Indexer {

  val vertexLookup = mutable.HashMap[Long, IngraphVertex]()
  val edgeLookup = mutable.HashMap[Long, IngraphEdge]()
  val vertexLabelLookup = new BufferMultimap[String, IngraphVertex]()
  val edgeTypeLookup = new BufferMultimap[String, IngraphEdge]()
  val mappers = mutable.Buffer[EntityToTupleMapper]()

  def clear(): Unit = {
    vertexLookup.clear()
    vertexLabelLookup.clear()
    edgeTypeLookup.clear()
    mappers.clear()
  }

  def fill(tupleMapper: EntityToTupleMapper): Unit = {
    for (vertex <- vertexLookup.values) {
      tupleMapper.addVertex(vertex)
    }
    for (vertexList <- edgeTypeLookup.values;
         vertex <- vertexList) {
      tupleMapper.addEdge(vertex)
    }
  }

  def subscribe(tupleMapper: EntityToTupleMapper): Unit = {
    mappers += tupleMapper
    fill(tupleMapper)
  }

  def addVertex(node: Node): IngraphVertex = {
    val id: Long = node.id()
    val properties: Map[String, Any]  = node.asMap().toMap
    val labels = node.labels().toSet
    val vertex = IngraphVertex(id, labels, properties)
    addVertex(vertex)
  }

  def addVertex(vertex: IngraphVertex): IngraphVertex = {
    for (label <- vertex.labels)
      vertexLabelLookup.addBinding(label, vertex)
    vertexLookup(vertex.id) = vertex
    mappers.foreach(_.addVertex(vertex))
    vertex
  }

  def removeVertexById(id: Long): Unit = {
    val vertex = vertexLookup(id)
    vertex.labels.foreach(label => vertexLabelLookup.removeBinding(label, vertex))
    vertexLookup.remove(id)
    mappers.foreach(_.removeVertex(vertex))
  }

  def addEdge(relation: Relationship): IngraphEdge = {
    val id: Long = relation.id()
    val properties: Map[String, Any] = relation.asMap().toMap
    val sourceVertex: IngraphVertex = vertexLookup(relation.startNodeId())
    val targetVertex: IngraphVertex = vertexLookup(relation.endNodeId())
    val label: String = relation.`type`()
    val edge = IngraphEdge(id, sourceVertex, targetVertex, label, properties)
    addEdge(edge)
  }

  def addEdge(edge: IngraphEdge): IngraphEdge = {
    edgeLookup(edge.id) = edge
    edge.sourceVertex.edgesOut.addBinding(edge.`type`, edge)
    edge.targetVertex.edgesIn.addBinding(edge.`type`, edge)
    edgeTypeLookup.addBinding(edge.`type`, edge)
    mappers.foreach(_.addEdge(edge))
    edge
  }

  def addEdge(id: Long, sourceId: Long, targetId: Long, label: String): IngraphEdge = {
    addEdge(IngraphEdge(id, vertexLookup(sourceId), vertexLookup(targetId), label, Map.empty))
  }

  def removeEdgeById(id: Long): Unit = {
    val edge = edgeLookup(id)
    mappers.foreach(_.removeEdge(edge))
    edgeTypeLookup.removeBinding(edge.`type`, edge)
    edgeLookup.remove(id)
  }

  def vertexById(id: Long): Option[IngraphVertex] = vertexLookup.get(id)
  def edgeById(id: Long): Option[IngraphEdge] = edgeLookup.get(id)
  def vertices(): Iterator[IngraphVertex] = vertexLabelLookup.valuesIterator.flatten
  def verticesJava(): JIterator[IngraphVertex] = vertices()
  def verticesByLabel(label: String): Iterator[IngraphVertex] = vertexLabelLookup.getOrElse(label, Seq()).iterator
  def verticesByLabelJava(label: String): JIterator[IngraphVertex] = verticesByLabel(label)
  def edges(): Iterator[IngraphEdge] = edgeLookup.valuesIterator
  def edgesJava(): JIterator[IngraphEdge] = edges()
  def edgesByType(label: String): Iterator[IngraphEdge] = edgeTypeLookup.getOrElse(label, Seq()).iterator
  def edgesByTypeJava(label: String): JIterator[IngraphEdge] = edgesByType(label)
  def getNumberOfVerticesWithLabel(label: String): Int =  vertexLabelLookup.get(label).map(_.size).getOrElse(0)
  def getNumberOfEdgesWithLabel(label: String): Int = edgeTypeLookup.get(label).map(_.size).getOrElse(0)

  val rnd = new Random(1)
  def newId(): Long = {
    var id = rnd.nextLong()
    while (edgeLookup.contains(id) || vertexLookup.contains(id)) {
      id = rnd.nextLong()
    }
    id
  }
}
