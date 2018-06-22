package hu.bme.mit.ire

import hu.bme.mit.ire.datatypes.Tuple
import ingraph.ire._
import ingraph.model.expr.{EdgeAttribute, PropertyAttribute, VertexAttribute}
import ingraph.model.fplan.{GetEdges, GetVertices}
import org.apache.spark.sql.catalyst.expressions.Literal

object TupleConstants {
  val ID_KEY = "id"
}

object PropertyTransformer {
  def apply(properties: Map[String, Any], key: String, id: Long): Any = {
    if (key == TupleConstants.ID_KEY)
      id
    else
      properties.getOrElse(key, null)
  }
}

object VertexTransformer {
  def apply(element: IngraphVertex, operator: GetVertices, idParser: IdParser): Tuple = {
    Vector(idParser(element.id)) ++
      operator.flatSchema.map(_.name).drop(1).map(key => PropertyTransformer(element.properties, key, element.id))
  }
}

object EdgeTransformer {
  def apply(edge: IngraphEdge, operator: GetEdges, idParser: IdParser): Tuple = {
    Vector(idParser(edge.sourceVertex.id), idParser(edge.id), idParser(edge.targetVertex.id)) ++
      operator.flatSchema.drop(3)
        .map {
          case PropertyAttribute(name, elementAttribute, _) => elementAttribute match {
            case _: EdgeAttribute => PropertyTransformer(edge.properties, name, edge.id)
            case v: VertexAttribute if v.name == operator.nnode.trg.name =>
              PropertyTransformer(edge.targetVertex.properties, name, edge.targetVertex.id)
            case v: VertexAttribute if v.name == operator.nnode.src.name =>
              PropertyTransformer(edge.sourceVertex.properties, name, edge.sourceVertex.id)
          }
        }
  }
}

class TupleCreator(vertexConverters: Map[Set[String], Set[GetVertices]],
                   edgeConverters: Map[String, Set[GetEdges]],
                   idParser: IdParser = PlainIdParser) extends GraphElementToTupleMapper {
  val edgeOpString = edgeConverters.values.flatten.map(op => op -> op.toString()).toMap
  var transaction: Transaction = _

  def addEdge(edge: IngraphEdge): Unit = {
    for (operators <- edgeConverters.get(edge.`type`); operator <- operators) {
      val sourceLabels = operator.nnode.src.labels.vertexLabels
      val targetLabels = operator.nnode.trg.labels.vertexLabels
      if (sourceLabels.subsetOf(edge.sourceVertex.labels) &&
        targetLabels.subsetOf(edge.targetVertex.labels)) {
        val tuple = EdgeTransformer(edge, operator, idParser)
        transaction.add(edgeOpString(operator), tuple)
        if (!operator.nnode.directed) {
          val rTuple = EdgeTransformer(
            edge.copy(sourceVertex = edge.targetVertex, targetVertex = edge.sourceVertex), operator, idParser)
          transaction.add(edgeOpString(operator), rTuple)
        }
      }
    }
  }

  def removeEdge(edge: IngraphEdge): Unit = {
    for (operators <- edgeConverters.get(edge.`type`); operator <- operators) {
      val sourceLabels = operator.nnode.src.labels.vertexLabels
      val targetLabels = operator.nnode.trg.labels.vertexLabels
      if (sourceLabels.subsetOf(edge.sourceVertex.labels) &&
        targetLabels.subsetOf(edge.targetVertex.labels)) {
        val tuple = EdgeTransformer(edge, operator, idParser)
        transaction.remove(edgeOpString(operator), tuple)
        if (!operator.nnode.directed) {
          val rTuple = EdgeTransformer(
            edge.copy(sourceVertex = edge.targetVertex, targetVertex = edge.sourceVertex), operator, idParser)
          transaction.remove(edgeOpString(operator), rTuple)
        }
      }
    }
  }

  def addVertex(vertex: IngraphVertex): Unit = {
    vertexConverters.filter(p => p._1.subsetOf(vertex.labels)).foreach {
      f =>
        for (operator <- f._2) {
          val tuple = VertexTransformer(vertex, operator, idParser)
          transaction.add(operator.nnode.v.name, tuple)
        }
    }
  }

  def removeVertex(vertex: IngraphVertex): Unit = {
    vertexConverters.filter(p => p._1.subsetOf(vertex.labels)).foreach {
      f =>
        for (operator <- f._2) {
          val tuple = VertexTransformer(vertex, operator, idParser)
          transaction.remove(operator.nnode.v.name, tuple)
        }
    }
  }
}

class PullTupleCreator(vertexOps: Seq[GetVertices], edgeOps: Seq[GetEdges],
                       indexer: Indexer, transaction: Transaction, idParser: IdParser = PlainIdParser) {
  for (op <- vertexOps) {
    val v = op.nnode.v
    val opLabels = v.labels.vertexLabels

    val vertices = v.properties.get(TupleConstants.ID_KEY) match {
      case None =>
        indexer.verticesByLabel(opLabels.head).filter(v => opLabels.subsetOf(v.labels))
      case Some(Literal(id, _)) =>
        val label = v.labels.vertexLabels.head
        val vertex = indexer.verticesByIdLabel(
          id.asInstanceOf[Long],
          label
        ).getOrElse(throw new IllegalStateException(s"Vertex not found with label ${label} and id ${id}"))
        assert(opLabels.subsetOf(vertex.labels), "Wrong labels on direct delete")
        Seq(vertex)
    }
    for (vertex <- vertices) {
      val tuple = VertexTransformer(vertex, op, idParser)
      transaction.add(v.name, tuple)
    }
  }

  for (operator <- edgeOps) {
    val sourceLabels = operator.nnode.src.labels.vertexLabels
    val targetLabels = operator.nnode.trg.labels.vertexLabels
    val labels = operator.nnode.edge.labels.edgeLabels
    val edges: Iterable[IngraphEdge] = operator.nnode.edge.properties.get(TupleConstants.ID_KEY) match {
      case None =>
        (for (label <- labels)
          yield {
            indexer.edgesByType(label).filter(e => sourceLabels.subsetOf(e.sourceVertex.labels)
              && targetLabels.subsetOf(e.targetVertex.labels))
          }).flatten
      case Some(Literal(id, _)) =>
        val edge = indexer.edgeById(id.asInstanceOf[Long]).get
        assert(sourceLabels.subsetOf(edge.sourceVertex.labels)
          && targetLabels.subsetOf(edge.targetVertex.labels), "Wrong vertex labels on direct delete")
        assert(edge.`type` == labels.head, "Wrong edge type on direct delete")
        Seq(edge)
    }

    for (edge <- edges) {
      val tuple = EdgeTransformer(edge, operator, idParser)
      transaction.add(operator.toString(), tuple)
      if (!operator.nnode.directed) {
        val rTuple = EdgeTransformer(
          edge.copy(sourceVertex = edge.targetVertex, targetVertex = edge.sourceVertex), operator, idParser)
        transaction.add(operator.toString(), rTuple)
      }
    }
  }
}
