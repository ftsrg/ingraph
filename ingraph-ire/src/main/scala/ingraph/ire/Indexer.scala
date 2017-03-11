package ingraph.ire

import hu.bme.mit.ire.util.BufferMultimap
import org.neo4j.driver.internal.{InternalNode, InternalRelationship}
import org.neo4j.driver.v1.Value

import scala.collection.JavaConversions._
import scala.collection.mutable
class Indexer {

  private val nodeLookup = mutable.HashMap[Long, IngraphVertex]()
  private val vertexLabelLookup = new BufferMultimap[String, IngraphVertex]()
  private val edgeLabelLookup = new BufferMultimap[String, IngraphEdge]()

  def addVertex(node: InternalNode) = new IngraphVertex(node)
  def addEdge(relation: InternalRelationship) = new IngraphEdge(relation)

  class IngraphVertex(node: InternalNode) {
    val id: Long = node.id()
    val properties: Map[String, Value] = node.keys().map(k => k -> node.get(k)).toMap
    val edges: mutable.ListMap[String, IngraphEdge] = mutable.ListMap[String, IngraphEdge]()
    val reverseEdges: mutable.ListMap[String, IngraphEdge] = mutable.ListMap[String, IngraphEdge]()
    for (label <- node.labels())
      vertexLabelLookup.addBinding(label, this)
    nodeLookup(id) = this
  }


  class IngraphEdge(relation: InternalRelationship) {
    val id: Long = relation.id()
    val properties: Map[String, Value] = relation.keys().map(k => k -> relation.get(k)).toMap
    val inVertex: IngraphVertex = nodeLookup(relation.startNodeId())
    inVertex.edges(relation.`type`()) = this
    val outVertex: IngraphVertex = nodeLookup(relation.endNodeId())
    outVertex.reverseEdges(relation.`type`()) = this
    edgeLabelLookup.addBinding(relation.`type`(), this)
  }

  def vertexById(id: Long): IngraphVertex = nodeLookup(id)
  def verticesByLabel(label: String): Seq[IngraphVertex] = vertexLabelLookup(label)
  def edgesByLabel(label: String): Seq[IngraphEdge] = edgeLabelLookup(label)
}
