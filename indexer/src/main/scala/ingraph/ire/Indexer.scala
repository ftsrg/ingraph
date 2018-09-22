package ingraph.ire


import com.google.common.collect.HashMultimap
import ingraph.bulkloader.csv.data.{CsvEdge, CsvVertex}

import scala.collection.JavaConversions._
import scala.collection.mutable
import scala.util.Random

trait IngraphElement {
  def properties: Map[String, Any]
}

case class IngraphVertex(id: Long,
                         labels: Set[String],
                         override val properties: Map[String, Any] = Map()) extends IngraphElement {
  val edgesOut: mutable.Buffer[IngraphEdge] = mutable.Buffer[IngraphEdge]()
  val edgesIn: mutable.Buffer[IngraphEdge] = mutable.Buffer[IngraphEdge]()

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

  val mappers = mutable.Buffer[TTupleCreator]()

  val vertexLookup = mutable.HashMap[Long, IngraphVertex]()
  val vertexLabelLookup = HashMultimap.create[String, IngraphVertex]()
  val vertexIdLabelLookup = mutable.HashMap[(Long, String), IngraphVertex]()

  val edgeLookup = mutable.HashMap[Long, IngraphEdge]()
  val edgeTypeLookup = HashMultimap.create[String, IngraphEdge]()

  def fill(tupleMapper: TTupleCreator): Unit = {
    for (vertex <- vertexLookup.values) {
      tupleMapper.addVertex(vertex)
    }
    for (edge <- edgeLookup.values) {
      tupleMapper.addEdge(edge)
    }
  }

  def subscribe(tupleMapper: TTupleCreator): Unit = {
    mappers += tupleMapper
    fill(tupleMapper)
  }

  def addVertex(vertex: CsvVertex, labels: Traversable[String]): IngraphVertex = {
    val id: Long = vertex.getId
    val properties: Map[String, Any] = vertex.getProperties.toMap
    val allLabels = labels.toSet ++ vertex.getCustomLabels.toSet
    addVertex(IngraphVertex(id, allLabels, properties))
  }

  def addVertex(vertex: IngraphVertex): IngraphVertex = {
    for (label <- vertex.labels) {
      vertexLabelLookup.put(label, vertex)
      vertexIdLabelLookup((vertex.id, label)) = vertex
    }

    vertexLookup(vertex.id) = vertex
    mappers.foreach(_.addVertex(vertex))
    vertex
  }

  def removeVertexById(id: Long, detach: Boolean = false): Unit = {
    val vertex = vertexLookup(id)
    val edges = vertex.edgesOut.iterator() ++ vertex.edgesIn.iterator()
    if (!detach && edges.nonEmpty)
      throw new IllegalArgumentException("Won't remove connected vertex without DETACH")
    else if (detach)
      for { edge <- edges.toList } removeEdge(edge)
    for (label <- vertex.labels) {
      vertexLabelLookup.remove(label, vertex)
      vertexIdLabelLookup.remove((vertex.id, label))
    }
    vertexLookup.remove(id)
    mappers.foreach(_.removeVertex(vertex))
  }

  def addEdge(edge: CsvEdge, sourceVertexLabel: String, edgeType: String, targetVertexLabel: String): IngraphEdge = {
    val id: Long = edge.getId
    val properties: Map[String, Any] = edge.getProperties.toMap
    val sourceVertex: IngraphVertex = vertexIdLabelLookup((edge.getSourceVertexId, sourceVertexLabel))
    val targetVertex: IngraphVertex = vertexIdLabelLookup((edge.getTargetVertexId, targetVertexLabel))
    addEdge(IngraphEdge(id, sourceVertex, targetVertex, edgeType, properties))
  }

  def addEdge(edge: IngraphEdge): IngraphEdge = {
    edgeLookup(edge.id) = edge
    edge.sourceVertex.edgesOut.add(edge)
    edge.targetVertex.edgesIn.add(edge)
    edgeTypeLookup.put(edge.`type`, edge)
    val srcTgt = (edge.sourceVertex.id, edge.targetVertex.id)
    mappers.foreach(_.addEdge(edge))
    edge
  }

  def addEdge(id: Long, sourceId: Long, targetId: Long, `type`: String, props: Map[String, Any] = Map()): IngraphEdge = {
    addEdge(IngraphEdge(id, vertexLookup(sourceId), vertexLookup(targetId), `type`, props))
  }

  def removeEdgeById(id: Long): Unit = {
    val edge = edgeLookup(id)
    removeEdge(edge)
  }

  def removeEdge(edge: IngraphEdge): Unit = {
    mappers.foreach(_.removeEdge(edge))
    edgeTypeLookup.remove(edge.`type`, edge)
    edgeLookup.remove(edge.id)
    edge.sourceVertex.edgesOut.remove(edge)
    edge.targetVertex.edgesIn.remove(edge)
  }

  def vertexById(id: Long): Option[IngraphVertex] = vertexLookup.get(id)
  def vertexByIdLabel(id: Long, label: String): Option[IngraphVertex] = vertexIdLabelLookup.get((id, label))
  def verticesByLabel(label: String): Iterator[IngraphVertex] = vertexLabelLookup.get(label).iterator
  def vertices(): Iterator[IngraphVertex] = vertexLookup.valuesIterator

  def edges(): Iterator[IngraphEdge] = edgeLookup.valuesIterator
  def edgeById(id: Long): Option[IngraphEdge] = edgeLookup.get(id)
  def edgesByType(label: String): Iterator[IngraphEdge] = edgeTypeLookup.get(label).iterator

  val rnd = new Random(1)
  def newId(): Long = {
    var id = rnd.nextLong()
    while (edgeLookup.contains(id) || vertexLookup.contains(id)) {
      id = rnd.nextLong()
    }
    id
  }
}
