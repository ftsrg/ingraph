package ingraph.ire

import hu.bme.mit.ire.datatypes.Tuple

trait EntityToTupleMapper {
  def elementToNode(element: IngraphVertex, required: Vector[String]): Tuple
  def addEdge(edge: IngraphEdge): Unit
  def removeEdge(edge: IngraphEdge): Unit
  def addVertex(vertex: IngraphVertex): Unit
  def removeVertex(vertex: IngraphVertex): Unit
}
