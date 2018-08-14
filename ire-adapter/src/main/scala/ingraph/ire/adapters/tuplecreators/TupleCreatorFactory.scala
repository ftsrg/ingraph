package ingraph.ire.adapters.tuplecreators

import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.inputs.InputMultiplexer
import ingraph.ire._
import ingraph.model.expr.{EdgeAttribute, NodeHasLabelsAttribute, PropertyAttribute, VertexAttribute}
import ingraph.model.fplan.{GetEdges, GetVertices}

object TupleConstants {
  val ID_KEY = "id"
}

object PropertyTransformer {
  def apply(properties: Map[String, Any], key: String, id: Long, labels: Set[String] = Set()): Any = {
    if (key == TupleConstants.ID_KEY)
      id
    else
      properties.getOrElse(key, null)
  }
}

object VertexTransformer {
  def apply(element: IngraphVertex, operator: GetVertices, idParser: IdParser): Tuple = {
    Vector(idParser(element.id)) ++
      operator.flatSchema.drop(1).map{
        case a: PropertyAttribute => PropertyTransformer(element.properties, a.name, element.id, element.labels)
        case NodeHasLabelsAttribute(_, labels, _) => element.labels.subsetOf(labels)
      }
  }
}

object EdgeTransformer {
  def apply(edge: IngraphEdge, operator: GetEdges, idParser: IdParser): Tuple = {
    Vector(idParser(edge.sourceVertex.id), idParser(edge.id), idParser(edge.targetVertex.id)) ++
      operator.flatSchema.drop(3)
        .map {
          case PropertyAttribute(name, elementAttribute, _) => elementAttribute match {
            case _: EdgeAttribute =>
              PropertyTransformer(edge.properties, name, edge.id)
            case v: VertexAttribute if v.name == operator.nnode.src.name =>
              PropertyTransformer(edge.sourceVertex.properties, name, edge.sourceVertex.id, edge.sourceVertex.labels)
            case v: VertexAttribute if v.name == operator.nnode.trg.name =>
              PropertyTransformer(edge.targetVertex.properties, name, edge.targetVertex.id, edge.targetVertex.labels)
          }
          case NodeHasLabelsAttribute(_, _, _) => ???
        }
  }
}

class TupleCreator(val vertexConverters: Map[Set[String], Set[GetVertices]],
                   val edgeConverters: Map[String, Set[GetEdges]],
                   val idParser: IdParser,
                   val inputMultiplexer: InputMultiplexer
                  ) extends TTupleCreator {
  val edgeOpString = edgeConverters.values.flatten.map(op => op -> op.toString()).toMap

  def addEdge(edge: IngraphEdge): Unit = {
    for (operators <- edgeConverters.get(edge.`type`); operator <- operators) {
      val sourceLabels = operator.nnode.src.labels.vertexLabels
      val targetLabels = operator.nnode.trg.labels.vertexLabels
      if (sourceLabels.subsetOf(edge.sourceVertex.labels) &&
        targetLabels.subsetOf(edge.targetVertex.labels)) {
        val tuple = EdgeTransformer(edge, operator, idParser)
        inputMultiplexer.add(edgeOpString(operator), tuple)
        if (!operator.nnode.directed) {
          val reverseEdgeTuple = EdgeTransformer(
            edge.copy(sourceVertex = edge.targetVertex, targetVertex = edge.sourceVertex), operator, idParser)
          inputMultiplexer.add(edgeOpString(operator), reverseEdgeTuple)
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
        inputMultiplexer.remove(edgeOpString(operator), tuple)
        if (!operator.nnode.directed) {
          val reverseEdgeTuple = EdgeTransformer(
            edge.copy(sourceVertex = edge.targetVertex, targetVertex = edge.sourceVertex), operator, idParser)
          inputMultiplexer.remove(edgeOpString(operator), reverseEdgeTuple)
        }
      }
    }
  }

  def addVertex(vertex: IngraphVertex): Unit = {
    vertexConverters.filter(p => p._1.subsetOf(vertex.labels)).foreach {
      f =>
        for (operator <- f._2) {
          val tuple = VertexTransformer(vertex, operator, idParser)
          inputMultiplexer.add(operator.nnode.v.name, tuple)
        }
    }
  }

  def removeVertex(vertex: IngraphVertex): Unit = {
    vertexConverters.filter(p => p._1.subsetOf(vertex.labels)).foreach {
      f =>
        for (operator <- f._2) {
          val tuple = VertexTransformer(vertex, operator, idParser)
          inputMultiplexer.remove(operator.nnode.v.name, tuple)
        }
    }
  }
}
