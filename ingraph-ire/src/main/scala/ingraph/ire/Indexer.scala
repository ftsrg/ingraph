package ingraph.ire

import hu.bme.mit.ire.util.BufferMultimap

import org.neo4j.driver.v1.Value
import org.neo4j.driver.v1.types.{Node, Relationship}

import scala.collection.JavaConversions._
import scala.collection.mutable


case class IngraphVertex(id: Long, properties: Map[String, AnyRef], labels: Set[String]) {
  val edges: mutable.ListMap[String, IngraphEdge] = mutable.ListMap[String, IngraphEdge]()
  val reverseEdges: mutable.ListMap[String, IngraphEdge] = mutable.ListMap[String, IngraphEdge]()
  override def toString: String = s"Vertex($id, $properties)"
}

case class IngraphEdge(id: Long, properties: Map[String, AnyRef],
                       sourceVertex: IngraphVertex,
                       targetVertex: IngraphVertex,
                      label: String) {
  override def toString: String = s"Edge(${sourceVertex.id} -[$id]-> ${targetVertex.id}, $properties)"
}

class Indexer(tupleMapper: EntityToTupleMapper) {

  val nodeLookup = mutable.HashMap[Long, IngraphVertex]()
  val vertexLabelLookup = new BufferMultimap[String, IngraphVertex]()
  val edgeLabelLookup = new BufferMultimap[String, IngraphEdge]()

  def addVertex(node: Node): IngraphVertex = {
    val id: Long = node.id()
    val properties: Map[String, AnyRef]  = node.asMap().toMap
    val labels = node.labels().toSet
    val vertex = IngraphVertex(id, properties, labels)
    for (label <- labels)
      vertexLabelLookup.addBinding(label, vertex)
    nodeLookup(id) = vertex
    tupleMapper.addVertex(vertex)
    vertex
  }
  def addEdge(relation: Relationship): IngraphEdge = {
    val id: Long = relation.id()
    val properties: Map[String, AnyRef] = relation.asMap().toMap
    val sourceVertex: IngraphVertex = nodeLookup(relation.startNodeId())
    val targetVertex: IngraphVertex = nodeLookup(relation.endNodeId())
    val label: String = relation.`type`()
    val edge = IngraphEdge(id, properties, sourceVertex, targetVertex, label)
    sourceVertex.edges(relation.`type`()) = edge
    targetVertex.reverseEdges(relation.`type`()) = edge
    edgeLabelLookup.addBinding(relation.`type`(), edge)
    tupleMapper.addEdge(edge)
    edge
  }

  def vertexById(id: Long): IngraphVertex = nodeLookup(id)
  def verticesByLabel(label: String): Seq[IngraphVertex] = vertexLabelLookup(label)
  def edgesByLabel(label: String): Seq[IngraphEdge] = edgeLabelLookup(label)
}
