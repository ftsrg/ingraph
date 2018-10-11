package ingraph.compiler.sql

import ingraph.model.expr.types.EdgeLabel
import org.cytosm.common.gtop.GTop
import org.cytosm.common.gtop.implementation.relational.{ImplementationEdge, ImplementationNode, TraversalHop}

import scala.collection.JavaConverters._

object GTopExtension {

  implicit class Extension(val gTop: GTop) extends AnyVal {
    def findEdgeTables(edgeLabels: Set[EdgeLabel]): Seq[(ImplementationEdge, TraversalHop, Seq[ImplementationNode], Seq[ImplementationNode])] = {
      gTop
        .getImplementationLevel.getImplementationEdges.asScala
        .filter(_.getTypes.asScala.toSet.intersect(edgeLabels).nonEmpty)
        .map { edge =>
          val paths = edge.getPaths
          assert(paths.size == 1, "Only supports one path per edge")
          val traversalHops = paths.get(0).getTraversalHops
          assert(traversalHops.size == 1, "Only supports one hop per path")

          val traversalHop = traversalHops.get(0)

          val sourceNodes: Seq[ImplementationNode] = gTop
            .getImplementationLevel.getImplementationNodes.asScala
            .filter(_.getTableName == traversalHop.getSourceTableName)
          val destinationNodes: Seq[ImplementationNode] = gTop.getImplementationLevel.getImplementationNodes.asScala
            .filter(_.getTableName == traversalHop.getDestinationTableName)
          assert(sourceNodes.nonEmpty && destinationNodes.nonEmpty, "Unknown tables")

          (edge, traversalHop, sourceNodes, destinationNodes)
        }
    }
  }

}
