package ingraph.ire

/**
  * Defines how to map graph elements (vertices and edges) to tuples.
  */
trait GraphElementToTupleMapper {
  def addEdge(edge: IngraphEdge): Unit
  def removeEdge(edge: IngraphEdge): Unit
  def addVertex(vertex: IngraphVertex): Unit
  def removeVertex(vertex: IngraphVertex): Unit
}
