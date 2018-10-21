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
    private def findEdgeTables(edgeLabels: Set[EdgeLabel]): Seq[(ImplementationEdge, TraversalHop, Seq[ImplementationNode], Seq[ImplementationNode])] = {
      gTop
        .getImplementationLevel.getImplementationEdges.asScala
        .filter(_.getTypes.asScala.toSet.intersect(edgeLabels).nonEmpty
          || edgeLabels.isEmpty /* no constraint on edge type */)
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
    def createConstraint(): (String, String) = {
      val customSqlPrefix = "SQL:"
      val pattern = restrictionClause.getPattern
      val condition =
        if (pattern == null) "IS NULL"
        else if (pattern.startsWith(customSqlPrefix)) pattern.substring(customSqlPrefix.length)
        else ":: text ~ " + getSingleQuotedString(pattern)


      val tableNameQuoted = getQuotedColumnName(restrictionClause.getTableName)
      val columnNameQuoted = getQuotedColumnName(restrictionClause.getColumnName)

      restrictionClause.getTableName -> s"$tableNameQuoted.$columnNameQuoted $condition"
    }
  }

  implicit class RestrictionClausesClass(val restrictionClauses: RestrictionClauses) extends AnyVal {
    /** (required tables, constraint) */
    def createConstraint(): (Set[String], String) = {
      val tableConstraintPairs = restrictionClauses.getRestrictionClause.asScala.map(_.createConstraint())
      val requiredTables = tableConstraintPairs.map(_._1).toSet
      val constraints = tableConstraintPairs.map(_._2)
      val combinedConstraint = '(' + constraints.mkString(" OR ") + ')'

      requiredTables -> combinedConstraint
    }
  }

  implicit class MultipleRestrictionClausesClass(val multipleClauses: Iterable[RestrictionClauses]) extends AnyVal {
    /** (required tables, constraints to be conjuncted) */
    def createConstraint(): (Set[String], Seq[String]) = {
      val tablesConstraintPairs = multipleClauses.map(_.createConstraint())
      val requiredTables = tablesConstraintPairs.flatMap(_._1).toSet
      val constraints = tablesConstraintPairs.map(_._2).toSeq

      requiredTables -> constraints
    }
  }

  implicit class MultipleRestrictionClausesJavaClass(val multipleClauses: java.lang.Iterable[RestrictionClauses]) extends AnyVal {
    /** (required tables, constraints to be conjuncted) */
    def createConstraint(): (Set[String], Seq[String]) = {
      multipleClauses.asScala.createConstraint()
    }
  }

}
