package ingraph.compiler.sql.driver

import java.lang
import java.sql.{Statement => sqlStatement}

import com.google.gson.JsonParser
import ingraph.compiler.sql.Util.withResources
import ingraph.compiler.sql._
import ingraph.driver.{CypherDriver, CypherDriverFactory}
import org.apache.log4j.{Level, LogManager}
import org.neo4j.driver.v1._
import org.neo4j.driver.v1.exceptions.NoSuchRecordException
import org.postgresql.jdbc.PgArray
import org.postgresql.util.PGobject

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
