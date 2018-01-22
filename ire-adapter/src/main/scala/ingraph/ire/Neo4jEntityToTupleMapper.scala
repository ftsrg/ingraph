package hu.bme.mit.ire

import java.util.{Iterator => JIterator}

import hu.bme.mit.ire.datatypes.Tuple
import ingraph.ire.{EntityToTupleMapper, IdParser, IngraphEdge, IngraphVertex}
import ingraph.model.tplan.{GetEdges, GetVertices}

class Neo4jEntityToTupleMapper(vertexConverters: Map[Set[String], Set[GetVertices]],
                               edgeConverters: Map[String, Set[GetEdges]]) extends IdParser with EntityToTupleMapper {

  private val vertexLookup: Map[String, (Set[String], Set[GetVertices])] = for ((labels, operators) <- vertexConverters;
                                                                                        label <- labels)
    yield label -> (labels, operators)

  override def idParser(obj: Any): Any = obj

  var transaction: Transaction = _

  def elementToNode(element: IngraphVertex, required: Seq[String]): Tuple = {
    Vector(idParser(element.id)) ++
      required.tail.map(key => element.properties(key))
  }

  def addEdge(edge: IngraphEdge): Unit = {
    for (operators <- edgeConverters.get(edge.`type`); operator <- operators) {
      val sourceLabels = operator.fnode.jnode.src.labels.vertexLabels
      val targetLabels = operator.fnode.jnode.trg.labels.vertexLabels
      if (sourceLabels.subsetOf(edge.sourceVertex.labels) &&
        targetLabels.subsetOf(edge.targetVertex.labels)) {
        val tuple = edgeToTupleType(edge, operator)
        transaction.add(operator.fnode.jnode.edge.name, tuple)
      }
    }
  }

  def removeEdge(edge: IngraphEdge): Unit = {
    for (operators <- edgeConverters.get(edge.`type`); operator <- operators) {
      val sourceLabels = operator.fnode.jnode.src.labels.vertexLabels
      val targetLabels = operator.fnode.jnode.trg.labels.vertexLabels
      if (sourceLabels.subsetOf(edge.sourceVertex.labels) &&
        targetLabels.subsetOf(edge.targetVertex.labels)) {
        val tuple = edgeToTupleType(edge, operator)
        transaction.remove(operator.fnode.jnode.edge.name, tuple)
      }
    }
  }

  def addVertex(vertex: IngraphVertex): Unit = {
    vertex.labels.find(vertexLookup.contains).foreach(
      f => {
        val (labels, operators) = vertexLookup(f)
        if (labels.subsetOf(vertex.labels)) {
          for (operator <- operators) {
            val tuple = elementToNode(vertex, operator.fnode.internalSchema.map(_.name))
            transaction.add(operator.fnode.jnode.v.name, tuple)
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
            val tuple = elementToNode(vertex, operator.fnode.internalSchema.map(_.name))
            transaction.remove(operator.fnode.jnode.v.name, tuple)
          }
        }
      })
  }

  private def edgeToTupleType(edge: IngraphEdge, operator: GetEdges): Tuple = {
    Vector(idParser(edge.sourceVertex.id), idParser(edge.id), idParser(edge.targetVertex.id)) ++
      operator.fnode.internalSchema.drop(3)
        .map {
          case a if a.nodeName == operator.fnode.jnode.src.name => edge.sourceVertex.properties(a.name)
          case a if a.nodeName == operator.fnode.jnode.trg.name => edge.targetVertex.properties(a.name)
          case a if a.nodeName == operator.fnode.jnode.edge.name => edge.properties(a.name)
        }
  }
}
