package hu.bme.mit.ire

import hu.bme.mit.ire.datatypes.Tuple
import ingraph.ire.{EntityToTupleMapper, IdParser, IngraphEdge, IngraphVertex}
import java.util.{Iterator => JIterator}

import hu.bme.mit.ire.messages.ChangeSet
import ingraph.model.eplan.GetEdges
import ingraph.model.eplan.GetVertices

class Neo4jEntityToTupleMapper(vertexConverters: Map[Set[String], Set[GetVertices]],
                               edgeConverters: Map[String, Set[GetEdges]],
                               inputLookup: Map[String, (ChangeSet) => Unit]) extends IdParser with EntityToTupleMapper {

  private val vertexLookup: Map[String, (Set[String], Set[GetVertices])] = for ((labels, operators) <- vertexConverters;
                                                                                        label <- labels)
    yield label -> (labels, operators)

  override def idParser(obj: Any): Any = obj

  var transaction: Transaction = _

  def elementToNode(element: IngraphVertex, required: Seq[String]): Tuple = {
    val nodeNameLength = required.head.length
    Vector(idParser(element.id)) ++
      required.tail.map(key => element.properties(key.drop(nodeNameLength + 1))) //TODO
  }

  def addEdge(edge: IngraphEdge): Unit = {
    for (operators <- edgeConverters.get(edge.`type`); operator <- operators) {
      val sourceLabels = operator.inode.src.labels.vertexLabels
      val targetLabels = operator.inode.trg.labels.vertexLabels
      if (sourceLabels.subsetOf(edge.sourceVertex.labels) &&
        targetLabels.subsetOf(edge.targetVertex.labels)) {
        val tuple = edgeToTupleType(edge, operator)
        transaction.add(operator.inode.edge.name, tuple)
      }
    }
  }

  def removeEdge(edge: IngraphEdge): Unit = {
    for (operators <- edgeConverters.get(edge.`type`); operator <- operators) {
      val sourceLabels = operator.inode.src.labels.vertexLabels
      val targetLabels = operator.inode.trg.labels.vertexLabels
      if (sourceLabels.subsetOf(edge.sourceVertex.labels) &&
        targetLabels.subsetOf(edge.targetVertex.labels)) {
        val tuple = edgeToTupleType(edge, operator)
        transaction.remove(operator.inode.edge.name, tuple)
      }
    }
  }

  def addVertex(vertex: IngraphVertex): Unit = {
    vertex.labels.find(vertexLookup.contains).foreach(
      f => {
        val (labels, operators) = vertexLookup(f)
        if (labels.subsetOf(vertex.labels)) {
          for (operator <- operators) {
            val tuple = elementToNode(vertex, operator.internalSchema.map(_.name))
            transaction.add(operator.nodeName, tuple)
          }
        }
      })
  }

  def removeVertex(vertex: IngraphVertex): Unit = {
    vertex.labels.find(vertexLookup.contains).foreach(
      f => {
        val (labels, operators) = vertexLookup(f)
        if (labels.subsetOf(vertex.labels)) {
          for (operator <- operators) {
            val tuple = elementToNode(vertex, operator.internalSchema.map(_.name))
            transaction.remove(operator.nodeName, tuple)
          }
        }
      })
  }

  private def edgeToTupleType(edge: IngraphEdge, operator: GetEdges): Tuple = {
    Vector(idParser(edge.sourceVertex.id), idParser(edge.id), idParser(edge.targetVertex.id)) ++
      operator.internalSchema.drop(3)
        .map {
          case a if a.nodeName == operator.inode.src.name => edge.sourceVertex.properties(a.name)
          case a if a.nodeName == operator.inode.trg.name => edge.targetVertex.properties(a.name)
          case a if a.nodeName == operator.inode.edge.name => edge.properties(a.name)
        }
  }
}
