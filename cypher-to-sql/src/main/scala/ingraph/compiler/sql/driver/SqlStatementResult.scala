package ingraph.compiler.sql.driver

import java.sql.ResultSet
import java.util.{List => javaList}

import ingraph.compiler.sql.driver.SqlDriver._
import org.neo4j.driver.internal.InternalRecord
import org.neo4j.driver.v1.summary.ResultSummary
import org.neo4j.driver.v1.{Record, StatementResult, util}

import scala.collection.JavaConverters._
import scala.collection.{immutable, mutable}

class SqlStatementResult(val columnNames: Seq[String], val results: Seq[Record]) extends StatementResult {

  override val keys: javaList[String] = columnNames.asJava

  private val iterator: java.util.Iterator[Record] = results.toIterator.asJava

  override def hasNext: Boolean = iterator.hasNext

  override def next(): Record = iterator.next()

  override def single(): Record = {
    if (!hasNext)
      throwNoSingleRecordInEmptyResult
    else {
      val single = next()

      val hasMoreThanOne = hasNext
      consume()
      if (hasMoreThanOne) throwMoreRecordsThanOne

      single
    }
  }

  override def peek(): Record = ???

  override def list(): javaList[Record] = results.asJava

  override def list[T](mapFunction: util.Function[Record, T]): javaList[T] =
    results.map(mapFunction(_)).asJava

  override def consume(): ResultSummary = {
    while (hasNext)
      next()

    null
  }

  override def summary(): ResultSummary = ???
}

object SqlStatementResult {
  // TODO use lazy loading
  def createFromResultSet(resultSet: ResultSet): SqlStatementResult = {
    val columnIndices = 1 to resultSet.getMetaData.getColumnCount

    val columnNames: immutable.IndexedSeq[String] = columnIndices.map(i => resultSet.getMetaData.getColumnName(i))

    val buffer = mutable.Buffer[Record]()

    while (resultSet.next()) {
      val columnValues = columnIndices.map(resultSet.getObject).map(SqlDriver.toValue)

      buffer += new InternalRecord(columnNames.asJava, columnValues.toArray)
    }

    new SqlStatementResult(columnNames, buffer.toIndexedSeq)
  }
}

object EmptySqlStatementResult extends StatementResult {

  import SqlDriver._

  override def keys(): javaList[String] = List[String]().asJava

  override def hasNext: Boolean = false

  override def next(): Record = throwNoMoreRecords

  override def single(): Record = throwNoSingleRecordInEmptyResult

  override def peek(): Record = throwCannotPeekPastLastRecord

  override def list(): javaList[Record] = List[Record]().asJava

  override def list[T](mapFunction: util.Function[Record, T]): javaList[T] = List[T]().asJava

  override def consume(): ResultSummary = null

  override def summary(): ResultSummary = ???
}
