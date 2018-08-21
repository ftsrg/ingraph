package ingraph.compiler.sql

import java.lang
import java.sql.{Connection, DriverManager, ResultSet, Statement => sqlStatement}
import java.util.{List => javaList, Map => javaMap}

import com.google.gson.JsonParser
import ingraph.compiler.sql.Util.withResources
import ingraph.driver.{CypherDriver, CypherDriverFactory}
import ingraph.model.fplan.Create
import org.apache.log4j.{Level, LogManager}
import org.neo4j.driver.internal.InternalRecord
import org.neo4j.driver.v1.exceptions.NoSuchRecordException
import org.neo4j.driver.v1.summary.ResultSummary
import org.neo4j.driver.v1.types._
import org.neo4j.driver.v1.{util, _}
import org.postgresql.jdbc.PgArray
import org.postgresql.util.PGobject

import scala.collection.JavaConverters._
import scala.collection.{immutable, mutable}

class SqlDriver extends CypherDriver {

  // TODO implement CREATE command instead of using Neo4j
  val backendDriver: CypherDriver = CypherDriverFactory.createNeo4jDriver()
  val backendSession: Session = backendDriver.session()

  LogManager.getRootLogger.setLevel(Level.OFF)
  val postgres = new EmbeddedPostgresWrapper
  val url = postgres.Url

  override def session: Session = new SqlSession(this)

  override def close(): Unit = {
    postgres.close()

    backendSession.close()
    backendDriver.close()
  }

  override def isEncrypted = false

  override def session(mode: AccessMode): Session = ???

  override def session(bookmark: String): Session = ???

  override def session(mode: AccessMode, bookmark: String): Session = ???

  override def session(bookmarks: lang.Iterable[String]): Session = ???

  override def session(mode: AccessMode, bookmarks: lang.Iterable[String]): Session = ???
}

class SqlSession(val sqlDriver: SqlDriver) extends Session {
  override def beginTransaction(): Transaction = new SqlTransaction(this)

  override def beginTransaction(bookmark: String): Transaction = ???

  override def readTransaction[T](work: TransactionWork[T]): T = ???

  override def writeTransaction[T](work: TransactionWork[T]): T = ???

  override def lastBookmark(): String = ???

  override def reset(): Unit = ???

  override def close(): Unit = {}

  override def run(statementTemplate: String, parameters: Value): StatementResult = ???

  override def run(statementTemplate: String, statementParameters: javaMap[String, AnyRef]): StatementResult = ???

  override def run(statementTemplate: String, statementParameters: Record): StatementResult = ???

  override def run(statementTemplate: String): StatementResult = ???

  override def run(statement: Statement): StatementResult = ???

  override def typeSystem(): TypeSystem = ???

  override def isOpen: Boolean = true
}

class SqlTransaction(val sqlSession: SqlSession) extends Transaction {

  val sqlConnection: Connection = DriverManager.getConnection(sqlSession.sqlDriver.url)
  sqlConnection.setAutoCommit(false)

  var toBeCommitted: Option[Boolean] = None

  override def success(): Unit = if (toBeCommitted.isEmpty) toBeCommitted = Some(true)

  override def failure(): Unit = toBeCommitted = Some(false)

  override def close(): Unit = {
    toBeCommitted match {
      case Some(true) => sqlConnection.commit()
      case _ => sqlConnection.rollback()
    }

    sqlConnection.close()
  }

  override def run(statementTemplate: String, parameters: Value): StatementResult =
    run(new Statement(statementTemplate, parameters))

  override def run(statementTemplate: String, statementParameters: javaMap[String, AnyRef]): StatementResult =
    run(new Statement(statementTemplate, statementParameters))

  override def run(statementTemplate: String, statementParameters: Record): StatementResult =
    run(statementTemplate, statementParameters.asMap())

  override def run(statementTemplate: String): StatementResult =
    run(new Statement(statementTemplate))

  override def run(statement: Statement): StatementResult = {
    val cypherQuery = statement.text
    val sqlCompiler = new CompileSql(cypherQuery, statement.parameters().asMap.asScala.toMap)

    withResources(sqlConnection.createStatement)(sqlStatement => {
      if (CompileSql.getNodes(sqlCompiler.fplan).exists(_.isInstanceOf[Create])) {
        val cypherSession = sqlSession.sqlDriver.backendSession
        cypherSession.run("MATCH (n) DETACH DELETE n")

        cypherSession.run(cypherQuery, statement.parameters)

        sqlStatement.executeUpdate(SqlQueries.createTables)

        ExportSteps.execute(cypherSession, sqlConnection)

        EmptySqlStatementResult
      }
      else {
        val sqlQuery = sqlCompiler.run()

        // TODO check lifecycle of ResultSet after closing sqlStatement if lazy loading is used
        new SqlStatementResult(sqlStatement.executeQuery(sqlQuery))
      }
    })
  }

  override def typeSystem(): TypeSystem = sqlSession.typeSystem()

  override def isOpen: Boolean = !sqlConnection.isClosed
}

class SqlStatementResult(resultSet: ResultSet) extends StatementResult {

  import SqlDriver._

  private val columnIndices = 1 to resultSet.getMetaData.getColumnCount

  val columnNames: immutable.IndexedSeq[String] = columnIndices.map(i => resultSet.getMetaData.getColumnName(i))

  override val keys: javaList[String] = columnNames.asJava

  // TODO use lazy loading
  private def loadResults(): immutable.IndexedSeq[Record] = {
    val buffer = mutable.Buffer[Record]()

    while (resultSet.next()) {
      val columnValues = columnIndices.map(resultSet.getObject).map(SqlDriver.toValue)

      buffer += new InternalRecord(keys, columnValues.toArray)
    }

    buffer.toIndexedSeq
  }

  val results: immutable.IndexedSeq[Record] = loadResults()

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

object SqlDriver {

  def dumpTable(sqlStatement: sqlStatement, tablename: String): Unit = {
    dump(sqlStatement, "SELECT * FROM " + tablename)
  }

  def dump(sqlStatement: sqlStatement, query: String): Unit = {
    withResources(sqlStatement.executeQuery(query))(rs => {
      println("vvvvvvvvvvv SQL RESULT vvvvvvvvvvv")

      val columnIndices = 1 to rs.getMetaData.getColumnCount
      columnIndices.foreach(i => println(rs.getMetaData.getColumnName(i) + ": " + rs.getMetaData.getColumnTypeName(i)))

      while (rs.next()) {
        println("---")
        columnIndices.foreach(i => println(rs.getMetaData.getColumnName(i) + " = " + rs.getObject(i)))
      }

      println("----------------------------------")
    })
  }

  private val jsonParser = new JsonParser

  def jsonToPojoValue(jsonString: String): Any = {
    val jsonPrimitive = jsonParser.parse(jsonString).getAsJsonPrimitive

    val pojoValue =
      if (jsonPrimitive.isBoolean)
        jsonPrimitive.getAsBoolean
      else if (jsonPrimitive.isNumber)
        jsonPrimitive.getAsLong
      else if (jsonPrimitive.isString)
        jsonPrimitive.getAsString

    pojoValue
  }

  def toValue(value: Any): Value = {
    value match {
      case value: PGobject if value.getType == "jsonb" => {
        toValue(jsonToPojoValue(value.getValue))
      }
      case value: PgArray => {
        val array = value.getArray.asInstanceOf[Array[_]]
          .map { element =>
            if (value.getBaseTypeName == "jsonb")
              jsonToPojoValue(element.asInstanceOf[String])
            else
              element
          }
          .map(toValue)

        toValue(array)
      }
      case default => Values.value(default)
    }
  }

  def throwNoMoreRecords =
    throw new NoSuchRecordException("No more records")

  def throwMoreRecordsThanOne =
    throw new NoSuchRecordException("Expected a result with a single record, but this result contains at least one more.")

  def throwNoSingleRecordInEmptyResult =
    throw new NoSuchRecordException("Cannot retrieve a single record, because this result is empty.")

  def throwCannotPeekPastLastRecord =
    throw new NoSuchRecordException("Cannot peek past the last record")
}
