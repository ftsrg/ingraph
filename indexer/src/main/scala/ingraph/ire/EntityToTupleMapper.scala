package ingraph.ire

trait EntityToTupleMapper {
  def addEdge(edge: IngraphEdge): Unit
  def removeEdge(edge: IngraphEdge): Unit
  def addVertex(vertex: IngraphVertex): Unit
  def removeVertex(vertex: IngraphVertex): Unit
}
