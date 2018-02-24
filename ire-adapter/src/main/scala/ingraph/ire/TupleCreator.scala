package hu.bme.mit.ire

import hu.bme.mit.ire.datatypes.Tuple
import ingraph.ire.{GraphElementToTupleMapper, IdParser, IngraphEdge, IngraphVertex}
import ingraph.model.expr.{EdgeAttribute, PropertyAttribute, VertexAttribute}
import ingraph.model.fplan.{GetEdges, GetVertices}

class TupleCreator(vertexConverters: Map[Set[String], Set[GetVertices]],
                   edgeConverters: Map[String, Set[GetEdges]]) extends IdParser with GraphElementToTupleMapper {

  override def idParser(obj: Any): Any = obj

  var transaction: Transaction = _

  def addEdge(edge: IngraphEdge): Unit = {
    for (operators <- edgeConverters.get(edge.`type`); operator <- operators) {
      val sourceLabels = operator.jnode.src.labels.vertexLabels
      val targetLabels = operator.jnode.trg.labels.vertexLabels
      if (sourceLabels.subsetOf(edge.sourceVertex.labels) &&
        targetLabels.subsetOf(edge.targetVertex.labels)) {
        val tuple = edgeToTuple(edge, operator)
        transaction.add(operator.toString(), tuple)
        if (!operator.jnode.directed) {
          val rTuple = edgeToTuple(
            edge.copy(sourceVertex = edge.targetVertex, targetVertex = edge.sourceVertex), operator)
          transaction.add(operator.toString(), rTuple)
        }
      }
    }
  }

  def removeEdge(edge: IngraphEdge): Unit = {
    for (operators <- edgeConverters.get(edge.`type`); operator <- operators) {
      val sourceLabels = operator.jnode.src.labels.vertexLabels
      val targetLabels = operator.jnode.trg.labels.vertexLabels
      if (sourceLabels.subsetOf(edge.sourceVertex.labels) &&
        targetLabels.subsetOf(edge.targetVertex.labels)) {
        val tuple = edgeToTuple(edge, operator)
        transaction.remove(operator.toString(), tuple)
        if (!operator.jnode.directed) {
          val rTuple = edgeToTuple(
            edge.copy(sourceVertex = edge.targetVertex, targetVertex = edge.sourceVertex), operator)
          transaction.remove(operator.toString(), rTuple)
        }
      }
    }
  }

  def addVertex(vertex: IngraphVertex): Unit = {
    vertexConverters.filter(p => p._1.subsetOf(vertex.labels)).foreach {
      f =>
        for (operator <- f._2) {
          val tuple = vertexToTuple(vertex, operator)
          transaction.add(operator.jnode.v.name, tuple)
        }
    }
  }

  def removeVertex(vertex: IngraphVertex): Unit = {
    vertexConverters.filter(p => p._1.subsetOf(vertex.labels)).foreach {
      f =>
        for (operator <- f._2) {
          val tuple = vertexToTuple(vertex, operator)
          transaction.remove(operator.jnode.v.name, tuple)
        }
    }
  }

  private def vertexToTuple(element: IngraphVertex, operator: GetVertices): Tuple = {
    Vector(idParser(element.id)) ++
      operator.internalSchema.map(_.name).drop(1).map(key => element.properties.getOrElse(key, null))
  }

  private def edgeToTuple(edge: IngraphEdge, operator: GetEdges): Tuple = {
    Vector(idParser(edge.sourceVertex.id), idParser(edge.id), idParser(edge.targetVertex.id)) ++
      operator.internalSchema.drop(3)
        .map {
          case PropertyAttribute(name, elementAttribute, _) => elementAttribute match {
            case _: EdgeAttribute => edge.properties.getOrElse(name, null)
            case v: VertexAttribute if v.name == operator.jnode.trg.name =>
              edge.targetVertex.properties.getOrElse(name, null)
            case v: VertexAttribute if v.name == operator.jnode.src.name =>
              edge.sourceVertex.properties.getOrElse(name, null)
          }
        }
  }

}
