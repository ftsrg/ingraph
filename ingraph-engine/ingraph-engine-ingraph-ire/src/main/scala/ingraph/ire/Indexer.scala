package ingraph.ire

import hu.bme.mit.ire.util.BufferMultimap
import org.neo4j.driver.v1.types.{Node, Relationship}

import scala.collection.JavaConversions._
import scala.collection.mutable
import scala.util.Random

trait IngraphElement {
  def properties: Map[String, AnyRef]
}

case class IngraphVertex(id: Long,
                         labels: Set[String],
                         override val properties: Map[String, AnyRef] = Map()) extends IngraphElement {
  val edges: mutable.ListMap[String, IngraphEdge] = mutable.ListMap[String, IngraphEdge]()
  val reverseEdges: mutable.ListMap[String, IngraphEdge] = mutable.ListMap[String, IngraphEdge]()
  override def toString: String = s"Vertex($id, $properties)"
}

case class IngraphEdge(id: Long,
                       sourceVertex: IngraphVertex,
                       targetVertex: IngraphVertex,
                       label: String,
                       override val properties: Map[String, AnyRef] = Map()) extends IngraphElement {
  override def toString: String = s"Edge(${sourceVertex.id} -[$label]-> ${targetVertex.id}, $properties)"
  def inverse(): IngraphEdge = IngraphEdge(id, targetVertex, sourceVertex, label, properties)
}

class Indexer {

  val vertexLookup = mutable.HashMap[Long, IngraphVertex]()
  val edgeLookup = mutable.HashMap[Long, IngraphEdge]()
  val vertexLabelLookup = new BufferMultimap[String, IngraphVertex]()
  val edgeLabelLookup = new BufferMultimap[String, IngraphEdge]()
  val mappers = mutable.Buffer[EntityToTupleMapper]()

  def clear(): Unit = {
    vertexLookup.clear()
    vertexLabelLookup.clear()
    edgeLabelLookup.clear()
    mappers.clear()
  }

  def fill(tupleMapper: EntityToTupleMapper): Unit = {
    for (vertex <- vertexLookup.values) {
      tupleMapper.addVertex(vertex)
    }
    for (vertexList <- edgeLabelLookup.values;
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
    val properties: Map[String, AnyRef]  = node.asMap().toMap
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
    val properties: Map[String, AnyRef] = relation.asMap().toMap
    val sourceVertex: IngraphVertex = vertexLookup(relation.startNodeId())
    val targetVertex: IngraphVertex = vertexLookup(relation.endNodeId())
    val label: String = relation.`type`()
    val edge = IngraphEdge(id, sourceVertex, targetVertex, label, properties)
    addEdge(edge)
  }

  def addEdge(edge: IngraphEdge): IngraphEdge = {
    edgeLookup(edge.id) = edge
    edge.sourceVertex.edges(edge.label) = edge
    edge.targetVertex.reverseEdges(edge.label) = edge
    edgeLabelLookup.addBinding(edge.label, edge)
    mappers.foreach(_.addEdge(edge))
    edge
  }

  def addEdge(id: Long, sourceId: Long, targetId: Long, label: String): IngraphEdge = {
    addEdge(IngraphEdge(id, vertexLookup(sourceId), vertexLookup(targetId), label, Map.empty))
  }

  def removeEdgeById(id: Long): Unit = {
    val edge = edgeLookup(id)
    mappers.foreach(_.removeEdge(edge))
    edgeLabelLookup.removeBinding(edge.label, edge)
    edgeLookup.remove(id)
  }

  def vertexById(id: Long): IngraphVertex = vertexLookup(id)
  def edgeById(id: Long): IngraphEdge = edgeLookup(id)
  def verticesByLabel(label: String): Seq[IngraphVertex] = vertexLabelLookup(label)
  def edgesByLabel(label: String): Seq[IngraphEdge] = edgeLabelLookup(label)
  def getNumberOfVerticesWithLabel(label: String): Int =  vertexLabelLookup(label).size
  def getNumberOfEdgesWithLabel(label: String): Int = edgeLabelLookup(label).size

  val rnd = new Random(1)
  def newId(): Long = {
    var id = rnd.nextLong()
    while (edgeLookup.contains(id) || vertexLookup.contains(id)) {
      id = rnd.nextLong()
    }
    id
  }
}
