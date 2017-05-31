package ingraph.ire

import hu.bme.mit.ire.util.BufferMultimap
import org.neo4j.driver.v1.Value
import org.neo4j.driver.v1.types.{Node, Relationship}

import scala.collection.JavaConversions._
import scala.collection.mutable
import scala.util.Random


case class IngraphVertex(id: Long, properties: Map[String, AnyRef], labels: Set[String]) {
  val edges: mutable.ListMap[String, IngraphEdge] = mutable.ListMap[String, IngraphEdge]()
  val reverseEdges: mutable.ListMap[String, IngraphEdge] = mutable.ListMap[String, IngraphEdge]()
  override def toString: String = s"Vertex($id, $properties)"
}

case class IngraphEdge(id: Long, properties: Map[String, AnyRef],
                       sourceVertex: IngraphVertex,
                       targetVertex: IngraphVertex,
                       label: String) {
  override def toString: String = s"Edge(${sourceVertex.id} -[$label]-> ${targetVertex.id}, $properties)"
  def inverse(): IngraphEdge = IngraphEdge(id, properties, targetVertex, sourceVertex, label)
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
    val vertex = IngraphVertex(id, properties, labels)
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
    val edge = IngraphEdge(id, properties, sourceVertex, targetVertex, label)
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
    addEdge(IngraphEdge(id, Map.empty, vertexLookup(sourceId), vertexLookup(targetId), label))
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

  val rnd = new Random(1)
  def newId(): Long = {
    var id = rnd.nextLong()
    while (edgeLookup.contains(id) || vertexLookup.contains(id)) {
      id = rnd.nextLong()
    }
    id
  }
}
