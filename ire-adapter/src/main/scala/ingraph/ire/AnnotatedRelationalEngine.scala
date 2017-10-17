package ingraph.ire

import hu.bme.mit.ire.engine.RelationalEngine
import hu.bme.mit.ire.util.BufferMultimap
import ingraph.model.eplan.{GetEdges, GetVertices}

abstract class AnnotatedRelationalEngine extends RelationalEngine {
  val vertexConverters: BufferMultimap[Seq[String], GetVertices]
  val edgeConverters: BufferMultimap[String, GetEdges]
}
