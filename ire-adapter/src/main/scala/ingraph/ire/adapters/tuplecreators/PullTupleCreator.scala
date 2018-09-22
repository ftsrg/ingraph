package ingraph.ire.adapters.tuplecreators

import hu.bme.mit.ire.inputs.InputTransaction
import ingraph.ire.{IdParser, Indexer, IngraphEdge}
import ingraph.model.fplan.{GetEdges, GetVertices}
import org.apache.spark.sql.catalyst.expressions.Literal

class PullTupleCreator(vertexOps: Seq[GetVertices],
                       edgeOps: Seq[GetEdges],
                       indexer: Indexer,
                       inputTransaction: InputTransaction,
                       idParser: IdParser
                      ) {
  for (op <- vertexOps) {
    val v = op.nnode.v
    val opLabels = v.labels.vertexLabels

    val vertices = v.properties.get(TupleConstants.ID_KEY) match {
      case None =>
        indexer.verticesByLabel(opLabels.head).filter(v => opLabels.subsetOf(v.labels))
      case Some(Literal(id, _)) =>
        val label = v.labels.vertexLabels.head
        val vertex = indexer.vertexByIdLabel(
          id.asInstanceOf[Long],
          label
        ).getOrElse(throw new IllegalStateException(s"Vertex not found with label ${label} and id ${id}"))
        assert(opLabels.subsetOf(vertex.labels), "Wrong labels on direct delete")
        Seq(vertex)
    }
    for (vertex <- vertices) {
      val tuple = VertexTransformer(vertex, op, idParser)
      inputTransaction.add(v.name, tuple)
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
      inputTransaction.add(operator.toString(), tuple)
      if (!operator.nnode.directed) {
        val rTuple = EdgeTransformer(
          edge.copy(sourceVertex = edge.targetVertex, targetVertex = edge.sourceVertex), operator, idParser)
        inputTransaction.add(operator.toString(), rTuple)
      }
    }
  }
}
