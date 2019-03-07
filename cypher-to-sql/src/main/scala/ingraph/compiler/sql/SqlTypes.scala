package ingraph.compiler.sql

import ingraph.compiler.sql.CompileSql.vertexTableIdPostfix
import ingraph.model.expr.types.{TResolvedName, TResolvedNameValue}
import ingraph.model.expr.{AttributeBase, ExpressionBase, ResolvableName, VertexAttribute}
import org.apache.spark.sql.catalyst.expressions.UnaryExpression

abstract class SqlColumnWrapper
  extends AttributeBase with ResolvableName

case class VertexColumnWithSeparateTableId(vertexAttribute: VertexAttribute, vertexTableIdColumn: VertexTableIdColumn)
  extends SqlColumnWrapper {

  override def resolvedName: TResolvedName = vertexAttribute.resolvedName

  override def name: String = vertexAttribute.name

  def getBothColumns: Seq[ResolvableName] = Seq(this, vertexTableIdColumn)
}

object VertexColumnWithSeparateTableId {
  def ensureVertexAndIdColumnsBothPresent(columns: Seq[ResolvableName]): Seq[ResolvableName] = {
    // remove id columns without vertex column
    // add id columns for every vertex column
    columns.flatMap {
      case vertexColumn@VertexColumnWithSeparateTableId(_, idColumn) => Seq(vertexColumn, idColumn)
      case _: VertexTableIdColumn => Seq()
      case col => Seq(col)
    }
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
