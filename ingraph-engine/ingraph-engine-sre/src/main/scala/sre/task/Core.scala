package sre.task

object Core {
  import ingraph.ire.{IngraphEdge, IngraphObject, IngraphVertex}
  type ObjectT[A] = IngraphObject[A]
  type VertexT = IngraphVertex
  type EdgeT = IngraphEdge
}
