package ingraph.compiler.sql

import java.io.File

import ingraph.compiler.sql.CompileSql.{getQuotedColumnName, getSingleQuotedString}
import ingraph.model.expr.types.{EdgeLabel, VertexLabel}
import org.cytosm.common.gtop.GTop
import org.cytosm.common.gtop.implementation.relational._
import org.cytosm.common.gtop.io.SerializationInterface

import scala.collection.JavaConverters._

object GTopExtension {

  def loadFromFile(path: String): GTop = {
    val gTop = SerializationInterface.read(new File(path))
    addIdColumnToGTopAttributes(gTop)

    gTop
  }

  def loadFromResource(resourcePath: String): GTop = {
    loadFromFile(getClass.getResource(resourcePath).getFile)
  }

  def addIdColumnToGTopAttributes(gTop: GTop): Unit = {
    val idAttributeName = "id"

    gTop.getAbstractionLevel.getAbstractionNodes.asScala.foreach { node =>
      val attributes = node.getAttributes
      if (!attributes.asScala.contains(idAttributeName))
        attributes.add(idAttributeName)
    }

    gTop.getImplementationLevel.getImplementationNodes.asScala.foreach { node =>
      assert(node.getId.size == 1, "Only supports single key")
      val nodeId = node.getId.get(0)

      if (!node.getAttributes.asScala.exists(_.getAbstractionLevelName == idAttributeName))
        node.getAttributes.add(new Attribute(nodeId.getColumnName, idAttributeName, nodeId.getDatatype))
    }
  }

  implicit class GTopClass(val gTop: GTop) extends AnyVal {
    def getVertexTableIdMap: Map[String, Int] = {
      val tables: Seq[String] = gTop.getImplementationLevel.getImplementationNodes.asScala
        .map(_.getTableName)
        .distinct

      tables.zipWithIndex.toMap
    }

    def getEdgeTypeIdMap: Map[String, Int] = {
      // distinct is needed since the same edge type can connect multiple node types,
      // therefore it can be listed multiple times
      val edgeTypes: Seq[String] = gTop.getAbstractionLevel.getAbstractionEdges.asScala
        .flatMap(_.getTypes.asScala)
        .distinct

      edgeTypes.zipWithIndex.toMap
    }

    private def findEdgeTables(edgeLabels: Set[EdgeLabel]): Seq[(ImplementationEdge, TraversalHop, Seq[ImplementationNode], Seq[ImplementationNode])] = {
      gTop
        .getImplementationLevel.getImplementationEdges.asScala
        // GTop extension for attributes stored in join tables
        .filterNot(_.getTypes.size == 0)
        .filter { implEdge =>
          assert(implEdge.getTypes.size == 1)

          implEdge.getTypes.asScala.toSet.intersect(edgeLabels).nonEmpty ||
            edgeLabels.isEmpty // no constraint on edge type
        }
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

    def findEdgeTuples(edgeLabels: Set[EdgeLabel],
                       sourceVertexLabels: Set[VertexLabel],
                       destinationVertexLabels: Set[VertexLabel]): Seq[(ImplementationEdge, TraversalHop, ImplementationNode, ImplementationNode)] = {
      val edgeTableTuples = findEdgeTables(edgeLabels)

      val possibleSourceNodes = findVertexTable(sourceVertexLabels)
      val possibleDestinationNodes = findVertexTable(destinationVertexLabels)

      edgeTableTuples.flatMap { case tuple@(_, _, sourceNodes, destinationNodes) =>
        sourceNodes.flatMap(sourceNode =>
          destinationNodes.map(destinationNode =>
            tuple.copy(_3 = sourceNode, _4 = destinationNode)
          )
        )
      }.filter { case (_, _, sourceNode, destinationNode) =>
        possibleSourceNodes.contains(sourceNode) && possibleDestinationNodes.contains(destinationNode)
      }
    }

    def findVertexTable(vertexLabelConstraints: Set[VertexLabel]): Seq[ImplementationNode] = {
      gTop.getImplementationLevel
        .getImplementationNodes.asScala
        .filter(node =>
          // all vertex label constraints from the pattern must be satisfied,
          // i.e. the implementation nodes should contain all of them
          // empty = {vertex label constraints} \ {types in the table}
          vertexLabelConstraints.diff(node.getTypes.asScala.toSet).isEmpty)
    }
  }

  implicit class RestrictionClauseClass(val restrictionClause: RestrictionClause) extends AnyVal {
    /** (tableName, constraint) */
    def createConstraint(tableAlias: String): String = {
      val customSqlPrefix = "SQL:"
      val pattern = restrictionClause.getPattern
      val condition =
        if (pattern == null) "IS NULL"
        else if (pattern.startsWith(customSqlPrefix)) pattern.substring(customSqlPrefix.length)
        else ":: text ~ " + getSingleQuotedString(pattern)

      val columnNameQuoted = getQuotedColumnName(restrictionClause.getColumnName)

      s"$tableAlias.$columnNameQuoted $condition"
    }
  }

  implicit class RestrictionClausesClass(val restrictionClauses: RestrictionClauses) extends AnyVal {
    /** (required tables, constraint) */
    def createConstraint(tableAlias: String): String = {
      val constraints = restrictionClauses.getRestrictionClause.asScala.map(_.createConstraint(tableAlias))
      val combinedConstraint = '(' + constraints.mkString(" OR ") + ')'

      combinedConstraint
    }
  }

  implicit class MultipleRestrictionClausesClass(val multipleClauses: Iterable[RestrictionClauses]) extends AnyVal {
    /** (required tables, constraints to be conjuncted) */
    def createConstraint(tableAlias: String): Seq[String] = {
      multipleClauses.map(_.createConstraint(tableAlias)).toSeq
    }
  }

  implicit class MultipleRestrictionClausesJavaClass(val multipleClauses: java.lang.Iterable[RestrictionClauses]) extends AnyVal {
    /** (required tables, constraints to be conjuncted) */
    def createConstraint(tableAlias: String): Seq[String] = {
      multipleClauses.asScala.createConstraint(tableAlias)
    }
  }

}
