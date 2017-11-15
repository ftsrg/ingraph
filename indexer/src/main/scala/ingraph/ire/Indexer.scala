package ingraph.ire

import java.util.{Iterator => JIterator}

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

  val mappers = mutable.Buffer[EntityToTupleMapper]()
  val vertexLookup = mutable.HashMap[Long, IngraphVertex]()
  val vertexLabelLookup = new BufferMultimap[String, IngraphVertex]()
  val edgeLookup = mutable.HashMap[Long, IngraphEdge]()
  val edgeTypeLookup = new BufferMultimap[String, IngraphEdge]()
  val edgeSrcTgtLookup = new BufferMultimap[(Long, Long), IngraphEdge]()
  val edgeSrcTgtTypeLookup = new BufferMultimap[((Long, Long), String), IngraphEdge]

  def clear(): Unit = {
    mappers.clear()
    vertexLookup.clear()
    vertexLabelLookup.clear()
    edgeLookup.clear()
    edgeTypeLookup.clear()
    edgeSrcTgtLookup.clear()
    edgeSrcTgtTypeLookup.clear()
  }

  def fill(tupleMapper: EntityToTupleMapper): Unit = {
    for (vertex <- vertexLookup.values) {
      tupleMapper.addVertex(vertex)
    }
    for (edge <- edgeLookup.values) {
      tupleMapper.addEdge(edge)
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

  def removeVertexById(id: Long, detach: Boolean = false): Unit = {
    val vertex = vertexLookup(id)
    val edges = vertex.edgesOut.valuesIterator.flatten ++ vertex.edgesIn.valuesIterator.flatten
    if (!detach && edges.nonEmpty)
      throw new IllegalArgumentException("Won't remove connected vertex without DETACH")
    else if (detach)
      for { edge <- edges } removeEdgeById(edge.id)
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
    val srcTgt = (edge.sourceVertex.id, edge.targetVertex.id)
    edgeSrcTgtLookup.addBinding(srcTgt, edge)
    edgeSrcTgtTypeLookup.addBinding((srcTgt, edge.`type`), edge)
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
    val srcTgt = (edge.sourceVertex.id, edge.targetVertex.id)
    edgeSrcTgtTypeLookup.removeBinding((srcTgt, edge.`type`), edge)
    edgeSrcTgtLookup.removeBinding(srcTgt, edge)
    edgeLookup.remove(id)
  }

  def edgesBySourceAndTarget(source: IngraphVertex, target: IngraphVertex) =
    edgeSrcTgtLookup.getOrElse((source.id, target.id), Seq()).iterator
  def edgesBySourceAndTargetJava(source: IngraphVertex, target: IngraphVertex): JIterator[IngraphEdge] = edgesBySourceAndTarget(source, target)
  def edgesBySourceAndTargetAndType(source: IngraphVertex, target: IngraphVertex, `type`: String) =
    edgeSrcTgtTypeLookup.getOrElse(((source.id, target.id), `type`), Seq()).iterator
  def edgesBySourceAndTargetAndTypeJava(source: IngraphVertex, target: IngraphVertex, `type`: String): JIterator[IngraphEdge] =
    edgeSrcTgtTypeLookup.getOrElse(((source.id, target.id), `type`), Seq()).iterator
  def vertexById(id: Long): Option[IngraphVertex] = vertexLookup.get(id)
  def edgeById(id: Long): Option[IngraphEdge] = edgeLookup.get(id)
  def vertices(): Iterator[IngraphVertex] = vertexLookup.valuesIterator
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
