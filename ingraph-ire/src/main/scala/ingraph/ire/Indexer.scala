package ingraph.ire

import hu.bme.mit.ire.util.BufferMultimap

import org.neo4j.driver.v1.Value
import org.neo4j.driver.v1.types.{Node, Relationship}

import scala.collection.JavaConversions._
import scala.collection.mutable


class IngraphVertex(node: Node, indexer: Indexer) {
  val id: Long = node.id()
  val properties: Map[String, AnyRef]  = node.asMap().toMap
  val edges: mutable.ListMap[String, IngraphEdge] = mutable.ListMap[String, IngraphEdge]()
  val reverseEdges: mutable.ListMap[String, IngraphEdge] = mutable.ListMap[String, IngraphEdge]()
  val labels = node.labels().toSet
  for (label <- labels)
    indexer.vertexLabelLookup.addBinding(label, this)
  indexer.nodeLookup(id) = this

  override def toString: String = s"Vertex($id, $properties)"
}

class IngraphEdge(relation: Relationship, indexer: Indexer) {
  val id: Long = relation.id()
  val properties: Map[String, AnyRef] = relation.asMap().toMap
  val sourceVertex: IngraphVertex = indexer.nodeLookup(relation.startNodeId())
  sourceVertex.edges(relation.`type`()) = this
  val targetVertex: IngraphVertex = indexer.nodeLookup(relation.endNodeId())
  targetVertex.reverseEdges(relation.`type`()) = this
  indexer.edgeLabelLookup.addBinding(relation.`type`(), this)
  val label: String = relation.`type`()
  override def toString: String = s"Edge(${sourceVertex.id} -[$id]-> ${targetVertex.id}, $properties)"
}

class Indexer(tupleMapper: EntityToTupleMapper) {

  val nodeLookup = mutable.HashMap[Long, IngraphVertex]()
  val vertexLabelLookup = new BufferMultimap[String, IngraphVertex]()
  val edgeLabelLookup = new BufferMultimap[String, IngraphEdge]()

  def addVertex(node: Node) = {
    val res = new IngraphVertex(node, this)
    tupleMapper.addVertex(res)
    res
  }
  def addEdge(relation: Relationship) = {
    val res = new IngraphEdge(relation, this)
    tupleMapper.addEdge(res)
    res
  }

  def vertexById(id: Long): IngraphVertex = nodeLookup(id)
  def verticesByLabel(label: String): Seq[IngraphVertex] = vertexLabelLookup(label)
  def edgesByLabel(label: String): Seq[IngraphEdge] = edgeLabelLookup(label)
}
