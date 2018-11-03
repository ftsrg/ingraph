package ingraph.compiler.sql

import ingraph.compiler.sql.CompileSql.{getNodes, getSql}
import ingraph.compiler.sql.GTopExtension._
import ingraph.compiler.sql.IndentationPreservingStringInterpolation._
import ingraph.compiler.sql.driver.ValueJsonConversion
import ingraph.compiler.test.CompilerTest
import ingraph.model.expr._
import ingraph.model.misc.{Function, FunctionCategory}
import ingraph.model.{fplan, nplan}
import org.apache.spark.sql.catalyst.expressions.{BinaryComparison, BinaryOperator, CaseWhen, Expression, IsNotNull, IsNull, Literal, Not}
import org.apache.spark.sql.types.{LongType, StringType}
import org.cytosm.common.gtop.GTop
import org.cytosm.common.gtop.implementation.relational.{ImplementationEdge, ImplementationNode, TraversalHop}
import org.neo4j.driver.v1.{Value, Values}

import scala.collection.JavaConverters._

object CompileSql {
  def escapeQuotes(name: String, toBeDoubled: String = "\"") = name.replace(toBeDoubled, toBeDoubled + toBeDoubled)

  def escapeSingleQuotes(string: String) = escapeQuotes(string, "'")

  def getSingleQuotedString(string: String): String =
    '\'' + escapeSingleQuotes(string) + '\''

  def getQuotedColumnName(name: String): String =
    '"' + escapeQuotes(name) + '"'

  def getQuotedColumnName(resolvableName: ResolvableName): String =
    getQuotedColumnName(resolvableName.resolvedName.get.resolvedName)

  def getNodes(plan: fplan.FNode): Seq[fplan.FNode] = {
    plan.children.flatMap(getNodes) :+ plan
  }

  def getSqlForProperty(requiredPropety: ResolvableName, nnode: nplan.GetEdges): String = {
    requiredPropety match {
      case prop: PropertyAttribute => {
        val propertyTableName =
          prop.elementAttribute match {
            case _: VertexAttribute => "vertex_property"
            case _: EdgeAttribute => "edge_property"
          }
        // prop.elementAttribute.resolvedName can be invalid in this context because of renaming,
        //  since it has the name of the column as it is in the outermost part of the query.
        //  here is a workaround
        val parentColumnName = getQuotedColumnName(nnode.output.find(_.name == prop.elementAttribute.name).get)
        val propertyName = getSingleQuotedString(prop.name)
        val newPropertyColumnName = getQuotedColumnName(prop)

        s"""(SELECT value FROM $propertyTableName WHERE parent = $parentColumnName AND key = $propertyName) AS $newPropertyColumnName"""
      }
      case _ => ""
    }
  }

  def getProjectionSql(renamePairs: Traversable[(String, String)], childSql: String, options: CompilerOptions): String = {
    val columns = renamePairs.map(pair => s"""${pair._1} AS ${pair._2}""").mkString(", ")
    i"""SELECT $columns
       |  FROM $childSql AS subquery"""
  }

  /** (missing properties, SQL code) */
  def getGetEdgesSql(node: fplan.GetEdges, options: CompilerOptions,
                     edgeTuple: (ImplementationEdge, TraversalHop, ImplementationNode, ImplementationNode))
  : (Set[PropertyAttribute], String) = {
    assert(node.nnode.directed, "Cannot compile undirected GetEdges. Use UnionAll instead.")
    val (edge, traversalHop, sourceNode, destinationNode) = edgeTuple

    assert(sourceNode.getId.size == 1 && destinationNode.getId.size == 1, "No support for composite keys")
    val sourceIdColumn = sourceNode.getId.get(0).getColumnName
    val destinationIdColumn = destinationNode.getId.get(0).getColumnName

    val fromTableAlias = "fromTable"
    val toTableAlias = "toTable"

    // in case of a 1-to-n relation,
    // if the edge is represented by a column in one of the vertex tables
    val (vertexTableBasedEdgeTableNode, edgeTableAliasOverride) =
    if (!traversalHop.getJoinTableName.isEmpty)
      None -> None
    else {
      if (traversalHop.getDestinationTableColumn == destinationIdColumn)
        Some(sourceNode) -> Some(fromTableAlias)
      else if (traversalHop.getSourceTableColumn == sourceIdColumn)
        Some(destinationNode) -> Some(toTableAlias)
      else
        throw new IllegalArgumentException("Couldn't determine which table has foreign key to the other.")
    }

    val edgeTableAlias = edgeTableAliasOverride.getOrElse("edgeTable")

    val (edgeTable, fromColumn, toColumn) =
      if (traversalHop.getJoinTableName.isEmpty) {
        val (sourceColumn, destinationColumn) =
          if (vertexTableBasedEdgeTableNode.get == sourceNode)
            sourceIdColumn -> traversalHop.getSourceTableColumn
          else
            traversalHop.getDestinationTableColumn -> destinationIdColumn

        (
          getQuotedColumnName(vertexTableBasedEdgeTableNode.get.getTableName),
          getQuotedColumnName(sourceColumn),
          getQuotedColumnName(destinationColumn))
      }
      else {
        (
          getQuotedColumnName(traversalHop.getJoinTableName),
          getQuotedColumnName(traversalHop.getJoinTableSourceColumn),
          getQuotedColumnName(traversalHop.getJoinTableDestinationColumn))
      }

    val edgeTypeId = options.gTop.get.getEdgeTypeIdMap(edgeTuple._1.getTypes.get(0))
    val edgeColumn = s"ROW($edgeTypeId, $edgeTableAlias.$fromColumn, $edgeTableAlias.$toColumn)::edge_type"

    val fromColumnNewName = getQuotedColumnName(node.nnode.src)
    val edgeColumnNewName = getQuotedColumnName(node.nnode.edge)
    val toColumnNewName = getQuotedColumnName(node.nnode.trg)

    val propertiesWithTablesAndMissing =
      node.requiredProperties.map {
        case prop@PropertyAttribute(propName, element, _) => {
          val (tableName, attributes) = element match {
            case vertex: VertexAttribute if vertex.resolvedName == node.nnode.src.resolvedName =>
              (fromTableAlias, sourceNode.getAttributes)
            case vertex: VertexAttribute if vertex.resolvedName == node.nnode.trg.resolvedName =>
              (toTableAlias, destinationNode.getAttributes)
            case _: EdgeAttribute =>
              (edgeTableAlias, traversalHop.getAttributes)
          }
          val attributeInTableIfExists = attributes.asScala.find(_.getAbstractionLevelName == propName)

          if (attributeInTableIfExists.isEmpty) {
            prop -> None
          } else {
            val columnName = attributeInTableIfExists.get.getColumnName
            val newName = getQuotedColumnName(prop)

            prop -> Some(tableName, columnName, newName)
          }
        }
      }.toMap
    val propertiesWithTables = propertiesWithTablesAndMissing.values.flatten
    val missingProperties = propertiesWithTablesAndMissing.filter(_._2.isEmpty).keySet

    val extraColumns = propertiesWithTables
      .map { case (tableAlias, columnName, newName) =>
        tableAlias + '.' + getQuotedColumnName(columnName) + " AS " + newName
      }
    val extraColumnsPart = extraColumns.mkString(", ")
    val commaBeforeExtraColumns =
      if (extraColumns.nonEmpty) ","
      else ""

    val sourceRestrictions = sourceNode.getRestrictions.createConstraint(fromTableAlias)
    val destinationRestrictions = destinationNode.getRestrictions.createConstraint(toTableAlias)
    val restrictions: String = getWhereClauseOrEmpty(sourceRestrictions ++ destinationRestrictions)

    val extraTablesToJoin = (propertiesWithTables
      .map { case (tableName, _, _) => tableName }
      .toSet
      ++ (if (sourceRestrictions.nonEmpty) Some(fromTableAlias) else None)
      ++ (if (destinationRestrictions.nonEmpty) Some(toTableAlias) else None)
      // We already have the edgeTable. No need to join again.
      - edgeTableAlias)
    val joinVertexTablesPart =
      extraTablesToJoin.map { tableAlias =>
        val sourceTableName = getQuotedColumnName(traversalHop.getSourceTableName)
        val joinSourceColumn = fromColumn
        val sourceColumn = getQuotedColumnName(traversalHop.getSourceTableColumn)

        val destinationTableName = getQuotedColumnName(traversalHop.getDestinationTableName)
        val destinationColumn = getQuotedColumnName(traversalHop.getDestinationTableColumn)
        val joinDestinationColumn = toColumn

        if (tableAlias == fromTableAlias)
          s"JOIN $sourceTableName $fromTableAlias ON ($fromTableAlias.$sourceColumn = $edgeTableAlias.$joinSourceColumn)"
        else if (tableAlias == toTableAlias)
          s"JOIN $destinationTableName $toTableAlias ON ($edgeTableAlias.$joinDestinationColumn = $toTableAlias.$destinationColumn)"
        else
          throw new NoSuchElementException("Unexpected table: " + tableAlias);
      }
        .mkString("\n")

    val vertexTableIdMap = options.gTop.get.getVertexTableIdMap
    val fromTableId = vertexTableIdMap(traversalHop.getSourceTableName)
    val toTableId = vertexTableIdMap(traversalHop.getDestinationTableName)

    val sql =
      i"""SELECT ROW($fromTableId, $edgeTableAlias.$fromColumn)::vertex_type AS $fromColumnNewName, $edgeColumn AS $edgeColumnNewName, ROW($toTableId, $edgeTableAlias.$toColumn)::vertex_type AS $toColumnNewName$commaBeforeExtraColumns
         |  $extraColumnsPart
         |  FROM $edgeTable $edgeTableAlias
         |    $joinVertexTablesPart
         |$restrictions"""

    missingProperties -> sql
  }

  def getGetEdgesSql(node: fplan.GetEdges, options: CompilerOptions): String = {
    assert(node.nnode.directed, "Cannot compile undirected GetEdges. Use UnionAll instead.")

    val edgeLabels = node.nnode.edge.labels.edgeLabels

    val edgeTable = "edge"
    val fromColumn = "\"from\""
    val toColumn = "\"to\""
    val edgeColumn = "edge_id"

    val fromColumnNewName = getQuotedColumnName(node.nnode.src)
    val edgeColumnNewName = getQuotedColumnName(node.nnode.edge)
    val toColumnNewName = getQuotedColumnName(node.nnode.trg)

    // TODO handle properties from edge lists

    val extraColumns = node.requiredProperties.map(getSqlForProperty(_, node.nnode))
    val extraColumnsPart = extraColumns.mkString(", ")
    val commaBeforeExtraColumns =
      if (extraColumns.nonEmpty) ","
      else ""

    val edgeLabelsEscaped = edgeLabels.map(name => getSingleQuotedString(name))
    val typeConstraint =
      if (edgeLabelsEscaped.isEmpty) None
      else Some(s"type IN (${edgeLabelsEscaped.mkString(", ")})")

    val vertexLabelConstraints =
      Map(fromColumnNewName -> node.src, toColumnNewName -> node.trg)
        .mapValues(_.labels)
        .flatMap { case (columnName, labelSet) => getVertexLabelSqlCondition(labelSet, columnName) }
        .toSeq

    i"""SELECT $fromColumnNewName, $edgeColumnNewName, $toColumnNewName$commaBeforeExtraColumns $extraColumnsPart FROM
       |  (
       |    SELECT "from" AS $fromColumnNewName, edge_id AS $edgeColumnNewName, "to" AS $toColumnNewName FROM edge
       |    ${getWhereClauseOrEmpty(typeConstraint)}
       |  ) subquery
       |${getWhereClauseOrEmpty(vertexLabelConstraints)}"""
  }

  def getWhereClauseOrEmpty(conjunctiveConstraints: Iterable[String]): String = {
    if (conjunctiveConstraints.isEmpty) ""
    else i"WHERE ${conjunctiveConstraints.mkString(" AND\n")}"
  }

  def getSql(node: Any, options: CompilerOptions): String = {
    val sqlString =
      node match {
        case IsNull(child) => IsNull(ExpressionWrapper(child, options)).sql
        case IsNotNull(child) => IsNotNull(ExpressionWrapper(child, options)).sql
        case ListExpression(list) if options.gTop.isDefined =>
          // use Postgres array if GTop is present
          // TODO: make array types consistent
          "ARRAY[" + list.map(getSql(_, options)).mkString(", ") + "]"
        case ListExpression(list) if options.gTop.isEmpty =>
          "driver_array(ARRAY[" + list.map(getSql(_, options)).mkString(", ") + "]::jsonb[])"
        case CaseWhen(branches, elseValue) => {
          val branchesPart = branches
            .map { case (c, v) => getSql(c, options) -> getSql(v, options) }
            .map { case (condition, value) => s"WHEN $condition THEN $value" }
            .mkString("\n")
          val elsePart = elseValue
            .map(getSql(_, options))
            .map("ELSE " + _)
            .getOrElse("")

          i"""CASE $branchesPart
             |     $elsePart
             |END"""
        }
        case node: ReturnItem => getSql(node.child, options)
        case FunctionInvocation(Function.LENGTH, Seq(edgeListAttribute: EdgeListAttribute), false) =>
          "array_length(" + getSql(edgeListAttribute, options) + ")"
        case FunctionInvocation(Function.NODE_HAS_LABELS, (vertexColumn: VertexAttribute) :: (vertexLabelSet: VertexLabelSet) :: Nil, false) => {
          getVertexLabelSqlCondition(vertexLabelSet, getQuotedColumnName(vertexColumn)).get
        }
        case FunctionInvocation(Function.EXISTS, Seq(propertyAttribute: PropertyAttribute), false) => {
          getSql(propertyAttribute, options) + " IS NOT NULL"
        }
        case FunctionInvocation(functor, Seq(expr: Expression), false)
          if functor.getCategory == FunctionCategory.CONVERSION => {

          val sqlType = functor match {
            case Function.TOBOOLEAN => "BOOLEAN"
            case Function.TOINTEGER | Function.TOINT => "BIGINT"
            case Function.TOFLOAT => "DOUBLE PRECISION"
            case Function.TOSTRING => "TEXT"
          }
          "(" + getSql(expr, options.copy(unwrapJson = true)) + ")::" + sqlType
        }
        case FunctionInvocation(functor@Function.COUNT_ALL, Nil, false) => {
          functor.getPrettyName
        }
        case node: FunctionInvocation => {
          val functionName = node.functor match {
            case Function.COLLECT => "array_agg"
            // ignore ID function since the column already contains the ID
            case functor@Function.ID => "/*" + functor.getPrettyName + "*/"
            case functor => functor.getPrettyName
          }
          val parametersString = node.children.map(getSql(_, options)).mkString(", ")
          val distinctPart = if (node.isDistinct) "DISTINCT " else ""

          functionName + "(" + distinctPart + parametersString + ")"
        }
        case node: BinaryOperator => {
          // convert Literals to JSON only if they are compared to JSON results
          val localUnwrapJson = node.isInstanceOf[BinaryComparison] &&
            node.children.forall {
              case _: PropertyAttribute => false
              case FunctionInvocation(Function.TYPE, _, _) => false
              case _ => true
            }

          val leftSql = getSql(node.left, options.copy(unwrapJson = localUnwrapJson))
          val rightSql = getSql(node.right, options.copy(unwrapJson = localUnwrapJson))
          val operator = node.sqlOperator

          if (Seq(leftSql, rightSql).exists(_.contains("\n")))
            i"""(
               |  $leftSql
               |$operator
               |  $rightSql
               |)"""
          else
            s"($leftSql $operator $rightSql)"
        }
        case node: ResolvableName => {
          val unwrapPart =
            if (options.unwrapJson && node.isInstanceOf[PropertyAttribute] && options.gTop.isEmpty)
              "->'value'->>'val'"
            else
              ""
          getQuotedColumnName(node) + unwrapPart
        }
        case node: IndexLookupExpression => {
          // TODO support indexing of SQL arrays too
          // JSON indexing
          // {"type": "ListValue", "value": {"values": [{"type": "StringValue", "value": {"val": "a"}}, {"type": "StringValue", "value": {"val": "b"}}]}}
          getSql(node.collection, options) + "->'value'->'values'->" + node.index.toString
        }
        case node: Literal => getSqlForLiteral(node, options)
        case node: Not => "NOT(" + getSql(node.child, options) + ")"
        case node: Parameter if options.inlineParameters => {
          val value = options.parameters(node.name)
          getSqlForLiteral(Literal(value), options)
        }
        case node: Parameter if !options.inlineParameters => {
          ":" + node.name
        }
      }

    sqlString
  }

  def convertAttributeAtProductionNode(attribute: ResolvableName): String = {
    val columnName = getQuotedColumnName(attribute)

    attribute match {
      case ReturnItem(VertexAttribute(_, _, _, _, _), _, _) => "to_vertex(" + columnName + ")"
      case ReturnItem(EdgeAttribute(_, _, _, _, _), _, _) => "to_edge(" + columnName + ")"
      case _ => columnName
    }
  }

  def getSqlForLiteral(literal: Literal, options: CompilerOptions): String = {
    if (options.unwrapJson || options.gTop.isDefined) {
      val sqlString = literal.dataType match {
        // SQL database cannot parse literal suffix (e.g. 42L)
        case _: LongType => literal.value.toString
        case _ => literal.sql
      }

      sqlString
    }
    else {
      val pojoValue = literal.dataType match {
        // https://github.com/apache/spark/blob/b3d88ac02940eff4c867d3acb79fe5ff9d724e83/sql/catalyst/src/main/scala/org/apache/spark/sql/catalyst/expressions/literals.scala#L59
        // String is stored in other type, needs conversion before JSON serialization
        case _: StringType => literal.value.toString
        case _ => literal.value
      }

      val value = Values.value(pojoValue)
      val jsonString = ValueJsonConversion.gson.toJson(value, classOf[Value])
      val sqlStringLiteral = Literal(jsonString)

      sqlStringLiteral.sql + "::jsonb"
    }
  }

  def getVertexLabelSqlCondition(labelSet: VertexLabelSet, columnName: String): Option[String] = {
    val labelsAsRowValues = labelSet.vertexLabels.map("(" + getSingleQuotedString(_) + ")")

    if (labelsAsRowValues.isEmpty)
      None
    else
      Some(
        s"""NOT EXISTS(VALUES ${labelsAsRowValues.mkString(", ")}
           |           EXCEPT ALL
           |           SELECT name
           |           FROM label
           |           WHERE parent = $columnName)""".stripMargin)
  }
}

class CompileSql(val cypherQuery: String, compilerOptions: CompilerOptions = CompilerOptions()) extends CompilerTest {

  val stages = compile(cypherQuery)

  val fPlan = stages.fplan

  val modifiedCompilerOptions = compilerOptions
    .copy(useSubQueries = compilerOptions.useSubQueries && !getNodes(fPlan).exists(_.isInstanceOf[fplan.Create]))

  // TODO don't execute it more than once
  def sqlNode = SqlNode(fPlan, modifiedCompilerOptions)._1

  def sql = sqlNode.sql.get

  def run(): String = {
    println(sql)
    sql
  }

}

case class CompilerOptions(parameters: Map[String, Any] = Map(),
                           gTop: Option[GTop] = None,
                           nodeId: Int = 0,
                           unwrapJson: Boolean = false,
                           inlineParameters: Boolean = true,
                           useSubQueries: Boolean = true) {}

case class ExpressionWrapper(expr: Expression, options: CompilerOptions) extends ExpressionBase {
  override def children: Seq[Expression] = expr.children

  override def productElement(n: Int): Any = expr.productElement(n)

  override def productArity: Int = expr.productArity

  override def canEqual(that: Any): Boolean = expr.canEqual(that)

  override def sql: String = getSql(expr, options)
}
