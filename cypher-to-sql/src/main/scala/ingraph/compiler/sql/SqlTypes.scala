package ingraph.compiler.sql

import ingraph.compiler.sql.CompileSql.vertexTableIdPostfix
import ingraph.model.expr.types.{TResolvedName, TResolvedNameValue}
import ingraph.model.expr.{AttributeBase, ExpressionBase, ResolvableName, VertexAttribute}
import org.apache.spark.sql.catalyst.expressions.{BinaryExpression, Expression, LeafExpression, UnaryExpression}

trait SqlColumnWrapper
  extends AttributeBase with ResolvableName

case class VertexColumnWithSeparateTableId(vertexAttribute: VertexAttribute, vertexTableIdColumn: VertexTableIdColumn)
  extends SqlColumnWrapper {

  override def resolvedName: TResolvedName = vertexAttribute.resolvedName

  override def name: String = vertexAttribute.name

  def getBothColumns: Seq[ResolvableName] = Seq(this, vertexTableIdColumn)
}

object VertexColumnWithSeparateTableId {
  val postfixPattern = "_[^_]+$".r

  def replaceWithVertexAttribute(columns: Seq[ResolvableName]): Seq[ResolvableName] = {
    columns.map { originalCol =>
      val unwrappedCol = RenamedColumn.unwrap(originalCol)

      unwrappedCol match {
        case v: VertexColumnWithSeparateTableId => RenamedColumn.rename(v.vertexAttribute, originalCol)
        case _ => originalCol
      }
    }
  }

  def replaceColumn(column: ResolvableName): Seq[ResolvableName] =
    column match {
      // remove id columns without vertex column
      // add id columns for every vertex column
      case vertexColumn@VertexColumnWithSeparateTableId(_, idColumn) => Seq(idColumn, vertexColumn)
      case _: VertexTableIdColumn => Seq()
      case renamedColumn: RenamedColumn => replaceRenamedColumn(renamedColumn)
      case col => Seq(col)
    }

  def replaceRenamedColumn(renamedColumn: RenamedColumn): Seq[ResolvableName] = {
    val newName = renamedColumn.resolvedName.get
    val unwrappedColumn = RenamedColumn.unwrap(renamedColumn)
    val newColumns = replaceColumn(unwrappedColumn)

    newColumns.map { newCol =>
      val postfix = postfixPattern.findFirstIn(newCol.resolvedName.get.resolvedName).getOrElse("")

      RenamedColumn.rename(newCol, TResolvedNameValue(newName.baseName + postfix, newName.resolvedName + postfix))
    }
  }

  private def replaceAliasOfResolvableName(alias: AliasWithNewResolvableName): Seq[AliasWithNewResolvableName] = {
    alias match {
      case AliasWithNewResolvableName(column: ResolvableName, newResolvedName) => {
        val newColumns = replaceColumn(column)

        newColumns.map { newCol =>
          val postfix = postfixPattern.findFirstIn(newCol.resolvedName.get.resolvedName).getOrElse("")

          alias.copy(child = newCol,
            newResolvedName = TResolvedNameValue(newResolvedName.baseName + postfix, newResolvedName.resolvedName + postfix))
        }
      }
    }
  }

  def replaceExpression(expr: Expression): Seq[Expression] =
    expr match {
      case resolvableName: ResolvableName => replaceColumn(resolvableName)
      case alias@AliasWithNewResolvableName(subqueryRef: SubqueryAttributeReference, newResolvedName) =>
        // unwrap subquery reference, transform, and wrap back
        replaceAliasOfResolvableName(alias.copy(child = subqueryRef.child))
          .map(newAlias =>
            newAlias.copy(child = subqueryRef.copy(child = newAlias.child.asInstanceOf[ResolvableName])))
      case alias@AliasWithNewResolvableName(_: ResolvableName, _) =>
        replaceAliasOfResolvableName(alias)
      case attrRef: SubqueryAttributeReference =>
        replaceColumn(attrRef.child).map(newCol => attrRef.copy(child = newCol))
      case _ => Seq(expr)
    }

  def ensureVertexAndIdColumnsBothPresent(columns: Seq[ResolvableName]): Seq[ResolvableName] = {
    columns.flatMap(replaceColumn)
  }

  def ensureVertexAndIdColumnsBothPresentInExpression(columns: Seq[Expression]): Seq[Expression] = {
    columns.flatMap(replaceExpression)
  }
}

case class VertexTableIdColumn(vertexAttribute: VertexAttribute,
                               tableName: String,
                               tableId: Int) extends SqlColumnWrapper {
  override def resolvedName: TResolvedName = vertexAttribute.resolvedName
    .map(orig => TResolvedNameValue(orig.baseName + vertexTableIdPostfix, orig.resolvedName + vertexTableIdPostfix))

  override def name: String = vertexAttribute.name + vertexTableIdPostfix
}

case class SubqueryAttributeReference(subqueryName: String, child: ResolvableName)
  extends UnaryExpression with ExpressionBase

case class AliasWithNewResolvableName(child: Expression, newResolvedName: TResolvedNameValue)
  extends UnaryExpression with ExpressionBase

case class RenamedColumn(originalColumn: ResolvableName, override val name: String, override val resolvedName: TResolvedName)
  extends SqlColumnWrapper

object RenamedColumn {
  def apply(originalColumn: ResolvableName, newResolvedName: TResolvedNameValue): RenamedColumn = {
    RenamedColumn(originalColumn, newResolvedName.baseName, Some(newResolvedName))
  }

  def unwrap(column: ResolvableName): ResolvableName = {
    column match {
      case RenamedColumn(originalColumn, _, _) => unwrap(originalColumn)
      case _ => column
    }
  }

  def rename(column: ResolvableName, newNameFrom: ResolvableName): ResolvableName = {
    rename(column, newNameFrom.resolvedName.get)
  }

  def rename(column: ResolvableName, newResolvedNameValue: TResolvedNameValue): ResolvableName = {
    if (column.resolvedName.get == newResolvedNameValue)
      column
    else
      RenamedColumn(column, newResolvedNameValue)
  }
}

case class EmptyArray(dataTypeString: String) extends LeafExpression with ExpressionBase

// should not inherit from BinaryOperator since the input types can differ
case class ConcatArray(override val left: Expression, override val right: Expression)
  extends BinaryExpression with ExpressionBase
