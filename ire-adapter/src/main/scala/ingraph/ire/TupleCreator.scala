package hu.bme.mit.ire

import hu.bme.mit.ire.datatypes.Tuple
import ingraph.ire._
import ingraph.model.expr.{EdgeAttribute, PropertyAttribute, VertexAttribute}
import ingraph.model.fplan.{GetEdges, GetVertices}

object VertexTransformer {
  def apply(element: IngraphVertex, operator: GetVertices, idParser: IdParser): Tuple = {
    Vector(idParser(element.id)) ++
      operator.internalSchema.map(_.name).drop(1).map(key => element.properties.getOrElse(key, null))
  }
}

object EdgeTransformer {
  def apply(edge: IngraphEdge, operator: GetEdges, idParser: IdParser): Tuple = {
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

class TupleCreator(vertexConverters: Map[Set[String], Set[GetVertices]],
                   edgeConverters: Map[String, Set[GetEdges]],
                   idParser: IdParser = PlainIdParser) extends GraphElementToTupleMapper {

  var transaction: Transaction = _

  def addEdge(edge: IngraphEdge): Unit = {
    for (operators <- edgeConverters.get(edge.`type`); operator <- operators) {
      val sourceLabels = operator.jnode.src.labels.vertexLabels
      val targetLabels = operator.jnode.trg.labels.vertexLabels
      if (sourceLabels.subsetOf(edge.sourceVertex.labels) &&
        targetLabels.subsetOf(edge.targetVertex.labels)) {
        val tuple = EdgeTransformer(edge, operator, idParser)
        transaction.add(operator.toString(), tuple)
        if (!operator.jnode.directed) {
          val rTuple = EdgeTransformer(
            edge.copy(sourceVertex = edge.targetVertex, targetVertex = edge.sourceVertex), operator, idParser)
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
        val tuple = EdgeTransformer(edge, operator, idParser)
        transaction.remove(operator.toString(), tuple)
        if (!operator.jnode.directed) {
          val rTuple = EdgeTransformer(
            edge.copy(sourceVertex = edge.targetVertex, targetVertex = edge.sourceVertex), operator, idParser)
          transaction.remove(operator.toString(), rTuple)
        }
      }
    }
  }

  def addVertex(vertex: IngraphVertex): Unit = {
    vertexConverters.filter(p => p._1.subsetOf(vertex.labels)).foreach {
      f =>
        for (operator <- f._2) {
          val tuple = VertexTransformer(vertex, operator, idParser)
          transaction.add(operator.jnode.v.name, tuple)
        }
    }
  }

  def removeVertex(vertex: IngraphVertex): Unit = {
    vertexConverters.filter(p => p._1.subsetOf(vertex.labels)).foreach {
      f =>
        for (operator <- f._2) {
          val tuple = VertexTransformer(vertex, operator, idParser)
          transaction.remove(operator.jnode.v.name, tuple)
        }
    }
  }
}

class PullTupleCreator(vertexOps: Seq[GetVertices], edgeOps: Seq[GetEdges],
                       indexer: Indexer, transaction: Transaction, idParser: IdParser = PlainIdParser) {
  for (op <- vertexOps) {
   val opLabels = op.jnode.v.labels.vertexLabels
   val vertices = indexer.verticesByLabel(opLabels.head).filter(v => opLabels.subsetOf(v.labels))
   for (vertex <- vertices)
   transaction.add(op.jnode.v.name, VertexTransformer(vertex, op, idParser))
  }

  for (operator <- edgeOps;
       label <- operator.jnode.edge.labels.edgeLabels) {
    val sourceLabels = operator.jnode.src.labels.vertexLabels
    val targetLabels = operator.jnode.trg.labels.vertexLabels
    val edges = indexer.edgesByType(label).filter(e => sourceLabels.subsetOf(e.sourceVertex.labels)
      && targetLabels.subsetOf(e.targetVertex.labels))
    for (edge <- edges) {
      val tuple = EdgeTransformer(edge, operator, idParser)
      transaction.add(operator.toString(), tuple)
      if (!operator.jnode.directed) {
        val rTuple = EdgeTransformer(
          edge.copy(sourceVertex = edge.targetVertex, targetVertex = edge.sourceVertex), operator, idParser)
        transaction.add(operator.toString(), rTuple)
      }
    }
  }
}
