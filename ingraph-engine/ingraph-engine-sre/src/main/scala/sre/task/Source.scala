package sre.task

import ingraph.ire.Indexer
import sre.task.Core.{Context, EdgeT, VertexT}

class Source(private val indexer: Indexer) {
  /*
    Returns an iterator for the vertex with the given `id`.
   */
  def vertex(id: Long): Iterator[Context[VertexT]] = {
    Seq(indexer.vertexById(id)).map((vertex) => Context(vertex, Map())).iterator
  }

  /*
    Returns an iterator for vertices that has all the labels given in the `labelSuperSetOf` parameter.
   */
  def vertices(labelsSupersetOf: Set[String]): Iterator[Context[VertexT]] = {
    labelsSupersetOf
      .map((l) => (l, indexer.getNumberOfVerticesWithLabel(l)))
      .toSeq
      .sortBy({ case (_, size) => size })
      .map({ case (label, _) => label })
      .foldLeft(Set.empty[VertexT])((result, label) => result & indexer.verticesByLabel(label).toSet)
      .iterator
      .map((vertex) => Context(vertex, Map()))
  }

  /*
    If `labelIn` is the empty set returns an iterator over all edges. Else returns an iterator for edges whose
    label is in `labelIn`.
   */
  def edges(labelIn: Set[String]): Iterator[Context[EdgeT]] = {
    labelIn
      .find((l) => indexer.getNumberOfEdgesWithLabel(l) > 0)
      .iterator
      .map((l) => indexer.edgesByLabel(l))
      .flatten
      .map((edge) => Context(edge, Map()))
  }

  /*
    Returns an iterator for the edge with the given `id`.
   */
  def edge(id: Long): Iterator[Context[EdgeT]] = {
    Seq(indexer.edgeById(id))
      .iterator
      .map((edge) => Context(edge, Map()))
  }
}
