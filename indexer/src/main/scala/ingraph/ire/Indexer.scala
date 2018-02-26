package ingraph.ire

import hu.bme.mit.ire.util.BufferMultimap
import org.neo4j.driver.v1.types.{Node, Relationship}

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

  val mappers = mutable.Buffer[GraphElementToTupleMapper]()

  val vertexLookup = mutable.HashMap[Long, IngraphVertex]()
  val vertexLabelLookup = new BufferMultimap[String, IngraphVertex]()
  val vertexIdLabelLookup = mutable.HashMap[(Long, String), IngraphVertex]()

  val edgeLookup = mutable.HashMap[Long, IngraphEdge]()
  val edgeTypeLookup = new BufferMultimap[String, IngraphEdge]()
  val edgeSrcTgtTypeLookup = new BufferMultimap[((Long, Long), String), IngraphEdge]

  def fill(tupleMapper: GraphElementToTupleMapper): Unit = {
    for (vertex <- vertexLookup.values) {
      tupleMapper.addVertex(vertex)
    }
    for (edge <- edgeLookup.values) {
      tupleMapper.addEdge(edge)
    }
  }

  def subscribe(tupleMapper: GraphElementToTupleMapper): Unit = {
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
    for (label <- vertex.labels) {
      vertexLabelLookup.addBinding(label, vertex)
      vertexIdLabelLookup((vertex.id, label)) = vertex
    }

    vertexLookup(vertex.id) = vertex
    mappers.foreach(_.addVertex(vertex))
    vertex
  }

  def removeVertexById(id: Long, detach: Boolean = false): Unit = {
    val vertex = vertexLookup(id)
    val edges = vertex.edgesOut.valuesIterator.flatten ++ vertex.edgesIn.valuesIterator.flatten
    if (!detach && edges.nonEmpty)
      throw new IllegalArgumentException("Won't remove connected vertex without DETACH")
    else if (detach)
      for { edge <- edges } removeEdgeById(edge.id)
    vertex.labels.foreach(label => {
      vertexLabelLookup.removeBinding(label, vertex)
      vertexIdLabelLookup.remove((vertex.id, label))
    })
    vertexLookup.remove(id)
    mappers.foreach(_.removeVertex(vertex))
  }

  def addEdge(relation: Relationship): IngraphEdge = {
    val id: Long = relation.id()
    val properties: Map[String, Any] = relation.asMap().toMap
    val sourceVertex: IngraphVertex = vertexLookup(relation.startNodeId())
    val targetVertex: IngraphVertex = vertexLookup(relation.endNodeId())
    val `type`: String = relation.`type`()
    val edge = IngraphEdge(id, sourceVertex, targetVertex, `type`, properties)
    addEdge(edge)
  }

  def addEdge(edge: IngraphEdge): IngraphEdge = {
    edgeLookup(edge.id) = edge
    edge.sourceVertex.edgesOut.addBinding(edge.`type`, edge)
    edge.targetVertex.edgesIn.addBinding(edge.`type`, edge)
    edgeTypeLookup.addBinding(edge.`type`, edge)
    val srcTgt = (edge.sourceVertex.id, edge.targetVertex.id)
    edgeSrcTgtTypeLookup.addBinding((srcTgt, edge.`type`), edge)
    mappers.foreach(_.addEdge(edge))
    edge
  }

  def addEdge(id: Long, sourceId: Long, targetId: Long, `type`: String, props: Map[String, Any] = Map()): IngraphEdge = {
    addEdge(IngraphEdge(id, vertexLookup(sourceId), vertexLookup(targetId), `type`, props))
  }

  def removeEdgeById(id: Long): Unit = {
    val edge = edgeLookup(id)
    mappers.foreach(_.removeEdge(edge))
    edgeTypeLookup.removeBinding(edge.`type`, edge)
    val srcTgt = (edge.sourceVertex.id, edge.targetVertex.id)
    edgeSrcTgtTypeLookup.removeBinding((srcTgt, edge.`type`), edge)
    edgeLookup.remove(id)
  }

  def edgesBySourceAndTargetAndType(source: IngraphVertex, target: IngraphVertex, `type`: String) =
    edgeSrcTgtTypeLookup.getOrElse(((source.id, target.id), `type`), Seq()).iterator

  def verticesById(id: Long): Option[IngraphVertex] = vertexLookup.get(id)
  def verticesByIdLabel(id: Long, label: String): Option[IngraphVertex] = vertexIdLabelLookup.get((id, label))
  def verticesByLabel(label: String): Iterator[IngraphVertex] = vertexLabelLookup.getOrElse(label, Seq()).iterator
  def vertices(): Iterator[IngraphVertex] = vertexLookup.valuesIterator

  def edges(): Iterator[IngraphEdge] = edgeLookup.valuesIterator
  def edgeById(id: Long): Option[IngraphEdge] = edgeLookup.get(id)
  def edgesByType(label: String): Iterator[IngraphEdge] = edgeTypeLookup.getOrElse(label, Seq()).iterator

  def getNumberOfVertices(): Int = vertexLookup.size
  def getNumberOfEdges(): Int = edgeLookup.size
  def getNumberOfLabels(): Int = vertexLabelLookup.keySet.size
  def getNumberOfTypes(): Int = edgeTypeLookup.keySet.size
  def getNumberOfEdgesWithType(`type`: String): Int = edgeTypeLookup.get(`type`).map(_.size).getOrElse(0)

  val rnd = new Random(1)
  def newId(): Long = {
    var id = rnd.nextLong()
    while (edgeLookup.contains(id) || vertexLookup.contains(id)) {
      id = rnd.nextLong()
    }
    id
  }
}
